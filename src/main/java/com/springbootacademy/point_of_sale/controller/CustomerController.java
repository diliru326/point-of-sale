package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.CustomerDTO;
import com.springbootacademy.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springbootacademy.point_of_sale.service.CustomerService;
import com.springbootacademy.point_of_sale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @PostMapping("/save")
//    public String saveCustomer(@RequestBody CustomerDTO customerDTO) {
//        customerService.saveCustomer(customerDTO);
//        return "saved";
//    }

    //below is the use of response entity for the above method

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO) {
       String messege =  customerService.saveCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"saved sucessfully",messege),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String messege = customerService.updateCustomer(customerUpdateDTO);
        return messege;
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id")int customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

//    @GetMapping(path = "/get-all-customers")
//    public List<CustomerDTO> getAllCustomers() {
//        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
//        return customerDTOList;
//    }

    @GetMapping(path = "/get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(new StandardResponse(
                201,"All customers found",customerDTOList),
                 HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id")int customerId) {
        String deleteCustomer = customerService.deleteCustomer(customerId);

        return deleteCustomer;
    }

    @GetMapping(path = "/get-all-customers-by-active-state/{status}")

    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status") boolean active) {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomersByActiveState(active);
        return customerDTOList;
    }
}




