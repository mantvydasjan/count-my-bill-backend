package lt.mj.CountMyBill.service;

import lt.mj.CountMyBill.exception.CounterNotFoundException;
import lt.mj.CountMyBill.model.Counter;
import lt.mj.CountMyBill.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    @Autowired
    private CounterRepository counterRepository;

    public Counter getCounterById(int counterId) throws CounterNotFoundException {
        return counterRepository.findById(counterId).orElseThrow(
                () -> new CounterNotFoundException("Counter by id " + counterId + "was not found"));
    }

}
