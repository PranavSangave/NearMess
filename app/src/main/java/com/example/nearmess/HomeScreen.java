package com.example.nearmess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.View.OnClickListener;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearmess.mess_card_recycler.MessCardData;
import com.example.nearmess.mess_card_recycler.MessRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


/***
 * Author : Rahul Shinde
 *last edited By : Rahul Shinde
 *Date : 15-08-2024
 * Note :
 */

public class HomeScreen extends Activity implements View.OnClickListener {

    Spinner area_combo;


    RecyclerView mess_recycler;
    LinearLayoutManager layoutManager;
    List<MessCardData> mess_list;
    MessRecyclerAdapter messRecyclerAdapter;

    TextView all,veg,non_veg;

    SearchView searchView;


    DrawerLayout drawerLayout;
    ImageView navigationBar;
    LinearLayout ll_First,ll_Second,ll_Third,ll_Fourth;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        onSetNavigationDrawerEvents();

        // initialized areaList as a List<String>
        List<String> areaList = new ArrayList<>();

        // Adding data to areaList
        areaList.add("Kondhwa");
        areaList.add("Katraj");
        areaList.add("Gopal Nagar");
        areaList.add("Kapil Nagar");

        area_combo = findViewById(R.id.area_spinner);



        // Set up the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeScreen.this, android.R.layout.simple_spinner_item, areaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_combo.setAdapter(adapter);

        mess_recycler = findViewById(R.id.recycler_for_mess);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mess_recycler.setLayoutManager(layoutManager);

        // Set up the Spinner's item selected listener
        area_combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Change text color of the selected item
                TextView textView = (TextView) view;
                if (textView != null) {
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Handle case when nothing is selected

            }
        });



        fetchMessCards();

        searchView = findViewById(R.id.search_mess);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                filterList(s);
                return true;
            }
        });



    }

    private void filterList(String s) {

        List<MessCardData> filteredList = new ArrayList<>();


        for (MessCardData dataDishes : mess_list) {

            if (dataDishes.getMess_name().toString().toLowerCase().contains(s.toString().toLowerCase())) {

                filteredList.add(dataDishes);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No mess found..!!", Toast.LENGTH_SHORT).show();

        } else {
            messRecyclerAdapter.setFilteredList(filteredList);
        }
    }


    private void fetchMessCards() {

        //here dummy data added to check recycler working
        mess_list = new ArrayList<>();

        mess_list.add(new MessCardData("Annapurna Mess", "11 pm to 12pm", "30 seats", "Open", "4.0", R.drawable.dish1));
        mess_list.add(new MessCardData("Sunrise Mess", "8 am to 10am", "25 seats", "Closed", "3.5", R.drawable.d2));
        mess_list.add(new MessCardData("Sapphire Mess", "12 pm to 1pm", "50 seats", "Open", "4.8", R.drawable.dish1));
        mess_list.add(new MessCardData("Golden Spoon", "7 pm to 9pm", "40 seats", "Open", "4.2", R.drawable.d2));
        mess_list.add(new MessCardData("Flavor Haven", "10 am to 11am", "20 seats", "Closed", "3.0", R.drawable.dish1));
        mess_list.add(new MessCardData("Urban Delight", "6 pm to 8pm", "35 seats", "Open", "4.5", R.drawable.dish1));
        mess_list.add(new MessCardData("Mouthful Munch", "9 am to 10am", "15 seats", "Open", "3.7", R.drawable.dish1));
        mess_list.add(new MessCardData("Spice Route", "1 pm to 2pm", "30 seats", "Closed", "3.2", R.drawable.dish1));
        mess_list.add(new MessCardData("Taste Breeze", "5 pm to 7pm", "28 seats", "Open", "4.1", R.drawable.dish1));
        mess_list.add(new MessCardData("Harmony Kitchen", "11 am to 12pm", "40 seats", "Open", "4.6", R.drawable.dish1));

        messRecyclerAdapter = new MessRecyclerAdapter(mess_list, HomeScreen.this);
        mess_recycler.setAdapter(messRecyclerAdapter);

    }

    private void onSetNavigationDrawerEvents() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navigationBar = findViewById(R.id.account2);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account2:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.share_linear:
                showToast("share");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.contact_linear:
                showToast("contact us");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.favourite_linear:
                showToast("favourite");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.logout_linear:
                showToast("logout");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            default:
                showToast("Default");
                drawerLayout.closeDrawer(navigationView, true);
                //drawerLayout.openDrawer(navigationView, true);
                break;
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