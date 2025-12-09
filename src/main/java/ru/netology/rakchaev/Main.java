package ru.netology.rakchaev;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        NetflixMovieService service = new NetflixMovieService();
        System.out.println(service.getMostPopularCountryByType(ContentType.MOVIE));
        List<NetflixMovie> list = service.getMovieByDateAddedRange(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1));
        list.forEach(movie -> System.out.println(movie.print()));
    }
}