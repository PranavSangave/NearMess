package com.example.nearmess;

import static com.example.nearmess.FirestoreKeys.DINNER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
 
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
 
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nearmess.callbacks.GetAllCommentsCallBack;
import com.example.nearmess.callbacks.GetAllDataFromDocumentCallBack;
import com.example.nearmess.callbacks.GetAllDocumentsCallBack;
import com.example.nearmess.callbacks.GetDataFromDocumentCallBack;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
 
import com.google.android.material.navigation.NavigationView;
 
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/***
 * Author : Vasudev Raut
 *last edited By : Rahul Shinde
 *Date : 15-08-2024
 * Note :
 */

public class Menu extends Activity implements View.OnClickListener {
    RecyclerView bottomSheetComment;
    private String ans = "";

    SharedPreferences sharedPreferences ;

    DrawerLayout drawerLayout;
    ImageView navigationBar;
    LinearLayout ll_First,ll_Second,ll_Third,ll_Fourth;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        overridePendingTransition(R.anim.got_down_to_up, R.anim.got_down_to_up1);


        onSetNavigationDrawerEvents();

        sharedPreferences = getSharedPreferences("NeerMessApplication", MODE_PRIVATE);

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

            /** Add Comment Code **/
            EditText commentEditText = bottomSheetView.findViewById(R.id.add_comment_edit_txt);
            ImageView sendBtn = bottomSheetView.findViewById(R.id.sendBtn);
            MyDatabase db = new MyDatabase(FirebaseFirestore.getInstance(), Menu.this);

            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /** TODO: ONCE YOU BIND ALL OTHER DATA WITH DATA COMMING FROM MESS, THEN PLEASE UPDATE FOLLOWING VALUES */
                    String comment = commentEditText.getText().toString();
                    LocalDate date = LocalDate.now();

                    String end_user_email =  sharedPreferences.getString("user_email", "NA");
                    if(!end_user_email.equals("NA"))
                        db.addComment("abc@gmail.com", date+"", FirestoreKeys.LUNCH, end_user_email, comment);

                    commentEditText.setText("");
                }
            });

            /** Displau Comments Code **/

            RecyclerView bottomSheetComment  = bottomSheetView.findViewById(R.id.commentRecyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            bottomSheetComment.setLayoutManager(layoutManager);

            ArrayList<CommentSetOnBottomSheetModel> comment_list = new ArrayList<>();

            db.fetchComments("abc@gmail.com", "2023-08-19", FirestoreKeys.LUNCH, new GetAllCommentsCallBack() {
                @Override
                public void onDataReceived(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot docSnap = task.getResult();
                        Map<String, Object> comments = (Map<String, Object>) docSnap.get(FirestoreKeys.COMMENTS);

                        // Traverse the HashMap using an enhanced for loop
                        for (Map.Entry<String, Object> entry : comments.entrySet()) {
                            String key = entry.getKey();

                            // Find the index of the "@" character
                            int atIndex = key.indexOf("@");

                            if (atIndex != -1) { // Check if "@" was found
                                // Remove the "@" character and everything after it
                                key = key.substring(0, atIndex);

                            }

                            ArrayList<String> commentTimeLs = (ArrayList<String>) entry.getValue();
                            comment_list.add(new CommentSetOnBottomSheetModel(key, commentTimeLs.get(0), commentTimeLs.get(1)));

                        }
                        CommentSetOnbottomSheetAdapter comment_adapter = new CommentSetOnbottomSheetAdapter(Menu.this, comment_list);
                        comment_adapter.notifyDataSetChanged();
                        bottomSheetComment.setAdapter(comment_adapter);

                    }
                }
            });



        bottomSheetDialog1.setContentView(bottomSheetView);
        bottomSheetDialog1.show();
    }


    private void onSetNavigationDrawerEvents() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navigationBar = findViewById(R.id.nav_menu);
        ll_First = findViewById(R.id.share_linear);
        ll_Second = findViewById(R.id.contact_linear);
        ll_Third = findViewById(R.id.favourite_linear);
        ll_Fourth = findViewById(R.id.logout_linear);


        navigationBar.setOnClickListener(this);
        ll_First.setOnClickListener(this);
        ll_Second.setOnClickListener(this);
        ll_Third.setOnClickListener(this);
        ll_Fourth.setOnClickListener(this);

        //drawerLayout.openDrawer(navigationView, true);

    }


    private static final int ACCOUNT_VIEW_ID = R.id.account2;
    private static final int SHARE_LINEAR_VIEW_ID = R.id.share_linear;
    private static final int CONTACT_LINEAR_VIEW_ID = R.id.contact_linear;
    private static final int FAVOURITE_LINEAR_VIEW_ID = R.id.favourite_linear;
    private static final int LOGOUT_LINEAR_VIEW_ID = R.id.logout_linear;

    // ...

    @Override
    public void onClick(View v) {
        if (v.getId() == ACCOUNT_VIEW_ID) {
            drawerLayout.openDrawer(navigationView, true);
        } else if (v.getId() == SHARE_LINEAR_VIEW_ID) {
            showToast("share");
            drawerLayout.closeDrawer(navigationView, true);
        } else if (v.getId() == CONTACT_LINEAR_VIEW_ID) {
            showToast("contact us");
            drawerLayout.closeDrawer(navigationView, true);
        } else if (v.getId() == FAVOURITE_LINEAR_VIEW_ID) {
            showToast("favourite");
            drawerLayout.closeDrawer(navigationView, true);
        } else if (v.getId() == LOGOUT_LINEAR_VIEW_ID) {
            showToast("logout");
            drawerLayout.closeDrawer(navigationView, true);
        } else {
            showToast("Default");
            drawerLayout.closeDrawer(navigationView, true);
        }
    }


    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView, true);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}