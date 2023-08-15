package com.treeleaf.restapi.service;

import com.treeleaf.restapi.entity.ThumbNailImage;

import java.util.List;

public interface ThumbNailImageService {
    List<ThumbNailImage> getAllThumbnailImages();
    ThumbNailImage getThumbnailImageById(Long id);
    ThumbNailImage createThumbnailImage(ThumbNailImage thumbnailImage);
    ThumbNailImage updateThumbnailImage(Long id, ThumbNailImage updatedThumbnailImage);
    void deleteThumbNailImage(Long id);
}
