package com.example.myapplication1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class FraisHorsForfait extends MainActivity {
    //declaration des variables
    Button btnAjouter, btnModifier, btnSupprimer;
    EditText  libelle;
    EditText montant;
    TextView date;
    SQLHelper database; //variable qui permet d'accéder à la classe SQLHelper pr pouvoir accéder à ses méthodes.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hors_forfait);
        database=new SQLHelper(this); //j'instancie la classe SQLHelper avec la variable database
        init();
    }
    //methode qui permet de relier les boutons avec leur objet graphique
    public void init(){
        btnAjouter=findViewById(R.id.btnAjouter);
        btnModifier=findViewById(R.id.btnModifier);
        btnSupprimer=findViewById(R.id.btnSupprimer);
        libelle=findViewById(R.id.libellefrais);
        montant=findViewById(R.id.montantfrais);
        date=findViewById(R.id.datefrais);
        DatePickerDialog picker;
    }

    public void ShowCal(View vv)
    {
        DatePickerDialog picker = new DatePickerDialog(FraisHorsForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, 2020, 12, 27);
        picker.show();
        Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    }

    public void monClick(View view){
        switch (view.getId()){
            case R.id.btnAjouter:
                String libelle1= libelle.getText().toString();
                double montant1 = Double.parseDouble(montant.getText().toString());//conversion d'un text en string et d'un string en double
                String date1=date.getText().toString();
                if(libelle1.trim().length()==0 || montant1==0 || date1.length()==0) { //test si les champs libelle, montant et date sont renseignes
                    afficherMessage("Erreur!", "Champs vides");
                    return;
                } else{
                    database.insertData(null, null, date1, montant1, libelle1);
                    afficherMessage("Succes!", "Informations ajoutées");
                    return;
                }
        }
    }

    public void clique_retour(View view) {
        finish();
    }

}
