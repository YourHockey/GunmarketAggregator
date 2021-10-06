package ru.vakoom.gunmarket.aggregator.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ru.vakoom.gunmarket.aggregator.web.dto.ExtendedProductDto;

public interface ProductDetailsPageApi {

    @ApiOperation(value = "Get extended product dto by id",
            notes = "Get extended product dto by id",
            response = ResponseEntity.class)
    ResponseEntity<ExtendedProductDto> getById(long productId);

}
