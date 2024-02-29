package demo.test.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import net.serenitybdd.cucumber.CucumberWithSerenity;

public class RunPersonalizar extends Runner {

	private CucumberWithSerenity cucumberWithSerenity;
	private Class<CucumberWithSerenity> classValue;

	public RunPersonalizar(Class<CucumberWithSerenity> classValue) throws Exception {
		this.classValue = classValue;
		cucumberWithSerenity = new CucumberWithSerenity(classValue);
	}

	@Override
	public Description getDescription() {
		return cucumberWithSerenity.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		try {
			runAnnotatedMethods(RunBefore.class);
			cucumberWithSerenity = new CucumberWithSerenity(classValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cucumberWithSerenity.run(notifier);
	}

	private void runAnnotatedMethods(Class<?> annotation) throws Exception {
		if (!annotation.isAnnotation()) {
			return;
		}

		Method[] methods = this.classValue.getMethods();

		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation item : annotations) {
				if (item.annotationType().equals(annotation)) {
					method.invoke(null);
					break;
				}
			}
		}
	}
}
