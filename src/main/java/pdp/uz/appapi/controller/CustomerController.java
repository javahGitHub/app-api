package pdp.uz.appapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appapi.entity.Customer;
import pdp.uz.appapi.payload.ApiResponse;
import pdp.uz.appapi.payload.CustomerDto;
import pdp.uz.appapi.service.CustomerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    /**
     * Read all Customers
     *
     * @return all Customers as List
     */
    @GetMapping()
    public List getCustomers() {
        return customerService.getCustomers();
    }


    /**
     * Read Customer by id
     *
     * @return Customer class
     */
    @GetMapping("{id}")
    public Object getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }


    /**
     * Add Customer
     *
     * @return Class{  String message ,Boolean success}
     */

    @PostMapping
    public ApiResponse addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    /**
     * Update Customer by id
     * @param id
     * @param customerDto
     * @return Class{ String message, Boolean success;}
     */
    @PostMapping("/update/{id}")
    public ApiResponse updateCustomer(@PathVariable int id,@Valid @RequestBody CustomerDto customerDto){
        return customerService.updateCustomer(id,customerDto);
    }


    /**
     * Delete Customer by id
     * @param id
     * @return Class{ String message, Boolean success;}
     */
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCustomer(@PathVariable Integer id){
        return customerService.deleteCustomer(id);
    }


    /**
     *
     * This sent message
     *
     * @param ex
     * @return String message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
