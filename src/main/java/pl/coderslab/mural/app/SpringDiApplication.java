package pl.coderslab.mural.app;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

public class SpringDiApplication {

	public static void main(String[] args) {

		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("muralePersistenceUnit");
		
		System.out.println("Dzia³a");

	}

}
