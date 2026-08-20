package service;


import Modele.Product;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws SQLException {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'id : " + id));
    }
}
