//Zuletzt geändert von Vivien Stumpe am 10.04.16
package de.app.mepa.einstellungen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import de.app.mepa.MyAdapter;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//von Vivien Stumpe, 01.04.16
//OnItemClickListener implementieren, um auf einen Klick in der ListView reagieren zu können
//von Vivien Stumpe, 09.04.16
public class Einstellungen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    //Button-Variablen für die Buttons in der Einstellungen Activity
    //von Vivien Stumpe, 01.04.16
    private Button btn_menu_einst;
    private Button btn_impr;
    private Button btn_stammdat_einst;
    private Button btn_sync_einst;
    private Button btn_löschen_einst;

    //von Vivien Stumpe, 09.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_einstellungen;
    private ListView listview_einstellungen;
    private MyAdapter myadapter_einstellungen;
    private int[] drawer_icons_einstellungen={R.drawable.mepa_icon, R.drawable.falleingabe,
            R.drawable.mepa_icon, R.drawable.upload, R.drawable.impressum, R.drawable.mepa_icon,};

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

        //von Vivien Stumpe, 09.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_einstellungen=(DrawerLayout) findViewById(R.id.drawerLayout_Einstellungen);
        listview_einstellungen=(ListView) findViewById(R.id.listview_einstellungen);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_einstellungen=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_einstellungen), drawer_icons_einstellungen);
        listview_einstellungen.setAdapter(myadapter_einstellungen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_einstellungen.setOnItemClickListener(this);
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
    //Von Vivien Stumpe, 09.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }

    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Einstellungen.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==1) {
            Intent intent = new Intent(Einstellungen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==2) {
            Intent intent = new Intent(Einstellungen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //von Vivien Stumpe, 10.04.16
        //Wenn das vierte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==3) {
            Intent intent = new Intent(Einstellungen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Einstellungen.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==5) {
            Intent intent = new Intent(Einstellungen.this, Stammdaten.class);
            startActivity(intent);
        }

    }
}

