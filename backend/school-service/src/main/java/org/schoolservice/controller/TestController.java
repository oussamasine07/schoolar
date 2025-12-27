package org.schoolservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test-school")
    public String testingSchool () {
        return "school success";
    }

}
