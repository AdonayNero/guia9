/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.model.Empleado;

/**
 *
 * @author Adonay
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "CapacitacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    
    /* INICIO DE LAS MODIFICACIONES */
    /**
     * Listado de empleados por Capacitacion
     * @param idCapacitacion
     * @return 
     */
    public List<Empleado> empleadoPorCapacitacion(int idCapacitacion){
        Query query = em.createQuery("SELECT C.codEmpleado FROM CapacitacionEmpleado C where C.idCapacitacion.idCapacitacion = :idCapacitacion");
        query.setParameter("idCapacitacion", idCapacitacion);
        return query.getResultList();
    }
    /* FIN DE LAS MODIFICACIONES */

    
}
