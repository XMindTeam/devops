package com.intel.spring.boot.sample.boot.web.controller;

import com.intel.spring.boot.sample.boot.web.dao.CharacterRepository;
import com.intel.spring.boot.sample.boot.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ecic Chen on 2016/2/28.
 */
@RestController
public class CharacterController {

    @Autowired
    private CharacterRepository repository;

    @RequestMapping("/characters")
    List<Character> characters() {
        return repository.findAll();
    }

    @RequestMapping(value = "/characters/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Integer id) {
        repository.delete(id);
    }

    @RequestMapping("/characters/{id}")
    Character character(@PathVariable("id") Integer id){
        return repository.findOne(id);
    }
}
