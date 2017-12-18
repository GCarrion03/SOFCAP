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
@Table(name = "TIPOEVALUACION", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tipoevaluacion.findAll", query = "SELECT t FROM Tipoevaluacion t"),
    @NamedQuery(name = "Tipoevaluacion.findByCodtev", query = "SELECT t FROM Tipoevaluacion t WHERE t.codtev = :codtev"),
    @NamedQuery(name = "Tipoevaluacion.findByDescripcion", query = "SELECT t FROM Tipoevaluacion t WHERE t.descripcion = :descripcion")})
public class Tipoevaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODTEV", nullable = false)
    private Integer codtev;
    @Column(name = "DESCRIPCION", length = 1024)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoevaluacion")
    private Collection<Plantillaevaluacion> plantillaevaluacionCollection;

    public Tipoevaluacion() {
    }

    public Tipoevaluacion(Integer codtev) {
        this.codtev = codtev;
    }

    public Integer getCodtev() {
        return codtev;
    }

    public void setCodtev(Integer codtev) {
        this.codtev = codtev;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Plantillaevaluacion> getPlantillaevaluacionCollection() {
        return plantillaevaluacionCollection;
    }

    public void setPlantillaevaluacionCollection(Collection<Plantillaevaluacion> plantillaevaluacionCollection) {
        this.plantillaevaluacionCollection = plantillaevaluacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codtev != null ? codtev.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoevaluacion)) {
            return false;
        }
        Tipoevaluacion other = (Tipoevaluacion) object;
        if ((this.codtev == null && other.codtev != null) || (this.codtev != null && !this.codtev.equals(other.codtev))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Tipoevaluacion[codtev=" + codtev + "]";
    }

}
