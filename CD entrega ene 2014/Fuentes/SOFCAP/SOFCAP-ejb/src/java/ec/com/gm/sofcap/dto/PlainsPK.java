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
public class PlainsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PLANTILLA")
    private int idPlantilla;
    @Basic(optional = false)
    @Column(name = "CODINSTR")
    private int codinstr;

    public PlainsPK() {
    }

    public PlainsPK(int idPlantilla, int codinstr) {
        this.idPlantilla = idPlantilla;
        this.codinstr = codinstr;
    }

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public int getCodinstr() {
        return codinstr;
    }

    public void setCodinstr(int codinstr) {
        this.codinstr = codinstr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPlantilla;
        hash += (int) codinstr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlainsPK)) {
            return false;
        }
        PlainsPK other = (PlainsPK) object;
        if (this.idPlantilla != other.idPlantilla) {
            return false;
        }
        if (this.codinstr != other.codinstr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.PlainsPK[idPlantilla=" + idPlantilla + ", codinstr=" + codinstr + "]";
    }

}
