package com.example.comma.blankcomma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity implements OnClickListener {
	// Declare Variables
	Button loginbutton;
	String useremailtxt;
	String passwordtxt;
	EditText password;
	EditText useremail;
	Button admin;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);

		useremail = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.signup).setOnClickListener(this);
		findViewById(R.id.login_admin).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
 				case R.id.login:
				onLogin();
				break;
			case R.id.signup:
				onSignup();
				break;
			case R.id.login_admin:
				startMain();
				break;
		}
	}

	private void startMain() {
		startActivity(new Intent(LoginSignupActivity.this, MainFlowActivity.class));
	}

	protected void onLogin() {
		useremailtxt = useremail.getText().toString();
		passwordtxt = password.getText().toString();

		ParseUser.logInInBackground(useremailtxt, passwordtxt, new LogInCallback() {
			@Override
			public void done(ParseUser parseUser, ParseException e) {
				if (parseUser != null) {
					Intent intent = new Intent(LoginSignupActivity.this, Welcome.class);
					startActivity(intent);
					Toast.makeText(LoginSignupActivity.this, ParseUser.getCurrentUser().getUsername() + "으로 Login에 성공했습니다.", Toast.LENGTH_LONG).show();
					finish();
				} else {
					Toast.makeText(LoginSignupActivity.this, "일치하는 정보가 없습니다.", Toast.LENGTH_LONG).show();
					return;
				}

				if (e != null) {
					if (BuildConfig.DEBUG) e.printStackTrace();
					Toast.makeText(LoginSignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
					return;
				}
			}
		});
	}

	protected void onSignup() {
		Intent intent = new Intent(LoginSignupActivity.this, SignupActivity.class);
		startActivity(intent);
		finish();
	}
}

//		final ParseUser user = new ParseUser();
//
//		useremailtxt = useremail.getText().toString();
//		passwordtxt = password.getText().toString();
//
//		if (useremailtxt.equals("")) {
//			Toast.makeText(getApplicationContext(),
//					"Email을 입력해주세요",
//					Toast.LENGTH_LONG).show();
//		} else if (passwordtxt.equals("")) {
//			Toast.makeText(getApplicationContext(),
//					"비밀번호를 입력해주세요",
//					Toast.LENGTH_LONG).show();
//		} else {
//			// Save new user data into Parse.com Data Storage
//			user.signUpInBackground(new SignUpCallback() {
//				public void done(ParseException e) {
//					if (e != null) {
//						if (BuildConfig.DEBUG) e.printStackTrace();
//						Toast.makeText(LoginSignupActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//						return;
//					}else if (e == null) {
//						// Show a simple Toast message upon successful registration
//						Intent intent = new Intent(LoginSignupActivity.this, SignupActivity.class);
//						startActivity(intent);
//						Toast.makeText(getApplicationContext(),
//								user.getEmail() + "님 회원가입 성공! 로그인 해주세요.",
//								Toast.LENGTH_LONG).show();
//						finish();
//					} else {
//						Toast.makeText(getApplicationContext(),
//								"회원가입 실패!", Toast.LENGTH_LONG)
//								.show();
//					}
//				}
//			});
//		}
