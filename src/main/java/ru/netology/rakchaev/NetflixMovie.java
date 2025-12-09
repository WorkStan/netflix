package ru.netology.rakchaev;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @JsonIgnore
    public String getCountriesFormatted() {
        return countries.toString();
    }

    public String getType() {
        return type.getDisplayName();
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director.toString();
    }
    @JsonProperty("casts")
    public List<String> getCasts() {
        List<String> actorNames = new ArrayList<>();
        for (Actor actor : casts) {
            actorNames.add(actor.toString());
        }
        return actorNames;
    }

    @JsonProperty("countries")
    public List<String> getCountriesNames() {
        List<String> countryNames = new ArrayList<>();
        for (Country country : countries) {
            if (country != null) {
                countryNames.add(country.toString());
            }
        }
        return countryNames;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Integer getReleaseYear() { return releaseYear; }

    public String getRating() { return rating; }

    public Integer getDuration() { return duration; }

    @JsonProperty("listedIn")
    public List<String> getListedIn() {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : listedIn) {
            categoryNames.add(category.toString());
        }
        return categoryNames;
    }

    public String getDescription() { return description; }

    @JsonIgnore
    public ContentType getContentType() {
        return type;
    }

    @JsonIgnore
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

    private void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    private String getDateAddedText() {
        if (dateAdded == null) {
            return "";
        }
        return this.dateAdded.toString();
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
}
