package com.ivi.dyndns.webszatyor.service;

import com.ivi.dyndns.webszatyor.model.User;

public interface UserService {

    boolean saveUser(User user);

    User findUserByEmail(String email);

}
