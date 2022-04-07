package pdp.uz.appapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.uz.appapi.controller.ProductNotfoundException;
import pdp.uz.appapi.entity.Customer;
import pdp.uz.appapi.payload.ApiResponse;
import pdp.uz.appapi.payload.CustomerDto;
import pdp.uz.appapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;


    /**
     * Method return all Customers as List
     */
    public List getCustomers() {
        return customerRepository.findAll();
    }


    /**
     * Method return  Customer as Class
     */
    public Object getCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent() ) {
            throw new ProductNotfoundException();
        }
        return optionalCustomer.get();

        // You may also  use { return optionalCustomer.orElse(null); } method


    }


    /**
     * Add Customer
     *
     * @return Class{  String message ,Boolean success}
     */

    public ApiResponse addCustomer(CustomerDto customerDto) {
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber()))
            return new ApiResponse("Phone Number already exist", false);

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        return new ApiResponse("Customer saved successfully ", true);
    }


    /**
     * Update Customer by id
     *
     * @param id
     * @param customerDto
     * @return Class{ String message, Boolean success;}
     */
    public ApiResponse updateCustomer(int id, CustomerDto customerDto) {
        //Check update phone number of customer in repository except him or her
        if (customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id))
            return new ApiResponse("Phone Number already exist", false);
        //Check id of customer in repository
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent())
            return new ApiResponse("Customer not found", false);

        Customer customer = optionalCustomer.get();
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customerRepository.save(customer);

        return new ApiResponse("Customer updates successfully", true);
    }


    /**
     * Delete Customer by id
     *
     * @param id
     * @return Class{ String message, Boolean success;}
     */
    public ApiResponse deleteCustomer(Integer id) {
        //Check id of customer in repository
        if (!customerRepository.existsById(id))
            return new ApiResponse("Customer not found", false);
        else
            customerRepository.deleteById(id);
        return new ApiResponse("Customer deleted successfully", true);

    }


}
