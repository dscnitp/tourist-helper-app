package com.dscnitp.touristshelperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dscnitp.touristshelperapp.Model.UserProfile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN =1000 ;
    Button google_btn;
    GoogleSignInClient mGoogleSigninClient;
    FirebaseAuth mAuth;
    public String email,Id;
    private Button sign_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sign_otp = findViewById(R.id.sign_in_otp);
        google_btn=(Button) findViewById(R.id.google_btn);
        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        google_btn.setOnClickListener(this);
        mGoogleSigninClient= GoogleSignIn.getClient(this,gso);
        sign_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_otp = new Intent(LoginScreen.this, LoginWithOtp.class);
                startActivity(login_otp);
            }
        });
    }

    private void google_login() {
        Intent signInIntent = mGoogleSigninClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        /*updateUI(account);*/
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            if(account!=null)
            {
                email=account.getEmail();
            }
            firebaseAuth(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google:", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this,"Sign In successful",Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuth(GoogleSignInAccount account) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase firebaseDatabase1=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference1=firebaseDatabase1.getReference("UserProfile");
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                HashMap<String,Object> hashMap= (HashMap<String, Object>) snapshot.getValue();
                                for(String key:hashMap.keySet())
                                {
                                    Object data=hashMap.get(key);
                                    HashMap<String,Object> hashMap1= (HashMap<String, Object>) data;
                                    String email=hashMap1.get("email").toString();
                                    if(email.equals(email))
                                    {
                                        FirebaseAuth auth= FirebaseAuth.getInstance();
                                        if(auth.getCurrentUser()!=null) {
                                            Id = auth.getCurrentUser().getUid();
                                            Intent intent = new Intent(LoginScreen.this, homeActivity.class);
                                            intent.putExtra("Id",Id);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    UserProfile userProfile=new UserProfile(
                            "",
                            email,
                            "",
                            "",
                            "",
                            "","","",""
                    );
                    Id=mAuth.getCurrentUser().getUid();
                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference("UserProfile").child(Id);
                    databaseReference.setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "You logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, homeActivity.class);
                            intent.putExtra("Id",Id);
                            startActivity(intent);
                        }
                    });
                    Toast.makeText(LoginScreen.this,"Firebase Auth successful",Toast.LENGTH_SHORT).show();
                    FirebaseUser user=mAuth.getCurrentUser();
                }
                else
                {
                    Toast.makeText(LoginScreen.this,"Firebase Auth failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view)
    {
        Log.i("onClick:","entered onClick");
        switch (view.getId())
        {
            case R.id.google_btn:
            {
                google_login();
                Log.i("entered"," google login");
            }
        }
    }
}