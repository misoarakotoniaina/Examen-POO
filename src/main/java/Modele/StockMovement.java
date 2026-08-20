package Modele;

import java.time.Instant;

public class StockMovement {
        private enum movementType{
            IN, OUT
        }
    private String id;
    private Instant createdAt;
    private movementType MovementType;
    private int quantity;
}
