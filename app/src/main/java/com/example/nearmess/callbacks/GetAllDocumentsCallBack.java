package com.example.nearmess.callbacks;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface GetAllDocumentsCallBack {
    void onDataReceived(@NonNull Task<QuerySnapshot> task);
}
