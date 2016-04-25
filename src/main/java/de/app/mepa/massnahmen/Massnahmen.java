//Zuletzt bearbeitet von Emile Yoncaova, 15.04.16
package de.app.mepa.massnahmen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erstbefund.Erstbefund;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;
import de.app.mepa.mepa.R;

public class Massnahmen extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerlayout_massnahmen;
    private ListView listview_massnahmen;
    private MyAdapter myadapter_massnahmen;
    private int[] drawer_icons_massnahmen={R.drawable.mepa_icon, R.drawable.einstellungen,
            R.drawable.falleingabe, R.drawable.falluebersicht, R.drawable.upload, R.drawable.impressum, R.drawable.stammdaten,};

    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;


    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massnahmen);

        drawerlayout_massnahmen=(DrawerLayout) findViewById(R.id.drawerLayout_Massnahmen);
        listview_massnahmen=(ListView) findViewById(R.id.listview_Massnahmen);

        myadapter_massnahmen=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav), drawer_icons_massnahmen);
        listview_massnahmen.setAdapter(myadapter_massnahmen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_massnahmen.setOnItemClickListener(this);

        //von Vivien Stumpe, 11.04.16
                    //Wechseln der Akitivität mittels Swipe
                  //Hauptelement der Activity finden und der Variable zuweisen
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        view=(View) findViewById(R.id.scrV_massnahmen);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Massnahmen.this, Erstbefund.class);
                startActivity(intent);
            }
        });
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_massnahmen, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_massnahmen.addDrawerListener(actionbardrawertoggle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Massnahmen.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Massnahmen.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Massnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Massnahmen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Massnahmen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Massnahmen.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Massnahmen.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}
