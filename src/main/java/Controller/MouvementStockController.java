package Controller;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import Modele.StockMovement;
import service.ProductService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MouvementStockController implements HttpHandler {

    private final MouvementStockService service;

    public MouvementStockController(MouvementStockService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        try {

            Map<String, String> params = parseQuery(exchange.getRequestURI().getQuery());
            String typeParam = params.get("type");

            List<MouvementStock> mouvements;


            if (typeParam != null && !typeParam.isBlank()) {
                TypeMouvement type;
                try {
                    type = TypeMouvement.valueOf(typeParam.toUpperCase());
                } catch (IllegalArgumentException e) {
                    sendJson(exchange, 400, "{\"erreur\":\"Paramètre 'type' invalide, valeurs acceptées : IN, OUT\"}");
                    return;
                }
                mouvements = service.getMouvementsByType(type);
            } else {
                mouvements = service.getAllMouvements();
            }

            String json = toJson(mouvements);
            sendJson(exchange, 200, json);

        } catch (Exception e) {
            sendJson(exchange, 500, "{\"erreur\":\"Erreur interne du serveur\"}");
        }
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isBlank()) {
            return params;
        }
        for (String pair : query.split("&")) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    private void sendJson(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private String toJson(List<Controller.MouvementStock> mouvements) {
        return "[]";
    }
}