package com.primetech.primetech_store.config;

import com.primetech.primetech_store.category.domain.models.Category;
import com.primetech.primetech_store.category.infrastructure.repositories.CategoryRepository;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.infrastructure.repositories.DeviceTypeRepository;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.infrastructure.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner initData(
            CategoryRepository categoryRepo,
            UserRoleRepository userRoleRepo,
            DeviceTypeRepository deviceTypeRepo) {
        return args -> {
            // Inicializar datos para UserRole
            insertIfNotExists(userRoleRepo, "normal");
            insertIfNotExists(userRoleRepo, "seller");

            // Inicializar datos para Category
            insertIfNotExists(categoryRepo, "cellular");
            insertIfNotExists(categoryRepo, "tablet");
            insertIfNotExists(categoryRepo, "laptop");
            insertIfNotExists(categoryRepo, "other");

            // Inicializar datos para DeviceType
            insertIfNotExists(deviceTypeRepo, "mobile");
            insertIfNotExists(deviceTypeRepo, "laptop");
            insertIfNotExists(deviceTypeRepo, "other");
        };
    }

    private void insertIfNotExists(UserRoleRepository repo, String roleName) {
        if (repo.findByRoleName(roleName).isEmpty()) {
            repo.save(new UserRole(UUID.randomUUID(), roleName));
        }
    }

    private void insertIfNotExists(CategoryRepository repo, String categoryName) {
        if (repo.findByCategoryName(categoryName).isEmpty()) {
            repo.save(new Category(UUID.randomUUID(), categoryName));
        }
    }

    private void insertIfNotExists(DeviceTypeRepository repo, String typeName) {
        if (repo.findDeviceTypeByTypeName(typeName).isEmpty()) {
            repo.save(new DeviceType(UUID.randomUUID(), typeName));
        }
    }
}
