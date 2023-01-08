package com.sogonggirls.gogogo.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sogonggirls.gogogo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private EditText mEmail, mPass;
    private TextView check_id;
    private Button goSignup, btnLogin, btnLogout;

    private LinearLayout ly_login_o, ly_login_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ly_login_o = findViewById(R.id.layout_login_o);
        ly_login_x = findViewById(R.id.layout_login_x);

        mEmail = findViewById(R.id.input_email);
        mPass = findViewById(R.id.input_pass);
        check_id = findViewById(R.id.tv_currentUser);

        btnLogin = findViewById(R.id.btn_login);
        goSignup = findViewById(R.id.go_signup);
        btnLogout = findViewById(R.id.btn_logout);

        //회원가입으로이동
        goSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        //로그인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPass.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null){
//                                        Toast.makeText(LoginActivity.this, "로그인 성공 : " + user.getUid(), Toast.LENGTH_SHORT).show();
//                                        check_id.setText("로그인 : " + user.getUid());
                                        Toast.makeText(LoginActivity.this, "로그인 성공 : " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                        check_id.setText(user.getEmail() + "님 환영합니다.");
                                        ly_login_x.setVisibility(View.GONE);
                                        ly_login_o.setVisibility(View.VISIBLE);
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, "login error.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        //로그아웃
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(LoginActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
                ly_login_o.setVisibility(View.GONE);
                ly_login_x.setVisibility(View.VISIBLE);
            }
        });
    }//onCreate

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            ly_login_x.setVisibility(View.GONE);
            ly_login_o.setVisibility(View.VISIBLE);
//            Toast.makeText(this, "자동로그인" + user.getUid(), Toast.LENGTH_SHORT).show();
//            check_id.setText("로그인 : " + user.getUid());
            Toast.makeText(this, "자동로그인" + user.getEmail(), Toast.LENGTH_SHORT).show();
            check_id.setText(user.getEmail() + "님 환영합니다.");

        }else{
            ly_login_o.setVisibility(View.GONE);
            ly_login_x.setVisibility(View.VISIBLE);
        }
    }
}