package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.CharityAlreadyExistsException;
import com.examly.springapp.model.Charity;
import com.examly.springapp.service.CharityService;

@RestController
@RequestMapping("/api")
public class CharityController {

    @Autowired CharityService charityService;

    @PostMapping("/charities")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addCharity(@RequestBody Charity charity){
        Charity c=charityService.addCharity(charity);

        if(c!=null){
            return ResponseEntity.status(201).body(charity);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
    }

    

    @GetMapping("/charities")
    public ResponseEntity<?> getAllCharaties(){
        List<Charity> c = charityService.getAllCharaties();

        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/charities/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editCharity(@PathVariable Long charityId ,@RequestBody Charity updatedCharity){
        Charity c=charityService.editCharity(charityId, updatedCharity);

        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/charities/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCharity(@PathVariable Long charityId){
        Charity c = charityService.deleteCharity(charityId);

        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/charities/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getCharityById(@PathVariable Long charityId){
        Charity c = charityService.getCharityById(charityId);

        if(c!=null){
            return ResponseEntity.status(200).body(c);
        }
        else{
            return ResponseEntity.status(500).body(null);
        }
    }

}
