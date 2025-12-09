package ru.netology.rakchaev;

import org.springframework.web.bind.annotation.*;

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
}
