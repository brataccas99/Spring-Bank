package com.springBank.code.REST.UsersREST;

import com.springBank.code.Services.UserServices.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class userREST {

    @Autowired
    UserService userService;
    private static final Logger logger = LogManager.getLogger(userREST.class.getName());

}
