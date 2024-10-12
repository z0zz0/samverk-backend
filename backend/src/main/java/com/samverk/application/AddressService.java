package com.samverk.application;

import com.samverk.domain.model.Address;
import com.samverk.domain.service.AddressDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    private final AddressDomainService addressDomainService;

    public AddressService(AddressDomainService addressDomainService) {
        this.addressDomainService = addressDomainService;
    }

    public List<Address> getAllAddresses() {
        return addressDomainService.getAllAddresses();
    }

    public Address getAddressById(UUID addressId) {
        return addressDomainService.getAddressById(addressId);
    }

    public Address createAddress(Address address) {
        return addressDomainService.createAddress(address);
    }

    public Address updateAddress(UUID addressId, Address addressDetails) {
        return addressDomainService.updateAddress(addressId, addressDetails);
    }

    public void deleteAddress(UUID addressId) {
        addressDomainService.deleteAddress(addressId);
    }
}