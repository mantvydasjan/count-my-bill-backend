package lt.mj.CountMyBill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    private CalculationService calculationService;

    @BeforeEach
    void setUpBeforeEach() { calculationService = new CalculationService(); }

    @Test
    void calculateConsumptionPrice() {

        double servicePrice = 1.2;
        double consumption = 100;
        double expectedConsumptionPrice = 120;

        double actualConsumptionPrice = calculationService.calculateConsumptionPrice(consumption, servicePrice);

        assertEquals(expectedConsumptionPrice, actualConsumptionPrice);
        

    }

}