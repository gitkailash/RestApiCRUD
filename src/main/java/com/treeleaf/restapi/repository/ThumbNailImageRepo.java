package com.treeleaf.restapi.repository;

import com.treeleaf.restapi.entity.ThumbNailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbNailImageRepo extends JpaRepository<ThumbNailImage, Long> {
}
