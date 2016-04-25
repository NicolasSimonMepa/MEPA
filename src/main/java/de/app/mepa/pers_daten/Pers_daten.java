//Zuletzt bearbeitet von Vivien Stumpe, 25.04.16
package de.app.mepa.pers_daten;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.stammdaten.Stammdaten;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.OnSwipeTouchListener;


public class Pers_daten extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
//von Vivien Stumpe, 10.04.16
//DrawerLayout für das Hamburger Menü
//ListView, die die Einträge des Menüs enthält
//Adapter, der die Einträge der ListView darstellt
//Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_pers_daten;
    private ListView listview_pers_daten;
    private MyAdapter myadapter_pers_daten;
  private int[] drawer_icons_pers_daten={R.drawable.mepa_icon, R.drawable.einstellungen, R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.impressum, R.drawable.stammdaten};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    /*von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    /* von Vivien Stumpe, 18.04.16
    Variablen für die Spinner in der Activity erstellen

    */
    private Spinner spin_zugef;

    /* von Vivien Stumpe, 18.04.16
    String Array erstellen mit den Elementen, die im Dropdown-Menü des Spinners in der Activity ausgewählt werden können
    */
    private String[]zugef = {"----", "Polizei", "RTW/KTW", "San-Team", "Security",
            "Angehörige", "Selbst", "Passanten", "Sonstiges"};

    /* von Vivien Stumpe, 18.04.16
    TableRow erzeugen
     */
    private TableRow tblr_sonstiges_zugef;

    /* von Vivien Stumpe, 25.04.16
    ImageViews für die Zurück und Vor Bilder in der Aktivität
     */
    private ImageView imgv_before;
    private ImageView imgv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_daten);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_pers_daten=(DrawerLayout) findViewById(R.id.drawerLayout_pers_daten);
        listview_pers_daten=(ListView) findViewById(R.id.listview_pers_daten);

        myadapter_pers_daten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav), drawer_icons_pers_daten);
        listview_pers_daten.setAdapter(myadapter_pers_daten);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_pers_daten.setOnItemClickListener(this);

        //von Vivien Stumpe, 11.04.16
        //Verbindung der View zur Scrollview in der Aktivität
    view=(View) findViewById(R.id.scrV_pers_daten);
        //OnTouchListener auf die View setzen
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            //Bei einem Swipe nach links wird die nächste Aktivität geöffnet
            public void onSwipeLeft() {
            Intent intent = new Intent(Pers_daten.this, Verletzung.class);
               startActivity(intent);
        }
        });

        /*von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle=new ActionBarDrawerToggle(this, drawerlayout_pers_daten, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_pers_daten.addDrawerListener(actionbardrawertoggle);

        /* von Vivien Stumpe, 18.04.16
        ArrayAdapter erzeugen
        Der ArrayAdapter generiert die Listenelemente des Spinners
        Parameter sind die aktuelle Activity, Systemressource, Array mit den Listenelementen
        */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Pers_daten.this,
                android.R.layout.simple_spinner_item,zugef);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* von Vivien Stumpe, 18.04.16
        Verknüpfung der Spinnervariable zum Spinner in der Activity herstellen
        SpinnerAdapter dem Spinner zuweisen damit die Elemente in der Activity angezeigt werden
        setOnItemSelectedListener implementieren, damit mit dem ausgewählten Eintrag auch gearbeitet werden kann
        */
        spin_zugef = (Spinner)findViewById(R.id.spin_zugefuehrt_pers_daten);
        spin_zugef.setAdapter(adapter);
        spin_zugef.setOnItemSelectedListener(this);

        /* von Vivien Stumpe, 18.04.16
        TableRow Variable der View zuordnen
        TableRow in der Activity ausblenden --> soll erst eingeblendet werden, wenn Sonstiges im Spinner gewählt wurde
         */
        tblr_sonstiges_zugef=(TableRow) findViewById(R.id.tblr_sonstiges_pers_daten);
        tblr_sonstiges_zugef.setVisibility(View.GONE);
        //View aktualisieren
        tblr_sonstiges_zugef.invalidate();

        /* von Vivien Stumpe, 22.04.16
            Tastatur wird nicht automatisch beim Öffnen der Aktivität eingeblendet
            sondern erst, wenn ins Eingabefeld geklickt wird
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        /* von Vivien Stumpe, 25.04.16
        Views in der Akitivität finden und den Variablen zuweisen
        OnClickListener darauf setzen, damit auf Klicks reagiert wird
         */
        imgv_before = (ImageView)findViewById(R.id.imgv_before_pers);
        imgv_next = (ImageView)findViewById(R.id.imgv_next_pers);
        imgv_before.setOnClickListener(this);
        imgv_next.setOnClickListener(this);


    }

    //von Vivien Stumpe, 12.04.16
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
            Intent intent = new Intent(Pers_daten.this, MainActivity.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Pers_daten.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Pers_daten.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Pers_daten.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das sechste Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==5) {
            Intent intent = new Intent(Pers_daten.this, Impressum.class);
            startActivity(intent);
        }
        //Wenn das siebte Element im Menü geklickt wurde, werden die Stammdaten geöffnet
        if(position==6) {
            Intent intent = new Intent(Pers_daten.this, Stammdaten.class);
            startActivity(intent);
        }
    }

    /* von Vivien Stumpe, 18.04.16
    */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 3:
                // Was soll passieren, wenn das vierte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 4:
                // Was soll passieren, wenn das fünfte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 5:
                // Was soll passieren, wenn das sechste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet

                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 6:
                // Was soll passieren, wenn das siebte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 7:
                // Was soll passieren, wenn das achte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                tblr_sonstiges_zugef.setVisibility(View.GONE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
            case 8:
                // Wenn sonstiges ausgewählt wurde, wird das Eingabefeld dazu angezeigt
                tblr_sonstiges_zugef.setVisibility(View.VISIBLE);
                //View aktualisieren
                tblr_sonstiges_zugef.invalidate();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        /* von Vivien Stumpe, 25.04.16
        Deklaration und Initialisierung einer Hilfsvariablen (clicked element),
        die die ID der geklickten View erhält
        */
        int ce = v.getId();

        /* von Vivien Stumpe, 25.04.16
        Ein Intent erzeugen, wenn die bestimmte ImageView geklickt wurde
        Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        Aufrufen der Activity mittels Intent
        */
        if(ce == R.id.imgv_before_pers){
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_next_pers){
            Intent intent = new Intent(Pers_daten.this, Verletzung.class);
            startActivity(intent);
        }
    }
}
