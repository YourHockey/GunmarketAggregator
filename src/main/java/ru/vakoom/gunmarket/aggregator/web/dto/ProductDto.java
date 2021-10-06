package ru.vakoom.gunmarket.aggregator.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.vakoom.gunmarket.commondatalayer.model.Type;

@Setter
@Getter
@Accessors(chain = true)
public class ProductDto {
    private Long productId;
    private String name;
    private String brand;
    private Type type;
    private String imageUrl;
}
