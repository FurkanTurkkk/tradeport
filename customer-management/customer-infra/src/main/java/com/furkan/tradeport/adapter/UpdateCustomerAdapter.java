package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaAddressEntity;
import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.exception.AddressEntityAlreadyExistException;
import com.furkan.tradeport.exception.CustomerEntityNotFoundException;
import com.furkan.tradeport.exception.IdNumberAlreadyExistException;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.persistence.SpringDataAddressRepository;
import com.furkan.tradeport.persistence.SpringDataCustomerRepository;
import com.furkan.tradeport.port.UpdateCustomerPort;
import com.furkan.tradeport.valueobject.Address;
import com.furkan.tradeport.valueobject.CustomerId;
import com.furkan.tradeport.valueobject.FullName;
import com.furkan.tradeport.valueobject.IdNumber;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerPort {
    private final SpringDataCustomerRepository customerRepository;
    private final AddressService addressService;

    public UpdateCustomerAdapter(SpringDataCustomerRepository customerRepository,
                                 AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String customerId = customer.getCustomerId().asString();
        JpaCustomerEntity customerEntity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerEntityNotFoundException("Customer not found in infra layer"));

        updateCustomerEntity(customerEntity, customer);
        JpaCustomerEntity saved = customerRepository.save(customerEntity);

        return toDomain(saved);
    }

    private void updateCustomerEntity(JpaCustomerEntity customerEntity, Customer updatedCustomer) {

        if (updatedCustomer.getFullname() != null) {
            String newFirstname = updatedCustomer.getFullname().firstName();
            String newLastname = updatedCustomer.getFullname().lastName();
            customerEntity.setFirstname(newFirstname != null ? newFirstname : customerEntity.getFirstname());
            customerEntity.setLastname(newLastname != null ? newLastname : customerEntity.getLastname());
        }

        if (updatedCustomer.getIdNumber() != null) {
            handleIdNumberUpdate(customerEntity, updatedCustomer);
        }

        if (updatedCustomer.getAddress() != null) {
            handleAddressUpdate(customerEntity, updatedCustomer);
        }
    }

    private void handleIdNumberUpdate(JpaCustomerEntity customerEntity, Customer updatedCustomer) {
        String newIdNumber = updatedCustomer.getIdNumber().asString();
        Optional<JpaCustomerEntity> customer = customerRepository.findByIdNumber(newIdNumber);
        if(customer.isPresent()) {
            throw new IdNumberAlreadyExistException("Bu kimlik numarası başka bir müşteriye ait");
        }
        customerEntity.setIdNumber(newIdNumber);
    }

    private void handleAddressUpdate(JpaCustomerEntity customerEntity, Customer updatedCustomer) {
        Address newAddress = updatedCustomer.getAddress();
        JpaAddressEntity currentAddress = customerEntity.getAddress();

        if (currentAddress != null &&
                newAddress.equals(new Address(
                        currentAddress.getCountry(),
                        currentAddress.getCity(),
                        currentAddress.getDistrict(),
                        currentAddress.getStreet(),
                        currentAddress.getApartmentNumber(),
                        currentAddress.getDoorNumber()
                ))) {
            return;
        }

        Optional<JpaAddressEntity> existingAddress = addressService.findByAllFields(newAddress);
        if (existingAddress.isPresent()) {
            customerEntity.setAddress(existingAddress.get());
            return;
        }

        JpaAddressEntity newAddressEntity = new JpaAddressEntity();
        newAddressEntity.setCountry(newAddress.country());
        newAddressEntity.setCity(newAddress.city());
        newAddressEntity.setDistrict(newAddress.district());
        newAddressEntity.setStreet(newAddress.street());
        newAddressEntity.setApartmentNumber(newAddress.apartmentNumber());
        newAddressEntity.setDoorNumber(newAddress.doorNumber());

        JpaAddressEntity savedAddress = addressService.saveAddress(newAddressEntity);
        customerEntity.setAddress(savedAddress);
    }

    private Customer toDomain(JpaCustomerEntity entity) {
        FullName fullName = new FullName(entity.getFirstname(), entity.getLastname());
        IdNumber idNumber = new IdNumber(entity.getIdNumber());
        Address address = null;
        JpaAddressEntity addr = entity.getAddress();
        if (addr != null) {
            address = new Address(
                    addr.getCountry(),
                    addr.getCity(),
                    addr.getDistrict(),
                    addr.getStreet(),
                    addr.getApartmentNumber(),
                    addr.getDoorNumber()
            );
        }
        return new Customer(CustomerId.of(entity.getCustomerId()), fullName, idNumber, address);
    }
}
