package com.wowls.catalogservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;

    public Iterable<CatalogEntity> getCatalogs() {
        return catalogRepository.findAll();
    }
}
