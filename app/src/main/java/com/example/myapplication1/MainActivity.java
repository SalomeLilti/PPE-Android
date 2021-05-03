package com.example.myapplication1;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick (View view) {
        if (view.getId() == R.id.btnfraisforfait){
            Intent intent = new Intent(getApplicationContext(), FraisForfait.class); //intent est une variable de type intent(classe) pr faire passer d'1 classe à l'autre
            startActivity(intent);
        }

        if (view.getId() == R.id.btnfraishorsforfait){
            Intent intent = new Intent(getApplicationContext(), FraisHorsForfait.class);
            startActivity(intent);
        }


        if (view.getId() == R.id.btnsynthese){
            Intent intent = new Intent(getApplicationContext(), SyntheseMois.class);
            startActivity(intent);
        }

    }

    //je cree la methode qui affiche un msg
    public void afficherMessage(String titre, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

    }

}
