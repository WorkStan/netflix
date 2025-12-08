package ru.netology.rakchaev;

import java.time.LocalDate;
import java.util.Arrays;

public class NetflixMovie {
    private final String id;

    public NetflixMovie(String id, String type, String title, String director, String[] casts, String country, Integer releaseYear, String rating, Integer duration, String listedIn, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.casts = casts;
        this.country = country;
        this.dateAdded = dateAdded;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.listedIn = listedIn;
        this.description = description;
    }

    private String type;
    private String title;
    private String director;
    private String[] casts;
    private String country;
    private LocalDate dateAdded;
    private Integer releaseYear;
    private String rating ;
    private Integer duration;
    private String listedIn;
    private String description;

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    private String getDateAddedText() {
        if (dateAdded == null) {
            return "";
        }
        return this.dateAdded.toString();
    }

    private String getCastsFormatted() {
        return Arrays.toString(this.casts);
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
                    """.formatted(id, type, title, director, Arrays.toString(casts), country, this.getDateAddedText(), releaseYear.toString(), rating, duration, listedIn, description);
    }
}
