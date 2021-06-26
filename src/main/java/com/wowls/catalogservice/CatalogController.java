package com.wowls.catalogservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping("/catalgos")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){
        List<ResponseCatalog> responseCatalogList = new ArrayList<>();
        catalogService.getCatalogs().forEach(
                catalogDto -> responseCatalogList.add(new ModelMapper().map(catalogDto, ResponseCatalog.class))
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseCatalogList);
    }
}
