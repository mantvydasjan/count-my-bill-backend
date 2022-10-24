package lt.mj.CountMyBill.controller;

import lt.mj.CountMyBill.config.RemoteDataConfig;
import lt.mj.CountMyBill.exception.CounterNotFoundException;
import lt.mj.CountMyBill.exception.PriceNotFoundException;
import lt.mj.CountMyBill.model.Counter;
import lt.mj.CountMyBill.model.Price;
import lt.mj.CountMyBill.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private RemoteDataConfig remoteDataConfig;

    @Autowired
    private PriceService priceService;

    @GetMapping(path = "/{priceId}")
    public ResponseEntity<Price> getPriceById(@PathVariable int priceId) throws PriceNotFoundException {
        Price price = priceService.getPriceById(priceId);
        System.out.println(remoteDataConfig.getPassword());
        return new ResponseEntity<>(price, HttpStatus.FOUND);


    }


}
