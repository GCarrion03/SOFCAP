/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guz
 */
@Embeddable
public class TemaGrupoCapPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GRUPO")
    private Integer id_Grupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODTEMA")
    private Integer codTema;

    public TemaGrupoCapPK() {
    }

    public Integer getCodTema() {
        return codTema;
    }

    public void setCodTema(Integer codTema) {
        this.codTema = codTema;
    }

    public Integer getId_Grupo() {
        return id_Grupo;
    }

    public void setId_Grupo(Integer id_Grupo) {
        this.id_Grupo = id_Grupo;
    }

    

    @Override
    public String toString() {
        return "ec.com.gm.sofcap.dto.TemaGrupoCapPK[codTema=" + codTema + ", idGrupo=" + id_Grupo + "]";
    }

}
