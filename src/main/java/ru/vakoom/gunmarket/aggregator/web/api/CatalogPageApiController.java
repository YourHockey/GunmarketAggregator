package ru.vakoom.gunmarket.aggregator.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gunmarket.model.Product;
import ru.gunmarket.web.dto.ProductDto;
import ru.gunmarket.web.preparer.FilterAndPageable;
import ru.vakoom.gunmarket.aggregator.service.ProductService;
import ru.vakoom.gunmarket.aggregator.web.mapper.ProductMapper;
import ru.vakoom.gunmarket.aggregator.web.preparer.Preparer;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CatalogPageApiController implements CatalogPageApi {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final List<Preparer> preparers;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 3000;

    @CrossOrigin()
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getByParams(@RequestParam Map<String, String> requestParams,
                                        @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER) Pageable pageable) {

        log.info("Incoming request. Params {}. {}", requestParams, pageable);

        FilterAndPageable filterAndPageable = new FilterAndPageable(requestParams, pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Product.class));

        List<Product> products = productService.getAllByParameters(
                filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );

        List<ProductDto> result = productMapper.productsToProductDtos(products);
        log.info("GetAllByParams request result size: {}", requestParams.size());
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/allProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(productMapper.productsToProductDtos(products));
    }

}
