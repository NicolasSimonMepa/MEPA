//Zuletzt bearbeitet von Vivien Stumpe am 01.05.16
package de.app.mepa.stammdaten;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;

import de.app.mepa.mepa.MainActivity;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

//OnClickListener implementieren, um bei einem Klick auf ein Steuerelement reagieren zu können
    //von Vivien Stumpe, 01.04.16
public class Stammdaten extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

        //von Vivien Stumpe, 10.04.16
        //DrawerLayout für das Hamburger Menü
        //ListView, die die Einträge des Menüs enthält
        //Adapter, der die Einträge der ListView darstellt
        //Array mit den Icons, die im Menü dargestellt werden sollen
        private DrawerLayout drawerlayout_stammdaten;
        private ListView listview_stammdaten;
        private MyAdapter myadapter_stammdaten;
        private int[] drawer_icons_stammdaten={R.drawable.falleingabe,
                R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Vivien Stumpe, 14.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    /* von Vivien Stumpe, 01.5.16
    Attribute festlegen
     */
    protected String kreisverband;
    protected String ort;
    protected String ortsverein;
    protected String veranstaltung;

    // Hilfsvariablen
    private EditText etxt_kreisverband;
    private EditText etxt_ort;
    private EditText etxt_ortsverein;
    private EditText etxt_veranstaltung;
    private LinearLayout buttons;
    private Button speichern;
    private Button verwerfen;
    private TextWatcher tw;
    private CheckBox cck_mosan;
    private CheckBox cck_hilfs;
    private CheckBox cck_sanw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stammdaten);

        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_stammdaten=(DrawerLayout) findViewById(R.id.drawerLayout_Stammdaten);
        listview_stammdaten=(ListView) findViewById(R.id.listview_stammdaten);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_stammdaten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_stammdaten);
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

        /* von Vivien Stumpe, 01.05.16
        Edittexte in der Activity finden und ansprechen
         */
        buttons=(LinearLayout)findViewById(R.id.lnl_stammdaten_buttons);
        buttons.setVisibility(buttons.GONE);
        etxt_kreisverband=(EditText)findViewById(R.id.etxt_kreisverband);
        etxt_ort=(EditText)findViewById(R.id.etxt_ort);
        etxt_ortsverein=(EditText)findViewById(R.id.etxt_ortsverein);
        etxt_veranstaltung=(EditText)findViewById(R.id.etxt_veranstaltung);
        /* von Vivien Stumpe, 01.05.16
        TextWatcher "beobachtet" den User bei der Eingabe in ein EditText
        Damit entsprechend auf Eingaben reagiert werden kann
         */
        tw=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Buttons speichern & verwerfen sind sichtbar
                buttons.setVisibility(buttons.VISIBLE);
            }
        };
        /* von Vivien Stumpe, 01.05.16
        TextChangedListener muss auf die Eingabefelder gesetzt werden
        damit auch registriert wird, wenn eine Eingabe getätigt wurde
        und entsprechend reagiert werden kann -> siehe TextWatcher
         */
        etxt_kreisverband.addTextChangedListener(tw);
        etxt_ort.addTextChangedListener(tw);
        etxt_ortsverein.addTextChangedListener(tw);
        etxt_veranstaltung.addTextChangedListener(tw);

        speichern=(Button)findViewById(R.id.btn_speichern_stammd);
        verwerfen=(Button)findViewById(R.id.btn_verwerfen_stammd);
        cck_hilfs=(CheckBox)findViewById(R.id.cck_hilfsstelle);
        cck_mosan=(CheckBox)findViewById(R.id.cck_mosan);
        cck_sanw=(CheckBox)findViewById(R.id.cck_sanw);
        speichern.setOnClickListener(this);
        verwerfen.setOnClickListener(this);
        cck_hilfs.setOnClickListener(this);
        cck_mosan.setOnClickListener(this);
        cck_sanw.setOnClickListener(this);

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
        //von Vivien Stumpe, 01.05.16
        //wenn eine der Checkboxen geklickt wurde erscheinen die Buttons
        if(ce == R.id.cck_hilfsstelle){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce == R.id.cck_mosan){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce == R.id.cck_sanw){
            buttons.setVisibility(buttons.VISIBLE);
        }
        if(ce == R.id.btn_speichern_stammd){
            //wenn das Eingabefeld nicht leer ist, werden die Buttons eingeblendet
            //muss noch angepasst werden -> Daten werden ja auch gelöscht?
            // & Vergleich mit bestehenden Daten fehlt
            if (etxt_kreisverband.getText().length()!=0){
                //aktObjekt.setKreisverband(etxt_kreisverband.getText().toString())
                Toast.makeText(this, "Kreisverband gespeichert", Toast.LENGTH_LONG).show();
            }
            if (etxt_ortsverein.getText().length()!=0){
                //aktObjekt.setOrtsverein(etxt_ortsverein.getText().toString())
                Toast.makeText(this, "Ortsverein gespeichert", Toast.LENGTH_LONG).show();
            }
            if (etxt_ort.getText().length()!=0){
                //aktObjekt.setOrt(etxt_ort.getText().toString())
                Toast.makeText(this, "Ort gespeichert", Toast.LENGTH_LONG).show();
            }
            if (etxt_veranstaltung.getText().length()!=0){
                //aktObjekt.setVeranstaltung(etxt_veranstaltung.getText().toString())
                Toast.makeText(this, "Veranstaltung gespeichert", Toast.LENGTH_LONG).show();
            }
            //Buttons wieder ausblenden
            buttons.setVisibility(buttons.GONE);
        }
        if(ce==R.id.btn_verwerfen_stammd){
            //Buttons werden ausgeblendet
            buttons.setVisibility(buttons.GONE);
            //Inhalt des Textfeldes löschen?
        }
    }
        //von Vivien Stumoe, 10.04.16
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
            selectItemFromDrawer(position);
        }
    // von Vivien Stumpe, 25.04.16 aktualisiert
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Stammdaten.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Stammdaten.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Stammdaten.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Stammdaten.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Stammdaten.this, Impressum.class);
            startActivity(intent);
        }
        }
    }
