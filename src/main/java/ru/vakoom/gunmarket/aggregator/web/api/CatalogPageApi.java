package ru.vakoom.gunmarket.aggregator.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.vakoom.gunmarket.aggregator.web.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface CatalogPageApi {

    @ApiOperation(value = "Get products dto by parameters",
            notes = "Get products dto by input parameters via query builder",
            response = List.class)
    List<ProductDto> getByParams(Map<String, String> requestParams, Pageable pageable);

    @ApiOperation(value = "Get all products dto",
            notes = "Get all products dto",
            response = ResponseEntity.class)
    ResponseEntity<List<ProductDto>> getAll();

}
