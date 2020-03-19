package com.sirmarcodevs.candymachinemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonPendentes;
    private Button buttonAprovados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        buttonPendentes = (Button) findViewById(R.id.buttonpendente);
        buttonAprovados = (Button) findViewById(R.id.buttonAprovados);

        setClickActions();
    }

    private void setClickActions() {
        buttonPendentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPendentes = new Intent(MainActivity.this,telaPedidosPendentes.class);
                startActivity(intentPendentes);
            }
        });

        buttonAprovados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAprovados = new Intent(MainActivity.this,telaPedidosAprovados.class);
                startActivity(intentAprovados);
            }
        });
    }


}
