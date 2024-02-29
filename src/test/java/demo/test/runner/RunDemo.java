package demo.test.runner;

import org.junit.runner.RunWith;

import demo.test.util.ExcelUtilPropio;
import io.cucumber.junit.CucumberOptions;

@RunWith(RunPersonalizar.class)

//@CucumberOptions(features = { "src/test/resources/features/" }, tags = "@getNombreModeloAuto", glue = { "demo" }, plugin = {
//"pretty", "html:target/serenity-reports/serenity-html-report",
//"json:target/serenity-reports/cucumber_report.json", "rerun:target/serenity-reports/rerun.txt" })

public class RunDemo {

	@RunBefore
	public static void previo() {
//		ExcelUtilPropio.getInstancia().escribirFeatures();
		System.out.println("ss1");
		
	}
}
