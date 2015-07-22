package com.example.comma.blankcomma;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.androidbegin.parseimageupload.MainActivity;
//import com.androidbegin.parseimageupload.R;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Welcome extends Activity {
	
	// Declare Variable
	Button logout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.welcome);
		
		// Retrieve current user from Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convert currentUser into String
		String struser = currentUser.getUsername().toString();
		
		// Locate TextView in welcome.xml
		TextView txtuser = (TextView) findViewById(R.id.txtuser);

		// Set the currentUser String into TextView
		txtuser.setText(struser + "님 환영합니다");
		
		// Locate Button in welcome.xml
		logout = (Button) findViewById(R.id.logout);

		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Logout current user
				ParseUser.logOut();
				finish();
			}
		});
		
		Button btn = (Button) findViewById(R.id.Button01);
		Button btn2 = (Button) findViewById(R.id.Button02);
		
		
        btn.setOnClickListener(new OnClickListener(){
        	TextView mData = (TextView) findViewById(R.id.tv1);
        	
        	@Override
        	public void onClick(View v) {
        		try {
        			ParseACL defaultACL = new ParseACL();
        			defaultACL.setPublicReadAccess(true); // �ش� �����Ϳ� ���� ���� ������ ��� ����� ���� �� �ֵ��� �մϴ�.
        			ParseObject data1 = new ParseObject("Image"); // object ���� �� �߰��� class �̸� �Է�

    				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
    						R.drawable.androidbegin);
    				ByteArrayOutputStream stream = new ByteArrayOutputStream();
    				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    				byte[] image = stream.toByteArray();
    				ParseFile file = new ParseFile("androidbegin.png", image);
    				

        			data1.put("email", "park.hwin@gmail.com"); // ������ �Է�
        			data1.put("memo", "MyEmailTest"); // ������ �Է�
        			data1.put("img", file);
        			
        			data1.setACL(defaultACL); // object�� ACL set	
        			data1.save(); // parse.com�� �ش� object save
        			
        			ParseObject data2 = new ParseObject("Test"); // object ���� �� �߰��� class �̸� �Է�
        			data2.put("email", "test5@test.com"); // ������ �Է�
        			data2.put("memo", "test5"); // ������ �Է�
        			data2.setACL(defaultACL); // object�� ACL set
        			data2.save(); // parse.com�� �ش� object save
        			
        			Toast toast = Toast.makeText(getApplicationContext(),
        					   "����!!", Toast.LENGTH_LONG);
        			toast.setGravity(Gravity.CENTER, 0, 0);
       
        			toast.show();
        			
        			}catch (ParseException e) {
        				e.printStackTrace();
        			}
        	}
        });
        btn2.setOnClickListener(new OnClickListener(){
        	TextView mData = (TextView) findViewById(R.id.tv1);
        	
        	@Override
        	public void onClick(View v) {
        		try {
        			ArrayList<ParseObject> datas = new ArrayList<ParseObject>(); // parse.com���� �о�� object���� ������ List
        			ParseQuery<ParseObject> query = ParseQuery.getQuery("Test"); // ������ mydatas class ������ ��û
        			query.whereEqualTo("email", "test4@test.com"); // my_type�� 1�� object�� �о��. �ش� �Լ� ȣ������ ������ class�� ��� �����͸� �о��.
        			datas.addAll(query.find()); // �о�� �����͸� List�� ����
        			// �о�� �����͸� ȭ�鿡 �����ֱ� ���� ó��
        			StringBuffer str = new StringBuffer();
        			for(ParseObject object : datas){
        				str.append("ObjectId: ");
        				str.append(object.getObjectId());
        				str.append("\n");
        				str.append("email: ");
        				str.append(object.get("email"));
        				str.append(", ");
        				str.append("memo: ");
        				str.append(object.get("memo"));
        				str.append("\n\n");
        			}
        			mData.setText(str.toString()); // TextView�� �����͸� �־��ش�.
        			  datas.clear();
        			} catch (ParseException e) {
        			e.printStackTrace();
        			}
        	}
        });
	}
}