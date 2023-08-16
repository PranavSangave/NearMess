package com.example.nearmess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
 
import android.view.WindowManager;
import android.widget.ImageView;
 
import android.widget.LinearLayout;
import android.widget.Toast;

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
 

import java.util.ArrayList;
import java.util.List;


/***
 * Author : Vasudev Raut
 *last edited By : Rahul Shinde
 *Date : 15-08-2024
 * Note :
 */

public class Menu extends Activity implements View.OnClickListener {
    RecyclerView bottomSheetComment;
    private String ans = "";

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