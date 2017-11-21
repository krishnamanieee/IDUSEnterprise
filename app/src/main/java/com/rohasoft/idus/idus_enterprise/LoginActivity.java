package com.rohasoft.idus.idus_enterprise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rohasoft.idus.idus_enterprise.other.GetUserCallback;
import com.rohasoft.idus.idus_enterprise.other.ServerRequest;
import com.rohasoft.idus.idus_enterprise.other.User;
import com.rohasoft.idus.idus_enterprise.other.UserLocalStore;


/**
 * Created by Ayothi selvam on 19-10-2017.
 */

public class LoginActivity extends Activity {

    EditText editText_username,editText_password;
    Button button_login;
    UserLocalStore userLocalstore;

    String username,pass;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_username= (EditText) findViewById(R.id.editText_UserId);
        editText_password= (EditText) findViewById(R.id.editText_Password);

        button_login= (Button) findViewById(R.id.button_login);


        userLocalstore=new UserLocalStore(this);

        logIn();




    }

    private void logIn() {

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=editText_username.getText().toString();
                pass=editText_password.getText().toString();
                User user=new User(username,pass);
                authenticate(user);
            }
        });
    }

    private void authenticate(User user){
        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returedUser) {
                if (returedUser == null){
                    showErrorMessage();
                }else {
                    logUserIn(returedUser );
                }
            }
        });
    }

    private void showErrorMessage(){

        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Incorrect user details :(");
        builder.setPositiveButton("ok",null);
        builder.show();

    }

    private void logUserIn(User returnedUser ){

        userLocalstore.storeUserData(returnedUser);
        userLocalstore.setUserLoggedIn(true);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
    /*@Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));


        }

    }
    public  boolean authenticate(){
        return userLocalstore.getUserLoggedIn();

    }*/








}
