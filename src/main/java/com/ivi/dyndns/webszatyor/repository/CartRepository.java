package com.ivi.dyndns.webszatyor.repository;

import com.ivi.dyndns.webszatyor.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cartRepository")
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
