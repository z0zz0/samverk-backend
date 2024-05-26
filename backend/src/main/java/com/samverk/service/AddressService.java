package com.samverk.service;

import com.samverk.domain.model.Address;
import com.samverk.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(UUID addressId, Address addressDetails) {
        Address address = getAddressById(addressId);
        address.setStreetAddress(addressDetails.getStreetAddress());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setMunicipality(addressDetails.getMunicipality());
        address.setStateProvince(addressDetails.getStateProvince());
        // Update other fields as necessary
        return addressRepository.save(address);
    }

    public void deleteAddress(UUID addressId) {
        addressRepository.deleteById(addressId);
    }
}
