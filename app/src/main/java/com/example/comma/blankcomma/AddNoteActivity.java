package com.example.comma.blankcomma;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ServiceConfigurationError;


public class AddNoteActivity extends ActionBarActivity {

    private Button addButton;
    private Button cancleButton;
    private PopupWindow popup;

    public final static int BOOK_SEARCH_SERVICE = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        addButton = (Button)findViewById(R.id.btn_addok);
        cancleButton = (Button)findViewById(R.id.btn_cancle);

        addButton.setOnClickListener(mClickListner);
        cancleButton.setOnClickListener(mClickListner);
    }

    Button.OnClickListener mClickListner = new View.OnClickListener() {
        public void onClick(View v) {
            EditText blanktitle = (EditText)findViewById(R.id.blank_title);
            String title = blanktitle.getText().toString();

            EditText blankmemo = (EditText)findViewById(R.id.blank_memo);
            String memo = blankmemo.getText().toString();

            switch (v.getId()) {
                case R.id. btn_addok:
                    if(memo.getBytes().length <= 0){
                        Toast.makeText(AddNoteActivity.this,"fill the memo", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(title.getBytes().length <= 0) {
                        //책 제목이 널인경우 제목없음으로 저장
                        Toast.makeText(AddNoteActivity.this,"no title", Toast.LENGTH_SHORT).show();
                        title = "제목없음";
                        setResult(RESULT_OK);
                        finish();
                    }else{
                       //책 제목이 널이 아니면 검색을 먼저 함
                        Toast.makeText(AddNoteActivity.this, title, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddNoteActivity.this, SearchListActivity.class);
                        intent.putExtra("bookTitle", title);
                        startActivityForResult(intent, BOOK_SEARCH_SERVICE);
                    }
                    break;
                case R.id.btn_cancle:
                    Toast.makeText(AddNoteActivity.this, "cancle", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                    finish();
                    break;
            }
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == BOOK_SEARCH_SERVICE) {
                //책을 선택하고 돌아오면 바로 나가기
                setResult(RESULT_OK);
                finish();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
