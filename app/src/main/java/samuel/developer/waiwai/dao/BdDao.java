package samuel.developer.waiwai.dao;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import samuel.developer.waiwai.datamodel.DataModelPalavra;
import samuel.developer.waiwai.model.Palavra;

public class BdDao extends DataSource {

    ContentValues dados;
    public BdDao(Context context) {
        super(context);
    }

    public Bitmap converteByteArrayToBitmap(byte[] imagem){
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        return bitmap;
    }

    public byte[] convertCircleBitmapToByteArray(Bitmap imagem){
        // BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        //Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imagemBytes[] = stream.toByteArray();
        return imagemBytes;

    }

    public boolean salvarPalavras(Palavra obj){
        boolean sucesso = true;
        dados = new ContentValues();
        dados.put(DataModelPalavra.getPalavra_portugues(), obj.getPalavraportugues());
        dados.put(DataModelPalavra.getPalavra_waiwai(), obj.getPalavrawaiwai());
        dados.put(DataModelPalavra.getPergunta(), obj.getPergunta());
        dados.put(DataModelPalavra.getCategoria(), obj.getCategoria());
        dados.put(DataModelPalavra.getImagempalavra(), obj.getImagem());
        sucesso = insert(DataModelPalavra.getTabelaPalavras(), dados);
        return sucesso;
    }
}
