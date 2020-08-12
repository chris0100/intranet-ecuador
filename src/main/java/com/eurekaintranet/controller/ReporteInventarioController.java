/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;


@Named(value = "reporteInventarioController")
@SessionScoped
public class ReporteInventarioController implements Serializable {

    private String cod;
    private int available;
    private BigDecimal sell;
    private String concatenado;
    private ReporteInventarioController reporteActual;
    private List<ReporteInventarioController> listaReporte1;
    private EmailController emailController;
    private String msjConsulta;
    private boolean estadoConsulta;
    
    private static String[] columns = {"Código", "Descripción", "Disponible", "Precio"};
    
    public ReporteInventarioController() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public ReporteInventarioController getReporteActual() {
        return reporteActual;
    }

    public void setReporteActual(ReporteInventarioController reporteActual) {
        this.reporteActual = reporteActual;
    }

   

    public String getConcatenado() {
        return concatenado;
    }

    public void setConcatenado(String concatenado) {
        this.concatenado = concatenado;
    }

    public List<ReporteInventarioController> getListaReporte1() {
        return listaReporte1;
    }

    public void setListaReporte1(List<ReporteInventarioController> listaReporte1) {
        this.listaReporte1 = listaReporte1;
    }
    
    public void recargarLista(){
        listaReporte1 = new ArrayList<>();
    }
    public String prepareListInvDisponible() throws SQLException, ClassNotFoundException{
        consultaInvDisponible();
        return "reporteInvDisponible";
    }
    public DefaultStreamedContent getDescargarXlsInvDisponible() {
        try {
             escribirXlsInvDisponible(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.xlsx"));
             String newFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.xlsx");
             return new DefaultStreamedContent(new FileInputStream(newFileName), "file/xlsx", "estructura.xlsx");
             }
          catch (Exception e) {
             return null;
         }
    }
    
    public void enviarXlsInvDisponible() throws SQLException, ClassNotFoundException, IOException {
       
                //emailController = new EmailController();
                //msjConsulta = "";
                //estadoConsulta = true;
                
                //consultaInvDisponible();
                //escribirXlsInvDisponible(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.xlsx"));
                //sendMailReporte();
                
                //recargarPlano();
    }
    
    
    // Realiza la consulta del inventario disponible
    public void consultaInvDisponible() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        //String query = "SELECT PRODUCT_ID as cod, AVAILABLE , SELL, CONCAT(concat(PRODUCT_NAME,concat(' ',talla)), CONCAT(' ', concat(color,concat(' ', concat(familia,concat(' ',concat(temporada, concat(' ',concat(anioPrenda,concat(' ',extensiones.descripcion)))))))))) as concatenado  FROM INVT_Ficha_Principal as tablaPrincipal inner join SIST_Lista_7 extensiones on tablaPrincipal.AnioPrenda = extensiones.codigo and extensiones.grupo_categoria = 'Añote' WHERE ((INVT_Ficha_Principal.AVAILABLE)>0) order by PRODUCT_NAME, talla,color,familia, temporada, extensiones.descripcion ASC ";
        String query = "SELECT PRODUCT_ID as cod, INVT_Bodegas_Saldos.AVAILABLE , INVT_PreciosProductoSucursal.Precio_Moneda_1 SELL,  CONCAT(concat(PRODUCT_NAME,concat(' ',talla)), CONCAT(' ', concat(color,concat(' ', concat(familia,concat(' ',concat(temporada, concat(' ',concat(anioPrenda,concat(' ',extensiones.descripcion)))))))))) as concatenado  FROM INVT_Ficha_Principal as tablaPrincipal  INNER JOIN SIST_Lista_7 extensiones on tablaPrincipal.AnioPrenda = extensiones.codigo and extensiones.grupo_categoria = 'Añote' INNER JOIN INVT_Bodegas_Saldos on INVT_bodegas_saldos.PRODUCT_ID_CORP = INVT_Ficha_Principal.PRODUCT_ID_CORP  INNER JOIN INVT_PreciosProductoSucursal ON INVT_Ficha_Principal.PRODUCT_ID_CORP = INVT_PreciosProductoSucursal.Product_ID_Corp WHERE INVT_Bodegas_Saldos.WARE_CODE = '001' and INVT_Bodegas_Saldos.AVAILABLE >0  order by PRODUCT_NAME, talla,color,familia, temporada, extensiones.descripcion ASC";
        listaReporte1 = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteActual = new ReporteInventarioController();
                    reporteActual.setConcatenado(rec.getString("concatenado").replace("  "," ").replace("  ", " ").replace("  ", " "));
                    reporteActual.setCod(rec.getString("cod"));
                    reporteActual.setAvailable(rec.getInt("AVAILABLE"));
                    reporteActual.setSell(rec.getBigDecimal("SELL"));
                    listaReporte1.add(reporteActual);
                } catch (Exception e) {
                    System.out.println("error"+e);
                }
            }
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    
    //Produce el archivo de excel para enviar el inventario disponible
    public void escribirXlsInvDisponible(String nombre) throws FileNotFoundException, IOException {
        try {
            String rutaArchivoExcel = nombre;
            FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
            Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
            CreationHelper createHelper = workbook.getCreationHelper();
            
            // Create a Sheet
            Sheet sheet = workbook.createSheet("Items");
            // Create a Font for styling header cells
            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLUE.getIndex());
            headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

            // Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment((short) 2);

            CellStyle alinearPrecio = workbook.createCellStyle();
            alinearPrecio.setAlignment((short) 3);

            // Create a Row
            Row titulo = sheet.createRow(0);
            Cell cellTitulo = titulo.createCell(0);
            cellTitulo.setCellValue("CORPORACIÓN EKAZIPPER DEL ECUADOR - INV. DISPONIBLE");
            cellTitulo.setCellStyle(headerCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
            Row headerRow = sheet.createRow(1);

            // Create cells
            for(int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            // Create Other rows and cells with employees data
            int rowNum = 2;
            int totalAvailable = 0;
            BigDecimal totalSell = new BigDecimal(0);
            for(ReporteInventarioController item: listaReporte1) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(item.getCod());
                row.createCell(1).setCellValue(item.getConcatenado());
                row.createCell(2).setCellValue(item.getAvailable());
                row.createCell(3).setCellValue(item.getSell().doubleValue());
                totalAvailable = totalAvailable+item.getAvailable();
                totalSell = totalSell.add((item.getSell().multiply(new BigDecimal(item.getAvailable()))));
            }
            Row total = sheet.createRow(rowNum++);
            total.createCell(0).setCellValue("TOTALES: ");
            total.createCell(2).setCellValue(totalAvailable);
            total.createCell(3).setCellValue(totalSell.doubleValue());
            // Resize all columns to fit the content size
            for(int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fileOut = new FileOutputStream(rutaArchivoExcel)) {
                workbook.write(fileOut);
            }
        }  catch (FileNotFoundException ex) {
            System.out.println("error "+ex);
        } 
    }
    
    
    public void sendMailReporte() throws FileNotFoundException{
         try {
            emailController = new EmailController();
            String asunto = "Reporte de inventario disponible CORPORACIÓN EKAZIPPER DEL ECUADOR S.A.";
            String cuerpo = "Se adjunta inventario disponible." +
                            "\n" +
                            "\n" +
                            "Nota: Este es un mensaje automático, por favor no responder." +
                            "\n" +
                            "\n" +
                            "Departamento de TI"+
                            "\n" +
                            "\n";
            String adjunto = "resources/plano/text1.xlsx";
            String nombreAdjunto = "reporte.xlsx";
            //listaventasecuador@ekazipper.com
            emailController.sendMailAdjunto("listaventasecuador@ekazipper.com,luisc@ekazipper.com,ecuador.facturacion@ekazipper.com, ccompras@ekazipper.com", asunto, cuerpo, adjunto, nombreAdjunto); 
         } catch (Exception e) {
             System.out.println("error correo "+e);
         }    
     }
    
     public void sendMailReporteError(String tipo) throws FileNotFoundException{
         try {
            emailController = new EmailController();
            String asunto = "Error en el envio de correo - "+tipo;
            String cuerpo = "Mensaje de la excepcion: \n" +
                            "\n" +msjConsulta+
                            "\n" +
                            "\n" +
                            "Nota: Este es un mensaje automático, por favor no responder." +
                            "\n" +
                            "\n" +
                            "Departamento de TI"+
                            "\n" +
                            "\n";
            emailController.sendMail("luisc@ekazipper.com, fabianq@ekazipper.com", asunto, cuerpo); 
         } catch (Exception e) {
             System.out.println("error correo "+e);
         }    
     }
     public void recargarPlano() throws IOException{
        try {
            BufferedWriter usuario = new BufferedWriter(new FileWriter(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.xlsx")));
            usuario.write("");
            usuario.close(); 
        } catch (Exception e) {
            System.out.println("No se pudo limpiar archivo "+e);
        } 
    }
}
