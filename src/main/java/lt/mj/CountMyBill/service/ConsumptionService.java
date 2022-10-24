package lt.mj.CountMyBill.service;

import lt.mj.CountMyBill.model.Consumption;
import lt.mj.CountMyBill.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public void addConsumption(int counterId,
                               double consumptionValue,
                               double consumptionPrice,
                               LocalDateTime startPeriod,
                               LocalDateTime endPeriod) {
        Consumption consumption = new Consumption();
        consumption.setCalculationTime(LocalDateTime.now());
        consumption.setStartPeriod(startPeriod);
        consumption.setEndPeriod(endPeriod);
        consumption.setCounterId(counterId);
        consumption.setConsumptionValue(consumptionValue);
        consumption.setConsumptionPrice(consumptionPrice);
        consumptionRepository.save(consumption);

    }

    public Optional<Consumption> getConsumptionById(int consumptionId) {
        return consumptionRepository.findById(consumptionId);
    }
}
