package com.springbootacademy.point_of_sale.service.impl;

import com.springbootacademy.point_of_sale.dto.CustomerDTO;
import com.springbootacademy.point_of_sale.dto.request.CustomerUpdateDTO;
import com.springbootacademy.point_of_sale.entity.Customer;
import com.springbootacademy.point_of_sale.exception.NotFoundException;
import com.springbootacademy.point_of_sale.repo.CustomerRepo;
import com.springbootacademy.point_of_sale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumbers(),
                customerDTO.getNic(),
                customerDTO.getActive()
        );
        customerRepo.save(customer);
        return "Customer Saved";
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            customerRepo.save(customer);
            return customerUpdateDTO.getCustomerName() + " Updated";
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.getActive()
            );
            return customerDTO;
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        if(customerList.size() > 0) {
            for (Customer customer : customerList) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomerSalary(),
                        customer.getContactNumbers(),
                        customer.getNic(),
                        customer.getActive()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }else {
            throw new NotFoundException("No customer found");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);
            return "deleted successfully" + customerId;
        } else {
            throw new RuntimeException("Customer Not Found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean active) {
        List<Customer> customerList = customerRepo.findAllByActiveEquals(active);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumbers(),
                    customer.getNic(),
                    customer.getActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}











