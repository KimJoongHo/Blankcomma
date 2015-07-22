package com.example.comma.blankcomma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity implements View.OnClickListener  {
    Button signup;
    EditText useremail;
    EditText username;
    EditText password;
    String useremailtxt;
    String usernametxt;
    String passwordtxt;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.signup);

        useremail = (EditText) findViewById(R.id.etEmail);
        username = (EditText) findViewById(R.id.etUserName);
        password = (EditText) findViewById(R.id.etPass);

        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.agepicker, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        findViewById(R.id.btnSingIn).setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.btnSingIn){
            onSignup();
        }
    }

    protected void onSignup(){
        final ParseUser user = new ParseUser();

		useremailtxt = useremail.getText().toString();
        usernametxt = username.getText().toString();
		passwordtxt = password.getText().toString();

		if (useremailtxt.equals("")) {
			Toast.makeText(getApplicationContext(),
                    "Email을 입력해주세요",
                    Toast.LENGTH_LONG).show();
		} else if (usernametxt.equals("")) {
			Toast.makeText(getApplicationContext(),
					"이름을 입력해주세요",
					Toast.LENGTH_LONG).show();
		} else if (passwordtxt.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "비밀번호를 입력해주세요",
                    Toast.LENGTH_LONG).show();
        } else {
            user.setEmail(useremailtxt);
            user.setUsername(usernametxt);
            user.setPassword(passwordtxt);
			// Save new user data into Parse.com Data Storage
			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					if (e != null) {
						if (BuildConfig.DEBUG) e.printStackTrace();
						Toast.makeText(SignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
						return;
					}else if (e == null) {
						// Show a simple Toast message upon successful registration
						Intent intent = new Intent(SignupActivity.this, Welcome.class);
						startActivity(intent);
						Toast.makeText(getApplicationContext(),
								user.getEmail() + "님 회원가입 성공! 로그인 해주세요.",
								Toast.LENGTH_LONG).show();
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								"회원가입 실패!", Toast.LENGTH_LONG)
								.show();
					}
				}
			});
		}

    }
}
