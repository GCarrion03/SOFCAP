/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author GUZ
 */
@Entity
@Table(name = "TEMA_GRUPO")
public class TemaGrupoCap implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TemaGrupoCapPK temaGrupoCapPK;
    
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GrupoCap grupoCap;
    
    @JoinColumn(name = "CODTEMA", referencedColumnName = "CODTEMA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema tema; 

    public TemaGrupoCap() {
    }

    public TemaGrupoCapPK getTemaGrupoCapPK() {
        return temaGrupoCapPK;
    }

    public void setTemaGrupoCapPK(TemaGrupoCapPK temaGrupoCapPK) {
        this.temaGrupoCapPK = temaGrupoCapPK;
    }

    public ec.com.gm.sofcap.dto.GrupoCap getGrupoCap() {
        return grupoCap;
    }

    public void setGrupoCap(ec.com.gm.sofcap.dto.GrupoCap grupoCap) {
        this.grupoCap = grupoCap;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    
    
}
