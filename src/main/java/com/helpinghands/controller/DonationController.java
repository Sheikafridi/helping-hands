package com.helpinghands.controller;

import com.helpinghands.model.Donation;
import com.helpinghands.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // Create a new donation
    @PostMapping("/donate")
    public ResponseEntity<String> donateFood(@RequestBody Donation donation) {
        donationService.saveDonation(donation);
        return ResponseEntity.ok("Donation added successfully.");
    }

    // Get all donations
    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }

    // Get a donation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        Donation donation = donationService.getDonationById(id);
        return donation != null ? ResponseEntity.ok(donation) : ResponseEntity.notFound().build();
    }

    // Update a donation
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable Long id, @RequestBody Donation updatedDonation) {
        boolean isUpdated = donationService.updateDonation(id, updatedDonation);
        return isUpdated 
            ? ResponseEntity.ok("Donation updated successfully.") 
            : ResponseEntity.notFound().build();
    }

    // Delete a donation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonation(@PathVariable Long id) {
        boolean isDeleted = donationService.deleteDonation(id);
        return isDeleted 
            ? ResponseEntity.ok("Donation deleted successfully.") 
            : ResponseEntity.notFound().build();
    }
}
