package lt.mj.CountMyBill.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "counter")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int counterId;
    private String serialNumber;
    private String description;
    private int customerId;
    @ManyToMany
    @JoinTable(
            name = "counter_price",
            joinColumns = @JoinColumn(name = "counter_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"))
    List<Price> prices = new ArrayList<>();

}
