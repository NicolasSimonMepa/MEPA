//Zuletzt bearbeitet von Vivien Stumpe am 01.04.16
package de.app.mepa.stammdaten;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.R;

    //OnClickListener implementieren, um bei einem Klick auf ein Steuerelement reagieren zu können
    //von Vivien Stumpe, 01.04.16
public class Stammdaten extends AppCompatActivity implements View.OnClickListener {
    //Erstellen einer Button-Variable, um den Button der Activity anzusprechen
    //von Vivien Stumpe, 01.04.16
    private Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stammdaten);

        //Buttonverweis der Schaltfläche zur Variable
        //von Vivien Stumpe, 01.04.16
        btn_menu = (Button)findViewById(R.id.btn_menu_stammd);

        // Event abfangen und die aktuelle View an den OnClickListener übergeben
        //von Vivien Stumpe, 01.04.16
        btn_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 01.04.16
        if (ce == R.id.btn_menu_stammd){
            Intent intent = new Intent(Stammdaten.this, Menu.class);
            startActivity(intent);
        }
    }
}
