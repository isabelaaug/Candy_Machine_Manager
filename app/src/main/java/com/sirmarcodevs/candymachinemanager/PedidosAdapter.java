package com.sirmarcodevs.candymachinemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {



    private Context mContext;
    private ArrayList<Pedido> pedidos;
    private String tipo;
    private int numPedidos = 0;

    public PedidosAdapter(Context mContext, ArrayList<Pedido> pedidos, String tipo) {
        this.mContext = mContext;
        this.pedidos = pedidos;
        this.tipo = tipo;
    }

    @Override
    public PedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_pedido, parent, false);
        return new PedidosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidosAdapter.ViewHolder holder, final int position) {
        final Pedido pedido = pedidos.get(position);
        getNumeroPedidos();

        holder.numPedido.setText("#"+pedido.getNumeropedido());
        holder.descPedido.setText("- "+pedido.getProduto()+"\n"+"- "+pedido.getQuantidade()+"\n"+"- "+pedido.getValor()+"\n"+"- "+pedido.getNome()+"\n"+"- "+pedido.getCpf()+"\n"+"- "+pedido.getEmail()+"\n"+"- "+pedido.getTelefone()+"\n"+"- "+pedido.getEndereco()+"\n"+"- "+pedido.getComplemento()+"\n"+"- "+pedido.getCep());

        if (tipo.equals("Aprovados")){
            holder.imgAccept.setEnabled(false);
            holder.imgAccept.setClickable(false);
            holder.imgAccept.setVisibility(View.INVISIBLE);

            holder.imgDecline.setEnabled(false);
            holder.imgDecline.setClickable(false);
            holder.imgDecline.setVisibility(View.INVISIBLE);
        } else {

            holder.imgAccept.setClickable(true);
            holder.imgDecline.setClickable(true);
            holder.imgDecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recusarPedido(position);
                }
            });

            holder.imgAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    aceitarPedido(position);
                           }
            });
        }

    }

    private void aceitarPedido(int position) {
        Pedido pedido = pedidos.get(position);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refDatabase = database.getReference("pedidos-aceitos").child(pedido.numero_pedido);
        refDatabase.setValue(pedido);

        String pedidoArduino = (pedido.getNumeropedido()+"&"+pedido.getProduto()+"&"+pedido.getQuantidade());
        DatabaseReference refArduino = database.getReference("pedidos-aceitos-arduino").child(String.valueOf(numPedidos));
        refArduino.setValue(pedidoArduino);

        numPedidos = numPedidos + 1;
        DatabaseReference refContador = database.getReference("contadorArduino");
        refContador.setValue(numPedidos);

        recusarPedido(position);
    }

    private void getNumeroPedidos() {
        if(numPedidos == 0) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference contadorRef = database.getReference("contadorArduino");
            contadorRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String contador = dataSnapshot.getValue().toString();
                    numPedidos = Integer.parseInt(contador);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void recusarPedido(int position) {
        Pedido pedido = pedidos.get(position);
        String rem = pedido.numero_pedido;
        DatabaseReference dados = FirebaseDatabase.getInstance().getReference();
        dados.child("pedidos-pendentes").child(rem).removeValue();
        pedidos.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numPedido,descPedido;
        ImageView imgAccept, imgDecline;

        public ViewHolder(View itemView) {
            super(itemView);
            numPedido = itemView.findViewById(R.id.num_pedido);
            imgAccept = itemView.findViewById(R.id.pedido_accept);
            imgDecline = itemView.findViewById(R.id.pedido_decline);
            descPedido = itemView.findViewById(R.id.desc_pedido);
        }
    }

}