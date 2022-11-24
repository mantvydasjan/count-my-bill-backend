package lt.mj.CountMyBill.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "consumption")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consumptionId;
    private LocalDateTime calculationTime;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private String period;
    private int counterId;
    private double consumptionPrice;
    private double consumptionValue;

}
