/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lccabalr
 */
@Entity
@Table(name = "t004_reporte_saldo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T004ReporteSaldo.findAll", query = "SELECT t FROM T004ReporteSaldo t ORDER BY t.nombreCliente, t.contratoId , t.descItem, t.ext1, t.ext2, t.ext3, t.ext4, t.descExt5 ASC")
    ,@NamedQuery(name = "T004ReporteSaldo.findAllConsolidado", query = "SELECT t FROM T004ReporteSaldo t ORDER BY t.nombreVendedor, t.contratoId , t.descItem, t.ext1, t.ext2, t.ext3, t.ext4, t.descExt5 ASC")
    , @NamedQuery(name = "T004ReporteSaldo.findById", query = "SELECT t FROM T004ReporteSaldo t WHERE t.id = :id")
    , @NamedQuery(name = "T004ReporteSaldo.findBySalesman", query = "SELECT t FROM T004ReporteSaldo t WHERE t.salesman = :salesman")
    , @NamedQuery(name = "T004ReporteSaldo.findByContratoIdCorp", query = "SELECT t FROM T004ReporteSaldo t WHERE t.contratoIdCorp = :contratoIdCorp")
    , @NamedQuery(name = "T004ReporteSaldo.findByContratoId", query = "SELECT t FROM T004ReporteSaldo t WHERE t.contratoId = :contratoId")
    , @NamedQuery(name = "T004ReporteSaldo.findByOrdenCompra", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ordenCompra = :ordenCompra")
    , @NamedQuery(name = "T004ReporteSaldo.findByFechaPedido", query = "SELECT t FROM T004ReporteSaldo t WHERE t.fechaPedido = :fechaPedido")
    , @NamedQuery(name = "T004ReporteSaldo.findByClientId", query = "SELECT t FROM T004ReporteSaldo t WHERE t.clientId = :clientId")
    , @NamedQuery(name = "T004ReporteSaldo.findByNombreCliente", query = "SELECT t FROM T004ReporteSaldo t WHERE t.nombreCliente = :nombreCliente")
    , @NamedQuery(name = "T004ReporteSaldo.findByIdItem", query = "SELECT t FROM T004ReporteSaldo t WHERE t.idItem = :idItem")
    , @NamedQuery(name = "T004ReporteSaldo.findByDescItem", query = "SELECT t FROM T004ReporteSaldo t WHERE t.descItem = :descItem")
    , @NamedQuery(name = "T004ReporteSaldo.findByDescCompleta", query = "SELECT t FROM T004ReporteSaldo t WHERE t.descCompleta = :descCompleta")
    , @NamedQuery(name = "T004ReporteSaldo.findByExt1", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ext1 = :ext1")
    , @NamedQuery(name = "T004ReporteSaldo.findByExt2", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ext2 = :ext2")
    , @NamedQuery(name = "T004ReporteSaldo.findByExt3", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ext3 = :ext3")
    , @NamedQuery(name = "T004ReporteSaldo.findByExt4", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ext4 = :ext4")
    , @NamedQuery(name = "T004ReporteSaldo.findByExt5", query = "SELECT t FROM T004ReporteSaldo t WHERE t.ext5 = :ext5")
    , @NamedQuery(name = "T004ReporteSaldo.findByDescExt5", query = "SELECT t FROM T004ReporteSaldo t WHERE t.descExt5 = :descExt5")
    , @NamedQuery(name = "T004ReporteSaldo.findByQuantity", query = "SELECT t FROM T004ReporteSaldo t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "T004ReporteSaldo.findByBackordernostock", query = "SELECT t FROM T004ReporteSaldo t WHERE t.backordernostock = :backordernostock")
    , @NamedQuery(name = "T004ReporteSaldo.findByDespachados", query = "SELECT t FROM T004ReporteSaldo t WHERE t.despachados = :despachados")
    , @NamedQuery(name = "T004ReporteSaldo.findByPorDespachar", query = "SELECT t FROM T004ReporteSaldo t WHERE t.porDespachar = :porDespachar")
    , @NamedQuery(name = "T004ReporteSaldo.findByPrecioFinalConDescuento", query = "SELECT t FROM T004ReporteSaldo t WHERE t.precioFinalConDescuento = :precioFinalConDescuento")
    , @NamedQuery(name = "T004ReporteSaldo.findByNombreVendedor", query = "SELECT t FROM T004ReporteSaldo t WHERE t.nombreVendedor = :nombreVendedor")
    , @NamedQuery(name = "T004ReporteSaldo.findByCorreoVendedor", query = "SELECT t FROM T004ReporteSaldo t WHERE t.correoVendedor = :correoVendedor")
    , @NamedQuery(name = "T004ReporteSaldo.findByFechaComprom", query = "SELECT t FROM T004ReporteSaldo t WHERE t.fechaComprom = :fechaComprom")})
public class T004ReporteSaldo implements Serializable {

    @Size(max = 105)
    @Column(name = "cod_barra_item")
    private String codBarraItem;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "salesman")
    private String salesman;
    @Size(max = 100)
    @Column(name = "contrato_id_corp")
    private String contratoIdCorp;
    @Size(max = 100)
    @Column(name = "contrato_id")
    private String contratoId;
    @Size(max = 100)
    @Column(name = "orden_compra")
    private String ordenCompra;
    @Size(max = 45)
    @Column(name = "fecha_pedido")
    private String fechaPedido;
    @Size(max = 100)
    @Column(name = "client_id")
    private String clientId;
    @Size(max = 100)
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Size(max = 100)
    @Column(name = "id_item")
    private String idItem;
    @Size(max = 100)
    @Column(name = "desc_item")
    private String descItem;
    @Size(max = 200)
    @Column(name = "desc_completa")
    private String descCompleta;
    @Size(max = 44)
    @Column(name = "ext1")
    private String ext1;
    @Size(max = 45)
    @Column(name = "ext2")
    private String ext2;
    @Size(max = 45)
    @Column(name = "ext3")
    private String ext3;
    @Size(max = 45)
    @Column(name = "ext4")
    private String ext4;
    @Size(max = 45)
    @Column(name = "ext5")
    private String ext5;
    @Size(max = 100)
    @Column(name = "desc_ext5")
    private String descExt5;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "backordernostock")
    private Integer backordernostock;
    @Column(name = "despachados")
    private Integer despachados;
    @Column(name = "por_despachar")
    private Integer porDespachar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_final_con_descuento")
    private BigDecimal precioFinalConDescuento;
    @Size(max = 100)
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;
    @Size(max = 100)
    @Column(name = "correo_vendedor")
    private String correoVendedor;
    @Size(max = 45)
    @Column(name = "fecha_comprom")
    private String fechaComprom;

    public T004ReporteSaldo() {
    }

    public T004ReporteSaldo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getContratoIdCorp() {
        return contratoIdCorp;
    }

    public void setContratoIdCorp(String contratoIdCorp) {
        this.contratoIdCorp = contratoIdCorp;
    }

    public String getContratoId() {
        return contratoId;
    }

    public void setContratoId(String contratoId) {
        this.contratoId = contratoId;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getDescCompleta() {
        return descCompleta;
    }

    public void setDescCompleta(String descCompleta) {
        this.descCompleta = descCompleta;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBackordernostock() {
        return backordernostock;
    }

    public void setBackordernostock(Integer backordernostock) {
        this.backordernostock = backordernostock;
    }

    public Integer getDespachados() {
        return despachados;
    }

    public void setDespachados(Integer despachados) {
        this.despachados = despachados;
    }

    public Integer getPorDespachar() {
        return porDespachar;
    }

    public void setPorDespachar(Integer porDespachar) {
        this.porDespachar = porDespachar;
    }

    public BigDecimal getPrecioFinalConDescuento() {
        return precioFinalConDescuento;
    }

    public void setPrecioFinalConDescuento(BigDecimal precioFinalConDescuento) {
        this.precioFinalConDescuento = precioFinalConDescuento;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }
    
    public String getNombreVendedorReporte() {
        try {
            boolean result = nombreVendedor.equals("");
        } catch (NullPointerException ex) {
            nombreVendedor="NO TIENE";
        }
        return nombreVendedor.trim();
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getCorreoVendedor() {
        return correoVendedor;
    }

    public void setCorreoVendedor(String correoVendedor) {
        this.correoVendedor = correoVendedor;
    }

    public String getFechaComprom() {
        return fechaComprom;
    }

    public void setFechaComprom(String fechaComprom) {
        this.fechaComprom = fechaComprom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T004ReporteSaldo)) {
            return false;
        }
        T004ReporteSaldo other = (T004ReporteSaldo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eurekaintranet.entities.T004ReporteSaldo[ id=" + id + " ]";
    }

    public String getCodBarraItem() {
        return codBarraItem;
    }

    public void setCodBarraItem(String codBarraItem) {
        this.codBarraItem = codBarraItem;
    }

}
