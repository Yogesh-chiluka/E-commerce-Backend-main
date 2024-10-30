package com.backend.Ecommerce.Backend.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.backend.Ecommerce.Backend.model.Image;
import com.backend.Ecommerce.Backend.service.dto.ImageDto;

public interface IImageService {

    Image getImageById(Long id);
    
    void deleteImageById(Long id);

    List<ImageDto> saveImage(List<MultipartFile> file, Long productIdLong);

    void updateImage(MultipartFile file, Long productId);
}
