package lt.mj.CountMyBill.service;

import lt.mj.CountMyBill.model.Measurement;
import lt.mj.CountMyBill.repository.MeasurementRepository;
import lt.mj.CountMyBill.repository.RemoteData;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    public RemoteData remoteData;

    public Iterable<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Iterable<Measurement> getMeasurementsByCounterIdAndDateTimeBetween(int counterId, LocalDateTime date) {

        return measurementRepository.findMeasurementsByCounterIdAndDateTimeBetween(counterId,
                date,
                date.plusDays(1));
    }

    public void addMeasurement() throws IOException, ParseException {

        setMeasurement("el1", 4);
        setMeasurement("coldw", 1);
        setMeasurement("hotw", 2);
        setMeasurement("heat1", 3);
        setMeasurement("heat2", 5);

    }


    private void setMeasurement(String counterEndpointName, int counterId) throws IOException {
        Measurement measurement = new Measurement();
        measurement.setDateTime(LocalDateTime.now());
        measurement.setValue(remoteData.readData(counterEndpointName));
        measurement.setCounterId(counterId);
        measurementRepository.save(measurement);
    }


}
