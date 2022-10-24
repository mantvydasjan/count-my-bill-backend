package lt.mj.CountMyBill;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
public class CountMyBillApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountMyBillApplication.class, args);
    }

}
