/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Cacheable(false)
@Entity
@Table(name = "t007_reporte_saldo_fecha_colombia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T007ReporteSaldoFechaColombia.findAll", query = "SELECT t FROM T007ReporteSaldoFechaColombia t")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findById", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.id = :id")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByNroPedidoUnoee", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.nroPedidoUnoee = :nroPedidoUnoee")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByNotas", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.notas = :notas")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByNroPedidoMba", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.nroPedidoMba = :nroPedidoMba")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByCodBarraItem", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.codBarraItem = :codBarraItem")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByFechaEntrega", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "T007ReporteSaldoFechaColombia.findByCantPendiente", query = "SELECT t FROM T007ReporteSaldoFechaColombia t WHERE t.cantPendiente = :cantPendiente")})
public class T007ReporteSaldoFechaColombia implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nro_pedido_unoee")
    private String nroPedidoUnoee;
    @Size(max = 300)
    @Column(name = "notas")
    private String notas;
    @Size(max = 200)
    @Column(name = "nro_pedido_mba")
    private String nroPedidoMba;
    @Size(max = 45)
    @Column(name = "cod_barra_item")
    private String codBarraItem;
    @Size(max = 45)
    @Column(name = "fecha_entrega")
    private String fechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cant_pendiente")
    private BigDecimal cantPendiente;
    @Size(max = 45)
    @Column(name = "cod_item_mba")
    private String codItemMba;

    public T007ReporteSaldoFechaColombia() {
    }

    public T007ReporteSaldoFechaColombia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNroPedidoUnoee() {
        return nroPedidoUnoee;
    }

    public void setNroPedidoUnoee(String nroPedidoUnoee) {
        this.nroPedidoUnoee = nroPedidoUnoee;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getNroPedidoMba() {
        return nroPedidoMba;
    }

    public void setNroPedidoMba(String nroPedidoMba) {
        this.nroPedidoMba = nroPedidoMba;
    }

    public String getCodBarraItem() {
        return codBarraItem;
    }

    public void setCodBarraItem(String codBarraItem) {
        this.codBarraItem = codBarraItem;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getCantPendiente() {
        return cantPendiente;
    }

    public void setCantPendiente(BigDecimal cantPendiente) {
        this.cantPendiente = cantPendiente;
    }

    public String getCodItemMba() {
        return codItemMba;
    }

    public void setCodItemMba(String codItemMba) {
        this.codItemMba = codItemMba;
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
        if (!(object instanceof T007ReporteSaldoFechaColombia)) {
            return false;
        }
        T007ReporteSaldoFechaColombia other = (T007ReporteSaldoFechaColombia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCodBarraItem()+" "+getFechaEntrega()+" "+getNotas()+" "+getNroPedidoMba()+" "+getNroPedidoUnoee()+" "+getCodItemMba();
    }
    
}
