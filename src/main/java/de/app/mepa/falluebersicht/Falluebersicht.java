//Zuletzt geändert von Vivien Stumpe 05.06.16
package de.app.mepa.falluebersicht;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.app.mepa.Adapter_Falluebersicht;
import de.app.mepa.FalleingabeDataSource;
import de.app.mepa.GlobaleDaten;
import de.app.mepa.MyAdapter;
import de.app.mepa.einstellungen.Einstellungen;
import de.app.mepa.falleingabe.Falleingabe;
import de.app.mepa.impressum.Impressum;
import de.app.mepa.mepa.R;
import de.app.mepa.pers_daten.Pers_daten;
import de.app.mepa.upload.Upload;

public class Falluebersicht extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //Button-Variablen für die Buttons in der Einstellungen Activity
    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16

    /* Vivien Stumpe, 01.05.16
    Das LinearLayout gibt es in der Fallübersicht nicht?
    private LinearLayout lnl_buttons;
    */

    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
    //DrawerLayout für das Hamburger Menü
    //ListView, die die Einträge des Menüs enthält
    //Adapter, der die Einträge der ListView darstellt
    //Array mit den Icons, die im Menü dargestellt werden sollen
    private DrawerLayout drawerlayout_falluebersicht;
    private ListView listview_falluebersicht;
    private MyAdapter myadapter_falluebersicht;
    private int[] drawer_icons_falluebersicht={R.drawable.falleingabe,
            R.drawable.falluebersicht, R.drawable.upload, R.drawable.einstellungen, R.drawable.impressum};

    /*Nicolas Simon, übernommen von Vivien Stumpe, 12.04.16
    Der ActionBarDrawerToggle sorgt dafür, dass das DrawerLayout in der übergebenen Toolbar angezeigt wird
    ActionBarDrawerToggle und Toolbar anlegen
    */
    private ActionBarDrawerToggle actionbardrawertoggle;
    Toolbar toolbar;

    private Button btn_speichern_fallueb;
    private Button btn_loeschen_fallueb;

    /* Vivien Stumpe, 21.05.16
    Adapter vom Typ Adapter_Falluebersicht, damit die Daten auch dargestellt werden können
    */

    ListView falluebersichtListView;
    Adapter_Falluebersicht faelleAdapter;
    FalleingabeDataSource dataSource;
    GlobaleDaten mfall;
    GlobaleDaten mfalldaten, mpat;
    String[] patList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falluebersicht);
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht = (DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht = (ListView) findViewById(R.id.listview_falluebersicht);
        //Zuweisen der Button-Variablen zu den Buttons in der Activity
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb = (Button) findViewById(R.id.btn_loeschen_fallueb);
        btn_speichern_fallueb = (Button) findViewById(R.id.btn_speichern_fallueb);
        // Events abfangen und an den OnClickListener die aktuelle View übergeben
        //von Vivien Stumpe, 01.04.16, eingefügt von Nicolas Simon 12.04.16
        btn_loeschen_fallueb.setOnClickListener(this);
        btn_speichern_fallueb.setOnClickListener(this);


        //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
        //zuweisen des Drawers und der ListView zu den Elementen in der xml Datei
        drawerlayout_falluebersicht = (DrawerLayout) findViewById(R.id.drawerLayout_Falluebersicht);
        listview_falluebersicht = (ListView) findViewById(R.id.listview_falluebersicht);
        //Adapter erzeugen und setzen, um die Einträge der ListView darzustellen
        myadapter_falluebersicht = new MyAdapter(this, this.getResources().getStringArray(R.array.drawer_nav_neu), drawer_icons_falluebersicht);
        listview_falluebersicht.setAdapter(myadapter_falluebersicht);
        //OnItemClickListener auf die ListView aktivieren, damit auf Klicks reagiert wird
        listview_falluebersicht.setOnItemClickListener(this);

       /* Verbindung zur Toolbar in der Acitivity herstellen
        Toolbar anstelle der ActionBar verwenden
        ActionBarDrawerToggle initialisieren
        DrawerListener setzen, damit registriert wird, welchen Status der Drawer hat
        */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbardrawertoggle = new ActionBarDrawerToggle(this, drawerlayout_falluebersicht, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout_falluebersicht.addDrawerListener(actionbardrawertoggle);


        /* von Vivien Stumpe, 21.05.16
        Die Listview wird mit den Fällen befüllt
        Die Einträge in der ListView sind klickbar (Löschen Icon siehe Adapter_Falluebersicht.java)
        Wird ein Eintrag geklickt gelangt man zur Falleingabe und die gespeicherten Daten werden angezeigt
         */
        dataSource=new FalleingabeDataSource(this);
        dataSource.open();
       // showAllListEntries();
        showAllListEntriesNeu();
       falluebersichtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent fall = new Intent(Falluebersicht.this, Falleingabe.class);
                // von Vivien Stumpe, 23.05.16
                //Teilt den String bei einem Leerzeichen
                String string = patList[position];
                String[] parts = string.split("\\s+");
                String prot_id_string = parts[0];

                Log.d("Fall", prot_id_string + " Übergebene Fall-ID aus der Listview");
                String name = parts[1];
                Log.d("Fall", name + " Übergebener Name aus der Listview");
                //prot_id enthält die ID des ausgewählten Falls
                int prot_id = Integer.parseInt(prot_id_string);
                ladeFallDaten(prot_id);
                mfall=(GlobaleDaten)getApplication();
                mfall.setFallAusgewahelt(true);
                startActivity(fall);
            }
        });

        mfall=(GlobaleDaten)getApplication();
        dataSource.close();
    }
    /* von Vivien Stumpe, 21.05.16
    Prozedur, die alle Fälle aus der DB liest und in der ListView darstellt
    !!Muss noch angepasst werden, wenn Daten aus der DB kommen!
     */
    private void showAllListEntries() {
        List<String> fallListe = new ArrayList<>(Arrays.asList(patList));
        faelleAdapter =
                new Adapter_Falluebersicht(
                        Falluebersicht.this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.falluebersicht_listview_item, // ID der XML-Layout Datei
                        patList); // Beispieldaten in einer ArrayList
        falluebersichtListView = (ListView)findViewById(R.id.list_falluebersicht);
        falluebersichtListView.setAdapter(faelleAdapter);
      /*  GlobaleDaten pat;
        dataSource=new FalleingabeDataSource(this);
        dataSource.open();
        pat=dataSource.selectPatient();
        dataSource.close();
        mfall.setPat_name(pat.getPat_name());
        Log.d("Fall", mfall.getPat_name());
        */
    }
    public void showAllListEntriesNeu () {
        patList = dataSource.getAllPat();

        faelleAdapter =
                new Adapter_Falluebersicht(
                        Falluebersicht.this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.falluebersicht_listview_item, // ID der XML-Layout Datei
                        patList); // Beispieldaten in einer ArrayList
        falluebersichtListView = (ListView)findViewById(R.id.list_falluebersicht);
        falluebersichtListView.setAdapter(faelleAdapter);
    }


    /*

    //von Nathalie Horn, 23.05.16
    //FalleingabeDataSource datasource = new FalleingabeDataSource(this);

    public void showAllListEntries() {
        List<GlobaleDaten> UebersichtList = dataSource.getUebersicht();

        ArrayAdapter<GlobaleDaten> UebersichtAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                UebersichtList);

        ListView UebersichtListView = (ListView) findViewById(R.id.list_falluebersicht);
        UebersichtListView.setAdapter(UebersichtAdapter);
    }
    */

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        //Hamburger Symbol mit dem Status des Drawers gleichsetzen (ob es geschlossen oder geöffnet ist)
        actionbardrawertoggle.syncState();
    }

    @Override
    public void onClick(View v) {
        //clicked element mit dem geklickten Button belegen
        //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
        int ce = v.getId();

    }

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

    }
    //Nicolas Simon, übernommen von Vivien Stumpe, 17.04.16
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Aufruf der Prozedur mit Übergabe der Position des geklickten Items/Menüpunkt
        selectItemFromDrawer(position);
        finish();
    }

    /* von Vivien Stumpe, 23.05.16
    Prozedur, die die Daten zum ausgewählten Fall lädt
    */
    public void ladeFallDaten(int id){
        dataSource.open();
        mfalldaten=dataSource.selectFall(id);
        mpat=dataSource.selectPat(id);
        patientendatenSpeichern(mpat);
        mfall=(GlobaleDaten)getApplication();
        mfalldaten=dataSource.selectFall(id);
        fallDatenSpeichern(mfalldaten);
    }
    /* von Vivien Stumpe, 27.05.16
    Prozedur, die die Patientendaten aus der DB zwischenspeichert
    damit sie im Screen angezeigt werden können
     */
    private void patientendatenSpeichern(GlobaleDaten mpatient){
        mfall=(GlobaleDaten)getApplication();
        mfall.setPat_name(mpatient.getPat_name());
        mfall.setPat_vorname(mpatient.getPat_vorname());
        mfall.setPat_geb(mpatient.getPat_geb());
        mfall.setPat_sex(mpatient.getPat_sex());
        mfall.setPat_tel(mpatient.getPat_tel());
        mfall.setPat_str(mpatient.getPat_str());
        mfall.setPat_plz(mpatient.getPat_plz());
        mfall.setPat_ort(mpatient.getPat_ort());
        mfall.setPat_land(mpatient.getPat_land());
        mfall.setPat_IDm(mpatient.getPatID());
        mfall.setPat_krankenkasse(mpatient.getPat_krankenkasse());
        mfall.setPat_versichertennr(mpatient.getPat_versichertennr());
        mfall.setPat_versnr(mpatient.getPat_versnr());
        mfall.setPat_sani(mpatient.getPat_sani());
        mfall.setEin_ID(mpatient.getFallID());
    }
    /*  von Vivien Stumpe, 27.05.16
        Prozedur, die die Daten aus der DB zum gewählten Fall übernimmt
        und diese dann zwischenspeichert,
        damit sie in den Screens angezeigt werden
     */
    private void fallDatenSpeichern(GlobaleDaten meinsatz){
        mfall=(GlobaleDaten)getApplication();
        //Verletzung
        mfall.setVerl_elektrounfall(meinsatz.getVerl_elektrounfall());
        mfall.setVerl_wunde_verletzung(meinsatz.getVerl_wunde_verletzung());
        mfall.setVerl_inhalationstrauma(meinsatz.getVerl_inhalationstrauma());
        mfall.setVerl_sonstiges(meinsatz.getVerl_sonstiges());
        mfall.setVerl_verbrennung(meinsatz.getVerl_verbrennung());
        mfall.setVerl_prellung_verletzung(meinsatz.getVerl_prellung_verletzung());
        mfall.setVerl_schaedel_art(meinsatz.getVerl_schaedel_art());
        mfall.setVerl_gesicht_art(meinsatz.getVerl_gesicht_art());
        mfall.setVerl_brustkorb_art(meinsatz.getVerl_brustkorb_art());
        mfall.setVerl_bws_lws_art(meinsatz.getVerl_bws_lws_art());
        mfall.setVerl_hws_art(meinsatz.getVerl_hws_art());
        mfall.setVerl_becken_art(meinsatz.getVerl_becken_art());
        mfall.setVerl_bauch_art(meinsatz.getVerl_bauch_art());
        mfall.setVerl_beine_art(meinsatz.getVerl_beine_art());
        mfall.setVerl_arme_art(meinsatz.getVerl_arme_art());
        mfall.setVerl_weichteile_art(meinsatz.getVerl_weichteile_art());
        mfall.setVerl_schaedel_grad(meinsatz.getVerl_schaedel_grad());
        mfall.setVerl_gesicht_grad(meinsatz.getVerl_gesicht_grad());
        mfall.setVerl_brustkorb_grad(meinsatz.getVerl_brustkorb_grad());
        mfall.setVerl_bws_lws_grad(meinsatz.getVerl_bws_lws_grad());
        mfall.setVerl_hws_grad(meinsatz.getVerl_hws_grad());
        mfall.setVerl_becken_grad(meinsatz.getVerl_becken_grad());
        mfall.setVerl_bauch_grad(meinsatz.getVerl_bauch_grad());
        mfall.setVerl_beine_grad(meinsatz.getVerl_beine_grad());
        mfall.setVerl_arme_grad(meinsatz.getVerl_arme_grad());
        mfall.setVerl_weichteile_grad(meinsatz.getVerl_weichteile_grad());

        //Erkrankung/Vergiftung
        mfall.setErk_atmung(meinsatz.getErk_atmung());
        mfall.setErk_herzkreislauf(meinsatz.getErk_herzkreislauf());
        mfall.setErk_baucherkrankung(meinsatz.getErk_baucherkrankung());
        mfall.setErk_stoffwechsel(meinsatz.getErk_stoffwechsel());
        mfall.setErk_hitzeschlag(meinsatz.getErk_hitzeschlag());
        mfall.setErk_vergiftung(meinsatz.getErk_vergiftung());
        mfall.setErk_unterkuehlung(meinsatz.getErk_unterkuehlung());
        mfall.setErk_gynaekologie(meinsatz.getErk_gynaekologie());
        mfall.setErk_geburtshilfe(meinsatz.getErk_geburtshilfe());
        mfall.setErk_hitzeerschoepfung(meinsatz.getErk_hitzeerschoepfung());
        mfall.setErk_kindernotfall(meinsatz.getErk_kindernotfall());
        mfall.setErk_neurologie(meinsatz.getErk_neurologie());
        mfall.setErk_psychatrie(meinsatz.getErk_psychatrie());
        mfall.setErk_alkoholisiert(meinsatz.getErk_alkoholisiert());
        mfall.setErk_sonstiges(meinsatz.getErk_sonstiges());
        mfall.setErk_edtxt_sonstiges(meinsatz.getErk_edtxt_sonstiges());
        mfall.setErk_schwindel(meinsatz.getErk_schwindel());
        mfall.setErk_erbrechen(meinsatz.getErk_erbrechen());

        //Maßnahmen
        mfall.setMas_stb_seitenlage(meinsatz.getMas_stb_seitenlage());
        mfall.setMas_oberkoerperhochlage(meinsatz.getMas_oberkoerperhochlage());
        mfall.setMas_flachlagerung(meinsatz.getMas_flachlagerung());
        mfall.setMas_schocklagerung(meinsatz.getMas_schocklagerung());
        mfall.setMas_vakuummatratze(meinsatz.getMas_vakuummatratze());
        mfall.setMas_hws_stuetzkragen(meinsatz.getMas_hws_stuetzkragen());
        mfall.setMas_medikamente(meinsatz.getMas_medikamente());
        mfall.setMas_extr_schienung(meinsatz.getMas_extr_schienung());
        mfall.setMas_wundversorgung(meinsatz.getMas_wundversorgung());
        mfall.setMas_ekg(meinsatz.getMas_ekg());
        mfall.setMas_ven_zugang(meinsatz.getMas_ven_zugang());
        mfall.setMas_infusion(meinsatz.getMas_infusion());
        mfall.setMas_atemwege_freim(meinsatz.getMas_atemwege_freim());
        mfall.setMas_notkompetenz(meinsatz.getMas_notkompetenz());
        mfall.setMas_sauerstoffgabe(meinsatz.getMas_sauerstoffgabe());
        mfall.setMas_intubation(meinsatz.getMas_intubation());
        mfall.setMas_beatmung(meinsatz.getMas_beatmung());
        mfall.setMas_herzdruckmassage(meinsatz.getMas_herzdruckmassage());
        mfall.setMas_erstdefibrillation(meinsatz.getMas_erstdefibrillation());
        mfall.setMas_betreuung(meinsatz.getMas_betreuung());
        mfall.setMas_sonstiges(meinsatz.getMas_sonstiges());
        mfall.setMas_sonstiges_text(meinsatz.getMas_sonstiges_text());
        mfall.setMas_keine(meinsatz.getMas_keine());

        //Erstbefund
        mfall.setErst_bewusstsein(meinsatz.getErst_bewusstsein());
        mfall.setErst_schmerzen(meinsatz.getErst_schmerzen());
        mfall.setErst_kreislauf(meinsatz.getErst_kreislauf());
        mfall.setErst_ekg(meinsatz.getErst_ekg());
        mfall.setErst_atmung(meinsatz.getErst_atmung());
        mfall.setErst_rr_sys(meinsatz.getErst_rr_sys());
        mfall.setErst_rr_dia(meinsatz.getErst_rr_dia());
        mfall.setErst_puls(meinsatz.getErst_puls());
        mfall.setErst_af(meinsatz.getErst_af());
        mfall.setErst_spo(meinsatz.getErst_spo());
        mfall.setErst_bz(meinsatz.getErst_bz());
        mfall.setErst_pupille_li(meinsatz.getErst_pupille_li());
        mfall.setErst_pupille_re(meinsatz.getErst_pupille_re());

        //Übergabe
        mfall.setErg_wertsachen(meinsatz.getErg_wertsachen());
        mfall.setBem_bemerkung(meinsatz.getBem_bemerkung());
        mfall.setErg_funkruf(meinsatz.getErg_funkruf());
        mfall.setErg_transport(meinsatz.getErg_transport());
        mfall.setErg_transport_ziel(meinsatz.getErg_transport_ziel());
        mfall.setErg_entlassung_ev(meinsatz.getErg_entlassung_ev());
        mfall.setErg_zeuge(meinsatz.getErg_zeuge());
        mfall.setErg_zustand(meinsatz.getErg_zustand());
        mfall.setErg_notarzt(meinsatz.getErg_notarzt());
        mfall.setErg_hausarzt_informiert(meinsatz.getErg_hausarzt_informiert());
        mfall.setErg_tod(meinsatz.getErg_tod());
        mfall.setErg_transport_sonstiges(meinsatz.getErg_transport_sonstiges());
        mfall.setErg_ersthelfermassn(meinsatz.getErg_ersthelfermassn());
        mfall.setErg_nachforderung_ktw(meinsatz.getErg_nachforderung_ktw());
        mfall.setErg_nachforderung_rtw(meinsatz.getErg_nachforderung_rtw());
        mfall.setErg_nachforderung_nef(meinsatz.getErg_nachforderung_nef());
        mfall.setErg_nachforderung_naw(meinsatz.getErg_nachforderung_naw());
        mfall.setErg_nachforderung_rth(meinsatz.getErg_nachforderung_rth());
        mfall.setErg_nachforderung_feuerwehr(meinsatz.getErg_nachforderung_feuerwehr());
        mfall.setErg_nachforderung_polizei(meinsatz.getErg_nachforderung_polizei());
        mfall.setErg_ergebnis_zeit(meinsatz.getErg_ergebnis_zeit());

        //Einsatz
        mfall.setEin_mosan(meinsatz.getEin_mosan());
        mfall.setEin_sanw(meinsatz.getEin_sanw());
        mfall.setEin_hilfs(meinsatz.getEin_hilfs());
        mfall.setEin_zugef(meinsatz.getEin_zugef());
        mfall.setEin_fundort(meinsatz.getEin_fundort());
        mfall.setNotf_notfallsituation(meinsatz.getNotf_notfallsituation());
    }
    /*  von Vivien Stumpe, 05.06.16
    zurück zur Falleingabe beim Drücken des Zurückpfeils des Smartphones
 */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Falleingabe.class);
        startActivity(intent);
        finish();
    }
}
