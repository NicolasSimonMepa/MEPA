//Zuletzt geändert von Vivien Stumpe am 25.04.16
package de.app.mepa.einstellungen;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.app.mepa.MyAdapter;
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
            Toast.makeText(this, "Daten werden gelöscht", Toast.LENGTH_SHORT).show();
            //werden die Buttons ausgeblendet
            lnl_buttons.setVisibility(View.GONE);
            //muss aufgerufen werden, um die View zu aktualisieren
            lnl_buttons.invalidate();
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
        if(position==0) {
            Intent intent = new Intent(Einstellungen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Einstellungen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Einstellungen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Einstellungen.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Einstellungen.this, Impressum.class);
            startActivity(intent);
        }
    }
}
