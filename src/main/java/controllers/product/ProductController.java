package controllers.product;

import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.ProductRepository;

import java.util.List;

public class ProductController {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("database");
    EntityManager em = emf.createEntityManager();

    private final ProductRepository productRepository = new ProductRepository(em);

    public Product save(Product product) {
        em.getTransaction().begin();
        Product newProduct = productRepository.save(product);
        em.getTransaction().commit();
        return newProduct;
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        productRepository.deleteById(id);
        em.getTransaction().commit();
    }
}
