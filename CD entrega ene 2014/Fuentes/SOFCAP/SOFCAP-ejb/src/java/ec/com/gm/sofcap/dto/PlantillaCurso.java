/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PLANTILLA_CURSO")
@NamedQueries({
    @NamedQuery(name = "PlantillaCurso.findAll", query = "SELECT p FROM PlantillaCurso p"),
    @NamedQuery(name = "PlantillaCurso.findByIdPlantilla", query = "SELECT p FROM PlantillaCurso p WHERE p.idPlantilla = :idPlantilla"),
    @NamedQuery(name = "PlantillaCurso.findByCodtipcap", query = "SELECT p FROM PlantillaCurso p WHERE p.codtipcap = :codtipcap"),
    @NamedQuery(name = "PlantillaCurso.findByCodtema", query = "SELECT p FROM PlantillaCurso p WHERE p.codtema = :codtema"),
    @NamedQuery(name = "PlantillaCurso.findByObjetivo", query = "SELECT p FROM PlantillaCurso p WHERE p.objetivo = :objetivo"),
    @NamedQuery(name = "PlantillaCurso.findByFechaInicio", query = "SELECT p FROM PlantillaCurso p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PlantillaCurso.findByFechaFin", query = "SELECT p FROM PlantillaCurso p WHERE p.fechaFin = :fechaFin")})
public class PlantillaCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ID_PLANTILLA")
    private Integer idPlantilla;
    @Column(name = "CODTIPCAP",nullable=true,insertable=true)
    private Integer codtipcap;
    @Column(name = "CODTEMA",nullable=false,insertable=true)
    private Integer codtema;
    @Column(name = "OBJETIVO")
    private String objetivo;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(mappedBy = "plantillaCurso")
    private Collection<Curso> cursoCollection;
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "plantillaCurso" )
    private Collection<Plains> plainsCollection;
    @JoinColumn(name = "CODTEMA", referencedColumnName = "CODTEMA",insertable=false,updatable=false)
    @ManyToOne
    private Tema tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantillaCurso")
    private Collection<Respuestascapacitado> respuestascapacitadoCollection;
    @JoinColumn(name = "CODTIPCAP", referencedColumnName = "CODTIPCAP",insertable=false,updatable=false)
    @ManyToOne
    private Tipodecapacitacion tipodecapacitacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantillaCurso") 
    private Collection<Plaasis> plaasisCollection;

    public Collection<Plaasis> getPlaasisCollection() {
        return plaasisCollection;
    }

    public void setPlaasisCollection(Collection<Plaasis> plaasisCollection) {
        this.plaasisCollection = plaasisCollection;
    }

    public PlantillaCurso() {
    }
    public Tipodecapacitacion getTipodecapacitacion() {
        return tipodecapacitacion;
    }

    public void setTipodecapacitacion(Tipodecapacitacion tipodecapacitacion) {
        this.tipodecapacitacion = tipodecapacitacion;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Collection<Respuestascapacitado> getRespuestascapacitadoCollection() {
        return respuestascapacitadoCollection;
    }

    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }



    public void setRespuestascapacitadoCollection(Collection<Respuestascapacitado> respuestascapacitadoCollection) {
        this.respuestascapacitadoCollection = respuestascapacitadoCollection;
    }

    public PlantillaCurso(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public PlantillaCurso(Integer idPlantilla, int codtipcap, int codtema) {
        this.idPlantilla = idPlantilla;
        this.codtipcap = codtipcap;
        this.codtema = codtema;
    }

    public Integer getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public Integer getCodtipcap() {
        return codtipcap;
    }

    public void setCodtipcap(Integer codtipcap) {
        this.codtipcap = codtipcap;
    }

    public Integer getCodtema() {
        return codtema;
    }

    public void setCodtema(Integer codtema) {
        this.codtema = codtema;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Collection<Plains> getPlainsCollection() {
        return plainsCollection;
    }

    public void setPlainsCollection(Collection<Plains> plainsCollection) {
        this.plainsCollection = plainsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantilla != null ? idPlantilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantillaCurso)) {
            return false;
        }
        PlantillaCurso other = (PlantillaCurso) object;
        if ((this.idPlantilla == null && other.idPlantilla != null) || (this.idPlantilla != null && !this.idPlantilla.equals(other.idPlantilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.PlantillaCurso[idPlantilla=" + idPlantilla + "]";
    }

}