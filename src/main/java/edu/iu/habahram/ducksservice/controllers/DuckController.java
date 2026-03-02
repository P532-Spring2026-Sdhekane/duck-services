package edu.iu.habahram.ducksservice.controllers;

import edu.iu.habahram.ducksservice.model.DuckData;
import edu.iu.habahram.ducksservice.model.DuckEntity;
import edu.iu.habahram.ducksservice.repository.DuckJPARepository;
import edu.iu.habahram.ducksservice.repository.DucksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/ducks")
public class DuckController {

    private final DuckJPARepository duckJPARepository;
    private final DucksRepository ducksRepository;

    public DuckController(DuckJPARepository duckJPARepository, DucksRepository ducksRepository) {
        this.duckJPARepository = duckJPARepository;
        this.ducksRepository = ducksRepository;
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody DuckData duck) {
        try {
            // Save to database
            DuckEntity entity = new DuckEntity(
                    duck.name(),
                    duck.type(),
                    duck.color(),
                    duck.size(),
                    duck.price(),
                    duck.quackSound(),
                    duck.flyBehavior()
            );
            DuckEntity saved = duckJPARepository.save(entity);
            return ResponseEntity.ok(saved.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<DuckData> findAll() {
        // USING JPA REPOSITORY - NOT FILE REPOSITORY
        System.out.println("Fetching ducks from PostgreSQL database");
        return duckJPARepository.findAll()
                .stream()
                .map(DuckEntity::toDuckData)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckData> find(@PathVariable int id) {
        Optional<DuckEntity> duckEntity = duckJPARepository.findById(id);
        if(duckEntity.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(duckEntity.get().toDuckData());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    // Keep these methods for image/audio operations (still using file system)
    @PostMapping("/{id}/image")
    public boolean updateImage(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksRepository.updateImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}/audio")
    public boolean updateAudio(@PathVariable int id,
                               @RequestParam MultipartFile file) {
        try {
            return ducksRepository.updateAudio(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        try {
            byte[] image = ducksRepository.getImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/audio")
    public ResponseEntity<?> getAudio(@PathVariable int id) {
        try {
            byte[] audio = ducksRepository.getAudio(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.valueOf("audio/mp3"))
                    .body(audio);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}