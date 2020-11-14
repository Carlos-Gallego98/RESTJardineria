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
import psp.jardineria.modelo.Clientes;
import psp.jardineria.pojos.Cliente;

// Path raiz del recurso 

@Path("clientes")

public class ClientesREST {
	
    /**
     * Devuelve todos los clientes
     * opcionalmente se pueden pasar el pais (por Querystring)
     * @return List<Cliente> (JSON)
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("pais") String pais) {
    	Response r;
    	
    	// lista de clientes a devolver
    	List<Cliente> lista = new ArrayList<Cliente>();
    	List<Clientes> listabdd = new ArrayList<Clientes>();
    	ClientesDAO clientesDAO = new ClientesDAO();
    	
    	// se recibe el pais por parametro
    	if (pais !=null) {
    		System.out.println("Entrada al Servicio LIST (por pais)" +pais);
    		listabdd = clientesDAO.getAll(pais);
    	}
    	// no se recibe el pais
    	else {
    		System.out.println("Entrada al servicio LIST (sin pais)");
    		listabdd = clientesDAO.getAll();
    	}
    	
    	// se mueven al POJO
    	Cliente clipojo = new Cliente();
    	Clientes clibbdd = new Clientes();
    	Iterator<Clientes> it = listabdd.iterator();
    	while (it.hasNext()) {
    		clipojo = new Cliente();
    		clibbdd = (Clientes) it.next();
    	   	clipojo.cargarClientedesdeModelo(clibbdd);
    	   	lista.add(clipojo);
    	}
    	r =  Response.status(Response.Status.OK).entity(lista).build();
    	return r;
    }
    
    /**
     * Devuelve un cliente
     * @return Cliente (JSON)
     */
    @GET
    @Path("/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("idCliente") int id) {
    	Response r;
    	ClientesDAO clientesDAO = new ClientesDAO();
    	Clientes clibbdd= clientesDAO.get(id);
    	if (clibbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El cliente "+id+" no existe").build();
		}
		else
		{
         	Cliente clipojo = new Cliente();
         	clipojo.cargarClientedesdeModelo(clibbdd);
			r =  Response.status(Response.Status.OK).entity(clipojo).build();
    	} 	
    	return r;
    }
	
    /**
     * Alta de un cliente
     * @return Cliente (JSON)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response altaCliente(Cliente cliente) {
    	Response r;
    	
    	if (cliente.getNombreCliente()==null || 
    		cliente.getTelefono()==null || 
    		cliente.getFax()==null ||
    		cliente.getLineaDireccion1()==null ||
    		cliente.getCiudad()==null) {
    		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
    	}
    	else {
    		ClientesDAO clientesDAO = new ClientesDAO();
    		Clientes clientecreado = clientesDAO.create(cliente.generarModeloDesdeCliente());
         	Cliente clipojo = new Cliente();
         	clipojo.cargarClientedesdeModelo(clientecreado);  
        	r =  Response.status(Response.Status.CREATED).entity(clipojo).build();
    	}
    	return r;
    }
  
    /**
     * Actualizacion de un cliente
     * @return Cliente (JSON)
     */
    @PUT
    @Path("/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizacionCliente(@PathParam("idCliente") int id, Cliente cliente) {
    	Response r;
    	ClientesDAO clientesDAO = new ClientesDAO();
    	Clientes clibbdd= clientesDAO.get(id);
    	if (clibbdd==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El cliente "+id+" no existe").build();
		}
       	else if (cliente.getNombreCliente()==null || 
        		cliente.getTelefono()==null || 
        		cliente.getFax()==null ||
        		cliente.getLineaDireccion1()==null ||
        		cliente.getCiudad()==null) {
        		r =  Response.status(Response.Status.BAD_REQUEST).entity("Faltan datos obligatorios").build();
        	}
    	else {  	
	    	clibbdd.setNombreCliente(cliente.getNombreCliente());
	    	clibbdd.setNombreContacto(cliente.getNombreContacto());
	    	clibbdd.setApellidoContacto(cliente.getApellidoContacto());
	    	clibbdd.setTelefono(cliente.getTelefono());
	    	clibbdd.setFax(cliente.getFax());
	    	clibbdd.setLineaDireccion1(cliente.getLineaDireccion1());
	    	clibbdd.setLineaDireccion2(cliente.getLineaDireccion2());
	    	clibbdd.setCiudad(cliente.getCiudad());
	    	clibbdd.setRegion(cliente.getRegion());
	    	clibbdd.setPais(cliente.getPais());
	    	clibbdd.setCodigoPostal(cliente.getCodigoPostal());
	    	clibbdd.setLimiteCredito(cliente.getLimiteCredito());
	    	
	    	Clientes clienteactualizado = clientesDAO.update(clibbdd);
	     	Cliente clipojo = new Cliente();
	     	clipojo.cargarClientedesdeModelo(clienteactualizado);	
        	r =  Response.status(Response.Status.OK).entity(clipojo).build();
    	}	
    	return r;
    }
    
    /**
     * Borrado de un cliente
     * @return Cliente (JSON)
     */  
    @DELETE
    @Path("/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borradoCliente(@PathParam("idCliente") int id) {
    	Response r;
    	ClientesDAO clientesDAO = new ClientesDAO();
    	Clientes cli = clientesDAO.get(id);
    	if (cli==null) {
    		r =  Response.status(Response.Status.NOT_FOUND).entity("El cliente "+id+" no existe").build();
		}
		else
		{
			clientesDAO.delete(cli);
	  		System.out.println("Cliente " +id+" borrado correctamente");
 			r =  Response.status(Response.Status.OK).build();
    	} 	
    	return r;
    }
    
}
