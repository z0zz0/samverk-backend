package com.samverk.domain.service;

import com.samverk.domain.entity.Address;
import com.samverk.domain.exception.AddressNotFoundException;
import com.samverk.domain.exception.CountryNotFoundException;
import com.samverk.domain.exception.DatabaseOperationException;
import com.samverk.domain.repository.AddressRepository;
import com.samverk.util.Log;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AddressDomainService {

    private final AddressRepository addressRepository;

    public AddressDomainService(AddressRepository addressRepository) 
    {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() 
    {
        Log.info("Fetching all addresses");
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID addressId) 
    {
        Log.info("Fetching address with id: {}", addressId);
        return addressRepository.findById(addressId).orElseThrow(() -> {
                    Log.error("Address not found with id: {}", addressId);
                    return new AddressNotFoundException("Address not found with id: " + addressId);
                });
    }

    @Transactional
    public Address createAddress(Address address) 
    {
        try {
            // Perform any necessary validation here, if not handled elsewhere
            Address createdAddress = addressRepository.save(address);
            Log.info("Created new address with id: {}", createdAddress.getAddressId());
            return createdAddress;
        } 
        catch (DataIntegrityViolationException e) 
        {
            Log.error("Failed to create address due to constraint violation", e);
            throw new DatabaseOperationException("Address creation failed: " + e.getMostSpecificCause().getMessage(),
                    e);
        } 
        catch (CountryNotFoundException e) 
        {
            Log.error("Country not found: {}", e.getMessage());
            throw e; // Re-throw to be handled by GlobalExceptionHandler
        } 
        catch (Exception e) 
        {
            Log.error("Unexpected error while creating address", e);
            throw new DatabaseOperationException("Failed to create address: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Address updateAddress(UUID addressId, Address addressDetails) 
    {
        try
        {
            Address address = getAddressById(addressId);

            // Update address fields
            address.setStreetAddress(addressDetails.getStreetAddress());
            address.setPostalCode(addressDetails.getPostalCode());
            address.setMunicipality(addressDetails.getMunicipality());
            address.setStateProvince(addressDetails.getStateProvince());
            address.setCountry(addressDetails.getCountry());

            Log.info("Updating address with id: {}", addressId);

            return addressRepository.save(address);
        } 
        catch (DataIntegrityViolationException e) 
        {
            Log.error("Failed to update address due to constraint violation", e);
            throw new DatabaseOperationException("Address update failed: " + e.getMostSpecificCause().getMessage(), e);
        } 
        catch (CountryNotFoundException e) 
        {
            Log.error("Country not found: {}", e.getMessage());
            throw e; // Re-throw to be handled by GlobalExceptionHandler
        } 
        catch (AddressNotFoundException e) 
        {
            Log.error(e.getMessage());
            throw e; // Re-throw to be handled by GlobalExceptionHandler
        } 
        catch (Exception e) 
        {
            Log.error("Unexpected error while updating address", e);
            throw new DatabaseOperationException("Failed to update address: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteAddress(UUID addressId) 
    {
        Log.info("Deleting address with id: {}", addressId);
        try 
        {
            Address address = getAddressById(addressId);
            addressRepository.delete(address);
            Log.info("Address with id: {} deleted successfully", addressId);
        }
         catch (AddressNotFoundException e) 
        {
            Log.error(e.getMessage());
            throw e; // Re-throw to be handled by GlobalExceptionHandler
        } 
        catch (Exception e) 
        {
            Log.error("Unexpected error while deleting address", e);
            throw new DatabaseOperationException("Failed to delete address: " + e.getMessage(), e);
        }
    }
}
