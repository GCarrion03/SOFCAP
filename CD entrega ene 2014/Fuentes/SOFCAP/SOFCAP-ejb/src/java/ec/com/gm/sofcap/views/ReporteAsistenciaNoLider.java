/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.views;

import ec.com.gm.sofcap.dto.Plaasis;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GUZ
 */
@Entity
@Table(name = "reporteAsistenciaNoLider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteAsistenciaNoLider.findAll", query = "SELECT r FROM ReporteAsistenciaNoLider r"),
    @NamedQuery(name = "ReporteAsistenciaNoLider.findByIdPlantilla", query = "SELECT r FROM ReporteAsistenciaNoLider r WHERE r.idPlantilla = :idPlantilla"),
    @NamedQuery(name = "ReporteAsistenciaNoLider.findByNomtema", query = "SELECT r FROM ReporteAsistenciaNoLider r WHERE r.nomtema = :nomtema"),
    @NamedQuery(name = "ReporteAsistenciaNoLider.findByCeco", query = "SELECT r FROM ReporteAsistenciaNoLider r WHERE r.ceco = :ceco"),
    @NamedQuery(name = "ReporteAsistenciaNoLider.findByPlanificados", query = "SELECT r FROM ReporteAsistenciaNoLider r WHERE r.planificados = :planificados"),
    @NamedQuery(name = "ReporteAsistenciaNoLider.findByAsistentes", query = "SELECT r FROM ReporteAsistenciaNoLider r WHERE r.asistentes = :asistentes")})
public class ReporteAsistenciaNoLider implements Serializable {
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_plantilla")
    @Id
    private int idPlantilla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomtema")
    private String nomtema;
    @Size(max = 25)
    @Column(name = "ceco")
    @Id
    private String ceco; 
    @Column(name = "planificados")
    private Integer planificados;
    @Column(name = "turno")
    @Id
    private Integer turno;
    @Column(name = "codtema")
    private Integer codtema;
    @Column(name = "asistentes")
    private Integer asistentes;
    @Transient
    private Integer[] colCeco;
    @Transient
    private Integer[] colTurno;
    @Transient
    private Integer[] colTema;
    @Transient
    private Boolean selected;
    @Transient
    Collection<Plaasis> asistentesPlanificacionCol;
    
    
    public ReporteAsistenciaNoLider() {
        
    }

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public Integer getCodtema() {
        return codtema;
    }

    public void setCodtema(Integer codtema) {
        this.codtema = codtema;
    }

    public Collection<Plaasis> getAsistentesPlanificacionCol() {
        return asistentesPlanificacionCol;
    }

    public void setAsistentesPlanificacionCol(Collection<Plaasis> asistentesPlanificacionCol) {
        this.asistentesPlanificacionCol = asistentesPlanificacionCol;
    }

    public String getNomtema() {
        return nomtema;
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

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public void setNomtema(String nomtema) {
        this.nomtema = nomtema;
    }

    public String getCeco() {
        return ceco;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    


    public Integer getPlanificados() {
        return planificados;
    }

    public void setPlanificados(Integer planificados) {
        this.planificados = planificados;
    }

    public Integer getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Integer asistentes) {
        this.asistentes = asistentes;
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
    
}
