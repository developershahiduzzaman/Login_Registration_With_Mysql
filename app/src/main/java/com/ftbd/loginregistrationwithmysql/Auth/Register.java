package com.ftbd.loginregistrationwithmysql.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftbd.loginregistrationwithmysql.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {

    EditText regNameET,regPhoneET,regEmailET,regPasswordET,regConfirmPasswordET;
    Button regBtn;
    TextView goToLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regNameET = findViewById(R.id.regName);
        regPhoneET = findViewById(R.id.regPhone);
        regEmailET = findViewById(R.id.regEmail);
        regPasswordET = findViewById(R.id.regPassword);
        regConfirmPasswordET = findViewById(R.id.regConfirmPassword);
        regBtn = findViewById(R.id.regButton);
        goToLogin = findViewById(R.id.goToLogin);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname,username,email,password;

                fullname = String.valueOf(regNameET.getText());
                username = String.valueOf(regPhoneET.getText());
                email = String.valueOf(regEmailET.getText());
                password = String.valueOf(regPasswordET.getText());


                if (!fullname.equals("") || !username.equals("") || !email.equals("") ||
                            !password.equals("")) {


                        //Start ProgressBar first (Set visibility VISIBLE)
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[4];
                                field[0] = "fullname";
                                field[1] = "username";
                                field[2] = "email";
                                field[3] = "password";
                                //Creating array for data
                                String[] data = new String[4];
                                data[0] = fullname;
                                data[1] = username;
                                data[2] = email;
                                data[3] = password;
                                PutData putData = new PutData("https://loginreg.shahiduzzaman.info/signup.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Registration Success")){
                                            Toast.makeText(Register.this,result, Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),Login.class));
                                            finish();
                                            
                                        }else {

                                            Toast.makeText(Register.this,result, Toast.LENGTH_SHORT).show();
                                            
                                        }
                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    } else {
                        regNameET.setError("Please Enter Name");
                        regPhoneET.setError("Please Enter Phone Number");
                        regEmailET.setError("Please Enter Email");
                        regPhoneET.setError("Please Enter Password");
                        regConfirmPasswordET.setError("Required..!");
                    }



            }
        });

    }
}