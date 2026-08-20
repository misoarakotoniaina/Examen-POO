package Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class StockMovement {
    private enum movementType{
            IN, OUT
        }
    private String id;
    private Instant createdAt;
    private movementType MovementType;
    private int quantity;
    List<Product> products= new ArrayList<>();

    public StockMovement(String id, Instant createdAt, movementType movementType, int quantity) {
        this.id = id;
        this.createdAt = createdAt;
        MovementType = movementType;
        this.quantity = quantity;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
