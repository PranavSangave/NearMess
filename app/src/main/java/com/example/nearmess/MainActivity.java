//package com.example.nearmess;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GoogleAuthProvider;
//
//public class MainActivity extends AppCompatActivity {
//
//
//    FirebaseAuth auth;
//    GoogleSignInClient mGoogleSignInClient;
//    ProgressDialog progressDialog;
//    Button signInButton;
//
//    TextView linksignup;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        linksignup = findViewById(R.id.linksignup);
//
//        linksignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),SignUp.class);
//                startActivity(i);
//            }
//        });
//
//        signInButton = findViewById(R.id.log_in_with_google);
//        auth =FirebaseAuth.getInstance();
//
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setTitle("Vasudev");
//        progressDialog.setMessage("We are trying");
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
//
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
//
//
//
//
//
//
//    }
//
//
//    int RC_SIGN_IN = 40;
//
//    private void signIn() {
//
//        Intent intent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(intent,RC_SIGN_IN);
//
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if(requestCode==RC_SIGN_IN)
//        {
//
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//
//            try {
//                GoogleSignInAccount account =   task.getResult(ApiException.class);
//                firebaseAuth(account.getIdToken());
//            } catch (ApiException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }
//
//
//    }
//
//    private void firebaseAuth(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful())
//                        {
//                            FirebaseUser user = auth.getCurrentUser();
//                            Users users = new Users();
//                            users.setUserId(user.getUid());
//                            users.setName(user.getDisplayName());
//                            users.setProfile(user.getPhotoUrl().toString());
//                            Toast.makeText(MainActivity.this,"Success"+user.getDisplayName(),Toast.LENGTH_LONG).show();
//
//
//                            Intent intent= new Intent(MainActivity.this,HomeScreen.class);
//                            startActivity(intent);
//
//                        }
//                        else {
//                            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//
//    }
//}


package com.example.nearmess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearmess.callbacks.GetAllDataFromDocumentCallBack;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/***
 * Author : Vasudev Raut
 *last edited By : Vasudev Raut
 *Date : 15-08-2025
 * Note :
 */
public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    Button signInButton;

    TextView linksignup;
    Button logIn;
    final String sharedPreferencesFileTitle = "NeerMessApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linksignup = findViewById(R.id.linksignup);
        signInWithGoogle();
        checkInSharedPreferences();
//        logInVefication();

        linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });

        logIn = findViewById(R.id.log_in_button);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInVerification();



            }
        });







    }
    private void checkInSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);

        if(sharedPreferences.contains("user_email")&sharedPreferences.contains("user_pass"))
        {
            String mobile = sharedPreferences.getString("user_email","");
            String pass = sharedPreferences.getString("user_pass","");

            if(!mobile.equals("") && !pass.equals(""))
            {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }
    }



    public void signInWithGoogle()
    {
        signInButton = findViewById(R.id.log_in_with_google);
        auth =FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Vasudev");
        progressDialog.setMessage("We are trying");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }



    int RC_SIGN_IN = 50;

    private void signIn() {

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                // Handle the ApiException gracefully
                Toast.makeText(this, "Google Sign-In failed: ", Toast.LENGTH_SHORT).show();
                // Log the error for debugging purposes
                Log.e("Log_in", "Google Sign-In failed with exception: " + e.getStatusCode(), e);
            }
        }
    }


    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = auth.getCurrentUser();
                            Users users = new Users();
                            users.setUserId(user.getUid());
                            users.setName(user.getDisplayName());
                            users.setProfile(user.getPhotoUrl().toString());
                            users.setEmail(user.getEmail());
                            Toast.makeText(MainActivity.this,"Success"+user.getEmail(),Toast.LENGTH_LONG).show();


                            signInWithGoogleValidation(users);

                        }
                        else {
                            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    public void logInVerification()
    {

        TextInputEditText user_email,user_password;

        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);


        String userEmail,userPassword;

        userEmail = user_email.getText().toString();
        userPassword = user_password.getText().toString();

        loginValidation(userEmail,userPassword);





    }

    private void loginValidation(String userEmail, String userPassword) {

        if(!userEmail.equals(""))
        {
            if(!userPassword.equals(""))
            {
                checkValidationInDatabase(userEmail,userPassword);
            }
            else {
                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();

        }


    }

    private void checkValidationInDatabase(String userEmail, String userPassword) {

        // here check in database if email found then log in and redirect next activity
        MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),this);

        myDatabase.getAllFromDocument(FirestoreKeys.END_USERS, userEmail, new GetAllDataFromDocumentCallBack() {
            @Override
            public void onDataReceived(DocumentSnapshot documentSnapshot) {

                String user_email = documentSnapshot.getId();
                String userPass = documentSnapshot.getString(FirestoreKeys.END_USER_PASS);

                if(userPass!=null)
                {

                    if(userPass!=null && userPass.equals(userPassword))
                    {

                        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_email", user_email);
                        editor.putString("user_pass", userPass);
                        editor.apply();

                        Intent intent= new Intent(MainActivity.this,HomeScreen.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Please Enter correct password..", Toast.LENGTH_SHORT).show();
                    }
//                    Toast.makeText(Log_in.this,"Already",Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(MainActivity.this, "You Don't have and account please register first..", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MainActivity.this,SignUp.class);
                    startActivity(intent);
                }


            }
        });






//                myDatabase.getItemFromDocument();


    }



    // User created Function for check log in with email is already present in database or if not present then register user and then redirect to the next activity
    public void signInWithGoogleValidation(Users users)
    {

        //check database if this email present then directly redirect next activity



        MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),this);
        myDatabase.getAllFromDocument(FirestoreKeys.END_USERS, users.getEmail(), new GetAllDataFromDocumentCallBack() {
            @Override
            public void onDataReceived(DocumentSnapshot documentSnapshot) {

//                String ownerEmail = documentSnapshot.getString(FirestoreKeys.END_?USER_EMAIL);
                String user_email = documentSnapshot.getId();
                String name = documentSnapshot.getString(FirestoreKeys.END_USER_PASS);
//                Toast.makeText(MainActivity.this, name+" raut"+user_email, Toast.LENGTH_SHORT).show();

                if(name!=null)
                {
//                    Toast.makeText(MainActivity.this,"Already",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_email", user_email);
                    editor.putString("user_pass", "LoginWithGoogle");
                    editor.apply();
                    Intent intent= new Intent(MainActivity.this,HomeScreen.class);
                    startActivity(intent);
                    finish();

                }
                else {
//                    Toast.makeText(MainActivity.this, "Please Register First....", Toast.LENGTH_SHORT).show();

                    MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),MainActivity.this);


                    List<String> ls  = new ArrayList<>();
                    SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_email", user_email);
                    editor.putString("user_pass", "LoginWithGoogle");
                    editor.apply();

                    myDatabase.createEndUser(users.getEmail(),users.getName(),"User Direct Login in with google",ls,"101");
                    Toast.makeText(MainActivity.this, "User Created Succesfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,HomeScreen.class);
                    startActivity(intent);
                    finish();
                }


            }
        });



        // else
        //Register user mail.....



    }


}