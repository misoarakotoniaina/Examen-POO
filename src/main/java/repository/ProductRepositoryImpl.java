package repository;


import Modele.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final DatabaseConfig databaseConfig;

    public ProductRepositoryImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, description, price, quantity_in_stock, created_at FROM product";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String sql = "SELECT id, name, description, price, quantity_in_stock, created_at FROM product WHERE id = ?";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToProduct(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Product save(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, description, price, quantity_in_stock) VALUES (?, ?, ?, ?) RETURNING id, created_at";

        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setInt(4, product.getQuantityInStock());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product.setId(rs.getLong("id"));
                    Timestamp timestamp = rs.getTimestamp("created_at");
                    if (timestamp != null) {
                        product.setCreatedAt(timestamp.toInstant());
                    }
                }
            }
        }
        return product;
    }

    @Override
    public void updateStock(Connection conn, Long productId, int newQuantity) throws SQLException {
        String sql = "UPDATE product SET quantity_in_stock = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setLong(2, productId);
            stmt.executeUpdate();
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantityInStock(rs.getInt("quantity_in_stock"));

        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            product.setCreatedAt(timestamp.toInstant());
        }

        return product;
    }
}