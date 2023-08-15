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

        // To show bottom sheet Dialog
        CardView commentCard = findViewById(R.id.commentCard);
        commentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });


        /** TODO: HERE PROBLEM IS WE CANNOT ACCESS DATA OUTSIDE THAT ON DATA RECEIVCED FUNCTION CALL */
        /** TODO: WORK ON IT */
        MyDatabase db = new MyDatabase(FirebaseFirestore.getInstance(), Menu.this);

        // Assuming db is your Firestore database reference
        db.getAllFromDocument(FirestoreKeys.MESS_OWNERS, "101", new GetAllDataFromDocumentCallBack() {
            @Override
            public void onDataReceived(DocumentSnapshot documentSnapshot) {
                String ownerName = documentSnapshot.getString(FirestoreKeys.MESS_OWNER_NAME);
            }
        });

        db.getItemFromDocument(FirestoreKeys.AREA, "Kondhwa", FirestoreKeys.MESS_ID, new GetDataFromDocumentCallBack() {
            @Override
            public void onDataReceived(Object data) {
                if(data == null) {
                    Toast.makeText(Menu.this, "Something Wents Wrong !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    List<String> ans = (List<String>) data;
                    Toast.makeText(Menu.this, ans+"", Toast.LENGTH_SHORT).show();
                }
            }
        });

        db.getAllDocumentsFromCollection(FirestoreKeys.AREA, new GetAllDocumentsCallBack() {
            @Override
            public void onDataReceived(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    String ids = "";
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        ids = ids += document.getId();
                    }
                    Toast.makeText(Menu.this, ids, Toast.LENGTH_SHORT).show();
                }
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