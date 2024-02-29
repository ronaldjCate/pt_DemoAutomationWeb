package demo.main.page;

import demo.main.util.PageObjectUtil;
import demo.main.xpath.XPathClasificacionAutos;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class AppClasificacionAutos extends PageObject {

// xpath
	protected XPathClasificacionAutos xpathClasificacionAutos = XPathClasificacionAutos.getInstancia();

// util
	protected PageObjectUtil pageObjectUtil = PageObjectUtil.getInstancia();

	public void selecciono_marca_modelo(String marca, String modelo) {
		Serenity.takeScreenshot();
		pageObjectUtil.seleniumClickinCellTable(getDriver(), xpathClasificacionAutos.tblMarcaModelo, marca, modelo, "Comments");
	}

}
