package de.app.mepa.einstellungen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;

public class Einstellungen extends AppCompatActivity implements View.OnClickListener{
    //Hallo dies ist eine Änderung
    //Vivien Stumpe, 01.04.16
    //Button für den Button Menü in der Einstellungen Activity
    private Button btn_menu_einst;
    private Button btn_impr_einst;
    private Button btn_stammdat_einst;
    private Button btn_sync_einst;
    private Button btn_löschen_einst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        //Zuweisen der Buttons zu den Buttons in der Activity
        btn_menu_einst = (Button)findViewById(R.id.btn_menu_einst);
        btn_impr_einst = (Button)findViewById(R.id.btn_impr_einst);
        btn_löschen_einst = (Button)findViewById(R.id.btn_löschen_einst);
        btn_stammdat_einst = (Button)findViewById(R.id.btn_stammdat_einst);
        btn_sync_einst = (Button)findViewById(R.id.btn_sync_einst);

        // Event abfangen
        btn_menu_einst.setOnClickListener(this);
        btn_impr_einst.setOnClickListener(this);
        btn_löschen_einst.setOnClickListener(this);
        btn_stammdat_einst.setOnClickListener(this);
        btn_sync_einst.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        if (ce == R.id.btn_menu_einst){
            Intent intent = new Intent(Einstellungen.this, Menu.class);
            startActivity(intent);
        }
        if (ce == R.id.btn_stammdat_einst){
            Intent intent = new Intent(Einstellungen.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}
//hallo ich habe etwas geändert Nicolas 1.04.2016
//Ich habe auch mal was probiert Vivien 01.04.16
