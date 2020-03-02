package com.marlonheraclito.emaxepdm.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.marlonheraclito.emaxepdm.modelo.Aluno;
import com.marlonheraclito.emaxepdm.utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DaoAluno implements DaoHelper<Aluno> {

    private SQLiteDatabase db;
    private DataBaseHelper helper;


    public DaoAluno(Context context) {
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public long insert(Aluno aluno) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", aluno.getNome());
        contentValues.put("NUMERO", aluno.getNumero());
        contentValues.put("PC", aluno.getPc());
        contentValues.put("PRESENCIA", aluno.getPresencia());

        long res = db.insert(helper.TABLE_NAME_ALUNO, null, contentValues);
        db.close();
        return res;
    }

    @Override
    public List<Aluno> select() {
        List<Aluno> listAluno = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + helper.TABLE_NAME_ALUNO + " WHERE PRESENCIA = 'P'", null);

        if(res.getCount()!= 0) {
            while (res.moveToNext()) {
                Aluno aluno = new Aluno(res.getString(0), res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4));
                listAluno.add(aluno);
            }
            res.close();
        }

        return listAluno;
    }

    public List<Aluno> selectFaltou() {
        List<Aluno> listAluno = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + helper.TABLE_NAME_ALUNO + " WHERE PRESENCIA = 'F'", null);

        if(res.getCount()!= 0) {
            while (res.moveToNext()) {
                Aluno aluno = new Aluno(res.getString(0), res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4));
                listAluno.add(aluno);
            }
            res.close();
        }

        return listAluno;
    }

    public List<Aluno> selectDesistiu() {
        List<Aluno> listAluno = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + helper.TABLE_NAME_ALUNO + " WHERE PRESENCIA = 'D'", null);

        if(res.getCount()!= 0) {
            while (res.moveToNext()) {
                Aluno aluno = new Aluno(res.getString(0), res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4));
                listAluno.add(aluno);
            }
            res.close();
        }

        return listAluno;
    }

    public Boolean update(Aluno aluno) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", aluno.getId());
        contentValues.put("NOME", aluno.getNome());
        contentValues.put("NUMERO", aluno.getNumero());
        contentValues.put("PC", aluno.getPc());
        contentValues.put("PRESENCIA", aluno.getPresencia());

        try {
            db.update(helper.TABLE_NAME_ALUNO, contentValues, "ID = ?", new String[] {aluno.getId()});
        } catch (Exception e) {
            Log.i("UP", String.valueOf(e));
        }

        return true;
    }


}
