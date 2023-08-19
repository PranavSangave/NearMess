package com.example.nearmess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    Button signInButton;

    Button btnCreate,verify_email;
    final String sharedPreferencesFileTitle = "NeerMessApplication";
    Users users;

    String verify_status="";
    TextInputEditText user_name,user_password,user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnCreate = findViewById(R.id.btnCreate);
        signUP();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),Menu.class);
//                startActivity(i);
                checkAgrement();
            }

        });

        TextView already_account = findViewById(R.id.already_account);
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });

//        verify_email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                checkAgrement();
//
//
//            }
//        });





    }

    public void signUP(){
        signInButton = findViewById(R.id.signinWithGoogle);
        auth =FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Near Mess");
        progressDialog.setMessage("Processing.....");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckBox checkAgreement ;
                checkAgreement = findViewById(R.id.user_agreement);

                if(checkAgreement.isChecked())
                {
                    signIn();
                }
                else {
                    Toast.makeText(SignUp.this,"Please checked I agree checkbox",Toast.LENGTH_SHORT).show();

                }




            }
        });

    }
    int RC_SIGN_IN = 40;

    private void signIn() {

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==RC_SIGN_IN)
        {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account =   task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new RuntimeException(e);
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
                            users = new Users();
                            users.setUserId(user.getUid());
                            users.setName(user.getDisplayName());
                            users.setProfile(user.getPhotoUrl().toString());
                            users.setEmail(user.getEmail());
                            verify_status = "Success";
                            Toast.makeText(SignUp.this,"Verify Success",Toast.LENGTH_LONG).show();


//                            Intent intent= new Intent(SignUp.this,Menu.class);
//                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(SignUp.this,"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }



    public void checkAgrement()
    {

        CheckBox checkAgreement ;
        checkAgreement = findViewById(R.id.user_agreement);

        if(checkAgreement.isChecked())
        {
            if(verify_status.equals("Success"))
            {
                registeration();
            }
            else {
                Toast.makeText(this, "Please Vefify email first", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            Toast.makeText(SignUp.this,"Please checked I agree checkbox",Toast.LENGTH_SHORT).show();

        }




    }

    public void registeration()
    {


        user_name = findViewById(R.id.user_name);
        user_email= findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        



       


        String userName,userEmail,userPassword;

        userName = user_name.getText().toString();
        userEmail = user_email.getText().toString();
        userPassword = user_password.getText().toString();
        




       



       


        checkValidation(userName,userEmail,userPassword);













    }

    private void checkValidation(String userName, String userEmail, String userPassword) {

        if(!userName.equals("")) {
            if(!userEmail.equals("")) {
                if(!userPassword.equals("")) {
                    informationStoreToDatabase(userName,userEmail,userPassword);

                }
                else {
                    user_password.setError("");
                    Toast.makeText(this, "Pleas Enter your password", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                user_email.setError("");
                Toast.makeText(this, "Pleas Enter your email", Toast.LENGTH_SHORT).show();

            }
        }
        else {
            user_name.setError("");
            Toast.makeText(this, "Pleas Enter your name", Toast.LENGTH_SHORT).show();
        }

    }

    private void informationStoreToDatabase(String userName, String userEmail, String userPassword) {

        MyDatabase myDatabase = new MyDatabase(FirebaseFirestore.getInstance(),this);
//        progressDialog.dismiss();
        //now store all record here into mess owner database

        List<String> ls  = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_email", userEmail);
        editor.putString("user_pass", userPassword);
        editor.apply();

        myDatabase.createEndUser(users.getEmail(),userName,userPassword,ls,"101");
        Toast.makeText(this, "User Created Succesfully..", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this,HomeScreen.class);
        startActivity(intent);
        finish();



    }


}