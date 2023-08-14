package com.example.nearmess;


/***
 * Author : Vasudev Raut
 *last edited By : Vasudev Raut
 *Date : 14-08-2023
 * Note :
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentSetOnbottomSheetAdapter extends RecyclerView.Adapter<CommentSetOnbottomSheetAdapter.MyHolder> {
    Context context;
    ArrayList<CommentSetOnBottomSheetModel> comment_list;

    public CommentSetOnbottomSheetAdapter(Context context, ArrayList<CommentSetOnBottomSheetModel> comment_list) {
        this.context = context;
        this.comment_list = comment_list;
//        Toast.makeText(context,"Vasudev"+comment_list.size(),Toast.LENGTH_LONG).show();


    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_recycler_card,parent,false);
        MyHolder myHolder1 = new MyHolder(view);
//        Toast.makeText(context,"Vasudev",Toast.LENGTH_LONG).show();

        return myHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.user_name.setText(comment_list.get(position).getUser_name());

    }

    @Override
    public int getItemCount() {
//        Toast.makeText(context,"Vasudev"+comment_list.size(),Toast.LENGTH_LONG).show();
        return comment_list.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {



        TextView user_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);


        }
    }

}
