/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.devspot.commons.dto;

import java.util.Collection;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

/**
 *
 * @author GUZ
 */
public class BaseDTO {
    @SuppressWarnings("rawtypes")
	public static <T> boolean isLoaded(T entity) {
		boolean init = true;
		if (entity == null) {
			init = false;
		} else if (entity instanceof HibernateProxy) {
			init = false;
		} else if (entity instanceof PersistentCollection) {
			PersistentCollection persistentCollection = (PersistentCollection) entity;
			if (!persistentCollection.getClass().isAssignableFrom(persistentCollection.getClass())) {
				// Si no es un objeto PersistentBag de Hibernate, esta no ha
				// sido inicializada.
				//init = CollectionUtils.isNotEmpty((Collection)entity);
			} else if (!persistentCollection.wasInitialized()) {
				// Si Hibernate no ha cargado la coleccion, no ha sido
				// inicializada
				init = false;
			}
		}
		return init;
	}
}
