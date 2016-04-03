//Zuletzt geändert von Vivien Stumpe am 03.04.16
package de.app.mepa.mepa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.menu.Menu;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//von Vivien Stumpe, 01.04.16
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Buttonvariable für den Button Menü in der Main Activity erstellen
    //von Vivien Stumpe, 01.04.16
    private Button btn_menu;
    
    //Buttonvariable für den Button Falleingabe in der Main Activity erstellen
    //von Vivien Stumpe, 03.04.16
    private Button btn_fallein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Buttonverweis der Schaltfläche zur Variable
        //von Vivien Stumpe, 01.04.16
            btn_menu = (Button)findViewById(R.id.btn_menu_main);
        // Event abfangen
        //von Vivien Stumpe, 01.04.16
            btn_menu.setOnClickListener(this);

        //Buttonverweis der Schaltfläche in der Activity zur Variable
        //von Vivien Stumpe, 03.04.16
        btn_fallein = (Button)findViewById(R.id.btn_falleingabe_main);
        //Klick des Buttons abfangen mit dem OnClickListener
        //von Vivien Stumpe, 03.04.16
        btn_fallein.setOnClickListener(this);

    }

//Muss zwingend implementiert werden für den OnClickListener
//von Vivien Stumpe, 01.04.16
    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();
            //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 01.04.16
        if (ce == R.id.btn_menu_main){
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
        }
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 03.04.16
        if (ce == R.id.btn_falleingabe_main){
                Intent intent = new Intent(MainActivity.this, Falleingabe.class);
                startActivity(intent);
        }
    }
}
