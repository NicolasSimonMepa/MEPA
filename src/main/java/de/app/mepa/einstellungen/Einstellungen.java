//Zuletzt geändert von Vivien Stumpe am 04.04.16
package de.app.mepa.einstellungen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import de.app.mepa.impressum.Impressum;
import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//von Vivien Stumpe, 01.04.16
public class Einstellungen extends AppCompatActivity implements View.OnClickListener{
    //Button-Variablen für die Buttons in der Einstellungen Activity
    //von Vivien Stumpe, 01.04.16
    private Button btn_menu_einst;
    private Button btn_impr;
    private Button btn_stammdat_einst;
    private Button btn_sync_einst;
    private Button btn_löschen_einst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        //Zuweisen der Button-Variablen zu den Buttons in der Activity
        //von Vivien Stumpe, 01.04.16
        btn_menu_einst = (Button)findViewById(R.id.btn_menu_einst);
        btn_impr = (Button)findViewById(R.id.btn_impr_einst);
        btn_löschen_einst = (Button)findViewById(R.id.btn_löschen_einst);
        btn_stammdat_einst = (Button)findViewById(R.id.btn_stammdat_einst);
        btn_sync_einst = (Button)findViewById(R.id.btn_sync_einst);

        // Events abfangen und an den OnClickListener die aktuelle View übergeben
        //von Vivien Stumpe, 01.04.16
        btn_menu_einst.setOnClickListener(this);
        btn_impr.setOnClickListener(this);
        btn_löschen_einst.setOnClickListener(this);
        btn_stammdat_einst.setOnClickListener(this);
        btn_sync_einst.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 01.04.16
        if (ce == R.id.btn_menu_einst){
            Intent intent = new Intent(Einstellungen.this, Menu.class);
            startActivity(intent);
        }
        if (ce == R.id.btn_stammdat_einst){
            Intent intent = new Intent(Einstellungen.this, Stammdaten.class);
            startActivity(intent);
        }
        //von Vivien Stumpe, 04.04.16
        if (ce == R.id.btn_impr_einst){
            Intent intent = new Intent(Einstellungen.this, Impressum.class);
            startActivity(intent);
        }
    }
}
