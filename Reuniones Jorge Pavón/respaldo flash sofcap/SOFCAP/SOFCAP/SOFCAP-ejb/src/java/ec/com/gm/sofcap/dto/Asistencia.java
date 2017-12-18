/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "ASISTENCIA", catalog = "SOFCAP", schema = "dbo")
public class Asistencia implements Serializable {

    @EmbeddedId
    protected AsistenciaPK asistenciaPK;

    private Double resN3;

    @JoinColumns({
        @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", insertable = false, updatable = false),
        @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Plaasis plaasis;

    public Plaasis getPlaasis() {
        return plaasis;
    }

    public void setPlaasis(Plaasis plaasis) {
        this.plaasis = plaasis;
    }



    public Double getResN3() {
        return resN3;
    }

    public void setResN3(Double resN3) {
        this.resN3 = resN3;
    }

    public AsistenciaPK getAsistenciaPK() {
        return asistenciaPK;
    }

    public void setAsistenciaPK(AsistenciaPK asistenciaPK) {
        this.asistenciaPK = asistenciaPK;
    }

}
