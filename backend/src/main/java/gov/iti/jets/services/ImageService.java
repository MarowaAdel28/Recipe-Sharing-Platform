package gov.iti.jets.services;

import gov.iti.jets.models.dtos.ImageDTO;
import gov.iti.jets.models.entities.Image;
import gov.iti.jets.repositories.ImageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Integer save(ImageDTO imageDto) {
        Image image = new Image();
        BeanUtils.copyProperties(imageDto, image);
        image = imageRepository.save(image);
        return image.getId();
    }

    public void delete(Integer id) {
        imageRepository.deleteById(id);
    }

    public void update(ImageDTO imageDto) {
        Image image = requireOne(imageDto.getId());
        BeanUtils.copyProperties(imageDto, image);
        imageRepository.save(image);
    }

    public ImageDTO getById(Integer id) {
        Image original = requireOne(id);
        return toDTO(original);
    }


    private ImageDTO toDTO(Image original) {
        ImageDTO bean = new ImageDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Image requireOne(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
