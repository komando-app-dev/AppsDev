package tomerbu.edu.lec15retrofit.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//Application Programming interface

//כל העבודה מול TMDB
public interface MovieService {

    //http:google.com/abc?q=tomer&browser=Safari
    @GET("discover/movie")
    Call<TMDBResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TMDBResponse> getTV(@Query("api_key") String apiKey, @Query("pupularity") String  asc);


    //Api with multiple End-point


    //discover/movie/3
    // @GET("discover/movie/{id}")
    //void getMovie(@Path("id") int id, @Query("api_key") String apiKey);
}


//עם כל בקשה צריך לשלוח בQuery String את ה-ApiKey

// @GET("genre/movie/list")
//  Call<GenreResponse>getGenres(@Query("api_key") String apiKey);

//https://api.themoviedb.org/3/genre/movie/list?api_key=b3b1492d3e91e9f9403a2989f3031b0c



//https://api.themoviedb.org/3/discover/movie?api_key=b3b1492d3e91e9f9403a2989f3031b0c

//Base URL of the API:
//https://api.themoviedb.org/3/

//path:
//discover/movie

//query string: אחרי ? מגיע
//api_key=b3b1492d3e91e9f9403a2989f3031b0c