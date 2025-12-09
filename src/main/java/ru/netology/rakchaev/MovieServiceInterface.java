package ru.netology.rakchaev;

import java.time.LocalDate;
import java.util.List;

public interface MovieServiceInterface {
    Country getMostPopularCountryByType(ContentType contentType);
    List<NetflixMovie> getMovieByDateAddedRange(LocalDate dateFrom, LocalDate dateTo);
}
