package com.marlonheraclito.emaxepdm.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.marlonheraclito.emaxepdm.modelo.User;
import com.marlonheraclito.emaxepdm.utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DaoUser implements DaoHelper<User>{

    private SQLiteDatabase db;
    private DataBaseHelper helper;


    public DaoUser(Context context) {
            helper = new DataBaseHelper(context);
        try {
            db = helper.getWritableDatabase();
        } catch (Exception e) {
            Log.i("ERRO", String.valueOf(e));
        }
    }

    @Override
    public long insert(User user) throws Exception {
        ContentValues values = new ContentValues();
        values.put("EMAIL", user.getEmail());
        values.put("PASSWORD", user.getPassword());

        long res = db.insert(helper.TABLE_NAME, null, values);
        db.close();

        return res;
    }

    @Override
    public List<User> select() {
        List<User> listaUser = new ArrayList<>();

        db = helper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + helper.TABLE_NAME, null);
        if(res.getCount() != 0) {
            while (res.moveToNext()) {
                User user = new User(res.getString(0), res.getString(1), res.getString(2));
                listaUser.add(user);
            }
        } res.close();
        return listaUser;
    }

    @Override
    public Boolean update(User user) throws Exception {
        return null;
    }
}


