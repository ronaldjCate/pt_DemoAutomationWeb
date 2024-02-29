package demo.main.page;

import demo.main.util.PageObjectUtil;
import demo.main.xpath.XPathDetalleAuto;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class AppDetalleAuto extends PageObject {

// xpath
	protected XPathDetalleAuto xpathDetalleAuto = XPathDetalleAuto.getInstancia();

// util
	protected PageObjectUtil pageObjectUtil = PageObjectUtil.getInstancia();

	public String getNombreModelo() {
		Serenity.takeScreenshot();
		String modelo = pageObjectUtil.seleniumGetText(getDriver(), xpathDetalleAuto.lblNombreModelo, 0);
		return modelo;
	}

}
