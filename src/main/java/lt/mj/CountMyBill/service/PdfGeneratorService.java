package lt.mj.CountMyBill.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lt.mj.CountMyBill.model.Consumption;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Optional;


@Service
public class PdfGeneratorService {

    public void export(HttpServletResponse response, Optional<Consumption> consumption) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Ataskaita", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontParagraph.setSize(12);


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Paragraph paragraph1 = new Paragraph("Periodo pradžia: " + String.valueOf(
                consumption.get().getStartPeriod()), fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph paragraph2 = new Paragraph("Periodo pabaiga: " + String.valueOf(
                consumption.get().getEndPeriod()), fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph3 = new Paragraph("Sunaudota: " + String.valueOf(
                df.format(consumption.get().getConsumptionValue())), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph4 = new Paragraph("Suma: " + String.valueOf(
                df.format(consumption.get().getConsumptionPrice())) + " Eur", fontParagraph1);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.close();
    }

}
