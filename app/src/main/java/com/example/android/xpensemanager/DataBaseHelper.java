package com.example.android.xpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="new3.db";
    public static final String TABLE_NAME_1="expenses";
    public static final String TABLE_NAME_2="incomes";



    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME_1+"(user text,Food int, Electronics int ,Transport int,Gift int, Bills int, Home int, Education int, ChildCare int , Miscellaneous int ,Date text,sum int)");
        db.execSQL("create table "+TABLE_NAME_2+"(user text,Business int, Salary int, Coupon int ,Grants int, Sale int ,Date text,sum int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME_1);
        onCreate(db);

    }
    public boolean insertData(String tableName,String colName,String username,int value)

    {


        SQLiteDatabase db=this.getWritableDatabase();
        int sum;

        String[] columns = { colName };
        String selection = "user" + " =?";
        String[] selectionArgs = { username };
        String limit = "1";

        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        long result=0; int value1=value;
        String d="";


        if(exists)
        {
            //change to select query
           Cursor resdate=db.rawQuery("select Date from "+tableName+" where user = \""+username+"\"",null);
            if( resdate != null && resdate.moveToFirst() ){
                d = resdate.getString(resdate.getColumnIndex("Date"));
                resdate.close();
            }

          if(!d.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()))
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put("user",username);
                contentValues.put(colName,value);
                contentValues.put("sum",value);
                contentValues.put("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
                result=db.insert( tableName,null,contentValues);
            }

            else {
              Cursor res = db.rawQuery("select " + colName + " from " + tableName, null);
              boolean m = (cursor.getCount() > 0);
              if (m) {
                  res.moveToFirst();
                  String prevSal = res.getString(0);
                  if (prevSal != null) {
                      int prev = Integer.parseInt(prevSal);
                      value1 = value + prev;
                  }
              }


              ContentValues contentValues1 = new ContentValues();
              contentValues1.put(colName, value1);
              String where = "user=? and date =?";
              String[] whereArgs = new String[]{username, new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().toString()};
              db.update(tableName, contentValues1, where, whereArgs);


              String[] columns2 = {"sum"};
              String selection2 = "user =? and date =?";
              String[] selectionArgs2 = {username, new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()};
              String limit1 = "1";

              Cursor cursor1 = db.query(tableName, columns2, selection2, selectionArgs2, null, null, null, limit1);
              cursor1.moveToFirst();
              String prevSum = cursor1.getString(0);
              int prev = Integer.parseInt(prevSum);
              value = value + prev;

              ContentValues contentValues2 = new ContentValues();
              contentValues1.put("sum", value);

              String where1 = "user=? and date=?";
              String[] whereArgs1 = new String[]{username, new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()};


              db.update(tableName, contentValues2, where1, whereArgs1);
          }



        }
        else
        {

            ContentValues contentValues=new ContentValues();
            contentValues.put("user",username);
            contentValues.put(colName,value);
            contentValues.put("sum",value);
            contentValues.put("date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            result=db.insert( tableName,null,contentValues);
        }





          if(result==-1)
              return false;
        else
            return true;


    }
public Cursor retrieveData(String username, String date,String table)
{

    SQLiteDatabase db=this.getWritableDatabase();
    //Cursor cursor = db.rawQuery("select * from  incomes",null);


    String[] columns2 = { "*" };
    String selection2 = "user =? and date =?";
    String[] selectionArgs2 = { username,date };
    String limit1 = "1";

    Cursor cursor= db.query(table, columns2, selection2, selectionArgs2, null, null, null, limit1);
    return cursor;

}
   /* public String[] retrieveData(String date,String username)
    {


        final String tableName = "incomes";
        String[] columns2 = { "*" };
        String selection2 = "user =? and date =?";
        String[] selectionArgs2 = { username,new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString() };
        String limit1 = "1";
        SQLiteDatabase db=this.getWritableDatabase();
        String limit = "1";
        Cursor cursor= db.query(tableName, columns2, selection2, selectionArgs2, null, null, null, limit);
        String[] data=new String[4];
        int i=0;

                data[0] = cursor.getString(cursor.getColumnIndex("Salary"));
                data[1] = cursor.getString(cursor.getColumnIndex("Investments"));
                data[2] = cursor.getString(cursor.getColumnIndex("Bank_Interests"));
                data[3] = cursor.getString(cursor.getColumnIndex("Others"));



        cursor.close();
        return data;

    }*/

public String findSum(String table, String col,String username)

{SQLiteDatabase db=this.getWritableDatabase();
    Cursor cursor = db.rawQuery("SELECT SUM(" + col + ") as Total FROM " + table+" where user = \""+username+"\"", null);
int total=0;
    if (cursor.moveToFirst()) {

         total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
}
return String.valueOf(total);

}


public String findExpense(String username)
{
    String Food=findSum("expenses","Food",username);
    String Electronics=findSum("expenses","Electronics",username);
    String Transport=findSum("expenses","Transport",username);
    String Gift=findSum("expenses","Gift",username);
    String Bills=findSum("expenses","Bills",username);
    String Home=findSum("expenses","Home",username);
    String Education=findSum("expenses","Education",username);
    String ChildCare=findSum("expenses","ChildCare",username);
    String Miscellaneous=findSum("expenses","Miscellaneous",username);
    int total=0;
    total=Integer.parseInt(Food)+
            Integer.parseInt(Electronics)+
            Integer.parseInt(Transport)+
            Integer.parseInt(Bills)+
            Integer.parseInt(Home)+
            Integer.parseInt(Education)+
            Integer.parseInt(ChildCare)+
            Integer.parseInt(Miscellaneous)+
            Integer.parseInt(Gift);
    String expense=String.valueOf(total);
    return  expense;
}

    public String findIncome(String username)
    {
        String Business=findSum("incomes","Business",username);
        String Salary=findSum("incomes","Salary",username);
        String Coupon=findSum("incomes","Coupon",username);
        String Grants=findSum("incomes","Grants",username);
        String Sale=findSum("incomes","Sale",username);

        int total=0;
        total=Integer.parseInt(Business)+
                Integer.parseInt(Salary)+
                Integer.parseInt(Coupon)+
                Integer.parseInt(Grants)+
                Integer.parseInt(Sale);

        String income=String.valueOf(total);
        return  income;
    }

}


  /*





            Cursor res=db.rawQuery("select Food from "+TABLE_NAME_1,null);
            res.moveToFirst();
            String prevSal=res.getString(0);
            int prev=Integer.parseInt(prevSal);
            inc=inc+prev;

        ContentValues contentValues1=new ContentValues();
        contentValues1.put(COL6,inc);

        String where = "user=?";
        String[] whereArgs = new String[] {username};

        db.update(TABLE_NAME_2,contentValues1, where, whereArgs);









            //db2.execSQL("UPDATE "+TABLE_NAME_2+" SET Salary = "+inc+" WHERE user = "+username);

        //db2.execSQL("UPDATE "+TABLE_NAME_1+" SET Food = "+exp+" WHERE name = "+username);

        /*if(result1==-1 || result2==-1)
            return false;
        else
            return true;*/