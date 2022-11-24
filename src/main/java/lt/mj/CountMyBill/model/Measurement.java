package lt.mj.CountMyBill.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int measurementId;
    private LocalDateTime dateTime;
    private double value;
    private int counterId;

}
