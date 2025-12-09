package ru.netology.rakchaev;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NetflixMovie {
    private final String id;
    private ContentType type;
    private String title;
    private Actor director;
    private List<Actor> casts;
    private List<Country> countries;
    private LocalDate dateAdded;
    private Integer releaseYear;
    private String rating;
    private Integer duration;
    private List<Category> listedIn;
    private String description;

    public NetflixMovie(String id, String type, String title, String director, String[] casts, String[] countries, Integer releaseYear, String rating, Integer duration, String[] listedIn, String description, Optional<LocalDate> dateAdded) {
        this.id = id;
        this.type = ContentType.fromString(type);
        this.title = title;
        if (!director.isEmpty()) {
            this.director = Actor.of(director);
        }
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.description = description;

        dateAdded.ifPresent(this::setDateAdded);

        setListedInFromString(listedIn);
        setCountriesFromString(countries);
        setCastsFromString(casts);
    }

    public String getId() {
        return id;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    private String getDateAddedText() {
        if (dateAdded == null) {
            return "";
        }
        return this.dateAdded.toString();
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    private String getCastsFormatted() {
        return casts.toString();
    }

    private String getListedInFormatted() {
        return listedIn.toString();
    }

    private String getDirectorFormatted() {
        if (director == null) {
            return "";
        }
        return director.toString();
    }

    private void setListedInFromString(String[] listedInStrings) {
        List<Category> list = new ArrayList<>();
        for (String listedInString : listedInStrings) {
            if (!listedInString.isEmpty()) {
                Category category = Category.of(listedInString);
                list.add(category);
            }
        }
        this.listedIn = list;
    }

    private void setCastsFromString(String[] castsString) {
        List<Actor> list = new ArrayList<>();
        for (String castString : castsString) {
            if (!castString.isEmpty()) {
                Actor actor = Actor.of(castString);
                list.add(actor);
            }
        }
        this.casts = list;
    }

    private void setCountriesFromString(String[] countriesString) {
        List<Country> list = new ArrayList<>();
        for (String countryString : countriesString) {
            if (!countryString.isEmpty()) {
                Country country = Country.of(countryString);
                list.add(country);
            }
        }
        this.countries = list;
    }

    public String getCountriesFormatted() {
        return countries.toString();
    }

    public String getType() {
        return type.getDisplayName();
    }

    public ContentType getContentType() {
        return type;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public String print() {
        return """
                ID: %s
                Type: %s
                Title: %s
                Director: %s
                Casts: %s
                Country: %s
                Added at: %s
                Release: %s
                Rating: %s
                Duration: %s minutes
                Listed at: %s
                Description: %s
                """.formatted(id, getType(), title, getDirectorFormatted(), getCastsFormatted(), getCountriesFormatted(), getDateAddedText(), releaseYear.toString(), rating, duration, getListedInFormatted(), description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NetflixMovie that = (NetflixMovie) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
