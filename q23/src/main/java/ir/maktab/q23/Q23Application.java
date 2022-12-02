package ir.maktab.q23;

import ir.maktab.q23.entity.Person;
import ir.maktab.q23.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Q23Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Q23Application.class, args);

		PersonService personService = context.getBean(PersonService.class);

		/*Optional<List<Person>> personList = personService.findByFirstNameAndLastName("Arash","Mohamadi");
		personList.ifPresent(System.out::println);*/
	}
}