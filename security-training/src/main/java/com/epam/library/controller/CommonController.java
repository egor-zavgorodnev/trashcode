package com.epam.library.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CommonController {

    @GetMapping("/getNote")
    public String getNote() {
        return "Общая инфа получаемая GETом";
    }


    @PostMapping("/somethingSecret")
    public String somethingSecret() {
        return "Тайная запись для админа";
    }

    @PatchMapping("/anno")
    @PreAuthorize("hasAuthority('read')") //аналог веб-конфига
    public String anno() {
        return "Доступ через аннотацию @preAuthorize";
    }

}

