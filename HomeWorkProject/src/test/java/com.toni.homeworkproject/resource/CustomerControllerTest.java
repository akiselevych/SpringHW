package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.response.CustomerResponseDto;
import com.toni.homeworkproject.facade.customer.CustomerRequestMapper;
import com.toni.homeworkproject.facade.customer.CustomerResponseMapper;
import com.toni.homeworkproject.service.CustomerService;
import com.toni.homeworkproject.service.DefaultService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomersController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultService<Customer> customerService;
    @MockBean
    private DefaultService<Account> accountService;
    @MockBean
    private DefaultService<Employer> employerService;
    @MockBean
    private CustomerResponseMapper customerResponseMapper;
    @MockBean
    private CustomerRequestMapper customerRequestMapper;


    @Test
    public void findAllPageableAndSortableWithQuantityTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(3L);
        Customer customer3 = new Customer();
        customer3.setId(5L);
        Customer customer4 = new Customer();
        customer4.setId(2L);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(1L);
        CustomerResponseDto customerResponseDto2 = new CustomerResponseDto();
        customerResponseDto2.setId(3L);
        CustomerResponseDto customerResponseDto3 = new CustomerResponseDto();
        customerResponseDto3.setId(5L);
        CustomerResponseDto customerResponseDto4 = new CustomerResponseDto();
        customerResponseDto4.setId(2L);

        List<Customer> customers = List.of(customer,customer4,customer2,customer3);

        when(customerService.findAll(0,4)).thenReturn(customers);
        when(customerResponseMapper.convertToDto(customer)).thenReturn(customerResponseDto);
        when(customerResponseMapper.convertToDto(customer2)).thenReturn(customerResponseDto2);
        when(customerResponseMapper.convertToDto(customer3)).thenReturn(customerResponseDto3);
        when(customerResponseMapper.convertToDto(customer4)).thenReturn(customerResponseDto4);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers").contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("quantity", "4"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id", Matchers.is(5)));
    }

    @Test
    public void findAllPageableAndSortableWithoutQuantityTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(3L);
        Customer customer3 = new Customer();
        customer3.setId(5L);
        Customer customer4 = new Customer();
        customer4.setId(2L);
        Customer customer5 = new Customer();
        customer.setId(6L);
        Customer customer6 = new Customer();
        customer2.setId(7L);
        Customer customer7 = new Customer();
        customer3.setId(8L);
        Customer customer8 = new Customer();
        customer4.setId(9L);
        Customer customer9 = new Customer();
        customer.setId(10L);
        Customer customer10 = new Customer();
        customer2.setId(11L);
        Customer customer11 = new Customer();
        customer3.setId(12L);
        Customer customer12 = new Customer();
        customer4.setId(13L);





        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(1L);
        CustomerResponseDto customerResponseDto2 = new CustomerResponseDto();
        customerResponseDto2.setId(3L);
        CustomerResponseDto customerResponseDto3 = new CustomerResponseDto();
        customerResponseDto3.setId(5L);
        CustomerResponseDto customerResponseDto4 = new CustomerResponseDto();
        customerResponseDto4.setId(2L);
        CustomerResponseDto customerResponseDto5 = new CustomerResponseDto();
        customerResponseDto.setId(6L);
        CustomerResponseDto customerResponseDto6 = new CustomerResponseDto();
        customerResponseDto2.setId(7L);
        CustomerResponseDto customerResponseDto7 = new CustomerResponseDto();
        customerResponseDto3.setId(8L);
        CustomerResponseDto customerResponseDto8 = new CustomerResponseDto();
        customerResponseDto4.setId(9L);
        CustomerResponseDto customerResponseDto9 = new CustomerResponseDto();
        customerResponseDto.setId(10L);
        CustomerResponseDto customerResponseDto10 = new CustomerResponseDto();
        customerResponseDto2.setId(11L);
        CustomerResponseDto customerResponseDto11 = new CustomerResponseDto();
        customerResponseDto3.setId(12L);
        CustomerResponseDto customerResponseDto12 = new CustomerResponseDto();
        customerResponseDto4.setId(13L);



        List<Customer> customers = List.of(customer,customer4,customer2,customer3,customer5,customer6,customer7,customer8,customer9,customer10);

        when(customerService.findAll(0,10)).thenReturn(customers);
        when(customerResponseMapper.convertToDto(customer)).thenReturn(customerResponseDto);
        when(customerResponseMapper.convertToDto(customer2)).thenReturn(customerResponseDto2);
        when(customerResponseMapper.convertToDto(customer3)).thenReturn(customerResponseDto3);
        when(customerResponseMapper.convertToDto(customer4)).thenReturn(customerResponseDto4);
        when(customerResponseMapper.convertToDto(customer5)).thenReturn(customerResponseDto5);
        when(customerResponseMapper.convertToDto(customer6)).thenReturn(customerResponseDto6);
        when(customerResponseMapper.convertToDto(customer7)).thenReturn(customerResponseDto7);
        when(customerResponseMapper.convertToDto(customer8)).thenReturn(customerResponseDto8);
        when(customerResponseMapper.convertToDto(customer9)).thenReturn(customerResponseDto9);
        when(customerResponseMapper.convertToDto(customer10)).thenReturn(customerResponseDto10);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers").contentType(MediaType.APPLICATION_JSON)
                        .param("page","0"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)));
    }

    @Test
    public void findCustomerByExistingId() throws Exception {
        Customer customer = new Customer();
        customer.setId(4L);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(4L);

        when(customerService.findById(4L)).thenReturn(Optional.of(customer));
        when(customerResponseMapper.convertToDto(customer)).thenReturn(customerResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)));
    }

    @Test
    public void findCustomerByNotExistingId() throws Exception {
        when(customerService.findById(4L)).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
