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
public class TurnoCapacitadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CEDCAPAC")
    private Integer cedCapac;
    @Basic(optional = false)
    @Column(name = "CODTURNO")
    private Integer codTurno;

    public TurnoCapacitadoPK() {
    }

    public TurnoCapacitadoPK(Integer cedCapac, Integer codTurno, Integer idCurso) {
        this.cedCapac = cedCapac;
        this.codTurno = codTurno;
    }

    public Integer getcedCapac() {
        return cedCapac;
    }

    public void setcedCapac(Integer cedCapac) {
        this.cedCapac = cedCapac;
    }

    public Integer getcodTurno() {
        return codTurno;
    }

    public void setcodTurno(Integer codTurno) {
        this.codTurno = codTurno;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Integer) cedCapac;
        hash += (Integer) codTurno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoCapacitadoPK)) {
            return false;
        }
        TurnoCapacitadoPK other = (TurnoCapacitadoPK) object;
        if (this.cedCapac != other.cedCapac) {
            return false;
        }
        if (this.codTurno != other.codTurno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.RespuestascapacitadoPK[cedCapac=" + cedCapac + ", codTurno=" + codTurno + "]";
    }

}
