package lt.mj.CountMyBill.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "remote")
public class RemoteDataConfig {

    private String ip;
    private String userName;
    private String password;

}
