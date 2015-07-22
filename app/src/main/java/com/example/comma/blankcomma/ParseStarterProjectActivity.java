package com.example.comma.blankcomma;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Button btn = (Button) findViewById(R.id.Button01);
		Button btn2 = (Button) findViewById(R.id.Button02);
		
		
        btn.setOnClickListener(new OnClickListener(){
        	TextView mData = (TextView) findViewById(R.id.tv1);
        	
        	@Override
        	public void onClick(View v) {
        		try {
        			ParseACL defaultACL = new ParseACL();
        			defaultACL.setPublicReadAccess(true); // �ش� �����Ϳ� ���� ���� ������ ��� ����� ���� �� �ֵ��� �մϴ�.
        			ParseObject data1 = new ParseObject("Test"); // object ���� �� �߰��� class �̸� �Է�
        			data1.put("email", "park.hwin@gmail.com"); // ������ �Է�
        			data1.put("memo", "MyEmailTest"); // ������ �Է�
        			data1.put("img", "MyEmailTest"); // ������ �Է�
        			data1.setACL(defaultACL); // object�� ACL set
        			data1.save(); // parse.com�� �ش� object save
        			ParseObject data2 = new ParseObject("Test"); // object ���� �� �߰��� class �̸� �Է�
        			data2.put("email", "test5@test.com"); // ������ �Է�
        			data2.put("memo", "test5"); // ������ �Է�
        			data2.setACL(defaultACL); // object�� ACL set
        			data2.save(); // parse.com�� �ش� object save.
        			} catch (ParseException e) {
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
