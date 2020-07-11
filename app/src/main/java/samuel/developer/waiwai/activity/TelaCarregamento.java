package samuel.developer.waiwai.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;

import samuel.developer.waiwai.R;
import samuel.developer.waiwai.config.AplicationController;
import samuel.developer.waiwai.dao.BdDao;
import samuel.developer.waiwai.datamodel.DataModelPalavra;
import samuel.developer.waiwai.helper.RecuperarBitmap;
import samuel.developer.waiwai.model.Palavra;

public class TelaCarregamento extends AppCompatActivity {

    SharedPreferences sPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carregamento);
        getSupportActionBar().hide();
        sPreferences = getSharedPreferences("first Run", MODE_PRIVATE);

        if (AplicationController.verificarGooglePlayService(TelaCarregamento.this)) {
            apresentarTelaSplash();
        } else {
            Toast.makeText(getApplicationContext(), "Google Services não configurados", Toast.LENGTH_LONG).show();
        }
    }

    public void apresentarTelaSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abriTelaInicialLogin();

            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sPreferences.getBoolean("firstRun", true)) {
            sPreferences.edit().putBoolean("firstRun", false).apply();
            BdDao bd = new BdDao(getBaseContext());
            RecuperarBitmap recBit = new RecuperarBitmap();
            //Palavras Diversas

            //Objetos Imagens
            Bitmap mesa = recBit.getBitmap(this, R.drawable.quiz_mesa);
            Bitmap cadeira = recBit.getBitmap(this, R.drawable.quiz_cadeira);
            Bitmap computador = recBit.getBitmap(this, R.drawable.quiz_computador);
            Bitmap projetor = recBit.getBitmap(this, R.drawable.quiz_datashow);
            Bitmap quadro = recBit.getBitmap(this, R.drawable.quiz_quadro);
            Bitmap mouse = recBit.getBitmap(this, R.drawable.quiz_mouse);
            Bitmap caneta = recBit.getBitmap(this, R.drawable.quiz_caneta);
            Bitmap caderno = recBit.getBitmap(this, R.drawable.quiz_caderno);
            Bitmap porta = recBit.getBitmap(this, R.drawable.quiz_porta);
            Bitmap janela = recBit.getBitmap(this, R.drawable.quiz_janela);
            Bitmap cantina = recBit.getBitmap(this, R.drawable.quiz_cantina);
            Bitmap labInfo = recBit.getBitmap(this, R.drawable.quiz_laboratorio_informatica);
            Bitmap labBio = recBit.getBitmap(this, R.drawable.quiz_laboratorio_biologia);
            Bitmap carro = recBit.getBitmap(this, R.drawable.quiz_carro);
            Bitmap moto = recBit.getBitmap(this, R.drawable.quiz_moto);
            Bitmap campus = recBit.getBitmap(this, R.drawable.quiz_campus);

            //Palavras Objetos
            Palavra pMesa = new Palavra("Mesa", "", "" +
                    "Móvel onde podemos realizar nossas refeições, realizar reuniões etc.", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(mesa));
            Palavra pCadeira = new Palavra("Cadeira", "", "" +
                    "Móvel onde podemos nos sentar", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(cadeira));
            Palavra pComputador = new Palavra("Computador", "Karita Mewretopo", "" +
                    "Equipamento eletrônico onde podemos armazenar dados, realizar operações matemáticas etc.", DataModelPalavra.getCategoriaObjetos(),bd.convertCircleBitmapToByteArray(computador));
            Palavra pProjetor = new Palavra("Projetor", "", "" +
                    "Equipamento eletrônico que projeta imagens geradas de um computador", DataModelPalavra.getCategoriaObjetos(),bd.convertCircleBitmapToByteArray(projetor));
            Palavra pQuadro = new Palavra("Quadro", "", "" +
                    "É uma superfície reusável onde se escreve textos ou desenhos que são feitos com giz ou outros marcadores apagáveis",DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(quadro));
            Palavra pMouse = new Palavra("Mouse", "", "" +
                    "É um periférico de entrada que tem como função movimentar o cursor da tela do computador", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(mouse));
            Palavra pCaneta = new Palavra("Caneta", "", "" +
                    "É um instrumento utilizado para a escrita utilizando tinta", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(caneta));
            Palavra pCaderno = new Palavra("Caderno", "", "" +
                    "Artigo escolar que tem como função armazenar dados escritos a caneta ou lápis", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(caderno));
            Palavra pPorta = new Palavra("Porta", "", "" +
                    "Elemento de vedação arquitetônica que permite a passagem de pessoas de um ambiente para outro", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(porta));
            Palavra pJanela = new Palavra("Janela", "", "" +
                    "Elemento de vedação arquitetônica que possibilita a ventilação do ambiente interno de uma casa", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(janela));
            Palavra pCantina = new Palavra("Cantina", "", "" +
                    "Local onde se vende lanches", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(cantina));
            Palavra pLabInfo = new Palavra("Laboratório de Informática", "" +
                    "", "Local onde se realiza atividades que necessitem de computador", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(labInfo));
            Palavra pLabBio = new Palavra("Laboratório de Biologia", "", "" +
                    "Local onde se realiza atividades que necessitem manipular produtos químicos", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(labBio));
            Palavra pCarro = new Palavra("Carro", "", "" +
                    "Objeto de quatro rodas responsável pela locomoção", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(carro));
            Palavra pMoto = new Palavra("Moto", "", "" +
                    "Objeto de duas rodas responsável pela locomoção", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(moto));
            Palavra pCampus = new Palavra("Campus", "", "" +
                    "Lugar onde se promove ensino, pesquisa e extensão", DataModelPalavra.getCategoriaObjetos(), bd.convertCircleBitmapToByteArray(campus));

            //Inserir Palavras - Objetos
            bd.salvarPalavras(pMesa);
            bd.salvarPalavras(pCadeira);
            bd.salvarPalavras(pComputador);
            bd.salvarPalavras(pProjetor);
            bd.salvarPalavras(pQuadro);
            bd.salvarPalavras(pMouse);
            bd.salvarPalavras(pCaneta);
            bd.salvarPalavras(pCaderno);
            bd.salvarPalavras(pPorta);
            bd.salvarPalavras(pJanela);
            bd.salvarPalavras(pCantina);
            bd.salvarPalavras(pLabInfo);
            bd.salvarPalavras(pLabBio);
            bd.salvarPalavras(pCarro);
            bd.salvarPalavras(pMoto);
            bd.salvarPalavras(pCampus);

            //--------------------------------------------------------------------------------------

            //Pessoas Imagens
            Bitmap professor = recBit.getBitmap(this, R.drawable.quiz_professor);
            Bitmap bibliotecaria = recBit.getBitmap(this, R.drawable.quiz_bibliotecaria);
            Bitmap tecLab = recBit.getBitmap(this, R.drawable.quiz_tecnicolaboratorio);
            Bitmap porteiro = recBit.getBitmap(this, R.drawable.quiz_estudante);
            Bitmap auxServicoGerais = recBit.getBitmap(this, R.drawable.quiz_auxiliar_servicos_gerais);
            Bitmap veterinario = recBit.getBitmap(this, R.drawable.quiz_veterinario);
            Bitmap neymar = recBit.getBitmap(this, R.drawable.quiz_neymar);
            Bitmap fonseca = recBit.getBitmap(this, R.drawable.quiz_fonseca);

            //Palavras - Pessoas
            Palavra pProfessor = new Palavra("Professor/Docente", "Panatanmekne", "" +
                    "Responsável por disseminar o conhecimento e orientar no processo de produzir ciência", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(professor));
            Palavra pBibliotecaria = new Palavra("Bibliotecário (a)", "", "" +
                    "Responsável por gerenciar e organizar a biblioteca do campus", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(bibliotecaria));
            Palavra pTecLab = new Palavra("Tecnico (a) de Laboratório", "", "" +
                    "Responsável por auxiliar os docentes nas atividades de laboratório", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(tecLab));
            Palavra pPorteiro = new Palavra("Porteiro (a)", "", "" +
                    "Responsável por gerenciar a entrada de pessoas no campus", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(porteiro));
            Palavra pAluno = new Palavra("Aluno/Discente", "Ahcamhokaimu", "" +
                    "Peça fundamental da Universidade. Recebe conhecimento dos docentes e produz ciência", DataModelPalavra.getCategoriaPessoas(), null);
            Palavra pAuxiliarServicoGeral = new Palavra("Auxiliar de Serviços Gerais", "", "" +
                    "Responsável pela manutenção das áreas do campus", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(auxServicoGerais));
            Palavra pVeterinario = new Palavra("Veterinário", "", "" +
                    "Responsável pela manutenção e restauração da saúde dos animais", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(veterinario));
            Palavra pNeymar = new Palavra("Neymar", "", "" +
                    "Jogador de futebol", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(neymar));
            Palavra pFonseca = new Palavra("Delegado Fonseca", "", "" +
                    "Responsável pela segurança pública da cidade de Oriximiná", DataModelPalavra.getCategoriaPessoas(), bd.convertCircleBitmapToByteArray(fonseca));

            //Adicionar Palavras - Pessoas
            bd.salvarPalavras(pProfessor);
            bd.salvarPalavras(pBibliotecaria);
            bd.salvarPalavras(pTecLab);
            bd.salvarPalavras(pPorteiro);
            bd.salvarPalavras(pAluno);
            bd.salvarPalavras(pAuxiliarServicoGeral);
            bd.salvarPalavras(pVeterinario);
            bd.salvarPalavras(pNeymar);
            bd.salvarPalavras(pFonseca);

            //--------------------------------------------------------------------------------------

            //Imagens - Cursos
            Bitmap cienciasBiologicas = recBit.getBitmap(this, R.drawable.quiz_ciencias_biologicas);
            //Bitmap sistemaInformacao = recBit.getBitmap(this, R.drawable.quiz_)
            Bitmap inteligenciaArtificial = recBit.getBitmap(this, R.drawable.quiz_inteligencia_artificial);
            Bitmap botanica = recBit.getBitmap(this, R.drawable.quiz_botanica);
            Bitmap zoologia = recBit.getBitmap(this, R.drawable.quiz_zoologia);
            Bitmap ecologia = recBit.getBitmap(this, R.drawable.quiz_ecologia);
            Bitmap quelonios = recBit.getBitmap(this, R.drawable.quiz_quelonios);
            Bitmap manejoPesca = recBit.getBitmap(this, R.drawable.quiz_manejo_pesca);
            Bitmap embriologia = recBit.getBitmap(this, R.drawable.quiz_embriologia);
            Bitmap genetica = recBit.getBitmap(this, R.drawable.quiz_genetica);
            Bitmap geoProcessamento = recBit.getBitmap(this, R.drawable.quiz_geoprocessamento);
            Bitmap sistemOperacional = recBit.getBitmap(this, R.drawable.quiz_sistema_operacional);
            Bitmap redesDeComputadores = recBit.getBitmap(this, R.drawable.quiz_redes_computadores);
            Bitmap bancoDeDados = recBit.getBitmap(this, R.drawable.quiz_banco_de_dados);
            Bitmap algoritmo = recBit.getBitmap(this, R.drawable.quiz_algoritmo);
            Bitmap natureza = recBit.getBitmap(this, R.drawable.quiz_natureza);
            //Bitmap ecossistema = recBit.getBitmap(this, R.drawable)
            Bitmap software = recBit.getBitmap(this, R.drawable.quiz_software);
            Bitmap hardware = recBit.getBitmap(this, R.drawable.quiz_hardware);


            //Palavras - Cursos
            Palavra pCienciasBiologicas = new Palavra("Ciências Biológicas", "", "" +
                    "É a ciência que estuda a vida e os organismos vivos", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(cienciasBiologicas));
            Palavra pSistemaInformacao = new Palavra("Sistema de Informação", "", "" +
                    "Área que manipula dados e gera informação", DataModelPalavra.getCategoriaCursos(), null);
            Palavra pInteligenciasArtificial = new Palavra("Inteligência Artificial", "", "" +
                    "Área que o estuda e projeta agentes inteligentes", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(inteligenciaArtificial));
            Palavra pBotanica = new Palavra("Botânica", "", "" +
                    "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(botanica));
            Palavra pZoologia = new Palavra("Zoologia", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(zoologia));
            Palavra pEcologia = new Palavra("Ecologia", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(ecologia));
            Palavra pQuelonios = new Palavra("Quelônios", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(quelonios));
            Palavra pManejoPesca = new Palavra("Manejo da Pesca", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(manejoPesca));
            Palavra pEmbriologia = new Palavra("Embriologia", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(embriologia));
            Palavra pGenetica = new Palavra("Genética", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(genetica));
            Palavra pGeoProcessamento = new Palavra("Geoprocessamento", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(geoProcessamento));
            Palavra pSistemaOperacional = new Palavra("Sistema Operacional", "", "" +
                    "É o conjunto de programas cuja função é gerenciar os recursos do sistema", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(sistemOperacional));
            Palavra pRedesDeComputadores = new Palavra("Redes de Computadores", "", "" +
                    "É um conjunto de dois ou mais dispositivos eletrônicos de computação interligados por um sistema de comunicação digital", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(redesDeComputadores));
            Palavra pBancoDeDados = new Palavra("Banco de Dados", "", "" +
                    "São coleções organizadas de dados que se relacionam de forma a criar algum sentido (Informação) e dar mais eficiência durante uma pesquisa ou estudo cientifico", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(bancoDeDados));
            Palavra pAlgoritmo = new Palavra("Algoritmo", "", "" +
                    "É uma sequência finita de ações executáveis que visam obter uma solução para um determinado tipo de problema", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(algoritmo));
            Palavra pNatureza = new Palavra("Natureza", "", "", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(natureza));
            Palavra pEcossistema = new Palavra("Ecossistema", "", "", DataModelPalavra.getCategoriaCursos(), null);
            Palavra pSoftware = new Palavra("Software", "", "" +
                    "É uma sequência de instruções a serem seguidas e/ou executadas, na manipulação, redirecionamento ou modificação de um dado (informação) ou acontecimento", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(software));
            Palavra pHardware = new Palavra("Hardware", "", "" +
                    "Referente a equipamentos físicos de um produto eletrônico", DataModelPalavra.getCategoriaCursos(), bd.convertCircleBitmapToByteArray(hardware));

            //Adicionar Palavras - Cursos
            bd.salvarPalavras(pCienciasBiologicas);
            bd.salvarPalavras(pSistemaInformacao);
            bd.salvarPalavras(pInteligenciasArtificial);
            bd.salvarPalavras(pBotanica);
            bd.salvarPalavras(pZoologia);
            bd.salvarPalavras(pEcologia);
            bd.salvarPalavras(pQuelonios);
            bd.salvarPalavras(pManejoPesca);
            bd.salvarPalavras(pEmbriologia);
            bd.salvarPalavras(pGenetica);
            bd.salvarPalavras(pGeoProcessamento);
            bd.salvarPalavras(pSistemaOperacional);
            bd.salvarPalavras(pRedesDeComputadores);
            bd.salvarPalavras(pBancoDeDados);
            bd.salvarPalavras(pAlgoritmo);
            bd.salvarPalavras(pNatureza);
            bd.salvarPalavras(pEcossistema);
            bd.salvarPalavras(pSoftware);
            bd.salvarPalavras(pHardware);
            //--------------------------------------------------------------------------------------

            //Palavras Diversas
            Palavra pD1 = new Palavra("Olá", "Ahei waha", "", DataModelPalavra.getCategoriaDiversos() , null);
            Palavra pD2 = new Palavra("Bom dia", "Titpaka", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD3 = new Palavra("Boa tarde", "Titkokonenta", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD4 = new Palavra("Boa noite", "Titkokmam", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD5 = new Palavra("Até logo", "Erawa ham amne Hara", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD6 = new Palavra("Tchau", "Amne Hara", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD8 = new Palavra("Ufopa", "Kehcanho Kacho yosotê", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD9 = new Palavra("Biologia", "TanHamyan Komo poko kehcamho Kacho", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD12 = new Palavra("Brasil", "Ahnorono Roowon", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD13 = new Palavra("Campeão", "Kana kane ro", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD14 = new Palavra("Pesquisa", "Ahceno Komo yepotopo isce Vehtopo", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD15 = new Palavra("Sociedade", "Tooto Komo", "Conjunto de pessoas", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD16 = new Palavra("Oriximiná", "Euto Oriximina", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD17 = new Palavra("Santarém", "Euto Santaren", "", DataModelPalavra.getCategoriaDiversos(), null);
            Palavra pD18 = new Palavra("Reitor", "Kehcanmhokocho yenêne", "", DataModelPalavra.getCategoriaDiversos(), null);

            //Inserir palavras Diversas
            bd.salvarPalavras(pD1);
            bd.salvarPalavras(pD1);
            bd.salvarPalavras(pD2);
            bd.salvarPalavras(pD3);
            bd.salvarPalavras(pD4);
            bd.salvarPalavras(pD5);
            bd.salvarPalavras(pD6);
            bd.salvarPalavras(pD8);
            bd.salvarPalavras(pD9);
            bd.salvarPalavras(pD12);
            bd.salvarPalavras(pD13);
            bd.salvarPalavras(pD14);
            bd.salvarPalavras(pD15);
            bd.salvarPalavras(pD16);
            bd.salvarPalavras(pD17);
            bd.salvarPalavras(pD18);
        } else {

        }
    }

    public void abriTelaInicialLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
