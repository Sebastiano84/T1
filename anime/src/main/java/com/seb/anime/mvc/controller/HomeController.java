package com.seb.anime.mvc.controller;

import com.seb.anime.json.JsonFileLoader;
import com.seb.anime.json.toHtml.Paragraph;
import com.seb.anime.mvc.forms.HomeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private JsonFileLoader jsonFileLoader;

    @RequestMapping(value = {"/home", "/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home(@ModelAttribute HomeForm homeForm, BindingResult bindingResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("name", name);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername(); //get logged in username
        model.addAttribute("username", username);
        model.addAttribute("homeForm", homeForm);
        System.out.println(model);
        return "home";
    }

    @RequestMapping(value = "/submitCode", method = RequestMethod.POST)
    public String submitCode(@ModelAttribute("animeCode") String animeCode,@ModelAttribute HomeForm homeForm, BindingResult bindingResult, Model model) {
        model.addAttribute("animeCode", animeCode);
        for (Paragraph error : jsonFileLoader.validateText(animeCode).getErrors()) {
            bindingResult.addError(new ObjectError(error.getText(), error.getText()));
        }


        return home(homeForm,bindingResult,model);
    }
}
