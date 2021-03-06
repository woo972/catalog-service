package com.wowls.catalogservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog implements Serializable {
    private String productId;
    private Integer stock;
    private Integer unitPrice;
    private LocalDateTime createdAt;
}
