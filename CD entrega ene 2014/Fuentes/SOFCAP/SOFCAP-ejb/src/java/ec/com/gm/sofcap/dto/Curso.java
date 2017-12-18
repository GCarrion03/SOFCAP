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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "CURSO")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findByFechaInicio", query = "SELECT c FROM Curso c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Curso.findByFechaFin", query = "SELECT c FROM Curso c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Curso.findByObservaciones", query = "SELECT c FROM Curso c WHERE c.observaciones = :observaciones")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ID_CURSO")
    private Integer idCurso;
    private Double resN1;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    private Integer codTurno;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "CODTURNO", referencedColumnName = "CODTURNO", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Turno turno;
    @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private PlantillaCurso plantillaCurso;
    @JoinColumn(name = "CODINSTR", referencedColumnName = "CODINSTR", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Instructor instructor;
    @JoinColumn(name = "CODCENENT", referencedColumnName = "CODCENENT", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Centroentrenamiento centroentrenamiento;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "curso")
    private Collection<CursosEvaluacion> cursosevaluacionCollection;

//    @JoinTable(name = "CURSOSEVALUACION", joinColumns = {
//    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "CODEVA", referencedColumnName = "CODEVA", nullable = false)})
//    @ManyToMany
//    private Collection<Plantillaevaluacion> plantillaevaluacionCollection;

//    @ManyToMany(mappedBy = "cursoCollection")
//    private Collection<Capacitado> capacitadoCollection;

    @Basic(optional = false)
    @Column(name = "ID_PLANTILLA", nullable = false)
    private Integer id_plantilla;



    @Basic(optional = false)
    @Column(name = "CODINSTR", nullable = false)
    private Integer codinstr;

    @Basic(optional = false)
    @Column(name = "CODCENENT", nullable = false,insertable=true,updatable=true)
    private Integer codcenent;

    public Curso() {
    }

    public Collection<CursosEvaluacion> getCursosevaluacionCollection() {
        return cursosevaluacionCollection;
    }

    public void setCursosevaluacionCollection(Collection<CursosEvaluacion> cursosevaluacionCollection) {
        this.cursosevaluacionCollection = cursosevaluacionCollection;
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(Integer codTurno) {
        this.codTurno = codTurno;
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

    public String getObservaciones() {
        return observaciones;
    }

    public Double getResN1() {
        return resN1;
    }

    public void setResN1(Double resN1) {
        this.resN1 = resN1;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public PlantillaCurso getPlantillaCurso() {
        return plantillaCurso;
    }

    public void setPlantillaCurso(PlantillaCurso plantillaCurso) {
        this.plantillaCurso = plantillaCurso;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Centroentrenamiento getCentroentrenamiento() {
        return centroentrenamiento;
    }

    public void setCentroentrenamiento(Centroentrenamiento centroentrenamiento) {
        this.centroentrenamiento = centroentrenamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Curso[idCurso=" + idCurso + "]";
    }
    
//    public Collection<Capacitado> getCapacitadoCollection() {
//        return capacitadoCollection;
//    }
//
//    public void setCapacitadoCollection(Collection<Capacitado> capacitadoCollection) {
//        this.capacitadoCollection = capacitadoCollection;
//    }

    public Integer getCodcenent() {
        return codcenent;
    }

    public void setCodcenent(Integer codcenent) {
        this.codcenent = codcenent;
    }

    public Integer getCodinstr() {
        return codinstr;
    }

    public void setCodinstr(Integer codinstr) {
        this.codinstr = codinstr;
    }

    public Integer getId_plantilla() {
        return id_plantilla;
    }

    public void setId_plantilla(Integer id_plantilla) {
        this.id_plantilla = id_plantilla;
    }

  
}
