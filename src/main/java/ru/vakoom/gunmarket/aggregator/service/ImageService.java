package ru.vakoom.gunmarket.aggregator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.gunmarket.commondatalayer.repo.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepo productRepo;

    public Optional<byte[]> getProductImage(long productId) {
        return productRepo.findById(productId)
                .map(Product::getImageUrl)
                .map(Paths::get)
                .map(p -> {
                    try {
                        return Files.readAllBytes(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }
}