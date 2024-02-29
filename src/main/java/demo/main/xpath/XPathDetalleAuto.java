package demo.main.xpath;

public class XPathDetalleAuto {
	
	// singleton
	private static XPathDetalleAuto obj = null;

	private XPathDetalleAuto() {
	}

	public static XPathDetalleAuto getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new XPathDetalleAuto();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public String lblNombreModelo = "//*[@class='row']/h3";

}
