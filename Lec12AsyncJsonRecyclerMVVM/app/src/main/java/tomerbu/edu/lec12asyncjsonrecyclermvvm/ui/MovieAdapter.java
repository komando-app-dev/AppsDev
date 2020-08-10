package tomerbu.edu.lec12asyncjsonrecyclermvvm.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tomerbu.edu.lec12asyncjsonrecyclermvvm.R;
import tomerbu.edu.lec12asyncjsonrecyclermvvm.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //properties:
    List<Movie> movies;

    //TODO: 2) get the event and hold it:
    MutableLiveData<Movie> selectedMovie;

    //constructor:
    public MovieAdapter(List<Movie> movies, MutableLiveData<Movie> selectedMovie) {
        this.movies = movies;
        this.selectedMovie = selectedMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the xml and return a new ViewHolder:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the movie at position:
        Movie m = movies.get(position);

        holder.tvTitle.setText(m.getTitle());
        holder.tvOverview.setText(m.getOverview());
        //TODO: How to show the image?

        //1)download the image -> store it to disk
        //2) BitmapFactory(in)->Bitmap
        //3) holder.setImageBitmap(2)
        Picasso.get().load(m.getBackdropPath()).into(holder.ivBackdrop);

        holder.itemView.setOnClickListener(v -> {
            //movie was clicked.
            //my job is to report to the fragment:
            //TODO:3) once the movie was clicked -> Trigger the event.
            selectedMovie.postValue(m);

            //Model: title, jsonLink
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //תפקידו של VH להחזיק תכונות של הViews ברמת המחלקה ולמצוא אותם לפי ID
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBackdrop;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //find views:
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivBackdrop = itemView.findViewById(R.id.ivBackDrop);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
