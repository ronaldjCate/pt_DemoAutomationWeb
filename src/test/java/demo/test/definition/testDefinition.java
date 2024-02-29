package demo.test.definition;

import static org.junit.Assert.assertEquals;

import demo.main.step.ModeloAutoStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class testDefinition {

	@Steps
	ModeloAutoStep modeloAutoStep;

	@Given("Ingreso a la web de demotest")
	public void ingreso_a_la_web_de_demotest() {
		modeloAutoStep.ingresoDemoTest();
	}

	@When("selecciono la clasificacion {string}")
	public void selecciono_la_clasificacion(String nombreClasificacion) {
		modeloAutoStep.selecciono_clasificacion(nombreClasificacion);
	}

	@When("selecciono la marca {string} y modelo {string} del auto")
	public void selecciono_la_marca_y_modelo_del_auto(String marca, String modelo) {
		modeloAutoStep.selecciono_marca_modelo(marca, modelo);
	}

	@Then("debe de tener el nombre de modelo {string}")
	public void debe_de_tener_el_nombre_de_modelo(String modeloEsperado) {
		assertEquals(modeloAutoStep.getNombreModelo(), modeloEsperado);
	}
}
