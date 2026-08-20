package Modele;

import java.time.Instant;
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

    public StockMovement(String id, Instant createdAt, movementType movementType, int quantity) {
        this.id = id;
        this.createdAt = createdAt;
        MovementType = movementType;
        this.quantity = quantity;
    }

}
