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
@Table(name = "TIPODECAPACITACION", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tipodecapacitacion.findAll", query = "SELECT t FROM Tipodecapacitacion t"),
    @NamedQuery(name = "Tipodecapacitacion.findByCodtipcap", query = "SELECT t FROM Tipodecapacitacion t WHERE t.codtipcap = :codtipcap"),
    @NamedQuery(name = "Tipodecapacitacion.findByNomtipcap", query = "SELECT t FROM Tipodecapacitacion t WHERE t.nomtipcap = :nomtipcap")})
public class Tipodecapacitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTIPCAP", nullable = false)
    private Integer codtipcap;
    @Column(name = "NOMTIPCAP", length = 1024)
    private String nomtipcap;
    @OneToMany(mappedBy = "tipodecapacitacion")
    private Collection<PlantillaCurso> cursoCollection;

    public Tipodecapacitacion() {
    }

    public Tipodecapacitacion(Integer codtipcap) {
        this.codtipcap = codtipcap;
    }

    public Integer getCodtipcap() {
        return codtipcap;
    }

    public void setCodtipcap(Integer codtipcap) {
        this.codtipcap = codtipcap;
    }

    public String getNomtipcap() {
        return nomtipcap;
    }

    public void setNomtipcap(String nomtipcap) {
        this.nomtipcap = nomtipcap;
    }

    public Collection<PlantillaCurso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<PlantillaCurso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtipcap != null ? codtipcap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodecapacitacion)) {
            return false;
        }
        Tipodecapacitacion other = (Tipodecapacitacion) object;
        if ((this.codtipcap == null && other.codtipcap != null) || (this.codtipcap != null && !this.codtipcap.equals(other.codtipcap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Tipodecapacitacion[codtipcap=" + codtipcap + "]";
    }

}
