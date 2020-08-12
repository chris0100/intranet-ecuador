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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author luis carlos cabal
 */
@Cacheable(false)
@Entity
@Table(name = "t006_reporte_saldo_vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T006ReporteSaldoVendedor.findAll", query = "SELECT t FROM T006ReporteSaldoVendedor t")
    , @NamedQuery(name = "T006ReporteSaldoVendedor.findById", query = "SELECT t FROM T006ReporteSaldoVendedor t WHERE t.id = :id")
    , @NamedQuery(name = "T006ReporteSaldoVendedor.findByNombre", query = "SELECT t FROM T006ReporteSaldoVendedor t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "T006ReporteSaldoVendedor.findByCorreo", query = "SELECT t FROM T006ReporteSaldoVendedor t WHERE t.correo = :correo")})
public class T006ReporteSaldoVendedor implements Serializable {

    @Size(max = 45)
    @Column(name = "codigo")
    private String codigo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;

    public T006ReporteSaldoVendedor() {
    }

    public T006ReporteSaldoVendedor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        if (!(object instanceof T006ReporteSaldoVendedor)) {
            return false;
        }
        T006ReporteSaldoVendedor other = (T006ReporteSaldoVendedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eurekaintranet.entities.T006ReporteSaldoVendedor[ id=" + id + " ]";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
