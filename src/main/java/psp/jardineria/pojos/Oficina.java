package psp.jardineria.pojos;

import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbPropertyOrder;

import psp.jardineria.modelo.Oficinas;
@JsonbPropertyOrder({"codigoOficina", "ciudad", "pais","region",
	"codigoPostal","telefono","lineaDireccion1","lineaDireccion2"})
public class Oficina implements java.io.Serializable {

	
	private String codigoOficina;
	private String ciudad;
	private String pais;
	private String region;
	private String codigoPostal;
	private String telefono;
	private String lineaDireccion1;
	private String lineaDireccion2;
	
	public void cargarOficinadesdeModelo(Oficinas ofi) {
		this.codigoOficina=ofi.getCodigoOficina();
		this.ciudad=ofi.getCiudad();
		this.pais=ofi.getPais();
		this.region=ofi.getRegion();
		this.telefono=ofi.getTelefono();
		this.codigoPostal=ofi.getCodigoPostal();
		this.lineaDireccion1=ofi.getLineaDireccion1();
		this.lineaDireccion2=ofi.getLineaDireccion2();
	}
	
	public Oficinas generarModeloDesdeOficina() {
		Oficinas ofi = new Oficinas();
		ofi.setCodigoOficina(this.codigoOficina);
		ofi.setCiudad(this.ciudad);
		ofi.setPais(this.pais);
		ofi.setRegion(this.region);
		ofi.setTelefono(this.telefono);
		ofi.setCodigoPostal(this.codigoPostal);
		ofi.setLineaDireccion1(this.lineaDireccion1);
		ofi.setLineaDireccion2(this.lineaDireccion2);
		return ofi;
	}
	
	public String getCodigoOficina() {
		return codigoOficina;
	}
	public void setCodigoOficina(String codigoOficina) {
		this.codigoOficina = codigoOficina;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	
	
	
	
	
}
