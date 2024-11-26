package repositories;

import entities.Product;
import jakarta.persistence.*;

import java.util.List;

public class ProductRepository {
    private final EntityManager em;

    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public Product save(Product product) {
        if (product.getId() == null) { // id nulo, então é objeto novo, persista
            em.persist(product);
        } else {
            product = em.merge(product); // id != null, então é objeto antigo, atualize
        }
        return product;
    }

    public Product findById(Long id) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        if(product != null) {
            em.remove(product);
        }
        else {
            throw new IllegalArgumentException("Product not found!");
        }
    }
}
