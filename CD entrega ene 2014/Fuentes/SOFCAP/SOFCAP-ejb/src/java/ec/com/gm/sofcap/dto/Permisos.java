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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PERMISOS")
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p"),
    @NamedQuery(name = "Permisos.findByIdpermiso", query = "SELECT p FROM Permisos p WHERE p.idpermiso = :idpermiso"),
    @NamedQuery(name = "Permisos.findByDesper", query = "SELECT p FROM Permisos p WHERE p.desper = :desper")})
public class Permisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPERMISO")
    private Integer idpermiso;
    @Column(name = "DESPER")
    private String desper;
    @JoinTable(name = "PERROL", joinColumns = {
        @JoinColumn(name = "IDPERMISO", referencedColumnName = "IDPERMISO")}, inverseJoinColumns = {
        @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")})
    @ManyToMany
    private Collection<Rol> rolCollection;

    public Permisos() {
    }

    public Permisos(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public Integer getIdpermiso() {
        return idpermiso;
    }

    public void setIdpermiso(Integer idpermiso) {
        this.idpermiso = idpermiso;
    }

    public String getDesper() {
        return desper;
    }

    public void setDesper(String desper) {
        this.desper = desper;
    }

    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermiso != null ? idpermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idpermiso == null && other.idpermiso != null) || (this.idpermiso != null && !this.idpermiso.equals(other.idpermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Permisos[idpermiso=" + idpermiso + "]";
    }

}
