/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.model.facade;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.model.CapacitacionEmpleado;
import sv.edu.udb.model.Capacitaciones;
import sv.edu.udb.model.Empleado;
import sv.edu.udb.util.JSFUtil;


/**
 *
 * @author Adonay
 */
@Stateless
public class CapacitacionEmpleadoFacade extends AbstractFacade<CapacitacionEmpleado> {

    @PersistenceContext(unitName = "CapacitacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionEmpleadoFacade() {
        super(CapacitacionEmpleado.class);
    }

    /* INICIO DE LAS MODIFICACIONES */
    /**
     * Para agregar un listado de empleados a una capacitación. Recibe Un
     * listado de códigos de empleados Verifica que previamente no haya sido
     * ingresado en la capacitación y lo inserta en la tabla
     * capacitacion_empleado
     *
     * @param codigosEmpleados
     * @param idCapacitacion
     */
    public void agregarEmpleadosACapacitacion(String[] codigosEmpleados, int idCapacitacion) {

        for (String codigo : codigosEmpleados) {

            Query query
                    = em.createQuery("SELECT count(CE) FROM CapacitacionEmpleado "
                            + " CE where CE.codEmpleado.codEmpleado = :codEmpleado "
                            + " and CE.idCapacitacion.idCapacitacion = :idCapacitacion");

            query.setParameter("codEmpleado", codigo);
            query.setParameter("idCapacitacion", idCapacitacion);

            Long validacionEmpleadoInscrito = (Long) query.getSingleResult();

            if (validacionEmpleadoInscrito > 0) {
                JSFUtil.addGlobalErrorMessage("El empleado ya ha sido registrado:" + codigo);
                continue;
            }

            Empleado empleado = new Empleado();
            empleado.setCodEmpleado(codigo);

            Capacitaciones capacitacion = new Capacitaciones();
            capacitacion.setIdCapacitacion(idCapacitacion);

            CapacitacionEmpleado capacitacionEmpleado = new CapacitacionEmpleado();
            capacitacionEmpleado.setCodEmpleado(empleado);
            capacitacionEmpleado.setIdCapacitacion(capacitacion);
            capacitacionEmpleado.setFechaInscripcion(new Date());

            this.create(capacitacionEmpleado);

        }

        JSFUtil.addGlobalMessage("Empleados agregados al curso.");
    }

    /* FIN DE LAS MODIFICACIONES */
}
