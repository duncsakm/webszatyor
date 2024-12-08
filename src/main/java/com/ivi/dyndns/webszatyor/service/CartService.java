package com.ivi.dyndns.webszatyor.service;

import com.ivi.dyndns.webszatyor.model.Cart;

public interface CartService {

    boolean saveCart(Cart cart);

    boolean updateCart(Cart cart);

    Cart findCart();

}
