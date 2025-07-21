package Util;

import GUI.frmBase;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportHelper implements Alerta,Confirmacion{
    
    private static final DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    
    String file= LocalDateTime.now().format(fileFormat);
    String fechaHoy = LocalDateTime.now().format(dateFormat);
    String horaHoy = LocalDateTime.now().format(timeFormat);
    
    public void generarPDF(JTable table, JLabel label){   
        if (Confirmacion("Desea Exportar los datos de la tabla?")) {
            String nombre = label.getText().substring(11);
            String dest = "C:\\REPORTES\\" + nombre +"_"+ file +".pdf";
            
            Paragraph titulo = new Paragraph("Reporte de " + nombre)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(Color.BLACK)
                    .setMarginBottom(20);
            
            Paragraph fecha = new Paragraph("Fecha: "+fechaHoy+"   Hora: "+horaHoy)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFontColor(Color.BLACK)
                    .setMarginBottom(20);

             try {
   
                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                TableModel model = table.getModel();
                int numCols = model.getColumnCount();

                // Crear una tabla en iText con el mismo número de columnas
                Table pdfTable = new Table(numCols);

                // Encabezados de columna
                for (int i = 0; i < numCols; i++) {
                    pdfTable.addHeaderCell(new Cell().add(model.getColumnName(i)));
                }

                // Datos de la tabla
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < numCols; col++) {
                        Object value = model.getValueAt(row, col);
                        pdfTable.addCell(new Cell().add(String.valueOf(value)));
                    }
                }
                
                document.add(titulo);
                document.add(fecha);
                document.add(pdfTable);
                document.close();

                Alerta("PDF creado correctamente en: " + dest);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void generarExcel(JTable table, JLabel label) throws IOException {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = (Sheet) libro.createSheet("Datos");

        TableModel modelo = table.getModel();

        // Escribir encabezados
        Row encabezado = hoja.createRow(0);
        for (int col = 0; col < modelo.getColumnCount(); col++) {
            encabezado.createCell(col).setCellValue(modelo.getColumnName(col));
        }

        // Escribir datos
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            Row filaExcel = hoja.createRow(fila + 1);
            for (int col = 0; col < modelo.getColumnCount(); col++) {
                Object valor = modelo.getValueAt(fila, col);
                filaExcel.createCell(col).setCellValue(valor != null ? valor.toString() : "");
            }
        }

        // Ajustar ancho de columnas
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            hoja.autoSizeColumn(i);
        }

        // Ruta fija para guardar el archivo (ejemplo: escritorio del usuario)
        String ruta = "C:\\REPORTES\\";
        // Nombre de archivo con fecha/hora
        String nombre = label.getText().substring(11);
        String nombreArchivo = nombre + "_" + file + ".xlsx";

        File archivo = new File(ruta + nombreArchivo);

        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            libro.write(salida);
        }

        libro.close();
    }
    
    public void generarPDF_Custom(JTable table, String texto){   
        if (Confirmacion("Desea Exportar los datos de la tabla?")) {
  
            String dest = "C:\\REPORTES\\" + texto +"_"+ file +".pdf";
            
            Paragraph titulo = new Paragraph("Reporte de " + texto)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(Color.BLACK)
                    .setMarginBottom(20);
            
            Paragraph fecha = new Paragraph("Fecha: "+fechaHoy+"   Hora: "+horaHoy)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setFontColor(Color.BLACK)
                    .setMarginBottom(20);

             try {
   
                PdfWriter writer = new PdfWriter(dest);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                TableModel model = table.getModel();
                int numCols = model.getColumnCount();

                // Crear una tabla en iText con el mismo número de columnas
                Table pdfTable = new Table(numCols);

                // Encabezados de columna
                for (int i = 0; i < numCols; i++) {
                    pdfTable.addHeaderCell(new Cell().add(model.getColumnName(i)));
                }

                // Datos de la tabla
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < numCols; col++) {
                        Object value = model.getValueAt(row, col);
                        pdfTable.addCell(new Cell().add(String.valueOf(value)));
                    }
                }
                
                document.add(titulo);
                document.add(fecha);
                document.add(pdfTable);
                document.close();

                Alerta("PDF creado correctamente en: " + dest);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void generarExcel_Custom(JTable table, String texto) throws IOException {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = (Sheet) libro.createSheet("Datos");

        TableModel modelo = table.getModel();

        // Escribir encabezados
        Row encabezado = hoja.createRow(0);
        for (int col = 0; col < modelo.getColumnCount(); col++) {
            encabezado.createCell(col).setCellValue(modelo.getColumnName(col));
        }

        // Escribir datos
        for (int fila = 0; fila < modelo.getRowCount(); fila++) {
            Row filaExcel = hoja.createRow(fila + 1);
            for (int col = 0; col < modelo.getColumnCount(); col++) {
                Object valor = modelo.getValueAt(fila, col);
                filaExcel.createCell(col).setCellValue(valor != null ? valor.toString() : "");
            }
        }

        // Ajustar ancho de columnas
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            hoja.autoSizeColumn(i);
        }

        // Ruta fija para guardar el archivo (ejemplo: escritorio del usuario)
        String ruta = "C:\\REPORTES\\";
        // Nombre de archivo con fecha/hora
        String nombreArchivo = texto + "_" + file + ".xlsx";

        File archivo = new File(ruta + nombreArchivo);

        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            libro.write(salida);
        }

        libro.close();
    }
        
}