/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eurekaintranet.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author luis carlos cabal
 */
@Entity
@Table(name = "t003_items_mba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "T003ItemsMba.findAll", query = "SELECT t FROM T003ItemsMba t")
    , @NamedQuery(name = "T003ItemsMba.findByIdRegistro", query = "SELECT t FROM T003ItemsMba t WHERE t.idRegistro = :idRegistro")
    , @NamedQuery(name = "T003ItemsMba.findByIdItem", query = "SELECT t FROM T003ItemsMba t WHERE t.idItem = :idItem")
    , @NamedQuery(name = "T003ItemsMba.findByDescItem", query = "SELECT t FROM T003ItemsMba t WHERE t.descItem = :descItem")
    , @NamedQuery(name = "T003ItemsMba.findByUm", query = "SELECT t FROM T003ItemsMba t WHERE t.um = :um")
    , @NamedQuery(name = "T003ItemsMba.findByGrupo", query = "SELECT t FROM T003ItemsMba t WHERE t.grupo = :grupo")
    , @NamedQuery(name = "T003ItemsMba.findBySubGrupo", query = "SELECT t FROM T003ItemsMba t WHERE t.subGrupo = :subGrupo")
    , @NamedQuery(name = "T003ItemsMba.findByCodCosto", query = "SELECT t FROM T003ItemsMba t WHERE t.codCosto = :codCosto")
    , @NamedQuery(name = "T003ItemsMba.findByCodIncome", query = "SELECT t FROM T003ItemsMba t WHERE t.codIncome = :codIncome")
    , @NamedQuery(name = "T003ItemsMba.findByCodInventario", query = "SELECT t FROM T003ItemsMba t WHERE t.codInventario = :codInventario")
    , @NamedQuery(name = "T003ItemsMba.findByCosto", query = "SELECT t FROM T003ItemsMba t WHERE t.costo = :costo")
    , @NamedQuery(name = "T003ItemsMba.findByPrecio", query = "SELECT t FROM T003ItemsMba t WHERE t.precio = :precio")
    , @NamedQuery(name = "T003ItemsMba.findByTipoOrigen", query = "SELECT t FROM T003ItemsMba t WHERE t.tipoOrigen = :tipoOrigen")
    , @NamedQuery(name = "T003ItemsMba.findByGrupoPrenda", query = "SELECT t FROM T003ItemsMba t WHERE t.grupoPrenda = :grupoPrenda")
    , @NamedQuery(name = "T003ItemsMba.findByExt1", query = "SELECT t FROM T003ItemsMba t WHERE t.ext1 = :ext1")
    , @NamedQuery(name = "T003ItemsMba.findByExt2", query = "SELECT t FROM T003ItemsMba t WHERE t.ext2 = :ext2")
    , @NamedQuery(name = "T003ItemsMba.findByExt3", query = "SELECT t FROM T003ItemsMba t WHERE t.ext3 = :ext3")
    , @NamedQuery(name = "T003ItemsMba.findByExt4", query = "SELECT t FROM T003ItemsMba t WHERE t.ext4 = :ext4")
    , @NamedQuery(name = "T003ItemsMba.findByExt5", query = "SELECT t FROM T003ItemsMba t WHERE t.ext5 = :ext5")
    , @NamedQuery(name = "T003ItemsMba.findByDescExt5", query = "SELECT t FROM T003ItemsMba t WHERE t.descExt5 = :descExt5")
    , @NamedQuery(name = "T003ItemsMba.findByCodMarca", query = "SELECT t FROM T003ItemsMba t WHERE t.codMarca = :codMarca")
    , @NamedQuery(name = "T003ItemsMba.findByEstado", query = "SELECT t FROM T003ItemsMba t WHERE t.estado = :estado")
    , @NamedQuery(name = "T003ItemsMba.findByEstadoPendienteCrear", query = "SELECT t FROM T003ItemsMba t WHERE (t.estado = :estado1 or t.estado = :estado2) and t.idTipoCreacion = :idTipoCreacion")
    , @NamedQuery(name = "T003ItemsMba.findByFechaCreacion", query = "SELECT t FROM T003ItemsMba t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "T003ItemsMba.findByDescripcionCompleta1", query = "SELECT t FROM T003ItemsMba t WHERE t.descripcionCompleta1 = :descripcionCompleta1")
    , @NamedQuery(name = "T003ItemsMba.findByDescripcionCompleta2", query = "SELECT t FROM T003ItemsMba t WHERE t.descripcionCompleta2 = :descripcionCompleta2")
    , @NamedQuery(name = "T003ItemsMba.findByCodBarra", query = "SELECT t FROM T003ItemsMba t WHERE t.codBarra = :codBarra")})
public class T003ItemsMba implements Serializable {

    @Column(name = "id_tipo_creacion")
    private Integer idTipoCreacion;

    @Size(max = 200)
    @Column(name = "usuario_solicitud")
    private String usuarioSolicitud;

    @Column(name = "cant_pedida")
    private BigDecimal cantPedida;
    @Column(name = "porcentaje_descuento")
    private BigDecimal porcentajeDescuento;
    @Column(name = "id_pedido_web")
    private Integer idPedidoWeb;
    @Column(name = "id_item_pedido_web")
    private Integer idItemPedidoWeb;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro")
    private Integer idRegistro;
    
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "id_item")
    private String idItem;
    @Size(max = 100)
    @Column(name = "desc_item")
    private String descItem;
    @Size(max = 100)
    @Column(name = "um")
    private String um;
    @Size(max = 100)
    @Column(name = "grupo")
    private String grupo;
    @Size(max = 100)
    @Column(name = "sub_grupo")
    private String subGrupo;
    @Size(max = 100)
    @Column(name = "cod_costo")
    private String codCosto;
    @Size(max = 100)
    @Column(name = "cod_income")
    private String codIncome;
    @Size(max = 100)
    @Column(name = "cod_inventario")
    private String codInventario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private BigDecimal costo;
    @Column(name = "precio")
    private BigDecimal precio;
    @Size(max = 100)
    @Column(name = "tipo_origen")
    private String tipoOrigen;
    @Size(max = 100)
    @Column(name = "grupo_prenda")
    private String grupoPrenda;
    @Size(max = 45)
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
    @Size(max = 5)
    @Column(name = "cod_marca")
    private String codMarca;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 300)
    @Column(name = "descripcion_completa_1")
    private String descripcionCompleta1;
    @Size(max = 300)
    @Column(name = "descripcion_completa_2")
    private String descripcionCompleta2;
    @Size(max = 45)
    @Column(name = "cod_barra")
    private String codBarra;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private T001Usuario idUsuario;

    public T003ItemsMba() {
    }

    public T003ItemsMba(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public T003ItemsMba(Integer idRegistro, String idItem) {
        this.idRegistro = idRegistro;
        this.idItem = idItem;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
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

    public String getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(String codMarca) {
        this.codMarca = codMarca;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public T001Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(T001Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T003ItemsMba)) {
            return false;
        }
        T003ItemsMba other = (T003ItemsMba) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eurekaintranet.entities.T003ItemsMba[ idRegistro=" + idRegistro + " ]";
    }

    public Integer getIdPedidoWeb() {
        return idPedidoWeb;
    }

    public void setIdPedidoWeb(Integer idPedidoWeb) {
        this.idPedidoWeb = idPedidoWeb;
    }

    public Integer getIdItemPedidoWeb() {
        return idItemPedidoWeb;
    }

    public void setIdItemPedidoWeb(Integer idItemPedidoWeb) {
        this.idItemPedidoWeb = idItemPedidoWeb;
    }

    public BigDecimal getCantPedida() {
        return cantPedida;
    }

    public void setCantPedida(BigDecimal cantPedida) {
        this.cantPedida = cantPedida;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getUsuarioSolicitud() {
        return usuarioSolicitud;
    }

    public void setUsuarioSolicitud(String usuarioSolicitud) {
        this.usuarioSolicitud = usuarioSolicitud;
    }

    public Integer getIdTipoCreacion() {
        return idTipoCreacion;
    }

    public void setIdTipoCreacion(Integer idTipoCreacion) {
        this.idTipoCreacion = idTipoCreacion;
    }


}
