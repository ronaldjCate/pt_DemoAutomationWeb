package demo.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import com.ibm.icu.impl.InvalidFormatException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;




public class ExcelUtil {
	
//    static XSSFWorkbook wrkbook;
//    static XSSFSheet wrksheet;
    static Hashtable dict = new Hashtable();
    static Hashtable dictData = new Hashtable();
    static Hashtable dictDataRow = new Hashtable();
    static int columna = 0;
    static String archivo = "";
    static Workbook workbook;
    static Sheet sheet; 

	public void cerrarIn(FileInputStream fileInputStream) throws Exception {
		if (fileInputStream != null) {
			fileInputStream.close();
		}
	}
	


	public void cerrarWb(Workbook workbook) throws Exception {
		if (workbook != null) {
			workbook.close();
		}
	}

	public void cerrarOut(FileOutputStream fileOutputStream) throws Exception {
		if (fileOutputStream != null) {
			fileOutputStream.close();
		}
	}
	
	public void cerrarWb2() throws Exception {
	
			workbook.close();
	
	}

	public void copiarPlantillas() {
		System.out.println("Entrando a Copiar Plantilla...");
		File file = new File(Constantes.rutaPlantillas);
		String[] archivos = file.list();

		// verificar si existe directorio
		File rutaDestino = new File(Constantes.ruta);

		if (!rutaDestino.exists()) {
			System.out.println("creando directorio local para los data driven");
			rutaDestino.mkdir();
		}

		for (int i = 0; i < archivos.length; i++) {
			System.out.println(archivos[i]);
			copiarArchivo(archivos[i], Constantes.rutaPlantillas, Constantes.ruta);
		
		}
	}

	private void copiarArchivo(String archivo, String rutaOrigen, String rutaDestino) {
		Path origenPath = FileSystems.getDefault().getPath(rutaOrigen + archivo);
		Path destinoPath = FileSystems.getDefault().getPath(rutaDestino + archivo);

		try {
			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	//se cargan todos los archivos features
	public void escribirFeatures() {
		List<String> features = listaFeature(Constantes.featRuta);

		for (String feature : features) {
			inExcelOutFeature(feature);
		}
	}

	private List<String> listaFeature(String directorio) {
		List<String> features = new ArrayList<String>();
		File folder = new File(directorio);

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				features.addAll(listaFeature(fileEntry.getPath()));
			} else {
				if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
					features.add(fileEntry.getPath());
				}
			}
		}

		return features;
	}

	private void inExcelOutFeature(String feature) {
		List<String> featureCompleto = new ArrayList<String>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String lineaFeature = "";
		String[] lineaSplit = null;
		String excel = "";
		String hoja = "";
		String vez = "0";
		List<List<String>> excelData = null;

		try {
			fileReader = new FileReader(feature);
			bufferedReader = new BufferedReader(fileReader);

			while ((lineaFeature = bufferedReader.readLine()) != null) {
				// System.out.println(lineaFeature);
				featureCompleto.add(lineaFeature);

				if (lineaFeature.trim().contains(Constantes.featLlave)) {
					lineaSplit = lineaFeature.trim().split("@");
					System.out.println("++++ " + lineaSplit[0] +" ++++ "+lineaSplit[1]+ " ++++ " +  lineaSplit[2] + " longitud " + lineaSplit.length);
					excel = lineaSplit[1];
					hoja = lineaSplit[2];
					if (lineaSplit.length == 4) {
						vez = lineaSplit[3];
					}

					excelData = excelToList(excel, hoja, vez);

					vez = "0";
					lineaFeature = bufferedReader.readLine();
					featureCompleto.add(lineaFeature);

					for (List<String> lista : excelData) {
						String fila = "      |";

						for (String string : lista) {
							string = string.replace("|", "\\|");
							fila += string + "|";
						}

//						System.out.println(fila);
						featureCompleto.add(fila);
					}

					while ((lineaFeature = bufferedReader.readLine()) != null) {
						if (lineaFeature.trim().startsWith("@")) {
							featureCompleto.add("");
							featureCompleto.add(lineaFeature);
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		escribirFeature(feature, featureCompleto);
	}

	public List<List<String>> excelToList(String excel, String hoja, String vez) {
		System.out.println("CONVERTIR EXCEL A LISTA");
		return new ArrayList<List<String>>();
	}

	private void escribirFeature(String feature, List<String> lista) {
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(feature);

			for (String string : lista) {
				fileWriter.write(string + "\n");
			}

			fileWriter.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	public  void inciarExcel(String ExcelSheetPath, String sheetName) throws IOException, InvalidFormatException {
		
   try {
       //Initialize
//    	  System.out.println("############" + ExcelSheetPath);
//    	  System.out.println("############" + sheetName);
    		File file = new File(ExcelSheetPath);
    		archivo = ExcelSheetPath;
    		FileInputStream fileInputStream = new FileInputStream(file);
    		workbook = WorkbookFactory.create(fileInputStream);
    		sheet = !sheetName.isEmpty() ? workbook.getSheet(sheetName) : workbook.getSheetAt(0);


          //Call the Column Dictionary to store column Names
//          ColumnDictionary();
//          SheetDictionary();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

//    public static String ReadCell(int column, int row) {
//        Cell celda = sheet.getRow(row).getCell(column);
//        String valor;
//        switch (celda.getCellTypeEnum()) {
//            case STRING:
//                return String.valueOf(celda.getStringCellValue());
//            case NUMERIC:
//                valor = celda.getNumericCellValue()+"";
//                return valor;
//            case BOOLEAN:
//                valor = celda.getBooleanCellValue()+"";
//                return valor;
//            case BLANK:
//                return "";
//            default:
//                return null;
//        }
//    }


    
    //Read Column Names
    private static int GetCell(String colName) {
        try {
            int value;
            value = ((Integer) dict.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }

    
    public static String WriteCell(int column, int row, String value) throws IOException {

    	sheet.getRow(row).createCell(column);
    	sheet.getRow(row).getCell(column).setCellValue(value);
    	sheet.getRow(row).getCell(column).setCellType(CellType.STRING);
        System.out.print("\n Celda: " + sheet.getRow(row).getCell(column).getStringCellValue().toString() + "\n");
//        System.out.print("\n *****");
        
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }
    
    public static String WriteCellName(String cabecera, int row, String value) throws IOException {
    	int column = buscarCabecera(cabecera);
    	sheet.getRow(row).createCell(column);
    	sheet.getRow(row).getCell(column).setCellValue(value);
    	sheet.getRow(row).getCell(column).setCellType(CellType.STRING);
        System.out.print("\n Celda: " + sheet.getRow(row).getCell(column).getStringCellValue().toString() + "\n");
//        System.out.print("\n *****");
        
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }
    
    public static int buscarCabecera(String nomCabecera) {
    	int numColumna = 0;
    	
    	for(int i=0;i<sheet.getRow(0).getLastCellNum();i++) {
    		
    		if(sheet.getRow(0).getCell(i).getStringCellValue().compareTo(nomCabecera)==0){
				break;
			}else {
				numColumna++;
			}
    		
    	}
    	
    	return numColumna;
    }

	
//    //Create Column Dictionary to hold all the Column Names
//    private static void ColumnDictionary() {
//        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
//        for (int col = 0; col < sheet.getRow(0).getLastCellNum(); col++) {
//            dict.put(ReadCell(col, 0), col);
//            String diccionario = dict.put(ReadCell(col, 0), col).toString();
//            columna = Integer.parseInt(dict.put(ReadCell(col, 0), col).toString());
//
//        }
//    }
//    
//    private static void SheetDictionary() {
//        //Iterate through all the rows in the Excel sheet and store the value in Hashtable
////        System.out.println("Total rows of excel found " + wrksheet.getLastRowNum() + "\n");
////        System.out.println("Total columnas of excel found " + wrksheet.getRow(1).getLastCellNum());
//
//        for (int row = 0; row < sheet.getLastRowNum(); row++) {
//            if (row != 0) {
//                for (int col = 0; col < sheet.getRow(row).getLastCellNum(); col++) {
////                    System.out.println("-------------" + ReadCell(col, 0) + "   " + ReadCell(col, row));
//                    dictData.put(ReadCell(col, 0), ReadCell(col, row));
//                }
//                dictDataRow.put(ReadCell(0, row) + "-" + ReadCell(1, row), dictData);
//                dictData = new Hashtable();
//
//            }
//        }
//    }
    
    public static void CloseExcel() throws IOException {
        FileOutputStream Fos = new FileOutputStream(archivo);
        workbook.write(Fos);
        workbook.close();
        Fos.close();
    }
    
    public static int buscar_valor(int columna, String valor) {
    	
    	Iterator<Row> rowIterator = sheet.iterator();
		Row row;
		int fila=1;
		// se recorre cada fila hasta el final
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			//se obtiene las celdas por fila
//			System.out.println(sheet.getRow(fila).getCell(columna).getStringCellValue() + " VS " +valor);
			
			try {
			if(sheet.getRow(fila).getCell(columna).getStringCellValue().compareTo(valor)==0){
				break;
			}else {
				fila++;
			}
			}catch (Exception e) {
				System.out.println(e.getMessage());
				fila=0;
			}
				

		}
		return(fila);
	
}
    
    public String absolutPath(NtlmPasswordAuthentication auth,String path) {
    	String ruta="";
	    SmbFile dir=null;
		File file = null;
    	try {
			dir = new SmbFile(path, auth);
			file = new File(dir.getUncPath());
			System.out.println("*********** " + file.getAbsolutePath());
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	return file.getAbsolutePath();
    }
}
