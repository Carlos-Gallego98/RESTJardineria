package psp.jardineria.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import psp.jardineria.modelo.Empleados;

public class EmpleadosDAO {

	Session ses;
	
	public EmpleadosDAO() {
		this.ses = HibernateUtil.getSessionFactory().openSession();	
	}
	
	// recupera un empleado por id
	public Empleados get(int id) {
		Empleados empleado = (Empleados) ses.get(Empleados.class, id);
		return empleado;
	}
	
	public List<Empleados> getAll(){
		Query query = ses.createQuery("from Empleados");
		List<Empleados> lista = query.getResultList();
		return lista;
	}
	
	public List<Empleados> getAll(String codigoOficina) {
		Query query = ses.createQuery("from Empleados where codigoOficina = :codigoOficina").setParameter("codigoOficina", codigoOficina);
		List<Empleados> lista = query.list();
		return lista;
	} 
	
	public Empleados create(Empleados e) {
		ses.beginTransaction();
		e.setCodigoEmpleado(calcularIDEmpleadomaximo()+1);
		ses.save(e);
		ses.getTransaction().commit();
		return e;
	}
	
	private int calcularIDEmpleadomaximo() {
		// calculo el id del empleado siguiente
		Query query = ses.createQuery("select max(codigoEmpleado) from Empleados"); 
		int maximo=(int) query.list().get(0);
		return maximo;
	}
	
	public Empleados update(Empleados e) {
		ses.beginTransaction();
		ses.update(e);
		ses.getTransaction().commit();
		return e;
	}
	
	public void delete(Empleados e) {
		ses.beginTransaction();
		ses.delete(e);
		ses.getTransaction().commit();
	}
	
	public static void main (String[] args) {
		EmpleadosDAO pgm = new EmpleadosDAO();
		pgm.test();
	}
	
	private void test() {
		
		/*
		 * Empleados e = new Empleados(); e.setNombre("Antonio");
		 * e.setApellido1("Mateu"); e.setApellido2("Lahoz"); OficinasDAO oficina = new
		 * OficinasDAO(); e.setOficinas(oficina.get("BCN-ES")); e.setExtension("6878");
		 * e.setEmail("antoniomateulahoz@gmail.com"); e.setPuesto("Director General");
		 * Empleados e2 = create(e); System.out.println(e2.getNombre());
		 * System.out.println(e2.getApellido1()); System.out.println(e2.getApellido2());
		 */
	
		
		 List<Empleados> lista = getAll(); for (int i=0; i<lista.size(); i++) {
		 System.out.println(lista.get(i).getNombre()+" "+lista.get(i).getApellido1()
		 +" "+lista.get(i).getApellido2()); }
		

		/*
		 * List<Empleados> lista = getAll("BCN-ES"); for (int i=0; i<lista.size(); i++)
		 * { System.out.println(lista.get(i).getNombre()+" "+lista.get(i).getApellido1()
		 * +" "+lista.get(i).getApellido2()); }
		 */
		 
		// Empleados e = get(32);
		// System.out.println(e.getNombre());
		/*
		 * e.setNombre("Antoniano"); update(e);
		 */
		// delete(e);
		
	}
	
	
	
}
