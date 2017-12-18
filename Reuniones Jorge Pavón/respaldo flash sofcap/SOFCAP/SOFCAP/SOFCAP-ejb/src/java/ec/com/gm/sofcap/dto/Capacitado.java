/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "CAPACITADO", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Capacitado.findAll", query = "SELECT c FROM Capacitado c"),
    @NamedQuery(name = "Capacitado.findByCedcapac", query = "SELECT c FROM Capacitado c WHERE c.cedcapac = :cedcapac"),
    @NamedQuery(name = "Capacitado.findByNomcapac", query = "SELECT c FROM Capacitado c WHERE c.nomcapac = :nomcapac"),
    @NamedQuery(name = "Capacitado.findByApecapac", query = "SELECT c FROM Capacitado c WHERE c.apecapac = :apecapac"),
    @NamedQuery(name = "Capacitado.findByCeco", query = "SELECT c FROM Capacitado c WHERE c.ceco = :ceco"),
    @NamedQuery(name = "Capacitado.findByTipcon", query = "SELECT c FROM Capacitado c WHERE c.tipcon = :tipcon")})
public class Capacitado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CEDCAPAC", nullable = false)
    private Integer cedcapac;
    @Column(name = "NOMCAPAC", length = 50)
    private String nomcapac;
    @Column (name="CODGM", length=20)
    private String codgm;
    @Column(name = "APECAPAC", length = 1024)
    private String apecapac;
    @Column(name = "CECO", length = 25)
    private String ceco;
    @Column(name = "SECCION", length = 25)
    private String seccion;
    @Column(name = "TURNO")
    private Integer turno;
    
    @Transient
    private Boolean seleccionado=Boolean.FALSE;
    @Transient
    private Double evaluacionN3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capacitado")
    private Collection<Plaasis> plaasisCollection;

    @Column(name = "TIPCON", length = 25)
    private String tipcon;
//    @JoinTable(name = "TURNOCAPACITADO", joinColumns = {
//        @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", nullable = false)}, inverseJoinColumns = {
//        @JoinColumn(name = "CODTURNO", referencedColumnName = "CODTURNO", nullable = false)})
//    @OneToMany
//    private Collection<Turno> turnoCollection;
    @JoinTable(name = "ASISTENCIA", joinColumns = {
    @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", nullable = false)}, inverseJoinColumns = {
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO", nullable = false)})
    @ManyToMany
    private Collection<Curso> cursoCollection;
    @JoinColumn(name = "CODAREA", referencedColumnName = "CODAREA", nullable = false)
    @ManyToOne(optional = false)
    private Areas areas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capacitado")
    private Collection<Respuestascapacitado> respuestasCapacitadosCol;

    public Capacitado() {
    }
    
    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public Collection<Respuestascapacitado> getRespuestasCapacitadosCol() {
        return respuestasCapacitadosCol;
    }

    public void setRespuestasCapacitadosCol(Collection<Respuestascapacitado> respuestasCapacitadosCol) {
        this.respuestasCapacitadosCol = respuestasCapacitadosCol;
    }

    public Double getEvaluacionN3() {
        return evaluacionN3;
    }

    public void setEvaluacionN3(Double evaluacionN3) {
        this.evaluacionN3 = evaluacionN3;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Collection<Plaasis> getPlaasisCollection() {
        return plaasisCollection;
    }

    public void setPlaasisCollection(Collection<Plaasis> plaasisCollection) {
        this.plaasisCollection = plaasisCollection;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCodgm() {
        return codgm;
    }

    public void setCodgm(String codgm) {
        this.codgm = codgm;
    }

    public Capacitado(Integer cedcapac) {
        this.cedcapac = cedcapac;
    }

    public Integer getCedcapac() {
        return cedcapac;
    }

    public void setCedcapac(Integer cedcapac) {
        this.cedcapac = cedcapac;
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

    public String getCeco() {
        return ceco;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    public String getTipcon() {
        return tipcon;
    }

    public void setTipcon(String tipcon) {
        this.tipcon = tipcon;
    }

    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedcapac != null ? cedcapac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitado)) {
            return false;
        }
        Capacitado other = (Capacitado) object;
        if ((this.cedcapac == null && other.cedcapac != null) || (this.cedcapac != null && !this.cedcapac.equals(other.cedcapac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Capacitado[cedcapac=" + cedcapac + "]";
    }

}
