//Zuletzt geändert von Vivien Stumpe, 10.04.16
//Zuletzt geändert von Nathalie Horn, 25.04.16
package de.app.mepa.upload;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;

public class Upload extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //Drawer Layout: Listview, Adapter und Array der Icons im Drawer Menü
    private DrawerLayout drawerlayout_upload;
    private ListView listview_upload;
    private MyAdapter myadapter_upload;
    private int[] drawer_icons_upload = {R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Nathalie Horn, 13.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // für das Drawer Layout, Drawer und Listview zuweisen
        drawerlayout_upload = (DrawerLayout) findViewById(R.id.drawerLayout_Upload);
        listview_upload = (ListView) findViewById(R.id.listview_upload);
        //Adapter für Listview erzeugen
        myadapter_upload = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_upload);
        listview_upload.setAdapter(myadapter_upload);
        listview_upload.setOnItemClickListener(this);

        /*von , 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_upload, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_upload.addDrawerListener(actionbardrawertoggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt (Drawer/Hamburger Menü)
        selectItemFromDrawer(position);
    }

    // geändert von Nathalie Horn, 25.04.16
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Upload.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Upload.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Upload.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Upload.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Upload.this, Impressum.class);
            startActivity(intent);
        }

    }

}


