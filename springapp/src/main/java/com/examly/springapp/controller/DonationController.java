package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Donation;
import com.examly.springapp.service.DonationService;

@RestController
@RequestMapping("/api")
public class DonationController {

    @Autowired DonationService donationService;

    @PostMapping("/donations")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> addDonation(@RequestBody Donation donation, @RequestParam("userId") Long userId, @RequestParam("charityId") Long charityId){
        Donation d = donationService.addDonation(userId, charityId, donation);

        if(d!=null){
            return ResponseEntity.status(201).body(d);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("/donations")
    public ResponseEntity<?> getAllDonations(){
        List<Donation> d = donationService.getAllDonations();

        if(d!=null){
            return ResponseEntity.status(200).body(d);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("/donations/user/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getDonationsByUserId(@PathVariable Long userId){
        List<Donation> d = donationService.getAllDonationsByUserId(userId);

        if(d!=null){
            return ResponseEntity.status(200).body(d);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping("/donations/charity/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getDonationsByCharityId(@PathVariable Long charityId){
        List<Donation> d = donationService.getAllDonationsByCharityId(charityId);
        double sum = 0;
        for(Donation donation:d){
            sum = sum + donation.getAmount();
        }

        if(d!=null){
            return ResponseEntity.status(200).body(sum);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }

    }

    @GetMapping("/donations/{donationId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getDonationById(@PathVariable Long donationId){
        Donation d = donationService.getDonationById(donationId);

        if(d!=null){
            return ResponseEntity.status(200).body(d);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
        
    }

}
