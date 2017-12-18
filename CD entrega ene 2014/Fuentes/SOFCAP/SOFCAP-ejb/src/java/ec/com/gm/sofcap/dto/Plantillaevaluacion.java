/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PLANTILLAEVALUACION", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Plantillaevaluacion.findAll", query = "SELECT p FROM Plantillaevaluacion p"),
    @NamedQuery(name = "Plantillaevaluacion.findByCodeva", query = "SELECT p FROM Plantillaevaluacion p WHERE p.codeva = :codeva"),
    @NamedQuery(name = "Plantillaevaluacion.findByDespla", query = "SELECT p FROM Plantillaevaluacion p WHERE p.despla = :despla")})
public class Plantillaevaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODEVA", nullable = false)
    private Integer codeva;
    @Column(name = "DESPLA", length = 100)
    private String despla;
//    @OneToMany(mappedBy = "plantillaevaluacionCollection")
//    private Collection<Curso> cursoCollection;
    @JoinColumn(name = "CODTEV", referencedColumnName = "CODTEV", nullable = false)
    @ManyToOne(optional = false)
    private Tipoevaluacion tipoevaluacion;
    @OneToMany(mappedBy = "plantillaEvaluacion")
    private Collection<CursosEvaluacion> cursosevaluacionCollection;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantillaevaluacion")
    private Collection<Pregunta> preguntaCollection;


     public Collection<CursosEvaluacion> getCursosevaluacionCollection() {
        return cursosevaluacionCollection;
    }

    public void setCursosevaluacionCollection(Collection<CursosEvaluacion> cursosevaluacionCollection) {
        this.cursosevaluacionCollection = cursosevaluacionCollection;
    }

    public Collection<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }


    public Plantillaevaluacion() {
    }

    public Plantillaevaluacion(Integer codeva) {
        this.codeva = codeva;
    }

    public Integer getCodeva() {
        return codeva;
    }

    public void setCodeva(Integer codeva) {
        this.codeva = codeva;
    }

    public String getDespla() {
        return despla;
    }

    public void setDespla(String despla) {
        this.despla = despla;
    }

//    public Collection<Curso> getCursoCollection() {
//        return cursoCollection;
//    }
//
//    public void setCursoCollection(Collection<Curso> cursoCollection) {
//        this.cursoCollection = cursoCollection;
//    }

    public Tipoevaluacion getTipoevaluacion() {
        return tipoevaluacion;
    }

    public void setTipoevaluacion(Tipoevaluacion tipoevaluacion) {
        this.tipoevaluacion = tipoevaluacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeva != null ? codeva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantillaevaluacion)) {
            return false;
        }
        Plantillaevaluacion other = (Plantillaevaluacion) object;
        if ((this.codeva == null && other.codeva != null) || (this.codeva != null && !this.codeva.equals(other.codeva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Plantillaevaluacion[codeva=" + codeva + "]";
    }

}
