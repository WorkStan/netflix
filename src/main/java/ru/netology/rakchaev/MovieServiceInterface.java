package ru.netology.rakchaev;

import java.time.LocalDate;
import java.util.Collection;

public interface MovieServiceInterface {
    Country getMostPopularCountryByType(ContentType contentType);
    Collection<NetflixMovie> getMovieByDateAddedRange(LocalDate dateFrom, LocalDate dateTo);
}
