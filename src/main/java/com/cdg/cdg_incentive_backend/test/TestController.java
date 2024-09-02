package com.cdg.cdg_incentive_backend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class TestController {
    @GetMapping("/test")
    public String homePage() {
        return "Hello from Spring boot app";
    }

    @GetMapping("/test/eiei")
    public String homePage2() {
        return "Hello from Spring boot app2";
    }

    @PostMapping("/test/eiei")
    public String homePage3() {
        return "Hello from Spring boot app2";
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "Private Route";
    }
}
