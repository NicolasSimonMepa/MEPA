/**
 * Created by Nathalie on 18.04.2016.
 */
// Zuletzt bearbeitet von Vivien Stumpe, 20.05.16
package de.app.mepa.mitarbeiterkonfig;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
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
    private int[] drawer_icons_mitarbeiterkonfig={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    //ActionBarDrawerToggle und Toolbar anlegen
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    //von Nathalie Horn 02.05.16
    //Attribute festlegen
    protected String vorname;
    protected String nachname;

    //Buttonvariablen für die Buttons Speichern und Verwerfen
    private LinearLayout lnl_buttons;
    private Button btn_speichern;
    private Button btn_verwerfen;
    private EditText etxt_mitarbeiter_name;
    private EditText etxt_mitarbeiter_vorname;
    private TextWatcher textWatcher;

    private GlobaleDaten mfall;
    /* von Vivien Stumpe, 20.05.16
Timer deklarieren mit der Zeit DELAY in Millisekunden
 */
    private Timer timer = new Timer();
    private final long DELAY = 2000; // in ms


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitarbeiterkonfig);

        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_mitarbeiterkonfig=(DrawerLayout) findViewById(R.id.drawerLayout_Mitarbeiterkonfig);
        listview_mitarbeiterkonfig=(ListView) findViewById(R.id.listview_mitarbeiterkonfig);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_mitarbeiterkonfig=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_mitarbeiterkonfig);
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

        /* von Nathalie Horn, 02.05.16
        Edittexte und Buttons in der Activity finden und ansprechen
         */
        lnl_buttons=(LinearLayout)findViewById(R.id.lnl_buttons);
        lnl_buttons.setVisibility(lnl_buttons.GONE);
        etxt_mitarbeiter_name=(EditText)findViewById(R.id.etxt_mitarbeiter_name);
        etxt_mitarbeiter_vorname=(EditText)findViewById(R.id.etxt_mitarbeiter_vorname);


         /* von Indra Marcheel, 18.05.2016
        es können nur character eingegeben werden
         */
        mfall=(GlobaleDaten)getApplication();
        if(mfall.getSan_vorname()==null) {
            etxt_mitarbeiter_vorname.setFilters(new InputFilter[]{
                    new InputFilter() {
                        public CharSequence filter(CharSequence src, int start,
                                                   int end, Spanned dst, int dstart, int dend) {
                            if (src.equals("")) { // for backspace
                                return src;
                            }
                            if (src.toString().matches("[a-zA-Z ]+")) {
                                return src;
                            }
                            return "";
                        }
                    }
            });
        }

        if(mfall.getSan_name()==null){
            etxt_mitarbeiter_name.setFilters(new InputFilter[] {
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
        }
        /*
        Zuweisen der Buttonvariablen zu den Buttons in der Activity
        Setzen des OnClickListeners, damit auf Klicks reagiert wird
         */
        btn_verwerfen = (Button)findViewById(R.id.btn_verwerfen_mitarbeiter_konfig);
        btn_speichern = (Button)findViewById(R.id.btn_speichern_mitarbeiter_konfig);
        btn_verwerfen.setOnClickListener(this);
        btn_speichern.setOnClickListener(this);

        textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Buttons speichern & verwerfen sind sichtbar
                lnl_buttons.setVisibility(lnl_buttons.VISIBLE);
                // von Vivien Stumpe, 20.05.16
                // Timer erst starten nachdem 3 Zeichen eingegeben wurden
                if (s.length() >= 3) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //Tastatur ausblenden
                            InputMethodManager imm =
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(etxt_mitarbeiter_name.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(etxt_mitarbeiter_vorname.getWindowToken(), 0);
                        }

                    }, DELAY);
                }
            }
        };

        /* von Vivien Stumpe, 01.05.16
        TextChangedListener muss auf die Eingabefelder gesetzt werden
        damit auch registriert wird, wenn eine Eingabe getätigt wurde
        und entsprechend reagiert werden kann -> siehe TextWatcher
         */
        etxt_mitarbeiter_name.addTextChangedListener(textWatcher);
        etxt_mitarbeiter_vorname.addTextChangedListener(textWatcher);

        /* von Vivien Stumpe, 16.05.16
        Falls Werte vorhanden sind, werden diese im Screen geladen
         */
        setWerte();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {
        //von Nathalie Horn, 02.05.16
        //clicked element mit dem geklickten Button belegen
        int ce = v.getId();

        if(ce == R.id.btn_speichern_mitarbeiter_konfig) {
            //wenn das Eingabefeld nicht leer ist, werden die Buttons eingeblendet
            //muss noch angepasst werden -> Daten werden ja auch gelöscht?
            // & Vergleich mit bestehenden Daten fehlt
            if (etxt_mitarbeiter_name.getText().length() != 0 || etxt_mitarbeiter_vorname.getText().length() != 0) {
                //aktObjekt.setKreisverband(etxt_kreisverband.getText().toString())
                Toast.makeText(this, "Mitarbeiter gespeichert", Toast.LENGTH_LONG).show();
            }
            speichereEingaben();

            //Buttons wieder ausblenden
            lnl_buttons.setVisibility(lnl_buttons.GONE);
        }

        if(ce==R.id.btn_verwerfen_mitarbeiter_konfig){
            Toast.makeText(this, "Mitarbeiter verworfen", Toast.LENGTH_LONG).show();
            //Buttons werden ausgeblendet
            lnl_buttons.setVisibility(lnl_buttons.GONE);
            //Inhalt des Textfeldes löschen?
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    // geändert von Nathalie Horn, 25.04.16
    private void selectItemFromDrawer(int position){

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if(position==0) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if(position==1) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==2) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if(position==3) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if(position==4) {
            Intent intent = new Intent(Mitarbeiterkonfig.this, Impressum.class);
            startActivity(intent);
        }
    }
    /* von Vivien Stumpe, 16.05.16
    Prozedur, die die eingegebenen Daten in den Variablen speichert
     */
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        mfall.setSan_name(etxt_mitarbeiter_name.getText().toString());
        Log.d("Fall", "Name gespeichert");

        mfall.setSan_vorname(etxt_mitarbeiter_vorname.getText().toString());
        Log.d("Fall", "Vorname gespeichert");
        mfall.setSan_vorh();
        mfall.setFall_angelegt();
    }
    /* von Vivien Stumpe, 16.05.16
    wird aufgerufen, wenn eine andere Aktivität in den Vordergrund gelangt
     */
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }

    /* von Vivien Stumpe, 16.05.16
    belegt die Eingabefelder etc. mit den lokal gespeicherten Werten, sofern vorhanden
     */
    public void setWerte(){
        mfall=(GlobaleDaten)getApplication();

        if((mfall.getSan_name()!=null)){
            etxt_mitarbeiter_name.setText(mfall.getSan_name());
        }
        if((mfall.getSan_vorname()!=null)){
            etxt_mitarbeiter_vorname.setText(mfall.getSan_vorname());
            }
        }
}
