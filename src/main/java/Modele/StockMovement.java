package Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
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
}
