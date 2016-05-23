//Zuletzt geändert von Vivien Stumpe am 21.05.2016
package de.app.mepa.falleingabe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import de.app.mepa.BackgroundTaskDB;
import de.app.mepa.FalleingabeDataSource;
import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.OnBoarding;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falluebersicht.Falluebersicht;
import de.app.mepa.impressum.Impressum;

import de.app.mepa.mepa.R;
import de.app.mepa.notfallsituation.notfallsituation;
import de.app.mepa.pers_daten.Pers_daten;
import de.app.mepa.verletzung.Verletzung;
import de.app.mepa.massnahmen.Massnahmen;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.erstbefund.Erstbefund;
import de.app.mepa.bemerkung.Bemerkung;
import de.app.mepa.ersthelfermassnahmen.Ersthelfermassnahmen;
import de.app.mepa.upload.Upload;


//OnClickListener implementieren, um auf einen Klick des Benutzers zu reagieren
//von Vivien Stumpe, 03.04.16
//OnItemClickListener implementieren, um auf einen Klick im Drawer zu reagieren
//von Vivien Stumpe, 08.04.16
public class Falleingabe extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //private Buttonvariable, die auf den Button in der Activity zeigen soll
    //von Vivien Stumpe, 03.04.16
    private Button btn_fallein;

    //private Textviewvariable, die auf Persönliche Daten in der Activity zeigen soll
    //von Vivien Stumpe, 14.04.16
    private TextView txtv_pers_daten;
    private TextView txtv_notfall;
    private TextView txtv_verletzung;
    private TextView txtv_erkrankung;
    private TextView txtv_massnahmen;
    private TextView txtv_erstbefund;
    private TextView txtv_uebergabe;
    private TextView txtv_bemerkung;

    //von Vivien Stumpe, 08.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_falleingabe;
    private ListView listview_falleingabe;
    private MyAdapter myadapter_fallein;
    private int[] drawer_icons_falleingabe = {R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;
    public int img_person;
    public int img_verletzung;
    public int img_erkrankung;
    public int img_maßnahmen;
    public int img_erstbefund;
    public int img_uebergabe;
    public int img_notfall;
    public int img_bemerkung;
    private View viewToAnimate;

    private LinearLayout lnl_buttons;
    private Button btn_speichern, btn_verwerfen;
    private GlobaleDaten mfall;

    /* von Vivien Stumpe, 06.05.16
    Test der Datenbank
     */
    public static final String LOG_TAG = Falleingabe.class.getSimpleName();

    private FalleingabeDataSource dataSource;

    public void setIconPerson(int person) {
        img_person = person;
        TextView txtv_person = (TextView) findViewById(R.id.txtv_fallein_pers);
        try {
            txtv_person.setCompoundDrawablesWithIntrinsicBounds(0, img_person, 0, 0);
                ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconVerletzung(int verletzung) {
        img_verletzung = verletzung;
        TextView txtv_verletzung = (TextView) findViewById(R.id.txtv_fallein_verletzung);
        try {
            txtv_verletzung.setCompoundDrawablesWithIntrinsicBounds(0, img_verletzung, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconErkrankung(int erkrankung) {
        img_erkrankung = erkrankung;
        TextView txtv_erkrankung = (TextView) findViewById(R.id.txtv_fallein_erkrankung);
        try {
            txtv_erkrankung.setCompoundDrawablesWithIntrinsicBounds(0, img_erkrankung, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconMaßnahmen(int maßnahmen) {
        img_maßnahmen = maßnahmen;
        TextView txtv_maßnahmen = (TextView) findViewById(R.id.txtv_fallein_maßnahmen);
        try {
            txtv_maßnahmen.setCompoundDrawablesWithIntrinsicBounds(0, img_maßnahmen, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconErstbefund(int erstbefund) {
        img_erstbefund = erstbefund;
        TextView txtv_erstbefund = (TextView) findViewById(R.id.txtv_fallein_erstbef);
        try {
            txtv_erstbefund.setCompoundDrawablesWithIntrinsicBounds(0, img_erstbefund, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconUebergabe(int uebergabe) {
        img_uebergabe = uebergabe;
        TextView txtv_uebergabe = (TextView) findViewById(R.id.txtv_fallein_uebergabe);
        try {
            txtv_uebergabe.setCompoundDrawablesWithIntrinsicBounds(0, img_uebergabe, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconNotfall(int notfall) {
        img_notfall = notfall;
        TextView txtv_notfall = (TextView) findViewById(R.id.txtv_fallein_notfall);
        try {
            txtv_notfall.setCompoundDrawablesWithIntrinsicBounds(0, img_notfall, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    public void setIconBemerkung(int bemerkung) {
        img_bemerkung = bemerkung;
        TextView txtv_bemerkung = (TextView) findViewById(R.id.txtv_fallein_bemerkung);
        try {
            txtv_bemerkung.setCompoundDrawablesWithIntrinsicBounds(0, img_bemerkung, 0, 0);
            ButtonsSichtbar();
        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falleingabe);

        // ------
        //Stammdaten & Mitarbeiter müssen aus der DB ausgelesen werden & den Variablen zugewiesen werden!!


        onBoard();

        //Verbindung zwischen Variable und TextView in der Activity herstellen
        //von Vivien Stumpe, 14.04.16
        txtv_pers_daten = (TextView) findViewById(R.id.txtv_fallein_pers);
        txtv_notfall = (TextView) findViewById(R.id.txtv_fallein_notfall);
        txtv_verletzung = (TextView) findViewById(R.id.txtv_fallein_verletzung);
        txtv_erkrankung = (TextView) findViewById(R.id.txtv_fallein_erkrankung);
        txtv_massnahmen = (TextView) findViewById(R.id.txtv_fallein_maßnahmen);
        txtv_erstbefund = (TextView) findViewById(R.id.txtv_fallein_erstbef);
        txtv_uebergabe = (TextView) findViewById(R.id.txtv_fallein_uebergabe);
        txtv_bemerkung = (TextView) findViewById(R.id.txtv_fallein_bemerkung);

        //Click Event abfangen und den OnClickListener für die aktuelle View aufrufen
        //von Vivien Stumpe, 14.04.16
        txtv_pers_daten.setOnClickListener(this);
        txtv_notfall.setOnClickListener(this);
        txtv_verletzung.setOnClickListener(this);
        txtv_erkrankung.setOnClickListener(this);
        txtv_massnahmen.setOnClickListener(this);
        txtv_erstbefund.setOnClickListener(this);
        txtv_uebergabe.setOnClickListener(this);
        txtv_bemerkung.setOnClickListener(this);

        //von Vivien Stumpe, 08.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falleingabe = (DrawerLayout) findViewById(R.id.drawerLayout_Falleingabe);
        listview_falleingabe = (ListView) findViewById(R.id.listview_falleingabe);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_fallein = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_falleingabe);
        listview_falleingabe.setAdapter(myadapter_fallein);
        listview_falleingabe.setOnItemClickListener(this);

        /*von Vivien Stumpe, 12.04.16
        Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle = new ActionBarDrawerToggle(this, drawerlayout_falleingabe, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_falleingabe.addDrawerListener(actionbardrawertoggle);
        /* von Vivien Stumpe, 25.04.16
        Name der App (MEPA) wird in der Toolbar ausgeblendet
         */
        getSupportActionBar().setTitle("");

        // von Vivien Stumpe, 02.05.16
        // Standard Icons für das Kachel-Menü laden
        IconsStart();

        lnl_buttons = (LinearLayout) findViewById(R.id.lnl_falleingabe_buttons);
        btn_speichern = (Button) findViewById(R.id.btn_speichern_fallein);
        btn_verwerfen = (Button) findViewById(R.id.btn_verwerfen_fallein);
        btn_speichern.setOnClickListener(this);
        btn_verwerfen.setOnClickListener(this);

        /* von Vivien Stumpe, 02.05.16
        View die animiert werden soll zur Variable zuordnen
        View unsichtbar machen
         */
        viewToAnimate=(View)findViewById(R.id.imgv_animation);
        viewToAnimate.setVisibility(viewToAnimate.GONE);

        /* von Vivien Stumpe, 06.05.16
        Verbindung zur DB herstellen
        wenn keine DB verfügbar ist, wird mit dem DBHelper eine neue erstellt
        */
        dataSource = new FalleingabeDataSource(this);
        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        /* von Vivien Stumpe, 09.05.16
        Testweise Daten in die DB einfügen
        erst muss ein Verband bestehen, damit ein Sanitäter angelegt werden kann - FK!
        Danach kann auch ein Patient angelegt werden

        dataSource.createVerband("DRK", "DRK-13");
        dataSource.createVerband("Schulze", "Marianne");
        dataSource.createSanitaeter("Meier", "Ludwig", 1);
        dataSource.createSanitaeter("Schulze", "Marianne", 2);
        dataSource.createPatient(34, "Stumpe", "Vivien", "22.02.1994", 1);
        */
       // dataSource.insertVerband(200, mfall.getVerb_kreisv(), mfall.getVerb_ortsv());
        //dataSource.insertVerband(100, "Test", "Test2");

        //Verbindung zur DB trennen
        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();

        // Icons ggf. ändern, wenn Daten gespeichert wurden
        IconsSave();
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
        //Deklaration und Initialisierung einer Hilfsvariablen (clicked element), die die ID des geklickten Buttons erhält
        //von Vivien Stumpe, 03.04.16
        int ce = v.getId();

        //Ein Intent erzeugen, wenn die bestimmte TextView geklickt wurde
        //Das Intent stellt eine Verbindung zur angegebenen Activity (Bildschirmseite) her
        //Aufrufen der Activity mittels Intent
        //von Vivien Stumpe, 14.04.16
        if (ce == R.id.txtv_fallein_pers) {
            Intent intent = new Intent(Falleingabe.this, Pers_daten.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_notfall) {
            Intent intent = new Intent(Falleingabe.this, notfallsituation.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_verletzung) {
            Intent intent = new Intent(Falleingabe.this, Verletzung.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_erkrankung) {
            Intent intent = new Intent(Falleingabe.this, Erkrankung.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_maßnahmen) {
            Intent intent = new Intent(Falleingabe.this, Massnahmen.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_erstbef) {
            Intent intent = new Intent(Falleingabe.this, Erstbefund.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_bemerkung) {
            Intent intent = new Intent(Falleingabe.this, Bemerkung.class);
            startActivity(intent);
        }
        if (ce == R.id.txtv_fallein_uebergabe) {
            Intent intent = new Intent(Falleingabe.this, Ersthelfermassnahmen.class);
            startActivity(intent);
        }
        /*Übergabe Seite ist Ersthelfermaßnahmen Seite
        */
        // von Vivien Stumpe, 02.05.16
        if (ce == R.id.btn_speichern_fallein) {
            speichern();
        }
        if (ce == R.id.btn_verwerfen_fallein) {
            verwerfen();
        }
    }

    //von Vivien Stumpe, 08.04.16
    @Override
    //Wird aufgerufen, wenn ein Element im Drawer geklickt wurde
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
    }

    // von Vivien Stumpe, 25.04.16 aktualisiert
    private void selectItemFromDrawer(int position) {

        //Wenn das erste Element im Menü geklickt wurde, werden die Falleingabe aufgerufen
        if (position == 0) {
            Intent intent = new Intent(Falleingabe.this, Falleingabe.class);
            startActivity(intent);
        }
        //Wenn das zweite Element im Menü geklickt wurde, wird die Falluebersicht aufgerufen
        if (position == 1) {
            Intent intent = new Intent(Falleingabe.this, Falluebersicht.class);
            startActivity(intent);
        }
        //Wenn das dritte Element im Menü geklickt wurde, wird der Upload geöffnet
        if (position == 2) {
            Intent intent = new Intent(Falleingabe.this, Upload.class);
            startActivity(intent);
        }
        //Wenn das vierte Element im Menü geklickt wurde, werden die Einstellungen geöffnet
        if (position == 3) {
            Intent intent = new Intent(Falleingabe.this, Einstellungen.class);
            startActivity(intent);
        }
        //Wenn das fünfte Element im Menü geklickt wurde, wird das Impressum geöffnet
        if (position == 4) {
            Intent intent = new Intent(Falleingabe.this, Impressum.class);
            startActivity(intent);
        }
    }
    
    /* von Vivien Stumpe, 02.05.16
    Prozedur, die die Standard Icons für das Kachelmenü lädt
     */
    protected void IconsStart(){
        setIconPerson(R.drawable.person);
        setIconVerletzung(R.drawable.verletzung);
        setIconErkrankung(R.drawable.erkrankung_vergiftung);
        setIconMaßnahmen(R.drawable.massnahmen);
        setIconErstbefund(R.drawable.befund);
        setIconUebergabe(R.drawable.uebergabe);
        setIconNotfall(R.drawable.notfallsituation);
        setIconBemerkung(R.drawable.bemerkung);
    }

    /* von Vivien Stumpe, 15.05.16
    Prozedur, die prüft, ob Daten eingegeben wurden und dementsprechend die Icons für das Kachelmenü lädt
    geändert von Vivien Stumpe, 21.05.16
    */
        protected void IconsSave() {
        mfall = (GlobaleDaten) getApplication();
        //Persönliche Daten
        //Vergleich, ob die Pflichtfelder eingegeben wurden -> muss im Screen noch sichergestellt sein!
        if ((mfall.getPat_name() != null) & (mfall.getPat_vorname() != null) & (mfall.getPat_geb() != null)) {
            if ((mfall.getPat_name().length() != 0) & (mfall.getPat_vorname().length() != 0) & (mfall.getPat_geb().length() != 0))
                setIconPerson(R.drawable.person_save);
        } else {
            setIconPerson(R.drawable.person);
        }
        //Verletzung
        if (mfall.Verl_check_notNull()) {
            if (mfall.Verl_check()) {
                setIconVerletzung(R.drawable.verletzung_save);
            } else {
                if (mfall.Verl_spinner_notNull()) {
                    setIconVerletzung(R.drawable.verletzung_save);
                } else {
                    setIconVerletzung(R.drawable.verletzung);
                }
            }
        } else {
            setIconVerletzung(R.drawable.verletzung);
        }
        //Erkrankung
        if (mfall.Erk_notNull()) {
            if (mfall.Erk_eingabe()) {
                setIconErkrankung(R.drawable.erkrankung_vergiftung_save);
            }
        } else {
            setIconErkrankung(R.drawable.erkrankung_vergiftung);
        }
        //Maßnahmen
        if (mfall.Mas_notNull()) {
            if (mfall.Mas_eingabe()) {
                setIconMaßnahmen(R.drawable.massnahmen_save);
            }
        } else {
            setIconMaßnahmen(R.drawable.massnahmen);
        }
        //Erstbefund

        if (mfall.Erst_spinner_notNull()) {
            setIconErstbefund(R.drawable.befund_save);
        } else {
            if (mfall.Erst_edit_notNull()) {

                if (mfall.Erst_eingabe()) {
                    setIconErstbefund(R.drawable.befund_save);
                } else {
                    setIconErstbefund(R.drawable.befund);
                }
            }
        }


        //Übergabe
        if (mfall.Erg_spinner_notNull()) {
            setIconUebergabe(R.drawable.uebergabe_save);
        } else {
            if (mfall.Erg_check_notNull()) {
                if (mfall.Erg_check()) {
                    setIconUebergabe(R.drawable.uebergabe_save);
                } else {
                    if (mfall.Erg_edit_notNull()) {
                        if (mfall.Erg_eingabe()) {
                            setIconUebergabe(R.drawable.uebergabe_save);
                        }
                    } else {
                        setIconUebergabe(R.drawable.uebergabe);
                    }
                }
            }
        }

        //Notfallsituation
        if(mfall.getNotf_notfallsituation()!=null){
            if(mfall.getNotf_notfallsituation().length()>0)
                setIconNotfall(R.drawable.notfallsituation_save);
        }
        else {
            setIconNotfall(R.drawable.notfallsituation);
        }
        //Bemerkung
        if(mfall.getBem_bemerkung()!=null){
            if(mfall.getBem_bemerkung().length()>0)
                setIconBemerkung(R.drawable.bemerkung_save);
        }
        else {
            setIconBemerkung(R.drawable.bemerkung);
        }
    }
    /* von Vivien Stumpe, 02.05.16
    Prozedur, die prüft, ob sich ein Icon geändert hat
    und wenn dies der Fall ist, die Buttons zum Speichern oder Verwerfen anzeigt
     */
    protected void ButtonsSichtbar() {
        boolean aenderung = false;
        if (img_person!=R.drawable.person) {
            aenderung = true;
        } else if (img_verletzung!=R.drawable.verletzung) {
            aenderung = true;
        } else if (img_erkrankung!=R.drawable.erkrankung_vergiftung) {
            aenderung = true;
        } else if (img_maßnahmen!=R.drawable.massnahmen) {
            aenderung = true;
        } else if (img_erstbefund!=R.drawable.befund) {
            aenderung = true;
        } else if (img_uebergabe!=R.drawable.uebergabe) {
            aenderung = true;
        } else if (img_notfall!= R.drawable.notfallsituation) {
            aenderung = true;
        } else if (img_bemerkung!=R.drawable.bemerkung) {
            aenderung = true;
        }

        if(aenderung){
            lnl_buttons.setVisibility(lnl_buttons.VISIBLE);
        }
    }
    /* von Vivien Stumpe, 02.05.16
    Prozedur, die beim Betätigen des Speichern-Buttons aufgerufen wird
    Die Daten werden in der DB gespeichert
    Es erfolgt eine Animation, dass die Daten erfolgreich gespeichert wurden
    Die Icons werden zurückgesetzt
     */
    public void speichern(){
        
        //Simon, 18.05.16, Die ID wird generiert:

        mfall=(GlobaleDaten)getApplication();
        mfall.setFallID(true);
        //Toast.makeText(Falleingabe.this, "ID Hash: " + mfall.getFallID(), Toast.LENGTH_LONG).show(); //Zum Testen, dass sie erzeugt wird
        
        //Animation
        //Daten speichern in der DB
        //Icons zurücksetzen
        // Die View wird sichtbar -> Animation
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        viewToAnimate.startAnimation(in);
        viewToAnimate.setVisibility(View.VISIBLE);

        // ---------------
        /* von Vivien Stumpe, 10.05.16
         * BackgroundTaskDB anlegen, um mit der Datenbank im Hintergrund zu interagieren
         * -> UI-Thread wird nicht belastet
         
        String verband= "DRK";
        String ortsverein="DRk14";
        BackgroundTaskDB bgtask = new BackgroundTaskDB(this);
        // BackgroundTaskDB aufrufen mit der gewünschten Aktion
        // der erste Parameter gibt an, was der bgtask tun soll
        // -> siehe doInBackground(...) im BackgroundTaskDB
        bgtask.execute("Verein hinzufügen", verband, ortsverein);
        */
        dataSource.open();
        dataSource.insertPatient(mfall.getPatID(), mfall.getPat_name(), mfall.getPat_vorname(), mfall.getPat_geb(),
                mfall.getSaniID(), mfall.getPat_sex(), mfall.getPat_str(), mfall.getPat_plz(), mfall.getPat_ort(),
                mfall.getPat_land(), mfall.getPat_tel(), mfall.getPat_krankenkasse(),
                mfall.getPat_versnr(), mfall.getPat_versichertennr());
        
        dataSource.insertEinsatz(mfall.getPatID(), mfall.getVer_date(), mfall.getEin_zugef(), mfall.getEin_hilfs(),
                mfall.getEin_mosan(), mfall.getEin_sanw(), mfall.getNotf_notfallsituation(), mfall.getEin_fundort(), mfall.getFallID());

        dataSource.insertErkrankung(mfall.getErk_atmung(), mfall.getErk_herzkreislauf(), mfall.getErk_baucherkrankung(),
                mfall.getErk_stoffwechsel(), mfall.getErk_hitzeschlag(), mfall.getErk_vergiftung(), mfall.getErk_unterkuehlung(),
                mfall.getErk_gynaekologie(), mfall.getErk_geburtshilfe(), mfall.getErk_hitzeerschoepfung(), mfall.getErk_kindernotfall(),
                mfall.getErk_neurologie(), mfall.getErk_psychatrie(), mfall.getErk_alkoholisiert(), mfall.getErk_sonstiges(),
                mfall.getErk_edtxt_sonstiges(), mfall.getErk_schwindel(), mfall.getErk_erbrechen(), mfall.getFallID());

        dataSource.insertMassnahmen(mfall.getFallID(), mfall.getMas_stb_seitenlage(), mfall.getMas_oberkoerperhochlage(), mfall.getMas_flachlagerung(), mfall.getMas_schocklagerung(),
                mfall.getMas_vakuummatratze(), mfall.getMas_hws_stuetzkragen(), mfall.getMas_medikamente(), mfall.getMas_extr_schienung(), mfall.getMas_wundversorgung(),
                mfall.getMas_ekg(), mfall.getMas_ven_zugang(), mfall.getMas_infusion(), mfall.getMas_atemwege_freim(),
                mfall.getMas_notkompetenz(), mfall.getMas_sauerstoffgabe(), mfall.getMas_intubation(), mfall.getMas_beatmung(), mfall.getMas_herzdruckmassage(),
                mfall.getMas_erstdefibrillation(), mfall.getMas_betreuung(), mfall.getMas_sonstiges(), mfall.getMas_sonstiges_text(), mfall.getMas_keine());
        
        dataSource.insertErstbefund(mfall.getFallID(), mfall.getErst_bewusstsein(), mfall.getErst_schmerzen(), mfall.getErst_kreislauf(),
                mfall.getErst_ekg(), mfall.getErst_atmung(), mfall.getErst_rr_sys(), mfall.getErst_rr_dia(),
                mfall.getErst_puls(), mfall.getErst_af(), mfall.getErst_spo(), mfall.getErst_bz(), 
                mfall.getErst_pupille_li(), mfall.getErst_pupille_re());
                
                        //von Nicolas Simon 23.05.16
        dataSource.insertVerletzung(mfall.getFallID(), mfall.getVerl_elektrounfall(), mfall.getVerl_wunde_verletzung(), mfall.getVerl_inhalationstrauma(),
                mfall.getVerl_sonstiges(), mfall.getVerl_verbrennung(), mfall.getVerl_prellung_verletzung(), mfall.getVerl_schaedel_art(), mfall.getVerl_gesicht_art(),
                mfall.getVerl_brustkorb_art(), mfall.getVerl_bws_lws_art(), mfall.getVerl_hws_art(), mfall.getVerl_becken_art(), mfall.getVerl_bauch_art(),
                mfall.getVerl_beine_art(), mfall.getVerl_arme_art(), mfall.getVerl_weichteile_art(), mfall.getVerl_schaedel_grad(), mfall.getVerl_gesicht_grad(),
                mfall.getVerl_brustkorb_grad(), mfall.getVerl_bws_lws_grad(), mfall.getVerl_hws_grad(), mfall.getVerl_becken_grad(), mfall.getVerl_bauch_grad(),
                mfall.getVerl_beine_grad(), mfall.getVerl_arme_grad(), mfall.getVerl_weichteile_grad());

        //von Nicolas Simon 23.05.16
        dataSource.insertErgebnis(mfall.getFallID(), mfall.getErg_ergebnis_zeit(), mfall.getErg_zustand(), mfall.getErg_wertsachen(),
                mfall.getBem_bemerkung(), mfall.getErg_funkruf(), mfall.getErg_transport(), mfall.getErg_transport_ziel(),
                mfall.getErg_entlassung_ev(), mfall.getErg_zeuge(), mfall.getErg_zustand(),
                mfall.getErg_notarzt(), mfall.getErg_hausarzt_informiert(), mfall.getErg_tod(), mfall.getErg_transport_sonstiges(),
                mfall.getErg_ersthelfermassn(),
                mfall.getErg_nachforderung_ktw(), mfall.getErg_nachforderung_rtw(), mfall.getErg_nachforderung_nef(), mfall.getErg_nachforderung_naw(),
                mfall.getErg_nachforderung_rth(), mfall.getErg_nachforderung_feuerwehr(), mfall.getErg_nachforderung_polizei());
        // von Vivien Stumpe, 02.05.16
        //Icons zurücksetzen & Speichern Verwerfen-Buttons ausblenden
       eingabenZuruecksetzen();
        IconsStart();
        lnl_buttons.setVisibility(lnl_buttons.GONE);
        //Die View wird wieder unsichtbar -> Animation
        Animation out = AnimationUtils.makeOutAnimation(this, true);
        viewToAnimate.startAnimation(out);
        viewToAnimate.setVisibility(View.INVISIBLE);
    }
    /* von Vivien Stumpe, 02.05.16
    Prozedur, die beim Betätigen des Verwerfen-Buttons aufgerufen wird
    Die Daten werden nicht in der DB gespeichert
    Die Icons werden zurückgesetzt
    Die Buttons verschwinden
    geändert am 15.05.16 -> eingabenZuruecksetzen();
     */
    public void verwerfen(){
        eingabenZuruecksetzen();
        //Icons zurücksetzen
        IconsStart();
        lnl_buttons.setVisibility(lnl_buttons.GONE);
    }
    /* von Vivien Stumpe, 15.05.16
    Prozedur, die alle gemachten Eingaben lokal löscht
     */
    public void eingabenZuruecksetzen(){
        mfall=(GlobaleDaten)getApplication();
        mfall.loeschePat();
        mfall.loescheEin();
        mfall.loescheBem();
        mfall.loescheErg();
        mfall.loescheErst();
        mfall.loescheNotf();
        mfall.loescheVerl();
        mfall.loescheMas();
        mfall.loescheErk();
    }

    public void onBoard() {
        mfall = (GlobaleDaten) getApplication();
        // Wurde übersprungen?
        if (!mfall.getUebersprungen()) {
            GlobaleDaten mverband, mveranstaltung, msani;
            dataSource=new FalleingabeDataSource(this);
            dataSource.open();
            mverband=dataSource.selectVerband();
            mveranstaltung=dataSource.selectVeranstaltung();
            msani=dataSource.selectSani();
            dataSource.close();
            mfall.setVerb_ID(mverband.getVerbandID());
            mfall.setVerb_kreisv(mverband.getVerb_kreisv());
            mfall.setVerb_ortsv(mverband.getVerb_ortsv());
            mfall.setVer_name(mveranstaltung.getVer_name());
            mfall.setVer_ort(mveranstaltung.getVer_ort());
            mfall.setVer_date(mveranstaltung.getVer_date());
            mfall.setSan_name(msani.getSan_name());
            mfall.setSan_vorname(msani.getSan_vorname());
            mfall.setSani_IDm(msani.getSaniID());
  //wenn nicht -> Gibt es Stammdaten zum Fall?
            if(!((mfall.getVer_name()!=null&mfall.getVer_ort()!=null&mfall.getVer_date()!=null)
                    &(mfall.getSan_name()!=null&mfall.getSan_vorname()!=null)
                    &(mfall.getVerb_kreisv()!=null&mfall.getVerb_ortsv()!=null))){

          
           // if (!mfall.getFall_angelegt()) {
                //wenn nicht -> onBoarding Activity starten
                Intent onboarding = new Intent(this, OnBoarding.class);
                startActivity(onboarding);
                //Falleingabe schließen
                finish();
            }
        }
    }
}
