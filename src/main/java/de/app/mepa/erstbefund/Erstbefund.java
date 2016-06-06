//Zuletzt bearbeitet von Vivien Stumpe, 29.05.16
package de.app.mepa.erstbefund;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.ersthelfermassnahmen.Ersthelfermassnahmen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;

public class Erstbefund extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener{
        private ImageView imgv_before;
        private ImageView imgv_menü;
        private Spinner spin_bewusstseinslage;
        private Spinner spin_kreislauf;
        private Spinner spin_pupillenfunktion_rechts;
        private Spinner spin_pupillenfunktion_links;
        private Spinner spin_ekg;
        private Spinner spin_schmerzen;
        private Spinner spin_atmung;
        private EditText edtxt_rr_systolisch;
        private EditText edtxt_rr_diastolisch;
        private EditText edtxt_puls;
        private EditText edtxt_atemfrequenz;
        private EditText edtxt_spo2;
        private EditText edtxt_blutzucker;
        private GlobaleDaten mfall;
        
        private String[]bewusstseinslage={"-----","orientiert","getrübt","bewusstlos"};
        private String[]kreislauf={"-----","schock","kreislaufstillstand","puls regelmäßig","puls unregelmäßig"};
        private String[]pupillenfunktion_rechts={"rechts","eng","mittel","weit","entrundet","lichtreaktion"};
        private String[]pupillenfunktion_links={"links","eng","mittel","weit","entrundet","lichtreaktion"};
        private String[]ekg={"-----","sinusrhythmus","rhythmusstörung","kammerflimmern","asystolie"};
        private String[]schmerzen={"-----","leicht","mittel","stark"};
        private String[]atmung={"-----","spontan/frei","atemnot","hyperventilation","atemstillstand"};

        private DrawerLayout drawerlayout_erstbefund;
        private ListView listview_erstbefund;
        private MyAdapter myadapter_erstbefund;
         private int[] drawer_icons_erstbefund={R.drawable.falleingabe,
                 R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

        Toolbar toolbar;


    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;
    /* von Vivien Stumpe, 20.05.16
    Textwatcher deklarieren
    Timer deklarieren mit der Zeit DELAY in Millisekunden
    */
    private Timer timer = new Timer();
    private final long DELAY = 4000; // in ms
    private TextWatcher tw;


@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstbefund);

        ArrayAdapter<String>adapter_bewusstseinslage=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,bewusstseinslage);

        adapter_bewusstseinslage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_kreislauf=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,kreislauf);

        adapter_kreislauf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_pupillenfunktion_rechts=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,pupillenfunktion_rechts);

        adapter_pupillenfunktion_rechts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_pupillenfunktion_links=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,pupillenfunktion_links);

        adapter_pupillenfunktion_links.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_ekg=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,ekg);

        adapter_ekg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_schmerzen=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,schmerzen);

        adapter_schmerzen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String>adapter_atmung=new ArrayAdapter<String>(Erstbefund.this,
                R.layout.spinner_layout,atmung);

        adapter_atmung.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spin_bewusstseinslage=(Spinner)findViewById(R.id.spin_bewusstseinslage);
        spin_bewusstseinslage.setAdapter(adapter_bewusstseinslage);
        spin_bewusstseinslage.setOnItemSelectedListener(this);

        spin_kreislauf=(Spinner)findViewById(R.id.spin_kreislauf);
        spin_kreislauf.setAdapter(adapter_kreislauf);
        spin_kreislauf.setOnItemSelectedListener(this);

        spin_pupillenfunktion_rechts=(Spinner)findViewById(R.id.spin_pupillenfunktion_rechts);
        spin_pupillenfunktion_rechts.setAdapter(adapter_pupillenfunktion_rechts);
        spin_pupillenfunktion_rechts.setOnItemSelectedListener(this);

        spin_pupillenfunktion_links=(Spinner)findViewById(R.id.spin_pupillenfunktion_links);
        spin_pupillenfunktion_links.setAdapter(adapter_pupillenfunktion_links);
        spin_pupillenfunktion_links.setOnItemSelectedListener(this);

        spin_ekg=(Spinner)findViewById(R.id.spin_ekg);
        spin_ekg.setAdapter(adapter_ekg);
        spin_ekg.setOnItemSelectedListener(this);

        spin_schmerzen=(Spinner)findViewById(R.id.spin_schmerzen);
        spin_schmerzen.setAdapter(adapter_schmerzen);
        spin_schmerzen.setOnItemSelectedListener(this);

        spin_atmung=(Spinner)findViewById(R.id.spin_atmung);
        spin_atmung.setAdapter(adapter_atmung);
        spin_atmung.setOnItemSelectedListener(this);

        edtxt_rr_systolisch=(EditText)findViewById(R.id.edtxt_rr_systolisch);
        edtxt_rr_diastolisch=(EditText)findViewById(R.id.edtxt_rr_diastolisch);
        edtxt_puls=(EditText)findViewById(R.id.edtxt_puls);
        edtxt_atemfrequenz=(EditText)findViewById(R.id.edtxt_atemfrequenz);
        edtxt_spo2=(EditText)findViewById(R.id.edtxt_spo2);
        edtxt_blutzucker=(EditText)findViewById(R.id.edtxt_blutzucker);

        setWerte();

        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_erstbefund=(DrawerLayout)findViewById(R.id.drawerLayout_Erstbefund);
        listview_erstbefund=(ListView)findViewById(R.id.listview_erstbefund);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_erstbefund=new MyAdapter(this,this.getResources().getStringArray(R.array.drawer_nav_neu),drawer_icons_erstbefund);
        listview_erstbefund.setAdapter(myadapter_erstbefund);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_erstbefund.setOnItemClickListener(this);
        
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    edtxt_rr_systolisch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if(edtxt_rr_systolisch.getText().toString().isEmpty()) {
                    // editText is empty
                } else {
                    int a = Integer.parseInt(edtxt_rr_systolisch.getText().toString());
                    if (a>140) {Context context = getApplicationContext();
                        CharSequence text = "Achtung! Hoher Blutdruck!";
                        int duration = Toast.LENGTH_SHORT;
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    if (a<100) {Context context = getApplicationContext();
                        CharSequence text = "Achtung! Niedriger Blutdruck!";
                        int duration = Toast.LENGTH_SHORT;
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                }
            }
                // code to execute when EditText loses focus
            }

    );

    edtxt_rr_diastolisch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if(edtxt_rr_diastolisch.getText().toString().isEmpty()) {
                    // editText is empty
                } else {
                    int b = Integer.parseInt(edtxt_rr_diastolisch.getText().toString());
                    if (b>100) {Context context = getApplicationContext();
                        CharSequence text = "Achtung! Hoher Blutdruck!";
                        int duration = Toast.LENGTH_SHORT;
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    if (b<70) {Context context = getApplicationContext();
                        CharSequence text = "Achtung! Niedriger Blutdruck!";
                        int duration = Toast.LENGTH_SHORT;
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }
        // code to execute when EditText loses focus
    }

    );

    edtxt_spo2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if(edtxt_spo2.getText().toString().isEmpty()) {
                    // editText is empty
                } else {
                    int c = Integer.parseInt(edtxt_spo2.getText().toString());
                    if (c<90) {Context context = getApplicationContext();
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        CharSequence text = "Achtung! Niedrige Sauerstoffsättigung!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }
        // code to execute when EditText loses focus
    }

    );
    edtxt_blutzucker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if(edtxt_blutzucker.getText().toString().isEmpty()) {
                    // editText is empty
                } else {
                    int d = Integer.parseInt(edtxt_blutzucker.getText().toString());
                    if (d>120) {Context context = getApplicationContext();
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);CharSequence text = "Achtung! Hoher Blutzucker!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    if (d<50) {Context context = getApplicationContext();
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        CharSequence text = "Achtung! Niedriger Blutzucker!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }
        // code to execute when EditText loses focus
    }

    );
    edtxt_atemfrequenz.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if (edtxt_atemfrequenz.getText().toString().isEmpty()) {
                    // editText is empty
                } else {
                    int e = Integer.parseInt(edtxt_atemfrequenz.getText().toString());
                    if (e < 11) {
                        Context context = getApplicationContext();
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        CharSequence text = "Achtung! Niedrige Atemfrequenz!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    if (e > 20) {
                        Context context = getApplicationContext();
                        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        vib.vibrate(500);
                        CharSequence text = "Achtung! Hohe Atemfrequenz!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }
                                                    // code to execute when EditText loses focus
                                                }

    );
            edtxt_puls.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                    @Override
                                                    public void onFocusChange(View v, boolean hasFocus) {
                                                        if (!hasFocus) {
                                                            if (edtxt_puls.getText().toString().isEmpty()) {
                                                                // editText is empty
                                                            } else {
                                                                int e = Integer.parseInt(edtxt_puls.getText().toString());
                                                                if (e < 45) {
                                                                    Context context = getApplicationContext();
                                                                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                                                    // Vibrate for 500 milliseconds
                                                                    vib.vibrate(500);
                                                                    CharSequence text = "Achtung! Niedriger Puls!";
                                                                    int duration = Toast.LENGTH_SHORT;
                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                    toast.show();
                                                                }
                                                                if (e > 85) {
                                                                    Context context = getApplicationContext();
                                                                    Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                                                    // Vibrate for 500 milliseconds
                                                                    vib.vibrate(500);
                                                                    CharSequence text = "Achtung! Hoher Puls!";
                                                                    int duration = Toast.LENGTH_SHORT;
                                                                    Toast toast = Toast.makeText(context, text, duration);
                                                                    toast.show();
                                                                }
                                                            }
                                                        }
                                                    }
                                                    // code to execute when EditText loses focus
                                                }

            );

                // von Vivien Stumpe, 11.04.16
                 //Hauptelement der Activity finden und der Variable zuweisen
                 //Wechseln der Aktivität mittels Swipe
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        view= findViewById(R.id.scrV_erstbefund);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Erstbefund.this, Ersthelfermassnahmen.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                drawerlayout_erstbefund.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Erstbefund.this, Massnahmen.class);
                startActivity(intent);
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            imgv_before = (ImageView)findViewById(R.id.imgv_before_erstbefund);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_erstbefund.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    /* von Vivien Stumpe, 20.05.16
        Tastatur ausblenden
         */
        tastaturausblenden();
        /*  von Vivien Stumpe, 29.05.16
            Felder sperren, wenn ein Fall gewählt wurde
         */
    sperreFelder();
        }

    /* von Vivien Stumpe, 20.05.16
           Prozedur, die die Tastatur 2 Sekunden nach der Eingabe von 3 Zeichen ausblendet
       */
    public void tastaturausblenden() {

         /* von Vivien Stumpe, 20.05.16
        TextWatcher "beobachtet" den User bei der Eingabe in ein EditText
        Damit entsprechend auf Eingaben reagiert werden kann
         */
        tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*if (edtxt_rr_diastolisch.getText().length()==3){
                    // Get instance of Vibrator from current Context
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 300 milliseconds
                    v.vibrate(300);
                }*/
            }


            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() >= 2) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            //Tastatur ausblenden
                            InputMethodManager imm =
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edtxt_rr_diastolisch.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(edtxt_rr_systolisch.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(edtxt_puls.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(edtxt_atemfrequenz.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(edtxt_blutzucker.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(edtxt_spo2.getWindowToken(), 0);
                        }
                    }, DELAY);
                }


            }
        };
        edtxt_rr_diastolisch.addTextChangedListener(tw);
        edtxt_rr_systolisch.addTextChangedListener(tw);
        edtxt_puls.addTextChangedListener(tw);
        edtxt_atemfrequenz.addTextChangedListener(tw);
        edtxt_blutzucker.addTextChangedListener(tw);
        edtxt_spo2.addTextChangedListener(tw);
    }


@Override
public void onItemSelected(AdapterView<?>parent,View view,int position,long id){
        switch(position){
        case 0:
        // Was soll passieren, wenn das erste Element gewählt wurde?
        break;
        case 1:
        // Was soll passieren, wenn das zweite Element gewählt wurde?
        break;
        case 2:
        // Was soll passieren, wenn das dritte Element gewählt wurde?
        break;
        case 3:
        // Was soll passieren, wenn das vierte Element gewählt wurde?
        break;
        }
        }
@Override
public void onNothingSelected(AdapterView<?>parent){
        }


@Override
public void onItemClick(AdapterView<?>parent,View view,int position,long id){
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
        }

private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
                Intent intent = new Intent(Erstbefund.this, Falleingabe.class);
                startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
                Intent intent = new Intent(Erstbefund.this, Falluebersicht.class);
                startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
                Intent intent = new Intent(Erstbefund.this, Upload.class);
                startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
                Intent intent = new Intent(Erstbefund.this, Einstellungen.class);
                startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
                Intent intent = new Intent(Erstbefund.this, Impressum.class);
                startActivity(intent);
        }
        drawerlayout_erstbefund.closeDrawers();
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
            if (ce == R.id.imgv_before_erstbefund) {
                        Intent intent = new Intent(Erstbefund.this, Falleingabe.class);
                        startActivity(intent);
            }
            if(ce == R.id.imgv_menu){
                drawerlayout_erstbefund.openDrawer(GravityCompat.START);
            }
        }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        if((spin_bewusstseinslage.getSelectedItem().toString().equals("orientiert"))){
            mfall.setErst_bewusstsein("orientiert");
        }
        if((spin_bewusstseinslage.getSelectedItem().toString().equals("getrübt"))){
            mfall.setErst_bewusstsein("getrübt");
        }
        if((spin_bewusstseinslage.getSelectedItem().toString().equals("bewusstlos"))){
            mfall.setErst_bewusstsein("bewusstlos");
        }
        if((spin_bewusstseinslage.getSelectedItem().toString().equals("-----"))){
            mfall.setErst_bewusstsein(null);
        }
        if((spin_kreislauf.getSelectedItem().toString().equals("schock"))){
            mfall.setErst_kreislauf("schock");
        }
        if((spin_kreislauf.getSelectedItem().toString().equals("kreislaufstillstand"))){
            mfall.setErst_kreislauf("kreislaufstillstand");
        }
        if((spin_kreislauf.getSelectedItem().toString().equals("puls regelmäßig"))){
            mfall.setErst_kreislauf("puls regelmäßig");
        }
        if((spin_kreislauf.getSelectedItem().toString().equals("puls unregelmäßig"))){
            mfall.setErst_kreislauf("Puls unregelmäßig");
        }
        if((spin_kreislauf.getSelectedItem().toString().equals("-----"))){
            mfall.setErst_kreislauf(null);
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("eng"))){
            mfall.setErst_pupille_li("eng");
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("mittel"))){
            mfall.setErst_pupille_li("mittel");
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("weit"))){
            mfall.setErst_pupille_li("weit");
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("entrundet"))){
            mfall.setErst_pupille_li("entrundet");
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("lichtreaktion"))){
            mfall.setErst_pupille_li("lichtreaktion");
        }
        if((spin_pupillenfunktion_links.getSelectedItem().toString().equals("links"))){
            mfall.setErst_pupille_li(null);
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("eng"))){
            mfall.setErst_pupille_re("eng");
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("mittel"))){
            mfall.setErst_pupille_re("mittel");
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("weit"))){
            mfall.setErst_pupille_re("weit");
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("entrundet"))){
            mfall.setErst_pupille_re("entrundet");
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("lichtreaktion"))){
            mfall.setErst_pupille_re("lichtreaktion");
        }
        if((spin_pupillenfunktion_rechts.getSelectedItem().toString().equals("rechts"))){
            mfall.setErst_pupille_re(null);
        }
        if((spin_ekg.getSelectedItem().toString().equals("sinusrhythmus"))){
            mfall.setErst_ekg("sinusrhythmus");
        }
        if((spin_ekg.getSelectedItem().toString().equals("rhythmusstörung"))){
            mfall.setErst_ekg("rhythmusstörung");
        }
        if((spin_ekg.getSelectedItem().toString().equals("kammerflimmern"))){
            mfall.setErst_ekg("kammerflimmern");
        }
        if((spin_ekg.getSelectedItem().toString().equals("asystolie"))){
            mfall.setErst_ekg("asystolie");
        }
        if((spin_ekg.getSelectedItem().toString().equals("-----"))){
            mfall.setErst_ekg(null);
        }
        if((spin_schmerzen.getSelectedItem().toString().equals("leicht"))){
            mfall.setErst_schmerzen("leicht");
        }
        if((spin_schmerzen.getSelectedItem().toString().equals("mittel"))){
            mfall.setErst_schmerzen("mittel");
        }
        if((spin_schmerzen.getSelectedItem().toString().equals("stark"))){
            mfall.setErst_schmerzen("stark");
        }
        if((spin_schmerzen.getSelectedItem().toString().equals("-----"))){
            mfall.setErst_schmerzen(null);
        }
        if((spin_atmung.getSelectedItem().toString().equals("spontan/frei"))){
            mfall.setErst_atmung("spontan/frei");
        }
        if((spin_atmung.getSelectedItem().toString().equals("atemnot"))){
            mfall.setErst_atmung("atemnot");
        }
        if((spin_atmung.getSelectedItem().toString().equals("hyperventilation"))){
            mfall.setErst_atmung("hyperventilation");
        }
        if((spin_atmung.getSelectedItem().toString().equals("atemstillstand"))){
            mfall.setErst_atmung("atemstillstand");
        }
        if((spin_atmung.getSelectedItem().toString().equals("-----"))){
            mfall.setErst_atmung(null);
        }
        mfall.setErst_rr_sys(edtxt_rr_systolisch.getText().toString());
        mfall.setErst_rr_dia(edtxt_rr_diastolisch.getText().toString());
        mfall.setErst_puls(edtxt_puls.getText().toString());
        mfall.setErst_af(edtxt_atemfrequenz.getText().toString());
        mfall.setErst_spo(edtxt_spo2.getText().toString());
        mfall.setErst_bz(edtxt_blutzucker.getText().toString());
    }
    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if((mfall.getErst_bewusstsein()!=null)){
            if(mfall.getErst_bewusstsein().equals("orientiert")){
                spin_bewusstseinslage.setSelection(1);
            }
            if(mfall.getErst_bewusstsein().equals("getrübt")){
                spin_bewusstseinslage.setSelection(2);
            }
            if(mfall.getErst_bewusstsein().equals("bewusstlos")){
                spin_bewusstseinslage.setSelection(3);
            }
        }
        if((mfall.getErst_kreislauf()!=null)){
            if(mfall.getErst_kreislauf().equals("schock")){
                spin_kreislauf.setSelection(1);
            }
            if(mfall.getErst_kreislauf().equals("kreislaufstillstand")){
                spin_kreislauf.setSelection(2);
            }
            if(mfall.getErst_kreislauf().equals("puls regelmäßig")){
                spin_kreislauf.setSelection(3);
            }
            if(mfall.getErst_kreislauf().equals("puls unregelmäßig")){
                spin_kreislauf.setSelection(4);
            }
        }
        if((mfall.getErst_pupille_li()!=null)){
            if(mfall.getErst_pupille_li().equals("eng")){
                spin_pupillenfunktion_links.setSelection(1);
            }
            if(mfall.getErst_pupille_li().equals("mittel")){
                spin_pupillenfunktion_links.setSelection(2);
            }
            if(mfall.getErst_pupille_li().equals("weit")){
                spin_pupillenfunktion_links.setSelection(3);
            }
            if(mfall.getErst_pupille_li().equals("entrundet")){
                spin_pupillenfunktion_links.setSelection(4);
            }
            if(mfall.getErst_pupille_li().equals("lichtreaktion")){
                spin_pupillenfunktion_links.setSelection(5);
            }
        }
        if((mfall.getErst_pupille_re()!=null)){
            if(mfall.getErst_pupille_re().equals("eng")){
                spin_pupillenfunktion_rechts.setSelection(1);
            }
            if(mfall.getErst_pupille_re().equals("mittel")){
                spin_pupillenfunktion_rechts.setSelection(2);
            }
            if(mfall.getErst_pupille_re().equals("weit")){
                spin_pupillenfunktion_rechts.setSelection(3);
            }
            if(mfall.getErst_pupille_re().equals("entrundet")){
                spin_pupillenfunktion_rechts.setSelection(4);
            }
            if(mfall.getErst_pupille_re().equals("lichtreaktion")){
                spin_pupillenfunktion_rechts.setSelection(5);
            }
        }
        if((mfall.getErst_ekg()!=null)){
            if(mfall.getErst_ekg().equals("sinusrhythmus")){
                spin_ekg.setSelection(1);
            }
            if(mfall.getErst_ekg().equals("rhythmusstörung")){
                spin_ekg.setSelection(2);
            }
            if(mfall.getErst_ekg().equals("kammerflimmern")){
                spin_ekg.setSelection(3);
            }
            if(mfall.getErst_ekg().equals("asystolie")){
                spin_ekg.setSelection(4);
            }
        }
        if((mfall.getErst_schmerzen()!=null)){
            if(mfall.getErst_schmerzen().equals("leicht")){
                spin_schmerzen.setSelection(1);
            }
            if(mfall.getErst_schmerzen().equals("mittel")){
                spin_schmerzen.setSelection(2);
            }
            if(mfall.getErst_schmerzen().equals("stark")){
                spin_schmerzen.setSelection(3);
            }
        }
        if((mfall.getErst_atmung()!=null)){
            if(mfall.getErst_atmung().equals("spontan/frei")){
                spin_atmung.setSelection(1);
            }
            if(mfall.getErst_atmung().equals("atemnot")){
                spin_atmung.setSelection(2);
            }
            if(mfall.getErst_atmung().equals("hyperventilation")){
                spin_atmung.setSelection(3);
            }
            if(mfall.getErst_atmung().equals("atemstillstand")){
                spin_atmung.setSelection(4);
            }
        }
        if ((mfall.getErst_rr_sys() != null)) {
            edtxt_rr_systolisch.setText(mfall.getErst_rr_sys());
        }
        if ((mfall.getErst_rr_dia() != null)) {
            edtxt_rr_diastolisch.setText(mfall.getErst_rr_dia());
        }
        if ((mfall.getErst_puls() != null)) {
            edtxt_puls.setText(mfall.getErst_puls());
        }
        if ((mfall.getErst_af() != null)) {
            edtxt_atemfrequenz.setText(mfall.getErst_af());
        }
        if ((mfall.getErst_spo() != null)) {
            edtxt_spo2.setText(mfall.getErst_spo());
        }
        if ((mfall.getErst_bz() != null)) {
            edtxt_blutzucker.setText(mfall.getErst_bz());
        }
    }
    /*  von Vivien Stumpe, 29.05.16
         Prozedur, die alle Felder sperrt,
         wenn ein Fall in der Fallübersicht ausgewählt wurde
        */
    public void sperreFelder(){
        if(mfall.getFallAusgewaehlt()) {
            spin_atmung.setEnabled(false);
            spin_bewusstseinslage.setEnabled(false);
            spin_ekg.setEnabled(false);
            spin_kreislauf.setEnabled(false);
            spin_pupillenfunktion_links.setEnabled(false);
            spin_pupillenfunktion_rechts.setEnabled(false);
            spin_schmerzen.setEnabled(false);
            edtxt_spo2.setEnabled(false);
            edtxt_blutzucker.setEnabled(false);
            edtxt_atemfrequenz.setEnabled(false);
            edtxt_puls.setEnabled(false);
            edtxt_rr_diastolisch.setEnabled(false);
            edtxt_rr_systolisch.setEnabled(false);
        }
    }
}
