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
        			defaultACL.setPublicReadAccess(true); // 해당 데이터에 대한 접근 권한을 모든 사람이 읽을 수 있도록 합니다.
        			ParseObject data1 = new ParseObject("Test"); // object 생성 및 추가될 class 이름 입력
        			data1.put("email", "park.hwin@gmail.com"); // 데이터 입력
        			data1.put("memo", "MyEmailTest"); // 데이터 입력
        			data1.put("img", "MyEmailTest"); // 데이터 입력
        			data1.setACL(defaultACL); // object에 ACL set
        			data1.save(); // parse.com에 해당 object save
        			ParseObject data2 = new ParseObject("Test"); // object 생성 및 추가될 class 이름 입력
        			data2.put("email", "test5@test.com"); // 데이터 입력
        			data2.put("memo", "test5"); // 데이터 입력
        			data2.setACL(defaultACL); // object에 ACL set
        			data2.save(); // parse.com에 해당 object save.
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
        			ArrayList<ParseObject> datas = new ArrayList<ParseObject>(); // parse.com에서 읽어온 object들을 저장할 List
        			ParseQuery<ParseObject> query = ParseQuery.getQuery("Test"); // 서버에 mydatas class 데이터 요청
        			query.whereEqualTo("email", "test4@test.com"); // my_type이 1인 object만 읽어옴. 해당 함수 호출하지 않으면 class의 모든 데이터를 읽어옴.
        			datas.addAll(query.find()); // 읽어온 데이터를 List에 저장
        			// 읽어온 데이터를 화면에 보여주기 위한 처리
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
        			mData.setText(str.toString()); // TextView에 데이터를 넣어준다.
        			  datas.clear();
        			} catch (ParseException e) {
        			e.printStackTrace();
        			}
        	}
        });
	
        
        
	}
}
