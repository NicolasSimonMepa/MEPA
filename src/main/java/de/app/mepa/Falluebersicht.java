//Zuletzt geändert von Vivien Stumpe 21.05.16
package de.app.mepa.falluebersicht;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.app.mepa.Adapter_Falluebersicht;
import de.app.mepa.FalleingabeDataSource;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

public class Falluebersicht extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //Button-Variablen für die Buttons in der Einstellungen Activity
    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16

    /* Vivien Stumpe, 01.05.16
    Das LinearLayout gibt es in der Fallübersicht nicht?
    private LinearLayout lnl_buttons;
    */

   //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_falluebersicht;
    private ListView listview_falluebersicht;
    private MyAdapter myadapter_falluebersicht;
    private int[] drawer_icons_falluebersicht={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*Nicolas Simon, übernommen von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    private Button btn_speichern_fallueb;
    private Button btn_loeschen_fallueb;

    /* Vivien Stumpe, 21.05.16
    String Array mit Testdaten, die in der ListView dargestellt werden sollen
    Adapter vom Typ Adapter_Falluebersicht, damit die Daten auch dargestellt werden können
    */
   String [] faelleArray = {
           "12343 Müller, Peter",
           "12234 Schulze, Klaus",
           "13542 Meier Hans",
           "16532 Wolf Michael",
           "17532 Blümel Petra",
           "14785 Kalend Tina",
           "14674 Fels Sophie",
           "36743 Nutella Lisa",
           "23454 Ulten Ute"
   };
    ListView falluebersichtListView;
    Adapter_Falluebersicht faelleAdapter;
    FalleingabeDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falluebersicht);
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht = (DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht = (ListView) findViewById(R.id.listview_falluebersicht);
        //Zuweisen der Button-Variablen zu den Buttons in der Activity
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb = (Button) findViewById(R.id.btn_loeschen_fallueb);
        btn_speichern_fallueb = (Button) findViewById(R.id.btn_speichern_fallueb);
        // Events abfangen und an den OnClickListener die aktuelle View übergeben
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb.setOnClickListener(this);
        btn_speichern_fallueb.setOnClickListener(this);


        //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht = (DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht = (ListView) findViewById(R.id.listview_falluebersicht);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_falluebersicht = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_falluebersicht);
        listview_falluebersicht.setAdapter(myadapter_falluebersicht);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_falluebersicht.setOnItemClickListener(this);

       /* Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle = new ActionBarDrawerToggle(this, drawerlayout_falluebersicht, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_falluebersicht.addDrawerListener(actionbardrawertoggle);


        /* von Vivien Stumpe, 21.05.16
        Die Listview wird mit den Fällen befüllt
        Die Einträge in der ListView sind klickbar (Löschen Icon siehe Adapter_Falluebersicht.java)
        Wird ein Eintrag geklickt gelangt man zur Falleingabe und die gespeicherten Daten werden angezeigt
         */
        dataSource=new FalleingabeDataSource(this);
        showAllListEntries();
        falluebersichtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent fall=new Intent(Falluebersicht.this, Falleingabe.class);
                // von Vivien Stumpe, 23.05.16
                //Teilt den String bei einem Leerzeichen
                String string = faelleArray[position];
                String[] parts = string.split("\\s+");
                String prot_id_string = parts[0];
                String name = parts[1];
                //prot_id enthält die ID des ausgewählten Falls
                int prot_id=Integer.parseInt(prot_id_string);
                //dataSource.selectFall(prot_id);
                startActivity(fall);
                Log.d("Fall", prot_id + " ID");
            }
        });
    }
    /* von Vivien Stumpe, 21.05.16
    Prozedur, die alle Fälle aus der DB liest und in der ListView darstellt
    !!Muss noch angepasst werden, wenn Daten aus der DB kommen!
     */
    private void showAllListEntries() {
        List<String> fallListe = new ArrayList<>(Arrays.asList(faelleArray));
        faelleAdapter =
                new Adapter_Falluebersicht(
                        Falluebersicht.this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.falluebersicht_listview_item, // ID der XML-Layout Datei
                        faelleArray); // Beispieldaten in einer ArrayList
        falluebersichtListView = (ListView)findViewById(R.id.list_falluebersicht);
        falluebersichtListView.setAdapter(faelleAdapter);
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
        //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
        int ce = v.getId();
        //Ein Intent erzeugen, wenn der Button geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //eingefügt von Nicolas Simon 17.04.16
        if (ce == R.id.btn_speichern_fallueb) {
           /*
            //werden die Buttons eingeblendet
            lnl_buttons.setVisibility(View.VISIBLE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
            */
        }
        if (ce == R.id.btn_loeschen_fallueb) {
            /*
            //werden die Buttons eingeblendet
            lnl_buttons.setVisibility(View.VISIBLE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
            */
        }
    }

    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Falluebersicht.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Falluebersicht.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Falluebersicht.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Falluebersicht.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Falluebersicht.this, Impressum.class);
            startActivity(intent);
        }

    }
    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    /* von Vivien Stumpe, 21.05.16
    Prozedur, die die Select Anweisungen zum ausgewählten Fall ausführt,
    damit die gespeicherten Werte in den Screens angezeigt werden können
    !FOLGT!
     */

    public void ladeFallDaten(int position){
        Log.d("Fall","Fall "+faelleArray[position]);
    }
}
