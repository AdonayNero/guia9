package sv.edu.udb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.model.Capacitaciones;
import sv.edu.udb.model.Empleado;
import sv.edu.model.facade.CapacitacionEmpleadoFacade;
import sv.edu.model.facade.CapacitacionesFacade;
import sv.edu.model.facade.EmpleadoFacade;

@ManagedBean
@RequestScoped
public class TrainingMB {
    
    private String codigosEmpleadosSeleccionado[];
    private Integer idCapacitacion = 0;
    
    @EJB
    CapacitacionesFacade capacitacionesFacade;
    @EJB
    CapacitacionEmpleadoFacade capacitacionEmpleadoFacade;
    @EJB
    EmpleadoFacade empleadoFacade;
    
    public List<Capacitaciones> getCapacitacionesList(){
        return capacitacionesFacade.findAll();
    }
    
    public List<Empleado> getListaEmpleadosPorCapacitacion(){
        
        return empleadoFacade.empleadoPorCapacitacion(idCapacitacion);
    }
    
    public String registrarEmpleado(){
        capacitacionEmpleadoFacade.agregarEmpleadosACapacitacion(codigosEmpleadosSeleccionado, idCapacitacion);
        
        return "";
    }
    


    public String[] getCodigosEmpleadosSeleccionado() {
        return codigosEmpleadosSeleccionado;
    }


    public void setCodigosEmpleadosSeleccionado(String[] codigosEmpleadosSeleccionado) {
        this.codigosEmpleadosSeleccionado = codigosEmpleadosSeleccionado;
    }

    /**
     * @return the idCapacitacion
     */
    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    /**
     * @param idCapacitacion the idCapacitacion to set
     */
    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }
    
}
