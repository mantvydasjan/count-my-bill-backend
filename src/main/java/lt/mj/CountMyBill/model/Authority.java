package lt.mj.CountMyBill.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Authority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonBackReference
    private Customer customer;
    private String authorityName;
  
}
