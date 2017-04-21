package com.seb.anime.mvc.controller;

import com.seb.anime.json.JsonFileLoader;
import com.seb.anime.json.toHtml.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
    @Autowired
    private JsonFileLoader jsonFileLoader;

    
    @RequestMapping(value = "/validateCode", method = {RequestMethod.POST})
    public @ResponseBody ValidationResult validateCode(@RequestBody String text) {
        return jsonFileLoader.validateText(text);
    }

}
