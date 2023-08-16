/**
 *  AUTHOR : PRANAV SANGAVE
 *  CREATION DATE : 15/08/2023
 *  LAST EDITED BY : PRANAV SANGAVE
 *  LAST MODIFIED DATE : 15/08/2023
 *
 *  NOTE: PLEASE DON't MODIFY THIS FILE AS ITS DIRECTLY CONNECTED WITH YOUR DB SCHEMA
 * **/

package com.example.nearmess.callbacks;

import com.google.firebase.firestore.DocumentSnapshot;

public interface GetAllDataFromDocumentCallBack {

    void onDataReceived(DocumentSnapshot documentSnapshot);

}
