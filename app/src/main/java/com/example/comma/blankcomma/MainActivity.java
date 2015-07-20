package com.example.comma.blankcomma;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView list;
    private RelativeLayout layout;
    public final static int NEW_NOTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new MyAdapter(this));

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.sing_row,null);

        TextView title = (TextView)row.findViewById(R.id.text_title);
        TextView memo = (TextView)row.findViewById(R.id.text_memo);
        ImageView image = (ImageView)row.findViewById(R.id.imageView);

        title.setText("테스트");
        memo.setText("새구절 입력");
        image.setImageResource(R.drawable.abc_ic_menu_copy_mtrl_am_alpha);

        layout = (RelativeLayout)findViewById(R.id.new_note_layout);

        //새로운 노트를 입력하는 부분 클릭
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "new memo activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE);
                return false;
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_NOTE) {
                //추가된 데이터를 읽어와 리스트뷰 최상위에 출력

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

class SingleRow{
    String title;
    String memo;
    int image;
    SingleRow(String title, String memo, int image){
        this.title = title;
        this.memo=memo;
        this.image = image;
    }
}
class MyAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;
    Context context;

    MyAdapter(Context c){
        context = c;
        list = new ArrayList<SingleRow>();
        Resources res = c.getResources();
        String [] titles = res.getStringArray(R.array.titles);
        String [] memoes = res.getStringArray(R.array.memos);
        int[] images={R.drawable.booksample1,R.drawable.booksample2,R.drawable.booksample3,R.drawable.booksample4};

        for(int i=0;i<4; i++){
            list.add(new SingleRow(titles[i],memoes[i],images[i]));
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the listview


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.sing_row,parent,false);

        TextView title = (TextView)row.findViewById(R.id.text_title);
        TextView memo = (TextView)row.findViewById(R.id.text_memo);
        ImageView image = (ImageView)row.findViewById(R.id.imageView);

        SingleRow temp = list.get(position);

        //db에서 데이터를 가져와 어댑터를 구성한다
        title.setText(temp.title);
        memo.setText(temp.memo);
        image.setImageResource(temp.image);


        return row;
    }


}