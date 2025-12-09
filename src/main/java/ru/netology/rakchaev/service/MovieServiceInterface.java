package ru.netology.rakchaev.service;

import ru.netology.rakchaev.model.ContentType;
import ru.netology.rakchaev.model.Country;
import ru.netology.rakchaev.model.NetflixMovie;

import java.time.LocalDate;
import java.util.Collection;

public interface MovieServiceInterface {
    Country getMostPopularCountryByType(ContentType contentType);
    Collection<NetflixMovie> getMovieByDateAddedRange(LocalDate dateFrom, LocalDate dateTo);
    Integer getAllDuration();
}
