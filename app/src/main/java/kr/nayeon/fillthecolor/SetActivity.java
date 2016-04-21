package kr.nayeon.fillthecolor;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SetActivity extends Activity {
    double kcal_sum=0;//누적칼로리
    double kcal_time=0;
    int goal=0;
    TextView month_kcal;
    ImageButton reset, apply;
    Button setMonth;
    int count;
  //  DBManager db,db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        month_kcal = (TextView)findViewById(R.id.month_text);
        reset = (ImageButton) findViewById(R.id.btn_setting);
        apply = (ImageButton)findViewById(R.id.apply);
        setMonth = (Button)findViewById(R.id.setMonth);
      //  db = new DBManager(getApplicationContext(),"KCAL.db",null,1);
      //  db2 = new DBManager(getApplicationContext(),"GOAL.db",null,1);
      //  if(db.CheckIsDataAlreadyInDBorNot("KCAL")==true){
//            month_kcal.setText(db2.printGoal());
       //     String str =db2.printGoal();
       ///     goal = Integer.parseInt(str);
     //   }
        getMonth();

    }
    public void getMonth(){
        if(count!=0) {
            Intent intent = getIntent();
            goal = intent.getExtras().getInt("goal");
            System.out.println("goal : "+goal);
            month_kcal.setText("" + goal);
        }else
        {
            goal=0;
            month_kcal.setText(""+goal);
        }

    }
    public void setMonth(View v){
        count++;
        Intent intent =  new Intent(this, SetMonthActivity.class);
        startActivity(intent);
        finish();

    }
    public void setting(View v){
        Intent intent = new Intent(v.getContext(), OpeningActivity.class);
        intent.putExtra("kcal_sum",0);
        intent.putExtra("kcal_time",0);
        /*if(db.CheckIsDataAlreadyInDBorNot("KCAL")==true){
            db.delete("delete * from KCAL");
            db2.delete("delete * from GOAL");
        }
        db.insert("insert into KCAL values(0,0,0,0,1);");
        db2.insert("insert into GOAL values(0,1);");
        */
        String sentence = "초기화 적용되었습니다.";
        Toast toast = Toast.makeText(getApplicationContext(),sentence, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void apply(View v){
        Intent intent = new Intent(v.getContext(), OpeningActivity.class);
        intent.putExtra("goal",goal);
        startActivity(intent);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set, menu);
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
}
