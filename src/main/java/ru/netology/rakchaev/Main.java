package ru.netology.rakchaev;

public class Main {
    public static void main(String[] args) {
        NetflixMovieService service = new NetflixMovieService();
        System.out.println(service.getMostPopularCountryByType(ContentType.MOVIE));
    }
}