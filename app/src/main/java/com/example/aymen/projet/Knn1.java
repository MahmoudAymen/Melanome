package com.example.aymen.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aymen on 30/04/2017.
 */
public class Knn1 {
    private SQLiteDatabase maBaseDonnees;
    private DataBaseOpenHelper openHelper;

    public Knn1(Context context) {
        openHelper = new DataBaseOpenHelper(context);

    }

    public SQLiteDatabase open() {
        maBaseDonnees = openHelper.getWritableDatabase();
        return maBaseDonnees;
    }

    public void close() {
        maBaseDonnees.close();
    }

    //Ajouter des informations au Base de donnnes
    public long insert(information inf) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Image",   inf.getImage());
        contentValues.put("valeur1", inf.getVal1());
        contentValues.put("valeur2", inf.getVal2());
        contentValues.put("valeur3", inf.getVal3());
        contentValues.put("valeur4", inf.getVal4());
        contentValues.put("valeur5", inf.getVal5());
        contentValues.put("valeur6", inf.getVal6());
        contentValues.put("valeur7", inf.getVal7());
        contentValues.put("Result", inf.getResult());
        return maBaseDonnees.insert("table_Knn", null, contentValues);

    }

    public double getval1(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur1"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval2(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur2"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval3(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur3"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval4(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur4"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval5(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur5"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval6(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur6"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

    public double getval7(int id) {
        double val = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"valeur7"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToNext())
            val = c.getDouble(id);
        return val;
    }

  /*  public double[] GetValeur(int id) {
        double[] tab = new double[]{getval1(id), getval2(id), getval3(id), getval4(id), getval5(id), getval6(id), getval7(id)};
        return tab;

    }
    public double[] GetvalR()
    {
        information information=new information();
        return (new double[]{information.getVal1(),information.getVal2(),information.getVal3(),information.getVal4(),information.getVal5(),information.getVal6(),information.getVal7()});

    }*/
    public Bitmap getImage(int id){
        maBaseDonnees = openHelper.getReadableDatabase();
        String qu = "select Image  from  table_Knn where id="+id;
        Cursor cur = maBaseDonnees.rawQuery(qu, null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(cur.getColumnIndex("Image"));
            ByteArrayInputStream inputStream=new ByteArrayInputStream(imgByte);
            cur.close();
            return BitmapFactory.decodeStream(inputStream);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null ;
    }
    public int result(int id) {
        int res = 0;
        maBaseDonnees = openHelper.getReadableDatabase();
        String qu = "select Result  from  table_Knn where id="+id;
        Cursor cur = maBaseDonnees.rawQuery(qu, null);
        if (cur.moveToFirst()){
            res = cur.getInt(cur.getColumnIndex("Result"));
            cur.close();
            }
        return res;
    }

    public void vide()
    {
        try {
            maBaseDonnees.execSQL("drop table table_Knn");
            openHelper.onCreate(maBaseDonnees);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public information getInformation(int id) {
        Cursor c = maBaseDonnees.query("table_Knn", new String[]{"id", "Image", "valeur1", "valeur2", "valeur3", "valeur4", "valeur5", "valeur6", "valeur7", "Result"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        information information = new information();
        information.setVal1(c.getInt(c.getColumnIndex("id")));
        information.setImage(c.getBlob(c.getColumnIndex("Image")));
        information.setVal1(c.getDouble(c.getColumnIndex("valeur1")));
        information.setVal2(c.getDouble(c.getColumnIndex("valeur2")));
        information.setVal3(c.getDouble(c.getColumnIndex("valeur3")));
        information.setVal4(c.getDouble(c.getColumnIndex("valeur4")));
        information.setVal5(c.getDouble(c.getColumnIndex("valeur5")));
        information.setVal6(c.getDouble(c.getColumnIndex("valeur6")));
        information.setVal7(c.getDouble(c.getColumnIndex("valeur7")));
        information.setResult(c.getInt(c.getColumnIndex("Result")));
        c.close();
        return information;
    }

    public List<information> getAllInformation() {
        List<information> informationList = new ArrayList<information>();
        String selectQuery = "select  * from table_Knn";
        Cursor c = maBaseDonnees.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                information information = new information();
                information.setId(c.getInt(c.getColumnIndex("id")));
                information.setImage(c.getBlob(c.getColumnIndex("Image")));
                information.setVal1(c.getDouble(c.getColumnIndex("valeur1")));
                information.setVal2(c.getDouble(c.getColumnIndex("valeur2")));
                information.setVal3(c.getDouble(c.getColumnIndex("valeur3")));
                information.setVal4(c.getDouble(c.getColumnIndex("valeur4")));
                information.setVal5(c.getDouble(c.getColumnIndex("valeur5")));
                information.setVal6(c.getDouble(c.getColumnIndex("valeur6")));
                information.setVal7(c.getDouble(c.getColumnIndex("valeur7")));
                information.setResult(c.getInt(c.getColumnIndex("Result")));
                informationList.add(information);


            } while (c.moveToNext());
        }
        c.close();
        return informationList;

    }
    public int getcount()
    {
        int count=0;
        String countQuery="select * from  table_Knn";
        maBaseDonnees=openHelper.getReadableDatabase();
        Cursor cursor = maBaseDonnees.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();
        return  count;
    }
    public void delete(int val)
    {
        maBaseDonnees.delete("table_Knn", "id=?", new String[]{String.valueOf(val)});
    }
    public void update(int id)
    {
        ContentValues cv=new ContentValues();
        cv.put("Result",0);
        maBaseDonnees.update("table_Knn", cv, "id=?", new String[]{String.valueOf(id)});

    }


}
