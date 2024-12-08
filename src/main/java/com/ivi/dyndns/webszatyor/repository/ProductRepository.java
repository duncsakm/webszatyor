package com.ivi.dyndns.webszatyor.repository;

import com.ivi.dyndns.webszatyor.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByCategoryId(Integer categoryId);

}
