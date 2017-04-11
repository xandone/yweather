package app.xandone.com.yweather.data.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import app.xandone.com.yweather.data.entity.Area;


public class DBHelper {
    private SQLiteDatabase db;
    private DBManager dbm;
    private static DBHelper dbHelper;

    private DBHelper(Context context) {
        super();
        dbm = new DBManager(context);
    }

    public static DBHelper newInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    public ArrayList<Area> getCity(String pcode) {
        dbm.openDatabase();
        db = dbm.getDatabase();
        ArrayList<Area> list = new ArrayList<>();

        try {
            String sql = "select * from city where pcode='" + pcode + "'";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isLast()) {
                String code = cursor.getString(cursor.getColumnIndex("code"));
                byte bytes[] = cursor.getBlob(2);
                String name = new String(bytes, "gbk");
                Area area = new Area();
                area.setName(name);
                area.setCode(code);
                area.setPcode(pcode);
                list.add(area);
                cursor.moveToNext();
            }
            String code = cursor.getString(cursor.getColumnIndex("code"));
            byte bytes[] = cursor.getBlob(2);
            String name = new String(bytes, "gbk");
            Area area = new Area();
            area.setName(name);
            area.setCode(code);
            area.setPcode(pcode);
            list.add(area);

        } catch (Exception e) {
            return null;
        }
        dbm.closeDatabase();
        db.close();

        return list;

    }

    public ArrayList<Area> getProvince() {
        dbm.openDatabase();
        db = dbm.getDatabase();
        ArrayList<Area> list = new ArrayList<>();

        try {
            String sql = "select * from province";
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isLast()) {
                String code = cursor.getString(cursor.getColumnIndex("code"));
                byte bytes[] = cursor.getBlob(2);
                String name = new String(bytes, "gbk");
                Area area = new Area();
                area.setName(name);
                area.setCode(code);
                list.add(area);
                cursor.moveToNext();
            }
            String code = cursor.getString(cursor.getColumnIndex("code"));
            byte bytes[] = cursor.getBlob(2);
            String name = new String(bytes, "gbk");
            Area area = new Area();
            area.setName(name);
            area.setCode(code);
            list.add(area);

        } catch (Exception e) {
            return null;
        }
        dbm.closeDatabase();
        db.close();
        return list;
    }
}
