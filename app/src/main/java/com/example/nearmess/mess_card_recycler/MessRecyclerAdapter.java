package com.example.nearmess.mess_card_recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearmess.Menu;
import com.example.nearmess.R;

import java.util.List;


public class MessRecyclerAdapter extends RecyclerView.Adapter<MessRecyclerAdapter.MessCard>{

    List<MessCardData> dataholder2;

    Context context;

    public MessRecyclerAdapter(List<MessCardData> dataholder2, Context context) {
        this.dataholder2 = dataholder2;
        this.context = context;

    }

    public void setFilteredList(List<MessCardData> filteredList) {
        Log.println(Log.DEBUG,"debug", "Finally"+filteredList);
        this.dataholder2 = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MessCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_mess_card,parent,false);
        return new MessCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessCard holder, int position) {

        final MessCardData temp = dataholder2.get(position);


//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_rating()); // Check the rating value
//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_name()); // Check the rating value
//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_status()); // Check the rating value
//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_timing()); // Check the rating value
//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_seating()); // Check the rating value
//        Log.d("MessRecyclerAdapter", "Rating: " + temp.getMess_img()); // Check the rating value

        holder.name.setText(temp.getMess_name());
        holder.seating.setText(temp.getMess_seating());
        holder.timing.setText(temp.getMess_timing());
        holder.rating.setText(temp.getMess_rating());
        holder.mess_img.setImageResource(temp.getMess_img());

        if(temp.getMess_status().equals("Open")) {
            int textColor = ContextCompat.getColor(context, R.color.button);
            holder.status.setTextColor(textColor);
        }

        else
            holder.status.setTextColor(Color.RED);

        holder.status.setText(temp.getMess_status());

//        Boolean isExpandable = dataholder2.get(position).getExpandable();
//        holder.expand.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messInfoActivity();
            }
        });

//        holder.l1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                messInfoActivity();
//            }
//        });
//        holder.l2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                messInfoActivity();
//            }
//        });
//        holder.l3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                messInfoActivity();
//            }
//        });
//        holder.l4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                messInfoActivity();
//            }
//        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messInfoActivity();
            }
        });
        holder.mess_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messInfoActivity();
            }
        });



    }

    private void messInfoActivity() {

        Intent intent = new Intent(context,Menu.class);
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return dataholder2.size();
    }


    class MessCard extends RecyclerView.ViewHolder
    {
        TextView name,seating,timing,rating,status;
        ImageView mess_img,drop;
        Button more;

        LinearLayout expand,l1,l2,l3,l4;

        public MessCard(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.mess_name);
            rating = itemView.findViewById(R.id.rating);
            seating = itemView.findViewById(R.id.seating_capacity);
            timing = itemView.findViewById(R.id.mess_timing);
            more = itemView.findViewById(R.id.more_btn);
            mess_img = itemView.findViewById(R.id.dish_img);
            status = itemView.findViewById(R.id.mess_status);

//            l1 = itemView.findViewById(R.id.status_linear);
//            l2 = itemView.findViewById(R.id.seating_linear);
//            l3 = itemView.findViewById(R.id.timing_linear);
//            l4 = itemView.findViewById(R.id.rating_linear);
//
//            drop = itemView.findViewById(R.id.drop_menu);
//            expand = itemView.findViewById(R.id.expand);

//            drop.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    MessCardData dataLead_backLog = dataholder2.get(getAdapterPosition());
//                    dataLead_backLog.setExpandable(!dataLead_backLog.getExpandable());
//                    notifyItemChanged(getAdapterPosition());
//                }
//            });





        }
    }
}
