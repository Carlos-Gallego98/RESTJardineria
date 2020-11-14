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

import psp.jardineria.dao.ClientesDAO;
import psp.jardineria.dao.EmpleadosDAO;
import psp.jardineria.dao.OficinasDAO;
import psp.jardineria.modelo.Clientes;
import psp.jardineria.modelo.Empleados;
import psp.jardineria.modelo.Oficinas;
import psp.jardineria.pojos.Cliente;
import psp.jardineria.pojos.Empleado;
import psp.jardineria.pojos.Oficina;

//Path raiz del recurso 

@Path("oficinas")

public class OficinasREST {

    /**
     * Devuelve todos las oficinas 
     * opcionalmente se pueden pasar el codigoOficina (por Querystring)
     * @return List<Oficina> (JSON)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("pais") String pais) {
    	Response r;
    	
    	// lista de oficinas a devolver
    	List<Oficina> lista = new ArrayList<Oficina>();
    	List<Oficinas> listabdd = new ArrayList<Oficinas>();
    	OficinasDAO oficinasDAO = new OficinasDAO();
    	
    	// se recibe el pais por parametro
    	if (pais !=null) {
    		System.out.println("Entrada al Servicio LIST (por pais)" +pais);
    		listabdd = oficinasDAO.getAll(pais);
    	}
    	// no se recibe el codigoOficina
    	else {
    		System.out.println("Entrada al servicio LIST (sin pais)");
    		listabdd = oficinasDAO.getAll();
    	}
    	
    	// se mueven al POJO
    	Oficina ofipojo = new Oficina();
    	Oficinas ofibbdd = new Oficinas();
    	Iterator<Oficinas> it = listabdd.iterator();
    	while (it.hasNext()) {
    		ofipojo = new Oficina();
    		ofibbdd = (Oficinas) it.next();
    		ofipojo.cargarOficinadesdeModelo(ofibbdd);
    	   	lista.add(ofipojo);
    	}
    	r =  Response.status(Response.Status.OK).entity(lista).build();
    	return r;
    }
    
    /**
     * Devuelve una oficina
     * @return Oficina (JSON)
     */
    @GET
    @Path("/{codigoOficina}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("codigoOficina") String codigoOficina) {
    	Response r;
    	OficinasDAO oficinasDAO = new OficinasDAO();
    	Oficinas ofibbdd= oficinasDAO.get(codigoOficina);
    	if (ofibbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El codigo oficina "+codigoOficina+" no existe").build();
		}
		else
		{
         	Oficina ofipojo = new Oficina();
         	ofipojo.cargarOficinadesdeModelo(ofibbdd);
			r =  Response.status(Response.Status.OK).entity(ofipojo).build();
    	} 	
    	return r;
    }
    
    /**
     * Alta de una oficina
     * @return Oficina (JSON)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response altaOficina(Oficina oficina) {
    	Response r;
    	OficinasDAO oficinasDAO = new OficinasDAO();
    	Oficinas ofibbdd= oficinasDAO.get(oficina.getCodigoOficina());
    	if (oficina.getCodigoOficina()==null ||
    			oficina.getCiudad()==null || 
    			oficina.getPais()==null ||
    			oficina.getCodigoPostal()==null ||
    			oficina.getTelefono()==null ||
    			oficina.getLineaDireccion1()==null) {
        		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
        	}
    	else if (ofibbdd!=null){
    		r =  Response.status(Response.Status.BAD_REQUEST).entity("El codigo de oficina "+oficina.getCodigoOficina() +" ya existe").build();
    	}
    	
    	else {
    		Oficinas oficinacreada = oficinasDAO.create(oficina.generarModeloDesdeOficina());
    		Oficina ofipojo = new Oficina();
    		ofipojo.cargarOficinadesdeModelo(oficinacreada);  
        	r =  Response.status(Response.Status.CREATED).entity(ofipojo).build();
    	}
    	return r;
    }
    
    /**
     * Actualizacion de un empleado
     * @return Empleado (JSON)
     */
    @PUT
    @Path("/{codigoOficina}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizacionEmpleado(@PathParam("codigoOficina") String codigoOficina, Oficina oficina) {
    	Response r;
    	OficinasDAO oficinasDAO = new OficinasDAO();
    	Oficinas ofibbdd = oficinasDAO.get(codigoOficina);
    	if (ofibbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("La oficina "+codigoOficina+" no existe").build();
    	}
       	else if (oficina.getCiudad()==null || 
    			oficina.getPais()==null ||
    			oficina.getCodigoPostal()==null ||
    			oficina.getTelefono()==null ||
    			oficina.getLineaDireccion1()==null) {
        		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
        	}
    	else {  	
        	ofibbdd.setCiudad(oficina.getCiudad());
        	ofibbdd.setPais(oficina.getPais());
        	ofibbdd.setRegion(oficina.getRegion());
        	ofibbdd.setCodigoPostal(oficina.getCodigoPostal());
        	ofibbdd.setTelefono(oficina.getTelefono());
        	ofibbdd.setLineaDireccion1(oficina.getLineaDireccion1());
        	ofibbdd.setLineaDireccion2(oficina.getLineaDireccion2());
        	
        	Oficinas oficinaactualizado = oficinasDAO.update(ofibbdd);
         	Oficina ofipojo = new Oficina();
         	ofipojo.cargarOficinadesdeModelo(oficinaactualizado);	
        	r =  Response.status(Response.Status.OK).entity(ofipojo).build();
    	}	
    	return r;
    }
    
    /**
     * Borrado de una oficina
     * @return Oficina (JSON)
     */ 
    @DELETE
    @Path("/{codigoOficina}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borradoOficina(@PathParam("codigoOficina") String codigoOficina) {
    	Response r;
    	OficinasDAO oficinasDAO = new OficinasDAO();
    	Oficinas ofi = oficinasDAO.get(codigoOficina);
    	if (ofi==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El codigo de Oficina "+codigoOficina+" no existe").build();
    	}
    	else
    	{
    		oficinasDAO.delete(ofi);
      		System.out.println("Codigo oficina " +codigoOficina+" borrado correctamente");
    			r =  Response.status(Response.Status.OK).build();
    	} 	
    	return r;
    }  
	    
}
