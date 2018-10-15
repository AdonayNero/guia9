package sv.edu.udb.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.model.Empleado;
import sv.edu.model.facade.EmpleadoFacade;
import sv.edu.udb.util.JSFUtil;

@ManagedBean
@RequestScoped
public class EmpleadosMB {


    private Empleado empleado;
    
    @EJB
    EmpleadoFacade empleadoFacade;//Generar Setter y Getter
    
    public EmpleadosMB(){
        empleado = new Empleado();
    }
    
    public String guardarEmpleado(){
        Empleado tmpEmpleado = empleadoFacade.find(empleado.getCodEmpleado());
        
        if(tmpEmpleado != null){
            JSFUtil.addGlobalErrorMessage("Codigo empleado duplicado");
        }else{
            empleadoFacade.create(getEmpleado());
            empleado = new Empleado();
            JSFUtil.addGlobalMessage("Empleado agregado exitosamente");
        }
        
        return "";
    }
    
    public List<Empleado> getEmpleadosList(){
        
        return empleadoFacade.findAll();
    }
    
    
        /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
}
