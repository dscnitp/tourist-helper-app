package com.dscnitp.touristshelperapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dscnitp.touristshelperapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN =1000 ;
    @BindView(R.id.google_btn)
    Button google_btn;
    GoogleSignInClient mGoogleSigninClient;
    FirebaseAuth mAuth;
    @BindView(R.id.sign_in_otp)
   public Button sign_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mAuth=FirebaseAuth.getInstance();
        ButterKnife.bind(this);

        if(new File("/data/data/your_application_package/shared_prefs/googleSignIn.xml").exists() || new File("/data/data/your_application_package/shared_prefs/numSignIn.xml").exists()){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        google_btn.setOnClickListener(this);
        mGoogleSigninClient= GoogleSignIn.getClient(this,gso);

    }
    @OnClick(R.id.sign_in_otp)
    public void Onselected ()
    {
        Intent login_otp = new Intent(LoginScreen.this, LoginWithOtp.class);
        startActivity(login_otp);
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

            SharedPreferences prefs = getSharedPreferences("userCredentials", 0);
            prefs.edit().putBoolean("googleSignIn", true).apply();
            prefs.edit().putString("email", account.getEmail()).apply();
            prefs.edit().putString("id", account.getId()).apply();
            prefs.edit().putString("username", account.getDisplayName()).apply();


            // Signed in successfully, show authenticated UI.
            Toast.makeText(this,account.getDisplayName()+" is in!",Toast.LENGTH_SHORT).show();
            firebaseAuth(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google:", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this,"Sign In successful",Toast.LENGTH_SHORT).show();
        }
    }

    static final String PREF_USER_NAME= "abc";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }



    private void firebaseAuth(GoogleSignInAccount account) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginScreen.this,"Firebase Auth successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                    FirebaseUser user=mAuth.getCurrentUser();
                }
                else
                {
                    Toast.makeText(LoginScreen.this,"Firebase Auth failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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