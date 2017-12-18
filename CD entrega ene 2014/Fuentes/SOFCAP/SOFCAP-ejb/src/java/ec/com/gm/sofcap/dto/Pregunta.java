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
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "PREGUNTA")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByCodprg", query = "SELECT p FROM Pregunta p WHERE p.codprg = :codprg"),
    @NamedQuery(name = "Pregunta.findByCabprg", query = "SELECT p FROM Pregunta p WHERE p.cabprg = :cabprg")})
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPRG")
    private Integer codprg;
    @Column(name = "CABPRG")
    private String cabprg;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private Collection<Respuesta> respuestaCollection;
    @JoinColumn(name = "CODEVA", referencedColumnName = "CODEVA")
    @ManyToOne(optional = false)
    private Plantillaevaluacion plantillaevaluacion;

    @Transient
    private Integer respuestaAux;

    @Transient
    private Double porcentaje;

    @Transient
    private Double media;

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    public Pregunta() {
    }

    public Pregunta(Integer codprg) {
        this.codprg = codprg;
    }

    public Integer getCodprg() {
        return codprg;
    }

    public void setCodprg(Integer codprg) {
        this.codprg = codprg;
    }

    public Integer getRespuestaAux() {
        return respuestaAux;
    }

    public void setRespuestaAux(Integer respuestaAux) {
        this.respuestaAux = respuestaAux;
    }

    public String getCabprg() {
        return cabprg;
    }

    public void setCabprg(String cabprg) {
        this.cabprg = cabprg;
    }

    public Collection<Respuesta> getRespuestaCollection() {
        return respuestaCollection;
    }

    public void setRespuestaCollection(Collection<Respuesta> respuestaCollection) {
        this.respuestaCollection = respuestaCollection;
    }

    public Plantillaevaluacion getPlantillaevaluacion() {
        return plantillaevaluacion;
    }

    public void setPlantillaevaluacion(Plantillaevaluacion plantillaevaluacion) {
        this.plantillaevaluacion = plantillaevaluacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprg != null ? codprg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.codprg == null && other.codprg != null) || (this.codprg != null && !this.codprg.equals(other.codprg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Pregunta[codprg=" + codprg + "]";
    }

}
