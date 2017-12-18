/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Guz
 */
@Embeddable
public class PlaasisPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PLANTILLA")
    private int idPlantilla;
    @Basic(optional = false)
    @Column(name = "CEDCAPAC")
    private int cedcapac;

    public PlaasisPK() {
    }

    public PlaasisPK(int idPlantilla, int cedcapac) {
        this.idPlantilla = idPlantilla;
        this.cedcapac = cedcapac;
    }

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public int getCedcapac() {
        return cedcapac;
    }

    public void setCedcapac(int cedcapac) {
        this.cedcapac = cedcapac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPlantilla;
        hash += (int) cedcapac;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaasisPK)) {
            return false;
        }
        PlaasisPK other = (PlaasisPK) object;
        if (this.idPlantilla != other.idPlantilla) {
            return false;
        }
        if (this.cedcapac != other.cedcapac) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.PlaasisPK[idPlantilla=" + idPlantilla + ", cedcapac=" + cedcapac + "]";
    }

}
