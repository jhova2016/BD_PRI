package com.example.bd_pri.Models.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bd_pri.Models.Elements.ElementRecycler;
import com.example.bd_pri.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class AdapterElementRecycler  extends RecyclerView.Adapter<AdapterElementRecycler.ViewHolderDatos> implements View.OnClickListener {


    //ArrayList<String> ListDatos;
    ArrayList<ElementRecycler> ListDatos;
    private View.OnClickListener Listener;
    private Context context;

    public AdapterElementRecycler (ArrayList<ElementRecycler> listDatos,Context context) {
        this.ListDatos = listDatos;
        this.context=context;
    }



    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.Section.setText(ListDatos.get(position).getSection());
        holder.Employment.setText(ListDatos.get(position).getEmployment());
        holder.Name.setText(ListDatos.get(position).getName());
        holder.ElectorKey.setText(ListDatos.get(position).getElectorKey());
        holder.Phone.setText(ListDatos.get(position).getPhone());
        holder.DirectBoss.setText(ListDatos.get(position).getDirectBoss());
        holder.Id=ListDatos.get(position).getId();


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


        String Id;
        TextView Section;
        TextView Employment;
        TextView Name;
        TextView ElectorKey;
        TextView Phone;
        TextView DirectBoss;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Section=itemView.findViewById(R.id.Section);
            Employment=itemView.findViewById(R.id.Employmen);
            Name=itemView.findViewById(R.id.Name);
            ElectorKey=itemView.findViewById(R.id.ElectorKey);
            Phone=itemView.findViewById(R.id.Phone);
            DirectBoss=itemView.findViewById(R.id.DirectBoss);


        }


    }
}
