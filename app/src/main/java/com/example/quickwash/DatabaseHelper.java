package com.example.quickwash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public  static final  String DATABASE_NAME = "accounts.db";
    public  static final String TABLE_NAME= "user_table";
    public  static  final String NAME="NAME";
    public  static  final String ADDRESS="ADDRESS";
    public  static  final String EMAIL="EMAIL";
    public  static  final String MOBILE="MOBILE";
    public  static  final String PASSWORD="PASSWORD";
    private static final String[] COLUMNS = { NAME, ADDRESS, EMAIL,MOBILE,PASSWORD };








    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
      db.execSQL("CREATE TABLE "+TABLE_NAME+" (NAME TEXT,ADDRESS TEXT,EMAIL TEXT,MOBILE TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

     public  boolean insertData(String name,String address,String email,String mobile,String password)
     {
         SQLiteDatabase db=this.getWritableDatabase();
         ContentValues contentValues= new ContentValues();
         contentValues.put(NAME,name);;
         contentValues.put(ADDRESS,address);
         contentValues.put(EMAIL,email);
         contentValues.put(MOBILE,mobile);
         contentValues.put(PASSWORD,password);

         long result=db.insert(TABLE_NAME,null,contentValues);
         db.close();


         if(result==-1)
         {
             return  false;
         }
         else
         {
             return  true;
         }
     }

     public  Cursor getAccountsData()
     {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= (Cursor) db.rawQuery("Select *from "+TABLE_NAME,null);
        return res;
     }


     public Cursor getLoggedInUserData(String sEmail)
     {
         SQLiteDatabase database=this.getReadableDatabase();
         String[] projcetions= {NAME,ADDRESS,EMAIL,MOBILE,PASSWORD};
         String selection = EMAIL+" LIKE ?";
         String[] selection_args= {sEmail};

         Cursor cursor=database.query(TABLE_NAME,projcetions,selection,selection_args,null,null,null);
         return  cursor;
     }

}
