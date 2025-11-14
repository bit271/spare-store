package com.bit.partsstore.services;

import com.bit.partsstore.DTO.ModelRequest;
import com.bit.partsstore.DTO.ModelResponse;
import com.bit.partsstore.models.Brand;
import com.bit.partsstore.models.Model;
import com.bit.partsstore.repositories.BrandRepository;
import com.bit.partsstore.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private static final String MODEL_ALREADY_EXISTS = "Model already exists";
    private static final String MODEL_NOT_FOUND = "Model not found";
    private static final String BRAND_NOT_FOUND = "Brand not found";
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelService(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    public List<ModelResponse> getModels() {
        return modelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ModelResponse addModel(ModelRequest request) {
        if (modelRepository.existsByNameAndBrandId(request.getName(), request.getBrandId())) {
            throw new RuntimeException(MODEL_ALREADY_EXISTS);
        }
        Model model = createModelFromRequest(request);
        Model saved = modelRepository.save(model);
        return mapToResponse(saved);
    }

    public Model deleteModel(int id) {
        return modelRepository.findById(id)
                .map(model -> {
                    modelRepository.delete(model);
                    return model;
                })
                .orElseThrow(() -> new RuntimeException(MODEL_NOT_FOUND));
    }

    private Model createModelFromRequest(ModelRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException(BRAND_NOT_FOUND));

        Model model = new Model();
        model.setName(request.getName());
        model.setBrand(brand);
        return model;
    }

    private ModelResponse mapToResponse(Model model) {
        return new ModelResponse(model.getId(), model.getName(), model.getBrand().getId(), model.getBrand().getName());
    }
}
