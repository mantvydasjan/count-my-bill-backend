package lt.mj.CountMyBill.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
