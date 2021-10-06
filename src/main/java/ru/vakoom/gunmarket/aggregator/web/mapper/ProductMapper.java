package ru.vakoom.gunmarket.aggregator.web.mapper;

import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.aggregator.web.dto.ExtendedProductDto;
import ru.vakoom.gunmarket.aggregator.web.dto.ProductDto;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<ProductDto> productsToProductDtos(List<Product> products) {
        return products.stream().map(this::productToProductDto).collect(Collectors.toList());
    }

    public Set<ProductDto> productsToProductDtos(Set<Product> products) {
        return products.stream().map(this::productToProductDto).collect(Collectors.toSet());
    }

    public ExtendedProductDto productToExtendedProductDto(Product product) {
        return new ExtendedProductDto().setProductId(product.getProductId())
                .setName(product.getName())
                .setBrand(product.getBrand().getShortName())
                .setType(product.getType())
                .setCompatibleProduct(productsToProductDtos(product.getCompatibleProduct()));
    }

    public ProductDto productToProductDto(Product product) {
        return new ProductDto().setProductId(product.getProductId())
                .setName(product.getName())
                .setBrand(product.getBrand().getShortName())
                .setImageUrl(product.getImageUrl())
                .setType(product.getType());
    }

}
