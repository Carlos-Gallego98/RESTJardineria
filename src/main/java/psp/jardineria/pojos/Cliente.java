package psp.jardineria.pojos;

import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbPropertyOrder;
import psp.jardineria.modelo.Clientes;

@JsonbPropertyOrder({"codigoCliente", "nombreCliente", "nombreContacto","apellidoContacto",
	"telefono","fax","lineaDireccion1","lineaDireccion2","ciudad","region",
	"pais","codigoPostal","limiteCredito"})
public class Cliente implements java.io.Serializable {
	
	private int codigoCliente;
	private String nombreCliente;
	private String nombreContacto;
	private String apellidoContacto;
	private String telefono;
	private String fax;
	private String lineaDireccion1;
	private String lineaDireccion2;
	private String ciudad;
	private String region;
	private String pais;
	private String codigoPostal;
//	private int codigoEmpleadoRepVentas;
	private BigDecimal limiteCredito;
	
	public void cargarClientedesdeModelo(Clientes cli) {
		this.codigoCliente=cli.getCodigoCliente();
		this.nombreCliente=cli.getNombreCliente();
		this.nombreContacto=cli.getNombreContacto();
		this.apellidoContacto=cli.getApellidoContacto();
		this.telefono=cli.getTelefono();
		this.fax=cli.getFax();
		this.lineaDireccion1=cli.getLineaDireccion1();
		this.lineaDireccion2=cli.getLineaDireccion2();
		this.ciudad=cli.getCiudad();
		this.region=cli.getRegion();
		this.pais=cli.getPais();
		this.codigoPostal=cli.getCodigoPostal();
//		private int codigoEmpleadoRepVentas;
		this.limiteCredito=cli.getLimiteCredito();
		
	}
	
	public Clientes generarModeloDesdeCliente() {
		Clientes cli = new Clientes();
		cli.setCodigoCliente(this.codigoCliente);
		cli.setNombreCliente(this.nombreCliente);
		cli.setNombreContacto(this.nombreContacto);
		cli.setApellidoContacto(this.apellidoContacto);
		cli.setTelefono(this.telefono);
		cli.setFax(this.fax);
		cli.setLineaDireccion1(this.lineaDireccion1);
		cli.setLineaDireccion2(this.lineaDireccion2);
		cli.setCiudad(this.ciudad);
		cli.setRegion(this.region);
		cli.setPais(this.pais);
		cli.setCodigoPostal(this.codigoPostal);
		cli.setLimiteCredito(this.limiteCredito);
		return cli;
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getApellidoContacto() {
		return apellidoContacto;
	}
	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLineaDireccion1() {
		return lineaDireccion1;
	}
	public void setLineaDireccion1(String lineaDireccion1) {
		this.lineaDireccion1 = lineaDireccion1;
	}
	public String getLineaDireccion2() {
		return lineaDireccion2;
	}
	public void setLineaDireccion2(String lineaDireccion2) {
		this.lineaDireccion2 = lineaDireccion2;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	
}

