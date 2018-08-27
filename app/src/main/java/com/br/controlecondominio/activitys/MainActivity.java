package com.br.controlecondominio.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.br.controlecondominio.R;
import com.br.controlecondominio.modelo.Despesa;

public class MainActivity extends AppCompatActivity {

    private ImageView img_prestador, img_morador, img_receita, img_despesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_prestador = findViewById(R.id.img_prestador);
        img_morador = findViewById(R.id.img_morador);
        img_receita = findViewById(R.id.img_receita);
        img_despesa = findViewById(R.id.img_despesa);

        img_prestador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaPrestadorActivity.class);
                startActivity(intent);
            }
        });
        img_morador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaMoradorActivity.class);
                startActivity(intent);
            }
        });
        img_receita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaReceitaActivity.class);
                startActivity(intent);
            }
        });
        img_despesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaDespesaActivity.class);
                startActivity(intent);
            }
        });


    }
}
