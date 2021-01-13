package com.lastminute.configuration.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
@ApiIgnore
public class SwaggerRedirectController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
