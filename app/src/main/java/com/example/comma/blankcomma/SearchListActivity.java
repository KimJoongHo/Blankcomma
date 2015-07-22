package com.example.comma.blankcomma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhdd.messi.Naver;
import com.hhdd.messi.event.NaverEventListener;
import com.hhdd.messi.naver.object.search.BookObject;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class SearchListActivity extends Activity implements NaverEventListener.OnBookListener {

    private Button selectButton;
    private Button selectCancle;
    final Naver open_api = new Naver();
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


        open_api.setBookListener(this);
        open_api.setSearchKey("a0467f281a6f774d9e9fcd07d96c8c2d");
        String title = getIntent().getStringExtra("bookTitle");
        Toast.makeText(SearchListActivity.this, title + "·Î °Ë»ö",Toast.LENGTH_LONG).show();
        open_api.BookSearch(title);


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
                    EditText editText = (EditText)findViewById(R.id.editText);
                    open_api.BookSearch(editText.getText().toString());
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

    @Override
    public void onResult(final ArrayList<BookObject> arrayList) {

        ListView listView = (ListView)findViewById(R.id.list_book_search);
        ListAdapter bookAdapter = new ListAdapter(this, R.layout.item_row, arrayList);
        listView.setAdapter(bookAdapter);
        /*listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                BookObject obj = arrayList.get(position);
           *//*     Intent page = new Intent(SearchListActivity.this, SearchListActivity.class);
                page.putExtra("title", obj.getTitle());
                page.putExtra("author", obj.getAuthor());
                page.putExtra("imageurl", obj.getImageUrl());
                page.putExtra("price", obj.getPrice());
                page.putExtra("discount", obj.getDiscount());
                page.putExtra("isbn", obj.getISBN());
                page.putExtra("dscrp", obj.getDescription());
                page.putExtra("link", obj.getPubdate());
                startActivity(page);*//*
            }

        });*/


    }

    @Override
    public void onFaultResult(int i, String s) {

        Toast.makeText(SearchListActivity.this, "not found", Toast.LENGTH_LONG).show();
    }

    public class ListAdapter extends ArrayAdapter<BookObject> {
        public ArrayList<BookObject> items;

        public ListAdapter(Context context, int textViewResourceId, ArrayList<BookObject> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.item_row, null);
            }

            BookObject Info = items.get(position);

            if (Info != null) {
                ImageView iv1 = (ImageView) v.findViewById(R.id.iv1);
                TextView tv1 = (TextView) v.findViewById(R.id.tv1);
                TextView tv2 = (TextView) v.findViewById(R.id.tv2);
                Info.BindImage(iv1);
                tv1.setText(Info.getTitle());
                tv2.setText(Info.getDescription());
            }
            return v;
        }

    }

}
