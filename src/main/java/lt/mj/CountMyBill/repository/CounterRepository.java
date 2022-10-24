package lt.mj.CountMyBill.repository;

import lt.mj.CountMyBill.model.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Integer> {

}

