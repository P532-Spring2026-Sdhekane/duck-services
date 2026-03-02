package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckData;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class DucksRepository {

    private final String IMAGES_FOLDER_PATH = "ducks/images/";
    private final String AUDIO_FOLDER_PATH = "ducks/audio/";

    public DucksRepository() {
        try {
            // Create images directory if it doesn't exist
            File ducksImagesDirectory = new File("ducks/images");
            if (!ducksImagesDirectory.exists()) {
                ducksImagesDirectory.mkdirs();
                System.out.println("Created images directory: " + ducksImagesDirectory.getAbsolutePath());
            }

            // Create audio directory if it doesn't exist
            File ducksAudioDirectory = new File("ducks/audio");
            if (!ducksAudioDirectory.exists()) {
                ducksAudioDirectory.mkdirs();
                System.out.println("Created audio directory: " + ducksAudioDirectory.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Warning: Could not create directories: " + e.getMessage());
        }
    }

    public boolean updateImage(int id, MultipartFile file) throws IOException {
        try {
            System.out.println("Uploading image: " + file.getOriginalFilename());
            System.out.println("Content type: " + file.getContentType());

            String fileExtension = ".png";
            Path path = Paths.get(IMAGES_FOLDER_PATH + id + fileExtension);

            // Create parent directories if they don't exist
            path.getParent().toFile().mkdirs();

            file.transferTo(path);
            System.out.println("Image saved successfully: " + path);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
            throw e;
        }
    }

    public boolean updateAudio(int id, MultipartFile file) throws IOException {
        try {
            System.out.println("Uploading audio: " + file.getOriginalFilename());
            System.out.println("Content type: " + file.getContentType());

            String fileExtension = ".mp3";
            Path path = Paths.get(AUDIO_FOLDER_PATH + id + fileExtension);

            // Create parent directories if they don't exist
            path.getParent().toFile().mkdirs();

            file.transferTo(path);
            System.out.println("Audio saved successfully: " + path);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving audio: " + e.getMessage());
            throw e;
        }
    }

    public byte[] getImage(int id) throws IOException {
        String fileExtension = ".png";
        Path path = Paths.get(IMAGES_FOLDER_PATH + id + fileExtension);

        if (!Files.exists(path)) {
            System.err.println("Image not found for duck ID: " + id);
            return new byte[0];
        }

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Error reading image for duck ID " + id + ": " + e.getMessage());
            return new byte[0];
        }
    }

    public byte[] getAudio(int id) throws IOException {
        String fileExtension = ".mp3";
        Path path = Paths.get(AUDIO_FOLDER_PATH + id + fileExtension);

        if (!Files.exists(path)) {
            System.err.println("Audio not found for duck ID: " + id);
            return new byte[0];
        }

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Error reading audio for duck ID " + id + ": " + e.getMessage());
            return new byte[0];
        }
    }

}