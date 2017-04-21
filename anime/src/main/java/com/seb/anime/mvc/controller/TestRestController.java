package com.seb.anime.mvc.controller;

import com.seb.anime.creator.AnimeGenerator;
import com.seb.anime.jpa.dao.AnimeRepository;
import com.seb.anime.jpa.db.model.Anime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private AnimeGenerator animeGenerator;


    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public @ResponseBody
    Anime test(@RequestParam String text) {
        return animeRepository.save(new Anime(text));
    }

    @RequestMapping(value = "/test1", method = {RequestMethod.GET})
    public @ResponseBody
    String test1() {
        StringBuilder sb = new StringBuilder();
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            try {
                if (applicationContext.getBean(beanName).getClass().getPackage().getName().startsWith("com.seb"))
                    sb.append(beanName).append("\n");
            } catch (NullPointerException e) {
                System.err.println(beanName);
                System.err.println(applicationContext.getBean(beanName).getClass().getPackage());
            }
        }
        return sb.toString();
    }

}
