package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.PartRequest;
import com.bit.partsstore.DTO.PartResponse;
import com.bit.partsstore.models.Part;
import com.bit.partsstore.services.PartService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/parts")
public class PartAdminController {

    private final PartService partService;

    public PartAdminController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public ResponseEntity<List<PartResponse>> getParts() {
        List<PartResponse> parts = partService.getParts();
        return ResponseEntity.ok(parts);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PartResponse> addPart(@ModelAttribute PartRequest request) {
        PartResponse response = partService.addPart(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Part> deletePart(@PathVariable int id) {
        Part deletedPart = partService.deletePart(id);
        return ResponseEntity.ok(deletedPart);
    }

}
