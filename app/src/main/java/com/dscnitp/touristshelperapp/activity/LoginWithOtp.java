package com.dscnitp.touristshelperapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dscnitp.touristshelperapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginWithOtp extends AppCompatActivity {
@BindView(R.id.request_btn_otp)
   public Button request;
@BindView(R.id.verify_otp)
   public Button verify;
@BindView(R.id.phno)
    public EditText mobile_edit ;
    @BindView(R.id.otp)
    public  EditText otp_edit;
    private String phone, mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    otp_edit.setText(code);
                    otp_edit.setVisibility(View.VISIBLE);
                    verify.setVisibility(View.VISIBLE);
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

    }
    @OnClick(R.id.request_btn_otp)
    public void requestClick()
    {
        phone = mobile_edit.getText().toString().trim();

        if(phone.length() < 10 || phone.isEmpty()){
            mobile_edit.setError("Enter a valid mobile");
            mobile_edit.requestFocus();
            return;
        }
        sendVerificationCode(phone);
    }
@OnClick (R.id.verify_otp)
public void verifyClick(){
    String code = otp_edit.getText().toString().trim();
    if (code.isEmpty() || code.length() < 6) {
        otp_edit.setError("Enter valid code");
        otp_edit.requestFocus();
        return;
    }
    verifyVerificationCode(code);
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
                            Toast.makeText(getApplicationContext(), "You logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginWithOtp.this, HomeActivity.class);
                            startActivity(intent);

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