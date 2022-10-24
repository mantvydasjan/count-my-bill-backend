package lt.mj.CountMyBill.service;

import lt.mj.CountMyBill.exception.PriceMismatchException;
import lt.mj.CountMyBill.exception.PriceNotFoundException;
import lt.mj.CountMyBill.model.Counter;
import lt.mj.CountMyBill.model.Price;
import lt.mj.CountMyBill.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Price getPriceById(int priceId) throws PriceNotFoundException {
        return priceRepository.findById(priceId).orElseThrow(
                () -> new PriceNotFoundException("Price by id " + priceId + "was not found"));
    }

    public double getServicePriceByCounter(Counter counter, LocalDateTime startPeriod, LocalDateTime endPeriod)
            throws PriceMismatchException {
        if (servicePrice(counter, endPeriod) == servicePrice(counter, startPeriod))
            return servicePrice(counter, endPeriod);
        else {
            throw new PriceMismatchException("Service prices mismatch. Please select different time period");
        }
    }

    private double servicePrice(Counter counter, LocalDateTime period) {
        List<Price> counterPrices = counter.getPrices();
        counterPrices.sort(Comparator.comparing(Price::getValidFrom));
        double servicePrice = 0;
        for (Price counterPrice : counterPrices) {
            if (counterPrice.getValidFrom().isBefore(period)
                    || counterPrice.getValidFrom().isEqual(period)) {
                servicePrice = counterPrice.getServicePrice();
            }
        }

        return servicePrice;
    }

}
