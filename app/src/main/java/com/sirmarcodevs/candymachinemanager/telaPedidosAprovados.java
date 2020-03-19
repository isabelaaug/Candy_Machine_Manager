package com.sirmarcodevs.candymachinemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class telaPedidosAprovados extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PedidosAdapter pedidosAdapter;
    private DatabaseReference dados;
    private ArrayList<Pedido> listPedidos = new ArrayList<>();
    private Pedido pedido;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedidos_pendentes);

        recyclerView = findViewById(R.id.lista);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        context = this;
        buscarPedidos();
    }

    private void buscarPedidos() {
        dados = FirebaseDatabase.getInstance().getReference();
        Query query = FirebaseDatabase.getInstance().getReference().child("pedidos-aceitos");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPedidos = new ArrayList<>();
                listPedidos.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    pedido = dataSnapshot1.getValue(Pedido.class);
                    listPedidos.add(pedido);
                    pedidosAdapter = new PedidosAdapter(context, listPedidos,"Aprovados");
                    recyclerView.setAdapter(pedidosAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}