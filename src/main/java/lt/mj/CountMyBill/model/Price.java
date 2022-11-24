package lt.mj.CountMyBill.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceId;
    private String serviceName;
    private float servicePrice;
    private LocalDateTime validFrom;
    @JsonIgnore
    @ManyToMany
    private List<Counter> counters = new ArrayList<>();

}
