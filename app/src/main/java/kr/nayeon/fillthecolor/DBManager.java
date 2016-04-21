package kr.nayeon.fillthecolor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE KCAL(kcal real, hat integer, sum_time integer, count integer);");
        db.execSQL("CREATE TABLE GOAL(goal integer,count integer);");
        //DBManager db = new DBManager(getApplicationContext(),"KCAL.db",null,1);
        //db.insert("insert into KCAL values(0.0,0,0,100,1);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public void update(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public void delete(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public String printKcal(){
        SQLiteDatabase db = getReadableDatabase();
        String str ="";
        Cursor cursor = db.rawQuery("select kcal from KCAL", null);
        str+=cursor.getFloat(0);
        return str;
    }
    public String printSumtime(){
        SQLiteDatabase db = getReadableDatabase();
        String str ="";
        Cursor cursor = db.rawQuery("select sum_time from KCAL", null);
        str+=cursor.getFloat(1);
        return str;
    }
    public String printGoal(){
        SQLiteDatabase db = getReadableDatabase();
        String str ="";
        Cursor cursor = db.rawQuery("select goal from GOAL", null);
        str+=cursor.getInt(0);
        return str;
    }
    public boolean CheckIsDataAlreadyInDBorNot(String TableName
                                                     ) {
        SQLiteDatabase sqldb = getReadableDatabase();
        String Query = "Select * from " + TableName +";";
        Cursor cursor = sqldb.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
