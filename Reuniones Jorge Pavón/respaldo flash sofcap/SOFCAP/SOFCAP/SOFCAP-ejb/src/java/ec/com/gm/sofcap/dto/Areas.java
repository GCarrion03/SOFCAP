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
import javax.persistence.Transient;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "AREAS", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a"),
    @NamedQuery(name = "Areas.findByCodarea", query = "SELECT a FROM Areas a WHERE a.codarea = :codarea"),
    @NamedQuery(name = "Areas.findByDesarea", query = "SELECT a FROM Areas a WHERE a.desarea = :desarea")})
public class Areas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODAREA", nullable = false)
    private Integer codarea;
    @Column(name = "DESAREA", length = 25)
    private String desarea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areas")
    private Collection<Capacitado> capacitadoCollection;

    @Transient
    private Long asistentes;

    public Areas() {
    }

    public Long getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Long asistentes) {
        this.asistentes = asistentes;
    }

    public Areas(Integer codarea) {
        this.codarea = codarea;
    }

    public Integer getCodarea() {
        return codarea;
    }
    
    public void setCodarea(Integer codarea) {
        this.codarea = codarea;
    }

    public String getDesarea() {
        return desarea;
    }

    public void setDesarea(String desarea) {
        this.desarea = desarea;
    }

    public Collection<Capacitado> getCapacitadoCollection() {
        return capacitadoCollection;
    }

    public void setCapacitadoCollection(Collection<Capacitado> capacitadoCollection) {
        this.capacitadoCollection = capacitadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codarea != null ? codarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.codarea == null && other.codarea != null) || (this.codarea != null && !this.codarea.equals(other.codarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Areas[codarea=" + codarea + "]";
    }

}
