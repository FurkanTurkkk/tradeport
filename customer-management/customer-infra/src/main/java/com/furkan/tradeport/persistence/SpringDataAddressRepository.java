package com.furkan.tradeport.persistence;

import com.furkan.tradeport.entity.JpaAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataAddressRepository extends JpaRepository<JpaAddressEntity, Long> {
    Optional<JpaAddressEntity> findByCountryAndCityAndDistrictAndStreetAndApartmentNumberAndDoorNumber(
            String country,
            String city,
            String district,
            String street,
            Integer apartmentNumber,
            Integer doorNumber
    );
}
