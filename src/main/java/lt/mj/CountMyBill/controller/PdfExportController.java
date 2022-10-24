package lt.mj.CountMyBill.controller;

import lt.mj.CountMyBill.service.ConsumptionService;
import lt.mj.CountMyBill.service.EmailService;
import lt.mj.CountMyBill.service.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PdfExportController {

    @Autowired
    private ConsumptionService consumptionService;
    private final PdfGeneratorService pdfGeneratorService;

    @Autowired
    private EmailService emailService;

    public PdfExportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("pdf/generate/{consumptionId}")
    public void generatePDF(HttpServletResponse response, @PathVariable int consumptionId) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response, consumptionService.getConsumptionById(consumptionId));
        emailService.sendEmail(consumptionService.getConsumptionById(consumptionId));

    }
}
