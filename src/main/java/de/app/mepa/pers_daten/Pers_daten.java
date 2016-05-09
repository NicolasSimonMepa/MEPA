//Zuletzt bearbeitet von Indra Marcheel, 09.05.16
//Zuletzt bearbeitet von Vivien Stumpe, 29.04.16
package de.app.mepa.pers_daten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;


public class Pers_daten extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
//von Vivien Stumpe, 10.04.16
//DrawerLayout für das Hamburger Menü
//ListView, die die Einträge des Menüs enthält
//Adapter, der die Einträge der ListView darstellt
//Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_pers_daten;
    private ListView listview_pers_daten;
    private MyAdapter myadapter_pers_daten;
  private int[] drawer_icons_pers_daten={R.drawable.falleingabe,
          R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    /*von Vivien Stumpe, 12.04.16
    Toolbar-variable anlegen, um die Toolbar in den Screen einzubinden
    */
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
    Edittext erzeugen
     */
    private EditText etxt_sonstiges;

    /* von Indra Marcheel, 09.05.2016
    Edittext erzeugen
     */
    private EditText etxt_name_pers_daten;
    private EditText etxt_vorname_pers_daten;
    private EditText etxt_str_pers_daten;

    /* von Vivien Stumpe, 25.04.16
    ImageViews für den Zurück Pfeil in der Aktivität
    ergänzt am 29.04.16 (imgv_menü)
     */
    private ImageView imgv_before;
    private ImageView imgv_menü;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pers_daten);
        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_pers_daten=(DrawerLayout) findViewById(R.id.drawerLayout_pers_daten);
        listview_pers_daten=(ListView) findViewById(R.id.listview_pers_daten);

        myadapter_pers_daten=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_pers_daten);
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
            /* von Vivien Stumpe, 26.04.16
            Bei einem Swipe nach rechts wird die vorherige Aktivität geöffnet
             */
            public void onSwipeRight() {
                Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
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


        /* von Vivien Stumpe, 18.04.16
        ArrayAdapter erzeugen
        Der ArrayAdapter generiert die Listenelemente des Spinners
        Parameter sind die aktuelle Activity, das Layout für den Spinnerinhalt, Array mit den Listenelementen
        Layout für den Spinner geändert am 25.04.16 (VS)
        */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Pers_daten.this,
                R.layout.spinner_layout,zugef);

        adapter.setDropDownViewResource(R.layout.spinner_layout);

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
        etxt_sonstiges=(EditText) findViewById(R.id.etxt_sonstiges_pers_daten);
        etxt_sonstiges.setVisibility(View.GONE);
        //View aktualisieren
        etxt_sonstiges.invalidate();

        etxt_name_pers_daten=(EditText) findViewById(R.id.etxt_name_pers_daten);
        /* von Indra Marcheel, 09.05.2016
        es können nur character eingegeben werden
         */
        etxt_name_pers_daten.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z ]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });

        etxt_vorname_pers_daten=(EditText) findViewById(R.id.etxt_vorname_pers_daten);
         /* von Indra Marcheel, 09.05.2016
        es können nur character eingegeben werden
         */
        etxt_vorname_pers_daten.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z ]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });
         /* von Indra Marcheel, 09.05.2016
        es können nur character, - , und zahlen eingegebne werden 
         */
        etxt_str_pers_daten=(EditText) findViewById(R.id.etxt_str_pers_daten);
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if ( !Character.isLetterOrDigit(source.charAt(i)) & !Character.toString(source.charAt(i)) .equals("-")) {
                        return "";
                    }
                }
                return null;
            }
        };

        etxt_str_pers_daten.setFilters(new InputFilter[]{filter});


        /* von Vivien Stumpe, 22.04.16
            Tastatur wird nicht automatisch beim Öffnen der Aktivität eingeblendet
            sondern erst, wenn ins Eingabefeld geklickt wird
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        /* von Indra Marcheel, 09.05.2016
        maximale eingabelänge wird auf 50 zeichen beschränkt
         */
        etxt_sonstiges.setFilters(new InputFilter[] { new InputFilter.LengthFilter(50) });



        /* von Vivien Stumpe, 25.04.16
        Views in der Akitivität finden und den Variablen zuweisen
        OnClickListener darauf setzen, damit auf Klicks reagiert wird
        ergänzt von Vivien Stumpe, 29.04.16 --> imgv_menü
         */
        imgv_before = (ImageView)findViewById(R.id.imgv_before_pers);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        /*von Vivien Stumpe, 29.04.16
        Der Drawer (Hamburger Menü) ist gesperrt und kann nicht geöffnet werden
        */
        drawerlayout_pers_daten.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    // von Vivien Stumpe, 25.04.16 aktualisiert
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Pers_daten.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Pers_daten.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Pers_daten.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Pers_daten.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Pers_daten.this, Impressum.class);
            startActivity(intent);
        }
        //Menü schließen
        drawerlayout_pers_daten.closeDrawers();
    }

    /* von Vivien Stumpe, 18.04.16
    */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Was soll passieren, wenn das erste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 1:
                // Was soll passieren, wenn das zweite Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 2:
                // Was soll passieren, wenn das dritte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 3:
                // Was soll passieren, wenn das vierte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 4:
                // Was soll passieren, wenn das fünfte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 5:
                // Was soll passieren, wenn das sechste Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet

                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 6:
                // Was soll passieren, wenn das siebte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 7:
                // Was soll passieren, wenn das achte Element gewählt wurde?
                // Sonstiges- Eingabefeld wird ausgeblendet
                etxt_sonstiges.setVisibility(View.GONE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
                break;
            case 8:
                // Wenn sonstiges ausgewählt wurde, wird das Eingabefeld dazu angezeigt
                etxt_sonstiges.setVisibility(View.VISIBLE);
                //View aktualisieren
                etxt_sonstiges.invalidate();
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

        /* von Vivien Stumpe, 29.04.16
        Das Menü wird geöffnet in der Startposition (bei uns links)
         */
        if(ce == R.id.imgv_menu){
            drawerlayout_pers_daten.openDrawer(GravityCompat.START);
        }

    }
}

