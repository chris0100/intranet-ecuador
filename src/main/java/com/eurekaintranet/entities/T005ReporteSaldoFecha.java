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
 * @author luis carlos cabal
 */
@Cacheable(false)
@Entity
@Table(name = "t005_reporte_saldo_fecha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T005ReporteSaldoFecha.findAll", query = "SELECT t FROM T005ReporteSaldoFecha t")
    , @NamedQuery(name = "T005ReporteSaldoFecha.findById", query = "SELECT t FROM T005ReporteSaldoFecha t WHERE t.id = :id")
    , @NamedQuery(name = "T005ReporteSaldoFecha.findByCodigo", query = "SELECT t FROM T005ReporteSaldoFecha t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "T005ReporteSaldoFecha.findByFecha", query = "SELECT t FROM T005ReporteSaldoFecha t WHERE t.fecha = :fecha")})
public class T005ReporteSaldoFecha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;

    public T005ReporteSaldoFecha() {
    }

    public T005ReporteSaldoFecha(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof T005ReporteSaldoFecha)) {
            return false;
        }
        T005ReporteSaldoFecha other = (T005ReporteSaldoFecha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eurekaintranet.entities.T005ReporteSaldoFecha[ id=" + id + " ]";
    }

}
