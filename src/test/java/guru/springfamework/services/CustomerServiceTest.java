package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final String FIRST_NAME = "Ronaldo";
    public static final String LAST_NAME = "Silva";
    public static final Long ID = 1L;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCostumers() throws Exception{

        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer(), new Customer(),
                new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(5, customerDTOS.size());

    }

    @Test
    public void getCustomerById() throws Exception{

        //given
        Customer ronaldo = new Customer();
        ronaldo.setFirstName(FIRST_NAME);
        ronaldo.setLastName(LAST_NAME);
        ronaldo.setId(ID);

        Optional<Customer> customerOptional = Optional.of(ronaldo);

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        //then
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(ID, customerDTO.getId());
    }
}
