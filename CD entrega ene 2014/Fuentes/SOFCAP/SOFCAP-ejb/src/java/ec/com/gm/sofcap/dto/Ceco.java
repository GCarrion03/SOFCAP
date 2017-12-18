/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GUZ
 */
@Entity
@Table(name = "CECO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ceco.findAll", query = "SELECT c FROM Ceco c"),
    @NamedQuery(name = "Ceco.findByCodceco", query = "SELECT c FROM Ceco c WHERE c.codceco = :codceco"),
    @NamedQuery(name = "Ceco.findByDescripcion", query = "SELECT c FROM Ceco c WHERE c.descripcion = :descripcion")})
public class Ceco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CODCECO")
    private String codceco;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public Ceco() {
    }

    public Ceco(String codceco) {
        this.codceco = codceco;
    }

    public String getCodceco() {
        return codceco;
    }

    public void setCodceco(String codceco) {
        this.codceco = codceco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codceco != null ? codceco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ceco)) {
            return false;
        }
        Ceco other = (Ceco) object;
        if ((this.codceco == null && other.codceco != null) || (this.codceco != null && !this.codceco.equals(other.codceco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Ceco[ codceco=" + codceco + " ]";
    }
    
}
