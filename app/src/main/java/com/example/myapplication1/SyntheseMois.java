package com.example.myapplication1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.AdapterView.OnItemClickListener;

import androidx.appcompat.app.AppCompatActivity;

public class SyntheseMois extends AppCompatActivity {
    private SimpleCursorAdapter dataAdapter;
    private SQLHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese_mois);
        db = new SQLHelper(this);
        db.open();



        displayListView();
    }

    //Fonction qui permet de remplir le ListView
    private void displayListView() {

        Cursor cursor = db.fetchAllFrais();

        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                SQLHelper.LIBELLE,
                SQLHelper.MONTANT,
                SQLHelper.DATE,
                SQLHelper.DATEACTU,
                SQLHelper.QUANTITE,
                SQLHelper.ID

        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.libellefrais,
                R.id.montantfrais,
                R.id.datefrais,
                R.id.dateActu,
                R.id.txtQte,
                R.id.idFrais,

        };
        //On créer l'adaptateur à l'aide du curseur pointant sur les données souhaitées
        // ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.frais_infos,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);


        //Pour que l'id du frais s'affiche quand on clique dessus, et pour supprimer le fraids
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // On obtient l'id du frais en cliquant sur le frais
                String myId =
                        cursor.getString(cursor.getColumnIndexOrThrow("idFrais"));
                Toast.makeText(getApplicationContext(),
                        myId, Toast.LENGTH_SHORT).show();

            }
        });
        //relier a l id et utiliser les fonctions de suppression de la bdd pr supprimer

    }

    //fonction pour supprimer un frais, à relier avec la poubelle de la list view
    public void doDeleteOnClick(View v) {
        Toast.makeText(v.getContext(),"You clicked the DELETE button for id " + ((String) v.getTag()), Toast.LENGTH_SHORT).show();
    }

    public void clique_retour(View view) {
        finish();
    }

}
