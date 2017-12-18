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
@Table(name = "LIDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lider.findAll", query = "SELECT l FROM Lider l"),
    @NamedQuery(name = "Lider.findByIdlider", query = "SELECT l FROM Lider l WHERE l.idlider = :idlider"),
    @NamedQuery(name = "Lider.findByCargo", query = "SELECT l FROM Lider l WHERE l.cargo = :cargo")})
public class Lider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "IDLIDER")
    private Integer idlider;
    @Size(max = 100)
    @Column(name = "CARGO")
    private String cargo;

    public Lider() {
    }

    public Lider(Integer idlider) {
        this.idlider = idlider;
    }

    public Integer getIdlider() {
        return idlider;
    }

    public void setIdlider(Integer idlider) {
        this.idlider = idlider;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlider != null ? idlider.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lider)) {
            return false;
        }
        Lider other = (Lider) object;
        if ((this.idlider == null && other.idlider != null) || (this.idlider != null && !this.idlider.equals(other.idlider))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Lider[ idlider=" + idlider + " ]";
    }
    
}
