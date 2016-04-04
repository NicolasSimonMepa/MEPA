//Zuletzt geändert von Vivien Stumpe am 04.04.16
package de.app.mepa.falleingabe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;
import de.app.mepa.pers_daten.Pers_daten;

//OnClickListener implementieren, um auf einen Klick des Benutzers zu reagieren
//von Vivien Stumpe, 03.04.16
public class Falleingabe extends AppCompatActivity implements View.OnClickListener{
    //private Buttonvariable, die auf den Button in der Activity zeigen soll
    //von Vivien Stumpe, 03.04.16
    private Button btn_fallein;

    //private Textviewvariable, die auf Persönliche Daten in der Activity zeigen soll
    //von Vivien Stumpe, 04.04.16
    private TextView txtv_pers_daten;

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

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 04.04.16
        txtv_pers_daten.setOnClickListener(this);

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
    }
}

