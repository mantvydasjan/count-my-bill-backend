package lt.mj.CountMyBill.controller;

import lt.mj.CountMyBill.model.Measurement;
import lt.mj.CountMyBill.service.MeasurementService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/measurement")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping(path = "/all")
    @CrossOrigin(origins = "http://127.0.0.1:5173/")
    public Iterable<Measurement> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping
    public Iterable<Measurement> getByCounterIdAndDateTimeBetween(
            @RequestParam("id") int id,
            @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime)
   {
        return measurementService.getMeasurementsByCounterIdAndDateTimeBetween(
                id, dateTime);
    }

 //  @Scheduled(fixedRate = 2, timeUnit = TimeUnit.MINUTES)
    public void addMeasurement() throws IOException, ParseException {
        measurementService.addMeasurement();
    }
}
