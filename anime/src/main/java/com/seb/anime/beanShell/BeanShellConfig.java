package com.seb.anime.beanShell;

import bsh.Interpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanShellConfig {
    @Bean
    public Interpreter interpreter() {
        Interpreter interpreter = new Interpreter();
        interpreter.setErr(System.err);
        return interpreter;
    }
}
