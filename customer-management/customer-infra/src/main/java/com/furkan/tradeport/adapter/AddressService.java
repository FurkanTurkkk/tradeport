package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaAddressEntity;
import com.furkan.tradeport.persistence.SpringDataAddressRepository;
import com.furkan.tradeport.valueobject.Address;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final SpringDataAddressRepository addressRepository;

    public AddressService(SpringDataAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public JpaAddressEntity saveAddress(JpaAddressEntity address) {
        return  addressRepository.save(address);
    }

    public Optional<JpaAddressEntity> findByAllFields(Address address) {
        return addressRepository.findByCountryAndCityAndDistrictAndStreetAndApartmentNumberAndDoorNumber(
                address.country(),
                address.city(),
                address.district(),
                address.street(),
                address.apartmentNumber(),
                address.doorNumber()
        );
    }
}
