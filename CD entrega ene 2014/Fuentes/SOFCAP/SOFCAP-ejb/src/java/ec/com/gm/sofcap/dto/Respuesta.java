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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "RESPUESTA")
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
    @NamedQuery(name = "Respuesta.findByCodres", query = "SELECT r FROM Respuesta r WHERE r.codres = :codres"),
    @NamedQuery(name = "Respuesta.findByDesres", query = "SELECT r FROM Respuesta r WHERE r.desres = :desres"),
    @NamedQuery(name = "Respuesta.findByEsacer", query = "SELECT r FROM Respuesta r WHERE r.esacer = :esacer")})
public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODRES")
    private Integer codres;
    @Column(name = "DESRES")
    private String desres;
    @Column(name = "ESACER")
    private Boolean esacer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "respuesta")
    private Collection<Respuestascapacitado> respuestascapacitadoCollection;
    @JoinColumn(name = "CODPRG", referencedColumnName = "CODPRG")
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public Respuesta() {
    }

    public Respuesta(Integer codres) {
        this.codres = codres;
    }


    public Integer getCodres() {
        return codres;
    }

    public void setCodres(Integer codres) {
        this.codres = codres;
    }

    public String getDesres() {
        return desres;
    }

    public void setDesres(String desres) {
        this.desres = desres;
    }

    public Boolean getEsacer() {
        return esacer;
    }

    public void setEsacer(Boolean esacer) {
        this.esacer = esacer;
    }

    public Collection<Respuestascapacitado> getRespuestascapacitadoCollection() {
        return respuestascapacitadoCollection;
    }

    public void setRespuestascapacitadoCollection(Collection<Respuestascapacitado> respuestascapacitadoCollection) {
        this.respuestascapacitadoCollection = respuestascapacitadoCollection;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codres != null ? codres.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.codres == null && other.codres != null) || (this.codres != null && !this.codres.equals(other.codres))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Respuesta[codres=" + codres + "]";
    }

}
