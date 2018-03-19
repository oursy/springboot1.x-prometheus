package com.example.demo1;


import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AllArgsConstructor
public class CityController {


    private final CityRepository cityRepository;

    @GetMapping(value = "cities")
    public List<City> all() {
        return cityRepository.findAll();
    }

    @GetMapping(value = "cities/{name}")
    public City byName(@PathVariable String name) {
        return cityRepository.findByNameIgnoringCase(name).get();
    }
}
