package com.example.shadow47.movies;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.hawk.Hawk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {


    private EditText email, password;
    private Button login;
    private TextView signUp,loginText;
    private FirebaseAuth auth;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email);
        pd=new ProgressDialog(MainActivity.this);
        password=findViewById(R.id.password);
        login=findViewById(R.id.lg);
        signUp=findViewById(R.id.signUp);
        loginText=findViewById(R.id.Login);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr =email.getText().toString();
                final String passwordstr =password .getText().toString();

                if (TextUtils.isEmpty(emailstr)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordstr)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);
                pd.setMessage("Signing in");
                pd.setCancelable(false);
                pd.show();

                //authenticate user
                auth.signInWithEmailAndPassword(emailstr, passwordstr)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                pd.dismiss();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                     {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
                                    mDatabase.child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    Intent intent = new Intent(MainActivity.this, Navigation.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }


}
