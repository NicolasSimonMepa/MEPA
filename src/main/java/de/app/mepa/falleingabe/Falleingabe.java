//Zuletzt geändert von Vivien Stumpe am 08.04.2016
package de.app.mepa.falleingabe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.pers_daten.Pers_daten;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.erkrankung.Erkrankung;

//OnClickListener implementieren, um auf einen Klick des Benutzers zu reagieren
//von Vivien Stumpe, 03.04.16
public class Falleingabe extends AppCompatActivity implements View.OnClickListener{
    //private Buttonvariable, die auf den Button in der Activity zeigen soll
    //von Vivien Stumpe, 03.04.16
    private Button btn_fallein;

    //private Textviewvariable, die auf Persönliche Daten in der Activity zeigen soll
    //von Vivien Stumpe, 04.04.16
    private TextView txtv_pers_daten;
    private TextView txtv_notfall;
    private TextView txtv_verletzung;
    private TextView txtv_erkrankung;
    private TextView txtv_massnahmen;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falleingabe);

        //Verbindung zwischen Buttonvariable und Button in der Activity herstellen
        //von Vivien Stumpe, 03.04.16
        btn_fallein = (Button)findViewById(R.id.btn_menu_fallein);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 03.04.16
        btn_fallein.setOnClickListener(this);

        //Verbindung zwischen Variable und TextView in der Activity herstellen
        //von Vivien Stumpe, 04.04.16
        txtv_pers_daten = (TextView)findViewById(R.id.txtv_pers_daten);
        txtv_notfall = (TextView)findViewById(R.id.txtv_notfallsit);
        txtv_verletzung = (TextView)findViewById(R.id.txtv_verletzung);
        txtv_erkrankung = (TextView)findViewById(R.id.txtv_erkrankung);
        txtv_massnahmen = (TextView)findViewById(R.id.txtv_maßnahmen);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 04.04.16
        txtv_pers_daten.setOnClickListener(this);
        txtv_notfall.setOnClickListener(this);
        txtv_verletzung.setOnClickListener(this);
        txtv_erkrankung.setOnClickListener(this);
        txtv_massnahmen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Deklaration und Initialisierung einer Hilfsvariablen (clicked element), die die ID des geklickten Buttons erhält
        //von Vivien Stumpe, 03.04.16
        int ce = v.getId();

        //Ein Intent erzeugen, wenn der bestimmte Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        //Aufrufen der Activity mittels Intent
        //von Vivien Stumpe, 03.04.16
        if(ce == R.id.btn_menu_fallein){
            Intent intent = new Intent(Falleingabe.this, Menu.class);
            startActivity(intent);
        }
        //Ein Intent erzeugen, wenn der bestimmte Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        //Aufrufen der Activity mittels Intent
        //von Vivien Stumpe, 04.04.16
        if(ce == R.id.txtv_pers_daten){
            Intent intent = new Intent(Falleingabe.this, Pers_daten.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_notfallsit){
            Intent intent = new Intent(Falleingabe.this, notfallsituation.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_verletzung){
            Intent intent = new Intent(Falleingabe.this, Verletzung.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_erkrankung){
            Intent intent = new Intent(Falleingabe.this, Erkrankung.class);
            startActivity(intent);
        }
        if(ce == R.id.txtv_maßnahmen){
            Intent intent = new Intent(Falleingabe.this, Massnahmen.class);
            startActivity(intent);
        }
    }
}
