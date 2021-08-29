package com.example.quickwash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    TextView loginButton;
    DatabaseHelper myDb;
    EditText name,address,email,mobile,password,reEnterPassword;
    Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         myDb=new DatabaseHelper(this);

         name=(EditText)findViewById(R.id.input_name);
         address=(EditText)findViewById(R.id.input_address);
         email=(EditText)findViewById(R.id.input_email);
         mobile=(EditText)findViewById(R.id.input_mobile);
         password=(EditText)findViewById(R.id.input_password);
         reEnterPassword=(EditText)findViewById(R.id.input_reEnterPassword);

         signupButton=(Button)findViewById(R.id.btn_signup);

         String sName = name.getText().toString();
        final String sAddress= address.getText().toString();
        final  String sEmail = email.getText().toString();
        final  String sMobile=mobile.getText().toString();
        final String sPassword=password.getText().toString();
        final String sReEnterpassword=reEnterPassword.getText().toString();

        signupButton.setOnClickListener(new View.OnClickListener()

         {
             @Override
             public void onClick(View v)
             {

                  insert();


             }
         });









        loginButton=(TextView)findViewById(R.id.link_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            Intent login=new Intent(SignUpActivity.this,LoginActivity.class);
            @Override
            public void onClick(View v) {
                startActivity(login);
            }
        });
    }

    private void insert()
    {
       String sName = name.getText().toString();
       String sAddress= address.getText().toString();
       String sEmail = email.getText().toString();
       String sMobile=mobile.getText().toString();
       String sPassword=password.getText().toString();
       String sReEnterpassword=reEnterPassword.getText().toString();

         if(TextUtils.isEmpty(sName) || TextUtils.isEmpty(sAddress) || TextUtils.isEmpty(sEmail)  || TextUtils.isEmpty(sMobile) || TextUtils.isEmpty(sPassword) || TextUtils.isEmpty(sReEnterpassword))
        {
            Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();

        }

       else if(!sPassword.equals(sReEnterpassword))
         {
             Toast.makeText(getApplicationContext(), "Password Mismatch", Toast.LENGTH_SHORT).show();

         }
        else
        {

            Boolean result = myDb.insertData(sName,sAddress,sEmail,sMobile,sPassword);

            if(result==true)
            {
                Toast.makeText(this,"You are siged up successfully",Toast.LENGTH_SHORT).show();

            }
            else
            {

                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();

            }
        }



    }
}
