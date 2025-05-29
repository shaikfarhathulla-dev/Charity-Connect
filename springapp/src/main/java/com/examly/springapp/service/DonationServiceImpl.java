package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Charity;
import com.examly.springapp.model.Donation;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.DonationRepo;
import com.examly.springapp.repository.UserRepo;

import java.util.Collections;

@Service("donationServiceImpl")
public class DonationServiceImpl implements DonationService{

    @Autowired DonationRepo donationRepo;

    @Autowired 
    private UserRepo userRepo;

    @Autowired
    private CharityService charityService;

    @Override
    public Donation addDonation(Long userId, Long charityId, Donation donation) {
      Optional<User> user = userRepo.findById(userId);
      Charity charity = charityService.getCharityById(charityId);
      if(user.isPresent() && charity != null){
         System.out.println(user.get().getUsername());
         System.out.println(charity.getCharityName());
         donation.setUser(user.get());
         donation.setCharity(charity);
         return donationRepo.save(donation);
      }else{
         return null;
      }
    }

    @Override
    public List<Donation> getAllDonations() {
       return donationRepo.findAllByOrderByAmountDesc();
    }

    @Override
    public List<Donation> getAllDonationsByUserId(Long userId) {
       return donationRepo.findByUserUserIdOrderByAmountDesc(userId);
    }

    @Override
    public List<Donation> getAllDonationsByCharityId(Long charityId) {
        return donationRepo.findByCharityCharityId(charityId);
    }

    @Override
    public Donation getDonationById(Long donationId) {
       Optional<Donation> d = donationRepo.findById(donationId);

       if(d.isPresent()){
        return d.get();
       }
       else{
        return null;
       }
    }

}
