package psp.jardineria.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;
import psp.jardineria.modelo.Oficinas;

public class OficinasDAO {
	
	Session ses;
	
	public OficinasDAO() {
		this.ses = HibernateUtil.getSessionFactory().openSession();	
	}
	
	// recupera una oficina por id
		public Oficinas get(String id) {
			Oficinas oficina = (Oficinas) ses.get(Oficinas.class, id);
			return oficina;
		}
		
		public List<Oficinas> getAll() {
			Query query = ses.createQuery("from Oficinas");
			List<Oficinas> lista = query.getResultList();
			return lista;
		}
		
		public List<Oficinas> getAll(String pais) {
			Query query = ses.createQuery("from Oficinas where pais = :pais").setParameter("pais", pais);
			List<Oficinas> lista = query.list();
			return lista;
		} 
		
		public Oficinas create(Oficinas o) {
			ses.beginTransaction();
			ses.save(o);
			ses.getTransaction().commit();
			return o;
		}
		
		public Oficinas update(Oficinas o) {
			ses.beginTransaction();
			ses.update(o);
			ses.getTransaction().commit();
			return o;
		}
		
		public void delete(Oficinas o) {
			ses.beginTransaction();
			ses.delete(o);
			ses.getTransaction().commit();
		}
		
		public static void main (String[] args) {
			OficinasDAO pgm = new OficinasDAO();
			pgm.test();
		}
		
		private void test() {
		/*
		 * Oficinas o = new Oficinas(); o.setCodigoOficina("ONT-CAN");
		 * o.setCiudad("Ontario"); o.setPais("Canada"); o.setRegion("Canada");
		 * o.setCodigoPostal("28660"); o.setTelefono("912374343");
		 * o.setLineaDireccion1("918282891821"); o.setLineaDireccion2("918282891822");
		 * Oficinas o2 = create(o); System.out.println(o2.getCiudad());
		 * System.out.println(o2.getPais()); System.out.println(o2.getRegion());
		 */
			
		/*
		 * Oficinas o = get("ONT-CAN"); System.out.println(o.getCodigoOficina());
		 * System.out.println(o.getCiudad()); System.out.println(o.getCodigoPostal());
		 * System.out.println(o.getRegion());
		 */
		//	List<Oficinas> lista = getAll();
		/*
		 * List<Oficinas> lista = getAll("España"); for (int i=0; i<lista.size(); i++) {
		 * System.out.println(lista.get(i).getCodigoOficina()+'
		 * '+lista.get(i).getCodigoPostal()); }
		 */
			
			Oficinas o = get("ONT-CAN"); 
		//	o.setTelefono("999999999");
		//	update(o);
			delete(o);
		}

}
