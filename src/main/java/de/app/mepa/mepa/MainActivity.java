//Zuletzt geändert von Vivien Stumpe am 12.04.16
package de.app.mepa.mepa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//von Vivien Stumpe, 01.04.16
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* von Vivien Stumpe, 12.04.16
    TextView Variablen für die TextViews in der MainActivity
    "Kachel Menü"
     */
    private TextView txtv_fallein;
    private TextView txtv_fallueb;
    private TextView txtv_stammdat;
    private TextView txtv_upload;
    private TextView txtv_einst;
    private TextView txtv_impressum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /* von Vivien Stumpe, 12.04.16
        Verbindung der TextView-Variablen zu den Elementen der View herstellen,
        damit auf Klicks der Benutzer reagiert werden kann.
        Einen Klick mit dem OnClickListener abfangen
         */
        txtv_einst= (TextView)findViewById(R.id.txtv_main_einstellungen);
        txtv_fallein=(TextView)findViewById(R.id.txtv_main_falleingabe);
        txtv_fallueb=(TextView)findViewById(R.id.txtv_main_falluebersicht);
        txtv_upload=(TextView)findViewById(R.id.txtv_main_upload);
        txtv_impressum=(TextView)findViewById(R.id.txtv_main_impressum);
        txtv_stammdat=(TextView)findViewById(R.id.txtv_main_stammdaten);

        txtv_einst.setOnClickListener(this);
        txtv_fallein.setOnClickListener(this);
        txtv_fallueb.setOnClickListener(this);
        txtv_upload.setOnClickListener(this);
        txtv_impressum.setOnClickListener(this);
        txtv_stammdat.setOnClickListener(this);
    }

//Muss zwingend implementiert werden für den OnClickListener
//von Vivien Stumpe, 01.04.16
    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();

        /* von Vivien Stumpe, 12.04.16
        Ein Intent erzeugen, wenn die TextView ausgewählt wurde
        Das Intent stellt eine Verbindung zur angegebenen Activity her und ruft diese auf
         */
        if (ce == R.id.txtv_main_einstellungen) {
            Intent intent = new Intent(MainActivity.this, Einstellungen.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_main_falleingabe) {
            Intent intent = new Intent(MainActivity.this, Falleingabe.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_main_falluebersicht) {
            Intent intent = new Intent(MainActivity.this, Falluebersicht.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_main_stammdaten) {
            Intent intent = new Intent(MainActivity.this, Stammdaten.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_main_upload) {
            Intent intent = new Intent(MainActivity.this, Upload.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_main_impressum) {
            Intent intent = new Intent(MainActivity.this, Impressum.class);
            startActivity(intent);
        }
    }
}
