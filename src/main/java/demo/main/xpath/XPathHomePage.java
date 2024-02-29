package demo.main.xpath;

public class XPathHomePage {
	
	// singleton
	private static XPathHomePage obj = null;

	private XPathHomePage() {
	}

	public static XPathHomePage getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new XPathHomePage();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public String imgClasificacion(String nombreClasificacion) {
		String xpathIMGClasificacion = "//*[@class='card-header text-xs-center' and text()='"+nombreClasificacion+"']/following-sibling::a";
		return xpathIMGClasificacion;
	}

}
