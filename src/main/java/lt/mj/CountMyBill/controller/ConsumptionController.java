package lt.mj.CountMyBill.controller;

import lt.mj.CountMyBill.exception.CounterNotFoundException;
import lt.mj.CountMyBill.exception.PriceMismatchException;
import lt.mj.CountMyBill.model.Consumption;
import lt.mj.CountMyBill.model.Counter;
import lt.mj.CountMyBill.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private CounterService counterService;

    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5173/")
    public Optional<Consumption> getConsumptionById(@PathVariable int id) {
        return consumptionService.getConsumptionById(id);
    }

    @PostMapping(path = "/add/")
    public ResponseEntity<String> addConsumption(
            @RequestParam("id") int id,
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startPeriod,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endPeriod)
            throws CounterNotFoundException {

        double consumptionValue;
        Counter counter;
        double servicePrice;
        double consumptionPrice;

        try {
            consumptionValue = calculationService.calculateConsumption(id, startPeriod, endPeriod);
            counter = counterService.getCounterById(id);
            servicePrice = priceService.getServicePriceByCounter(counter, startPeriod, endPeriod);
            consumptionPrice = calculationService.calculateConsumptionPrice(consumptionValue, servicePrice);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumption value can not be calculated");
        } catch (PriceMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        consumptionService.addConsumption(id, consumptionValue, consumptionPrice, startPeriod, endPeriod);

        return ResponseEntity.status(HttpStatus.CREATED).body("Consumption for counter %s "+ id + " created." );

    }
}


