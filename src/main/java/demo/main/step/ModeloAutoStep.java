package demo.main.step;

import demo.main.page.AppClasificacionAutos;
import demo.main.page.AppDetalleAuto;
import demo.main.page.AppHomepage;

public class ModeloAutoStep {

	private AppHomepage appHomePage;
	private AppClasificacionAutos appClasificacionAutos;
	private AppDetalleAuto appDetalleAuto;
	
	public void ingresoDemoTest() {
		appHomePage.inicializar("https://buggy.justtestit.org/");
	}

	public void selecciono_clasificacion(String nombreClasificacion) {
		appHomePage.selecciono_clasificacion(nombreClasificacion);
	}

	public void selecciono_marca_modelo(String marca, String modelo) {
		appClasificacionAutos.selecciono_marca_modelo(marca, modelo);
	}

	public Object getNombreModelo() {
		return appDetalleAuto.getNombreModelo();
	}


}
