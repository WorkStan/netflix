package ru.netology.rakchaev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collection;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        try (
//            InputStream inputStream = Main.class.getResourceAsStream("/netflix_titles.csv");
//        ) {
//            NetflixMovieService service = new NetflixMovieService(inputStream);
//
//            Collection<NetflixMovie> movies = service.getMovieByDateAddedRange(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1));
//            movies.forEach(movie -> System.out.println(movie.print()));
//
//            System.out.println(service.getMostPopularCountryByType(ContentType.MOVIE));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}