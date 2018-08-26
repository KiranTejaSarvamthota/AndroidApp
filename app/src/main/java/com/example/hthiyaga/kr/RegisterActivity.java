package com.example.hthiyaga.kr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/**
 * Created by hthiyaga on 4/15/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

EditText editTextemail, editTextPassword;
ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextemail = (EditText) findViewById(R.id.textInputEditTextEmail);
        editTextPassword =(EditText) findViewById(R.id.textInputEditTextPassword);
        progressBar =(ProgressBar)findViewById(R.id.progressbar) ;
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.appCompatButtonRegister).setOnClickListener(this);
        findViewById(R.id.appCompatTextViewLoginLink).setOnClickListener(this);


    }
private void registerUser(){

  String email= editTextemail.getText().toString().trim();
  String password= editTextPassword.getText().toString().trim();

  if(email.isEmpty()){

      editTextemail.setError("Email is required");
      editTextemail.requestFocus();
      return;
  }

  if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
      editTextemail.setError("Please enter a valid email");
      editTextemail.requestFocus();
      return;

  }

  if(password.isEmpty()){

      editTextPassword.setError("Password is required");
      editTextPassword.requestFocus();
      return;
  }

  if(password.length()<6){
      editTextPassword.setError("Minimum length of password is 6");
      editTextPassword.requestFocus();
      return;
  }

  progressBar.setVisibility(View.VISIBLE);
mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        progressBar.setVisibility(View.GONE);
        if(task.isSuccessful()){

            Toast.makeText(getApplicationContext(),"User Registered Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent (RegisterActivity.this, UserActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        else {

            if(task.getException() instanceof FirebaseAuthUserCollisionException){

                Toast.makeText(getApplicationContext(),"Email already exists", Toast.LENGTH_SHORT).show();
            }

           else{

                Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }



        }
    }
});

}

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.appCompatButtonRegister:

                registerUser();

         break;

            case R.id.appCompatTextViewLoginLink:
                startActivity(new Intent(this,MainActivity.class));

        }
    }
}
