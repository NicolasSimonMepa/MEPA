//Zuletzt bearbeitet von Vivien Stumpe am 14.04.16
package de.app.mepa.stammdaten;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.menu.Menu;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

//OnClickListener implementieren, um bei einem Klick auf ein Steuerelement reagieren zu können
    //von Vivien Stumpe, 01.04.16
public class Stammdaten extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //Erstellen einer Button-Variable, um den Button der Activity anzusprechen
    //von Vivien Stumpe, 01.04.16
    private Button btn_menu;

        //von Vivien Stumpe, 10.04.16
        //DrawerLayout für das Hamburger Menü
        //ListView, die die Einträge des Menüs enthält
        //Adapter, der die Einträge der ListView darstellt
        //Array mit den Icons, die im Menü dargestellt werden sollen
        private DrawerLayout drawerlayout_stammdaten;
        private ListView listview_stammdaten;
        private MyAdapter myadapter_stammdaten;
        private int[] drawer_icons_stammdaten={R.drawable.mepa_icon, R.drawable.mepa_icon,
                R.drawable.falleingabe, R.drawable.mepa_icon, R.drawable.upload, R.drawable.impressum};

    /*von Vivien Stumpe, 14.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stammdaten);


        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_stammdaten=(DrawerLayout) findViewById(R.id.drawerLayout_Stammdaten);
        listview_stammdaten=(ListView) findViewById(R.id.listview_stammdaten);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_stammdaten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_stammdaten), drawer_icons_stammdaten);
        listview_stammdaten.setAdapter(myadapter_stammdaten);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_stammdaten.setOnItemClickListener(this);

        /*von Vivien Stumpe, 14.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_stammdaten, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_stammdaten.addDrawerListener(actionbardrawertoggle);
    }


    //von Vivien Stumpe, 14.04.16
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Vivien Stumpe, 01.04.16
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 01.04.16
       /* if (ce == R.id.btn_menu_stammd){
            Intent intent = new Intent(Stammdaten.this, Menu.class);
            startActivity(intent);
        }
        */
    }
        //von Vivien Stumoe, 10.04.16
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
            selectItemFromDrawer(position);
        }
        //von Vivien Stumpe, 10.04.16
        private void selectItemFromDrawer(int position){
            //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
            if(position==0) {
                Intent intent = new Intent(Stammdaten.this, MainActivity.class);
                startActivity(intent);
            }
            //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
            if(position==1) {
                Intent intent = new Intent(Stammdaten.this, Einstellungen.class);
                startActivity(intent);
            }
            //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
            if(position==2) {
                Intent intent = new Intent(Stammdaten.this, Falleingabe.class);
                startActivity(intent);
            }
            //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
            if(position==3) {
                Intent intent = new Intent(Stammdaten.this, Falluebersicht.class);
                startActivity(intent);
            }
            //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
            if(position==4) {
                Intent intent = new Intent(Stammdaten.this, Upload.class);
                startActivity(intent);
            }
            //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
            if(position==5) {
                Intent intent = new Intent(Stammdaten.this, Impressum.class);
                startActivity(intent);
            }
        }
    }
