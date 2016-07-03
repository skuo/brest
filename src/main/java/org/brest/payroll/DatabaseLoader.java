package org.brest.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

	private final EmployeeRepository employees;
	private final ManagerRepository managers;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository,
						  ManagerRepository managerRepository) {

		this.employees = employeeRepository;
		this.managers = managerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
	    log.info("Loading initial data");
	    
		Manager steve = this.managers.save(new Manager("steve", "kuo",
							"ROLE_MANAGER"));
		Manager ted = this.managers.save(new Manager("ted", "bear",
							"ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("steve", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("Frodo", "Baggins", "ring bearer", steve));
		this.employees.save(new Employee("Bilbo", "Baggins", "burglar", steve));
		this.employees.save(new Employee("Gandalf", "the Grey", "wizard", steve));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("ted", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("Samwise", "Gamgee", "gardener", ted));
		this.employees.save(new Employee("Merry", "Brandybuck", "pony rider", ted));
		this.employees.save(new Employee("Peregrin", "Took", "pipe smoker", ted));

		SecurityContextHolder.clearContext();
	}
}
