package com.treeleaf.restapi.controller;

import com.treeleaf.restapi.entity.ThumbNailImage;
import com.treeleaf.restapi.service.ThumbNailImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/thumbNailImages")
public class ThumbNailImageController {

    private final ThumbNailImageService thumbNailImageService;
    @GetMapping
    public List<ThumbNailImage> getAllThumbnailImages() {
        return thumbNailImageService.getAllThumbnailImages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThumbNailImage> getThumbnailImageById(@PathVariable Long id) {
        ThumbNailImage thumbnailImage = thumbNailImageService.getThumbnailImageById(id);
        if (thumbnailImage != null) {
            return ResponseEntity.ok(thumbnailImage);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ThumbNailImage> createThumbnailImage(@RequestBody ThumbNailImage thumbnailImage) {
        ThumbNailImage createdThumbnailImage = thumbNailImageService.createThumbnailImage(thumbnailImage);
        return ResponseEntity.ok(createdThumbnailImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThumbNailImage> updateThumbnailImage(@PathVariable Long id, @RequestBody ThumbNailImage updatedThumbnailImage) {
        ThumbNailImage updated = thumbNailImageService.updateThumbnailImage(id, updatedThumbnailImage);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThumbnailImage(@PathVariable Long id) {
        thumbNailImageService.deleteThumbNailImage(id);
        return ResponseEntity.ok().build();
    }
}
