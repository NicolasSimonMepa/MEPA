/**
 * Created by Nathalie on 18.04.2016.
 */
// Zuletzt bearbeitet von Nathalie Horn, 18.04.16
package de.app.mepa.mitarbeiterkonfig;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;


//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//OnItemClickListener implementieren, um auf einen Klick in der ListView reagieren zu können

public class Mitarbeiterkonfig extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_mitarbeiterkonfig;
    private ListView listview_mitarbeiterkonfig;
    private MyAdapter myadapter_mitarbeiterkonfig;
    private int[] drawer_icons_mitarbeiterkonfig={R.drawable.mepa_icon, R.drawable.einstellungen,
            R.drawable.falleingabe, R.drawable.falluebersicht, R.drawable.upload, R.drawable.impressum, R.drawable.stammdaten,};

    //ActionBarDrawerToggle und Toolbar anlegen
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    /* 
Buttonvariablen für die Buttons Speichern und Verwerfen
 */
    private LinearLayout lnl_buttons;
    private Button btn_speichern;
    private Button btn_verwerfen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiterkonfig);

        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_mitarbeiterkonfig=(DrawerLayout) findViewById(R.id.drawerLayout_Mitarbeiterkonfig);
        listview_mitarbeiterkonfig=(ListView) findViewById(R.id.listview_mitarbeiterkonfig);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_mitarbeiterkonfig=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_erfassung), drawer_icons_mitarbeiterkonfig);
        listview_mitarbeiterkonfig.setAdapter(myadapter_mitarbeiterkonfig);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_mitarbeiterkonfig.setOnItemClickListener(this);

        /*
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_mitarbeiterkonfig, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_mitarbeiterkonfig.addDrawerListener(actionbardrawertoggle);

        //Zuweisen der Button-Variablen zu den Buttons in der Activity
        lnl_buttons = (LinearLayout)findViewById(R.id.lnl_buttons);

        /*
        Zuweisen der Buttonvariablen zu den Buttons in der Activity
        Setzen des OnClickListeners, damit auf Klicks reagiert wird
         */
        btn_verwerfen = (Button)findViewById(R.id.btn_verwerfen_mitarbeiter_konfig);
        btn_speichern = (Button)findViewById(R.id.btn_speichern_mitarbeiter_konfig);
        btn_verwerfen.setOnClickListener(this);
        btn_speichern.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Stammdaten.class);
            startActivity(intent);
        }
    }
}




