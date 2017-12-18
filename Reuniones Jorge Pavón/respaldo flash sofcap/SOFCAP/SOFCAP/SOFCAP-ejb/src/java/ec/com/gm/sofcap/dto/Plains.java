/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PLAINS")
@NamedQueries({
    @NamedQuery(name = "Plains.findAll", query = "SELECT p FROM Plains p"),
    @NamedQuery(name = "Plains.findByIdPlantilla", query = "SELECT p FROM Plains p WHERE p.plainsPK.idPlantilla = :idPlantilla"),
    @NamedQuery(name = "Plains.findByCodinstr", query = "SELECT p FROM Plains p WHERE p.plainsPK.codinstr = :codinstr")})
public class Plains implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlainsPK plainsPK;
    @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlantillaCurso plantillaCurso;
    @JoinColumn(name = "CODINSTR", referencedColumnName = "CODINSTR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Instructor instructor;
    

    public Plains() {
    }

    public Plains(PlainsPK plainsPK) {
        this.plainsPK = plainsPK;
    }

    public Plains(int idPlantilla, int codinstr) {
        this.plainsPK = new PlainsPK(idPlantilla, codinstr);
    }

    public PlainsPK getPlainsPK() {
        return plainsPK;
    }

    public void setPlainsPK(PlainsPK plainsPK) {
        this.plainsPK = plainsPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plainsPK != null ? plainsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plains)) {
            return false;
        }
        Plains other = (Plains) object;
        if ((this.plainsPK == null && other.plainsPK != null) || (this.plainsPK != null && !this.plainsPK.equals(other.plainsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Plains[plainsPK=" + plainsPK + "]";
    }

}
