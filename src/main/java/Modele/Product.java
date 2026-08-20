package Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal unitPrice;

    public Product(String id, String name, String description, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
    }
}
