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
@Table(name = "PRINCIPIOS_GMS", catalog = "SOFCAP", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "PrincipiosGms.findAll", query = "SELECT p FROM PrincipiosGms p"),
    @NamedQuery(name = "PrincipiosGms.findByCodprinci", query = "SELECT p FROM PrincipiosGms p WHERE p.codprinci = :codprinci"),
    @NamedQuery(name = "PrincipiosGms.findByDesprinci", query = "SELECT p FROM PrincipiosGms p WHERE p.desprinci = :desprinci")})
public class PrincipiosGms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPRINCI", nullable = false)
    private Integer codprinci;
    @Column(name = "DESPRINCI", length = 50)
    private String desprinci;
    @OneToMany(mappedBy = "principiosGms")
    private Collection<Tema> temaCollection;

    public PrincipiosGms() {
    }

    public PrincipiosGms(Integer codprinci) {
        this.codprinci = codprinci;
    }

    public Integer getCodprinci() {
        return codprinci;
    }

    public void setCodprinci(Integer codprinci) {
        this.codprinci = codprinci;
    }

    public String getDesprinci() {
        return desprinci;
    }

    public void setDesprinci(String desprinci) {
        this.desprinci = desprinci;
    }

    public Collection<Tema> getTemaCollection() {
        return temaCollection;
    }

    public void setTemaCollection(Collection<Tema> temaCollection) {
        this.temaCollection = temaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprinci != null ? codprinci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrincipiosGms)) {
            return false;
        }
        PrincipiosGms other = (PrincipiosGms) object;
        if ((this.codprinci == null && other.codprinci != null) || (this.codprinci != null && !this.codprinci.equals(other.codprinci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.PrincipiosGms[codprinci=" + codprinci + "]";
    }

}
