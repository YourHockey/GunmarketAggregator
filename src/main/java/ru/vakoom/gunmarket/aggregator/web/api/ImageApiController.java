package ru.vakoom.gunmarket.aggregator.web.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vakoom.gunmarket.aggregator.service.ImageService;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageApiController implements ImageApi {
    private final ImageService imageService;

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE) // try jpg)))
    public ResponseEntity<byte[]> getProductImage(@PathVariable("id") long productId) {
        return ResponseEntity.of(imageService.getProductImage(productId));
    }
}
