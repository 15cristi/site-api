package com.parcare.parcare_sistem.controller;

import com.parcare.parcare_sistem.dto.UserProfileDTO;
import com.parcare.parcare_sistem.model.User;
import com.parcare.parcare_sistem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public UserProfileDTO getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findOptionalByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setFullName(user.getFullName());
        dto.setLicensePlate(user.getLicensePlate());
        dto.setProfileImageUrl(user.getProfileImageUrl());

        return dto;
    }


    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserProfileDTO dto) {

        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilizatorul nu a fost găsit");
        }

        user.setFullName(dto.getFullName());
        user.setLicensePlate(dto.getLicensePlate());
        user.setProfileImageUrl(dto.getProfileImageUrl());

        userRepository.save(user);

        return ResponseEntity.ok("✅ Profil actualizat cu succes!");
    }

    @DeleteMapping("/profile")
    public ResponseEntity<?> clearProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilizatorul nu a fost găsit");
        }

        // 🔄 Resetează câmpurile profilului
        user.setFullName(null);
        user.setLicensePlate(null);
        user.setProfileImageUrl(null);

        userRepository.save(user);

        return ResponseEntity.ok("🧹 Datele profilului au fost șterse.");
    }

}
