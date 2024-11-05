package com.primetech.primetech_store.user.infraestructure.controllers;

import com.primetech.primetech_store.user.application.DeleteUserImageApplication;
import com.primetech.primetech_store.user.application.GetUserImageApplication;
import com.primetech.primetech_store.user.application.UploadUserImageApplication;
import com.primetech.primetech_store.user.domain.models.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-image")
@RequiredArgsConstructor
public class UserImageController {
    private final UploadUserImageApplication uploadUserImageApplication;
    private final DeleteUserImageApplication deleteUserImageApplication;
    private final GetUserImageApplication getUserImageApplication;

    @GetMapping("")
    public ResponseEntity<Map<String, String>> getImage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            UserImage userImage = getUserImageApplication.getUserImage(email);
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", userImage.getImgURL());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            uploadUserImageApplication.uploadUserProfile(file, email);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Image successfully uploaded");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @DeleteMapping("/{userImageId}")
    public ResponseEntity<Map<String, String>> deleteUserImage(@PathVariable("userImageId") UUID userImageId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            deleteUserImageApplication.deleteUserImage(email, userImageId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Image deleted correctly");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
