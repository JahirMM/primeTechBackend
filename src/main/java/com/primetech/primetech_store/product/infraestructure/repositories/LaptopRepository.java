package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LaptopRepository extends JpaRepository<Laptop, UUID> {
}
