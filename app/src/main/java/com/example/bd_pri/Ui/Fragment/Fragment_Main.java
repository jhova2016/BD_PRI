package com.example.bd_pri.Ui.Fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bd_pri.Models.Adapters.AdapterElementRecycler;
import com.example.bd_pri.Models.Adapters.AdapterHistory;
import com.example.bd_pri.Models.Elements.ElementHistory;
import com.example.bd_pri.Models.Elements.ElementRecycler;
import com.example.bd_pri.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Main extends Fragment {



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    final ArrayList<ElementRecycler> ElementAllRecycler = new ArrayList<ElementRecycler>();

    //Left
    RecyclerView RecyclerViewLeft;
    AdapterElementRecycler AdapterElementRecyclerLeft;
    final ArrayList<ElementRecycler> ElementRecyclerLeft = new ArrayList<ElementRecycler>();

    //EndLeft

    //Right
    RecyclerView RecyclerViewRight;
    AdapterElementRecycler AdapterElementRecyclerRight;
    final ArrayList<ElementRecycler> ElementRecyclerRight = new ArrayList<ElementRecycler>();
    FloatingActionButton FloatingButtonRight;
    //EndRight

    //History

    RecyclerView RecyclerHistory;
    AdapterHistory AdapterElementRecyclerHistory;
    final ArrayList<ElementHistory> ElementRecyclerHistory = new ArrayList<ElementHistory>();

    //EndHistory


    AlertDialog DialogNewElementRight;


    String ElementSelected="";


    public Fragment_Main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Main.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Main newInstance(String param1, String param2) {
        Fragment_Main fragment = new Fragment_Main();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__main, container, false);

        RecyclerHistory=view.findViewById(R.id.RecyclerHistory);
        RecyclerHistory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        FloatingButtonRight=view.findViewById(R.id.floatingButtonRight);

        RecyclerViewLeft = view.findViewById(R.id.RecyclerLeft);
        RecyclerViewLeft.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        RecyclerViewRight = view.findViewById(R.id.RecyclerRight);
        RecyclerViewRight.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ElementRecyclerHistory.clear();
        ElementRecyclerHistory.add(new ElementHistory("uno"));
        AdapterElementRecyclerHistory = new AdapterHistory(ElementRecyclerHistory,getContext());
        AdapterElementRecyclerHistory.notifyDataSetChanged();
        RecyclerHistory.setAdapter(AdapterElementRecyclerHistory);

        AdapterElementRecyclerHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Aux=ElementRecyclerHistory.get(RecyclerHistory.getChildAdapterPosition(v)).getName();

                Toast.makeText(getContext(),Aux,Toast.LENGTH_LONG).show();

                FillRecyclerRight("###");
                FillRecyclerLeft(Aux);



            }
        });



        DownDataBase();


        FloatingButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewRight(ElementSelected);
                DialogNewElementRight.show();
            }
        });



        return view;
    }

    public void DownDataBase()
    {

        db.collection("Elements")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ElementAllRecycler.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                ElementAllRecycler.add(new ElementRecycler(
                                            document.getId(),
                                            document.get("Name").toString(),
                                            document.get("Section").toString(),
                                            document.get("Employment").toString(),
                                            document.get("ElectorKey").toString(),
                                            document.get("Phone").toString(),
                                            document.get("DirectBoss").toString()
                                    ));
                                FillRecyclerLeft("");
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }


    public void  FillRecyclerLeft(String TypeElement)
    {



        ElementRecyclerLeft.clear();
        for(int i=0;i<ElementAllRecycler.size();i++)
        {

            if(ElementAllRecycler.get(i).getDirectBoss().equals(TypeElement))
            {
                ElementRecyclerLeft.add(ElementAllRecycler.get(i));
            }

        }

        AdapterElementRecyclerLeft = new AdapterElementRecycler(ElementRecyclerLeft,getContext());
        AdapterElementRecyclerLeft.notifyDataSetChanged();
        RecyclerViewLeft.setAdapter(AdapterElementRecyclerLeft);

        AdapterElementRecyclerLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Aux=ElementRecyclerLeft.get(RecyclerViewLeft.getChildAdapterPosition(v)).getName();

                FillRecyclerRight(Aux);



            }
        });


    }

    public void  FillRecyclerRight(String ElementName)
    {

        ElementRecyclerRight.clear();
        for(int i=0;i<ElementAllRecycler.size();i++)
        {

            if(ElementAllRecycler.get(i).getDirectBoss().equals(ElementName))
            {
                ElementRecyclerRight.add(ElementAllRecycler.get(i));
            }

        }
        ElementSelected=ElementName;

        AdapterElementRecyclerRight = new AdapterElementRecycler(ElementRecyclerRight,getContext());
        AdapterElementRecyclerRight.notifyDataSetChanged();
        RecyclerViewRight.setAdapter(AdapterElementRecyclerRight);


        AdapterElementRecyclerRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Aux=ElementRecyclerRight.get(RecyclerViewRight.getChildAdapterPosition(v)).getDirectBoss();
                String Aux2=ElementRecyclerRight.get(RecyclerViewRight.getChildAdapterPosition(v)).getName();

                FillRecyclerLeft(Aux);
                FillRecyclerRight(Aux2);

                ElementRecyclerHistory.add(new ElementHistory(Aux));
                AdapterElementRecyclerHistory.notifyDataSetChanged();
                RecyclerHistory.setAdapter(AdapterElementRecyclerHistory);


            }
        });




    }


    public void DialogNewRight(String directboss)
    {

        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_element,null);

        EditText Name=view.findViewById(R.id.EditName);
        EditText Section=view.findViewById(R.id.EditSection);
        EditText Employment=view.findViewById(R.id.EditEmployment);
        EditText ElectorKey=view.findViewById(R.id.EditElectorKey);
        EditText Phone=view.findViewById(R.id.EditPhone);
        TextView DirectBoss=view.findViewById(R.id.EditDirectBoss);
        DirectBoss.setText(directboss);


        Button Save=view.findViewById(R.id.BtnSave);
        Button Cancel=view.findViewById(R.id.BtnCancel);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a new user with a first and last name
                Map<String, Object> Element = new HashMap<>();
                Element.put("Name", Name.getText().toString());
                Element.put("Section", Section.getText().toString());
                Element.put("Employment", Employment.getText().toString());
                Element.put("ElectorKey", ElectorKey.getText().toString());
                Element.put("Phone", Phone.getText().toString());
                Element.put("DirectBoss",directboss);

// Add a new document with a generated ID
                db.collection("Elements")
                        .add(Element)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                                DownDataBase();
                                Toast.makeText(getContext(),"Se agrego correctamente",Toast.LENGTH_LONG).show();
                                DialogNewElementRight.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });


            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogNewElementRight.dismiss();

            }
        });


        builder.setView(view);
        DialogNewElementRight =builder.create();
        DialogNewElementRight.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



    }



}