// zuletzt geändert von Emile Yoncaova, 19.04.16
package de.app.mepa.ersthelfermassnahmen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

public class Ersthelfermassnahmen extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    private Spinner spin_ersthelfermassnahmen;
    private DrawerLayout drawerlayout_ersthelfermassnahmen;
    private ListView listview_ersthelfermassnahmen;
    private MyAdapter myadapter_ersthelfermassnahmen;
    private int[]drawer_icons_ersthelfermassnahmen={R.drawable.mepa_icon,R.drawable.mepa_icon,
            R.drawable.falleingabe,R.drawable.mepa_icon,R.drawable.upload,R.drawable.impressum,R.drawable.mepa_icon,};

    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    private String[]ersthelfermassnahmen={"keine","suffizient","insuffizient","AED"};



    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    /* ---------------------------------------
    private View view;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ersthelfermassnahmen);

        ArrayAdapter<String> adapter_ersthelfermassnahmen=new ArrayAdapter<String>(Ersthelfermassnahmen.this,
                android.R.layout.simple_spinner_item,ersthelfermassnahmen);

        adapter_ersthelfermassnahmen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_ersthelfermassnahmen=(Spinner)findViewById(R.id.spin_ersthelfermassnahmen);
        spin_ersthelfermassnahmen.setAdapter(adapter_ersthelfermassnahmen);
        spin_ersthelfermassnahmen.setOnItemSelectedListener(this);

        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_ersthelfermassnahmen=(DrawerLayout)findViewById(R.id.drawerLayout_Ersthelfermassnahmen);
        listview_ersthelfermassnahmen=(ListView)findViewById(R.id.listview_ersthelfermassnahmen);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_ersthelfermassnahmen=new MyAdapter(this,this.getResources().getStringArray(R.array.drawer_nav_erfassung),drawer_icons_ersthelfermassnahmen);
        listview_ersthelfermassnahmen.setAdapter(myadapter_ersthelfermassnahmen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_ersthelfermassnahmen.setOnItemClickListener(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_ersthelfermassnahmen, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_ersthelfermassnahmen.addDrawerListener(actionbardrawertoggle);


                 // von Vivien Stumpe, 19.04.16
                //Wechseln der Aktivität mittels Swipe
                 //Hauptelement der Activity finden und der Variable zuweisen
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        /* ------------------------------------------
        view=(View) findViewById(R.id.scrV_ersthelfermassnahmen);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Ersthelfermassnahmen.this, notfallsituation.class);
                startActivity(intent);
            }
        });
        */

    }

    @Override
    public void onItemSelected(AdapterView<?>parent,View view,int position,long id){
        switch(position){
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                break;
            case 3:
                // Was soll passieren, wenn das vierte Element gewählt wurde?
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?>parent){
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Ersthelfermassnahmen der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Ersthelfermassnahmen.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}
