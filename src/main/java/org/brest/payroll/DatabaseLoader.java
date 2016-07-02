package org.brest.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);
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
