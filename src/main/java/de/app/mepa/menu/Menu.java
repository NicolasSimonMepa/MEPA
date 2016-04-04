//Zuletzt bearbeitet von Vivien Stume am 04.04.16
package de.app.mepa.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;

        //OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
        //von Vivien Stumpe, 01.04.16
public class Menu extends AppCompatActivity implements View.OnClickListener{
public class Menu extends AppCompatActivity implements View.OnClickListener{
    //Erstellen von TextView-Variablen, um die TextViews in der Activity anzusprechen
    //von Vivien Stumpe, 01.04.16
    private TextView txtv_start;
    private TextView txtv_einst;
    private TextView txtv_falleing;
    private TextView txtv_falluebersicht;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        //Referenz zur TextView in der Activity herstellen
        //von Vivien Stumpe, 01.04.16
        txtv_start=(TextView)findViewById(R.id.txtv_start_menu);
        txtv_einst=(TextView)findViewById(R.id.txtv_einstellungen_menu);  
        txtv_falleing=(TextView)findViewById(R.id.txtv_falleingabe_menu);
        txtv_falluebersicht=(TextView)findViewById(R.id.txtv_falluebersicht_menu);
        
        //Übergabe der aktuellen View an den OnClickListener, um diese zu verarbeiten
        //von Vivien Stumpe, 01.04.16
        txtv_start.setOnClickListener(this);
        txtv_einst.setOnClickListener(this);
        txtv_falleing.setOnClickListener(this);
        txtv_falluebersicht.setOnClickListener(this);

    }

        //Prozedur, die implementiert werden muss für den OnClickListener
        //Definiert, was passiert wenn das entsprechende Element geklickt wurde
        //Von Vivien Stumpe, 01.04.16
    @Override
    public void onClick(View v) {
        //Implementierung einer Hilfsvariablen, um das geklickte Element zu identifizieren
        int ce = v.getId();
        
        //Wenn das geklickte Element mit dem angegebenen Element übereinstimmt
        //Wird ein neuer Intent erzeugt, der eine Verbindung zur angegebenen Activity herstellt
        //..und diese aufruft
        //von Vivien Stumpe, 01.04.16
        if (ce == R.id.txtv_start_menu){
            Intent intent = new Intent(Menu.this, MainActivity.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_einstellungen_menu){
            Intent intent = new Intent(Menu.this, Einstellungen.class);
            startActivity(intent);
        }
        //von Vivien Stumpe, 03.04.16
        if (ce == R.id.txtv_falleingabe_menu){
            Intent intent = new Intent(Menu.this, Falleingabe.class);
            startActivity(intent);
        }
        //von Vivien Stumpe, 04.04.16
        if (ce == R.id.txtv_falluebersicht_menu){
            Intent intent = new Intent(Menu.this, Falluebersicht.class);
            startActivity(intent);
        }
    }
}
