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
public class AsistenciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CEDCAPAC")
    private int cedcapac;
    @Basic(optional = false)
    @Column(name = "ID_PLANTILLA")
    private int idPlantilla;

    public AsistenciaPK() {
    }

    public AsistenciaPK(int cedcapac, int idPlantilla) {
        this.cedcapac = cedcapac;
        this.idPlantilla = idPlantilla;
    }

    public int getCedcapac() {
        return cedcapac;
    }

    public void setCedcapac(int cedcapac) {
        this.cedcapac = cedcapac;
    }

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cedcapac;
        hash += (int) idPlantilla;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaPK)) {
            return false;
        }
        AsistenciaPK other = (AsistenciaPK) object;
        if (this.cedcapac != other.cedcapac) {
            return false;
        }
        if (this.idPlantilla != other.idPlantilla) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.customerapp.ejb.AsistenciaPK[cedcapac=" + cedcapac + ", idPlantilla=" + idPlantilla + "]";
    }

}
