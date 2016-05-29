//Zuletzt geändert von Vivien Stumpe, 29.05.16
package de.app.mepa.erkrankung;

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
import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.mepa.R;
import de.app.mepa.upload.Upload;
import de.app.mepa.verletzung.Verletzung;

public class Erkrankung extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{
    private ImageView imgv_before;
    private ImageView imgv_menü;
    //von Vivien Stumpe, 10.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_erkrankung;
    private ListView listview_erkrankung;
    private MyAdapter myadapter_erkrankung;
    private int[] drawer_icons_erkrankung={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;

    Toolbar toolbar;

    private GlobaleDaten mfall;
    private CheckBox cck_erkrankung_keine;
    private CheckBox cck_erkrankung_alkoholisiert;
    private CheckBox cck_erkrankung_erbrechen;
    private CheckBox cck_erkrankung_schwindel;
    private CheckBox cck_erkrankung_herzkreislauf;
    private CheckBox cck_erkrankung_hitzeschlag;
    private CheckBox cck_erkrankung_hitzeerschoepfung;
    private CheckBox cck_erkrankung_vergiftung;
    private CheckBox cck_erkrankung_atmung;
    private CheckBox cck_erkrankung_unterkuehlung;
    private CheckBox cck_erkrankung_baucherkrankung;
    private CheckBox cck_erkrankung_stoffwechsel;
    private CheckBox cck_erkrankung_neurologie;
    private CheckBox cck_erkrankung_gynaekologie;
    private CheckBox cck_erkrankung_psychatrie;
    private CheckBox cck_erkrankung_kindernotfall;
    private CheckBox cck_erkrankung_geburtshilfe;
    private CheckBox cck_erkrankung_sonstiges;
    private EditText edtxt_erkrankung_sonstiges;
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
        setContentView(R.layout.activity_erkrankung);

        //von Vivien Stumpe, 10.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_erkrankung=(DrawerLayout) findViewById(R.id.drawerLayout_Erkrankung);
        listview_erkrankung=(ListView) findViewById(R.id.listview_Erkrankung);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_erkrankung=new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_erkrankung);
        listview_erkrankung.setAdapter(myadapter_erkrankung);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_erkrankung.setOnItemClickListener(this);

        //von Vivien Stumpe, 11.04.16
        //Verbindung der View zur Scrollview in der Aktivität
        view=(View) findViewById(R.id.scrV_erkrankung);
        //OnTouchListener auf die View setzen
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            //Bei einem Swipe nach links wird die nächste Aktivität geöffnet
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Erkrankung.this, Massnahmen.class);
                startActivity(intent);
            }

            public void onSwipeRight() {
                drawerlayout_erkrankung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                Intent intent = new Intent(Erkrankung.this, Verletzung.class);
                startActivity(intent);
            }
        });
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        imgv_before = (ImageView)findViewById(R.id.imgv_before_erkrankung);
        imgv_before.setOnClickListener(this);
        imgv_menü=(ImageView)findViewById(R.id.imgv_menu);
        imgv_menü.setOnClickListener(this);

        drawerlayout_erkrankung.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        cck_erkrankung_keine=(CheckBox)findViewById(R.id.cck_erkrankung_keine);
        cck_erkrankung_alkoholisiert=(CheckBox)findViewById(R.id.cck_erkrankung_alkoholisiert);
        cck_erkrankung_erbrechen=(CheckBox)findViewById(R.id.cck_erkrankung_erbrechen);
        cck_erkrankung_schwindel=(CheckBox)findViewById(R.id.cck_erkrankung_schwindel);
        cck_erkrankung_herzkreislauf=(CheckBox)findViewById(R.id.cck_erkrankung_herzkreislauf);
        cck_erkrankung_hitzeschlag=(CheckBox)findViewById(R.id.cck_erkrankung_hitzeschlag);
        cck_erkrankung_hitzeerschoepfung=(CheckBox)findViewById(R.id.cck_erkrankung_hitzeerschoepfung);
        cck_erkrankung_vergiftung=(CheckBox)findViewById(R.id.cck_erkrankung_vergiftung);
        cck_erkrankung_atmung=(CheckBox)findViewById(R.id.cck_erkrankung_atmung);
        cck_erkrankung_unterkuehlung=(CheckBox)findViewById(R.id.cck_erkrankung_unterkuehlung);
        cck_erkrankung_baucherkrankung=(CheckBox)findViewById(R.id.cck_erkrankung_baucherkrankung);
        cck_erkrankung_stoffwechsel=(CheckBox)findViewById(R.id.cck_erkrankung_stoffwechsel);
        cck_erkrankung_neurologie=(CheckBox)findViewById(R.id.cck_erkrankung_neurologie);
        cck_erkrankung_psychatrie=(CheckBox)findViewById(R.id.cck_erkrankung_psychatrie);
        cck_erkrankung_gynaekologie=(CheckBox)findViewById(R.id.cck_erkrankung_gynaekologie);
        cck_erkrankung_kindernotfall=(CheckBox)findViewById(R.id.cck_erkrankung_kindernotfall);
        cck_erkrankung_geburtshilfe=(CheckBox)findViewById(R.id.cck_erkrankung_geburtshilfe);
        cck_erkrankung_sonstiges=(CheckBox)findViewById(R.id.cck_erkrankung_sonstiges);
        edtxt_erkrankung_sonstiges=(EditText)findViewById(R.id.edtxt_erkrankung_sonstiges);

        cck_erkrankung_keine.setOnClickListener(this);
        cck_erkrankung_alkoholisiert.setOnClickListener(this);
        cck_erkrankung_erbrechen.setOnClickListener(this);
        cck_erkrankung_schwindel.setOnClickListener(this);
        cck_erkrankung_herzkreislauf.setOnClickListener(this);
        cck_erkrankung_hitzeschlag.setOnClickListener(this);
        cck_erkrankung_hitzeerschoepfung.setOnClickListener(this);
        cck_erkrankung_vergiftung.setOnClickListener(this);
        cck_erkrankung_atmung.setOnClickListener(this);
        cck_erkrankung_unterkuehlung.setOnClickListener(this);
        cck_erkrankung_baucherkrankung.setOnClickListener(this);
        cck_erkrankung_stoffwechsel.setOnClickListener(this);
        cck_erkrankung_neurologie.setOnClickListener(this);
        cck_erkrankung_psychatrie.setOnClickListener(this);
        cck_erkrankung_gynaekologie.setOnClickListener(this);
        cck_erkrankung_kindernotfall.setOnClickListener(this);
        cck_erkrankung_geburtshilfe.setOnClickListener(this);
        cck_erkrankung_sonstiges.setOnClickListener(this);

        setWerte();
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
                            imm.hideSoftInputFromWindow(edtxt_erkrankung_sonstiges.getWindowToken(), 0);
                        }

                    }, DELAY);
                }
            }

        };
        edtxt_erkrankung_sonstiges.addTextChangedListener(tw);
    }
    //von Vivien Stumpe, 10.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }
    //von Vivien Stumpe, 10.04.16
    private void selectItemFromDrawer(int position){
        //Wenn das erste Element im Menü geklickt wurde, wird zurück zum Start navigiert
        if(position==0) {
            Intent intent = new Intent(Erkrankung.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, werden die Einstellungen aufgerufen
        if(position==1) {
            Intent intent = new Intent(Erkrankung.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird die Falleingabe aufgerufen
        if(position==2) {
            Intent intent = new Intent(Erkrankung.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, wird die Fallübersicht geöffnet
        if(position==3) {
            Intent intent = new Intent(Erkrankung.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird der Upload geöffnet
        if(position==4) {
            Intent intent = new Intent(Erkrankung.this, Impressum.class);
            startActivity(intent);
        }
        drawerlayout_erkrankung.closeDrawers();
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
        if (ce == R.id.imgv_before_erkrankung) {
            Intent intent = new Intent(Erkrankung.this, Falleingabe.class);
            startActivity(intent);
        }
        if(ce == R.id.imgv_menu){
            drawerlayout_erkrankung.openDrawer(GravityCompat.START);
        }
    }
    public void onPause(){
        super.onPause();
        //Eingaben werden lokal gespeichert
        speichereEingaben();
    }
    public void speichereEingaben(){
        mfall=(GlobaleDaten)getApplication();
        if (cck_erkrankung_keine.isChecked()){
            mfall.setErk_keine(1);
        }
        else
            mfall.setErk_keine(0);
        if (cck_erkrankung_alkoholisiert.isChecked()){
            mfall.setErk_alkoholisiert(1);
        }
        else
            mfall.setErk_alkoholisiert(0);
        if (cck_erkrankung_erbrechen.isChecked()){
            mfall.setErk_erbrechen(1);
        }
        else
            mfall.setErk_erbrechen(0);
        if (cck_erkrankung_schwindel.isChecked()){
            mfall.setErk_schwindel(1);
        }
        else
            mfall.setErk_schwindel(0);
        if (cck_erkrankung_herzkreislauf.isChecked()){
            mfall.setErk_herzkreislauf(1);
        }
        else
            mfall.setErk_herzkreislauf(0);
        if (cck_erkrankung_hitzeschlag.isChecked()){
            mfall.setErk_hitzeschlag(1);
        }
        else
            mfall.setErk_hitzeschlag(0);
        if (cck_erkrankung_hitzeerschoepfung.isChecked()){
            mfall.setErk_hitzeerschoepfung(1);
        }
        else
            mfall.setErk_hitzeerschoepfung(0);
        if (cck_erkrankung_vergiftung.isChecked()){
            mfall.setErk_vergiftung(1);
        }
        else
            mfall.setErk_vergiftung(0);
        if (cck_erkrankung_atmung.isChecked()){
            mfall.setErk_atmung(1);
        }
        else
            mfall.setErk_atmung(0);
        if (cck_erkrankung_unterkuehlung.isChecked()){
            mfall.setErk_unterkuehlung(1);
        }
        else
            mfall.setErk_unterkuehlung(0);
        if (cck_erkrankung_baucherkrankung.isChecked()){
            mfall.setErk_baucherkrankung(1);
        }
        else
            mfall.setErk_baucherkrankung(0);
        if (cck_erkrankung_stoffwechsel.isChecked()){
            mfall.setErk_stoffwechsel(1);
        }
        else
            mfall.setErk_stoffwechsel(0);
        if (cck_erkrankung_neurologie.isChecked()){
            mfall.setErk_neurologie(1);
        }
        else
            mfall.setErk_neurologie(0);
        if (cck_erkrankung_psychatrie.isChecked()){
            mfall.setErk_psychatrie(1);
        }
        else
            mfall.setErk_psychatrie(0);
        if (cck_erkrankung_gynaekologie.isChecked()){
            mfall.setErk_gynaekologie(1);
        }
        else
            mfall.setErk_gynaekologie(0);
        if (cck_erkrankung_kindernotfall.isChecked()){
            mfall.setErk_kindernotfall(1);
        }
        else
            mfall.setErk_kindernotfall(0);
        if (cck_erkrankung_geburtshilfe.isChecked()){
            mfall.setErk_geburtshilfe(1);
        }
        else
            mfall.setErk_geburtshilfe(0);
        if (cck_erkrankung_sonstiges.isChecked()){
            mfall.setErk_sonstiges(1);
        }
        else
            mfall.setErk_sonstiges(0);
        mfall.setErk_edtxt_sonstiges(edtxt_erkrankung_sonstiges.getText().toString());
    }

    public void setWerte() {
        mfall = (GlobaleDaten) getApplication();
        if(mfall.getErk_keine()!=null) {
            if (mfall.getErk_keine() == 1) {
                cck_erkrankung_keine.setChecked(true);
            }
        }
        if(mfall.getErk_alkoholisiert()!=null) {
            if (mfall.getErk_alkoholisiert() == 1) {
                cck_erkrankung_alkoholisiert.setChecked(true);
            }
        }
        if(mfall.getErk_erbrechen()!=null) {
            if (mfall.getErk_erbrechen() == 1) {
                cck_erkrankung_erbrechen.setChecked(true);
            }
        }
        if(mfall.getErk_schwindel()!=null) {
            if (mfall.getErk_schwindel() == 1) {
                cck_erkrankung_schwindel.setChecked(true);
            }
        }
        if(mfall.getErk_herzkreislauf()!=null) {
            if (mfall.getErk_herzkreislauf() == 1) {
                cck_erkrankung_herzkreislauf.setChecked(true);
            }
        }
        if(mfall.getErk_hitzeschlag()!=null) {
            if (mfall.getErk_hitzeschlag() == 1) {
                cck_erkrankung_hitzeschlag.setChecked(true);
            }
        }
        if(mfall.getErk_hitzeerschoepfung()!=null) {
            if (mfall.getErk_hitzeerschoepfung() == 1) {
                cck_erkrankung_hitzeerschoepfung.setChecked(true);
            }
        }
        if(mfall.getErk_vergiftung()!=null) {
            if (mfall.getErk_vergiftung() == 1) {
                cck_erkrankung_vergiftung.setChecked(true);
            }
        }
        if(mfall.getErk_atmung()!=null) {
            if (mfall.getErk_atmung() == 1) {
                cck_erkrankung_atmung.setChecked(true);
            }
        }
        if(mfall.getErk_unterkuehlung()!=null) {
            if (mfall.getErk_unterkuehlung() == 1) {
                cck_erkrankung_unterkuehlung.setChecked(true);
            }
        }
        if(mfall.getErk_baucherkrankung()!=null) {
            if (mfall.getErk_baucherkrankung() == 1) {
                cck_erkrankung_baucherkrankung.setChecked(true);
            }
        }
        if(mfall.getErk_stoffwechsel()!=null) {
            if (mfall.getErk_stoffwechsel() == 1) {
                cck_erkrankung_stoffwechsel.setChecked(true);
            }
        }
        if(mfall.getErk_neurologie()!=null) {
            if (mfall.getErk_neurologie() == 1) {
                cck_erkrankung_neurologie.setChecked(true);
            }
        }
        if(mfall.getErk_psychatrie()!=null) {
            if (mfall.getErk_psychatrie() == 1) {
                cck_erkrankung_psychatrie.setChecked(true);
            }
        }
        if(mfall.getErk_gynaekologie()!=null) {
            if (mfall.getErk_gynaekologie() == 1) {
                cck_erkrankung_gynaekologie.setChecked(true);
            }
        }
        if(mfall.getErk_kindernotfall()!=null) {
            if (mfall.getErk_kindernotfall() == 1) {
                cck_erkrankung_kindernotfall.setChecked(true);
            }
        }
        if(mfall.getErk_geburtshilfe()!=null) {
            if (mfall.getErk_geburtshilfe() == 1) {
                cck_erkrankung_geburtshilfe.setChecked(true);
            }
        }
        if(mfall.getErk_sonstiges()!=null) {
            if (mfall.getErk_sonstiges() == 1) {
                cck_erkrankung_sonstiges.setChecked(true);
            }
        }
        if((mfall.getErk_edtxt_sonstiges()!=null)){
            edtxt_erkrankung_sonstiges.setText(mfall.getErk_edtxt_sonstiges());
        }
    }
    /*  von Vivien Stumpe, 29.05.16
        Prozedur, die alle Felder sperrt,
        wenn ein Fall in der Fallübersicht ausgewählt wurde
    */
    public void sperreFelder(){
        if(mfall.getFallAusgewaehlt()) {
            cck_erkrankung_alkoholisiert.setEnabled(false);
            cck_erkrankung_atmung.setEnabled(false);
            cck_erkrankung_baucherkrankung.setEnabled(false);
            cck_erkrankung_erbrechen.setEnabled(false);
            cck_erkrankung_geburtshilfe.setEnabled(false);
            cck_erkrankung_gynaekologie.setEnabled(false);
            cck_erkrankung_herzkreislauf.setEnabled(false);
            cck_erkrankung_hitzeerschoepfung.setEnabled(false);
            cck_erkrankung_hitzeschlag.setEnabled(false);
            cck_erkrankung_keine.setEnabled(false);
            cck_erkrankung_kindernotfall.setEnabled(false);
            cck_erkrankung_neurologie.setEnabled(false);
            cck_erkrankung_psychatrie.setEnabled(false);
            cck_erkrankung_schwindel.setEnabled(false);
            cck_erkrankung_sonstiges.setEnabled(false);
            cck_erkrankung_stoffwechsel.setEnabled(false);
            cck_erkrankung_unterkuehlung.setEnabled(false);
            cck_erkrankung_vergiftung.setEnabled(false);
            edtxt_erkrankung_sonstiges.setEnabled(false);
        }
    }
}
