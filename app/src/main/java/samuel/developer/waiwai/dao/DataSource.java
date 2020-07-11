package samuel.developer.waiwai.dao;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

import samuel.developer.waiwai.datamodel.DataModelPalavra;

public class DataSource extends SQLiteOpenHelper {

    public static final String DB_NAME = "banco_local.db";
    private Context mContext;
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
    }
    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public DataSource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    private String palavarasTabela = DataModelPalavra.criarTabelaPalavra();


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("PRAGMA foreign_keys=ON;");
            db.execSQL(palavarasTabela);
            Log.d("BD", "Sucesso ao criar BD");
        }catch ( Exception e ){
            Log.e("BD", "DB--> ERRO: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String table, ContentValues dados){

        boolean sucesso = true;

        try {
            sucesso = db.insert(table, null, dados) > 0;
        }catch (Exception e){

        }
        return sucesso;
    }

    public String getPalavraPortuguesById(String tabela, int id){
        SQLiteDatabase db = getReadableDatabase();
        String palavra = "";
        Cursor cursor = db.rawQuery("SELECT " + DataModelPalavra.getPalavra_portugues() + " FROM " + tabela + " WHERE id = " + id,null);
        if (cursor.moveToFirst()){
            do{
                palavra = cursor.getString(cursor.getColumnIndex(DataModelPalavra.getPalavra_portugues()));
                return palavra;

            }while(cursor.moveToNext());
        }

        return palavra;
    }
    public String getPalavraWaiById(String tabela, int id){
        SQLiteDatabase db = getReadableDatabase();
        String palavra = "";
        Cursor cursor = db.rawQuery("SELECT " + DataModelPalavra.getPalavra_waiwai() + " FROM " + tabela + " WHERE id = " + id, null);
        if (cursor.moveToFirst()){
            do{
                palavra = cursor.getString(cursor.getColumnIndex(DataModelPalavra.getPalavra_waiwai()));
                return palavra;
            }while(cursor.moveToNext());
        }
        return palavra;
    }

    public String getDescricaoPalavraById(String tabela, int id){
        SQLiteDatabase db = getReadableDatabase();
        String descricao = "";
        Cursor cursor = db.rawQuery("SELECT " + DataModelPalavra.getPergunta() + " FROM " + tabela + " WHERE id = " + id, null);
        if (cursor.moveToFirst()){
            do{
                descricao = cursor.getString(cursor.getColumnIndex(DataModelPalavra.getPergunta()));
                return descricao;
            }while(cursor.moveToNext());
        }
        return descricao;
    }
    public byte[] getImagemPalavra (String tabela, int id){
        SQLiteDatabase db = getReadableDatabase();
        byte[] palavra = null;
        Cursor cursor = db.rawQuery("SELECT " + DataModelPalavra.getImagempalavra() + " FROM " + tabela + " WHERE id = " + id, null);
        if (cursor.moveToFirst()){
            do{
                palavra = cursor.getBlob(cursor.getColumnIndex(DataModelPalavra.getImagempalavra()));
                return palavra;
            }while(cursor.moveToNext());
        }
        return palavra;
    }
    public ArrayList<String> getPalavras(String tabela){
        SQLiteDatabase db = getReadableDatabase();
        String palavra;
        Cursor cursor = db.rawQuery("SELECT "+ DataModelPalavra.getPalavra_portugues() + " FROM " + tabela, new String[]{});
        ArrayList<String> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                palavra = cursor.getString(cursor.getColumnIndex(DataModelPalavra.getPalavra_portugues()));
                lista.add(palavra);
                if(cursor.isLast()){
                    return lista;
                }
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<String> getPalavrasByFase(String tabela, String fase){
        SQLiteDatabase db = getReadableDatabase();
        String palavra;
        Cursor cursor = db.rawQuery("SELECT "+ DataModelPalavra.getCategoria() + " FROM " + tabela + " WHERE " + DataModelPalavra.getCategoria() + " = " + fase, new String[]{});
        ArrayList<String> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                palavra = cursor.getString(cursor.getColumnIndex(DataModelPalavra.getPalavra_portugues()));
                lista.add(palavra);
                if(cursor.isLast()){
                    return lista;
                }
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<Integer> getIdByFase(String tabela, String fase) {
        SQLiteDatabase db = getReadableDatabase();
        int palavra;
        Cursor cursor = db.rawQuery("SELECT id FROM " + tabela + " WHERE " + DataModelPalavra.getCategoria() + " = " + fase, new String[]{});
        ArrayList<Integer> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                palavra = cursor.getInt(cursor.getColumnIndex(DataModelPalavra.getPalavra_portugues()));
                lista.add(palavra);
                if(cursor.isLast()){
                    return lista;
                }
            }while(cursor.moveToNext());
        }
        return lista;
    }
}
