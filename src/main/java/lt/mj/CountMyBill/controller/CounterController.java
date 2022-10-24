package lt.mj.CountMyBill.controller;

import lt.mj.CountMyBill.exception.CounterNotFoundException;
import lt.mj.CountMyBill.model.Consumption;
import lt.mj.CountMyBill.model.Counter;
import lt.mj.CountMyBill.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Optional;

@RestController
@RequestMapping("/counters")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @Value("${remote.password}")
    private String password;

    PriceController priceController = new PriceController();

    @GetMapping(path = "/{counterId}")
    @CrossOrigin(origins = "http://127.0.0.1:5173/")
    public ResponseEntity<Counter> getCounterById(@PathVariable int counterId) {
        Counter counter;
        System.out.println(password);
        try {
            counter = counterService.getCounterById(counterId);
        } catch (CounterNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(counter, HttpStatus.FOUND);

    }}

