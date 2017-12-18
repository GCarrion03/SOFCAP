/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.views;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GUZ
 */
@Entity
@Table(name = "reporteCIndividual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteCIndividual.findAll", query = "SELECT r FROM ReporteCIndividual r"),
    @NamedQuery(name = "ReporteCIndividual.findByCodgm", query = "SELECT r FROM ReporteCIndividual r WHERE r.codgm = :codgm"),
    @NamedQuery(name = "ReporteCIndividual.findByNomtema", query = "SELECT r FROM ReporteCIndividual r WHERE r.nomtema = :nomtema"),
    @NamedQuery(name = "ReporteCIndividual.findByConteo", query = "SELECT r FROM ReporteCIndividual r WHERE r.conteo = :conteo"),
    @NamedQuery(name = "ReporteCIndividual.findByPromedio", query = "SELECT r FROM ReporteCIndividual r WHERE r.promedio = :promedio")})
public class ReporteCIndividual implements Serializable {
    @Size(max = 50)
    @Column(name = "nomcapac")
    private String nomcapac;
    @Size(max = 1024)
    @Column(name = "apecapac")
    private String apecapac;
    @Column(name = "turno")
    private Integer turno;
    @Basic(optional = false)
    @NotNull
    @Id
    @Column(name = "codtema")
    private Integer codtema;
    @Size(max = 25)
    @Column(name = "ceco")
    private Integer ceco;
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "codgm")
    @Id
    private String codgm;
    @Size(max = 20)
    @Column(name = "cedcapac")
    private Integer cedcapac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomtema")
    private String nomtema;
    @Column(name = "conteo")
    private Integer conteo;
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "idlider")
    private Integer idLider;
    @Size(min = 1, max = 200)
    @Column(name = "cargo")
    private String cargo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "promedio")
    private BigDecimal promedio;
    
    @Transient
    private Integer[] colCeco;
    @Transient
    private Integer[] colTurno;
    @Transient
    private Integer[] colTema;
    @Transient
    private Integer[] colIdLider;
    
    @Transient
    private Boolean seleccionado=Boolean.FALSE;
    @Transient
    private Boolean permitirSeleccion=Boolean.TRUE;
    
    @Transient
    private Boolean esAnteriormentePlanificado=Boolean.FALSE;
    
    public ReporteCIndividual() {
    }

    public String getCodgm() {
        return codgm;
    }

    public void setCodgm(String codgm) {
        this.codgm = codgm;
    }

    public String getNomtema() {
        return nomtema;
    }

    public Boolean getEsAnteriormentePlanificado() {
        return esAnteriormentePlanificado;
    }

    public void setEsAnteriormentePlanificado(Boolean esAnteriormentePlanificado) {
        this.esAnteriormentePlanificado = esAnteriormentePlanificado;
    }


    public void setNomtema(String nomtema) {
        this.nomtema = nomtema;
    }

    public Integer getCedcapac() {
        return cedcapac;
    }

    public void setCedcapac(Integer cedcapac) {
        this.cedcapac = cedcapac;
    }

   

    public Integer getConteo() {
        return conteo;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getIdLider() {
        return idLider;
    }

    public void setIdLider(Integer idLider) {
        this.idLider = idLider;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void setConteo(Integer conteo) {
        this.conteo = conteo;
    }

    public Integer[] getColCeco() {
        return colCeco;
    }

    public void setColCeco(Integer[] colCeco) {
        this.colCeco = colCeco;
    }

    public Integer[] getColTema() {
        return colTema;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public void setColTema(Integer[] colTema) {
        this.colTema = colTema;
    }

    public Integer[] getColTurno() {
        return colTurno;
    }

    public void setColTurno(Integer[] colTurno) {
        this.colTurno = colTurno;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public String getNomcapac() {
        return nomcapac;
    }

    public void setNomcapac(String nomcapac) {
        this.nomcapac = nomcapac;
    }

    public String getApecapac() {
        return apecapac;
    }

    public void setApecapac(String apecapac) {
        this.apecapac = apecapac;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Integer getCodtema() { 
        return codtema;
    }

    public void setCodtema(Integer codtema) {
        this.codtema = codtema;
    }

    public Integer getCeco() {
        return ceco;
    }

    public void setCeco(Integer ceco) {
        this.ceco = ceco;
    }

    public Integer[] getColIdLider() {
        return colIdLider;
    }

    public Boolean getPermitirSeleccion() {
        return permitirSeleccion;
    }

    public void setPermitirSeleccion(Boolean permitirSeleccion) {
        this.permitirSeleccion = permitirSeleccion;
    }

    public void setColIdLider(Integer[] colIdLider) {
        this.colIdLider = colIdLider;
    }
    
}
