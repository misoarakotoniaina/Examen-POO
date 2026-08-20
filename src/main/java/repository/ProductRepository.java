package repository;

import Modele.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll() throws SQLException;
    Optional<Product> findById(Long id) throws SQLException;
    Product save(Product product) throws SQLException;
    void updateStock(Connection conn, Long productId, int newQuantity) throws SQLException;
}

