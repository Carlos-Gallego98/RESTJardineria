package psp.jardineria.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import psp.jardineria.dao.EmpleadosDAO;
import psp.jardineria.modelo.Empleados;
import psp.jardineria.pojos.Empleado;

// Path raiz del recurso 

@Path("empleados")

public class EmpleadosREST {
	
    /**
     * Devuelve todos los empleados
     * opcionalmente se pueden pasar la oficina (por Querystring)
     * @return List<Empleado> (JSON)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("oficina") String codigoOficina) {
    	Response r;
    	
    	// lista de clientes a devolver
    	List<Empleado> lista = new ArrayList<Empleado>();
    	List<Empleados> listabdd = new ArrayList<Empleados>();
    	EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    	 	
    	// se recibe la extension por parametro
    	if (codigoOficina !=null) {
    		System.out.println("Entrada al Servicio LIST (por oficina)" +codigoOficina);
    		listabdd = empleadosDAO.getAll(codigoOficina);
    	}
    	// no se recibe el pais
    	else {
    		System.out.println("Entrada al servicio LIST (sin oficina)");
    		listabdd = empleadosDAO.getAll();
    	}
    	
    	// se mueven al POJO
    	Empleado emppojo = new Empleado();
    	Empleados empbbdd = new Empleados();
    	Iterator<Empleados> it = listabdd.iterator();
    	while (it.hasNext()) {
    		emppojo = new Empleado();
    		empbbdd = (Empleados) it.next();
    		emppojo.cargarEmpleadodesdeModelo(empbbdd);
    	   	lista.add(emppojo);
    	}
    	r =  Response.status(Response.Status.OK).entity(lista).build();
    	return r;
    }
       
    
    /**
     * Devuelve un empleado
     * @return Empleado (JSON)
     */
    @GET
    @Path("/{idEmpleado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("idEmpleado") int id) {
    	Response r;
    	EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    	Empleados empbbdd= empleadosDAO.get(id);
    	if (empbbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El empleado "+id+" no existe").build();
		}
		else
		{
         	Empleado emppojo = new Empleado();
         	emppojo.cargarEmpleadodesdeModelo(empbbdd);
			r =  Response.status(Response.Status.OK).entity(emppojo).build();
    	} 	
    	return r;
    }
	
    /**
     * Alta de un empleado
     * @return Empleado (JSON)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response altaEmpleado(Empleado empleado) {
    	Response r;
    	
    	if (empleado.getNombre()==null || 
    			empleado.getApellido1()==null || 
    			empleado.getExtension()==null ||
    			empleado.getEmail()==null ||
    			empleado.getCodigoOficina()==null) {
        		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
        	}
    	
    	else {
    		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    		Empleados empleadocreado = empleadosDAO.create(empleado.generarModeloDesdeEmpleado());
         	Empleado emppojo = new Empleado();
         	emppojo.cargarEmpleadodesdeModelo(empleadocreado);  
        	r =  Response.status(Response.Status.CREATED).entity(emppojo).build();
    	}
    	return r;
    }
  
    /**
     * Actualizacion de un empleado
     * @return Empleado (JSON)
     */
    @PUT
    @Path("/{idEmpleado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizacionEmpleado(@PathParam("idEmpleado") int id, Empleado empleado) {
    	Response r;
    	EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    	Empleados empbbdd = empleadosDAO.get(id);
    	if (empbbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El empleado "+id+" no existe").build();
    	}
       	else if (empleado.getNombre()==null || 
    			empleado.getApellido1()==null || 
    			empleado.getExtension()==null ||
    			empleado.getEmail()==null ||
    			empleado.getCodigoOficina()==null) {
        		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
        	}
    	else {  	
        	empbbdd.setNombre(empleado.getNombre());
        	empbbdd.setApellido1(empleado.getApellido1());
        	empbbdd.setApellido2(empleado.getApellido2());
        	empbbdd.setExtension(empleado.getExtension());
        	empbbdd.setEmail(empleado.getEmail());
        	empbbdd.setPuesto(empleado.getPuesto());
        	
        	Empleados empleadoactualizado = empleadosDAO.update(empbbdd);
         	Empleado emppojo = new Empleado();
         	emppojo.cargarEmpleadodesdeModelo(empleadoactualizado);	
        	r =  Response.status(Response.Status.OK).entity(emppojo).build();
    	}	
    	return r;
    }
    
    /**
     * Borrado de un empleado
     * @return Empleado (JSON)
     */  
    @DELETE
    @Path("/{idEmpleado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borradoEmpleado(@PathParam("idEmpleado") int id) {
    	Response r;
    	EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    	Empleados emp = empleadosDAO.get(id);
    	if (emp==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El empleado "+id+" no existe").build();
    	}
    	else
    	{
    		empleadosDAO.delete(emp);
      		System.out.println("Empleado " +id+" borrado correctamente");
    			r =  Response.status(Response.Status.OK).build();
    	} 	
    	return r;
    }
    
}

// borrador
