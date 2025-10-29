package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaAddressEntity;
import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.model.Customer;
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
    private final SpringDataCustomerRepository repository;

    public UpdateCustomerAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String customerId = customer.getCustomerId().asString();
        JpaCustomerEntity entity = repository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found in infra layer"));

        updateCustomerEntity(entity, customer);
        repository.save(entity);
        return toDomain(entity);
    }

    private void updateCustomerEntity(JpaCustomerEntity existingEntity, Customer updatedCustomer) {
        // FullName varsa güncelle
        if (updatedCustomer.getFullname() != null) {
            String newFirstname = updatedCustomer.getFullname().firstName();
            String newLastname = updatedCustomer.getFullname().lastName();

            // null değilse değiştir, değilse eskiyi koru
            existingEntity.setFirstname(newFirstname != null ? newFirstname : existingEntity.getFirstname());
            existingEntity.setLastname(newLastname != null ? newLastname : existingEntity.getLastname());
        }

        // IdNumber varsa güncelle
        if (updatedCustomer.getIdNumber() != null) {
            existingEntity.setIdNumber(updatedCustomer.getIdNumber().asString());
        }

        // Address varsa güncelle
        if (updatedCustomer.getAddress() != null) {
            JpaAddressEntity existingAddress = getJpaAddressEntity(existingEntity, updatedCustomer);
            existingEntity.setAddress(existingAddress);
        }

    }


    private static JpaAddressEntity getJpaAddressEntity(JpaCustomerEntity existingEntity, Customer updatedCustomer) {
        JpaAddressEntity existingAddress = existingEntity.getAddress();
        if (existingAddress == null)
            existingAddress = new JpaAddressEntity();

        Address updatedAddress = updatedCustomer.getAddress();

        if (updatedAddress.country() != null)
            existingAddress.setCountry(updatedAddress.country());

        if (updatedAddress.city() != null)
            existingAddress.setCity(updatedAddress.city());

        if (updatedAddress.district() != null)
            existingAddress.setDistrict(updatedAddress.district());

        if (updatedAddress.street() != null)
            existingAddress.setStreet(updatedAddress.street());

        if (updatedAddress.apartmentNumber() != null)
            existingAddress.setApartmentNumber(updatedAddress.apartmentNumber());

        if (updatedAddress.doorNumber() != null)
            existingAddress.setDoorNumber(updatedAddress.doorNumber());
        return existingAddress;
    }


    private Customer toDomain(JpaCustomerEntity entity) {
        FullName fullName = new FullName(entity.getFirstname(), entity.getLastname());
        IdNumber idNumber = new IdNumber(entity.getIdNumber());
        JpaAddressEntity addressEntity = entity.getAddress();
        Address address = null;
        if(addressEntity != null) {
            address = new Address(addressEntity.getCountry(),
                    addressEntity.getCity(),
                    addressEntity.getDistrict(),
                    addressEntity.getStreet(),
                    addressEntity.getApartmentNumber(),
                    addressEntity.getDoorNumber());
        }
        return new Customer(CustomerId.of(entity.getCustomerId()),fullName,idNumber,address);
    }
}
