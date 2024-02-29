package demo.test.util;

//import rimac.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;

import demo.test.inout.LeerDD_Reembolso;

public class ExcelUtilPropio extends ExcelUtil {

	// singleton
	private static ExcelUtilPropio obj = null;

	private ExcelUtilPropio() {
	}

	public static ExcelUtilPropio getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new ExcelUtilPropio();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	// singleton

	public List<List<String>> excelToList(String excel, String hoja, String vez) {
		List<List<String>> data = new ArrayList<List<String>>();
//		System.out.println("excel: " + excel + " - hoja: " + hoja + " VEZ: " + vez  );
		try {
			String[] excelSplit = excel.split("\\|");
			// int v = Integer.parseInt(vez);

//			System.out.println(" valor 1: " + excelSplit[0] + " valor 2: " + excelSplit[1] + " LONGITUD: " + excelSplit.length  );

			switch (excelSplit[0]) {
				case "DataUrbania":
					if(excelSplit[1].compareTo("U")==0) {
						data = LeerDD_Reembolso.getInstancia().leerUltimaFilaDD(hoja);
					}else {
						data = LeerDD_Reembolso.getInstancia().leerDD(hoja);
					}
					break;
				default:
					break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		return data;
	}
}
