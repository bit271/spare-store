package com.bit.partsstore.services;

import com.bit.partsstore.DTO.PartRequest;
import com.bit.partsstore.DTO.PartResponse;
import com.bit.partsstore.models.Car;
import com.bit.partsstore.models.Category;
import com.bit.partsstore.models.Part;
import com.bit.partsstore.repositories.CarRepository;
import com.bit.partsstore.repositories.CategoryRepository;
import com.bit.partsstore.repositories.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartService {

    private static final String CAR_NOT_FOUND_MSG = "Car not found";
    private static final String CATEGORY_NOT_FOUND_MSG = "Category not found";
    private static final String PART_NOT_FOUND_MSG = "Part not found";

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;
    private final ImageStorageService imageStorageService;

    public PartService(PartRepository partRepository, CarRepository carRepository, CategoryRepository categoryRepository, ImageStorageService imageStorageService) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<PartResponse> getParts() {
        return partRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PartResponse addPart(PartRequest request) {
        try {
            Part part = createPartFromRequest(request);
            Part saved = partRepository.save(part);
            imageStorageService.savePartImage(request.getImage(), part.getImageName());
            return mapToResponse(saved);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add part", e);
        }
    }

    public Part deletePart(int id) {
        return partRepository.findById(id)
                .map(part -> {
                    imageStorageService.deletePartImage(part.getImageName());
                    partRepository.delete(part);
                    return part;
                })
                .orElseThrow(() -> new RuntimeException(PART_NOT_FOUND_MSG));
    }

    private Part createPartFromRequest(PartRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND_MSG));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_MSG));
        String uniqueImageName = UUID.randomUUID() + "_" + request.getImage().getOriginalFilename();

        return new Part(
                car,
                category,
                request.getName(),
                request.getAvailableCount(),
                request.getPrice(),
                request.getCatalogNum(),
                request.getDescription(),
                uniqueImageName
        );
    }

    private PartResponse mapToResponse(Part part) {
        Car car = part.getCar();
        String carName = car.getBrand().getName() + " " + car.getModel().getName() + " (" + car.getYear() + ")";
        return new PartResponse(
                part.getId(),
                part.getName(),
                part.getPrice(),
                part.getAvailableCount(),
                carName,
                part.getCategory().getName(),
                part.getCatalogNum(),
                part.getDescription(),
                part.getImageName()
        );
    }
}
