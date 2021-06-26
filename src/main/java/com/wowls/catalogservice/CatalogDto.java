package com.wowls.catalogservice;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CatalogDto implements Serializable {
    private long id;
    private String productId;
    private Integer stock;
    private Integer unitPrice;
    private Integer totalPrice;

    private String userId;
    private String orderId;
}
