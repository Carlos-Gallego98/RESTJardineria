package psp.jardineria.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import psp.jardineria.modelo.Clientes;

public class ClientesDAO {

	Session ses;
	
	public ClientesDAO() {
		this.ses = HibernateUtil.getSessionFactory().openSession();	
	}
	
	// recupera un cliente por id
	public Clientes get(int id) {
		Clientes cliente = (Clientes) ses.get(Clientes.class, id);
		return cliente;
	}

	public List<Clientes> getAll() {
		Query query = ses.createQuery("from Clientes");
		List<Clientes> lista = query.list();
		return lista;
	}
	
	public List<Clientes> getAll(String pais) {
		Query query = ses.createQuery("from Clientes where pais = :pais").setParameter("pais", pais);
		List<Clientes> lista = query.list();
		return lista;
	} 
	

	public Clientes create(Clientes c) {
		ses.beginTransaction();
		// calculo el id del cliente siguiente
		c.setCodigoCliente(calcularIDClientemaximo()+1);
		ses.save(c);
		ses.getTransaction().commit();
		return c;
	}
	
	private int calcularIDClientemaximo() {
		
		Query query = ses.createQuery("select max(codigoCliente) from Clientes"); 
		int maximo=(int) query.list().get(0);
		return maximo;
	}
	
	public Clientes update(Clientes c) {
		ses.beginTransaction();
		ses.update(c);
		ses.getTransaction().commit();
		return c;
	}
	
	public void delete(Clientes c) {
		ses.beginTransaction();
		ses.delete(c);
		ses.getTransaction().commit();
	}


	public static void main(String[] args) {
		ClientesDAO pgm = new ClientesDAO();
		pgm.test();

	}

	private void test() {

		// Clientes c= get(1);
		// System.out.println(c.getNombreCliente());
		// System.out.println(c.getApellidoContacto());

	//	List<Clientes> lista = getAll();
	//	for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	//		Clientes clientes = (Clientes) iterator.next();
	//		System.out.println(clientes.getNombreCliente() + " " + clientes.getApellidoContacto());
//		}
		
		/* System.out.println(calcularIDClientemaximo()); */
		
		/*
		 * Clientes c = new Clientes(); c.setNombreCliente("Prueba");
		 * c.setNombreContacto("Contacto"); c.setApellidoContacto("Apellido");
		 * c.setTelefono("123456789"); c.setFax("987654321");
		 * c.setLineaDireccion1("calle"); c.setLineaDireccion2("mayor");
		 * c.setCiudad("madrid"); c.setRegion("madrid"); c.setPais("españa");
		 * c.setCodigoPostal("12345"); Clientes c2 = create(c);
		 * System.out.println(c2.getCodigoCliente());
		 * System.out.println(c2.getNombreCliente());
		 */
		 
		/*
		 * Clientes c = get(39); c.setNombreContacto("Cree");
		 * c.setTelefono("919283243"); update(c);
		 */
		
	//	delete(get(40));
		
		List<Clientes> lista = getAll("USA");
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				Clientes clientes = (Clientes) iterator.next();
				System.out.println(clientes.getNombreCliente() + " " + clientes.getApellidoContacto());
			}

	}

}
