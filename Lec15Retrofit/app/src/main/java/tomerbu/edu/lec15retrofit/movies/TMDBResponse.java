package tomerbu.edu.lec15retrofit.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * נדרשנו ע"י GSON ליצור מחלקות שדומות לJSON
 */
public class TMDBResponse { //MovieManager
    //properties:

    @SerializedName("results")
    private ArrayList<Movie> movies;

    //בנאי ריק
    public TMDBResponse() {}

    //getters and setters
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    //toString
    @Override
    public String toString() {
        return "TMDBResponse{" +
                "movies=" + movies +
                '}';
    }
}
