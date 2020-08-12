/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.controller;

import com.eurekaintranet.entities.T001Usuario;
import com.eurekaintranet.entities.T003ItemsMba;
import com.eurekaintranet.sessions.T003ItemsMbaFacade;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;


@Named(value = "crearItemMbaController")
@SessionScoped
public class CrearItemMbaController implements Serializable {

    private String idItem;
    private String descItem;
    private String um;
    private String grupo;
    private String subGrupo;
    private String codCosto;
    private String codIncome;
    private String codInventario;
    private BigDecimal costo;
    private BigDecimal precio;
    private String tipoOrigen;
    private String grupoPrenda;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String descExt5;
    private double codMarca;
    //set campos 4d MBA
    
    private String descripcionCompleta1;
    private String descripcionCompleta2;
    private String codigoBarra;
    
    @EJB
    private T003ItemsMbaFacade itemFacade;
    private T003ItemsMba itemActual;
    private T003ItemsMba itemDuplicar;
    private List<T003ItemsMba> listaItem;
    private List<T003ItemsMba> listaItemCrear;
    private List<T003ItemsMba> listaItemPedidoWeb;
    
    private ExtController extActual;
    private List<ExtController> listaExt1;
    private List<ExtController> listaExt2;
    private List<ExtController> listaExt3;
    private List<ExtController> listaExt4;
    private List<ExtController> listaExt5;
    
    private String paramIdItem;
    private String paramDescItem;
    private String paramExt1;
    private String paramExt2;
    private String paramExt3;
    private String paramExt4;
    private String paramExt5;
    private String paramDescExt5;
    
    private T001Usuario usuarioActual;
    private int consecutivo;
    
    private final String[] estado = {"Talla","Color", "Famil", "Tempo", "Añote"};
    
    private List<Integer> listaPedidosSinItemCreado;
    private EmailController emailController;
    private List<String> listaDescCompletaItem;
    private String descCompletaItem;
        
    //campos 4d tabla INVT_Ficha_Principal
    public CrearItemMbaController() {
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubGrupo() {
        return subGrupo;
    }

    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    public String getCodCosto() {
        return codCosto;
    }

    public void setCodCosto(String codCosto) {
        this.codCosto = codCosto;
    }

    public String getCodIncome() {
        return codIncome;
    }

    public void setCodIncome(String codIncome) {
        this.codIncome = codIncome;
    }

    public String getCodInventario() {
        return codInventario;
    }

    public void setCodInventario(String codInventario) {
        this.codInventario = codInventario;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(String tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public String getGrupoPrenda() {
        return grupoPrenda;
    }

    public void setGrupoPrenda(String grupoPrenda) {
        this.grupoPrenda = grupoPrenda;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public String getDescExt5() {
        return descExt5;
    }

    public void setDescExt5(String descExt5) {
        this.descExt5 = descExt5;
    }

    public double getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(double codMarca) {
        this.codMarca = codMarca;
    }

    public ExtController getExtActual() {
        return extActual;
    }

    public void setExt1Actual(ExtController extActual) {
        this.extActual = extActual;
    }

    public List<ExtController> getListaExt1() {
        return listaExt1;
    }

    public void setListaExt1(List<ExtController> listaExt1) {
        this.listaExt1 = listaExt1;
    }
    
    //campos controller

    public T003ItemsMbaFacade getItemFacade() {
        return itemFacade;
    }

    public void setItemFacade(T003ItemsMbaFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    public T003ItemsMba getItemActual() {
        return itemActual;
    }

    public void setItemActual(T003ItemsMba itemActual) {
        this.itemActual = itemActual;
    }

    public T003ItemsMba getItemDuplicar() {
        return itemDuplicar;
    }

    public void setItemDuplicar(T003ItemsMba itemDuplicar) {
        this.itemDuplicar = itemDuplicar;
    }

    public List<T003ItemsMba> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<T003ItemsMba> listaItem) {
        this.listaItem = listaItem;
    }
    
    public List<T003ItemsMba> getListaItemCrear() {
        return listaItemCrear;
    }

    public List<T003ItemsMba> getListaItemPedidoWeb() {
        return listaItemPedidoWeb;
    }

    public void setListaItemPedidoWeb(List<T003ItemsMba> listaItemPedidoWeb) {
        this.listaItemPedidoWeb = listaItemPedidoWeb;
    }

    
    public void setListaItemCrear(List<T003ItemsMba> listaItemCrear) {
        this.listaItemCrear = listaItemCrear;
    }

    

    public String getParamIdItem() {
        return paramIdItem;
    }

    public void setParamIdItem(String paramIdItem) {
        this.paramIdItem = paramIdItem;
    }

    public String getParamDescItem() {
        return paramDescItem;
    }

    public void setParamDescItem(String paramDescItem) {
        this.paramDescItem = paramDescItem;
    }

    public String getParamExt1() {
        return paramExt1;
    }

    public void setParamExt1(String paramExt1) {
        this.paramExt1 = paramExt1;
    }

    public String getParamExt2() {
        return paramExt2;
    }

    public void setParamExt2(String paramExt2) {
        this.paramExt2 = paramExt2;
    }

    public String getParamExt3() {
        return paramExt3;
    }

    public void setParamExt3(String paramExt3) {
        this.paramExt3 = paramExt3;
    }

    public String getParamExt4() {
        return paramExt4;
    }

    public void setParamExt4(String paramExt4) {
        this.paramExt4 = paramExt4;
    }

    public String getParamExt5() {
        return paramExt5;
    }

    public void setParamExt5(String paramExt5) {
        this.paramExt5 = paramExt5;
    }

    public String getParamDescExt5() {
        return paramDescExt5;
    }

    public void setParamDescExt5(String paramDescExt5) {
        this.paramDescExt5 = paramDescExt5;
    }

   
    public T001Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(T001Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDescripcionCompleta1() {
        return descripcionCompleta1;
    }

    public void setDescripcionCompleta1(String descripcionCompleta1) {
        this.descripcionCompleta1 = descripcionCompleta1;
    }

    public String getDescripcionCompleta2() {
        return descripcionCompleta2;
    }

    public void setDescripcionCompleta2(String descripcionCompleta2) {
        this.descripcionCompleta2 = descripcionCompleta2;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<ExtController> getListaExt2() {
        return listaExt2;
    }

    public void setListaExt2(List<ExtController> listaExt2) {
        this.listaExt2 = listaExt2;
    }

    public List<ExtController> getListaExt3() {
        return listaExt3;
    }

    public void setListaExt3(List<ExtController> listaExt3) {
        this.listaExt3 = listaExt3;
    }

    public List<ExtController> getListaExt4() {
        return listaExt4;
    }

    public void setListaExt4(List<ExtController> listaExt4) {
        this.listaExt4 = listaExt4;
    }

    public List<ExtController> getListaExt5() {
        return listaExt5;
    }

    public void setListaExt5(List<ExtController> listaExt5) {
        this.listaExt5 = listaExt5;
    }

    public List<String> getListaDescCompletaItem() {
        return listaDescCompletaItem;
    }

    public void setListaDescCompletaItem(List<String> listaDescCompletaItem) {
        this.listaDescCompletaItem = listaDescCompletaItem;
    }

    public String getDescCompletaItem() {
        return descCompletaItem;
    }

    public void setDescCompletaItem(String descCompletaItem) {
        this.descCompletaItem = descCompletaItem;
    }
    
    
    //METODOS
    
    public void recargarLista(){
        listaItem = new ArrayList<>();
        listaItemCrear = new ArrayList<>();
        listaItemCrear = getItemFacade().itemsCrear((short)0,(short)1);
    }
    
    public void recargarListaPortalWeb(){
        listaItem = new ArrayList<>();
        listaItemCrear = new ArrayList<>();
        listaItemCrear = getItemFacade().itemsCrearPortalWeb((short)0,(short)1);
    }
    
    public void recargarParametros(){
            paramIdItem = "";
            paramDescItem = "";
            paramExt1 = "";
            paramExt2 = "";
            paramExt3 = "";
            paramExt4 = "";
            paramExt5 = "";
            paramDescExt5 = "";
    }
    public void recargarExtensiones(){
        extActual = new ExtController();
        listaExt1 = new ArrayList<>();
        listaExt2 = new ArrayList<>();
        listaExt3 = new ArrayList<>();
        listaExt4 = new ArrayList<>();
        listaExt5 = new ArrayList<>();
    }
    public String prepareCreate() throws SQLException, ClassNotFoundException{
        recargarParametros();
        recargarLista();
        recargarExtensiones();
        consultarExt();
        consecutivo = 0;
        itemActual = new T003ItemsMba();
        itemDuplicar = new T003ItemsMba();
        consultarItemsPortalWeb();
        //recargarCamposExt();
        return "/administrador/modCrearItemsMba/crearItem";
    }

    
    
    /*
    public void createItemDuplicado(){
        itemActual.setCamp48(itemActual.getCamp3()+" "+itemActual.getCamp110().getId()+" "+itemActual.getCamp111().getId()+" "+itemActual.getCamp112().getId()+" "+itemActual.getCamp113().getId()+" "+itemActual.getCamp114().getId()+" "+itemActual.getCamp114().getDescripcion());
        itemActual.setCamp49(itemActual.getCamp3()+" "+itemActual.getCamp110().getId()+" "+itemActual.getCamp111().getId()+" "+itemActual.getCamp112().getId()+" "+itemActual.getCamp113().getId()+" "+itemActual.getCamp114().getId());
        itemActual.setEstado(true);
        itemActual.setIdUsuario(usuarioActual);
        itemActual.setFechaCreacion(new Date());
        getItemFacade().create(itemActual);
    }
    public String duplicarItem() throws SQLException, ClassNotFoundException{
        itemDuplicar = itemActual;
        listaItem.remove(itemActual);
        listaItemCrear.add(itemDuplicar);
        itemDuplicar.setCamp2("INV"+String.format("%08d", consultarUltimaReferencia()+consecutivo));
        itemDuplicar.setEstado(false);
        consecutivo ++;
        return "crear";
    }*/
    
    public void recargarParametrosVacios(){
        if(paramIdItem.equals("NULL")){
            paramIdItem = "";
        }
        if(paramDescItem.equals("NULL")){
            paramDescItem = "";
        }
        if(paramExt1.equals("NULL")){
            paramExt1 = "";
        }
        if(paramExt2.equals("NULL")){
            paramExt2 = "";
        }
        if(paramExt3.equals("NULL")){
            paramExt3 = "";
        }
        if(paramExt3.equals("NULL")){
            paramExt3 = "";
        }
        if(paramExt4.equals("NULL")){
            paramExt4 = "";
        }
        if(paramExt5.equals("NULL")){
            paramExt5 = "";
        }        
    }
    public String crearwhere(){
        String where ="";
        if(!paramIdItem.equals("")){
            where = where+" PRODUCT_ID LIKE '%"+paramIdItem+"%'";
        }
        if(!paramDescItem.equals("")){
            if(paramIdItem.equals("")){
                where = where+" PRODUCT_NAME LIKE '%"+paramDescItem+"%'";
            }else{
                where = where+" AND  PRODUCT_NAME LIKE '%"+paramDescItem+"%'";
            }
        }
        if(!paramExt1.equals("")){
            if(paramIdItem.equals("") && paramDescItem.equals("")){
                where = where+" TALLA LIKE '%"+paramExt1+"%'";
            }else{
                where = where+" AND TALLA LIKE '%"+paramExt1+"%'";
            }
        }
        if(!paramExt2.equals("")){
            if(paramIdItem.equals("") && paramDescItem.equals("") && paramExt1.equals("")){
                where = where+" COLAND LIKE '%"+paramExt2+"%'";
            }else{
                where = where+" AND COLAND LIKE '%"+paramExt2+"%'";
            }
        }
        if(!paramExt3.equals("")){
            if(paramIdItem.equals("") && paramDescItem.equals("") && paramExt1.equals("") && paramExt2.equals("")){
                where = where+" FAMILIA LIKE '%"+paramExt3+"%'";
            }else{
                where = where+" AND FAMILIA LIKE '%"+paramExt3+"%'";
            }
        }
        if(!paramExt4.equals("")){
            if(paramIdItem.equals("") && paramDescItem.equals("") && paramExt1.equals("") && paramExt2.equals("") && paramExt3.equals("")){
                where = where+" TEMPORADA LIKE '%"+paramExt4+"%'";
            }else{
                where = where+" AND TEMPORADA LIKE '%"+paramExt4+"%'";
            }
        }
        if(!paramExt5.equals("")){
            if(paramIdItem.equals("") && paramDescItem.equals("") && paramExt1.equals("") && paramExt2.equals("") && paramExt3.equals("") && paramExt4.equals("")){
                where = where+" ANIOPRENDA LIKE '%"+paramExt5+"%'";
            }else{
                where = where+" AND ANIOPRENDA LIKE '%"+paramExt5+"%'";
            }
        }
        return where;
    }
    public String consultarPalabraClave() throws SQLException, ClassNotFoundException{
        crearwhere();
        consultarItemPalabraClave();
        return "crearItem";
    }
    public void consultarItemPalabraClave() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
            String query = "SELECT INVT_Ficha_Principal.PRODUCT_ID as idItem, INVT_Ficha_Principal.PRODUCT_NAME as descItem, INVT_Ficha_Principal.UM as um, INVT_Ficha_Principal.GROUP_CODE as grupo, INVT_Ficha_Principal.SUB_GROUP_CODE as subGrupo, INVT_Ficha_Principal.[GL COST CODE] as codCosto, INVT_Ficha_Principal.[GL INCOME CODE] as codIncome, INVT_Ficha_Principal.[GL INVEN CODE] as codInventario, INVT_Ficha_Principal.ACTUAL_COST as costo, INVT_Ficha_Principal.SELL as precio, INVT_Ficha_Principal.Tipo_Origen as tipoOrigen, INVT_Ficha_Principal.GrupoPrenda as grupoPrenda, INVT_Ficha_Principal.Talla as ext1, INVT_Ficha_Principal.Color as ext2, INVT_Ficha_Principal.Familia as ext3, INVT_Ficha_Principal.Temporada as ext4, INVT_Ficha_Principal.AnioPrenda as ext5, SIST_Lista_7.DESCRIPCION as descExt5, INVT_Ficha_Principal.Codigo_Marca as codMarca FROM INVT_Ficha_Principal INNER JOIN SIST_Lista_7 ON  INVT_Ficha_Principal.ANIOPRENDA = SIST_Lista_7.codigo AND SIST_Lista_7.Grupo_categoria = 'Añote'  WHERE ("+crearwhere()+") order by PRODUCT_ID;";
        listaItem = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    itemActual = new T003ItemsMba();
                    itemActual.setIdItem(rec.getString("idItem"));
                    itemActual.setDescItem(rec.getString("descItem"));
                    itemActual.setUm(rec.getString("um"));
                    itemActual.setGrupo(rec.getString("grupo"));
                    itemActual.setSubGrupo(rec.getString("subGrupo"));
                    itemActual.setCodCosto(rec.getString("codCosto"));
                    itemActual.setCodIncome(rec.getString("codIncome"));
                    itemActual.setCodInventario(rec.getString("codInventario"));
                    itemActual.setCosto(rec.getBigDecimal("costo"));
                    itemActual.setPrecio(rec.getBigDecimal("precio"));
                    itemActual.setTipoOrigen(rec.getString("tipoOrigen"));
                    itemActual.setGrupoPrenda(rec.getString("grupoPrenda"));
                    itemActual.setExt1(rec.getString("ext1"));
                    itemActual.setExt2(rec.getString("ext2"));
                    itemActual.setExt3(rec.getString("ext3"));
                    itemActual.setExt4(rec.getString("ext4"));
                    itemActual.setExt5(rec.getString("ext5"));
                    itemActual.setDescExt5(rec.getString("descExt5"));
                    itemActual.setExt5(itemActual.getExt5()+"-"+itemActual.getDescExt5());
                    itemActual.setCodMarca(rec.getString("codMarca"));
                    listaItem.add(itemActual);
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
    public String duplicarItem() throws SQLException, ClassNotFoundException{
        itemDuplicar = itemActual;
        //listaItem.remove(itemActual);
        itemDuplicar.setIdItem("INV");
        itemDuplicar.setCodBarra("");
        itemDuplicar.setEstado((short)0);
        itemDuplicar.setIdTipoCreacion(1);
        itemFacade.create(itemDuplicar);
        listaItemCrear= getItemFacade().itemsCrear((short)0,(short)1);
        return "crearItem";
    }
    public Boolean validarItemDuplicado(){
        boolean itemRepetido = false;
        for(T003ItemsMba item:listaItemCrear){
           if(itemActual.getDescripcionCompleta1().equals(item.getDescripcionCompleta1())){
               itemRepetido = true;
            }
        }
        return itemRepetido;
    } 
    
    public String duplicarItemPedidoWeb() throws SQLException, ClassNotFoundException{
        if(!validarItemDuplicado()){
            itemDuplicar = itemActual;
            //listaItem.remove(itemActual);
            itemDuplicar.setIdItem("INV");
            itemDuplicar.setCodBarra("");
            itemDuplicar.setEstado((short)0);
            itemDuplicar.setIdTipoCreacion(2);
            String ext5Temp="";
            String descExt5Temp="";
            extVacio();
            if(!itemActual.getExt5().equals("")){
                descExt5Temp=(itemActual.getExt5().substring(itemActual.getExt5().indexOf('-')+1,itemActual.getExt5().length()));
                ext5Temp=(itemActual.getExt5().substring(0,itemActual.getExt5().indexOf('-')));
            }
            completarDatosItemPedidosWeb();
            itemDuplicar.setDescripcionCompleta1((itemActual.getDescItem()+" "+itemActual.getExt1()+" "+itemActual.getExt2()+" "+itemActual.getExt3()+" "+itemActual.getExt4()+" "+ext5Temp+" "+descExt5Temp).replace("  "," ").replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " "));
            itemFacade.create(itemDuplicar);
            listaItemCrear= getItemFacade().itemsCrearPortalWeb((short)0,(short)1);
            listaItemPedidoWeb.remove(itemDuplicar);
            return "crearItemPortalWeb";
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto existente", "Este producto ya se encuentra pendiente por crear");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return null;
        }
    }
    public int consultarUltimaReferencia4d()
        throws SQLException, ClassNotFoundException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT max(cast(INVT_Ficha_Principal.PRODUCT_ID as int))+1 referencia FROM INVT_Ficha_Principal";
        int referencia = 0;
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    referencia = rec.getInt("referencia");
                } catch (Exception e) {
                    System.out.println("error"+e);
                }
            } 
            return referencia;
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
             return 0;
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public int consultarUltimaReferenciaMysql()
        throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_eureka_ecuador","root","1234"); 
        Statement stmt = null;
        String query = "SELECT max(convert(substring(id_item,4),signed integer)) + 1 referencia FROM bd_eureka_ecuador.t003_items_mba";
        int referencia = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                referencia = rs.getInt("referencia");
            }
            return referencia;
        } catch (SQLException e ) {
            System.out.println("error "+e);
            return 0;
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
    
    
    public void escribirConector() throws IOException{
        try {
            int numLinea = 0;
            String linea = "";
            BufferedWriter texto = new BufferedWriter(new FileWriter(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.txt")));
            while (numLinea<20){
                texto.newLine();
                numLinea++;
            }
            numLinea = 1;
            for(T003ItemsMba item: listaItemCrear){
                if(item.getEstado() == 1){
                    linea = numLinea+" \t"+item.getIdItem()+" \t"+item.getDescItem()+"\t \t"+item.getUm()+" \t"+item.getGrupo()+" \t"+item.getSubGrupo()+"\tTRUE\tP\t"+item.getCodCosto()+" \t"+item.getCodIncome()+" \t"+item.getCodInventario()+"\t999.999.999.999\t\tFALSE\tFALSE\tFALSE\t\t"+item.getCodCosto()+"\t\t\t\t\t\t\t\t\t\t"+item.getPrecio()+"\t\t\t\t\t\t\t\t\t\t\t0\t\t\t\t"+item.getDescripcionCompleta1()+"\t"+item.getDescripcionCompleta2()+"\t\t\tIBN\tFALSE\t\t\t\tFALSE\t00/00/0000\t\t\tFALSE\tFALSE\tFALSE\tFALSE\tFALSE\tFALSE\t\t"+"cod barras"+"\t\t\tFALSE\t\t\t\t\t\t\t\t\t\t\tFALSE\t\t\tFALSE\t\t\t\t"+item.getTipoOrigen()+"\tTRUE\tTRUE\tFALSE\tFALSE\tFALSE\tFALSE\tFALSE\t\t\tFALSE\tFALSE\t"+item.getGrupoPrenda()+" \t"+item.getExt1()+" \t"+item.getExt2()+" \t"+item.getExt3()+" \t"+item.getExt4()+" \t"+item.getExt5()+"\t0\tFALSE\t\t\t"+item.getFechaCreacion()+"\t115A718002\t\t\tFALSE\tFALSE\t"+item.getCodMarca()+"\t\tFALSE\t\t0\tFALSE\t1";
                    texto.write(linea);
                    texto.newLine();
                    numLinea ++;
                }
            }
            texto.close(); 
        } catch (Exception e) {
            System.out.println("No se pudo limpiar archivo "+e);
        } 
    }
    public DefaultStreamedContent getDescargarConector() {
        try {
             escribirConector();
             String newFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/plano/text1.txt");
             listaItemCrear = new ArrayList<>();
             listaItem = new ArrayList<>();
             return new DefaultStreamedContent(new FileInputStream(newFileName), "file/txt", "estructura.txt");
             }
          catch (Exception e) {
             return null;
         }
    }
    public void createItemDuplicado(){
        try {
            extVacio();
            String itemDuplicado=consultarItemDuplicado();
            String codBarrasDuplicado =consultarCodBarrasDuplicado();
            if(codBarrasDuplicado.equals("")){
                if(itemDuplicado.equals("") ){
                    if(!itemActual.getExt5().equals("")){
                        itemActual.setDescExt5(itemActual.getExt5().substring(itemActual.getExt5().indexOf('-')+1,itemActual.getExt5().length()));
                        itemActual.setExt5(itemActual.getExt5().substring(0,itemActual.getExt5().indexOf('-')));
                    }
                    itemActual.setDescripcionCompleta1((itemActual.getDescItem()+" "+itemActual.getExt1()+" "+itemActual.getExt2()+" "+itemActual.getExt3()+" "+itemActual.getExt4()+" "+itemActual.getExt5()+" "+itemActual.getDescExt5()).replace("  "," ").replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " "));
                    itemActual.setDescripcionCompleta2((itemActual.getDescItem()+" "+itemActual.getExt1()+" "+itemActual.getExt2()+" "+itemActual.getExt3()+" "+itemActual.getExt4()+" "+itemActual.getExt5()).replace("  "," ").replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " "));
                    itemActual.setEstado((short)1);
                    itemActual.setIdUsuario(usuarioActual);
                    itemActual.setFechaCreacion(new Date());getItemFacade().edit(itemActual);
                    //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    //String texto2x="|"+itemActual.getIdItem()+"|"+itemActual.getDescItem()+"||"+itemActual.getUm()+"|"+itemActual.getGrupo()+"|"+itemActual.getSubGrupo()+"|TRUE|P|"+itemActual.getCodCosto()+"|"+itemActual.getCodIncome()+"|"+itemActual.getCodInventario()+"|999999999999||FALSE|FALSE|FALSE|||"+itemActual.getCodCosto()+"||||||||||0|"+itemActual.getPrecio()+"||||||||||||0|||||"+itemActual.getDescripcionCompleta1()+"|"+itemActual.getDescripcionCompleta2()+"||||IBN|FALSE|||||FALSE|00/00/0000|||FALSE|FALSE|FALSE||FALSE|FALSE|FALSE|||"+itemActual.getCodBarra()+"||||FALSE||||||||||||FALSE||||FALSE|||||"+itemActual.getTipoOrigen()+"|TRUE|TRUE|FALSE|FALSE|FALSE|FALSE|FALSE|||FALSE|FALSE|"+itemActual.getGrupoPrenda()+"|"+itemActual.getExt1()+"|"+itemActual.getExt2()+"|"+itemActual.getExt3()+"|"+itemActual.getExt4()+"|"+itemActual.getExt5()+"||0|FALSE|||"+formatoFecha.format(itemActual.getFechaCreacion())+"|||||FALSE|FALSE|"+itemActual.getCodMarca()+"|||FALSE|||0|";
                    //String query = "INSERT INTO SIST_API(CODE_s, CORP_s, GROUP_CATEGORY_s, INTEGER_1, TEXTO1_10,TEXTO1_24, TEXTO2_X, TEXTO3_X, TEXTO4_X, NumDoc_s, IDConsulta_s, Local_origen, Local_destino,ORIGIN, Opcion_i )VALUES('16','AMERI','API',1,'2','192.168.181.233','"+texto2x+"',9,'RP1-AMERI', 'PRI', 'PRI','PRI', 0)";
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto existente", "Este producto ya se encuentra registrado en la base de datos del MBA");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código de barras existente", "El código de barras ya se encuentra registrado en la base de datos del MBA");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        
    }
    
    
    
    public void extVacio(){
        try {
            boolean result1 = itemActual.getExt1().equals("");
        } catch (NullPointerException ex) {
            itemActual.setExt1("");
        }
        try {
            boolean result2 = itemActual.getExt2().equals("");
        } catch (NullPointerException ex) {
            itemActual.setExt2("");
        }
        try {
            boolean result3 = itemActual.getExt3().equals("");
        } catch (NullPointerException ex) {
            itemActual.setExt3("");
        }
        try {
            boolean result4 = itemActual.getExt4().equals("");
        } catch (NullPointerException ex) {
            itemActual.setExt4("");
        }
        try {
            boolean result5 = itemActual.getExt5().equals("");
        } catch (NullPointerException ex) {
            itemActual.setExt5("");
        }
        try {
            boolean result6 = itemActual.getCodBarra().equals("");
        } catch (NullPointerException ex) {
            itemActual.setCodBarra("");
        }
    }
    public void consultarExt() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        for (int i = 0; i < estado.length; i++){
            String query = "select codigo, descripcion from SIST_Lista_7 where SIST_Lista_7.Grupo_Categoria = '"+estado[i]+"'";
            try {
            stmt = conn.createStatement();
            ResultSet rec = stmt.executeQuery(query);
                while (rec.next()) {
                    try {
                        extActual = new ExtController();
                        extActual.setCodigo(rec.getString("codigo"));
                        extActual.setDescripcion("");
                        switch (i) {
                            case 0:
                                listaExt1.add(extActual);
                                break;
                            case 1:
                                listaExt2.add(extActual);
                                break;
                            case 2:
                                listaExt3.add(extActual);
                                break;
                            case 3:
                                listaExt4.add(extActual);
                                break;
                            case 4:
                                extActual.setDescripcion("-"+rec.getString("descripcion"));
                                listaExt5.add(extActual);
                                break;
                            default:
                                break;
                        }
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
    }
    public String consultarDescExt5() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "select codigo, descripcion from SIST_Lista_7 where SIST_Lista_7.Grupo_Categoria = 'Añote' AND SIST_Lista_7.codigo = '"+itemActual.getExt5()+"'";
        String descripcionExt5 = "";
        try {
            stmt = conn.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    descripcionExt5 =rec.getString("codigo");
                } catch (SQLException e) {
                    System.out.println("error"+e);
                    return "";
                }
            }
            return descripcionExt5;
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
            return "";
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    
    public String consultarItemDuplicado() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "select INVT_Ficha_Principal.PRODUCT_ID from INVT_Ficha_Principal WHERE PRODUCT_NAME = '"+itemActual.getDescItem()+"' AND Talla = '"+itemActual.getExt1()+"' AND Color = '"+itemActual.getExt2()+"' AND Familia= '"+itemActual.getExt3()+"' AND Temporada = '"+itemActual.getExt4()+"' AND AnioPrenda= '"+itemActual.getExt5().substring(0, itemActual.getExt5().indexOf('-'))+"'";
        String item = "";
        try {
            stmt = conn.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                   item = rec.getString("PRODUCT_ID");
                } catch (SQLException e) {
                    System.out.println("error"+e);
                    return "";
                }
            }
            return item;
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
            return "";
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public String consultarCodBarrasDuplicado() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "select INVT_Ficha_Principal.CODE_PROV_O_ALT from INVT_Ficha_Principal WHERE CODE_PROV_O_ALT = '"+ itemActual.getCodBarra()+"'";
        String item = "";
        try {
            stmt = conn.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                   item = rec.getString("CODE_PROV_O_ALT");
                } catch (Exception e) {
                    System.out.println("error"+e);
                    return "";
                }
            }
            return item;
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
            return "";
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    public void delete(){
        try {
            itemFacade.remove(itemActual);
            listaItemCrear = getItemFacade().itemsCrear((short)0,(short)1);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto eliminado", "El producto fue eliminado de la lista de productos pendientes por crear");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
        }
    }
    public void deletePortalWeb(){
        try {
            itemFacade.remove(itemActual);
            listaItemCrear = getItemFacade().itemsCrearPortalWeb((short)0,(short)1);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto eliminado", "El producto fue eliminado de la lista de productos pendientes por crear");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception e) {
        }
    }
    //Insert API MBA
    public String cargarDatosListaMBA() throws SQLException, ClassNotFoundException{
        consecutivo = 0;
        for(T003ItemsMba item: listaItemCrear){
            if(item.getEstado() == 1){
                item.setIdItem("INV"+String.format("%08d", consultarUltimaReferencia4d()+consecutivo));
                item.setEstado((short)2);
                item.setIdUsuario(usuarioActual);
                getItemFacade().edit(item);
                consecutivo ++;
                insertarDatosAPI(item);
                listaItemCrear = getItemFacade().itemsCrear((short)0,(short)1 );
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productos enviados", "Productos enviados correctamente");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "crearItem";
    }
    public void insertarDatosAPI(T003ItemsMba item) throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String texto2x=""+item.getIdItem()+"|"+item.getDescItem()+"||"+item.getUm()+"|"+item.getGrupo()+"|"+item.getSubGrupo()+"|1|P|"+item.getCodCosto()+"|"+item.getCodIncome()+"|"+item.getCodInventario()+"|999999999999||0|0|0|||"+item.getCosto()+"||||||||||0|"+item.getPrecio()+"||||||||||||0|||||"+item.getDescripcionCompleta1()+"|"+item.getDescripcionCompleta2()+"||||IBN|0|||||0|00/00/0000|||0|0|0||0|0|0|||"+item.getCodBarra()+"||||0||||||||||||0||||0|||||"+item.getTipoOrigen()+"|1|1|0|0|0|0|0|||0|0|"+item.getGrupoPrenda()+"|"+item.getExt1()+"|"+item.getExt2()+"|"+item.getExt3()+"|"+item.getExt4()+"|"+item.getExt5()+"||0|0|||"+formatoFecha.format(item.getFechaCreacion())+"|||||0|0|"+item.getCodMarca()+"|||0|||0";
        String query = "INSERT INTO SIST_API(CODE_s, CORP_s, GROUP_CATEGORY_s, INTEGER_1,LONGINT_1 ,TEXTO1_10,TEXTO1_24,TEXTO2_X, NumDoc_s,IDConsulta_s,Local_origen, Local_destino,ORIGIN, Opcion_i) VALUES ('16','EK001','API',1,1,'1','172.16.162.152','"+texto2x+"','9','RP1-EK001','PRI', 'PRI','PRI',0)";
        System.out.println("Insert: "+query);
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
    //logCreados
    public String prepareLogCreados(){
        listaItem = itemFacade.itemsCrear((short)2);
        return "log";
    }
    
    //items para crear portal web
     public String prepareCreateItemPedidoWeb() throws SQLException, ClassNotFoundException{
        recargarParametros();
        recargarListaPortalWeb();
        recargarExtensiones();
        consultarExt();
        consecutivo = 0;
        itemActual = new T003ItemsMba();
        itemDuplicar = new T003ItemsMba();
        descCompletaItem = "";
        consultarItemsPortalWeb();
        //recargarCamposExt();
        return "crearItemPortalWeb";
    }
    public void consultarItemsPortalWeb() throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT descripcion_item,descripcion_corta, ext1, ext2, ext3, ext4, ext5, grupo,t003_pedidos.id_pedido,id_item_pedido, cant_pedida, porcentaje_descuento,t001_usuario.nombre FROM bd_portalweb_ecuador.t004_item_pedido inner join bd_portalweb_ecuador.t003_pedidos on t003_pedidos.id_pedido = t004_item_pedido.id_pedido inner join bd_portalweb_ecuador.t001_usuario on t003_pedidos.id_usuario_creacion = t001_usuario.id_usuario  where t004_item_pedido.estado = 0";
        listaItemPedidoWeb= new ArrayList<>();
        List<T003ItemsMba> listaItemComparaDuplicados  = getItemFacade().itemsCrearPortalWeb((short)0,(short)1);
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    boolean itemEncontrado = false;
                    itemActual = new T003ItemsMba();
                    itemActual.setDescItem(rec.getString("descripcion_corta"));
                    itemActual.setExt1(rec.getString("ext1"));
                    itemActual.setExt2(rec.getString("ext2"));
                    itemActual.setExt3(rec.getString("ext3"));
                    itemActual.setExt4(rec.getString("ext4"));
                    itemActual.setExt5(rec.getString("ext5"));
                    itemActual.setDescripcionCompleta1(rec.getString("descripcion_item"));
                    itemActual.setGrupo(rec.getString("grupo"));
                    itemActual.setIdPedidoWeb(rec.getInt("id_pedido"));
                    itemActual.setIdItemPedidoWeb(rec.getInt("id_item_pedido"));
                    itemActual.setCantPedida(rec.getBigDecimal("cant_pedida"));
                    itemActual.setPorcentajeDescuento(rec.getBigDecimal("porcentaje_descuento"));
                    itemActual.setUsuarioSolicitud(rec.getString("nombre"));
                    itemActual.setEstado((short)0);
                    if(!listaItemComparaDuplicados.isEmpty()){
                        for(T003ItemsMba item: listaItemComparaDuplicados){
                            if(rec.getInt("id_item_pedido")==item.getIdItemPedidoWeb()){
                                itemEncontrado = true;
                                break;
                            }
                        }
                        if(itemEncontrado==false){
                            listaItemPedidoWeb.add(itemActual);
                        }
                    }else{
                        listaItemPedidoWeb.add(itemActual);
                    }
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
    
    public void consultarParametrosPorGrupo() throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT descripcion_item,descripcion_corta, ext1, ext2, ext3, ext4, ext5, grupo, id_pedido, id_item_pedido FROM bd_portalweb_ecuador.t004_item_pedido where estado = 0";
        listaItem = new ArrayList<>();
        List<T003ItemsMba> listaItemComparaDuplicados  = getItemFacade().itemsCrear((short)0,(short)1);
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    boolean itemEncontrado = false;
                    itemActual = new T003ItemsMba();
                    itemActual.setDescItem(rec.getString("descripcion_corta"));
                    itemActual.setExt1(rec.getString("ext1"));
                    itemActual.setExt2(rec.getString("ext2"));
                    itemActual.setExt3(rec.getString("ext3"));
                    itemActual.setExt4(rec.getString("ext4"));
                    itemActual.setExt5(rec.getString("ext5"));
                    itemActual.setDescripcionCompleta1(rec.getString("descripcion_item"));
                    itemActual.setIdPedidoWeb(rec.getInt("id_pedido"));
                    itemActual.setIdItemPedidoWeb(rec.getInt("id_item_pedido"));
                    itemActual.setEstado((short)0);
                    if(!listaItemComparaDuplicados.isEmpty()){
                        for(T003ItemsMba item: listaItemComparaDuplicados){
                            if(itemActual.getDescripcionCompleta1().equals(item.getDescripcionCompleta1())){
                                itemEncontrado = true;
                                break;
                            }
                        }
                        if(itemEncontrado==false){
                            listaItem.add(itemActual);
                        }
                    }else{
                        listaItem.add(itemActual);
                    }
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
    
    public void completarDatosItemPedidosWeb() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        String query = "SELECT INVT_Ficha_Principal.UM as um,  INVT_Ficha_Principal.SUB_GROUP_CODE as subGrupo, INVT_Ficha_Principal.[GL COST CODE] as codCosto,  INVT_Ficha_Principal.[GL INCOME CODE] as codIncome, INVT_Ficha_Principal.[GL INVEN CODE] as codInventario, INVT_Ficha_Principal.Tipo_Origen as tipoOrigen, INVT_Ficha_Principal.GrupoPrenda as grupoPrenda, INVT_Ficha_Principal.Codigo_Marca as codMarca FROM INVT_Ficha_Principal  where PRODUCT_NAME = '"+itemDuplicar.getDescItem().trim()+"' LIMIT 1;";
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    itemDuplicar.setUm(rec.getString("um"));
                    itemDuplicar.setSubGrupo(rec.getString("subGrupo"));
                    itemDuplicar.setCodCosto(rec.getString("codCosto"));
                    itemDuplicar.setCodIncome(rec.getString("codIncome"));
                    itemDuplicar.setCodInventario(rec.getString("codInventario"));
                    itemDuplicar.setTipoOrigen(rec.getString("tipoOrigen"));
                    itemDuplicar.setGrupoPrenda(rec.getString("grupoPrenda"));
                    itemDuplicar.setCodMarca(rec.getString("codMarca"));
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
    
     public String cargarDatosListaPedidosWebMBA() throws SQLException, ClassNotFoundException, FileNotFoundException{
        consecutivo = 0;
        for(T003ItemsMba item: listaItemCrear){
            if(item.getEstado() == 1){
                item.setIdItem("INV"+String.format("%08d", consultarUltimaReferencia4d()+consecutivo));
                item.setEstado((short)2);
                item.setIdUsuario(usuarioActual);
                getItemFacade().edit(item);
                consecutivo ++;
                actualizarItemPedidoWebMBA(item);
                insertarDatosAPI(item);
                
                ExecutorService executor = Executors.newFixedThreadPool(5);
                executor.submit(() -> {
                    try {
                        sincronizarPedidoWeb();
                    } catch (SQLException ex) {
                        Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                
                listaItemCrear = getItemFacade().itemsCrearPortalWeb((short)0,(short)1 );
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productos enviados", "Productos enviados correctamente");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "crearItemPortalWeb";
        
    }
    
    
    public void actualizarItemPedidoWebMBA(T003ItemsMba item) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        BigDecimal valor = item.getCantPedida().multiply(item.getPrecio());
        BigDecimal descuento = (valor.multiply(item.getPorcentajeDescuento())).divide(new BigDecimal(100));
        int estado;
        if(item.getPorcentajeDescuento().compareTo(consultarTopePorcentajeDescuento())>0){
            estado = 2;
        }else{
            estado =1;
        }
        String query = "UPDATE t004_item_pedido SET descripcion_item = '"+item.getDescripcionCompleta1()+"', cod_producto = '"+item.getIdItem()+"', descripcion_corta = '"+item.getDescItem()+"' , ext1 = '"+item.getExt1()+"',ext2 = '"+item.getExt2()+"',ext3 = '"+item.getExt3()+"',ext4 = '"+item.getExt4()+"',ext5 = '"+item.getExt5()+"', precio = "+item.getPrecio()+", um = '"+item.getUm()+"', estado = "+estado+" , valor = "+valor+" , descuento = "+descuento+" WHERE id_item_pedido ="+item.getIdItemPedidoWeb()+" ;";
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
    
    
    
    public void obtenerPedidoSinItemCreado() throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT id_pedido FROM bd_portalweb_ecuador.t003_pedidos where estado =1;";
        listaPedidosSinItemCreado = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    int pedido = rec.getInt("id_pedido");
                    listaPedidosSinItemCreado.add(pedido);
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
    public void sincronizarPedidoWeb() throws SQLException, ClassNotFoundException, FileNotFoundException{
        obtenerPedidoSinItemCreado();
        List<Integer> pedido = new ArrayList<>();
        for(int item: listaPedidosSinItemCreado){
            if(consultarItemsPedidoWebSincronizar(item)==0){
                pedido.add(item);
            }
        }
        for(int item: pedido){
            if(consultarItemsPedidoWebSincronizarDescuento(item)==0){
                actualizarPedidoWebMBACartera(item);
                sendMailNotificacion("Aprobación de pedidos (Cartera)", "3", consultarCorreoNotificacion("ac"), item);
            }else{
                actualizarPedidoWebMBADescuento(item);
                consultarCorreoNotificacion("ad");
                sendMailNotificacion("Aprobación de pedidos (Descuentos)", "2", consultarCorreoNotificacion("ad"), item);
            }
        }
    }
    
    public int consultarItemsPedidoWebSincronizar(int pedido) throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT id_item_pedido FROM bd_portalweb_ecuador.t004_item_pedido where id_pedido = "+pedido+" and estado = 0";
        int cont = 0;
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                cont++;
                break;
            }
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
        return cont;
    }
    
    public int consultarItemsPedidoWebSincronizarDescuento(int pedido) throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT estado FROM bd_portalweb_ecuador.t004_item_pedido where id_pedido = "+pedido;
        int cont = 0;
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                int estado = rec.getInt("estado");
                if(estado==2){
                    cont++;
                    break;
                }
            }
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
        return cont;
    }
    
    public void actualizarPedidoWebMBACartera(int pedido) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t003_pedidos SET estado = 3 WHERE id_pedido ="+pedido+" ;";
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
    
    public void actualizarPedidoWebMBADescuento(int pedido) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "UPDATE t003_pedidos SET estado = 2 WHERE id_pedido ="+pedido+" ;";
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
    
    //obtener tope porcentaje descuento
    public BigDecimal consultarTopePorcentajeDescuento() throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT valor_numerico FROM t005_configuracion_pedido where tipo = 'descuento'";
        BigDecimal descuento = new BigDecimal(0);
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                descuento = rec.getBigDecimal("valor_numerico");
            }
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
        return descuento;
    }
    //Obtener correo envio
    public String consultarCorreoNotificacion(String tipo) throws SQLException, ClassNotFoundException{  
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_portalweb_ecuador","root","3K4$.2015$#"); 
        Statement stmt = null;
        String query = "SELECT descripcion FROM t005_configuracion_pedido where tipo = '"+tipo+"'";
        String correo = "";
        try {
            stmt = con.createStatement();
            ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                correo = rec.getString("descripcion");
            }
        }catch (SQLException sqle) {
            System.out.println("error "+sqle);
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
        return correo;
    }
    
    //enviar correos
    public void sendMailNotificacion(String asuntoCorreo, String tipoAprobacion, String correo,int pedido) throws FileNotFoundException{
        emailController = new EmailController();
        String cuerpo ="";
        cuerpo = "Se notifica que el pedido número <strong>"+pedido+"</strong> esta pendiente por su aprobación, "
                           +"por favor ingresa al botón que esta abajo, recuerda que debes estar logueado en la plataforma."
                           +"<br></br>" 
                           +"<a style=\"text-decoration: none; padding: 5px;font-weight: bold;font-size: 1em;color: #ffffff;background-color: #1a5493;border-radius: 0px;\" href=\"http://172.16.162.152:8080/portalweb/faces/procesarAprobacionPedido.xhtml?id="+pedido+"&tipo="+tipoAprobacion+"\" target=\"_blank\"  >Ver pedido</a>"
                           +"<br></br>"  
                           +"Nota: Este es un mensaje automático, por favor no responder." 
                           +"<br></br>"  
                           +"Departamento de TI"
                           +"<br></br><br></br>";
        emailController.sendMail(correo+",luisc@ekazipper.com", asuntoCorreo, cuerpo);  
    }
    
    public List<String> completeDescItem(String query) {
        List<String> results = new ArrayList<String>();
        for (String text : listaDescCompletaItem) {
            if (text.contains(query.toUpperCase())) {
                results.add(text);
            }
        }
        return results;
    }
    public void consultarDescCompletaItem() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
        listaDescCompletaItem = new ArrayList<>();
        String query = "select concat(PRODUCT_ID,concat(' - ',DESCRIPTION)) desc_completa from INVT_Ficha_Principal WHERE GROUP_CODE = '"+itemActual.getGrupo()+"' ORDER BY product_name";
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    String descripcion = "";
                    descripcion=rec.getString("desc_completa");
                    listaDescCompletaItem.add(descripcion);
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
    
    public void recargarItemExistente() throws SQLException, ClassNotFoundException{
        descCompletaItem="";
        descItem ="";
        ext1="";
        ext2="";
        ext3="";
        ext4="";
        ext5="";
        consultarDescCompletaItem();
    }
    
    public void consultarItemReferencia() throws SQLException, ClassNotFoundException{  
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:odbc:MBA; CharsEncoding=UTF-8");
        Statement stmt = null;
            String query = "SELECT INVT_Ficha_Principal.PRODUCT_ID as idItem, INVT_Ficha_Principal.PRODUCT_NAME as descItem, INVT_Ficha_Principal.UM as um, INVT_Ficha_Principal.GROUP_CODE as grupo, INVT_Ficha_Principal.SUB_GROUP_CODE as subGrupo, INVT_Ficha_Principal.[GL COST CODE] as codCosto, INVT_Ficha_Principal.[GL INCOME CODE] as codIncome, INVT_Ficha_Principal.[GL INVEN CODE] as codInventario, INVT_Ficha_Principal.ACTUAL_COST as costo, INVT_Ficha_Principal.SELL as precio, INVT_Ficha_Principal.Tipo_Origen as tipoOrigen, INVT_Ficha_Principal.GrupoPrenda as grupoPrenda, INVT_Ficha_Principal.Talla as ext1, INVT_Ficha_Principal.Color as ext2, INVT_Ficha_Principal.Familia as ext3, INVT_Ficha_Principal.Temporada as ext4, INVT_Ficha_Principal.AnioPrenda as ext5, SIST_Lista_7.DESCRIPCION as descExt5, INVT_Ficha_Principal.Codigo_Marca as codMarca FROM INVT_Ficha_Principal INNER JOIN SIST_Lista_7 ON  INVT_Ficha_Principal.ANIOPRENDA = SIST_Lista_7.codigo AND SIST_Lista_7.Grupo_categoria = 'Añote' WHERE PRODUCT_ID= '"+descCompletaItem.substring(0,11)+"' order by PRODUCT_ID;";
        listaItem = new ArrayList<>();
        try {
        stmt = conn.createStatement();
        ResultSet rec = stmt.executeQuery(query);
            while (rec.next()) {
                try {
                    itemActual.setIdItem(rec.getString("idItem"));
                    itemActual.setDescItem(rec.getString("descItem"));
                    itemActual.setUm(rec.getString("um"));
                    itemActual.setGrupo(rec.getString("grupo"));
                    itemActual.setSubGrupo(rec.getString("subGrupo"));
                    itemActual.setCodCosto(rec.getString("codCosto"));
                    itemActual.setCodIncome(rec.getString("codIncome"));
                    itemActual.setCodInventario(rec.getString("codInventario"));
                    itemActual.setCosto(rec.getBigDecimal("costo"));
                    itemActual.setPrecio(rec.getBigDecimal("precio"));
                    itemActual.setTipoOrigen(rec.getString("tipoOrigen"));
                    itemActual.setGrupoPrenda(rec.getString("grupoPrenda"));
                    itemActual.setExt1(rec.getString("ext1"));
                    itemActual.setExt2(rec.getString("ext2"));
                    itemActual.setExt3(rec.getString("ext3"));
                    itemActual.setExt4(rec.getString("ext4"));
                    itemActual.setExt5(rec.getString("ext5"));
                    itemActual.setDescExt5(rec.getString("descExt5"));
                    itemActual.setExt5(itemActual.getExt5()+"-"+itemActual.getDescExt5());
                    itemActual.setCodMarca(rec.getString("codMarca"));
                    if(!itemActual.getExt5().equals("")){
                        itemActual.setDescExt5(itemActual.getExt5().substring(itemActual.getExt5().indexOf('-')+1,itemActual.getExt5().length()));
                        itemActual.setExt5(itemActual.getExt5().substring(0,itemActual.getExt5().indexOf('-')));
                    }
                    itemActual.setDescripcionCompleta1((itemActual.getDescItem()+" "+itemActual.getExt1()+" "+itemActual.getExt2()+" "+itemActual.getExt3()+" "+itemActual.getExt4()+" "+itemActual.getExt5()+" "+itemActual.getDescExt5()).replace("  "," ").replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " "));
                    itemActual.setDescripcionCompleta2((itemActual.getDescItem()+" "+itemActual.getExt1()+" "+itemActual.getExt2()+" "+itemActual.getExt3()+" "+itemActual.getExt4()+" "+itemActual.getExt5()).replace("  "," ").replace("  ", " ").replace("  ", " ").replace("  ", " ").replace("  ", " "));
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
    
    public void createItemExistente(){
        try {
            itemActual.setEstado((short)2);
            itemActual.setIdUsuario(usuarioActual);
            itemActual.setFechaCreacion(new Date());
            getItemFacade().edit(itemActual); 
            actualizarItemPedidoWebMBA(itemActual);
            ExecutorService executor = Executors.newFixedThreadPool(5);
            executor.submit(() -> {
                try {
                    sincronizarPedidoWeb();
                } catch (SQLException ex) {
                    Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CrearItemMbaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            listaItemCrear = getItemFacade().itemsCrearPortalWeb((short)0,(short)1 );
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        
    }
}
