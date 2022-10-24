package lt.mj.CountMyBill.repository;

import lt.mj.CountMyBill.model.Price;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface PriceRepository extends CrudRepository<Price, Integer> {

    Iterable<Price> getPriceByValidFromAfterAndCountersEquals(LocalDate dateFrom, int id);

    Iterable<Price> getPriceByCountersIs(int counterId);

}