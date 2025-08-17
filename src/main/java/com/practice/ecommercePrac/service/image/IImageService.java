package com.practice.ecommercePrac.service.image;

import com.practice.ecommercePrac.dto.ImageDto;
import com.practice.ecommercePrac.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> SaveImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file,  Long imageId);
}
