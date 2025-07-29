package com.example.fantasy.shared.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for file operations
 */
@Slf4j
public final class FileUtils {
    
    private FileUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Save a multipart file to a directory
     * @param file the multipart file to save
     * @param directory the directory to save to
     * @return the path to the saved file or empty if saving fails
     */
    public static Optional<Path> saveMultipartFile(MultipartFile file, String directory) {
        if (file.isEmpty()) {
            return Optional.empty();
        }
        
        try {
            // Create directory if it doesn't exist
            Path directoryPath = Paths.get(directory);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            
            // Generate a unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String filename = UUID.randomUUID() + extension;
            
            // Save the file
            Path filePath = directoryPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);
            
            return Optional.of(filePath);
        } catch (IOException e) {
            log.error("Error saving file", e);
            return Optional.empty();
        }
    }
    
    /**
     * Get the file extension
     * @param filename the filename
     * @return the file extension or empty string if no extension
     */
    public static String getFileExtension(String filename) {
        if (StringUtils.isEmpty(filename) || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
    
    /**
     * Get the file name without extension
     * @param filename the filename
     * @return the file name without extension
     */
    public static String getFileNameWithoutExtension(String filename) {
        if (StringUtils.isEmpty(filename)) {
            return "";
        }
        if (!filename.contains(".")) {
            return filename;
        }
        return filename.substring(0, filename.lastIndexOf("."));
    }
    
    /**
     * Delete a file
     * @param filePath the path to the file
     * @return true if the file was deleted, false otherwise
     */
    public static boolean deleteFile(Path filePath) {
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting file: {}", filePath, e);
            return false;
        }
    }
    
    /**
     * Delete a file
     * @param file the file to delete
     * @return true if the file was deleted, false otherwise
     */
    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        return file.delete();
    }
}
