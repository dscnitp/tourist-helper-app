package com.dscnitp.touristshelperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dscnitp.touristshelperapp.Model.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginWithOtp extends AppCompatActivity {

    private Button request, verify;
    private EditText mobile_edit, otp_edit;
    private String phone, mVerificationId,Id;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);

        request = findViewById(R.id.request_btn_otp);
        mobile_edit = findViewById(R.id.phno);
        otp_edit = findViewById(R.id.otp);
        verify=findViewById(R.id.verify_otp);
        mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    otp_edit.setText(code);
                    verifyVerificationCode(code);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(LoginWithOtp.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
            }
        };

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = mobile_edit.getText().toString().trim();

                if(phone.length() < 10 || phone.isEmpty()){
                    mobile_edit.setError("Enter a valid mobile");
                    mobile_edit.requestFocus();
                    return;
                }
                sendVerificationCode(phone);
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp_edit.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    otp_edit.setError("Enter valid code");
                    otp_edit.requestFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginWithOtp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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
                                            String contact=hashMap1.get("contact").toString();
                                            if(contact.equals(phone))
                                            {
                                                FirebaseAuth auth= FirebaseAuth.getInstance();
                                                if(auth.getCurrentUser()!=null) {
                                                    Id = auth.getCurrentUser().getUid();
                                                    Intent intent = new Intent(LoginWithOtp.this, homeActivity.class);
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
                                    "",
                                    "",
                                    phone,
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
                                    Intent intent = new Intent(LoginWithOtp.this, homeActivity.class);
                                    intent.putExtra("Id",Id);
                                    startActivity(intent);
                                }
                            });
                        } else {

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}