package com.ivi.dyndns.webszatyor.handler;

import com.ivi.dyndns.webszatyor.model.Address;
import com.ivi.dyndns.webszatyor.model.Cart;
import com.ivi.dyndns.webszatyor.model.RegisterModel;
import com.ivi.dyndns.webszatyor.model.User;
import com.ivi.dyndns.webszatyor.service.AddressService;
import com.ivi.dyndns.webszatyor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    @Qualifier("addressService")
    @Autowired
    private AddressService addressService;

    public RegisterModel init() {

        return new RegisterModel();

    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel registerModel) {
        String transitionValue = "success";

        // Getting User

        User user = registerModel.getUser();
        if (user.getRole().equalsIgnoreCase("USER")) {
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        // save User
        userService.saveUser(user);

        // Getting Address

        Address billing = registerModel.getBilling();

        billing.setUser(user);
        billing.setBilling(true);
        addressService.saveAddress(billing);

        return transitionValue;
    }

    public String validateUser(User user, MessageContext messageContext) {

        String transitionValue = "success";
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            messageContext.addMessage(new MessageBuilder().error().source("confirmPassword")
                    .defaultText("Password does not match the confirm Password").build());
            transitionValue = "failure";
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            messageContext.addMessage(
                    new MessageBuilder().error().source("email").defaultText("Email is already in use").build());
            transitionValue = "failure";
        }

        return transitionValue;
    }

}
