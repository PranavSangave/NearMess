package com.example.nearmess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.nearmess.callbacks.GetAllDataFromDocumentCallBack;
import com.example.nearmess.callbacks.GetAllDocumentsCallBack;
import com.example.nearmess.mess_card_recycler.MessCardData;
import com.example.nearmess.mess_card_recycler.MessRecyclerAdapter;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
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

    final String sharedPreferencesFileTitle = "NeerMessApplication";

    RecyclerView mess_recycler;
    LinearLayoutManager layoutManager;
    List<MessCardData> mess_list;
    MessRecyclerAdapter messRecyclerAdapter;

    TextView all, veg, non_veg;

    SearchView searchView;
    ArrayAdapter<String> messAddressAdapter;


    DrawerLayout drawerLayout;
    ImageView navigationBar;
    LinearLayout ll_First, ll_Second, ll_Third, ll_Fourth;
    NavigationView navigationView;
    ImageView f_mess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        onSetNavigationDrawerEvents();

        f_mess = findViewById(R.id.favrait_mess);

        f_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeScreen.this,FavouriteMess.class);
                startActivity(intent);


            }
        });

        area_combo = findViewById(R.id.area_spinner);




        addLocation();



        // initialized areaList as a List<String>
//        List<String> areaList = new ArrayList<>();

        // Adding data to areaList
//        areaList.add("Kondhwa");
//        areaList.add("Singhgad");
//        areaList.add("Gopal Nagar");
//        areaList.add("Kapil Nagar");



        // Set up the adapter for the spinner
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeScreen.this, android.R.layout.simple_spinner_item, areaList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        area_combo.setAdapter(adapter);


        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);



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


                Toast.makeText(HomeScreen.this, "Update in "+area_combo.getSelectedItem(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mess_location", area_combo.getSelectedItem().toString());

                editor.apply();
                fetchMessCards();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Handle case when nothing is selected

            }
        });


//        fetchMessCards();

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
        messRecyclerAdapter = new MessRecyclerAdapter(mess_list, HomeScreen.this);
        mess_list.clear();
        messRecyclerAdapter.notifyDataSetChanged();

        mess_recycler.setAdapter(messRecyclerAdapter);
        String area = area_combo.getSelectedItem().toString();




        MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),this);

        myDatabase.getAllFromDocument(FirestoreKeys.AREA, area, new GetAllDataFromDocumentCallBack() {
            @Override
            public void onDataReceived(DocumentSnapshot documentSnapshot) {

                List<String> all_mess_list = (List<String>)documentSnapshot.get(FirestoreKeys.MESS_ID);
                if(all_mess_list!=null && all_mess_list.size()!=0)
                {
                    for(String vendorEmail : all_mess_list)
                    {
//                        Toast.makeText(HomeScreen.this, ""+all_mess_list.toString(), Toast.LENGTH_SHORT).show();

                        myDatabase.getAllFromDocument(FirestoreKeys.MESS_OWNERS, vendorEmail, new GetAllDataFromDocumentCallBack() {
                            @Override
                            public void onDataReceived(DocumentSnapshot documentSnapshot) {
                                String last_updated_date = documentSnapshot.getString("STREAK_LAST_UPDATE_DATE");

                                LocalDate date = LocalDate.now();

                                if(documentSnapshot!=null && last_updated_date.equals(date+""))
                                {

                                    String mess_name = documentSnapshot.getString(FirestoreKeys.MESS_NAME);

                                    boolean mess_open_close_stauts = (boolean)documentSnapshot.getBoolean(FirestoreKeys.MESS_STATUS);
//                                    Toast.makeText(HomeScreen.this, "mess_open_close_stauts"+documentSnapshot.getId(), Toast.LENGTH_SHORT).show();

                                    String mess_capecity = documentSnapshot.getString(FirestoreKeys.SEATING_CAPACITY);
                                    String last_update = documentSnapshot.getString("STREAK_LAST_UPDATE_DATE");

                                    if(mess_open_close_stauts==true )
                                    {
//                                        mess_open_close_stauts = "Open";
                                        mess_list.add(new MessCardData(mess_name, "11 pm to 12pm", mess_capecity, "Open", "4.0", R.drawable.dish1));

                                        messRecyclerAdapter = new MessRecyclerAdapter(mess_list, HomeScreen.this);
                                        mess_recycler.setAdapter(messRecyclerAdapter);
                                    }
                                    else if(mess_open_close_stauts==false) {
//                                        mess_open_close_stauts = "Closed";
                                        mess_list.add(new MessCardData(mess_name, "11 pm to 12pm", mess_capecity, "Closed", "4.0", R.drawable.dish1));

                                        messRecyclerAdapter = new MessRecyclerAdapter(mess_list, HomeScreen.this);
                                        mess_recycler.setAdapter(messRecyclerAdapter);
                                    }



                                }




                            }
                        });
                    }






                }




            }
        });










//
//        mess_list.add(new MessCardData("Annapurna Mess", "11 pm to 12pm", "30 seats", "Open", "4.0", R.drawable.dish1));
//        mess_list.add(new MessCardData("Sunrise Mess", "8 am to 10am", "25 seats", "Closed", "3.5", R.drawable.d2));
//        mess_list.add(new MessCardData("Sapphire Mess", "12 pm to 1pm", "50 seats", "Open", "4.8", R.drawable.dish1));
//        mess_list.add(new MessCardData("Golden Spoon", "7 pm to 9pm", "40 seats", "Open", "4.2", R.drawable.d2));
//        mess_list.add(new MessCardData("Flavor Haven", "10 am to 11am", "20 seats", "Closed", "3.0", R.drawable.dish1));
//        mess_list.add(new MessCardData("Urban Delight", "6 pm to 8pm", "35 seats", "Open", "4.5", R.drawable.dish1));
//        mess_list.add(new MessCardData("Mouthful Munch", "9 am to 10am", "15 seats", "Open", "3.7", R.drawable.dish1));
//        mess_list.add(new MessCardData("Spice Route", "1 pm to 2pm", "30 seats", "Closed", "3.2", R.drawable.dish1));
//        mess_list.add(new MessCardData("Taste Breeze", "5 pm to 7pm", "28 seats", "Open", "4.1", R.drawable.dish1));
//        mess_list.add(new MessCardData("Harmony Kitchen", "11 am to 12pm", "40 seats", "Open", "4.6", R.drawable.dish1));
//


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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void addLocation()
    {





        MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),this);

        myDatabase.getAllDocumentsFromCollection(FirestoreKeys.AREA, new GetAllDocumentsCallBack() {
            @Override
            public void onDataReceived(@NonNull Task<QuerySnapshot> task) {
                List<String> all_location = new ArrayList<>();
//                all_location.add("Select Location");
                if(task.isSuccessful()) {
                    String ids = "";
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        all_location.add(document.getId());

                    }
//                          Toast.makeText(RegisterScreen.this, ids, Toast.LENGTH_SHORT).show();
                }

                messAddressAdapter = new ArrayAdapter<String>(HomeScreen.this, android.R.layout.simple_spinner_item,all_location);
                messAddressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
                String mess_Address = sharedPreferences.getString("mess_location","");



                area_combo.setAdapter(messAddressAdapter);
                if(sharedPreferences.contains("mess_location"))
                {

                    int positions = messAddressAdapter.getPosition(mess_Address);
//                    Toast.makeText(HomeScreen.this, "Somethig error here"+area_combo.getItemAtPosition(positions), Toast.LENGTH_SHORT).show();


                    area_combo.setSelection(positions);

                }



            }
        });







    }


}