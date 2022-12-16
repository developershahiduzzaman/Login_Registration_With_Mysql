package com.ftbd.loginregistrationwithmysql.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftbd.loginregistrationwithmysql.MainActivity;
import com.ftbd.loginregistrationwithmysql.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {
    TextView goToReg;
    EditText logPhoneET,logPasswordET;
    Button logButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goToReg = findViewById(R.id.goToReg);
        logPhoneET = findViewById(R.id.logPhone);
        logPasswordET = findViewById(R.id.loginPassword);
        logButton = findViewById(R.id.loginButton);


        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname,username,email,password;

                username = String.valueOf(logPhoneET.getText());
                password = String.valueOf(logPasswordET.getText());


                if (!username.equals("") || !password.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("https://loginreg.shahiduzzaman.info/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")){
                                        Toast.makeText(Login.this,result, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();

                                    }else {

                                        Toast.makeText(Login.this,result, Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    logPhoneET.setError("Please Enter Phone Number");
                    logPasswordET.setError("Please Enter Password");

                }



            }
        });
    }
}