//Zuletzt geändert von Vivien Stumpe, 30.05.16
package de.app.mepa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.util.Log;
import android.util.Xml;

/**
 * Created by vivienstumpe on 29.05.16.
 * Klasse, die eine XML Datei erzeugen kann
 * Mithilfe eines Parameters vom Typ GlobaleDaten wird ein Objekt übergeben
 * und dessen Attribute werden in die XML Datei geschrieben
 * Quellen:
 * https://developer.android.com/reference/org/xmlpull/v1/XmlSerializer.html
 * http://www.anddev.org/write_a_simple_xml_file_in_the_sd_card_using_xmlserializer-t8350.html
 */
public class XMLCreator {

    /*  von Vivien Stumpe, 29.05.16
        TAGS als String Konstanten
    */
    private static final String ROOT = "clinicalDocument";
    private static final String RECORDTARGET = "recordTarget";
    private static final String PAT_ROlE = "patientRole";
    private static final String PAT="patient";
    private static final String ID = "id";
    private static final String ADDR = "addr";
    private static final String STR = "streetName";
    private static final String HAUSNR = "houseNumber";
    private static final String PLZ = "postalCode";
    private static final String ORT = "city";
    private static final String TEL= "telecom";
    private static final String NAME="name";
    private static final String GIVEN="given";
    private static final String FAM="family";
    private static final String GENCODE="administrativeGenderCode";
    private static final String BIRTH="birthTime";
    private static final String AUT="author";
    private static final String ASSIGNAUT="assignedAuthor";
    private static final String ASSIGPER="assignedPerson";
    private static final String REPRORG="representedOrganization";
    private static final String BODY="structuredBody";
    private static final String SEC="section";
    private static final String LAND="land";
    private static final String KRANKENK="krankenkasse";
    private static final String KRKNR="krankenkassennr";
    private static final String VERSNR="versichertennr";
    private static final String SANID="sanitaeter_id";
    private static final String KREISV="kreisverband";
    private static final String ORTSV="ortsverein";
    private static final String VERNAME="veranstaltungsname";
    private static final String VERORT="veranstaltungsort";
    private static final String VERDATE="veranstaltungsdatum";
    private static final String EINDATE="einsatzdatum";
    private static final String EINBEG="einsatzbeginn";
    private static final String EINEND="einsatzende";
    private static final String ZUGEF="zugefuehrt_durch";
    private static final String HILFS="hilfsstele";
    private static final String MOSAN="mosan_team";
    private static final String SANW="san_wache";
    private static final String EINPOL="polizei_txt";
    private static final String EINRTW="rtw_ktw_txt";
    private static final String EINSAN="san_team_txt";
    private static final String FUNDORT="fundort";
    private static final String NOTF="notfallsituation";
    private static final String STBSEIT="stabile_seitenlage";
    private static final String OBERKHOCH="oberkoerper_hochlagerung";
    private static final String FLACHL="flachlagerung";
    private static final String SCHOCKL="schocklagerung";
    private static final String VAKUUM="vakuummatratze";
    private static final String HWSST="hws_stuetzkragen";
    private static final String MEDI="medikamente";
    private static final String EXTRSCH="extr_schienung";
    private static final String WUNDV="wundversorgung";
    private static final String EKG="ekg";
    private static final String VENZUG="ven_zugang";
    private static final String INF="infusion";
    private static final String ATEMW="atemwege_freimachen";
    private static final String NOTK="notkompetenz";
    private static final String SAUERST="sauerstoffgabe";
    private static final String INT="intubation";
    private static final String BEATM="beatmung";
    private static final String HERZDM="herzdruckmassage";
    private static final String ERSTDEF="erstdefibrillation";
    private static final String BETR="betreuung";
    private static final String SONST="sonstiges";
    private static final String SCHAEDLG="schaedel_hirn_grad";
    private static final String GESIG="gesicht_grad";
    private static final String HWSG="hws_grad";
    private static final String BRUSTG="brustkorb_grad";
    private static final String BAUCHG="bauch_grad";
    private static final String BWSG="bws_lws_grad";
    private static final String BECKENG="becken_grad";
    private static final String ARMEG="arme_grad";
    private static final String BEINEG="beine_grad";
    private static final String WEICHTG="weichteile_grad";
    private static final String SCHAEDLA="schaedel_hirn_art";
    private static final String GESIA="gesicht_art";
    private static final String HWSA="hws_art";
    private static final String BRUSTA="brustkorb_art";
    private static final String BAUCHA="bauch_art";
    private static final String BWSA="bws_lws_art";
    private static final String BECKENA="becken_art";
    private static final String ARMEA="arme_art";
    private static final String BEINEA="beine_art";
    private static final String WEICHTA="weichteile_art";
    private static final String VERBR="verbrennung";
    private static final String ELEKTRO="elektrounfall";
    private static final String INHALAT="inhalationstrauma";
    private static final String PRELL="prellung_verletzung";
    private static final String WUND="wunde_verletzung";
    private static final String ATM="atmung";
    private static final String HERZK="herz_kreislauf";
    private static final String BAUCHE="baucherkrankung";
    private static final String STOFFW="stoffwechsel";
    private static final String HITZS="hitzschlag";
    private static final String VERG="vergiftung";
    private static final String UNTERK="unterkuehlung";
    private static final String GYN="gynaekologie";
    private static final String GEBURT="geburtshilfe";
    private static final String HITZE="hitzeerschoepfung";
    private static final String KIND="kindernotfall";
    private static final String NEURO="neurologie";
    private static final String PSYCH="psychatrie";
    private static final String ALK="alkoholisiert";
    private static final String ERBR="uebelkeit_erbrechen";
    private static final String SCHW="schwindel";
    private static final String BEW="bewusstsein";
    private static final String SCHM="schmerzen";
    private static final String KREISL="kreislauf";
    private static final String RRSYS="rr_sys";
    private static final String RRDIA="rr_dia";
    private static final String PULS="puls";
    private static final String AF="af";
    private static final String SPO="spo2";
    private static final String BZ="bz";
    private static final String PUPILLI="pupille_li";
    private static final String PUPILRE="pupille_re";
    private static final String ZUSTVERB="zustand_verbessert";
    private static final String WERTS="wertsachen";
    private static final String WERTSZ="wertsachen_zeit";
    private static final String KTWN="nachforderung_ktw";
    private static final String ZEITN="nachforderung_zeit";
    private static final String RTWN="nachforderung_rtw";
    private static final String NEFN="nachforderung_nef";
    private static final String NAWN="nachforderung_naw";
    private static final String RTHN="nachforderung_rth";
    private static final String FEUERWN="nachforderung_feuerwehr";
    private static final String POLN="nachforderung_polizei";
    private static final String FUNK="funkruf";
    private static final String FUNKZ="funkruf_zeit";
    private static final String EV="entlassung_ev";
    private static final String ZEUG="zeuge";
    private static final String ZUST="zustand";
    private static final String HAUSA="hausarzt";
    private static final String TOD="tod";
    private static final String TRNSSONST="transport_sonstiges";
    private static final String ERGZ="ergebnis_zeit";
    private static final String ERSTH="ersthelfermassnahmen";
    private static final String BEM="bemerkungen";
    private static final String FOTO="foto";
    private static final String SONSTTXT="sonstiges_text";

    /*  von Vivien Stumpe, 29.05.16
        Prozedur, die die XML Datei erzeugt,
        erwartet wird ein aktueller Fall -> Objekt vom Typ GlobaleDaten
        bearbeitet von Vivien Stumpe, 30.05.16
     */
    public void schreibeXML(GlobaleDaten fall) {

        /*  von Vivien Stumpe, 29.05.16
            XML-Datei im MEPA_Dateiordner mit der Fall-ID als Namen anlegen
            Werte des übergebenen Falls werden eingesetzt
         */

        File ordner;
        ordner = new File(Environment.getExternalStorageDirectory(), "MEPA_Dateiordner");
        if (!ordner.exists()) {
            ordner.mkdirs();
        }
        File newxmlfile = new File(ordner, fall.getFallID() + ".xml");
        try {
            newxmlfile.createNewFile();
        } catch (IOException e) {
            Log.e("IOException", "exception in createNewFile() method");
        }
        //FileOutputStream
        FileOutputStream fileos = null;
        try {
            fileos = new FileOutputStream(newxmlfile);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "can't create FileOutputStream");
        }
        //XML Creator erstellen, damit eine XML Datei geschrieben werden kann
        XmlSerializer serializer = Xml.newSerializer();
        try {

            //FileOutputStream als Output des Serializers setzen, UTF-8 encoding
            serializer.setOutput(fileos, "UTF-8");
            // <?xml declaration (if encoding not null) and standalone flag (if standalone not null)
            serializer.startDocument(null, Boolean.valueOf(true));
            //set indentation option
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            //Root -> ClinicalDocument
            serializer.startTag(null, ROOT);

            serializer.startTag(null, RECORDTARGET);
            serializer.attribute(null, "typeCode", "RCT");
            serializer.attribute(null, "contextControlCode", "OP");

            //PatientRole
            serializer.startTag(null, PAT_ROlE);
            serializer.attribute(null, "classCode", "PAT");

            serializer.startTag(null, ID);
            serializer.text(String.valueOf(fall.getFallID()));
            serializer.endTag(null, ID);

            //Adresse
            serializer.startTag(null, ADDR);
            serializer.attribute(null, "use", "HP");

            serializer.startTag(null, STR);
            if(fall.getPat_str()!=null) {
                serializer.text(fall.getPat_str());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, STR);

            serializer.startTag(null, PLZ);
            if(fall.getPat_plz()!=null) {
                serializer.text(fall.getPat_plz());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, PLZ);

            serializer.startTag(null, ORT);
            if(fall.getPat_ort()!=null) {
                serializer.text(fall.getPat_ort());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ORT);

            serializer.endTag(null, ADDR);

            //Telefon
            serializer.startTag(null, TEL);
            serializer.attribute(null, "use", "HP");
            if(fall.getPat_tel()!=null) {
                serializer.attribute(null, "value", fall.getPat_tel());
            }
            else{
                serializer.attribute(null, "value", "Telefonnummer");
            }

            serializer.endTag(null, TEL);

            //Classcode
            serializer.startTag(null, PAT);
            serializer.attribute(null, "classCode", "PSN");
            serializer.attribute(null, "determinerCode", "INSTANCE");

            serializer.startTag(null, NAME);

            serializer.startTag(null, GIVEN);
            serializer.text(fall.getPat_vorname());
            serializer.endTag(null, GIVEN);

            serializer.startTag(null, FAM);
            serializer.text(fall.getPat_name());
            serializer.endTag(null, FAM);

            serializer.endTag(null, NAME);

            serializer.startTag(null, GENCODE);
            serializer.text("gencode");
            serializer.endTag(null, GENCODE);

            serializer.startTag(null, BIRTH);
            serializer.text(fall.getPat_geb());
            serializer.endTag(null, BIRTH);

            serializer.endTag(null, PAT);
            serializer.endTag(null, PAT_ROlE);
            serializer.endTag(null, RECORDTARGET);

            //Autor -> Sani
            serializer.startTag(null, AUT);
            serializer.startTag(null, ASSIGNAUT);
            serializer.attribute(null, "classCode", "ASSIGNED");

            serializer.startTag(null, ASSIGPER);
            serializer.attribute(null, "classCode", "PSN");
            serializer.attribute(null, "determinerCode", "INSTANCE");

            serializer.startTag(null, NAME);

            serializer.startTag(null, GIVEN);
            serializer.text(fall.getSan_vorname());
            serializer.endTag(null, GIVEN);

            serializer.startTag(null, FAM);
            serializer.text(fall.getSan_name());
            serializer.endTag(null, FAM);

            serializer.endTag(null, NAME);

            serializer.endTag(null, ASSIGPER);

            serializer.startTag(null, REPRORG);
            serializer.startTag(null, NAME);
            serializer.text("Festivealth");
            serializer.endTag(null, NAME);
            serializer.endTag(null, REPRORG);

            serializer.endTag(null, ASSIGNAUT);
            serializer.endTag(null, AUT);

            //structured Body
            serializer.startTag(null, BODY);

            //Tabelle Patient
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "patient");

            serializer.startTag(null, LAND);
            if(fall.getPat_land()!=null) {
                serializer.text(fall.getPat_land());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, LAND);

            serializer.startTag(null, KRANKENK);
            if(fall.getPat_krankenkasse()!=null) {
                serializer.text(fall.getPat_krankenkasse());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, KRANKENK);

            serializer.startTag(null, KRKNR);
            if(fall.getPat_versnr()!=null) {
                serializer.text(fall.getPat_versnr());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, KRKNR);

            serializer.startTag(null, VERSNR);
            if(fall.getPat_versichertennr()!=null) {
                serializer.text(fall.getPat_versichertennr());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, VERSNR);

            serializer.endTag(null, SEC);

            //Tabelle Einsatz
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "einsatz");

            serializer.startTag(null, EINDATE);
            serializer.text(fall.getVer_date());
            serializer.endTag(null, EINDATE);

            serializer.startTag(null, EINBEG);
            serializer.text("Einsatzbeginn");
            serializer.endTag(null, EINBEG);

            serializer.startTag(null, EINEND);
            serializer.text("Einsatzende");
            serializer.endTag(null, EINEND);

            serializer.startTag(null, ZUGEF);
            if(fall.getEin_zugef()!=null) {
                serializer.text(fall.getEin_zugef());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ZUGEF);

            serializer.startTag(null, HILFS);
            serializer.text(String.valueOf(fall.getEin_hilfs()));
            serializer.endTag(null, HILFS);

            serializer.startTag(null, MOSAN);
            serializer.text(String.valueOf(fall.getEin_mosan()));
            serializer.endTag(null, MOSAN);

            serializer.startTag(null, SANW);
            serializer.text(String.valueOf(fall.getEin_sanw()));
            serializer.endTag(null, SANW);

            serializer.startTag(null, EINPOL);
            serializer.text("Polizei");
            serializer.endTag(null, EINPOL);

            serializer.startTag(null, EINRTW);
            serializer.text("RTW KTW");
            serializer.endTag(null, EINRTW);

            serializer.startTag(null, EINSAN);
            serializer.text("San Team");
            serializer.endTag(null, EINSAN);

            serializer.startTag(null, FUNDORT);
            if(fall.getEin_fundort()!=null) {
                serializer.text(fall.getEin_fundort());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, FUNDORT);

            serializer.startTag(null, NOTF);
            if(fall.getNotf_notfallsituation()!=null) {
                serializer.text(fall.getNotf_notfallsituation());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, NOTF);

            serializer.endTag(null, SEC);

            //Tabelle Maßnahmen
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "massnahmen");

            serializer.startTag(null, STBSEIT);
            serializer.text(String.valueOf(fall.getMas_stb_seitenlage()));
            serializer.endTag(null, STBSEIT);

            serializer.startTag(null, OBERKHOCH);
            serializer.text(String.valueOf(fall.getMas_oberkoerperhochlage()));
            serializer.endTag(null, OBERKHOCH);

            serializer.startTag(null, FLACHL);
            serializer.text(String.valueOf(fall.getMas_flachlagerung()));
            serializer.endTag(null, FLACHL);

            serializer.startTag(null, SCHOCKL);
            serializer.text(String.valueOf(fall.getMas_schocklagerung()));
            serializer.endTag(null, SCHOCKL);

            serializer.startTag(null, VAKUUM);
            serializer.text(String.valueOf(fall.getMas_vakuummatratze()));
            serializer.endTag(null, VAKUUM);

            serializer.startTag(null, HWSST);
            serializer.text(String.valueOf(fall.getMas_hws_stuetzkragen()));
            serializer.endTag(null, HWSST);

            serializer.startTag(null, MEDI);
            serializer.text(String.valueOf(fall.getMas_medikamente()));
            serializer.endTag(null, MEDI);

            serializer.startTag(null, EXTRSCH);
            serializer.text(String.valueOf(fall.getMas_extr_schienung()));
            serializer.endTag(null, EXTRSCH);

            serializer.startTag(null, WUNDV);
            serializer.text(String.valueOf(fall.getMas_wundversorgung()));
            serializer.endTag(null, WUNDV);

            serializer.startTag(null, EKG);
            serializer.text(String.valueOf(fall.getMas_ekg()));
            serializer.endTag(null, EKG);

            serializer.startTag(null, VENZUG);
            serializer.text(String.valueOf(fall.getMas_ven_zugang()));
            serializer.endTag(null, VENZUG);

            serializer.startTag(null, INF);
            serializer.text(String.valueOf(fall.getMas_infusion()));
            serializer.endTag(null, INF);

            serializer.startTag(null, ATEMW);
            serializer.text(String.valueOf(fall.getMas_atemwege_freim()));
            serializer.endTag(null, ATEMW);

            serializer.startTag(null, NOTK);
            serializer.text(String.valueOf(fall.getMas_notkompetenz()));
            serializer.endTag(null, NOTK);

            serializer.startTag(null, SAUERST);
            serializer.text(String.valueOf(fall.getMas_sauerstoffgabe()));
            serializer.endTag(null, SAUERST);

            serializer.startTag(null, INT);
            serializer.text(String.valueOf(fall.getMas_intubation()));
            serializer.endTag(null, INT);

            serializer.startTag(null, BEATM);
            serializer.text(String.valueOf(fall.getMas_beatmung()));
            serializer.endTag(null, BEATM);

            serializer.startTag(null, HERZDM);
            serializer.text(String.valueOf(fall.getMas_herzdruckmassage()));
            serializer.endTag(null, HERZDM);

            serializer.startTag(null, ERSTDEF);
            serializer.text(String.valueOf(fall.getMas_erstdefibrillation()));
            serializer.endTag(null, ERSTDEF);

            serializer.startTag(null, BETR);
            serializer.text(String.valueOf(fall.getMas_betreuung()));
            serializer.endTag(null, BETR);

            serializer.startTag(null, SONST);
            serializer.text(String.valueOf(fall.getMas_sonstiges()));
            serializer.endTag(null, SONST);

            serializer.startTag(null, SONSTTXT);
            if(fall.getMas_sonstiges_text() != null) {
                serializer.text(fall.getMas_sonstiges_text());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, SONSTTXT);

            serializer.endTag(null, SEC);

            //Tabelle Verletzung
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "verletzung");

            serializer.startTag(null, SCHAEDLG);
            if(fall.getVerl_schaedel_grad()!=null) {
                serializer.text(fall.getVerl_schaedel_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, SCHAEDLG);

            serializer.startTag(null, GESIG);
            if(fall.getVerl_gesicht_grad()!=null) {
                serializer.text(fall.getVerl_gesicht_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, GESIG);

            serializer.startTag(null, HWSG);
            if(fall.getVerl_hws_grad()!=null) {
                serializer.text(fall.getVerl_hws_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, HWSG);

            serializer.startTag(null, BRUSTG);
            if(fall.getVerl_brustkorb_grad()!=null) {
                serializer.text(fall.getVerl_brustkorb_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BRUSTG);

            serializer.startTag(null, BAUCHG);
            //serializer.text(fall.getVerl_bauch_grad());
            if(fall.getVerl_bauch_grad()!=null) {
                serializer.text(fall.getVerl_bauch_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BAUCHG);

            serializer.startTag(null, BWSG);
            if(fall.getVerl_bws_lws_grad()!=null) {
                serializer.text(fall.getVerl_bws_lws_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BWSG);

            serializer.startTag(null, BECKENG);
            if(fall.getVerl_becken_grad()!=null) {
                serializer.text(fall.getVerl_becken_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BECKENG);

            serializer.startTag(null, ARMEG);
            if(fall.getVerl_arme_grad()!=null) {
                serializer.text(fall.getVerl_arme_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ARMEG);

            serializer.startTag(null, BEINEG);
            if(fall.getVerl_beine_grad()!=null) {
                serializer.text(fall.getVerl_beine_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BEINEG);

            serializer.startTag(null, WEICHTG);
            if(fall.getVerl_weichteile_grad()!=null) {
                serializer.text(fall.getVerl_weichteile_grad());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, WEICHTG);

            serializer.startTag(null, SCHAEDLA);
            if(fall.getVerl_schaedel_art()!=null) {
                serializer.text(fall.getVerl_schaedel_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, SCHAEDLA);

            serializer.startTag(null, GESIA);
            if(fall.getVerl_gesicht_art()!=null) {
                serializer.text(fall.getVerl_gesicht_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, GESIA);

            serializer.startTag(null, HWSA);
            if(fall.getVerl_hws_art()!=null) {
                serializer.text(fall.getVerl_hws_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, HWSA);

            serializer.startTag(null, BRUSTA);
            if(fall.getVerl_brustkorb_art()!=null) {
                serializer.text(fall.getVerl_brustkorb_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BRUSTA);

            serializer.startTag(null, BAUCHA);
            if(fall.getVerl_bauch_art()!=null) {
                serializer.text(fall.getVerl_bauch_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BAUCHA);

            serializer.startTag(null, BWSA);
            if(fall.getVerl_bws_lws_art()!=null) {
                serializer.text(fall.getVerl_bws_lws_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BWSA);

            serializer.startTag(null, BECKENA);
            if(fall.getVerl_becken_art()!=null) {
                serializer.text(fall.getVerl_becken_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BECKENA);

            serializer.startTag(null, ARMEA);
            if(fall.getVerl_arme_art()!=null) {
                serializer.text(fall.getVerl_arme_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ARMEA);

            serializer.startTag(null, BEINEA);
            if(fall.getVerl_beine_art()!=null) {
                serializer.text(fall.getVerl_beine_art());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BEINEA);

            serializer.startTag(null, WEICHTA);
            if(fall.getVerl_weichteile_art()!=null) {
                serializer.text(fall.getVerl_weichteile_art());
            } else {
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, WEICHTA);

            serializer.startTag(null, VERBR);
            serializer.text(String.valueOf(fall.getVerl_verbrennung()));
            serializer.endTag(null, VERBR);

            serializer.startTag(null, ELEKTRO);
            serializer.text(String.valueOf(fall.getVerl_elektrounfall()));
            serializer.endTag(null, ELEKTRO);

            serializer.startTag(null, INHALAT);
            serializer.text(String.valueOf(fall.getVerl_inhalationstrauma()));
            serializer.endTag(null, INHALAT);

            serializer.startTag(null, WUND);
            serializer.text(String.valueOf(fall.getVerl_wunde_verletzung()));
            serializer.endTag(null, WUND);

            serializer.startTag(null, PRELL);
            serializer.text(String.valueOf(fall.getVerl_prellung_verletzung()));
            serializer.endTag(null, PRELL);

            serializer.startTag(null, SONST);
            serializer.text(String.valueOf(fall.getVerl_sonstiges()));
            serializer.endTag(null, SONST);

            serializer.endTag(null, SEC);

            //Tabelle Erkrankung
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "erkrankung");

            serializer.startTag(null, ATM);
            serializer.text(String.valueOf(fall.getErk_atmung()));
            serializer.endTag(null, ATM);

            serializer.startTag(null, HERZK);
            serializer.text(String.valueOf(fall.getErk_herzkreislauf()));
            serializer.endTag(null, HERZK);

            serializer.startTag(null, BAUCHE);
            serializer.text(String.valueOf(fall.getErk_baucherkrankung()));
            serializer.endTag(null, BAUCHE);

            serializer.startTag(null, STOFFW);
            serializer.text(String.valueOf(fall.getErk_stoffwechsel()));
            serializer.endTag(null, STOFFW);

            serializer.startTag(null, HITZS);
            serializer.text(String.valueOf(fall.getErk_hitzeschlag()));
            serializer.endTag(null, HITZS);

            serializer.startTag(null, VERG);
            serializer.text(String.valueOf(fall.getErk_vergiftung()));
            serializer.endTag(null, VERG);

            serializer.startTag(null, UNTERK);
            serializer.text(String.valueOf(fall.getErk_unterkuehlung()));
            serializer.endTag(null, UNTERK);

            serializer.startTag(null, GYN);
            serializer.text(String.valueOf(fall.getErk_gynaekologie()));
            serializer.endTag(null, GYN);

            serializer.startTag(null, GEBURT);
            serializer.text(String.valueOf(fall.getErk_geburtshilfe()));
            serializer.endTag(null, GEBURT);

            serializer.startTag(null, HITZE);
            serializer.text(String.valueOf(fall.getErk_hitzeerschoepfung()));
            serializer.endTag(null, HITZE);

            serializer.startTag(null, KIND);
            serializer.text(String.valueOf(fall.getErk_kindernotfall()));
            serializer.endTag(null, KIND);

            serializer.startTag(null, NEURO);
            serializer.text(String.valueOf(fall.getErk_neurologie()));
            serializer.endTag(null, NEURO);

            serializer.startTag(null, PSYCH);
            serializer.text(String.valueOf(fall.getErk_psychatrie()));
            serializer.endTag(null, PSYCH);

            serializer.startTag(null, ALK);
            serializer.text(String.valueOf(fall.getErk_alkoholisiert()));
            serializer.endTag(null, ALK);

            serializer.startTag(null, ERBR);
            serializer.text(String.valueOf(fall.getErk_erbrechen()));
            serializer.endTag(null, ERBR);

            serializer.startTag(null, SCHW);
            serializer.text(String.valueOf(fall.getErk_schwindel()));
            serializer.endTag(null, SCHW);

            serializer.startTag(null, SONST);
            serializer.text(String.valueOf(fall.getErk_sonstiges()));
            serializer.endTag(null, SONST);

            serializer.endTag(null, SEC);

            //Tabelle Erstbefund
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "erstbefund");

            serializer.startTag(null, BEW);
            if(fall.getErst_bewusstsein()!=null) {
                serializer.text(fall.getErst_bewusstsein());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BEW);

            serializer.startTag(null, SCHM);
            if(fall.getErst_schmerzen()!=null) {
                serializer.text(fall.getErst_schmerzen());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, SCHM);

            serializer.startTag(null, KREISL);
            if(fall.getErst_kreislauf()!=null) {
                serializer.text(fall.getErst_kreislauf());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, KREISL);

            serializer.startTag(null, EKG);
            if(fall.getErst_ekg()!=null) {
                serializer.text(fall.getErst_ekg());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, EKG);

            serializer.startTag(null, ATM);
            if(fall.getErst_atmung()!=null) {
                serializer.text(fall.getErst_atmung());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ATM);

            serializer.startTag(null, RRSYS);
            if(fall.getErst_rr_sys()!=null) {
                serializer.text(fall.getErst_rr_sys());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, RRSYS);

            serializer.startTag(null, RRDIA);
            if(fall.getErst_rr_dia()!=null) {
                serializer.text(fall.getErst_rr_dia());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, RRDIA);

            serializer.startTag(null, PULS);
            if(fall.getErst_puls()!=null) {
                serializer.text(fall.getErst_puls());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, PULS);

            serializer.startTag(null, AF);
            if(fall.getErst_af()!=null) {
                serializer.text(fall.getErst_af());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, AF);

            serializer.startTag(null, SPO);
            if(fall.getErst_spo()!=null) {
                serializer.text(fall.getErst_spo());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, SPO);

            serializer.startTag(null, BZ);
            if(fall.getErst_bz()!=null) {
                serializer.text(fall.getErst_bz());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BZ);

            serializer.startTag(null, PUPILLI);
            if(fall.getErst_pupille_li()!=null) {
                serializer.text(fall.getErst_pupille_li());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, PUPILLI);

            serializer.startTag(null, PUPILRE);
            if(fall.getErst_pupille_re()!=null) {
                serializer.text(fall.getErst_pupille_re());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, PUPILRE);

            serializer.endTag(null, SEC);

            //Tabelle Ergebnis
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "ergebnis");

            serializer.startTag(null, ZUSTVERB);
            if(fall.getErg_zustand()!=null) {
                serializer.text(fall.getErg_zustand());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ZUSTVERB);

            serializer.startTag(null, ERGZ);
            if(fall.getErg_ergebnis_zeit()!=null) {
                serializer.text(fall.getErg_ergebnis_zeit());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ERGZ);

            serializer.startTag(null, WERTS);
            if(fall.getErg_wertsachen()!=null) {
                serializer.text(fall.getErg_wertsachen());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, WERTS);

            serializer.startTag(null, WERTSZ);
            serializer.text("Zeit");
            serializer.endTag(null, WERTSZ);

            serializer.startTag(null, ZEITN);
            serializer.text("Zeit");
            serializer.endTag(null, ZEITN);

            serializer.startTag(null, KTWN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_ktw()));
            serializer.endTag(null, KTWN);

            serializer.startTag(null, RTWN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_rtw()));
            serializer.endTag(null, RTWN);

            serializer.startTag(null, NEFN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_nef()));
            serializer.endTag(null, NEFN);

            serializer.startTag(null, NAWN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_naw()));
            serializer.endTag(null, NAWN);

            serializer.startTag(null, RTHN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_rth()));
            serializer.endTag(null, RTHN);

            serializer.startTag(null, FEUERWN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_feuerwehr()));
            serializer.endTag(null, FEUERWN);

            serializer.startTag(null, POLN);
            serializer.text(String.valueOf(fall.getErg_nachforderung_polizei()));
            serializer.endTag(null, POLN);

            serializer.startTag(null, FUNK);
            if(fall.getErg_funkruf()!=null) {
                serializer.text(fall.getErg_funkruf());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, FUNK);

            serializer.startTag(null, FUNKZ);
            serializer.text("Zeit");
            serializer.endTag(null, FUNKZ);

            serializer.startTag(null, EV);
            serializer.text(String.valueOf(fall.getErg_entlassung_ev()));
            serializer.endTag(null, EV);

            serializer.startTag(null, ZEUG);
            if(fall.getErg_zeuge()!=null) {
                serializer.text(fall.getErg_zeuge());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ZEUG);

            serializer.startTag(null, ZUST);
            serializer.text("Zustand");
            serializer.endTag(null, ZUST);

            serializer.startTag(null, HAUSA);
            serializer.text(String.valueOf(fall.getErg_hausarzt_informiert()));
            serializer.endTag(null, HAUSA);

            serializer.startTag(null, TOD);
            serializer.text(String.valueOf(fall.getErg_tod()));
            serializer.endTag(null, TOD);

            serializer.startTag(null, TRNSSONST);
            if(fall.getErg_transport_sonstiges()!=null) {
                serializer.text(fall.getErg_transport_sonstiges());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, TRNSSONST);

            serializer.startTag(null, ERSTH);
            if(fall.getErg_ersthelfermassn()!=null) {
                serializer.text(fall.getErg_ersthelfermassn());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, ERSTH);

            serializer.startTag(null, BEM);
            if(fall.getBem_bemerkung()!=null) {
                serializer.text(fall.getBem_bemerkung());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, BEM);

            serializer.startTag(null, FOTO);
            serializer.text("??");
            serializer.endTag(null, FOTO);

            serializer.startTag(null, SONSTTXT);
            serializer.text("Text");
            serializer.endTag(null, SONSTTXT);

            serializer.endTag(null, SEC);

            //Tabelle Sanitäter
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "sanitaeter");

            serializer.startTag(null, SANID);
            serializer.text(String.valueOf(fall.getSaniID()));
            serializer.endTag(null, SANID);

            serializer.endTag(null, SEC);

            //Tabelle Verband
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "verband");

            serializer.startTag(null, KREISV);
            serializer.text(fall.getVerb_kreisv());
            serializer.endTag(null, KREISV);

            serializer.startTag(null, ORTSV);
            serializer.text(fall.getVerb_ortsv());
            serializer.endTag(null, ORTSV);

            serializer.endTag(null, SEC);

            //Tabelle Veranstaltung
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "veranstaltung");

            serializer.startTag(null, VERNAME);
            serializer.text(fall.getVer_name());
            serializer.endTag(null, VERNAME);

            serializer.startTag(null, VERORT);
            if(fall.getVer_ort()!=null) {
                serializer.text(fall.getVer_ort());
            }
            else{
                serializer.text("keine Angabe");
            }
            serializer.endTag(null, VERORT);

            serializer.startTag(null, VERDATE);
            serializer.text(fall.getVer_date());
            serializer.endTag(null, VERDATE);

            serializer.endTag(null, SEC);

            //Ende
            serializer.endTag(null, BODY);
            serializer.endTag(null, ROOT);
            serializer.endDocument();

            //XML Daten in die Datei schreiben
            serializer.flush();
            //Filestream schließen
            fileos.close();

        } catch (Exception e) {
            Log.e("Exception", "error occurred while creating xml file");
            Log.e("Exception", e.toString());
        }
    }
}
