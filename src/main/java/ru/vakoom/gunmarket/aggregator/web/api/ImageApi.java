package ru.vakoom.gunmarket.aggregator.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

public interface ImageApi {

    @ApiOperation(value = "Get product image",
            notes = "Get product image by id",
            response = ResponseEntity.class)
    ResponseEntity<byte[]> getProductImage(long productId);

}
