/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Guz
 */
@Entity
@Table(name = "CURSOSEVALUACION")
public class TurnoCapacitado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurnoCapacitadoPK turnoCapacitadoPK;

    @JoinColumn(name = "CODTURNO", referencedColumnName = "CODTURNO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Turno turno;

    @JoinColumn(name = "CEDCAPAC", referencedColumnName = "CEDCAPAC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Capacitado capacitado;

    public Capacitado getCapacitado() {
        return capacitado;
    }

    public void setCapacitado(Capacitado capacitado) {
        this.capacitado = capacitado;
    }

//    @OneToMany(mappedBy = "turnoCapacitado")
//    private Collection<Capacitado> capacitadoCollection;

//    public Collection<Capacitado> getCapacitadoCollection() {
//        return capacitadoCollection;
//    }
//
//    public void setCapacitadoCollection(Collection<Capacitado> capacitadoCollection) {
//        this.capacitadoCollection = capacitadoCollection;
//    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public TurnoCapacitadoPK getTurnoCapacitadoPK() {
        return turnoCapacitadoPK;
    }

    public void setTurnoCapacitadoPK(TurnoCapacitadoPK turnoCapacitadoPK) {
        this.turnoCapacitadoPK = turnoCapacitadoPK;
    }

   
}
