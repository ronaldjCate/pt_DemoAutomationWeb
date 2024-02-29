package demo.main.xpath;

public class XPathClasificacionAutos {
	
	// singleton
	private static XPathClasificacionAutos obj = null;

	private XPathClasificacionAutos() {
	}

	public static XPathClasificacionAutos getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new XPathClasificacionAutos();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public String tblMarcaModelo = "//*[@class='cars table table-hover']";
	
}
