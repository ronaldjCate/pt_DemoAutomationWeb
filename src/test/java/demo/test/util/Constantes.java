package demo.test.util;

import demo.main.util.UtilDef;

public class Constantes {

//	data driven y otros archivos
	public static final String usar = "X";
	private static final String res = "src/test/resources/dataDriven/";
	public static final String rutaPlantillas = res + "0_plantillas/";
	public static final String ruta = res + System.getProperty("user.name") + "/";

//	features
	public static final String featRuta = "src/test/resources/features/";
	public static final String featLlave = "###DATOS###@";

	public static String dominioDP = UtilDef.getInstancia().getVarEnvPro("dominioDP"); 
	public static String usuarioDP = UtilDef.getInstancia().getVarEnvPro("usuarioDP");
	public static String passwordDP = UtilDef.getInstancia().getVarEnvPro("passwordDP");
	
	public static final String UsarRemota = "NO";
	
	public static final String RutaLocal = "/Users/christianmaurysolis/git/Demo-auto-test-v1/src/test/resources/dataDriven/";
	public static final String RutaRemota = "smb://01-045923.rimac.com.pe//DataPuebaAutomatizacion//Proy_CanalAPP_iOS//datapool//";

//	public static final String fileData_01 = (UsarRemota.compareTo("NO")==0 ? RutaLocal+"DataReembolso.xlsx" : RutaRemota+"DataReembolso.xlsx");
	public static final String fileData_01 = (UsarRemota.compareTo("NO")==0 ? RutaLocal+"DataUrbania.xlsx" : RutaRemota+"DataUrbania.xlsx");
	

}
