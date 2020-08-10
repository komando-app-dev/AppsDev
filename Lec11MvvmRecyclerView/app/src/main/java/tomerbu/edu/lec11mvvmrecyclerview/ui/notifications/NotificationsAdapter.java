package tomerbu.edu.lec11mvvmrecyclerview.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tomerbu.edu.lec11mvvmrecyclerview.R;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{
    //להציג רשימה:
    List<String> data;//60
    //בנאי:
    public NotificationsAdapter(List<String> data) {
        this.data = data;
    }
    //אנחנו רוצים להציג אלמנט חוזר:
    //ליצור מופע של אלמנט בודד.
    //ViewHolder -> TextView, ImageView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //0) each View instance has the context:
        Context c = parent.getContext();
        //1) LayoutInflater from context
        LayoutInflater inflater = LayoutInflater.from(c);
        //Context has access to Res folder!

        //View v = inflate an xml file:
        //כדי ליצור מופע של View מנפחים XML
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(itemView);
        return vh;
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.))
    }
    @Override//60
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String datum = data.get(position);
        //bind data to the views (By position)
        holder.tvItem.setText(datum);//*views in itemView
    }

    //כמה שורות יש בRecycler
    @Override
    public int getItemCount() {
        return data.size();
    }
    //inner class that extends View Holder

    //Java class for a single item (Row)

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        //properties:
        TextView tvItem;

        //Constructor:
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }

        //void populate(Person p){
            //tvItem.setText(p.firstName);
       // }
    }
}
