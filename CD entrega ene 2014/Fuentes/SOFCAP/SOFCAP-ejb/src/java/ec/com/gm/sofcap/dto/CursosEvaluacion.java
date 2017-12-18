/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "CURSOSEVALUACION")
public class CursosEvaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CursosEvaluacionPK cursosEvaluacionPK;

    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch= FetchType.LAZY)
    private Curso curso;

    @JoinColumn(name = "CODEVA", referencedColumnName = "CODEVA", insertable = false, updatable = false)
    @ManyToOne(optional = false,fetch= FetchType.LAZY)
    private Plantillaevaluacion plantillaEvaluacion;

    public Plantillaevaluacion getPlantillaEvaluacion() {
        return plantillaEvaluacion;
    }

    public void setPlantillaEvaluacion(Plantillaevaluacion plantillaEvaluacion) {
        this.plantillaEvaluacion = plantillaEvaluacion;
    }

    public CursosEvaluacion() {
        this.cursosEvaluacionPK=new CursosEvaluacionPK();
    }

    



    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public CursosEvaluacionPK getCursosEvaluacionPK() {
        return cursosEvaluacionPK;
    }

    public void setCursosEvaluacionPK(CursosEvaluacionPK cursosEvaluacionPK) {
        this.cursosEvaluacionPK = cursosEvaluacionPK;
    }
   

}
