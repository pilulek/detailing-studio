package com.lilierygina.detailingstudio.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lilierygina.detailingstudio.entity.Order;
import com.lilierygina.detailingstudio.entity.ServiceType;
import com.lilierygina.detailingstudio.entity.ServicesList;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class DocumentGenerator {
    public static final String FONT = "/fonts/arial.ttf";

    public Document generateDocument(Model model, Order order, ServicesList servicesList) {
        Document document = new Document();
        try {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 10, Font.NORMAL);

            PdfWriter.getInstance(document, new FileOutputStream("src/main/pdfdocuments/iTextTable.pdf"));

            document.open();

            Paragraph p1 = new Paragraph("Заказчик: " + order.getOwner(), font);
            p1.setIndentationLeft(25);
            p1.setIndentationRight(25);
            p1.setSpacingAfter(2);
            document.add(p1);

            Paragraph p2 = new Paragraph("Номер заказа: " + order.getId(), font);
            p2.setIndentationLeft(25);
            p2.setIndentationRight(25);
            p2.setSpacingAfter(2);
            document.add(p2);

            Paragraph p13 = new Paragraph("Дата заказа: " + order.getOrderDate().toLocaleString().replace(", 0:00:00", ""), font);
            p13.setIndentationLeft(25);
            p13.setIndentationRight(25);
            p13.setSpacingAfter(2);
            document.add(p13);

            Paragraph p3 = new Paragraph("Валюта: Руб.", font);
            p3.setIndentationLeft(25);
            p3.setIndentationRight(25);
            p3.setSpacingAfter(20);
            document.add(p3);

            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(15);
            table.setWidthPercentage(90);
            float[] columnWidths = new float[]{10f, 40f, 10f, 10f, 10f};
            table.setWidths(columnWidths);
            addTableHeader(table, font);
            addRows(table, order, servicesList, font);
            document.add(table);

            Paragraph p4 = new Paragraph("Итого:         " + servicesList.getTotalPrice(), font);
            p4.setIndentationLeft(400);
            p4.setIndentationRight(5);
            p4.setSpacingAfter(1);
            document.add(p4);

            Paragraph p5 = new Paragraph("В том числе НДС: ", font);
            p5.setIndentationLeft(347);
            p5.setIndentationRight(5);
            p5.setSpacingAfter(1);
            document.add(p5);

            Paragraph p6 = new Paragraph("Всего (с учетом НДС):         " + servicesList.getTotalPrice(), font);
            p6.setIndentationLeft(326);
            p6.setIndentationRight(5);
            p6.setSpacingAfter(35);
            document.add(p6);

            Paragraph p7 = new Paragraph("Всего указано услуг на сумму: _________________________________________________________________________________", new Font(bf, 8, Font.ITALIC));
            p7.setIndentationLeft(25);
            p7.setIndentationRight(5);
            p7.setSpacingAfter(20);
            document.add(p7);

            Paragraph p8 = new Paragraph("Вышеперечисленные услуги выполнены полностью и в срок. " +
                "Заказчик претензий по объему, качеству и срокам оказания услуг не имеет.", new Font(bf, 8, Font.ITALIC));
            p8.setIndentationLeft(25);
            p8.setIndentationRight(5);
            p8.setSpacingAfter(35);
            document.add(p8);

            Paragraph p9 = new Paragraph("Заказчик:              _______________________              __________________________________", font);
            p9.setIndentationLeft(25);
            p9.setIndentationRight(5);
            p9.setSpacingAfter(2);
            document.add(p9);

            Paragraph p10 = new Paragraph("(подпись)                                                                                                                      (расшифровка подписи)", new Font(bf, 5, Font.NORMAL));
            p10.setIndentationLeft(150);
            p10.setIndentationRight(5);
            p10.setSpacingAfter(0);
            document.add(p10);

            Paragraph p11 = new Paragraph("Исполнитель:       _______________________              __________________________________", font);
            p11.setIndentationLeft(25);
            p11.setIndentationRight(5);
            p11.setSpacingAfter(2);
            document.add(p11);

            Paragraph p12 = new Paragraph("(подпись)                                                                                                                      (расшифровка подписи)", new Font(bf, 5, Font.NORMAL));
            p12.setIndentationLeft(150);
            p12.setIndentationRight(5);
            p12.setSpacingAfter(0);
            document.add(p12);

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
        return document;
    }

    private void addTableHeader(PdfPTable table, Font font) throws DocumentException, IOException {

        Stream.of("№", "Наименование работы/услуги", "Количество", "Цена", "Сумма")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(1);
                header.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                header.setPhrase(new Phrase(columnTitle, font));
                table.addCell(header);
            });
    }

    private void addRows(PdfPTable table, Order order, ServicesList servicesList, Font font) throws DocumentException, IOException {

        Stream.of("1", ServiceType.getName(servicesList.getServiceName1()), "1", servicesList.getServicePrice1(), servicesList.getServicePrice1())
            .forEach(columnValue -> {
                PdfPCell col = new PdfPCell();
                col.setBorderWidth(1);
                col.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                col.setPhrase(new Phrase(String.valueOf(columnValue), font));
                table.addCell(col);
            });
        if (!servicesList.getServiceName2().equals(ServiceType.NONE.getServiceType())) {
            Stream.of("2", ServiceType.getName(servicesList.getServiceName2()), "1", servicesList.getServicePrice2(), servicesList.getServicePrice2())
                .forEach(columnValue -> {
                    PdfPCell col = new PdfPCell();
                    col.setBorderWidth(1);
                    col.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    col.setPhrase(new Phrase(String.valueOf(columnValue), font));
                    table.addCell(col);
                });
        }
        if (!servicesList.getServiceName3().equals(ServiceType.NONE.getServiceType())) {
            Stream.of("3", ServiceType.getName(servicesList.getServiceName3()), "1", servicesList.getServicePrice3(), servicesList.getServicePrice3())
                .forEach(columnValue -> {
                    PdfPCell col = new PdfPCell();
                    col.setBorderWidth(1);
                    col.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    col.setPhrase(new Phrase(String.valueOf(columnValue), font));
                    table.addCell(col);
                });
        }
    }
}
