package psp.jardineria.pojos;

import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbPropertyOrder;

import psp.jardineria.dao.EmpleadosDAO;
import psp.jardineria.dao.OficinasDAO;
import psp.jardineria.modelo.Empleados;

@JsonbPropertyOrder({"codigoEmpleado", "nombre", "apellido1","apellido2",
	"extension","email","puesto"})
public class Empleado implements java.io.Serializable {
	
	private int codigoEmpleado;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String extension;
	private String email;
	private String codigoOficina;
	private int codigoJefe; 
	private String puesto;

	
	public void cargarEmpleadodesdeModelo(Empleados emp) {
		this.codigoEmpleado=emp.getCodigoEmpleado();
		this.nombre=emp.getNombre();
		this.apellido1=emp.getApellido1();
		this.apellido2=emp.getApellido2();
		this.extension=emp.getExtension();
		this.email=emp.getEmail();
		this.codigoOficina=emp.getOficinas().getCodigoOficina();
		if (emp.getEmpleados() != null) {
			this.codigoJefe=emp.getEmpleados().getCodigoEmpleado();
		}
		
		this.puesto=emp.getPuesto();
	}
	
	public Empleados generarModeloDesdeEmpleado() {
		Empleados emp = new Empleados();
		emp.setCodigoEmpleado(this.codigoEmpleado);
		emp.setNombre(this.nombre);
		emp.setApellido1(this.apellido1);
		emp.setApellido2(this.apellido2);
		emp.setExtension(this.extension);
		emp.setEmail(this.email);
		OficinasDAO oficinasDAO = new OficinasDAO();
		emp.setOficinas(oficinasDAO.get(this.codigoOficina));
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		emp.setEmpleados(empleadosDAO.get(this.codigoJefe));
		emp.setPuesto(this.puesto);
		return emp;
	}

	public int getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(int codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	public String getCodigoOficina() {
		return codigoOficina;
	}

	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}

	public int getCodigoJefe() {
		return codigoJefe;
	}

	public void setCodigoJefe(int codigoJefe) {
		this.codigoJefe = codigoJefe;
	}
	
}

