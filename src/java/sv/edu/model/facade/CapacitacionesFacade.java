/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.model.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.model.Capacitaciones;

/**
 *
 * @author Adonay
 */
@Stateless
public class CapacitacionesFacade extends AbstractFacade<Capacitaciones> {

    @PersistenceContext(unitName = "CapacitacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionesFacade() {
        super(Capacitaciones.class);
    }
    
}
