package lt.mj.CountMyBill.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.mj.CountMyBill.model.Consumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmailService {

    private static final String SenderEMAIL = "laikasskrenda@gmail.com";
    private static final String ReceiverEMAIL = "mantvydas.jan@gmail.com";

    @Autowired
    private final JavaMailSender javaMailSender;

    public void sendEmail(Optional<Consumption> consumption) {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(SenderEMAIL);
        simpleMailMessage.setTo(ReceiverEMAIL);
        simpleMailMessage.setSubject("Ataskaita");
        simpleMailMessage.setText(
                "Periodo pradžia: " + consumption.get().getStartPeriod() + "\n" +
                "Periodo pabaiga: " + consumption.get().getEndPeriod() + "\n" +
                "Sunaudota " + df.format(consumption.get().getConsumptionValue()) + "\n" +
                "Suma " + df.format(consumption.get().getConsumptionPrice()) + "Eur");

        javaMailSender.send(simpleMailMessage);
        log.info("Email was sent");
    }

}
