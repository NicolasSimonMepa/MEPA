//Zuletzt bearbeitet von Vivien Stumpe, 29.05.16
package de.app.mepa.massnahmen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.erstbefund.Erstbefund;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.upload.Upload;
import de.app.mepa.mepa.R;

public class Massnahmen extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ImageView imgv_before;
    private ImageView imgv_menü;
    private DrawerLayout drawerlayout_massnahmen;
    private ListView listview_massnahmen;
    private MyAdapter myadapter_massnahmen;
    private int[] drawer_icons_massnahmen={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    Toolbar toolbar;

    private GlobaleDaten mfall;
    private CheckBox cck_massnahmen_keine;
    private CheckBox cck_massnahmen_stabile_seitenlage;
    private CheckBox cck_massnahmen_herzdruckmassage;
    private CheckBox cck_massnahmen_schocklagerung;
    private CheckBox cck_massnahmen_oberkoerperhochlagerung;
    private CheckBox cck_massnahmen_flachlagerung;
    private CheckBox cck_massnahmen_wundversorgung;
    private CheckBox cck_massnahmen_sauerstoffgabe;
    private CheckBox cck_massnahmen_vakuummatratze;
    private CheckBox cck_massnahmen_hws_stuetzkragen;
    private CheckBox cck_massnahmen_extremitaetenschienung;
    private CheckBox cck_massnahmen_ekg;
    private CheckBox cck_massnahmen_atemwege;
    private CheckBox cck_massnahmen_notkompetenz;
    private CheckBox cck_massnahmen_erstdefibrillation;
    private CheckBox cck_massnahmen_venoeser_zugang;
    private CheckBox cck_massnahmen_medikamente;
    private CheckBox cck_massnahmen_beatmung;
    private CheckBox cck_massnahmen_betreuung;
    private CheckBox cck_massnahmen_infusion;
    private CheckBox cck_massnahmen_intubation;
    private CheckBox cck_massnahmen_sonstiges;
    private EditText edtxt_massnahmen_sonstiges;
    /* von Vivien Stumpe, 20.05.16
Textwatcher deklarieren
Timer deklarieren mit der Zeit DELAY in Millisekunden
*/
    private Timer timer = new Timer();
    private final long DELAY = 2000; // in ms
    private TextWatcher tw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massnahmen);

        drawerlayout_massnahmen=(DrawerLayout) findViewById(R.id.drawerLayout_Massnahmen);
        listview_massnahmen=(ListView) findViewById(R.id.listview_Massnahmen);

        myadapter_massnahmen=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_massnahmen);
        listview_massnahmen.setAdapter(myadapter_massnahmen);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_massnahmen.setOnItemClickListener(this);

        //von Vivien Stumpe, 11.04.16
                    //Wechseln der Akitivität mittels Swipe
                  //Hauptelement der Activity finden und der Variable zuweisen
                 //Darauf den OnTouchListener setzen, damit auf Berührungen reagiert wird
                 //wenn nach links gewischt wird, wird die nächste Seite mittels Intent geöffnet
        view=(View) findViewById(R.id.scrV_massnahmen);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Massnahmen.this, Erstbefund.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                drawerlayout_massnahmen.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Massnahmen.this, Erkrankung.class);
                startActivity(intent);
            }
        });
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cck_massnahmen_keine=(CheckBox)findViewById(R.id.cck_massnahmen_keine);
        cck_massnahmen_stabile_seitenlage=(CheckBox)findViewById(R.id.cck_massnahmen_stabile_seitenlage);
        cck_massnahmen_herzdruckmassage=(CheckBox)findViewById(R.id.cck_massnahmen_herzdruckmassage);
        cck_massnahmen_schocklagerung=(CheckBox)findViewById(R.id.cck_massnahmen_schocklagerung);
        cck_massnahmen_oberkoerperhochlagerung=(CheckBox)findViewById(R.id.cck_massnahmen_oberkoerperhochlagerung);
        cck_massnahmen_flachlagerung=(CheckBox)findViewById(R.id.cck_massnahmen_flachlagerung);
        cck_massnahmen_wundversorgung=(CheckBox)findViewById(R.id.cck_massnahmen_wundversorgung);
        cck_massnahmen_sauerstoffgabe=(CheckBox)findViewById(R.id.cck_massnahmen_sauerstoffgabe);
        cck_massnahmen_vakuummatratze=(CheckBox)findViewById(R.id.cck_massnahmen_vakuummatratze);
        cck_massnahmen_hws_stuetzkragen=(CheckBox)findViewById(R.id.cck_massnahmen_hws_stuetzkragen);
        cck_massnahmen_extremitaetenschienung=(CheckBox)findViewById(R.id.cck_massnahmen_extremitaetenschienung);
        cck_massnahmen_ekg=(CheckBox)findViewById(R.id.cck_massnahmen_ekg);
        cck_massnahmen_atemwege=(CheckBox)findViewById(R.id.cck_massnahmen_atemwege);
        cck_massnahmen_notkompetenz=(CheckBox)findViewById(R.id.cck_massnahmen_notkompetenz);
        cck_massnahmen_erstdefibrillation=(CheckBox)findViewById(R.id.cck_massnahmen_erstdefibrillation);
        cck_massnahmen_venoeser_zugang=(CheckBox)findViewById(R.id.cck_massnahmen_venoeser_zugang);
        cck_massnahmen_medikamente=(CheckBox)findViewById(R.id.cck_massnahmen_medikamente);
        cck_massnahmen_beatmung=(CheckBox)findViewById(R.id.cck_massnahmen_beatmung);
        cck_massnahmen_betreuung=(CheckBox)findViewById(R.id.cck_massnahmen_betreuung);
        cck_massnahmen_infusion=(CheckBox)findViewById(R.id.cck_massnahmen_infusion);
        cck_massnahmen_intubation=(CheckBox)findViewById(R.id.cck_massnahmen_intubation);
        cck_massnahmen_sonstiges=(CheckBox)findViewById(R.id.cck_massnahmen_sonstiges);
        edtxt_massnahmen_sonstiges=(EditText)findViewById(R.id.edtxt_massnahmen_sonstiges);
        cck_massnahmen_keine.setOnClickListener(this);
        cck_massnahmen_stabile_seitenlage.setOnClickListener(this);
        cck_massnahmen_herzdruckmassage.setOnClickListener(this);
        cck_massnahmen_schocklagerung.setOnClickListener(this);
        cck_massnahmen_oberkoerperhochlagerung.setOnClickListener(this);
        cck_massnahmen_flachlagerung.setOnClickListener(this);
        cck_massnahmen_wundversorgung.setOnClickListener(this);
        cck_massnahmen_sauerstoffgabe.setOnClickListener(this);
        cck_massnahmen_vakuummatratze.setOnClickListener(this);
        cck_massnahmen_hws_stuetzkragen.setOnClickListener(this);
        cck_massnahmen_extremitaetenschienung.setOnClickListener(this);
        cck_massnahmen_ekg.setOnClickListener(this);
        cck_massnahmen_atemwege.setOnClickListener(this);
        cck_massnahmen_notkompetenz.setOnClickListener(this);
        cck_massnahmen_erstdefibrillation.setOnClickListener(this);
        cck_massnahmen_venoeser_zugang.setOnClickListener(this);
        cck_massnahmen_medikamente.setOnClickListener(this);
        cck_massnahmen_beatmung.setOnClickListener(this);
        cck_massnahmen_betreuung.setOnClickListener(this);
        cck_massnahmen_infusion.setOnClickListener(this);
        cck_massnahmen_intubation.setOnClickListener(this);
        cck_massnahmen_sonstiges.setOnClickListener(this);

        setWerte();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_massnahmen);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_massnahmen.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
    public void tastaturausblenden(){

         /* von Vivien Stumpe, 20.05.16
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

                //Timer erst starten nachdem 3 Zeichen eingegeben wurden
                if (s.length() >= 3) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //Tastatur ausblenden
                            InputMethodManager imm =
                                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edtxt_massnahmen_sonstiges.getWindowToken(), 0);
                        }

                    }, DELAY);
                }
            }

        };
        edtxt_massnahmen_sonstiges.addTextChangedListener(tw);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Massnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Massnahmen.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Massnahmen.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Massnahmen.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Massnahmen.this, Impressum.class);
            startActivity(intent);
        }
        drawerlayout_massnahmen.closeDrawers();
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
        if (ce == R.id.imgv_before_massnahmen) {
            Intent intent = new Intent(Massnahmen.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_menu){
            drawerlayout_massnahmen.openDrawer(GravityCompat.START);
        }
    }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }
    public void speichereEingaben() {
        mfall = (GlobaleDaten) getApplication();
        if (cck_massnahmen_keine.isChecked()) {
            mfall.setMas_keine(1);
        } else
            mfall.setMas_keine(0);
        if (cck_massnahmen_stabile_seitenlage.isChecked()) {
            mfall.setMas_stb_seitenlage(1);
        } else
            mfall.setMas_stb_seitenlage(0);
        if (cck_massnahmen_herzdruckmassage.isChecked()) {
            mfall.setMas_herzdruckmassage(1);
        } else
            mfall.setMas_herzdruckmassage(0);
        if (cck_massnahmen_schocklagerung.isChecked()) {
            mfall.setMas_schocklagerung(1);
        } else
            mfall.setMas_schocklagerung(0);
        if (cck_massnahmen_oberkoerperhochlagerung.isChecked()) {
            mfall.setMas_oberkoerperhochlage(1);
        } else
            mfall.setMas_oberkoerperhochlage(0);
        if (cck_massnahmen_flachlagerung.isChecked()) {
            mfall.setMas_flachlagerung(1);
        } else
            mfall.setMas_flachlagerung(0);
        if (cck_massnahmen_wundversorgung.isChecked()) {
            mfall.setMas_wundversorgung(1);
        } else
            mfall.setMas_wundversorgung(0);
        if (cck_massnahmen_sauerstoffgabe.isChecked()) {
            mfall.setMas_sauerstoffgabe(1);
        } else
            mfall.setMas_sauerstoffgabe(0);
        if (cck_massnahmen_vakuummatratze.isChecked()) {
            mfall.setMas_vakuummatratze(1);
        } else
            mfall.setMas_vakuummatratze(0);
        if (cck_massnahmen_hws_stuetzkragen.isChecked()) {
            mfall.setMas_hws_stuetzkragen(1);
        } else
            mfall.setMas_hws_stuetzkragen(0);
        if (cck_massnahmen_extremitaetenschienung.isChecked()) {
            mfall.setMas_extr_schienung(1);
        } else
            mfall.setMas_extr_schienung(0);
        if (cck_massnahmen_ekg.isChecked()) {
            mfall.setMas_ekg(1);
        } else
            mfall.setMas_ekg(0);
        if (cck_massnahmen_atemwege.isChecked()) {
            mfall.setMas_atemwege_freim(1);
        } else
            mfall.setMas_atemwege_freim(0);
        if (cck_massnahmen_notkompetenz.isChecked()) {
            mfall.setMas_notkompetenz(1);
        } else
            mfall.setMas_notkompetenz(0);
        if (cck_massnahmen_erstdefibrillation.isChecked()) {
            mfall.setMas_erstdefibrillation(1);
        } else
            mfall.setMas_erstdefibrillation(0);
        if (cck_massnahmen_venoeser_zugang.isChecked()) {
            mfall.setMas_ven_zugang(1);
        } else
            mfall.setMas_ven_zugang(0);
        if (cck_massnahmen_medikamente.isChecked()) {
            mfall.setMas_medikamente(1);
        } else
            mfall.setMas_medikamente(0);
        if (cck_massnahmen_beatmung.isChecked()) {
            mfall.setMas_beatmung(1);
        } else
            mfall.setMas_beatmung(0);
        if (cck_massnahmen_betreuung.isChecked()) {
            mfall.setMas_betreuung(1);
        } else
            mfall.setMas_betreuung(0);
        if (cck_massnahmen_infusion.isChecked()) {
            mfall.setMas_infusion(1);
        } else
            mfall.setMas_infusion(0);
        if (cck_massnahmen_intubation.isChecked()) {
            mfall.setMas_intubation(1);
        } else
            mfall.setMas_intubation(0);
        if (cck_massnahmen_sonstiges.isChecked()) {
            mfall.setMas_sonstiges(1);
        } else
            mfall.setMas_sonstiges(0);
        mfall.setMas_sonstiges_text(edtxt_massnahmen_sonstiges.getText().toString());
    }
    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if (mfall.getMas_keine() != null) {
            if (mfall.getMas_keine() == 1) {
                cck_massnahmen_keine.setChecked(true);
            }
        }
        if (mfall.getMas_stb_seitenlage() != null) {
            if (mfall.getMas_stb_seitenlage() == 1) {
                cck_massnahmen_stabile_seitenlage.setChecked(true);
            }
        }
        if (mfall.getMas_herzdruckmassage() != null) {
            if (mfall.getMas_herzdruckmassage() == 1) {
                cck_massnahmen_herzdruckmassage.setChecked(true);
            }
        }
        if (mfall.getMas_schocklagerung() != null) {
            if (mfall.getMas_schocklagerung() == 1) {
                cck_massnahmen_schocklagerung.setChecked(true);
            }
        }
        if (mfall.getMas_oberkoerperhochlage() != null) {
            if (mfall.getMas_oberkoerperhochlage() == 1) {
                cck_massnahmen_oberkoerperhochlagerung.setChecked(true);
            }
        }
        if (mfall.getMas_flachlagerung() != null) {
            if (mfall.getMas_flachlagerung() == 1) {
                cck_massnahmen_flachlagerung.setChecked(true);
            }
        }
        if (mfall.getMas_wundversorgung() != null) {
            if (mfall.getMas_wundversorgung() == 1) {
                cck_massnahmen_wundversorgung.setChecked(true);
            }
        }
        if (mfall.getMas_sauerstoffgabe() != null) {
            if (mfall.getMas_sauerstoffgabe() == 1) {
                cck_massnahmen_sauerstoffgabe.setChecked(true);
            }
        }
        if (mfall.getMas_vakuummatratze() != null) {
            if (mfall.getMas_vakuummatratze() == 1) {
                cck_massnahmen_vakuummatratze.setChecked(true);
            }
        }
        if (mfall.getMas_hws_stuetzkragen() != null) {
            if (mfall.getMas_hws_stuetzkragen() == 1) {
                cck_massnahmen_hws_stuetzkragen.setChecked(true);
            }
        }
        if (mfall.getMas_extr_schienung() != null) {
            if (mfall.getMas_extr_schienung() == 1) {
                cck_massnahmen_extremitaetenschienung.setChecked(true);
            }
        }
        if (mfall.getMas_ekg() != null) {
            if (mfall.getMas_ekg() == 1) {
                cck_massnahmen_ekg.setChecked(true);
            }
        }
        if (mfall.getMas_atemwege_freim() != null) {
            if (mfall.getMas_atemwege_freim() == 1) {
                cck_massnahmen_atemwege.setChecked(true);
            }
        }
        if (mfall.getMas_notkompetenz() != null) {
            if (mfall.getMas_notkompetenz() == 1) {
                cck_massnahmen_notkompetenz.setChecked(true);
            }
        }
        if (mfall.getMas_erstdefibrillation() != null) {
            if (mfall.getMas_erstdefibrillation() == 1) {
                cck_massnahmen_erstdefibrillation.setChecked(true);
            }
        }
        if (mfall.getMas_ven_zugang() != null) {
            if (mfall.getMas_ven_zugang() == 1) {
                cck_massnahmen_venoeser_zugang.setChecked(true);
            }
        }
        if (mfall.getMas_medikamente() != null) {
            if (mfall.getMas_medikamente() == 1) {
                cck_massnahmen_medikamente.setChecked(true);
            }
        }
        if (mfall.getMas_beatmung() != null) {
            if (mfall.getMas_beatmung() == 1) {
                cck_massnahmen_beatmung.setChecked(true);
            }
        }
        if (mfall.getMas_betreuung() != null) {
            if (mfall.getMas_betreuung() == 1) {
                cck_massnahmen_betreuung.setChecked(true);
            }
        }
        if (mfall.getMas_infusion() != null) {
            if (mfall.getMas_infusion() == 1) {
                cck_massnahmen_infusion.setChecked(true);
            }
        }
        if (mfall.getMas_intubation() != null) {
            if (mfall.getMas_intubation() == 1) {
                cck_massnahmen_intubation.setChecked(true);
            }
        }
        if (mfall.getMas_sonstiges() != null) {
            if (mfall.getMas_sonstiges() == 1) {
                cck_massnahmen_sonstiges.setChecked(true);
            }
        }
        if((mfall.getMas_sonstiges_text()!=null)){
            edtxt_massnahmen_sonstiges.setText(mfall.getMas_sonstiges_text());
        }
    }
    /*  von Vivien Stumpe, 29.05.16
    Prozedur, die alle Felder sperrt,
    wenn ein Fall in der Fallübersicht ausgewählt wurde
*/
    public void sperreFelder(){
        if(mfall.getFallAusgewaehlt()) {
            cck_massnahmen_atemwege.setEnabled(false);
            cck_massnahmen_beatmung.setEnabled(false);
            cck_massnahmen_betreuung.setEnabled(false);
            cck_massnahmen_ekg.setEnabled(false);
            cck_massnahmen_erstdefibrillation.setEnabled(false);
            cck_massnahmen_extremitaetenschienung.setEnabled(false);
            cck_massnahmen_flachlagerung.setEnabled(false);
            cck_massnahmen_herzdruckmassage.setEnabled(false);
            cck_massnahmen_hws_stuetzkragen.setEnabled(false);
            cck_massnahmen_infusion.setEnabled(false);
            cck_massnahmen_intubation.setEnabled(false);
            cck_massnahmen_keine.setEnabled(false);
            cck_massnahmen_medikamente.setEnabled(false);
            cck_massnahmen_notkompetenz.setEnabled(false);
            cck_massnahmen_oberkoerperhochlagerung.setEnabled(false);
            cck_massnahmen_sauerstoffgabe.setEnabled(false);
            cck_massnahmen_schocklagerung.setEnabled(false);
            cck_massnahmen_sonstiges.setEnabled(false);
            cck_massnahmen_stabile_seitenlage.setEnabled(false);
            cck_massnahmen_vakuummatratze.setEnabled(false);
            cck_massnahmen_venoeser_zugang.setEnabled(false);
            cck_massnahmen_wundversorgung.setEnabled(false);
            edtxt_massnahmen_sonstiges.setEnabled(false);
        }
    }
}
