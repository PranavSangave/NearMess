package com.example.nearmess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nearmess.callbacks.GetAllDataFromDocumentCallBack;
import com.example.nearmess.callbacks.GetAllDocumentsCallBack;
import com.example.nearmess.callbacks.GetDataFromDocumentCallBack;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/***
 * Author : Vasudev Raut
 * last edited By : Vasudev Raut
 * Date : 14-08-2024
 * Note :
 */

public class Menu extends AppCompatActivity {
    RecyclerView bottomSheetComment;
    private String ans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        overridePendingTransition(R.anim.got_down_to_up, R.anim.got_down_to_up1);

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