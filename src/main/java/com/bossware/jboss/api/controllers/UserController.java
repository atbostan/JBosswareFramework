package com.bossware.jboss.api.controllers;

import com.bossware.jboss.api.controllers.base.JBossControllerBase;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends JBossControllerBase {

    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",value = "Bearer JWT Token",paramType = "header")
    })
    @GetMapping
    public String fun(){
        return "heelo";
    }
}
