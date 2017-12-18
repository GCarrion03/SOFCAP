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
public class RespuestascapacitadoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODRES")
    private int codres;
    @Basic(optional = false)
    @Column(name = "CEDCAPAC")
    private int cedcapac;
    @Basic(optional = false)
    @Column(name = "ID_CURSO")
    private int idCurso;
    @Column(name = "CODPRG")
    @Basic(optional = false)
    private Integer codprg;

    public RespuestascapacitadoPK() {
    }

    public RespuestascapacitadoPK(int codres, int cedcapac, int idCurso) {
        this.codres = codres;
        this.cedcapac = cedcapac;
        this.idCurso = idCurso;
    }

    public int getCodres() {
        return codres;
    }

    public Integer getCodprg() {
        return codprg;
    }

    public void setCodprg(Integer codprg) {
        this.codprg = codprg;
    }

    public void setCodres(int codres) {
        this.codres = codres;
    }

    public int getCedcapac() {
        return cedcapac;
    }

    public void setCedcapac(int cedcapac) {
        this.cedcapac = cedcapac;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codres;
        hash += (int) cedcapac;
        hash += (int) idCurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestascapacitadoPK)) {
            return false;
        }
        RespuestascapacitadoPK other = (RespuestascapacitadoPK) object;
        if (this.codres != other.codres) {
            return false;
        }
        if (this.cedcapac != other.cedcapac) {
            return false;
        }
        if (this.idCurso != other.idCurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.RespuestascapacitadoPK[codres=" + codres + ", cedcapac=" + cedcapac + ", idCurso=" + idCurso + "]";
    }

}
