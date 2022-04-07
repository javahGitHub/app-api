package pdp.uz.appapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/customer2")
public class CustomerController_2 {

    @Autowired
    CustomerService customerService;


    /**
     * Read all Customers
     *
     * @return all Customers as List
     */
    @GetMapping()
    public ResponseEntity<List> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }


    /**
     * Read Customer by id
     *
     * @return Customer class
     */
    @GetMapping("/{id}")
    public HttpEntity<Object> getCustomer(@PathVariable Integer id) {
        return ResponseEntity.ok( customerService.getCustomer(id));
    }


    /**
     * Add Customer
     *
     * @return Class{  String message ,Boolean success}
     */

    @PostMapping
    public HttpEntity< ApiResponse> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = customerService.addCustomer( customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * Update Customer by id
     * @param id
     * @param customerDto
     * @return Class{ String message, Boolean success;}
     */
    @PostMapping("/update/{id}")
    public ResponseEntity< ApiResponse >updateCustomer(@PathVariable int id,@Valid @RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);

    }


    /**
     * Delete Customer by id
     * @param id
     * @return Class{ String message, Boolean success;}
     */
    @DeleteMapping("/delete/{id}")
    public HttpEntity<ApiResponse> deleteCustomer(@PathVariable Integer id){
        ApiResponse apiResponse = customerService.deleteCustomer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
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
