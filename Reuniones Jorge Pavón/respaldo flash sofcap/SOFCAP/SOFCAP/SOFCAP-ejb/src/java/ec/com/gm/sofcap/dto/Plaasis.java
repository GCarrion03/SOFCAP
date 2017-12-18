/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PLAASIS")
@NamedQueries({
    @NamedQuery(name = "Plaasis.findAll", query = "SELECT p FROM Plaasis p"),
    @NamedQuery(name = "Plaasis.findByIdPlantilla", query = "SELECT p FROM Plaasis p WHERE p.plaasisPK.idPlantilla = :idPlantilla"),
    @NamedQuery(name = "Plaasis.findByCedcapac", query = "SELECT p FROM Plaasis p WHERE p.plaasisPK.cedcapac = :cedcapac"),
    @NamedQuery(name = "Plaasis.findByFechareg", query = "SELECT p FROM Plaasis p WHERE p.fechareg = :fechareg")})
public class Plaasis implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlaasisPK plaasisPK;
    @Column(name = "FECHAREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
     @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlantillaCurso plantillaCurso;
    @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Capacitado capacitado;

    @JoinColumns({
        @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", insertable = false, updatable = false),
        @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", insertable = false, updatable = false)})
    @ManyToOne(optional = true,fetch=FetchType.LAZY)
    private Asistencia asistencia;

    public Plaasis() {
    }

    public Capacitado getCapacitado() {
        return capacitado;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public void setCapacitado(Capacitado capacitado) {
        this.capacitado = capacitado;
    }

    public PlantillaCurso getPlantillaCurso() {
        return plantillaCurso;
    }

    public void setPlantillaCurso(PlantillaCurso plantillaCurso) {
        this.plantillaCurso = plantillaCurso;
    }

    public Plaasis(PlaasisPK plaasisPK) {
        this.plaasisPK = plaasisPK;
    }

    public Plaasis(int idPlantilla, int cedcapac) {
        this.plaasisPK = new PlaasisPK(idPlantilla, cedcapac);
    }

    public PlaasisPK getPlaasisPK() {
        return plaasisPK;
    }

    public void setPlaasisPK(PlaasisPK plaasisPK) {
        this.plaasisPK = plaasisPK;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaasisPK != null ? plaasisPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plaasis)) {
            return false;
        }
        Plaasis other = (Plaasis) object;
        if ((this.plaasisPK == null && other.plaasisPK != null) || (this.plaasisPK != null && !this.plaasisPK.equals(other.plaasisPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Plaasis[plaasisPK=" + plaasisPK + "]";
    }

}
