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
@Table(name = "TURNO") 
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByCodturno", query = "SELECT t FROM Turno t WHERE t.codturno = :codturno"),
    @NamedQuery(name = "Turno.findByDesturno", query = "SELECT t FROM Turno t WHERE t.desturno = :desturno")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTURNO")
    private Integer codturno;
    @Column(name = "DESTURNO")
    private String desturno;
    @OneToMany(mappedBy = "turno")
    private Collection<Curso> cursoCollection;

    public Turno() {
    }

    public Turno(Integer codturno) {
        this.codturno = codturno;
    }

    public Integer getCodturno() {
        return codturno;
    }

    public void setCodturno(Integer codturno) {
        this.codturno = codturno;
    }

    public String getDesturno() {
        return desturno;
    }

    public void setDesturno(String desturno) {
        this.desturno = desturno;
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
        hash += (codturno != null ? codturno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.codturno == null && other.codturno != null) || (this.codturno != null && !this.codturno.equals(other.codturno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Turno[codturno=" + codturno + "]";
    }

}
