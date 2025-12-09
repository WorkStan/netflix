package ru.netology.rakchaev;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetflixMovieService implements MovieServiceInterface {

    private final Map<String, NetflixMovie> netflixMovies = new HashMap<>();

    private static boolean isValidId(String id) {
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

    public NetflixMovieService(InputStream inputStream) {
        if (inputStream != null) {
            parseStreamAndPutData(inputStream);
        }
    }

    private void parseStreamAndPutData(InputStream inputStream) {
        try (
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
        ) {
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
                    System.err.println("Line " + lineNumber + " skipped: Invalid format - expected 12 columns, got " + vals.length);
                    continue;
                }

                String id = vals[0].trim();

                if (!isValidId(id)) {
                    System.err.println("Line " + lineNumber + " skipped: Invalid ID format - '" + id + "'");
                    continue;
                }

                String type = vals[1].trim();
                String title = vals[2].trim();
                String director = vals[3].trim();

                String cast = vals[4].trim().replaceAll("^\"+|\"+$", "");
                String[] casts = cast.split(", ");

                String country = vals[5].trim().replaceAll("^\"+|\"+$", "");
                String[] countries = country.split(", ");

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

                String listedInText = vals[10].trim().replaceAll("^\"+|\"+$", "");
                String[] listedIn = listedInText.split(", ");

                String description = vals[11].trim().replaceAll("^\"+|\"+$", "");

                NetflixMovie movie = new NetflixMovie(id, type, title, director, casts, countries, releaseYear, rating, duration, listedIn, description, dateAdded);

                create(movie);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<NetflixMovie> findAll() {
        return netflixMovies.values();
    }

    public NetflixMovie findById(String id) {
        return netflixMovies.get(id);
    }

    public void create(NetflixMovie netflixMovie) {
        if (!netflixMovies.containsKey(netflixMovie.getId()))
            netflixMovies.put(netflixMovie.getId(), netflixMovie);
    }

    public void update(NetflixMovie netflixMovie) {
        netflixMovies.put(netflixMovie.getId(), netflixMovie);
    }

    public void delete(String id) {
        if (!netflixMovies.containsKey(id))
            netflixMovies.remove(id);
    }

    @Override
    public Country getMostPopularCountryByType(ContentType contentType) {
        if (contentType == null) {
            return null;
        }

        Map<Country, Integer> countryCounts = new HashMap<>();

        netflixMovies.forEach((id, movie) -> {
            if (movie != null && movie.getContentType().equals(contentType)) {
                List<Country> countries = movie.getCountries();

                for (Country country : countries) {
                    countryCounts.put(country, countryCounts.getOrDefault(country, 0) + 1);
                }
            }
        });

        if (countryCounts.isEmpty()) {
            return null;
        }

        Country mostPopular = null;
        int maxCount = 0;

        for (Map.Entry<Country, Integer> entry : countryCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }

        return mostPopular;
    }

    @Override
    public Collection<NetflixMovie> getMovieByDateAddedRange(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null) {
            dateFrom = LocalDate.MIN;
        }
        if (dateTo == null) {
            dateTo = LocalDate.MAX;
        }

        Collection<NetflixMovie> listMovies = new ArrayList<>();
        for (NetflixMovie movie : netflixMovies.values()) {
            if(movie.getDateAdded() == null) {
                continue;
            }
            if(movie.getDateAdded().isAfter(dateFrom) && movie.getDateAdded().isBefore(dateTo)) {
                listMovies.add(movie);
            }
        }

        return listMovies;
    }
}
