package com.ivi.dyndns.webszatyor.controllers;

import com.ivi.dyndns.webszatyor.model.User;
import com.ivi.dyndns.webszatyor.model.UserModel;
import com.ivi.dyndns.webszatyor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

    private UserModel userModel = null;

    @ModelAttribute("userModel")
    public UserModel getUserModel() {

        if (httpSession.getAttribute("userModel") == null) {
            // add the user model

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(authentication.getName());
            if (user != null) {
                userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setEmail(user.getEmail());
                userModel.setRole(user.getRole());
                userModel.setFullName(user.getFirstName() + " " + user.getLastName());

                if (userModel.getRole().equalsIgnoreCase("USER")) {
                    // set the cart only if user is a buyer

                    userModel.setCart(user.getCart());
                }
                httpSession.setAttribute("userModel", userModel);
            }

        }

        return (UserModel) httpSession.getAttribute("userModel");
    }

}
