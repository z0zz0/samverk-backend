package com.samverk.domain.service;

import com.samverk.domain.entity.Address;
import com.samverk.domain.repository.AddressRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AddressDomainService {
    private final AddressRepository addressRepository;

    public AddressDomainService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        Log.info("Fetching all addresses");
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) {
        Log.info("Fetching address with id: " + addressId);
        return addressRepository.findById(addressId)
                .orElseThrow(() -> {
                    Log.error("Address not found with id: " + addressId);
                    return new RuntimeException("Address not found");
                });
    }

    @Transactional
    public Address createAddress(Address address) {
        Log.info("Creating new address");
        return addressRepository.save(address);
    }

    @Transactional
    public Address updateAddress(UUID addressId, Address addressDetails) {
        Log.info("Updating address with id: " + addressId);

        Address address = getAddressById(addressId);
        
        address.setStreetAddress(addressDetails.getStreetAddress());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setMunicipality(addressDetails.getMunicipality());
        address.setStateProvince(addressDetails.getStateProvince());

        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(UUID addressId) {
        Log.info("Deleting address with id: " + addressId);
        addressRepository.deleteById(addressId);
    }
}