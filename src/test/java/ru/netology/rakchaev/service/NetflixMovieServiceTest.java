package ru.netology.rakchaev.service;

import org.junit.jupiter.api.Test;
import ru.netology.rakchaev.model.NetflixMovie;

public class NetflixMovieServiceTest {

    @Test
    void getAllDuration() {
        NetflixMovieService service = new NetflixMovieService();
        service.netflixMovies.clear();
    }
}
