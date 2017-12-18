/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "CENTROENTRENAMIENTO")
@NamedQueries({
    @NamedQuery(name = "Centroentrenamiento.findAll", query = "SELECT c FROM Centroentrenamiento c"),
    @NamedQuery(name = "Centroentrenamiento.findByCodcenent", query = "SELECT c FROM Centroentrenamiento c WHERE c.codcenent = :codcenent"),
    @NamedQuery(name = "Centroentrenamiento.findByDescenent", query = "SELECT c FROM Centroentrenamiento c WHERE c.descenent = :descenent")})
public class Centroentrenamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODCENENT")
    private Integer codcenent;
    @Column(name = "DESCENENT")
    private String descenent;
    @OneToMany(mappedBy = "centroentrenamiento")
    private Collection<Curso> cursoCollection;

    public Centroentrenamiento() {
    }

    public Centroentrenamiento(Integer codcenent) {
        this.codcenent = codcenent;
    }

    public Integer getCodcenent() {
        return codcenent;
    }

    public void setCodcenent(Integer codcenent) {
        this.codcenent = codcenent;
    }

    public String getDescenent() {
        return descenent;
    }

    public void setDescenent(String descenent) {
        this.descenent = descenent;
    }

    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcenent != null ? codcenent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centroentrenamiento)) {
            return false;
        }
        Centroentrenamiento other = (Centroentrenamiento) object;
        if ((this.codcenent == null && other.codcenent != null) || (this.codcenent != null && !this.codcenent.equals(other.codcenent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Centroentrenamiento[codcenent=" + codcenent + "]";
    }

}
