package samuel.developer.waiwai.datamodel;

public class DataModelPalavra {

    private final static String TABELA_PALAVRAS = "palavras";
    private final static String id = "id";
    private final static String palavra_portugues = "palavraportugues";
    private final static String palavra_waiwai = "palavrawaiwai";
    private final static String imagempalavra = "imagem";
    private final static String pergunta = "pergunta";
    private final static String categoria = "categoria";
    private final static String CATEGORIA_OBJETOS = "objetos";
    private final static String CATEGORIA_PESSOAS = "pessoas";
    private final static String CATEGORIA_CURSOS = "cursos";
    private final static String CATEGORIA_DIVERSOS = "diversos";

    private static String queryCriarTabelaPalavras = "";

    public static String criarTabelaPalavra(){
        queryCriarTabelaPalavras += " CREATE TABLE IF NOT EXISTS " + TABELA_PALAVRAS;
        queryCriarTabelaPalavras += " (";
        queryCriarTabelaPalavras += id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabelaPalavras += palavra_portugues + " TEXT, ";
        queryCriarTabelaPalavras += palavra_waiwai + " TEXT, ";
        queryCriarTabelaPalavras += pergunta + " TEXT, ";
        queryCriarTabelaPalavras += categoria + " TEXT, ";
        queryCriarTabelaPalavras += imagempalavra + " BLOB";
        queryCriarTabelaPalavras += ")";
        return queryCriarTabelaPalavras;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static String getCategoriaObjetos() {
        return CATEGORIA_OBJETOS;
    }

    public static String getCategoriaPessoas() {
        return CATEGORIA_PESSOAS;
    }

    public static String getCategoriaDiversos() {
        return CATEGORIA_DIVERSOS;
    }

    public static String getCategoriaCursos() {
        return CATEGORIA_CURSOS;
    }

    public static String getTabelaPalavras() {
        return TABELA_PALAVRAS;
    }

    public static String getId() {
        return id;
    }

    public static String getPalavra_portugues() {
        return palavra_portugues;
    }

    public static String getPalavra_waiwai() {
        return palavra_waiwai;
    }

    public static String getImagempalavra() {
        return imagempalavra;
    }

    public static String getQueryCriarTabelaPalavras() {
        return queryCriarTabelaPalavras;
    }

    public static String getPergunta() {
        return pergunta;
    }

    public static void setQueryCriarTabelaPalavras(String queryCriarTabelaPalavras) {
        DataModelPalavra.queryCriarTabelaPalavras = queryCriarTabelaPalavras;
    }
}
