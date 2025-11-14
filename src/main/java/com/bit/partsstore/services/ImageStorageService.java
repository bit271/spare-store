package com.bit.partsstore.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service responsible for storing cars and parts images on the local file system.
 * Saves uploaded image files to a predefined directory 'uploads/'.
 */
@Service
public class ImageStorageService {

    @Value("${server.url}")
    private String serverUrl;
    private final Path carUploadPath = Paths.get("uploads/cars");
    private final Path partUploadPath = Paths.get("uploads/parts");

    public void saveCarImage(MultipartFile imageFile, String filename) {
        saveImage(imageFile, filename, carUploadPath);
    }

    public void deleteCarImage(String filename) {
        deleteImage(filename, carUploadPath);
    }

    public void savePartImage(MultipartFile imageFile, String filename) {
        saveImage(imageFile, filename, partUploadPath);
    }

    public void deletePartImage(String filename) {
        deleteImage(filename, partUploadPath);
    }

    public String getCarImageUrl(String filename) {
        return serverUrl + "/" + carUploadPath + "/" + filename;
    }

    public String getPartImageUrl(String filename) {
        return serverUrl + "/" + partUploadPath + "/" + filename;
    }

    /**
     * Saves the uploaded car image to the configured upload directory.
     * Creates the directory if it does not exist and replaces the file if it already exists.
     *
     * @param imageFile the image file to be saved
     * @param filename  the name under which the file should be saved
     * @throws RuntimeException if saving fails for any reason
     */
    private void saveImage(MultipartFile imageFile, String filename, Path uploadPath) {
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path targetPath = uploadPath.resolve(filename);
            Files.copy(imageFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + filename, e);
        }
    }

    /**
     * Deletes an image file from the upload directory.
     *
     * @param filename the name of the file to delete
     * @throws RuntimeException if deletion fails
     */
    private void deleteImage(String filename, Path deletePath) {
        try {
            Path filePath = deletePath.resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image: " + filename, e);
        }
    }

}
