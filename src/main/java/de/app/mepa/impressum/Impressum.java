//Zuletzt geändert von Nicolas Simon am 30.04.16
package de.app.mepa.impressum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

public class Impressum extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    private DrawerLayout drawerlayout_impressum;
    private ListView listview_impressum;
    private MyAdapter myadapter_impressum;
    private int[] drawer_icons_impressum={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressum);

        //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_impressum=(DrawerLayout) findViewById(R.id.drawerLayout_Impressum);
        listview_impressum=(ListView) findViewById(R.id.listview_impressum); // kann kein Listview_impressum finden, müsste eig erzeugt sein?!
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_impressum=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_impressum);
        listview_impressum.setAdapter(myadapter_impressum);
        listview_impressum.setOnItemClickListener(this);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_impressum, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_impressum.addDrawerListener(actionbardrawertoggle);
    }
    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf

        }

    @Override
    //Wird aufgerufen, wenn ein Element im Drawer geklickt wurde
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }

    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Impressum.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Impressum.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Impressum.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Impressum.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Impressum.this, Impressum.class);
            startActivity(intent);
        }

    }
}
