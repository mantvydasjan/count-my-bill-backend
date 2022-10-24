package lt.mj.CountMyBill.repository;

import lt.mj.CountMyBill.model.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {
    Iterable<Measurement> findMeasurementsByCounterIdAndDateTimeBetween(int counterId, LocalDateTime dateFrom, LocalDateTime dateTo);


}
