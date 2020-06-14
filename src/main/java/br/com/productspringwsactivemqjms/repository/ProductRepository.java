package br.com.productspringwsactivemqjms.repository;

import br.com.productspringwsactivemqjms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
