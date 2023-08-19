package com.example.nearmess.callbacks;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public interface GetMealFromDate {
    void onDataReceived(@NonNull Task<DocumentSnapshot> task);
}
