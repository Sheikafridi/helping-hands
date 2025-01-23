package com.helpinghands.service;

import com.helpinghands.model.Donation;
import com.helpinghands.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        return donation.orElse(null);
    }

    public boolean updateDonation(Long id, Donation updatedDonation) {
        if (donationRepository.existsById(id)) {
            updatedDonation.setId(id);
            donationRepository.save(updatedDonation);
            return true;
        }
        return false;
    }

    public boolean deleteDonation(Long id) {
        if (donationRepository.existsById(id)) {
            donationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
