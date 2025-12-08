package ru.netology.rakchaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static boolean isValidId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^s\\d+$");
        return pattern.matcher(id.trim()).matches();
    }

    public static Optional<LocalDate> parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return Optional.empty();
        }

        String cleaned = dateString.trim()
                .replaceAll("^\"|\"$", "")
                .trim();

        if (cleaned.equalsIgnoreCase("")) {
            return Optional.empty();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                .withLocale(Locale.ENGLISH);

        try {
            return Optional.of(LocalDate.parse(cleaned, formatter));
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + cleaned);
            return Optional.empty();
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/netflix_titles.csv");

        if (inputStream == null) {
            System.err.println("File not found!");
            return;
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        Integer fileLines = 10_000;
        NetflixMovie[] netflixMovies = new NetflixMovie[fileLines];

        int lineNumber = 0;
        if (reader.ready()) {
            reader.readLine();
            lineNumber++;
        }

        while (reader.ready()) {
            lineNumber++;

            String line = reader.readLine();

            String[] vals = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            if (vals.length != 12) {
                System.err.println("Line " + lineNumber + ": Invalid format - expected 12 columns, got " + vals.length);
                continue;
            }

            String id = vals[0].trim();

            if (!isValidId(id)) {
                System.err.println("Line " + lineNumber + ": Invalid ID format - '" + id + "'");
                continue;
            }

            String type = vals[1].trim();
            String title = vals[2].trim();
            String director = vals[3].trim();

            String cast = vals[4].replaceAll("^\"+|\"+$", "");
            String[] casts = cast.split(", ");

            String country = vals[5].trim().replaceAll("^\"+|\"+$", "");

            Optional<LocalDate> dateAdded = parseDate(vals[6]);

            Integer releaseYear = Integer.parseInt(vals[7]);
            String rating = vals[8];

            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(vals[9]);
            Integer duration;
            if (matcher.find()) {
                duration = Integer.parseInt(matcher.group());
            } else {
                duration = null;
            }

            String listedIn = vals[10];
            String description = vals[11].trim().replaceAll("^\"+|\"+$", "");

            NetflixMovie movie = new NetflixMovie(id, type, title, director, casts, country, releaseYear, rating, duration, listedIn, description);
            dateAdded.ifPresent(movie::setDateAdded);

            netflixMovies[lineNumber] = movie;

            System.out.println(movie.print());
        }
    }
}