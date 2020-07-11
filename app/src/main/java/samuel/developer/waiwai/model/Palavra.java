package samuel.developer.waiwai.model;

public class Palavra {

    private int id;
    private String palavraportugues;
    private String palavrawaiwai;
    private String pergunta;
    private String categoria;
    private byte[] imagem;
    private byte[] audio;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Palavra(String palavraportugues, String palavrawaiwai, String pergunta, String categoria, byte[] imagem) {
        this.palavraportugues = palavraportugues;
        this.palavrawaiwai = palavrawaiwai;
        this.pergunta = pergunta;
        this.categoria = categoria;
        this.imagem = imagem;
    }


    public Palavra(String palavraportugues, String palavrawaiwai) {
        this.palavraportugues = palavraportugues;
        this.palavrawaiwai = palavrawaiwai;
    }

    public Palavra(String palavraportugues, String palavrawaiwai, String pergunta) {
        this.palavraportugues = palavraportugues;
        this.palavrawaiwai = palavrawaiwai;
        this.pergunta = pergunta;
    }

    public Palavra(String palavraportugues, String palavrawaiwai, String pergunta, byte[] imagem) {
        this.palavraportugues = palavraportugues;
        this.palavrawaiwai = palavrawaiwai;
        this.pergunta = pergunta;
        this.imagem = imagem;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String descricao) {
        this.pergunta = descricao;
    }

    public String getPalavraportugues() {
        return palavraportugues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPalavraportugues(String palavraportugues) {
        this.palavraportugues = palavraportugues;
    }

    public String getPalavrawaiwai() {
        return palavrawaiwai;
    }

    public void setPalavrawaiwai(String palavrawaiwai) {
        this.palavrawaiwai = palavrawaiwai;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
}
