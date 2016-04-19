//Zuletzt geändert von Nicoals Simon 17.04.16
package de.app.mepa.falluebersicht;

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

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

public class Falluebersicht extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //Button-Variablen für die Buttons in der Einstellungen Activity
    //eingefügt von Nicolas Simon 17.04.16


    private LinearLayout lnl_buttons;

   //eingefügt von Nicolas Simon 17.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_falluebersicht;
    private ListView listview_falluebersicht;
    private MyAdapter myadapter_falluebersicht;
    private int[] drawer_icons_falluebersicht={R.drawable.mepa_icon, R.drawable.einstellungen,R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.impressum, R.drawable.stammdaten};

    /*von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    private Button btn_speichern_fallueb;
    private Button btn_loeschen_fallueb;
   // private LinearLayout lnl_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falluebersicht);
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht=(DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht=(ListView) findViewById(R.id.listview_falluebersicht);
        //Zuweisen der Button-Variablen zu den Buttons in der Activity
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb = (Button)findViewById(R.id.btn_loeschen_fallueb);
        btn_speichern_fallueb = (Button)findViewById(R.id.btn_speichern_fallueb);
        // Events abfangen und an den OnClickListener die aktuelle View übergeben
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb.setOnClickListener(this);
        btn_speichern_fallueb.setOnClickListener(this);


        //eingefügt von Nicolas Simon 17.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht=(DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht=(ListView) findViewById(R.id.listview_falluebersicht);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_falluebersicht=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav), drawer_icons_falluebersicht);
        listview_falluebersicht.setAdapter(myadapter_falluebersicht);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_falluebersicht.setOnItemClickListener(this);

       /* Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_falluebersicht, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_falluebersicht.addDrawerListener(actionbardrawertoggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //von Nicoals Simon, 17.04.16
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //eingefügt von Nicolas Simon 17.04.16
        if (ce == R.id.btn_speichern_fallueb) {
            //werden die Buttons eingeblendet
            lnl_buttons.setVisibility(View.VISIBLE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
        }
        if (ce == R.id.btn_loeschen_fallueb) {
            //werden die Buttons eingeblendet
            lnl_buttons.setVisibility(View.VISIBLE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
        }
    }
    //Von Nicolas Simon, 17.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }

    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Falluebersicht.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Falluebersicht.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Falluebersicht.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Falluebersicht.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Falluebersicht.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Falluebersicht.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Falluebersicht.this, Stammdaten.class);
            startActivity(intent);
        }

    }
}
