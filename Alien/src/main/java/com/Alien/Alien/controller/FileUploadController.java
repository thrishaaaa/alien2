package com.Alien.Alien.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    // Define the path to the upload directory.
    // System.getProperty("user.dir") gets the root directory of your project.
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("criteria") String criteria) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {
            // Create the directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Create a unique filename to avoid overwrites (e.g., criteria-timestamp-originalfilename.pdf)
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = criteria + "-" + System.currentTimeMillis() + "-" + originalFileName;

            // Get the path where the file will be saved
            Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);

            // Save the file to the local system
            Files.copy(file.getInputStream(), filePath);

            // Return a success response with the name of the saved file
            return ResponseEntity.ok("File uploaded successfully: " + uniqueFileName);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }
}

