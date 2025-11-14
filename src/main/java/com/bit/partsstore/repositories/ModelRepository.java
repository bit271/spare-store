package com.bit.partsstore.repositories;

import com.bit.partsstore.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    boolean existsByNameAndBrandId(String name, Integer brandId);
    Optional<Model> findByNameAndBrandId(String name, Integer brandId);
}
