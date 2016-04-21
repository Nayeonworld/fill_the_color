package kr.nayeon.fillthecolor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SetMonthActivity extends Activity {
    EditText setMonth=null;
    Button apply;
    int setMonth_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_month);

        setMonth=(EditText)findViewById(R.id.setMonth_Edit);

        apply=(Button)findViewById(R.id.setMonth_btn);
         apply.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(SetMonthActivity.this, SetActivity.class);
                 intent.putExtra("goal",setMonth_int);
                 startActivity(intent);
                 finish();
             }
         });

    }
    public void Send(View v){
        if(setMonth!=null) {
            // setMonth_int = Integer.parseInt(setMonth.getText().toString());
            String sText = setMonth.getText().toString();
            setMonth_int = Integer.parseInt(sText);
            //DBManager db = new DBManager(getApplicationContext(),"KCAL.db",null,1);
            //db.update("update Goal set goal = "+setMonth_int+"where count =1");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_month, menu);
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
        Intent intent = new Intent(this, SetActivity.class);
        startActivity(intent);
        finish();
    }
}
