package run.netology.rakchaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/netflix_titles.csv");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        while (reader.ready()) {
            System.out.println(reader.readLine());
        }
    }
}