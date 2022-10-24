package lt.mj.CountMyBill.service;

import lt.mj.CountMyBill.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
public class CalculationService {

    @Autowired
    MeasurementService measurementService = new MeasurementService();

    public double calculateConsumption(int counterId, LocalDateTime periodStartDate, LocalDateTime periodEndDate) {

        Iterable<Measurement> periodStartMeasurement = measurementService
                .getMeasurementsByCounterIdAndDateTimeBetween(counterId, periodStartDate);

        Iterable<Measurement> periodEndMeasurement = measurementService
                .getMeasurementsByCounterIdAndDateTimeBetween(counterId, periodEndDate);

        return calculateLowestMeasurementValue(periodEndMeasurement) -
                calculateLowestMeasurementValue(periodStartMeasurement);
    }

    public double calculateLowestMeasurementValue(Iterable<Measurement> iterable) {

        Collection<Measurement> measurements = new ArrayList<Measurement>();

        iterable.forEach(measurements::add);

        Measurement lowestMeasurement = measurements
                .stream()
                .min(Comparator.comparing(Measurement::getValue))
                .orElseThrow(NoSuchElementException::new);

        return lowestMeasurement.getValue();
    }

    public double calculateConsumptionPrice(double consumption, double servicePrice) {

        return consumption * servicePrice;

    }
}
