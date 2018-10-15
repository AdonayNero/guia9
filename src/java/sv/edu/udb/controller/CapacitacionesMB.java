/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.model.Capacitaciones;
import sv.edu.udb.model.Categorias;
import sv.edu.model.facade.CapacitacionesFacade;
import sv.edu.model.facade.CategoriasFacade;
import sv.edu.udb.util.JSFUtil;


@ManagedBean
@RequestScoped
public class CapacitacionesMB {
    
    @EJB
    CapacitacionesFacade capacitacionesFacade;
    
    @EJB
    CategoriasFacade categoriasFacade;
    
    private Capacitaciones capacitaciones;//<--Gegenerar Setters y Getters
    private Categorias categorias;//<--No tiene setter y getter
    
    public CapacitacionesMB(){
        capacitaciones = new Capacitaciones();
        categorias = new Categorias();
        capacitaciones.setIdCategoria(categorias);
    }
    
    public List<Categorias> getListaCategorias(){
        return categoriasFacade.findAll();
    }
    
    public List<Capacitaciones> getListaCapacitaciones(){
        return capacitacionesFacade.findAll();
    }
    
    public String guardarCapacitacion(){
        capacitacionesFacade.create(capacitaciones);
        capacitaciones = new Capacitaciones();
        capacitaciones.setIdCategoria(categorias);
        JSFUtil.addGlobalMessage("Capacitacion Agregada");
        return "";
    }
    
    public String modificarCapacitacion(){
        capacitacionesFacade.edit(capacitaciones);
        return "";
    }

    /**
     * @return the capacitaciones
     */
    public Capacitaciones getCapacitaciones() {
        return capacitaciones;
    }

    /**
     * @param capacitaciones the capacitaciones to set
     */
    public void setCapacitaciones(Capacitaciones capacitaciones) {
        this.capacitaciones = capacitaciones;
    }
}
