package ru.vakoom.gunmarket.aggregator.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vakoom.gunmarket.aggregator.service.ProductService;
import ru.vakoom.gunmarket.aggregator.web.dto.ExtendedProductDto;
import ru.vakoom.gunmarket.aggregator.web.mapper.ProductMapper;
import ru.vakoom.gunmarket.commondatalayer.model.Product;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductDetailsPageApiController implements ProductDetailsPageApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @CrossOrigin
    @GetMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExtendedProductDto> getById(@PathVariable long productId) {
        Product product = productService.getById(productId);
        return ResponseEntity.ok(productMapper.productToExtendedProductDto(product));
    }

}
