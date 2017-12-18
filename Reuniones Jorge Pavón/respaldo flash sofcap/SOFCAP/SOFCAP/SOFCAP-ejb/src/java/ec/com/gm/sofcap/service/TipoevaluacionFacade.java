/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Tipoevaluacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Guz
 */
@Stateless
public class TipoevaluacionFacade extends AbstractFacade<Tipoevaluacion> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoevaluacionFacade() {
        super(Tipoevaluacion.class);
    }

}
