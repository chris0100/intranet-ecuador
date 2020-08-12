/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.controller;

import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Luis carlos cabal
 */
@Named(value = "reporteAlertaController")
@SessionScoped
public class ReporteAlertaController implements Serializable {

    private String codigo;
    private String descripcion;
    private List<ReporteAlertaController> listaCodBarra;
    private List<ReporteAlertaController> listaPrecio;
    private List<ReporteAlertaController> listaCosto;
    private List<ReporteAlertaController> listaCliente;
    private ReporteAlertaController reporteAlertaActual;
    private EmailController emailController;
    
    public ReporteAlertaController() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EmailController getEmailController() {
        return emailController;
    }

    public void setEmailController(EmailController emailController) {
        this.emailController = emailController;
    }

    public ReporteAlertaController getReporteAlertaActual() {
        return reporteAlertaActual;
    }

    public void setReporteAlertaActual(ReporteAlertaController reporteAlertaActual) {
        this.reporteAlertaActual = reporteAlertaActual;
    }

    public List<ReporteAlertaController> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<ReporteAlertaController> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<ReporteAlertaController> getListaCodBarra() {
        return listaCodBarra;
    }

    public void setListaCodBarra(List<ReporteAlertaController> listaCodBarra) {
        this.listaCodBarra = listaCodBarra;
    }

    public List<ReporteAlertaController> getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(List<ReporteAlertaController> listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public List<ReporteAlertaController> getListaCosto() {
        return listaCosto;
    }

    public void setListaCosto(List<ReporteAlertaController> listaCosto) {
        this.listaCosto = listaCosto;
    }
    
    public void recargarLista(){
        listaCodBarra = new ArrayList<>();
        listaCosto = new ArrayList<>();
        listaPrecio = new ArrayList<>();
        listaCliente = new ArrayList<>();
    }
    public void prepareEnviarAlerta() throws SQLException, ClassNotFoundException, IOException{
        recargarLista();
        consultaCodBarra();
        consultaCosto();
        consultaPrecio();
        consultaCliente();
        enviarAlerta();
    }
    public void enviarAlerta() throws SQLException, ClassNotFoundException, IOException {
        createPDF();
        sendMailReporte();
    }
    public void consultaCodBarra() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT PRODUCT_ID , DESCRIPTION FROM INVT_Ficha_Principal as tablaPrincipal WHERE CODE_PROV_O_ALT = '' and PRODUCT_TYPE = 'venta' order by DESCRIPTION ASC;";
        listaCodBarra = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteAlertaActual = new ReporteAlertaController();
                    reporteAlertaActual.setCodigo(rec.getString("PRODUCT_ID"));
                    reporteAlertaActual.setDescripcion(rec.getString("DESCRIPTION"));
                    listaCodBarra.add(reporteAlertaActual);
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
    
    public List<ReporteAlertaController> consultarTablaItem() throws SQLException, ClassNotFoundException{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT PRODUCT_ID_CORP, DESCRIPTION FROM INVT_Ficha_Principal order by PRODUCT_ID_CORP";
        List<ReporteAlertaController> listaPrecioValidado = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteAlertaActual = new ReporteAlertaController();
                    reporteAlertaActual.setCodigo(rec.getString("PRODUCT_ID_CORP"));
                    reporteAlertaActual.setDescripcion(rec.getString("DESCRIPTION"));
                    listaPrecioValidado.add(reporteAlertaActual);
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
        return listaPrecioValidado;
    }
     public List<String> consultarTablaPrecioItem() throws ClassNotFoundException, SQLException{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT PRODUCT_ID_CORP FROM INVT_PreciosProductoSucursal order by PRODUCT_ID_CORP";
        List<String> precios = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    String codigoItem;
                    codigoItem = rec.getString("PRODUCT_ID_CORP");
                    precios.add(codigoItem);
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
        return precios;
    }
     
     public void obtenerItemSinTablaPrecio() throws ClassNotFoundException, SQLException{
         listaPrecio = new ArrayList<>();
         List<String> precios = consultarTablaPrecioItem();
         boolean itemEncontrado = false;
         for(ReporteAlertaController item: consultarTablaItem()){
             itemEncontrado = false;
             for(String item2: precios){
                 if(item.getCodigo().equals(item2)){
                     itemEncontrado = true;
                     break;
                 }
             }
             if(!itemEncontrado){
                 listaPrecio.add(item);
             }
         }
     }
    
    
    
    
    public void consultaPrecio() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT INVT_Ficha_Principal.PRODUCT_ID , INVT_Ficha_Principal.DESCRIPTION FROM  INVT_PreciosProductoSucursal inner join INVT_Ficha_Principal on INVT_Ficha_Principal.PRODUCT_ID_CORP =  INVT_PreciosProductoSucursal.PRODUCT_ID_CORP where (INVT_PreciosProductoSucursal.Precio_Moneda_1 <= 0)";
        obtenerItemSinTablaPrecio();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteAlertaActual = new ReporteAlertaController();
                    reporteAlertaActual.setCodigo(rec.getString("PRODUCT_ID"));
                    reporteAlertaActual.setDescripcion(rec.getString("DESCRIPTION"));
                    listaPrecio.add(reporteAlertaActual);
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
    public void consultaCosto() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT PRODUCT_ID , DESCRIPTION FROM INVT_Ficha_Principal as tablaPrincipal WHERE [GL COST CODE] = '' and PRODUCT_TYPE = 'venta' order by DESCRIPTION ASC;";
        listaCosto = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteAlertaActual = new ReporteAlertaController();
                    reporteAlertaActual.setCodigo(rec.getString("PRODUCT_ID"));
                    reporteAlertaActual.setDescripcion(rec.getString("DESCRIPTION"));
                    listaCosto.add(reporteAlertaActual);
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
    public void consultaCliente() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT IDENTIFICACION_FISCAL, NOMBRE_CLIENTE FROM CLNT_Ficha_Principal where SALESMAN = '' and IDENTIFICACION_FISCAL <> '';";
        listaCliente = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteAlertaActual = new ReporteAlertaController();
                    reporteAlertaActual.setCodigo(rec.getString("IDENTIFICACION_FISCAL"));
                    reporteAlertaActual.setDescripcion(rec.getString("NOMBRE_CLIENTE"));
                    listaCliente.add(reporteAlertaActual);
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
    public void sendMailReporte() throws FileNotFoundException{
         try {
            emailController = new EmailController();
            String asunto = "Alerta datos incompletos MBA, CORPORACIÓN EKAZIPPER DEL ECUADOR S.A.";
            String cuerpo = "Se adjunta alerta de datos incompletos en la plataforma MBA." +
                            "\n" +
                            "\n" +
                            "Nota: Este es un mensaje automático, por favor no responder." +
                            "\n" +
                            "\n" +
                            "Departamento de TI"+
                            "\n" +
                            "\n";
            String adjunto = "resources/plano/text1.pdf";
            String nombreAdjunto = "reporte.pdf";
            //listaventasecuador@ekazipper.com
            emailController.sendMailAdjunto("luisc@ekazipper.com, taniaz@ekazipper.com, ecuador.administracion@ekazipper.com, ecuador.facturacion@ekazipper.com", asunto, cuerpo, adjunto, nombreAdjunto); 
         } catch (Exception e) {
             System.out.println("error correo "+e);
         }    
     }
     
     
    public void createPDF(){
        try {
            FacesContext facesContext= FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session= (HttpSession) externalContext.getSession(true);
            String url ="http" + "://" + "localhost" + ":" + "8080" + "/intranet"  + "/faces/usuario/modReportes/formato3.xhtml;jsessionid=" + session.getId()+"?pdf=true";
            OutputStream out = new FileOutputStream(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.pdf"));
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);
            out.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
    
}
