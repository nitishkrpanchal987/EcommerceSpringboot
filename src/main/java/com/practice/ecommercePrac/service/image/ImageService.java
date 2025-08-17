package com.practice.ecommercePrac.service.image;

import com.practice.ecommercePrac.dto.ImageDto;
import com.practice.ecommercePrac.exceptions.ImageNotFoundException;
import com.practice.ecommercePrac.model.Image;
import com.practice.ecommercePrac.model.Product;
import com.practice.ecommercePrac.repository.ImageRepository;
import com.practice.ecommercePrac.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ImageService implements IImageService{
    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private ImageRepository imageRepository;
    private ProductService productService;
    @Override
    public Image getImageById(Long id) {
        logger.info("inside id method");
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found!!"));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> new ImageNotFoundException("Image not found!!"));
    }

    @Override
    public List<ImageDto> SaveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImageDtos = new ArrayList<>();
        for(MultipartFile file:files){
            try{
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String downloadUrl = "/api/v1/images/image/download" + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl("/api/v1/images/image/download/" + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setImageId(savedImage.getId());
                imageDto.setImageName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDtos.add(imageDto);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return savedImageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        image.setFileName(file.getOriginalFilename());
        try {
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
