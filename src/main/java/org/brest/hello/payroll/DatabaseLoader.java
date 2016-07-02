package org.brest.hello.payroll;

import org.brest.hello.GreetingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DatabaseLoader implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private final EmployeeRepository repository;

    @Autowired
    public DatabaseLoader(EmployeeRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void run(String... strings) throws Exception {
        log.info("Loading initial data");
        this.repository.save(new Employee("Frodo", "Baggis", "Rig bearer"));
    }
}
