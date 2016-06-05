//Zuletzt bearbeitet von Vivien Stumpe, 29.05.16
package de.app.mepa.verletzung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.pers_daten.Pers_daten;
import de.app.mepa.upload.Upload;

public class Verletzung extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {
    //Variablen für die Spinner in der Activity erstellen
    //Nicolas Simon, übernommen von Vivien Stumpe, 04.04.16
    private Spinner spin_schaedel_art;
    private Spinner spin_gesicht_art;
    private Spinner spin_hws_art;
    private Spinner spin_brustkorb_art;
    private Spinner spin_bauch_art;
    private Spinner spin_bws_art;
    private Spinner spin_becken_art;
    private Spinner spin_arme_art;
    private Spinner spin_beine_art;
    private Spinner spin_weichteile_art;

    private Spinner spin_schaedel_grad;
    private Spinner spin_gesicht_grad;
    private Spinner spin_hws_grad;
    private Spinner spin_brustkorb_grad;
    private Spinner spin_bauch_grad;
    private Spinner spin_bws_grad;
    private Spinner spin_becken_grad;
    private Spinner spin_arme_grad;
    private Spinner spin_beine_grad;
    private Spinner spin_weichteile_grad;

    private CheckBox cck_prellung_verletzung;
    private CheckBox cck_verbrennung_verletzung;
    private CheckBox cck_wunde_verletzung;
    private CheckBox cck_elektro_verletzung;
    private CheckBox cck_inhalation_verletzung;
    private CheckBox cck_sonstiges_verletzung;

    private GlobaleDaten mfall;

    //String Array erstellen mit den Elementen, die im Dropdown-Menü des Spinners in der Activity ausgewählt werden können
    //Nicolas Simon, übernommen von Vivien Stumpe, 04.04.16
    private String[]art = {"-----", "offen", "geschlossen"};
    private String[]grad = {"-----", "leicht", "mittel", "schwer"};
    //Nicolas Simon, übernommen von Vivien Stumpe, 10.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_verletzung;
    private ListView listview_verletzung;
    private MyAdapter myadapter_verletzung;
    private int[] drawer_icons_verletzung={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    /*Nicolas Simon, übernommen von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ImageView imgv_before;
    private ImageView imgv_menü;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verletzung);
        //ArrayAdapter erzeugen
        //Der ArrayAdapter generiert die Listenelemente des Spinners
        //Parameter sind die aktuelle Activity, Systemressource, Array mit den Listenelementen
        //Nicolas Simon, übernommen von Vivien Stumpe, 04.04.16
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Verletzung.this,
                R.layout.spinner_layout,art);

        adapter.setDropDownViewResource(R.layout.spinner_layout);

        ArrayAdapter<String> adapter_grad = new ArrayAdapter<String>(Verletzung.this,
                R.layout.spinner_layout,grad);

        adapter_grad.setDropDownViewResource(R.layout.spinner_layout);

        //Verknüpfung der Spinnervariable zum Spinner in der Activity herstellen
        //SpinnerAdapter dem Spinner zuweisen damit die Elemente ind er Activity angezeigt werden
        //setOnItemSelectedListener implementieren, damit mit dem ausgewählten Eintrag auch gearbeitet werden kann
        //Nicolas Simon, übernommen von Vivien Stumpe, 04.04.16
        spin_schaedel_art = (Spinner)findViewById(R.id.spin_schaedel_art);
        spin_schaedel_art.setAdapter(adapter);
        spin_schaedel_art.setOnItemSelectedListener(this);

        spin_gesicht_art = (Spinner)findViewById(R.id.spin_gesicht_art);
        spin_gesicht_art.setAdapter(adapter);
        spin_gesicht_art.setOnItemSelectedListener(this);

        spin_hws_art = (Spinner)findViewById(R.id.spin_hws_art);
        spin_hws_art.setAdapter(adapter);
        spin_hws_art.setOnItemSelectedListener(this);

        spin_brustkorb_art = (Spinner)findViewById(R.id.spin_brustkorb_art);
        spin_brustkorb_art.setAdapter(adapter);
        spin_brustkorb_art.setOnItemSelectedListener(this);

        spin_bauch_art = (Spinner)findViewById(R.id.spin_bauch_art);
        spin_bauch_art.setAdapter(adapter);
        spin_bauch_art.setOnItemSelectedListener(this);

        spin_bws_art = (Spinner)findViewById(R.id.spin_bws_art);
        spin_bws_art.setAdapter(adapter);
        spin_bws_art.setOnItemSelectedListener(this);

        spin_becken_art = (Spinner)findViewById(R.id.spin_becken_art);
        spin_becken_art.setAdapter(adapter);
        spin_becken_art.setOnItemSelectedListener(this);

        spin_arme_art = (Spinner)findViewById(R.id.spin_arme_art);
        spin_arme_art.setAdapter(adapter);
        spin_arme_art.setOnItemSelectedListener(this);

        spin_beine_art = (Spinner)findViewById(R.id.spin_beine_art);
        spin_beine_art.setAdapter(adapter);
        spin_beine_art.setOnItemSelectedListener(this);

        spin_weichteile_art = (Spinner)findViewById(R.id.spin_weichteile_art);
        spin_weichteile_art.setAdapter(adapter);
        spin_weichteile_art.setOnItemSelectedListener(this);

        spin_schaedel_grad = (Spinner)findViewById(R.id.spin_schaedel_grad);
        spin_schaedel_grad.setAdapter(adapter_grad);
        spin_schaedel_grad.setOnItemSelectedListener(this);

        spin_gesicht_grad = (Spinner)findViewById(R.id.spin_gesicht_grad);
        spin_gesicht_grad.setAdapter(adapter_grad);
        spin_gesicht_grad.setOnItemSelectedListener(this);

        spin_hws_grad = (Spinner)findViewById(R.id.spin_hws_grad);
        spin_hws_grad.setAdapter(adapter_grad);
        spin_hws_grad.setOnItemSelectedListener(this);

        spin_brustkorb_grad = (Spinner)findViewById(R.id.spin_brustkorb_grad);
        spin_brustkorb_grad.setAdapter(adapter_grad);
        spin_brustkorb_grad.setOnItemSelectedListener(this);

        spin_bauch_grad = (Spinner)findViewById(R.id.spin_bauch_grad);
        spin_bauch_grad.setAdapter(adapter_grad);
        spin_bauch_grad.setOnItemSelectedListener(this);

        spin_bws_grad = (Spinner)findViewById(R.id.spin_bws_grad);
        spin_bws_grad.setAdapter(adapter_grad);
        spin_bws_grad.setOnItemSelectedListener(this);

        spin_becken_grad = (Spinner)findViewById(R.id.spin_becken_grad);
        spin_becken_grad.setAdapter(adapter_grad);
        spin_becken_grad.setOnItemSelectedListener(this);

        spin_arme_grad = (Spinner)findViewById(R.id.spin_arme_grad);
        spin_arme_grad.setAdapter(adapter_grad);
        spin_arme_grad.setOnItemSelectedListener(this);

        spin_beine_grad = (Spinner)findViewById(R.id.spin_beine_grad);
        spin_beine_grad.setAdapter(adapter_grad);
        spin_beine_grad.setOnItemSelectedListener(this);

        spin_weichteile_grad = (Spinner)findViewById(R.id.spin_weichteile_grad);
        spin_weichteile_grad.setAdapter(adapter_grad);
        spin_weichteile_grad.setOnItemSelectedListener(this);

        cck_prellung_verletzung=(CheckBox)findViewById(R.id.cck_prellung_verletzung);
        cck_verbrennung_verletzung=(CheckBox)findViewById(R.id.cck_verbrennung_verletzung);
        cck_wunde_verletzung=(CheckBox)findViewById(R.id.cck_wunde_verletzung);
        cck_elektro_verletzung=(CheckBox)findViewById(R.id.cck_elektro_verletzung);
        cck_inhalation_verletzung=(CheckBox)findViewById(R.id.cck_inhalation_verletzung);
        cck_sonstiges_verletzung=(CheckBox)findViewById(R.id.cck_sonstiges_verletzung);

        cck_prellung_verletzung.setOnClickListener(this);
        cck_verbrennung_verletzung.setOnClickListener(this);
        cck_wunde_verletzung.setOnClickListener(this);
        cck_elektro_verletzung.setOnClickListener(this);
        cck_inhalation_verletzung.setOnClickListener(this);
        cck_sonstiges_verletzung.setOnClickListener(this);

        setWerte();
        //Nicolas Simon, übernommen von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_verletzung=(DrawerLayout) findViewById(R.id.drawerLayout_Verletzung);
        listview_verletzung=(ListView) findViewById(R.id.listview_verletzung);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_verletzung=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_verletzung);
        listview_verletzung.setAdapter(myadapter_verletzung);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_verletzung.setOnItemClickListener(this);

        //Nicolas Simon, übernommen von Vivien Stumpe, 11.04.16
        //Verbindung der View zur Scrollview in der Aktivität
        view=(View) findViewById(R.id.scrV_verletzung);
        //OnTouchListener auf die View setzen

        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            //Bei einem Swipe nach links wird die nächste Aktivität geöffnet
            public void onSwipeLeft() {
                Intent intent = new Intent(Verletzung.this, Erkrankung.class);
                startActivity(intent);
                finish();
            }

            public void onSwipeRight() {
                drawerlayout_verletzung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Verletzung.this, Pers_daten.class);
                startActivity(intent);
                finish();
            }
        });

                /*Nicolas Simon, übernommen von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_verletzung);
       // imgv_next = (ImageView)findViewById(R.id.imgv_next_verletzung);
        imgv_before.setOnClickListener(this);
       // imgv_next.setOnClickListener(this);

        drawerlayout_verletzung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        /*  von Vivien Stumpe, 29.05.16
            Felder sperren, wenn ein Fall gewählt wurde
         */
        sperreFelder();
    }


    //Prozedur, die aufgerufen wird wenn ein Listenelement im Spinner ausgewählt wurde
    //Nicolas Simon, übernommen von Vivien Stumpe, 06.04.16
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //Nicolas Simon, übernommen von Vivien Stumpe, 15.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
        finish();
    }
    //Nicolas Simon, übernommen von Vivien Stumpe, 15.04.16
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
        //Menü schließen
        drawerlayout_verletzung.closeDrawers();
    }
    @Override
    public void onClick(View v) {
        /* Nicolas Simon, übernommen von Vivien Stumpe, 25.04.16
        Deklaration und Initialisierung einer Hilfsvariablen (clicked element),
        die die ID der geklickten View erhält
        */
        int ce = v.getId();

        /* Nicolas Simon, übernommen von Vivien Stumpe, 25.04.16
        Ein Intent erzeugen, wenn die bestimmte ImageView geklickt wurde
        Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        Aufrufen der Activity mittels Intent
        */
        if (ce == R.id.imgv_before_verletzung) {
            Intent intent = new Intent(Verletzung.this, Falleingabe.class);
            startActivity(intent);
        }
        /* Nicolas Simon, übernommen von Vivien Stumpe, 30.04.16
        Das Menü wird geöffnet in der Startposition (bei uns links)
         */
       if(ce == R.id.imgv_menu){
         drawerlayout_verletzung.openDrawer(GravityCompat.START);
       }
    }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        if(!mfall.getFallAusgewaehlt()){
            speichereEingaben();
        }
    }
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        if (cck_prellung_verletzung.isChecked()){
            mfall.setVerl_prellung_verletzung(1);
        }
        else
            mfall.setVerl_prellung_verletzung(0);
        if (cck_verbrennung_verletzung.isChecked()){
            mfall.setVerl_verbrennung(1);
        }
        else
            mfall.setVerl_verbrennung(0);
        if (cck_wunde_verletzung.isChecked()){
            mfall.setVerl_wunde_verletzung(1);
        }
        else
            mfall.setVerl_wunde_verletzung(0);
        if (cck_elektro_verletzung.isChecked()){
            mfall.setVerl_elektrounfall(1);
        }
        else
            mfall.setVerl_elektrounfall(0);
        if (cck_inhalation_verletzung.isChecked()){
            mfall.setVerl_inhalationstrauma(1);
        }
        else
            mfall.setVerl_inhalationstrauma(0);
        if (cck_sonstiges_verletzung.isChecked()){
            mfall.setVerl_sonstiges(1);
        }
        else
            mfall.setVerl_sonstiges(0);
        if((spin_schaedel_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_schaedel_art("offen");
        }
        if((spin_schaedel_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_schaedel_art(null);
        }
        if((spin_schaedel_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_schaedel_art("geschlossen");
        }
        if((spin_schaedel_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_schaedel_grad("leicht");
        }
        if((spin_schaedel_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_schaedel_grad(null);
        }
        if((spin_schaedel_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_schaedel_grad("mittel");
        }
        if((spin_schaedel_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_schaedel_grad("schwer");
        }
        if((spin_gesicht_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_gesicht_art("offen");
        }
        if((spin_gesicht_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_gesicht_art(null);
        }
        if((spin_gesicht_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_gesicht_art("geschlossen");
        }
        if((spin_gesicht_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_gesicht_grad("leicht");
        }
        if((spin_gesicht_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_gesicht_grad(null);
        }
        if((spin_gesicht_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_gesicht_grad("mittel");
        }
        if((spin_gesicht_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_gesicht_grad("schwer");
        }
        if((spin_hws_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_hws_art("offen");
        }
        if((spin_gesicht_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_gesicht_grad(null);
        }
        if((spin_hws_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_hws_art("geschlossen");
        }
        if((spin_hws_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_hws_grad("leicht");
        }
        if((spin_hws_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_hws_grad(null);
        }
        if((spin_hws_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_hws_grad("mittel");
        }
        if((spin_hws_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_hws_grad("schwer");
        }
        if((spin_brustkorb_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_brustkorb_art("offen");
        }
        if((spin_brustkorb_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_brustkorb_art(null);
        }
        if((spin_brustkorb_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_brustkorb_art("geschlossen");
        }
        if((spin_brustkorb_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_brustkorb_grad("leicht");
        }
        if((spin_brustkorb_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_brustkorb_grad(null);
        }
        if((spin_brustkorb_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_brustkorb_grad("mittel");
        }
        if((spin_brustkorb_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_brustkorb_grad("schwer");
        }
        if((spin_bauch_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_bauch_art("offen");
        }
        if((spin_bauch_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_bauch_art(null);
        }
        if((spin_bauch_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_bauch_art("geschlossen");
        }
        if((spin_bauch_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_bauch_grad("leicht");
        }
        if((spin_bauch_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_bauch_grad(null);
        }
        if((spin_bauch_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_bauch_grad("mittel");
        }
        if((spin_bauch_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_bauch_grad("schwer");
        }
        if((spin_bws_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_bws_lws_art("offen");
        }
        if((spin_bws_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_bws_lws_art(null);
        }
        if((spin_bws_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_bws_lws_art("geschlossen");
        }
        if((spin_bws_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_bws_lws_grad("leicht");
        }
        if((spin_bws_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_bws_lws_grad(null);
        }
        if((spin_bws_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_bws_lws_grad("mittel");
        }
        if((spin_bws_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_bws_lws_grad("schwer");
        }
        if((spin_becken_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_becken_art("offen");
        }
        if((spin_becken_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_becken_art(null);
        }
        if((spin_becken_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_becken_art("geschlossen");
        }
        if((spin_becken_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_becken_grad("leicht");
        }
        if((spin_becken_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_becken_grad(null);
        }
        if((spin_becken_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_becken_grad("mittel");
        }
        if((spin_becken_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_becken_grad("schwer");
        }
        if((spin_arme_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_arme_art("offen");
        }
        if((spin_arme_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_arme_art(null);
        }
        if((spin_arme_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_arme_art("geschlossen");
        }
        if((spin_arme_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_arme_grad("leicht");
        }
        if((spin_arme_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_arme_grad(null);
        }
        if((spin_arme_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_arme_grad("mittel");
        }
        if((spin_arme_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_arme_grad("schwer");
        }
        if((spin_beine_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_beine_art("offen");
        }
        if((spin_beine_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_beine_art(null);
        }
        if((spin_beine_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_beine_art("geschlossen");
        }
        if((spin_beine_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_beine_grad("leicht");
        }
        if((spin_beine_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_beine_grad(null);
        }
        if((spin_beine_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_beine_grad("mittel");
        }
        if((spin_beine_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_beine_grad("schwer");
        }
        if((spin_weichteile_art.getSelectedItem().toString().equals("offen"))){
            mfall.setVerl_weichteile_art("offen");
        }
        if((spin_weichteile_art.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_weichteile_art(null);
        }
        if((spin_weichteile_art.getSelectedItem().toString().equals("geschlossen"))){
            mfall.setVerl_weichteile_art("geschlossen");
        }
        if((spin_weichteile_grad.getSelectedItem().toString().equals("leicht"))){
            mfall.setVerl_weichteile_grad("leicht");
        }
        if((spin_weichteile_grad.getSelectedItem().toString().equals("-----"))){
            mfall.setVerl_weichteile_grad(null);
        }
        if((spin_weichteile_grad.getSelectedItem().toString().equals("mittel"))){
            mfall.setVerl_weichteile_grad("mittel");
        }
        if((spin_weichteile_grad.getSelectedItem().toString().equals("schwer"))){
            mfall.setVerl_weichteile_grad("schwer");
        }
    }
    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if(mfall.getVerl_prellung_verletzung()!=null) {
            if (mfall.getVerl_prellung_verletzung() == 1) {
                cck_prellung_verletzung.setChecked(true);
            }
        }
        if(mfall.getVerl_verbrennung()!=null) {
            if (mfall.getVerl_verbrennung() == 1) {
                cck_verbrennung_verletzung.setChecked(true);
            }
        }
        if(mfall.getVerl_wunde_verletzung()!=null) {
            if (mfall.getVerl_wunde_verletzung() == 1) {
                cck_wunde_verletzung.setChecked(true);
            }
        }
        if(mfall.getVerl_elektrounfall()!=null) {
            if (mfall.getVerl_elektrounfall() == 1) {
                cck_elektro_verletzung.setChecked(true);
            }
        }
        if(mfall.getVerl_inhalationstrauma()!=null) {
            if (mfall.getVerl_inhalationstrauma() == 1) {
                cck_inhalation_verletzung.setChecked(true);
            }
        }
        if(mfall.getVerl_sonstiges()!=null) {
            if (mfall.getVerl_sonstiges() == 1) {
                cck_sonstiges_verletzung.setChecked(true);
            }
        }
        if((mfall.getVerl_schaedel_art()!=null)){
            if(mfall.getVerl_schaedel_art().equals("offen")){
                spin_schaedel_art.setSelection(1);
            }
            if(mfall.getVerl_schaedel_art().equals("geschlossen")){
                spin_schaedel_art.setSelection(2);
            }
        }
        if((mfall.getVerl_schaedel_grad()!=null)){
            if(mfall.getVerl_schaedel_grad().equals("leicht")){
                spin_schaedel_grad.setSelection(1);
            }
            if(mfall.getVerl_schaedel_grad().equals("mittel")){
                spin_schaedel_grad.setSelection(2);
            }
            if(mfall.getVerl_schaedel_grad().equals("schwer")){
                spin_schaedel_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_gesicht_art()!=null)){
            if(mfall.getVerl_gesicht_art().equals("offen")){
                spin_gesicht_art.setSelection(1);
            }
            if(mfall.getVerl_gesicht_art().equals("geschlossen")){
                spin_gesicht_art.setSelection(2);
            }
        }
        if((mfall.getVerl_gesicht_grad()!=null)){
            if(mfall.getVerl_gesicht_grad().equals("leicht")){
                spin_gesicht_grad.setSelection(1);
            }
            if(mfall.getVerl_gesicht_grad().equals("mittel")){
                spin_gesicht_grad.setSelection(2);
            }
            if(mfall.getVerl_gesicht_grad().equals("schwer")){
                spin_gesicht_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_hws_art()!=null)){
            if(mfall.getVerl_hws_art().equals("offen")){
                spin_hws_art.setSelection(1);
            }
            if(mfall.getVerl_hws_art().equals("geschlossen")){
                spin_hws_art.setSelection(2);
            }
        }
        if((mfall.getVerl_hws_grad()!=null)){
            if(mfall.getVerl_hws_grad().equals("leicht")){
                spin_hws_grad.setSelection(1);
            }
            if(mfall.getVerl_hws_grad().equals("mittel")){
                spin_hws_grad.setSelection(2);
            }
            if(mfall.getVerl_hws_grad().equals("schwer")){
                spin_hws_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_brustkorb_art()!=null)){
            if(mfall.getVerl_brustkorb_art().equals("offen")){
                spin_brustkorb_art.setSelection(1);
            }
            if(mfall.getVerl_brustkorb_art().equals("geschlossen")){
                spin_brustkorb_art.setSelection(2);
            }
        }
        if((mfall.getVerl_brustkorb_grad()!=null)){
            if(mfall.getVerl_brustkorb_grad().equals("leicht")){
                spin_brustkorb_grad.setSelection(1);
            }
            if(mfall.getVerl_brustkorb_grad().equals("mittel")){
                spin_brustkorb_grad.setSelection(2);
            }
            if(mfall.getVerl_brustkorb_grad().equals("schwer")){
                spin_brustkorb_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_bauch_art()!=null)){
            if(mfall.getVerl_bauch_art().equals("offen")){
                spin_bauch_art.setSelection(1);
            }
            if(mfall.getVerl_bauch_art().equals("geschlossen")){
                spin_bauch_art.setSelection(2);
            }
        }
        if((mfall.getVerl_bauch_grad()!=null)){
            if(mfall.getVerl_bauch_grad().equals("leicht")){
                spin_bauch_grad.setSelection(1);
            }
            if(mfall.getVerl_bauch_grad().equals("mittel")){
                spin_bauch_grad.setSelection(2);
            }
            if(mfall.getVerl_bauch_grad().equals("schwer")){
                spin_bauch_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_bws_lws_art()!=null)){
            if(mfall.getVerl_bws_lws_art().equals("offen")){
                spin_bws_art.setSelection(1);
            }
            if(mfall.getVerl_bws_lws_art().equals("geschlossen")){
                spin_bws_art.setSelection(2);
            }
        }
        if((mfall.getVerl_bws_lws_grad()!=null)){
            if(mfall.getVerl_bws_lws_grad().equals("leicht")){
                spin_bws_grad.setSelection(1);
            }
            if(mfall.getVerl_bws_lws_grad().equals("mittel")){
                spin_bws_grad.setSelection(2);
            }
            if(mfall.getVerl_bws_lws_grad().equals("schwer")){
                spin_bws_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_becken_art()!=null)){
            if(mfall.getVerl_becken_art().equals("offen")){
                spin_becken_art.setSelection(1);
            }
            if(mfall.getVerl_becken_art().equals("geschlossen")){
                spin_becken_art.setSelection(2);
            }
        }
        if((mfall.getVerl_becken_grad()!=null)){
            if(mfall.getVerl_becken_grad().equals("leicht")){
                spin_becken_grad.setSelection(1);
            }
            if(mfall.getVerl_becken_grad().equals("mittel")){
                spin_becken_grad.setSelection(2);
            }
            if(mfall.getVerl_becken_grad().equals("schwer")){
                spin_becken_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_arme_art()!=null)){
            if(mfall.getVerl_arme_art().equals("offen")){
                spin_arme_art.setSelection(1);
            }
            if(mfall.getVerl_arme_art().equals("geschlossen")){
                spin_arme_art.setSelection(2);
            }
        }
        if((mfall.getVerl_arme_grad()!=null)){
            if(mfall.getVerl_arme_grad().equals("leicht")){
                spin_arme_grad.setSelection(1);
            }
            if(mfall.getVerl_arme_grad().equals("mittel")){
                spin_arme_grad.setSelection(2);
            }
            if(mfall.getVerl_arme_grad().equals("schwer")){
                spin_arme_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_beine_art()!=null)){
            if(mfall.getVerl_beine_art().equals("offen")){
                spin_beine_art.setSelection(1);
            }
            if(mfall.getVerl_beine_art().equals("geschlossen")){
                spin_beine_art.setSelection(2);
            }
        }
        if((mfall.getVerl_beine_grad()!=null)){
            if(mfall.getVerl_beine_grad().equals("leicht")){
                spin_beine_grad.setSelection(1);
            }
            if(mfall.getVerl_beine_grad().equals("mittel")){
                spin_beine_grad.setSelection(2);
            }
            if(mfall.getVerl_beine_grad().equals("schwer")){
                spin_beine_grad.setSelection(3);
            }
        }
        if((mfall.getVerl_weichteile_art()!=null)){
            if(mfall.getVerl_weichteile_art().equals("offen")){
                spin_weichteile_art.setSelection(1);
            }
            if(mfall.getVerl_weichteile_art().equals("geschlossen")){
                spin_weichteile_art.setSelection(2);
            }
        }
        if((mfall.getVerl_weichteile_grad()!=null)){
            if(mfall.getVerl_weichteile_grad().equals("leicht")){
                spin_weichteile_grad.setSelection(1);
            }
            if(mfall.getVerl_weichteile_grad().equals("mittel")){
                spin_weichteile_grad.setSelection(2);
            }
            if(mfall.getVerl_weichteile_grad().equals("schwer")){
                spin_weichteile_grad.setSelection(3);
            }
        }
    }
    /*  von Vivien Stumpe, 29.05.16
    Prozedur, die alle Felder sperrt,
    wenn ein Fall in der Fallübersicht ausgewählt wurde
 */
    public void sperreFelder(){
        if(mfall.getFallAusgewaehlt()) {
            cck_elektro_verletzung.setEnabled(false);
            cck_inhalation_verletzung.setEnabled(false);
            cck_prellung_verletzung.setEnabled(false);
            cck_sonstiges_verletzung.setEnabled(false);
            cck_verbrennung_verletzung.setEnabled(false);
            cck_wunde_verletzung.setEnabled(false);
            spin_schaedel_art.setEnabled(false);
            spin_arme_art.setEnabled(false);
            spin_bauch_art.setEnabled(false);
            spin_becken_art.setEnabled(false);
            spin_beine_art.setEnabled(false);
            spin_brustkorb_art.setEnabled(false);
            spin_bws_art.setEnabled(false);
            spin_gesicht_art.setEnabled(false);
            spin_hws_art.setEnabled(false);
            spin_weichteile_art.setEnabled(false);
            spin_schaedel_grad.setEnabled(false);
            spin_arme_grad.setEnabled(false);
            spin_bauch_grad.setEnabled(false);
            spin_becken_grad.setEnabled(false);
            spin_beine_grad.setEnabled(false);
            spin_brustkorb_grad.setEnabled(false);
            spin_bws_grad.setEnabled(false);
            spin_gesicht_grad.setEnabled(false);
            spin_hws_grad.setEnabled(false);
            spin_weichteile_grad.setEnabled(false);
        }
    }
    /*  von Vivien Stumpe, 05.06.16
        zurück zu den persönlichen Daten beim Drücken des Zurückpfeils des Smartphones
    */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Pers_daten.class);
        startActivity(intent);
        finish();
    }
}
