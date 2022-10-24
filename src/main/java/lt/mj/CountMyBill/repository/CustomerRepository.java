package lt.mj.CountMyBill.repository;

import lt.mj.CountMyBill.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
