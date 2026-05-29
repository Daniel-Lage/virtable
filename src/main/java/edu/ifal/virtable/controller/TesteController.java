package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste-protegido")
    public String testeProtegido() {
        return "Você acessou uma rota protegida com JWT!";
    }
}