package com.ivi.dyndns.webszatyor.service;

import com.ivi.dyndns.webszatyor.model.User;
import com.ivi.dyndns.webszatyor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean saveUser(User user) {
        // TODO Auto-generated method stub
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public User findUserByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findUserByEmail(email);
    }

}
