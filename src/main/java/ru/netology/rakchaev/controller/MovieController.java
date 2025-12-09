package ru.netology.rakchaev.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.rakchaev.model.ContentType;
import ru.netology.rakchaev.model.Country;
import ru.netology.rakchaev.service.NetflixMovieService;
import ru.netology.rakchaev.model.NetflixMovie;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/movie")
public class MovieController {
    private final NetflixMovieService service;

    public MovieController(NetflixMovieService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<NetflixMovie> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public NetflixMovie getOne(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PostMapping()
    public void add(@RequestBody NetflixMovie netflixMovie) {
        service.create(netflixMovie);
    }

    @PostMapping("{id}")
    public void update(@RequestBody NetflixMovie netflixMovie) {
        service.update(netflixMovie);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("most-popular-by-type/{type}")
    public Country getMostPopularByType(@PathVariable("type") ContentType type) {
        return service.getMostPopularCountryByType(type);
    }

    @PostMapping("get-by-dat-added")
    public Collection<NetflixMovie> add(@RequestBody LocalDate dateFrom, @RequestBody LocalDate dateTo) {
        return service.getMovieByDateAddedRange(dateFrom, dateTo);
    }

    @GetMapping("get-all-duration")
    public Integer getAllDuration() {
        return service.getAllDuration();
    }
}