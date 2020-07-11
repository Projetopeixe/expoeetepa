package samuel.developer.waiwai.activity.fases;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import samuel.developer.waiwai.R;
import samuel.developer.waiwai.dao.DataSource;
import samuel.developer.waiwai.datamodel.DataModelPalavra;

public class Fase1 extends AppCompatActivity {

    private TextView opcao1, opcao2, opcao3, opcao4;
    private LinearLayout altern1, altern2, altern3, altern4;
    private boolean altern1Clicado, altern2Clicado, altern3Clicado, altern4Clicada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fases);
        carregarComponentes();
        getSupportActionBar().hide();
        altern1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altern1Clicado = true;
                altern2Clicado = false;
                altern3Clicado = false;
                altern4Clicada = false;
                verificarClicado();
            }
        });

        altern2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altern2Clicado = true;
                altern1Clicado = false;
                altern3Clicado = false;
                altern4Clicada = false;
                verificarClicado();
            }
        });
        altern3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altern3Clicado = true;
                altern2Clicado = false;
                altern1Clicado = false;
                altern4Clicada = false;
                verificarClicado();
            }
        });
        altern4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altern4Clicada = true;
                altern2Clicado = false;
                altern3Clicado = false;
                altern1Clicado = false;
                verificarClicado();
            }
        });
    }
    public void verificarClicado(){
        if (altern1Clicado){
            altern1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            altern2.setBackgroundColor(getResources().getColor(R.color.branco));
            altern3.setBackgroundColor(getResources().getColor(R.color.branco));
            altern4.setBackgroundColor(getResources().getColor(R.color.branco));
            opcao1.setTextColor(getResources().getColor(R.color.branco));
            opcao2.setTextColor(getResources().getColor(R.color.preto));
            opcao3.setTextColor(getResources().getColor(R.color.preto));
            opcao4.setTextColor(getResources().getColor(R.color.preto));
        }else if(altern2Clicado){
            altern2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            altern1.setBackgroundColor(getResources().getColor(R.color.branco));
            altern3.setBackgroundColor(getResources().getColor(R.color.branco));
            altern4.setBackgroundColor(getResources().getColor(R.color.branco));
            opcao2.setTextColor(getResources().getColor(R.color.branco));
            opcao1.setTextColor(getResources().getColor(R.color.preto));
            opcao3.setTextColor(getResources().getColor(R.color.preto));
            opcao4.setTextColor(getResources().getColor(R.color.preto));
        }else if(altern3Clicado){
            altern3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            altern1.setBackgroundColor(getResources().getColor(R.color.branco));
            altern2.setBackgroundColor(getResources().getColor(R.color.branco));
            altern4.setBackgroundColor(getResources().getColor(R.color.branco));
            opcao3.setTextColor(getResources().getColor(R.color.branco));
            opcao1.setTextColor(getResources().getColor(R.color.preto));
            opcao2.setTextColor(getResources().getColor(R.color.preto));
            opcao4.setTextColor(getResources().getColor(R.color.preto));
        }else if(altern4Clicada){
            altern4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            altern1.setBackgroundColor(getResources().getColor(R.color.branco));
            altern2.setBackgroundColor(getResources().getColor(R.color.branco));
            altern3.setBackgroundColor(getResources().getColor(R.color.branco));
            opcao4.setTextColor(getResources().getColor(R.color.branco));
            opcao1.setTextColor(getResources().getColor(R.color.preto));
            opcao2.setTextColor(getResources().getColor(R.color.preto));
            opcao3.setTextColor(getResources().getColor(R.color.preto));
        }
    }
    private void carregarComponentes(){
        //Seleção
        altern1 = findViewById(R.id.alternativaA);
        altern2 = findViewById(R.id.alternativaB);
        altern3 = findViewById(R.id.alternativaC);
        altern4 = findViewById(R.id.alternativaD);
        opcao1 = findViewById(R.id.opcao1);
        opcao2 = findViewById(R.id.opcao2);
        opcao3 = findViewById(R.id.opcao3);
        opcao4 = findViewById(R.id.opcao4);
    }



}