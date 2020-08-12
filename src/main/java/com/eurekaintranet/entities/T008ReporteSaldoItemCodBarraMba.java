/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eurekaintranet.entities;

import java.io.Serializable;
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
@Table(name = "t008_reporte_saldo_item_cod_barra_mba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T008ReporteSaldoItemCodBarraMba.findAll", query = "SELECT t FROM T008ReporteSaldoItemCodBarraMba t")
    , @NamedQuery(name = "T008ReporteSaldoItemCodBarraMba.findByIdReporteSaldoItemCodBarraMba", query = "SELECT t FROM T008ReporteSaldoItemCodBarraMba t WHERE t.idReporteSaldoItemCodBarraMba = :idReporteSaldoItemCodBarraMba")
    , @NamedQuery(name = "T008ReporteSaldoItemCodBarraMba.findByCodItemMba", query = "SELECT t FROM T008ReporteSaldoItemCodBarraMba t WHERE t.codItemMba = :codItemMba")
    , @NamedQuery(name = "T008ReporteSaldoItemCodBarraMba.findByCodBarraMba", query = "SELECT t FROM T008ReporteSaldoItemCodBarraMba t WHERE t.codBarraMba = :codBarraMba")})
public class T008ReporteSaldoItemCodBarraMba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reporte_saldo_item_cod_barra_mba")
    private Integer idReporteSaldoItemCodBarraMba;
    @Size(max = 45)
    @Column(name = "cod_item_mba")
    private String codItemMba;
    @Size(max = 45)
    @Column(name = "cod_barra_mba")
    private String codBarraMba;

    public T008ReporteSaldoItemCodBarraMba() {
    }

    public T008ReporteSaldoItemCodBarraMba(Integer idReporteSaldoItemCodBarraMba) {
        this.idReporteSaldoItemCodBarraMba = idReporteSaldoItemCodBarraMba;
    }

    public Integer getIdReporteSaldoItemCodBarraMba() {
        return idReporteSaldoItemCodBarraMba;
    }

    public void setIdReporteSaldoItemCodBarraMba(Integer idReporteSaldoItemCodBarraMba) {
        this.idReporteSaldoItemCodBarraMba = idReporteSaldoItemCodBarraMba;
    }

    public String getCodItemMba() {
        return codItemMba;
    }

    public void setCodItemMba(String codItemMba) {
        this.codItemMba = codItemMba;
    }

    public String getCodBarraMba() {
        return codBarraMba;
    }

    public void setCodBarraMba(String codBarraMba) {
        this.codBarraMba = codBarraMba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporteSaldoItemCodBarraMba != null ? idReporteSaldoItemCodBarraMba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T008ReporteSaldoItemCodBarraMba)) {
            return false;
        }
        T008ReporteSaldoItemCodBarraMba other = (T008ReporteSaldoItemCodBarraMba) object;
        if ((this.idReporteSaldoItemCodBarraMba == null && other.idReporteSaldoItemCodBarraMba != null) || (this.idReporteSaldoItemCodBarraMba != null && !this.idReporteSaldoItemCodBarraMba.equals(other.idReporteSaldoItemCodBarraMba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eurekaintranet.entities.T008ReporteSaldoItemCodBarraMba[ idReporteSaldoItemCodBarraMba=" + idReporteSaldoItemCodBarraMba + " ]";
    }
    
}
