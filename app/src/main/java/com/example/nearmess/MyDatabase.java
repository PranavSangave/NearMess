/**
 *  AUTHOR : PRANAV SANGAVE
 *  CREATION DATE : 15/08/2023
 *  LAST EDITED BY : PRANAV SANGAVE
 *  LAST MODIFIED DATE : 19/08/2023
 *
 *  NOTE: PLEASE DON't MODIFY THIS FILE UNLESS AND UNTIL YOU WANT TO CHANGE DATABASE FUNCTIONS
 * **/

package com.example.nearmess;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nearmess.callbacks.GetAllCommentsCallBack;
import com.example.nearmess.callbacks.GetAllDataFromDocumentCallBack;
import com.example.nearmess.callbacks.GetAllDocumentsCallBack;
import com.example.nearmess.callbacks.GetDataFromDocumentCallBack;
import com.example.nearmess.callbacks.GetMealFromDate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyDatabase {

    private FirebaseFirestore storeRef;
    private DocumentReference docRef;
    private Context context;

    MyDatabase(FirebaseFirestore storeRef, Context context)
    {
        this.storeRef = storeRef;
        this.context = context;
    }

    /** (SETTER METHODS) */

    /** THIS METHOD IS USE TO CREATE NEW MESS DOCUMENT IN MESS_OWNER_COLLECTION (SETTER)*/
    public void createMessOwner(String mess_id, String email,
                                String password, String owner_nm,
                                String mess_nm, String typed_addr,
                                String map_link, String mess_phone,
                                String seating_capacity, String service1,
                                String service2, String menu_type,
                                String morning_start_time, String morning_end_time,
                                String evening_start_time, String evening_end_time, String is_weekly)
    {

        docRef = storeRef.document(FirestoreKeys.MESS_OWNERS+"/"+email);

        Map<String, Object> messOwnerDetails = new HashMap<>();
        messOwnerDetails.put(FirestoreKeys.MESS_ID, mess_id);
        messOwnerDetails.put(FirestoreKeys.OWNER_PASS, password);
        messOwnerDetails.put(FirestoreKeys.MESS_OWNER_NAME, owner_nm);
        messOwnerDetails.put(FirestoreKeys.MESS_NAME, mess_nm);
        messOwnerDetails.put(FirestoreKeys.MESS_TYPED_ADDRESS, typed_addr);
        messOwnerDetails.put(FirestoreKeys.MESS_MAP_LINK, map_link);
        messOwnerDetails.put(FirestoreKeys.MESS_PHONE, mess_phone);
        messOwnerDetails.put(FirestoreKeys.SEATING_CAPACITY, seating_capacity);
        messOwnerDetails.put(FirestoreKeys.SERVICES, service1 + "," + service2);
        messOwnerDetails.put(FirestoreKeys.MENU_TYPE, menu_type);
        messOwnerDetails.put(FirestoreKeys.MORNING_START_TIME, morning_start_time);
        messOwnerDetails.put(FirestoreKeys.MORNING_END_TIME, morning_end_time);
        messOwnerDetails.put(FirestoreKeys.EVENING_START_TIME, evening_start_time);
        messOwnerDetails.put(FirestoreKeys.EVENING_END_TIME, evening_end_time);
        messOwnerDetails.put(FirestoreKeys.IS_WEEKLY, is_weekly);

        // Adding this map to firestore in mentioned document (MessOwner/MESS_ID)
        docRef.set(messOwnerDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("FIRESTORE", "MESS_UPDATED");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FIRESTORE","ERROR" +e);
            }
        });
    }

    /** THIS METHOD IS USE TO CREATE NEW END-USER DOCUMENT IN END_USER_COLLECTION */
    public void createEndUser(String email, String name, String pass,
                              List<String> favourite_mess_ids, String companion_mess_id)
    {
        docRef = storeRef.document(FirestoreKeys.END_USERS+"/"+email);

        Map<String, Object> endUserDetails = new HashMap<>();
        endUserDetails.put(FirestoreKeys.END_USER_NAME, name);
        endUserDetails.put(FirestoreKeys.END_USER_PASS, pass);
        endUserDetails.put(FirestoreKeys.FAVOURITE_MESS_IDS, favourite_mess_ids);
        endUserDetails.put(FirestoreKeys.COMPANION_MESS_ID, companion_mess_id);

        // Adding this map to firestore in mentioned document (MessOwner/MESS_ID)
        docRef.set(endUserDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("FIRESTORE", "END_USER_UPDATED");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FIRESTORE","END_USER_UPDATE_ERROR: " + e);
            }
        });
    }

    /** THIS METHOD IS USE TO CREATE NEW MESS_MENU DOCUMENT IN MESS_MENU_COLLECTION */


    public void createMessMenu(String mess_email, int meal_type_constant, List<String> charges,
                               List<String> menu, String specialMsg)
    {
        String mainDocPath = FirestoreKeys.MESS_MENU + "/" + mess_email;
        DocumentReference mainDoc = storeRef.document(mainDocPath);

        // Fetching today's date
        LocalDate date = LocalDate.now();

        Map<String, Object> mealMap = new HashMap<>();

        // Map for comments
        Map<String, Object> comments = new HashMap<>(); // keep it empty  as initially we don't have comments
        List<String> likes = new ArrayList<>();  // initially empty
        List<String> dislikes = new ArrayList<>(); // initially empty

        mealMap.put(FirestoreKeys.CHARGES, charges);
        mealMap.put(FirestoreKeys.MENU_STRING, menu);
        mealMap.put(FirestoreKeys.SPECIAL_MSG, specialMsg);
        mealMap.put(FirestoreKeys.COMMENTS, comments);
        mealMap.put(FirestoreKeys.LIKES, likes);
        mealMap.put(FirestoreKeys.DISLIKES, dislikes);


        DocumentReference dateDoc;
        if(meal_type_constant == FirestoreKeys.LUNCH)
        {
            dateDoc = mainDoc.collection(date+"").document(FirestoreKeys.LUNCH_STR);
        }
        else if(meal_type_constant == FirestoreKeys.DINNER)
        {
            dateDoc = mainDoc.collection(date+"").document(FirestoreKeys.DINNER_STR);
        }
        else return;


        dateDoc.set(mealMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error" + e, Toast.LENGTH_SHORT).show();
            }
        });



    }




    /** THIS METHOD IS USE TO CREATE NEW MESS_MENU DOCUMENT IN MESS_MENU_COLLECTION */
    public void createLocations(String location, List<String> mess_ids)
    {
        String documentPath = FirestoreKeys.AREA + "/" + location;

        Map<String, Object> locationWiseMess = new HashMap<>();
        locationWiseMess.put(FirestoreKeys.MESS_ID, mess_ids);

        storeRef.document(documentPath).set(locationWiseMess)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("FIRESTORE", "NEW_LOCATION_CREATED");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIRESTORE", "NEW_LOCATION_CREATION_ERROR: " + e);
                    }
                });
    }

    /** THIS METHOD IS USE TO CREATE NEW COMMENT */
    public void addComment(String mess_email, String date, int meal_type, String end_user_email, String comment)
    {
        String mealType;
        if(meal_type == FirestoreKeys.LUNCH) mealType = FirestoreKeys.LUNCH_STR;
        else if(meal_type == FirestoreKeys.DINNER) mealType = FirestoreKeys.DINNER_STR;
        else return;

        // FETCHING TIME IN HH:MM FORMAT
        // Get the current time
        long currentTimeMillis = System.currentTimeMillis();
        Date currentTime = new Date(currentTimeMillis);

        // Create a SimpleDateFormat with the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        // Format the current time
        String formattedTime = sdf.format(currentTime);

        DocumentReference dref = storeRef.collection(FirestoreKeys.MESS_MENU).document(mess_email).collection(date)
                .document(mealType);

        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                   DocumentSnapshot ds = task.getResult();
                   Map<String,Object> mp = (Map<String,Object>)ds.get(FirestoreKeys.COMMENTS);
                   List<String> commentLs = new ArrayList<>(); // 0th index -> comment | 1st index -> time
                   commentLs.add(comment);
                   commentLs.add(formattedTime);
                   mp.put(end_user_email, commentLs);

                   dref.update(FirestoreKeys.COMMENTS, mp).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(context, "Error Occured " + e, Toast.LENGTH_SHORT).show();
                       }
                   });

                }
            }
        });


    }

    /** THIS METHOD IS USE TO CREATE NEW COMMENT */
    public void fetchComments(String mess_email, String date, int meal_type, GetAllCommentsCallBack callBack)
    {
        String mealType;
        if(meal_type == FirestoreKeys.LUNCH) mealType = FirestoreKeys.LUNCH_STR;
        else if(meal_type == FirestoreKeys.DINNER) mealType = FirestoreKeys.DINNER_STR;
        else return;

        DocumentReference dref = storeRef.collection(FirestoreKeys.MESS_MENU).document(mess_email).collection(date)
                .document(mealType);

        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    callBack.onDataReceived(task);
                }
            }
        });
    }


    /** (GETTER METHODS) */

    /** THIS METHOD GIVES SPECIFIC ITEM FROM SPECIFIED COLLECTION AND DOCUMENT */
    /** SAMPLE FUNCTION CALLL
     * =========================
     * db.getItemFromDocument(FirestoreKeys.AREA, "Kondhwa", FirestoreKeys.MESS_ID, new GetDataFromDocumentCallBack() {
     *             @Override
     *             public void onDataReceived(Object data) {
     *                 if(data == null) {
     *                     Toast.makeText(Menu.this, "Something Wents Wrong !", Toast.LENGTH_SHORT).show();
     *                 }
     *                 else
     *                 {
     *                     List<String> ans = (List<String>) data;
     *                     Toast.makeText(Menu.this, ans+"", Toast.LENGTH_SHORT).show();
     *                 }
     *             }
     *         });
     */
    public void getItemFromDocument(String CollectionName, String DocumentName, String ItemName, GetDataFromDocumentCallBack callBack) {
        docRef = storeRef.document(CollectionName + "/" + DocumentName);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Object item = documentSnapshot.get(ItemName);
                callBack.onDataReceived(item);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callBack.onDataReceived(null);
            }
        });
    }

    /** THIS METHOD GIVES ALL ITEMS FROM SPECIFIED COLLECTION AND DOCUMENT */
    /** SAMPLE FUNCTION CALLL
     * =========================
     * db.getAllFromDocument(FirestoreKeys.MESS_OWNERS, "101", new GetAllDataFromDocumentCallBack() {
     *             @Override
     *             public void onDataReceived(DocumentSnapshot documentSnapshot) {
     *                 String ownerName = documentSnapshot.getString(FirestoreKeys.MESS_OWNER_NAME);
     *             }
     *         });
     */
    public void getAllFromDocument(String CollectionName, String DocumentName, GetAllDataFromDocumentCallBack callBack) {
        docRef = storeRef.document(CollectionName + "/" + DocumentName);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                callBack.onDataReceived(documentSnapshot);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error :" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /** THIS METHOD GIVES ALL ITEMS FROM SPECIFIED COLLECTION AND DOCUMENT */
    /** SAMPLE FUNCTION CALLL
     * =========================
     * db.getAllDocumentsFromCollection(FirestoreKeys.AREA, new GetAllDocumentsCallBack() {
     *             @Override
     *             public void onDataReceived(@NonNull Task<QuerySnapshot> task) {
     *                 if(task.isSuccessful()) {
     *                     String ids = "";
     *                     for(QueryDocumentSnapshot document : task.getResult()) {
     *                         ids = ids += document.getId();
     *                         String name = document.getString("END_USER_NAME);
     *                     }
     *                     Toast.makeText(Menu.this, ids, Toast.LENGTH_SHORT).show();
     *                 }
     *             }
     *         });
     */
    public void getAllDocumentsFromCollection(String CollectionName, GetAllDocumentsCallBack callBack) {

        storeRef.collection(CollectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        callBack.onDataReceived(task);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callBack.onDataReceived(null);
                    }
                });
    }

    /** THIS METHOD GIVES ALL ITEMS FROM SPECIFIED COLLECTION AND DOCUMENT */
    /** SAMPLE FUNCTION CALLL
     * =========================
     *      db.getMessMenu("pranavsangave2003@gmail.com", date + "", FirestoreKeys.DINNER_STR, new GetMealFromDate() {
     *             @Override
     *             public void onDataReceived(@NonNull Task<DocumentSnapshot> task) {
     *                 if(task.isSuccessful())
     *                 {
     *                     DocumentSnapshot doc = task.getResult();
     *                     String msg = doc.getString(FirestoreKeys.SPECIAL_MSG);
     *                     Toast.makeText(HomeScreen.this, msg, Toast.LENGTH_SHORT).show();
     *                 }
     *                 else
     *                     Toast.makeText(HomeScreen.this, "Error", Toast.LENGTH_SHORT).show();
     *             }
     *         });
     */
    public void getMessMenu(String mess_email, String menu_date, String meal_type, GetMealFromDate callback)
    {
        DocumentReference dref = storeRef.collection(FirestoreKeys.MESS_MENU).document(mess_email)
                .collection(menu_date).document(meal_type);

        dref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                callback.onDataReceived(task);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
