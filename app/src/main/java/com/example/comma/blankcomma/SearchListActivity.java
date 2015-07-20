package com.example.comma.blankcomma;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class SearchListActivity extends Activity {

    private Button selectButton;
    private Button selectCancle;
    //private TextView searchtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.activity_search_list);
/*
        searchtitle = (TextView)findViewById(R.id.search_title);
        searchtitle.setText(getIntent().getStringExtra("bookTitle"));*/
        selectButton = (Button)findViewById(R.id.btn_select_book);
        selectButton.setOnClickListener(mClickListner);

        selectCancle = (Button)findViewById(R.id.btn_select_cancle);
        selectCancle.setOnClickListener(mClickListner);
    }

    Button.OnClickListener mClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_select_book:
                    setResult(RESULT_OK);
                    Toast.makeText(SearchListActivity.this, "result ok", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.btn_select_cancle:
                    finish();
                    break;
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_list, menu);
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
