package tomerbu.edu.lec15retrofit.movies;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
//דורש JAVA8
/**
 עבודה עם GSON
 כל התכונות צריכות להיות זהות למה שיש בJson
 (מאחר וזה כלי אוטומטי)
 מתבססת על Reflection
 */
@Entity//(tableName = "movies") //Entity, DAO (interface), Database (abstract)
public class Movie {
    //properties:
    private String title;
    private String overview;
    //add String thumbnail

    @PrimaryKey
    private long id;

    @SerializedName("poster_path") //השם שקיים בJson
    private String posterPath;//השם שבחרנו
    //יש עוד הרבה תכונות במחלקה Movie

    //דרישה - בנאי ריק
    //דרישה JAVA 8
    public Movie() {
    }

    //Room should ignore this constructor:
    @Ignore // tell room to ignore this constructor
    public Movie(String title, String overview, long id, String posterPath) {
        this.title = title;
        this.overview = overview;
        this.id = id;
        this.posterPath = posterPath;
    }

    //getters - setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }
}
