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
public class CursosEvaluacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_CURSO")
    private Integer id_Curso;
    @Basic(optional = false)
    @Column(name = "CODEVA")
    private Integer codEva;

    public CursosEvaluacionPK() {
    }

    public CursosEvaluacionPK(Integer id_Curso, Integer codEva, Integer idCurso) {
        this.id_Curso = id_Curso;
        this.codEva = codEva;
    }

    public Integer getid_Curso() {
        return id_Curso;
    }

    public void setid_Curso(Integer id_Curso) {
        this.id_Curso = id_Curso;
    }

    public Integer getcodEva() {
        return codEva;
    }

    public void setcodEva(Integer codEva) {
        this.codEva = codEva;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Integer) id_Curso;
        hash += (Integer) codEva;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursosEvaluacionPK)) {
            return false;
        }
        CursosEvaluacionPK other = (CursosEvaluacionPK) object;
        if (this.id_Curso != other.id_Curso) {
            return false;
        }
        if (this.codEva != other.codEva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.RespuestascapacitadoPK[id_Curso=" + id_Curso + ", codEva=" + codEva + "]";
    }

}
