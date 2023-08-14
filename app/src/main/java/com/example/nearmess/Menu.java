package com.example.nearmess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


/***
 * Author : Vasudev Raut
 *last edited By : Vasudev Raut
 *Date : 14-08-2023
 * Note :
 */

public class Menu extends AppCompatActivity {
    RecyclerView bottomSheetComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // To show bottom sheet Dialog
        CardView commentCard = findViewById(R.id.commentCard);
        commentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });






    }

    public void showBottomSheetDialog()
    {


        final BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(
                Menu.this,R.style.BottomSheetDialogTheme
        );


        View bottomSheetView = LayoutInflater.from(Menu.this)
                .inflate(
                        R.layout.layout_bottom_sheet,
                        (LinearLayout)findViewById(R.id.bottomsheetcontainer)
                );

            RecyclerView bottomSheetComment  = bottomSheetView.findViewById(R.id.commentRecyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            bottomSheetComment.setLayoutManager(layoutManager);

            ArrayList<CommentSetOnBottomSheetModel> comment_list = new ArrayList<CommentSetOnBottomSheetModel>();

            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));

            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));
            comment_list.add(new CommentSetOnBottomSheetModel("Vasudev Raut","Hare krushnaaa","eeee","eeeee","eeee","eeee"));


        CommentSetOnbottomSheetAdapter comment_adapter = new CommentSetOnbottomSheetAdapter(Menu.this, comment_list);

            bottomSheetComment.setAdapter(comment_adapter);
            comment_adapter.notifyDataSetChanged();




















        bottomSheetDialog1.setContentView(bottomSheetView);
        bottomSheetDialog1.show();

    }



}