package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addCategories();

        addCustomers();

    }

    private void addCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Data Loaded = " + categoryRespository.count() );
    }

    private void addCustomers(){

        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Newman");

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Lachappele");

        Customer david = new Customer();
        david.setFirstName("David");
        david.setLastName("Winter");

        Customer anne = new Customer();
        anne.setFirstName("Anne");
        anne.setLastName("Hine");

        Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Eastman");

        customerRepository.save(joe);
        customerRepository.save(michael);
        customerRepository.save(david);
        customerRepository.save(anne);
        customerRepository.save(alice);

        System.out.println("Customers data loaded = " + customerRepository.count());
    }
}
