/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "RESPUESTASCAPACITADO")
@NamedQueries({
    @NamedQuery(name = "Respuestascapacitado.findAll", query = "SELECT r FROM Respuestascapacitado r"),
    @NamedQuery(name = "Respuestascapacitado.findByCodres", query = "SELECT r FROM Respuestascapacitado r WHERE r.respuestascapacitadoPK.codres = :codres"),
    @NamedQuery(name = "Respuestascapacitado.findByCedcapac", query = "SELECT r FROM Respuestascapacitado r WHERE r.respuestascapacitadoPK.cedcapac = :cedcapac"),
    @NamedQuery(name = "Respuestascapacitado.findByIdCurso", query = "SELECT r FROM Respuestascapacitado r WHERE r.respuestascapacitadoPK.idCurso = :idCurso")})
public class Respuestascapacitado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestascapacitadoPK respuestascapacitadoPK;
    @JoinColumn(name = "CODRES", referencedColumnName = "CODRES", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Respuesta respuesta;
    @JoinColumn(name = "ID_PLANTILLA", referencedColumnName = "ID_PLANTILLA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlantillaCurso plantillaCurso;
    @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Capacitado capacitado;



    public Respuestascapacitado() {
    }

    public Respuestascapacitado(RespuestascapacitadoPK respuestascapacitadoPK) {
        this.respuestascapacitadoPK = respuestascapacitadoPK;
    }

    public Respuestascapacitado(int codres, int cedcapac, int idCurso) {
        this.respuestascapacitadoPK = new RespuestascapacitadoPK(codres, cedcapac, idCurso);
    }

    public RespuestascapacitadoPK getRespuestascapacitadoPK() {
        return respuestascapacitadoPK;
    }

    public void setRespuestascapacitadoPK(RespuestascapacitadoPK respuestascapacitadoPK) {
        this.respuestascapacitadoPK = respuestascapacitadoPK;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    public PlantillaCurso getPlantillaCurso() {
        return plantillaCurso;
    }

    public void setPlantillaCurso(PlantillaCurso plantillaCurso) {
        this.plantillaCurso = plantillaCurso;
    }

   

    public Capacitado getCapacitado() {
        return capacitado;
    }

    public void setCapacitado(Capacitado capacitado) {
        this.capacitado = capacitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestascapacitadoPK != null ? respuestascapacitadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestascapacitado)) {
            return false;
        }
        Respuestascapacitado other = (Respuestascapacitado) object;
        if ((this.respuestascapacitadoPK == null && other.respuestascapacitadoPK != null) || (this.respuestascapacitadoPK != null && !this.respuestascapacitadoPK.equals(other.respuestascapacitadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.Respuestascapacitado[respuestascapacitadoPK=" + respuestascapacitadoPK + "]";
    }

}
