//Zuletzt geändert von Vivien Stumpe am 06.06.16
package de.app.mepa.einstellungen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.app.mepa.FalleingabeDataSource;
import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.XMLCreator;
import de.app.mepa.ersthelfermassnahmen.Ersthelfermassnahmen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.mitarbeiterkonfig.Mitarbeiterkonfig;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;

//OnClickListener implementieren, um zu reagieren wenn eine View geklickt wurde
//von Vivien Stumpe, 01.04.16
//OnItemClickListener implementieren, um auf einen Klick in der ListView reagieren zu können
//von Vivien Stumpe, 09.04.16
public class Einstellungen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //von Vivien Stumpe, 09.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_einstellungen;
    private ListView listview_einstellungen;
    private MyAdapter myadapter_einstellungen;
    private int[] drawer_icons_einstellungen={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    //TextView-Variablen für die TextViews in der Einstellungen Activity
    //von Vivien Stumpe, 14.04.16
    // ergänzt von Vivien Stumpe, 25.04.16 - Stammdaten
    private TextView txtv_mitarbeiter;
    private TextView txtv_löschen;
    private LinearLayout lnl_buttons;
    private TextView txtv_stammdaten;

    /* von Vivien Stumpe, 18.04.16
    Buttonvariablen für die Buttons Abbrechen und Löschen
     */
    private Button btn_loeschen;
    private Button btn_abbrechen;
    private FalleingabeDataSource dataSource;
    private GlobaleDaten mfall;
    private ImageView img_ani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        //von Vivien Stumpe, 09.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_einstellungen=(DrawerLayout) findViewById(R.id.drawerLayout_Einstellungen);
        listview_einstellungen=(ListView) findViewById(R.id.listview_einstellungen);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_einstellungen=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_einstellungen);
        listview_einstellungen.setAdapter(myadapter_einstellungen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_einstellungen.setOnItemClickListener(this);

        /*von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_einstellungen, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_einstellungen.addDrawerListener(actionbardrawertoggle);

       //Zuweisen der Button-Variablen zu den Buttons in der Activity
        //von Vivien Stumpe, 14.04.16
        // ergänzt von Vivien Stumpe, 25.04.16 - Stammdaten
        txtv_mitarbeiter = (TextView)findViewById(R.id.txtv_einst_mitarbeiter);
        txtv_löschen = (TextView)findViewById(R.id.txtv_einst_loeschen);
        txtv_stammdaten = (TextView)findViewById(R.id.txtv_einst_stammdaten);
        lnl_buttons = (LinearLayout)findViewById(R.id.lnl_einst_buttons);
        /* Wenn es das LinearLayout gibt
        Wird es beim Starten der Aktivität ausgeblendet
         */
        if (lnl_buttons != null) {
            lnl_buttons.setVisibility(View.GONE);
            //View aktualisieren
            lnl_buttons.invalidate();
        }

        // Events abfangen und an den OnClickListener die aktuelle View übergeben
        //von Vivien Stumpe, 14.04.16
        //ergänzt von Vivien Stumpe, 25.04.16 - Stammdaten
        txtv_mitarbeiter.setOnClickListener(this);
        txtv_löschen.setOnClickListener(this);
        txtv_stammdaten.setOnClickListener(this);

        /* von Vivien Stumpe, 18.04.16
        Zuweisen der Buttonvariablen zu den Buttons in der Activity
        Setzen des OnClickListeners, damit auf Klicks reagiert wird
         */
        btn_abbrechen = (Button)findViewById(R.id.btn_einst_abbrechen);
        btn_loeschen = (Button)findViewById(R.id.btn_einst_löschen);
        btn_abbrechen.setOnClickListener(this);
        btn_loeschen.setOnClickListener(this);
        dataSource=new FalleingabeDataSource(this);
        img_ani=(ImageView)findViewById(R.id.imgv_animation_einst);

    }

    //von Vivien Stumpe, 12.04.16
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
        //Ein Intent erzeugen, wenn die TextView geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her und ruft diese auf
        //von Vivien Stumpe, 14.04.16
        //zuletzt geändert von Nathalie Horn, 18.04.16 --> Activity zu Mitarbeiterkonfig
        //ergänzt von Vivien Stumpe, 25.04.16 - Stammdaten
        if (ce == R.id.txtv_einst_mitarbeiter){
            Intent intent = new Intent(Einstellungen.this, Mitarbeiterkonfig.class);
            startActivity(intent);

        }
        if (ce == R.id.txtv_einst_stammdaten){
            Intent intent = new Intent(Einstellungen.this, Stammdaten.class);
            startActivity(intent);

        }
        //wenn "Lokale Daten löschen" ausgewählt wurde
        if (ce == R.id.txtv_einst_loeschen){
            //werden die Buttons eingeblendet
            lnl_buttons.setVisibility(View.VISIBLE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
        }

        /* von Vivien Stumpe, 18.04.16
        Wenn Abbrechen gedrückt wird, verschwinden die Buttons
        Wenn Löschen gedrückt wird, werden die Daten gelöscht
        -> Löschen fehlt noch!
         */
        if (ce == R.id.btn_einst_abbrechen){
            //werden die Buttons ausgeblendet
            lnl_buttons.setVisibility(View.GONE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
        }
        if(ce == R.id.btn_einst_löschen){
            loescheDaten();
        }
    }
    //Von Vivien Stumpe, 09.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }

    // von Vivien Stumpe, 25.04.16 aktualisiert
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if (position == 0) {
            Intent intent = new Intent(getApplicationContext(), Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if (position == 1) {
            Intent intent = new Intent(getApplicationContext(), Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if (position == 2) {
            Intent intent = new Intent(getApplicationContext(), Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if (position == 3) {
            Intent intent = new Intent(getApplicationContext(), Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if (position == 4) {
            Intent intent = new Intent(getApplicationContext(), Impressum.class);
            startActivity(intent);
        }
    }
    /*  von Vivien Stumpe, 31.05.16
        Wird aufgerufen, wenn alle Daten gelöscht werden sollen
        Alle Datensätze aus der DB werden gelöscht
        Zwischenspeicher wird gelöscht
        Alle XML-Dateien werden gelöscht
        Animation, wenn Dateien gelöscht wurden
    */
    private void loescheDaten(){
        /* von Vivien Stumpe, 23.05.16
            Datenbank öffnen
            Alle Datensätze löschen
        */
        dataSource.open();
        dataSource.deleteAll();
        mfall=(GlobaleDaten)getApplication();
        //von Vivien Stumpe, 24.05.16
        //lokal gespeicherte Daten werden auch gelöscht
        mfall.loescheVerb();
        mfall.loescheSan();
        mfall.loescheVer();
        mfall.setVer_vorh_m(false);
        mfall.setVerb_vorh_m(false);
        mfall.setSan_vorh_m(false);
        mfall.loeschePat();
        mfall.loescheVerl();
        mfall.loescheMas();
        mfall.loescheErk();
        mfall.loescheErg();
        mfall.loescheBem();
        mfall.loescheErst();
        mfall.loescheEin();
            /*  von Vivien Stumpe, 31.05.16
                Löschen von allen Dokumenten im MEPA_Dateiordner
             */
        Boolean geloescht;
        XMLCreator xml=new XMLCreator();
        geloescht=xml.deleteAllXML();
            /*  von Vivien Stumpe, 06.06.16
                Foto des Falls wird ebenfalls gelöscht wenn eins existiert
            */
        Ersthelfermassnahmen ersth=new Ersthelfermassnahmen();
        ersth.deleteAllJPG();
        if (geloescht){
            // Die View wird sichtbar -> Animation
            Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            img_ani.startAnimation(in);
            img_ani.setVisibility(View.VISIBLE);
           // Animation out = AnimationUtils.makeOutAnimation(this, true);
            //img_ani.startAnimation(out);
            img_ani.setVisibility(View.INVISIBLE);
        }

        //werden die Buttons ausgeblendet
        lnl_buttons.setVisibility(View.GONE);
        //muss aufgerufen werden, um die View zu aktualisieren
        lnl_buttons.invalidate();
    }
    /*  von Vivien Stumpe, 05.06.16
        zurück zum Erstbefund beim Drücken des Zurückpfeils des Smartphones
    */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Falleingabe.class);
        startActivity(intent);
        finish();
    }
}
