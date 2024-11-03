package com.samverk.api.controller;

import com.samverk.application.AddressService;
import com.samverk.domain.entity.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable UUID id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return new ResponseEntity<>(addressService.createAddress(address), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable UUID id, @RequestBody Address addressDetails) {
        return ResponseEntity.ok(addressService.updateAddress(id, addressDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
