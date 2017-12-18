/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.com.gm.sofcap.service;

import ec.com.gm.sofcap.dto.Tema;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Guz
 */
@Stateless
public class TemaFacade extends AbstractFacade<Tema> {
    @PersistenceContext(unitName = "SOFCAP-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TemaFacade() {
        super(Tema.class);
    }

    public Collection<Tema> obtenerTemasPrincipio (Integer codPrincipio)
    {
                HashMap<String, Object> parametros = null;
		StringBuilder select = null;
		Query query = null;
//		HashMap<String, Object> parametros = null;
			select = new StringBuilder();

			//GENERA SENTENCIA SQL
			this.generarConsulta(new Tema(), select);
                        if (codPrincipio!=null){
                        select.append(" where Tema.principiosGms.codprinci = :pcodprinci");
        }
                        select.append(" order by Tema.nomtema asc");
                        query = em.createQuery(select.toString());
			//CREA LA CONSULTA
                        if (codPrincipio!=null){
//           		parametros.put("pcodtema", codPrincipio);
                        query.setParameter("pcodprinci", codPrincipio);
        }
                        
			//PARAMETROS
			return query.getResultList();

    }
    private void generarConsulta(Tema tema, StringBuilder select){
		select.append("select distinct Tema from ");
		select.append(Tema.class.getName()).append(" Tema");

	}
}
