package com.treeleaf.restapi.service.impl;

import com.treeleaf.restapi.entity.ThumbNailImage;
import com.treeleaf.restapi.exception.ThumbNailImageNotFoundException;
import com.treeleaf.restapi.repository.ThumbNailImageRepo;
import com.treeleaf.restapi.service.ThumbNailImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ThumbNailImageImpl implements ThumbNailImageService {

    private final ThumbNailImageRepo thumbNailImageRepo;
    @Override
    public List<ThumbNailImage> getAllThumbnailImages() {
        return thumbNailImageRepo.findAll();
    }

    @Override
    public ThumbNailImage getThumbnailImageById(Long id) {
        Optional<ThumbNailImage> checkThumbNailImg = thumbNailImageRepo.findById(id);
        if (checkThumbNailImg.isPresent()) {
            return checkThumbNailImg.get();
        }else {
            log.error("Thumbnail Search Error.");
            throw new ThumbNailImageNotFoundException("Thumbnail image Not Found.");
        }
    }

    @Override
    public ThumbNailImage createThumbnailImage(ThumbNailImage thumbnailImage) {
        log.info("Blog Post save successfully");
        return thumbNailImageRepo.save(thumbnailImage);
    }

    @Override
    public ThumbNailImage updateThumbnailImage(Long id, ThumbNailImage thumbnailImage) {
        Optional<ThumbNailImage> result = thumbNailImageRepo.findById(id);
        if (result.isPresent()) {
            ThumbNailImage thumbNailPersistent = result.get();
            thumbNailPersistent.setImageUrl(thumbnailImage.getImageUrl());
            thumbNailImageRepo.save(thumbNailPersistent);

            log.info("Thumbnail update for id " + id);
            return thumbNailPersistent;
        }else {
            log.info("Thumbnail search error for id " + id);
            throw new ThumbNailImageNotFoundException("ThumbNailImage Not Found.");
        }
    }

    @Override
    public void deleteThumbNailImage(Long id) {
        Optional<ThumbNailImage> user = thumbNailImageRepo.findById(id);
        if (user.isEmpty()) {
            log.error("ThumbNailImage Search Error for id "+ id);
            throw new ThumbNailImageNotFoundException("ThumbNailImage Not Found.");
        }else {
            log.info("Thumbnail Image delete successful.");
            thumbNailImageRepo.deleteById(id);
        }
    }
}
