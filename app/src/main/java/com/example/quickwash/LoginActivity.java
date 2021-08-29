package com.example.quickwash;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    TextView signupButton;
    DatabaseHelper myDb,myDbUser;
    Button loginButton;
    EditText userEmail,userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupButton=(TextView)findViewById(R.id.link_signup);
        loginButton=(Button)findViewById(R.id.btn_login);
        userEmail=(EditText)findViewById(R.id.input_email);
        userPassword=(EditText)findViewById(R.id.input_password);
        final  String sEmail = userEmail.getText().toString();

        myDb=new DatabaseHelper(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMe(sEmail);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            Intent sign=new Intent(LoginActivity.this,SignUpActivity.class);
            @Override
            public void onClick(View v) {
                startActivity(sign);
            }
        });
    }

    private void clickMe(String sEmail)
    {
        Cursor res=myDb.getAccountsData();
        ArrayList<String> userData=new ArrayList<String>();
        ArrayList<String>  lUserData=new ArrayList<String>();
        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();


        if(res!=null && res.getCount() >0)
        {
            while(res.moveToNext())
            {

               userData.add(res.getString(0));
               userData.add(res.getString(1));
               userData.add(res.getString(2));
               userData.add(res.getString(3));
               userData.add(res.getString(4));

            }


            if(userData.contains(email) && userData.contains(password))
            {

                Cursor result=myDb.getLoggedInUserData(email);

                if(result.moveToFirst())
                {
                    String loggedUserName=result.getString(0);
                    String loggeduserAddress=result.getString(1);
                    String loggedUserEmail=result.getString(2);
                    String loggedUserMobile=result.getString(3);
                    String loggedUserPassword=result.getString(4);

                    Toast.makeText(this, "You are logged in successfully", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(this,Home.class);
                    Bundle b=new Bundle();
                    b.putString("Name",loggedUserName);
                    b.putString("Email",loggedUserEmail);
                    b.putString("Address",loggeduserAddress);
                    b.putString("Mobile",loggedUserMobile);
                    i.putExtras(b);
                    startActivity(i);
                }





            }
            if(userData.contains(email) && !userData.contains(password))
            {
                Toast.makeText(this,"Invalid Password",Toast.LENGTH_LONG).show();
            }

            if(!userData.contains(email) && !userData.contains(password))
            {
                Toast.makeText(this,"You are not registered user",Toast.LENGTH_LONG).show();
            }
       }




    }
}
