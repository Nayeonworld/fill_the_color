package kr.nayeon.fillthecolor;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends Activity  {
    ImageButton left, right;
    ImageButton send;
    TextView time, sum_time;
    final double kcal_min = 2.1;
    final double kcal_hour = 126;
    ImageView hat;
    EditText edit;
    String selected_time=null;//스피너에서 선택된거
    double kcal_sum=0;//누적 칼로리
    double today_kcal=0;
    int input_time =0,sumtime_int=0;//input_time 입력받은 시간 sumtime_int 누적시간
    int color_cnt =0;
    int  goal=20;
    ArrayAdapter <CharSequence> adapter;
    int color[]={R.drawable.hat_red,R.drawable.hat_orange,R.drawable.hat_yellow,R.drawable.hat_green,R.drawable.hat_blue,R.drawable.hat_navy,R.drawable.hat_puple,R.drawable.hat_gold};
  //  DBManager db;
   // DBManager db1;
    Spinner menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //DBManager db = new DBManager(getApplicationContext(),"KCAL.db",null,1);
        //DBManager db1 = new DBManager(getApplicationContext(),"GOAL.db",null,1);
        menu =(Spinner) findViewById(R.id.menu);
        left = (ImageButton)findViewById(R.id.Left_btn);
        right = (ImageButton)findViewById(R.id.Right_btn);
        send = (ImageButton)findViewById(R.id.btn_send);
        time = (TextView)findViewById(R.id.time);
        sum_time = (TextView)findViewById(R.id.sum_time);
        edit=(EditText)findViewById(R.id.edit);
    //    Intent intent = getIntent();
//        goal = intent.getExtras().getInt("goal");
//        kcal_sum=intent.getExtras().getDouble("kcal_sum");
     //   kcal_time=intent.getExtras().getDouble("kcal_time");
        hat = (ImageView)findViewById(R.id.hat);
        adapter = ArrayAdapter.createFromResource(this,R.array.lists,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(adapter);
        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String str =(String)menu.getSelectedItem();
                selected_time = str;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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
    public void onBackPressed() {
        Intent intent = new Intent(this, OpeningActivity.class);
        startActivity(intent);
        finish();
    }
    public void Left(View v){
        Intent intent = new Intent(this, OpeningActivity.class);
        startActivity(intent);
        finish();
    }
    public void Right(View v){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("today_kcal",today_kcal);
        startActivity(intent);
        finish();
    }
    public void Send(View v){
        String str1 = null;
        str1=(edit.getText()).toString();
        input_time = Integer.parseInt(str1);

        String sentence = "적용되었습니다.";
        Toast toast = Toast.makeText(getApplicationContext(),sentence, Toast.LENGTH_SHORT);
        toast.show();

        if(selected_time.equals("분")){
            today_kcal=input_time*kcal_min;
            sumtime_int+=input_time;

        }else if(selected_time.equals("시")){
            today_kcal=input_time*kcal_hour;
            sumtime_int+=input_time*60;

        }
        kcal_sum+=today_kcal;
        sum_time.setText(""+sumtime_int);

        if(goal<=kcal_sum){
            color_cnt++;
            hat.setBackgroundResource(color[color_cnt]);
        }

       // db.update("update KCAL set sum_time = "+sumtime_int+" kcal = "+kcal_sum+" hat = "+color_cnt+" where count =1");

    }


}
