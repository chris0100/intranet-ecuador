/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.controller;

import com.eurekaintranet.entities.T004ReporteSaldo;
import com.eurekaintranet.entities.T005ReporteSaldoFecha;
import com.eurekaintranet.entities.T006ReporteSaldoVendedor;
import com.eurekaintranet.entities.T007ReporteSaldoFechaColombia;
import com.eurekaintranet.entities.T008ReporteSaldoItemCodBarraMba;
import com.eurekaintranet.sessions.T004ReporteSaldoFacade;
import com.eurekaintranet.sessions.T005ReporteSaldoFechaFacade;
import com.eurekaintranet.sessions.T006ReporteSaldoVendedorFacade;
import com.eurekaintranet.sessions.T007ReporteSaldoFechaColombiaFacade;
import com.eurekaintranet.sessions.T008ReporteSaldoItemCodBarraMbaFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientBuilder;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author LUIS CARLOS CABAL ROJAS
 */
@Named(value = "reporteSaldoController")
@SessionScoped
public class ReporteSaldoController implements Serializable {

    @EJB
    private T004ReporteSaldoFacade reporteSaldoFacade;
    private T004ReporteSaldo reporteSaldoActual;
    private List<T004ReporteSaldo> listaReporteSaldo;
    private List<T004ReporteSaldo> listaReporteSaldoEnvio;
    @EJB
    private T006ReporteSaldoVendedorFacade reporteSaldoVendedorFacade;
    private T006ReporteSaldoVendedor reporteSaldoVendedorActual;
    private List<T006ReporteSaldoVendedor> listaReporteSaldoVendedor;
    @EJB
    private T005ReporteSaldoFechaFacade reporteSaldoFechaFacade;
    private T005ReporteSaldoFecha reporteSaldoFechaActual;
    private List<T005ReporteSaldoFecha> listaReporteSaldoFecha;
    @EJB
    private T007ReporteSaldoFechaColombiaFacade reporteSaldoFechaColombiaFacade;
    private T007ReporteSaldoFechaColombia reporteSaldoFechaColombiaActual;
    private List<T007ReporteSaldoFechaColombia> listaReporteSaldoFechaColombia;
    @EJB
    private T008ReporteSaldoItemCodBarraMbaFacade reporteSaldoItemCodBarraMbaFacade;
    private T008ReporteSaldoItemCodBarraMba reporteSaldoItemCodBarraMbaActual;
    private List<T008ReporteSaldoItemCodBarraMba> listaReporteSaldoItemCodBarraMba;
    
    private EmailController emailController;
    private String nombreEnvio;
    private String correoEnvio;
    private BigDecimal subTotalCantPrecio;
    private BigDecimal valueSubTotal;
    private BigDecimal valueTotal;
    
    
    public ReporteSaldoController() {
    }

    public T004ReporteSaldoFacade getReporteSaldoFacade() {
        return reporteSaldoFacade;
    }

    public void setReporteSaldoFacade(T004ReporteSaldoFacade reporteSaldoFacade) {
        this.reporteSaldoFacade = reporteSaldoFacade;
    }

    public T004ReporteSaldo getReporteSaldoActual() {
        return reporteSaldoActual;
    }

    public void setReporteSaldoActual(T004ReporteSaldo reporteSaldoActual) {
        this.reporteSaldoActual = reporteSaldoActual;
    }

    public List<T004ReporteSaldo> getListaReporteSaldo() {
        return listaReporteSaldo = getReporteSaldoFacade().findAll();
    }

    public void setListaReporteSaldo(List<T004ReporteSaldo> listaReporteSaldo) {
        this.listaReporteSaldo = listaReporteSaldo;
    }

    public T006ReporteSaldoVendedorFacade getReporteSaldoVendedorFacade() {
        return reporteSaldoVendedorFacade;
    }

    public void setReporteSaldoVendedorFacade(T006ReporteSaldoVendedorFacade reporteSaldoVendedorFacade) {
        this.reporteSaldoVendedorFacade = reporteSaldoVendedorFacade;
    }

    public T006ReporteSaldoVendedor getReporteSaldoVendedorActual() {
        return reporteSaldoVendedorActual;
    }

    public void setReporteSaldoVendedorActual(T006ReporteSaldoVendedor reporteSaldoVendedorActual) {
        this.reporteSaldoVendedorActual = reporteSaldoVendedorActual;
    }

    public List<T006ReporteSaldoVendedor> getListaReporteSaldoVendedor() {
        return listaReporteSaldoVendedor = getReporteSaldoVendedorFacade().findAll();
    }

    public void setListaReporteSaldoVendedor(List<T006ReporteSaldoVendedor> listaReporteSaldoVendedor) {
        this.listaReporteSaldoVendedor = listaReporteSaldoVendedor;
    }

    public T005ReporteSaldoFechaFacade getReporteSaldoFechaFacade() {
        return reporteSaldoFechaFacade;
    }

    public void setReporteSaldoFechaFacade(T005ReporteSaldoFechaFacade reporteSaldoFechaFacade) {
        this.reporteSaldoFechaFacade = reporteSaldoFechaFacade;
    }

    public T005ReporteSaldoFecha getReporteSaldoFechaActual() {
        return reporteSaldoFechaActual;
    }

    public void setReporteSaldoFechaActual(T005ReporteSaldoFecha reporteSaldoFechaActual) {
        this.reporteSaldoFechaActual = reporteSaldoFechaActual;
    }

    public List<T005ReporteSaldoFecha> getListaReporteSaldoFecha() {
        return listaReporteSaldoFecha;
    }

    public void setListaReporteSaldoFecha(List<T005ReporteSaldoFecha> listaReporteSaldoFecha) {
        this.listaReporteSaldoFecha = listaReporteSaldoFecha;
    }

    public EmailController getEmailController() {
        return emailController;
    }

    public void setEmailController(EmailController emailController) {
        this.emailController = emailController;
    }

    

    public List<T004ReporteSaldo> getListaReporteSaldoEnvio() {
        return listaReporteSaldoEnvio;
    }

    public void setListaReporteSaldoEnvio(List<T004ReporteSaldo> listaReporteSaldoEnvio) {
        this.listaReporteSaldoEnvio = listaReporteSaldoEnvio;
    }

    public String getNombreEnvio() {
        return nombreEnvio;
    }

    public void setNombreEnvio(String nombreEnvio) {
        this.nombreEnvio = nombreEnvio;
    }

    public String getCorreoEnvio() {
        return correoEnvio;
    }

    public void setCorreoEnvio(String correoEnvio) {
        this.correoEnvio = correoEnvio;
    }

    public BigDecimal getSubTotalCantPrecio() {
        return subTotalCantPrecio;
    }

    public void setSubTotalCantPrecio(BigDecimal subTotalCantPrecio) {
        this.subTotalCantPrecio = subTotalCantPrecio;
    }

    public BigDecimal getValueSubTotal() {
        return valueSubTotal;
    }

    public void setValueSubTotal(BigDecimal valueSubTotal) {
        this.valueSubTotal = valueSubTotal;
    }

    public BigDecimal getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(BigDecimal valueTotal) {
        this.valueTotal = valueTotal;
    }

    public T007ReporteSaldoFechaColombiaFacade getReporteSaldoFechaColombiaFacade() {
        return reporteSaldoFechaColombiaFacade;
    }

    public void setReporteSaldoFechaColombiaFacade(T007ReporteSaldoFechaColombiaFacade reporteSaldoFechaColombiaFacade) {
        this.reporteSaldoFechaColombiaFacade = reporteSaldoFechaColombiaFacade;
    }

    public T007ReporteSaldoFechaColombia getReporteSaldoFechaColombiaActual() {
        return reporteSaldoFechaColombiaActual;
    }

    public void setReporteSaldoFechaColombiaActual(T007ReporteSaldoFechaColombia reporteSaldoFechaColombiaActual) {
        this.reporteSaldoFechaColombiaActual = reporteSaldoFechaColombiaActual;
    }

    public List<T007ReporteSaldoFechaColombia> getListaReporteSaldoFechaColombia() {
        return listaReporteSaldoFechaColombia;
    }

    public void setListaReporteSaldoFechaColombia(List<T007ReporteSaldoFechaColombia> listaReporteSaldoFechaColombia) {
        this.listaReporteSaldoFechaColombia = listaReporteSaldoFechaColombia;
    }

    public T008ReporteSaldoItemCodBarraMbaFacade getReporteSaldoItemCodBarraMbaFacade() {
        return reporteSaldoItemCodBarraMbaFacade;
    }

    public void setReporteSaldoItemCodBarraMbaFacade(T008ReporteSaldoItemCodBarraMbaFacade reporteSaldoItemCodBarraMbaFacade) {
        this.reporteSaldoItemCodBarraMbaFacade = reporteSaldoItemCodBarraMbaFacade;
    }

    public T008ReporteSaldoItemCodBarraMba getReporteSaldoItemCodBarraMbaActual() {
        return reporteSaldoItemCodBarraMbaActual;
    }

    public void setReporteSaldoItemCodBarraMbaActual(T008ReporteSaldoItemCodBarraMba reporteSaldoItemCodBarraMbaActual) {
        this.reporteSaldoItemCodBarraMbaActual = reporteSaldoItemCodBarraMbaActual;
    }

    public List<T008ReporteSaldoItemCodBarraMba> getListaReporteSaldoItemCodBarraMba() {
        return listaReporteSaldoItemCodBarraMba;
    }

    public void setListaReporteSaldoItemCodBarraMba(List<T008ReporteSaldoItemCodBarraMba> listaReporteSaldoItemCodBarraMba) {
        this.listaReporteSaldoItemCodBarraMba = listaReporteSaldoItemCodBarraMba;
    }

    
    public void recargarLista(){
        listaReporteSaldo = new ArrayList<>();
        listaReporteSaldoFecha = new ArrayList<>();
        listaReporteSaldoVendedor = new ArrayList<>();
    }
    public void recargarTotalesReporte(){
        subTotalCantPrecio = new BigDecimal(0);
        valueSubTotal = new BigDecimal(0);
        valueTotal = new BigDecimal(0);
    }
    
    
    public void recargarConsultasTablas() throws SQLException, ClassNotFoundException, FileNotFoundException, MalformedURLException{
        limpiarConsultaSaldo();
        limpiarConsultaFechaColombia();
        limpiarConsultaItemCodBarrasMba();
        limpiarConsultaVendedor();
        consultaSaldoDisponible();
        consultaFechaColombia();
        consultaItemCodBarrasMba();
        consultaVendedor();
        updateSaldoVendedor();
        updateSaldoFechaColombia();
        updateCodItemSaldoFechaColombia();
        updateFechaEntregaMba();
    }
    public void prepareEnviarReporteVendedores() throws SQLException, ClassNotFoundException, FileNotFoundException, MalformedURLException{
        recargarConsultasTablas();
        recargarTotalesReporte();
        envioDatosVendedores();
    }
    public void prepareEnviarReporteConsolidado() throws SQLException, ClassNotFoundException, FileNotFoundException, MalformedURLException{
        recargarTotalesReporte();
        envioDatosConsolidado();
    }
    //limpiar tablas
    public void limpiarConsultaSaldo()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "DELETE FROM bd_eureka_ecuador.t004_reporte_saldo;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void limpiarConsultaFecha()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "DELETE FROM bd_eureka_ecuador.t005_reporte_saldo_fecha;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void limpiarConsultaFechaColombia()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "DELETE FROM bd_eureka_ecuador.t007_reporte_saldo_fecha_colombia;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void limpiarConsultaItemCodBarrasMba()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "DELETE FROM bd_eureka_ecuador.t008_reporte_saldo_item_cod_barra_mba;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public void limpiarConsultaVendedor()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "DELETE FROM bd_eureka_ecuador.t006_reporte_saldo_vendedor;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    //Consulta - insert tablas
    public void consultaSaldoDisponible() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=System");
        Statement stmt = null;
        String query = "SELECT  CLNT_Pedidos_Principal.SALESMAN,  CLNT_Pedidos_Principal.CONTRATO_ID_CORP, CLNT_Pedidos_Principal.CONTRATO_ID,  CLNT_Pedidos_Principal.ORDEN_COMPRA, CLNT_Pedidos_Principal.FECHA_PEDIDO,   CLNT_Pedidos_Principal.CLIENT_ID, CLNT_Ficha_Principal.NOMBRE_CLIENTE,  CLNT_Pedidos_Detalle.PRODUCT_ID,  CLNT_Pedidos_Detalle.PRODUCT_NAME, INVT_Ficha_Principal.DESCRIPTION as desc1, INVT_Ficha_Principal.TALLA, INVT_Ficha_Principal.COLOR, INVT_Ficha_Principal.FAMILIA, INVT_Ficha_Principal.TEMPORADA, INVT_Ficha_Principal.ANIOPRENDA,  SIST_Lista_7.Descripcion as desc2,  CLNT_Pedidos_Detalle.QUANTITY, CLNT_Pedidos_Detalle.BackOrderNoStock, CLNT_Pedidos_Detalle.Despachados,  CLNT_Pedidos_Detalle.Por_Despachar, CLNT_Pedidos_Detalle.Precio_final_con_descuento, INVT_Ficha_Principal.CODE_PROV_O_ALT AS codigo_barra FROM CLNT_Pedidos_Principal INNER JOIN CLNT_Pedidos_Detalle ON CLNT_Pedidos_Principal.CONTRATO_ID_CORP = CLNT_Pedidos_Detalle.CONTRATO_ID_CORP INNER JOIN CLNT_Ficha_Principal ON CLNT_Ficha_Principal.CODIGO_CLIENTE_EMPRESA = CLNT_Pedidos_Principal.CLIENT_ID_CORP INNER JOIN INVT_Ficha_Principal ON CLNT_Pedidos_Detalle.PRODUCT_ID_CORP = INVT_Ficha_Principal.PRODUCT_ID_CORP INNER JOIN SIST_Lista_7 ON INVT_Ficha_Principal.AnioPrenda = SIST_Lista_7.Codigo WHERE CLNT_Pedidos_Principal.CONTRATO_ID_CORP=[CLNT_Pedidos_Detalle].[CONTRATO_ID_CORP] AND CLNT_Pedidos_Detalle.Por_Despachar>0 AND CLNT_Pedidos_Detalle.PRODUCT_ID_CORP=[INVT_Ficha_Principal].[PRODUCT_ID_CORP] AND CLNT_Pedidos_Principal.Tipo_Doc_OC_PE_CT='PE' AND  SIST_Lista_7.Grupo_Categoria='Añote'";
        listaReporteSaldo = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteSaldoActual = new T004ReporteSaldo();
                    reporteSaldoActual.setSalesman(rec.getString("SALESMAN"));
                    reporteSaldoActual.setContratoIdCorp(rec.getString("CONTRATO_ID_CORP"));
                    reporteSaldoActual.setContratoId(rec.getString("CONTRATO_ID"));
                    reporteSaldoActual.setOrdenCompra(rec.getString("ORDEN_COMPRA"));
                    reporteSaldoActual.setFechaPedido(rec.getString("FECHA_PEDIDO"));
                    reporteSaldoActual.setClientId(rec.getString("CLIENT_ID"));
                    reporteSaldoActual.setNombreCliente(rec.getString("NOMBRE_CLIENTE").replace("Ã‘", "Ñ"));
                    reporteSaldoActual.setIdItem(rec.getString("PRODUCT_ID"));
                    reporteSaldoActual.setDescItem(rec.getString("PRODUCT_NAME"));
                    reporteSaldoActual.setDescCompleta(rec.getString("desc1"));
                    reporteSaldoActual.setExt1(rec.getString("TALLA"));
                    reporteSaldoActual.setExt2(rec.getString("COLOR"));
                    reporteSaldoActual.setExt3(rec.getString("FAMILIA"));
                    reporteSaldoActual.setExt4(rec.getString("TEMPORADA"));
                    reporteSaldoActual.setExt5(rec.getString("ANIOPRENDA"));
                    reporteSaldoActual.setDescExt5(rec.getString("desc2"));
                    reporteSaldoActual.setQuantity(rec.getInt("QUANTITY"));
                    reporteSaldoActual.setBackordernostock(rec.getInt("BackOrderNoStock"));
                    reporteSaldoActual.setDespachados(rec.getInt("Despachados"));
                    reporteSaldoActual.setPorDespachar(rec.getInt("Por_Despachar"));
                    reporteSaldoActual.setCodBarraItem(rec.getString("codigo_barra"));
                    reporteSaldoActual.setPrecioFinalConDescuento(rec.getBigDecimal("Precio_final_con_descuento"));
                    reporteSaldoFacade.create(reporteSaldoActual);
                    } catch (SQLException e) {
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
    public void consultaFechaColombia() throws MalformedURLException{  
        String response = ClientBuilder.newClient() .target(
            URI.create(new URL("http://192.168.160.154:8080/intranet/webresources/reporteFechasEcuador").toExternalForm()))
            .request().get(String.class);
        Gson gson = new Gson();
	Type tipo = new TypeToken<List<T007ReporteSaldoFechaColombia>>(){}.getType();
	listaReporteSaldoFechaColombia = gson.fromJson(response, tipo);
        for(T007ReporteSaldoFechaColombia item: listaReporteSaldoFechaColombia){
            getReporteSaldoFechaColombiaFacade().create(item);
        }
    }
    public void consultaItemCodBarrasMba() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=System");
        Statement stmt = null;
        String query = "SELECT DISTINCT CLNT_Pedidos_Detalle.PRODUCT_ID_CORP, INVT_Ficha_Principal.CODE_PROV_O_ALT FROM CLNT_Pedidos_Principal CLNT_Pedidos_Principal INNER JOIN CLNT_Pedidos_Detalle CLNT_Pedidos_Detalle  ON CLNT_Pedidos_Detalle.CONTRATO_ID_CORP = CLNT_Pedidos_Principal.CONTRATO_ID_CORP INNER JOIN INVT_Ficha_Principal INVT_Ficha_Principal ON INVT_Ficha_Principal.PRODUCT_ID_CORP = CLNT_Pedidos_Detalle.PRODUCT_ID_CORP WHERE CLNT_Pedidos_Detalle.Por_Despachar>0  AND CLNT_Pedidos_Principal.Tipo_Doc_OC_PE_CT='PE'  AND INVT_Ficha_Principal.CODE_PROV_O_ALT <> '' ORDER BY CLNT_Pedidos_Detalle.PRODUCT_ID_CORP";
        listaReporteSaldoItemCodBarraMba = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteSaldoItemCodBarraMbaActual = new T008ReporteSaldoItemCodBarraMba();
                    reporteSaldoItemCodBarraMbaActual.setCodItemMba(rec.getString("PRODUCT_ID_CORP"));
                    reporteSaldoItemCodBarraMbaActual.setCodBarraMba(rec.getString("CODE_PROV_O_ALT"));
                    reporteSaldoItemCodBarraMbaFacade.create(reporteSaldoItemCodBarraMbaActual);
                    } catch (SQLException e) {
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
    
    
    public void consultaFecha() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=System");
        Statement stmt = null;
        String query = "SELECT PEDIDOS_ADICIONAL.CONTRATO_ID, PEDIDOS_ADICIONAL.REFERENCIA_D1 FROM PEDIDOS_ADICIONAL WHERE PEDIDOS_ADICIONAL.CONTRATO_ID Like '%PE%';";
        listaReporteSaldoFecha = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteSaldoFechaActual = new T005ReporteSaldoFecha();
                    reporteSaldoFechaActual.setCodigo(rec.getString("CONTRATO_ID"));
                    reporteSaldoFechaActual.setFecha(rec.getString("REFERENCIA_D1"));
                    reporteSaldoFechaFacade.create(reporteSaldoFechaActual);
                    } catch (SQLException e) {
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
    public void consultaVendedor() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=System");
        Statement stmt = null;
        String query = "SELECT SIST_Lista_1.CODE, SIST_Lista_1.DESCRIPTION_SPN, SIST_Lista_1.STRING80 FROM SIST_Lista_1 WHERE SIST_Lista_1.GROUP_CATEGORY='sellm' and SIST_Lista_1.STRING80 <> '';";
        listaReporteSaldoVendedor = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    reporteSaldoVendedorActual = new T006ReporteSaldoVendedor();
                    reporteSaldoVendedorActual.setCodigo(rec.getString("CODE"));
                    reporteSaldoVendedorActual.setNombre(rec.getString("DESCRIPTION_SPN"));
                    reporteSaldoVendedorActual.setCorreo(rec.getString("STRING80"));
                    reporteSaldoVendedorFacade.create(reporteSaldoVendedorActual);
                    } catch (SQLException e) {
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
    
    
    
    
    
    //update
    public void updateSaldoVendedor()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t004_reporte_saldo INNER JOIN t006_reporte_saldo_vendedor ON t004_reporte_saldo.salesman = t006_reporte_saldo_vendedor.codigo SET t004_reporte_saldo.nombre_vendedor = t006_reporte_saldo_vendedor.nombre, t004_reporte_saldo.correo_vendedor =  t006_reporte_saldo_vendedor.correo;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void updateSaldoFecha()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t004_reporte_saldo  INNER JOIN t005_reporte_saldo_fecha ON t004_reporte_saldo.contrato_id_corp = t005_reporte_saldo_fecha.codigo  SET t004_reporte_saldo.fecha_comprom = t005_reporte_saldo_fecha.fecha;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public void updateSaldoFechaColombia()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t004_reporte_saldo INNER JOIN t007_reporte_saldo_fecha_colombia ON t004_reporte_saldo.contrato_id_corp = t007_reporte_saldo_fecha_colombia.nro_pedido_mba AND t004_reporte_saldo.cod_barra_item = t007_reporte_saldo_fecha_colombia.cod_barra_item SET t004_reporte_saldo.fecha_comprom = t007_reporte_saldo_fecha_colombia.fecha_entrega;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void updateCodItemSaldoFechaColombia()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t007_reporte_saldo_fecha_colombia INNER JOIN t008_reporte_saldo_item_cod_barra_mba ON t007_reporte_saldo_fecha_colombia.cod_barra_item = t008_reporte_saldo_item_cod_barra_mba.cod_barra_mba SET t007_reporte_saldo_fecha_colombia.cod_item_mba = t008_reporte_saldo_item_cod_barra_mba.cod_item_mba;";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void updateFechaEntregaMba()
        throws SQLException, ClassNotFoundException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=System");
        Statement stmt = null;
        for(T007ReporteSaldoFechaColombia item: getReporteSaldoFechaColombiaFacade().findAll()){
            String query = "UPDATE CLNT_Pedidos_Detalle SET Referencia_1='"+item.getFechaEntrega()+"' WHERE CONTRATO_ID_CORP ='"+item.getNroPedidoMba()+"' AND PRODUCT_ID_CORP = '"+item.getCodItemMba()+"';";
            //System.out.println("update "+query);
            try {
                stmt = conn.createStatement();
                stmt.execute(query);
            }catch (SQLException sqle) {
                System.out.println("error "+sqle);
            }
            finally {
                if (stmt != null) { stmt.close(); }
            }
        }
    }
    //Ciclo envio datos
    public void envioDatosVendedores() throws FileNotFoundException{
        for(T006ReporteSaldoVendedor item: reporteSaldoVendedorFacade.findAll()){
            nombreEnvio = item.getNombre();
            correoEnvio = item.getCorreo();
            listaReporteSaldoEnvio = new ArrayList<>();
            valueTotal = new BigDecimal(0);
            for(T004ReporteSaldo item2: reporteSaldoFacade.ordenarReporte()){
                if(item2.getSalesman().equals(item.getCodigo())){
                    listaReporteSaldoEnvio.add(item2);
                }
            }
            createPDFVendedor();
            sendMailReporte(correoEnvio, nombreEnvio);
        }
    }
    //Ciclo envio datos General
    public void envioDatosConsolidado() throws FileNotFoundException{
        listaReporteSaldoEnvio = new ArrayList<>();
        valueTotal = new BigDecimal(0);
        for(T006ReporteSaldoVendedor item: reporteSaldoVendedorFacade.findAll()){
            for(T004ReporteSaldo item2: reporteSaldoFacade.ordenarReporte()){
                if(item2.getSalesman().equals(item.getCodigo())){
                    item2.setNombreVendedor(item.getNombre());
                    listaReporteSaldoEnvio.add(item2);
                }
            }
        }
        for(T004ReporteSaldo item: reporteSaldoFacade.ordenarReporte()){
            if(item.getSalesman().equals("")){
                item.setNombreVendedor("SIN VENDEDOR");
                listaReporteSaldoEnvio.add(item);
            }
        }
        correoEnvio ="taniaz@ekazipper.com, ecuador.facturacion@ekazipper.com";
        nombreEnvio ="TANIA ZONENSAIN";
        createPDFConsolidado();
        sendMailReporte(correoEnvio, nombreEnvio);
    }
    public void sendMailReporte(String correo, String nombre) throws FileNotFoundException{
         try {
            emailController = new EmailController();
            String asunto = "Reporte saldos de pedidos - CORPORACIÓN EKAZIPPER DEL ECUADOR S.A.";
            String cuerpo = "Señor(a) " +nombreEnvio+".\n\n"
                    + "Se adjunta el reporte diario de los saldos de pedidos.\n\n"
                    + "Nota: Este es un mensaje automático, por favor no responder.\n\n"
                    + "Departamento de TI\n\n";
            String adjunto = "resources/plano/text1.pdf";
            String nombreAdjunto = "reporteSaldosPedidos.pdf";
            emailController.sendMailAdjunto("ecuador.facturacion@ekazipper.com, luisc@ekazipper.com,"+correo, asunto, cuerpo, adjunto,nombreAdjunto); 
         } catch (Exception e) {
             System.out.println("error correo "+e);
         }    
    }
    public void createPDFVendedor(){
        try {
            FacesContext facesContext= FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session= (HttpSession) externalContext.getSession(true);
            String url ="http" + "://" + "localhost" + ":" + "8080" + "/intranet"  + "/faces/usuario/modReportes/formato.xhtml;jsessionid=" + session.getId()+"?pdf=true";
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
    public void createPDFConsolidado(){
        try {
            FacesContext facesContext= FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session= (HttpSession) externalContext.getSession(true);
            String url ="http" + "://" + "localhost" + ":" + "8080" + "/intranet"  + "/faces/usuario/modReportes/formato2.xhtml;jsessionid=" + session.getId()+"?pdf=true";
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
    //Calcular total y subtotal reporte
    public BigDecimal getSubTotalAcumulado() {
        BigDecimal aux = subTotalCantPrecio ;
        subTotalCantPrecio = new BigDecimal(0);
        return aux;
    }
    public void obtenerSubTotal(int cantidad, BigDecimal precio){
        valueSubTotal  = precio.multiply(new BigDecimal(cantidad));
        subTotalCantPrecio = valueSubTotal.add(subTotalCantPrecio);
        valueTotal = valueSubTotal.add(valueTotal);
    }
}
