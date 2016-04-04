//Zuletzt geändert von Vivien Stumpe am 04.04.16
package de.app.mepa.falluebersicht;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;

public class Falluebersicht extends AppCompatActivity implements View.OnClickListener {

    //Erstellen einer Button-Variable
    private Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falluebersicht);
        //Buttonverweis der Schaltfläche zur Variable
        btn_menu = (Button)findViewById(R.id.btn_menu_fallueb);
        // Event abfangen
        btn_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        if (ce == R.id.btn_menu_fallueb){
            Intent intent = new Intent(Falluebersicht.this, Menu.class);
            startActivity(intent);
        }
    }
}
