package com.example.bd_pri.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_pri.Models.Elements.ElementHistory;
import com.example.bd_pri.Models.Elements.ElementRecycler;
import com.example.bd_pri.R;

import java.util.ArrayList;

public class AdapterHistory  extends RecyclerView.Adapter<AdapterHistory.ViewHolderDatos> implements View.OnClickListener {


    //ArrayList<String> ListDatos;
    ArrayList<ElementHistory> ListDatos;
    private View.OnClickListener Listener;
    private Context context;

    public AdapterHistory (ArrayList<ElementHistory> listDatos,Context context) {
        this.ListDatos = listDatos;
        this.context=context;
    }



    @NonNull
    @Override
    public AdapterHistory.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        view.setOnClickListener(this);
        return new AdapterHistory.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.ViewHolderDatos holder, int position) {

        holder.Name.setText(ListDatos.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return ListDatos.size();
    }

    public  void  setOnClickListener(View.OnClickListener listener)
    {
        this.Listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(Listener!=null)
        {
            Listener.onClick(v);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {


        TextView Name;



        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);



        }


    }
}
