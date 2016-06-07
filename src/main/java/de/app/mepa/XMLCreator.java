//Zuletzt geändert von Vivien Stumpe, 31.05.16
package de.app.mepa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
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
    public void writeXML(GlobaleDaten fall) {

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
            serializer.endTag(null, STR);

            serializer.startTag(null, PLZ);
            if(fall.getPat_plz()!=null) {
                serializer.text(fall.getPat_plz());
            }
            serializer.endTag(null, PLZ);

            serializer.startTag(null, ORT);
            if(fall.getPat_ort()!=null) {
                serializer.text(fall.getPat_ort());
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
            String sex;
            if(fall.getPat_sex()!=null) {
                sex = fall.getPat_sex();
            }
            else{
                sex="keine Angabe";
            }
            serializer.text(String.valueOf(fall.getPatID())+" "+sex);
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
            serializer.endTag(null, LAND);

            serializer.startTag(null, KRANKENK);
            if(fall.getPat_krankenkasse()!=null) {
                serializer.text(fall.getPat_krankenkasse());
            }
            serializer.endTag(null, KRANKENK);

            serializer.startTag(null, KRKNR);
            if(fall.getPat_versnr()!=null) {
                serializer.text(fall.getPat_versnr());
            }
            serializer.endTag(null, KRKNR);

            serializer.startTag(null, VERSNR);
            if(fall.getPat_versichertennr()!=null) {
                serializer.text(fall.getPat_versichertennr());
            }
            serializer.endTag(null, VERSNR);

            serializer.endTag(null, SEC);

            //Tabelle Einsatz
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "einsatz");

            serializer.startTag(null, EINDATE);
            serializer.text(fall.getVer_date());
            serializer.endTag(null, EINDATE);

           /* wird nicht erfasst
            serializer.startTag(null, EINBEG);
            serializer.text("Einsatzbeginn");
            serializer.endTag(null, EINBEG);

            serializer.startTag(null, EINEND);
            serializer.text("Einsatzende");
            serializer.endTag(null, EINEND);
            */

            serializer.startTag(null, ZUGEF);
            if(fall.getEin_zugef()!=null) {
                serializer.text(fall.getEin_zugef());
            }
            serializer.endTag(null, ZUGEF);

            serializer.startTag(null, HILFS);
            if(fall.getEin_hilfs()!=null) {
                serializer.text(String.valueOf(fall.getEin_hilfs()));
            }
            serializer.endTag(null, HILFS);

            serializer.startTag(null, MOSAN);
            if(fall.getEin_mosan()!=null) {
                serializer.text(String.valueOf(fall.getEin_mosan()));
            }
            serializer.endTag(null, MOSAN);

            serializer.startTag(null, SANW);
            if(fall.getEin_sanw()!=null) {
                serializer.text(String.valueOf(fall.getEin_sanw()));
            }
            serializer.endTag(null, SANW);

            /*
            wird nicht erfasst
            serializer.startTag(null, EINPOL);
            serializer.text("Polizei");
            serializer.endTag(null, EINPOL);

            serializer.startTag(null, EINRTW);
            serializer.text("RTW KTW");
            serializer.endTag(null, EINRTW);

            serializer.startTag(null, EINSAN);
            serializer.text("San Team");
            serializer.endTag(null, EINSAN);

            */
            serializer.startTag(null, FUNDORT);
            if(fall.getEin_fundort()!=null) {
                serializer.text(fall.getEin_fundort());
            }
            serializer.endTag(null, FUNDORT);

            serializer.startTag(null, NOTF);
            if(fall.getNotf_notfallsituation()!=null) {
                serializer.text(fall.getNotf_notfallsituation());
            }
            serializer.endTag(null, NOTF);

            serializer.endTag(null, SEC);

            //Tabelle Maßnahmen
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "massnahmen");

            serializer.startTag(null, STBSEIT);
            if(fall.getMas_stb_seitenlage()!=null) {
                serializer.text(String.valueOf(fall.getMas_stb_seitenlage()));
            }
            serializer.endTag(null, STBSEIT);

            serializer.startTag(null, OBERKHOCH);
            if(fall.getMas_oberkoerperhochlage()!=null) {
                serializer.text(String.valueOf(fall.getMas_oberkoerperhochlage()));
            }
            serializer.endTag(null, OBERKHOCH);

            serializer.startTag(null, FLACHL);
            if(fall.getMas_flachlagerung()!=null) {
                serializer.text(String.valueOf(fall.getMas_flachlagerung()));
            }
            serializer.endTag(null, FLACHL);

            serializer.startTag(null, SCHOCKL);
            if(fall.getMas_schocklagerung()!=null) {
                serializer.text(String.valueOf(fall.getMas_schocklagerung()));
            }
            serializer.endTag(null, SCHOCKL);

            serializer.startTag(null, VAKUUM);
            if(fall.getMas_vakuummatratze()!=null) {
                serializer.text(String.valueOf(fall.getMas_vakuummatratze()));
            }
            serializer.endTag(null, VAKUUM);

            serializer.startTag(null, HWSST);
            if(fall.getMas_hws_stuetzkragen()!=null) {
                serializer.text(String.valueOf(fall.getMas_hws_stuetzkragen()));
            }
            serializer.endTag(null, HWSST);

            serializer.startTag(null, MEDI);
            if(fall.getMas_medikamente()!=null) {
                serializer.text(String.valueOf(fall.getMas_medikamente()));
            }
            serializer.endTag(null, MEDI);

            serializer.startTag(null, EXTRSCH);
            if(fall.getMas_extr_schienung()!=null) {
                serializer.text(String.valueOf(fall.getMas_extr_schienung()));
            }
            serializer.endTag(null, EXTRSCH);

            serializer.startTag(null, WUNDV);
            if(fall.getMas_wundversorgung()!=null) {
                serializer.text(String.valueOf(fall.getMas_wundversorgung()));
            }
            serializer.endTag(null, WUNDV);

            serializer.startTag(null, EKG);
            if(fall.getMas_ekg()!=null) {
                serializer.text(String.valueOf(fall.getMas_ekg()));
            }
            serializer.endTag(null, EKG);

            serializer.startTag(null, VENZUG);
            if(fall.getMas_ven_zugang()!=null) {
                serializer.text(String.valueOf(fall.getMas_ven_zugang()));
            }
            serializer.endTag(null, VENZUG);

            serializer.startTag(null, INF);
            if(fall.getMas_infusion()!=null) {
                serializer.text(String.valueOf(fall.getMas_infusion()));
            }
            serializer.endTag(null, INF);

            serializer.startTag(null, ATEMW);
            if(fall.getMas_atemwege_freim()!=null) {
                serializer.text(String.valueOf(fall.getMas_atemwege_freim()));
            }
            serializer.endTag(null, ATEMW);

            serializer.startTag(null, NOTK);
            if(fall.getMas_notkompetenz()!=null) {
                serializer.text(String.valueOf(fall.getMas_notkompetenz()));
            }
            serializer.endTag(null, NOTK);

            serializer.startTag(null, SAUERST);
            if(fall.getMas_sauerstoffgabe()!=null) {
                serializer.text(String.valueOf(fall.getMas_sauerstoffgabe()));
            }
            serializer.endTag(null, SAUERST);

            serializer.startTag(null, INT);
            if(fall.getMas_intubation()!=null) {
                serializer.text(String.valueOf(fall.getMas_intubation()));
            }
            serializer.endTag(null, INT);

            serializer.startTag(null, BEATM);
            if(fall.getMas_beatmung()!=null) {
                serializer.text(String.valueOf(fall.getMas_beatmung()));
            }
            serializer.endTag(null, BEATM);

            serializer.startTag(null, HERZDM);
            if(fall.getMas_herzdruckmassage()!=null) {
                serializer.text(String.valueOf(fall.getMas_herzdruckmassage()));
            }
            serializer.endTag(null, HERZDM);

            serializer.startTag(null, ERSTDEF);
            if(fall.getMas_erstdefibrillation()!=null) {
                serializer.text(String.valueOf(fall.getMas_erstdefibrillation()));
            }
            serializer.endTag(null, ERSTDEF);

            serializer.startTag(null, BETR);
            if(fall.getMas_betreuung()!=null) {
                serializer.text(String.valueOf(fall.getMas_betreuung()));
            }
            serializer.endTag(null, BETR);

            serializer.startTag(null, SONST);
            if(fall.getMas_sonstiges()!=null) {
                serializer.text(String.valueOf(fall.getMas_sonstiges()));
            }
            serializer.endTag(null, SONST);

            serializer.startTag(null, SONSTTXT);
            if(fall.getMas_sonstiges_text() != null) {
                serializer.text(fall.getMas_sonstiges_text());
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
            serializer.endTag(null, SCHAEDLG);

            serializer.startTag(null, GESIG);
            if(fall.getVerl_gesicht_grad()!=null) {
                serializer.text(fall.getVerl_gesicht_grad());
            }
            serializer.endTag(null, GESIG);

            serializer.startTag(null, HWSG);
            if(fall.getVerl_hws_grad()!=null) {
                serializer.text(fall.getVerl_hws_grad());
            }
            serializer.endTag(null, HWSG);

            serializer.startTag(null, BRUSTG);
            if(fall.getVerl_brustkorb_grad()!=null) {
                serializer.text(fall.getVerl_brustkorb_grad());
            }
            serializer.endTag(null, BRUSTG);

            serializer.startTag(null, BAUCHG);
            if(fall.getVerl_bauch_grad()!=null) {
                serializer.text(fall.getVerl_bauch_grad());
            }
            serializer.endTag(null, BAUCHG);

            serializer.startTag(null, BWSG);
            if(fall.getVerl_bws_lws_grad()!=null) {
                serializer.text(fall.getVerl_bws_lws_grad());
            }
            serializer.endTag(null, BWSG);

            serializer.startTag(null, BECKENG);
            if(fall.getVerl_becken_grad()!=null) {
                serializer.text(fall.getVerl_becken_grad());
            }
            serializer.endTag(null, BECKENG);

            serializer.startTag(null, ARMEG);
            if(fall.getVerl_arme_grad()!=null) {
                serializer.text(fall.getVerl_arme_grad());
            }
            serializer.endTag(null, ARMEG);

            serializer.startTag(null, BEINEG);
            if(fall.getVerl_beine_grad()!=null) {
                serializer.text(fall.getVerl_beine_grad());
            }
            serializer.endTag(null, BEINEG);

            serializer.startTag(null, WEICHTG);
            if(fall.getVerl_weichteile_grad()!=null) {
                serializer.text(fall.getVerl_weichteile_grad());
            }
            serializer.endTag(null, WEICHTG);

            serializer.startTag(null, SCHAEDLA);
            if(fall.getVerl_schaedel_art()!=null) {
                serializer.text(fall.getVerl_schaedel_art());
            }
            serializer.endTag(null, SCHAEDLA);

            serializer.startTag(null, GESIA);
            if(fall.getVerl_gesicht_art()!=null) {
                serializer.text(fall.getVerl_gesicht_art());
            }
            serializer.endTag(null, GESIA);

            serializer.startTag(null, HWSA);
            if(fall.getVerl_hws_art()!=null) {
                serializer.text(fall.getVerl_hws_art());
            }
            serializer.endTag(null, HWSA);

            serializer.startTag(null, BRUSTA);
            if(fall.getVerl_brustkorb_art()!=null) {
                serializer.text(fall.getVerl_brustkorb_art());
            }
            serializer.endTag(null, BRUSTA);

            serializer.startTag(null, BAUCHA);
            if(fall.getVerl_bauch_art()!=null) {
                serializer.text(fall.getVerl_bauch_art());
            }
            serializer.endTag(null, BAUCHA);

            serializer.startTag(null, BWSA);
            if(fall.getVerl_bws_lws_art()!=null) {
                serializer.text(fall.getVerl_bws_lws_art());
            }
            serializer.endTag(null, BWSA);

            serializer.startTag(null, BECKENA);
            if(fall.getVerl_becken_art()!=null) {
                serializer.text(fall.getVerl_becken_art());
            }
            serializer.endTag(null, BECKENA);

            serializer.startTag(null, ARMEA);
            if(fall.getVerl_arme_art()!=null) {
                serializer.text(fall.getVerl_arme_art());
            }
            serializer.endTag(null, ARMEA);

            serializer.startTag(null, BEINEA);
            if(fall.getVerl_beine_art()!=null) {
                serializer.text(fall.getVerl_beine_art());
            }
            serializer.endTag(null, BEINEA);

            serializer.startTag(null, WEICHTA);
            if(fall.getVerl_weichteile_art()!=null) {
                serializer.text(fall.getVerl_weichteile_art());
            }
            serializer.endTag(null, WEICHTA);

            serializer.startTag(null, VERBR);
            if(fall.getVerl_verbrennung()!=null) {
                serializer.text(String.valueOf(fall.getVerl_verbrennung()));
            }
            serializer.endTag(null, VERBR);

            serializer.startTag(null, ELEKTRO);
            if(fall.getVerl_elektrounfall()!=null) {
                serializer.text(String.valueOf(fall.getVerl_elektrounfall()));
            }
            serializer.endTag(null, ELEKTRO);

            serializer.startTag(null, INHALAT);
            if(fall.getVerl_inhalationstrauma()!=null) {
                serializer.text(String.valueOf(fall.getVerl_inhalationstrauma()));
            }
            serializer.endTag(null, INHALAT);

            serializer.startTag(null, WUND);
            if(fall.getVerl_wunde_verletzung()!=null) {
                serializer.text(String.valueOf(fall.getVerl_wunde_verletzung()));
            }
            serializer.endTag(null, WUND);

            serializer.startTag(null, PRELL);
            if(fall.getVerl_prellung_verletzung()!=null) {
                serializer.text(String.valueOf(fall.getVerl_prellung_verletzung()));
            }
            serializer.endTag(null, PRELL);

            serializer.startTag(null, SONST);
            if(fall.getVerl_sonstiges()!=null) {
                serializer.text(String.valueOf(fall.getVerl_sonstiges()));
            }
            serializer.endTag(null, SONST);

            serializer.endTag(null, SEC);

            //Tabelle Erkrankung
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "erkrankung");

            serializer.startTag(null, ATM);
            if(fall.getErk_atmung()!=null) {
                serializer.text(String.valueOf(fall.getErk_atmung()));
            }
            serializer.endTag(null, ATM);

            serializer.startTag(null, HERZK);
            if(fall.getErk_herzkreislauf()!=null) {
                serializer.text(String.valueOf(fall.getErk_herzkreislauf()));
            }
            serializer.endTag(null, HERZK);

            serializer.startTag(null, BAUCHE);
            if(fall.getErk_baucherkrankung()!=null) {
                serializer.text(String.valueOf(fall.getErk_baucherkrankung()));
            }
            serializer.endTag(null, BAUCHE);

            serializer.startTag(null, STOFFW);
            if(fall.getErk_stoffwechsel()!=null) {
                serializer.text(String.valueOf(fall.getErk_stoffwechsel()));
            }
            serializer.endTag(null, STOFFW);

            serializer.startTag(null, HITZS);
            if(fall.getErk_hitzeschlag()!=null) {
                serializer.text(String.valueOf(fall.getErk_hitzeschlag()));
            }
            serializer.endTag(null, HITZS);

            serializer.startTag(null, VERG);
            if(fall.getErk_vergiftung()!=null) {
                serializer.text(String.valueOf(fall.getErk_vergiftung()));
            }
            serializer.endTag(null, VERG);

            serializer.startTag(null, UNTERK);
            if(fall.getErk_unterkuehlung()!=null) {
                serializer.text(String.valueOf(fall.getErk_unterkuehlung()));
            }
            serializer.endTag(null, UNTERK);

            serializer.startTag(null, GYN);
            if(fall.getErk_gynaekologie()!=null) {
                serializer.text(String.valueOf(fall.getErk_gynaekologie()));
            }
            serializer.endTag(null, GYN);

            serializer.startTag(null, GEBURT);
            if(fall.getErk_geburtshilfe()!=null) {
                serializer.text(String.valueOf(fall.getErk_geburtshilfe()));
            }
            serializer.endTag(null, GEBURT);

            serializer.startTag(null, HITZE);
            if(fall.getErk_hitzeerschoepfung()!=null) {
                serializer.text(String.valueOf(fall.getErk_hitzeerschoepfung()));
            }
            serializer.endTag(null, HITZE);

            serializer.startTag(null, KIND);
            if(fall.getErk_kindernotfall()!=null) {
                serializer.text(String.valueOf(fall.getErk_kindernotfall()));
            }
            serializer.endTag(null, KIND);

            serializer.startTag(null, NEURO);
            if(fall.getErk_neurologie()!=null) {
                serializer.text(String.valueOf(fall.getErk_neurologie()));
            }
            serializer.endTag(null, NEURO);

            serializer.startTag(null, PSYCH);
            if(fall.getErk_psychatrie()!=null) {
                serializer.text(String.valueOf(fall.getErk_psychatrie()));
            }
            serializer.endTag(null, PSYCH);

            serializer.startTag(null, ALK);
            if(fall.getErk_alkoholisiert()!=null) {
                serializer.text(String.valueOf(fall.getErk_alkoholisiert()));
            }
            serializer.endTag(null, ALK);

            serializer.startTag(null, ERBR);
            if(fall.getErk_erbrechen()!=null) {
                serializer.text(String.valueOf(fall.getErk_erbrechen()));
            }
            serializer.endTag(null, ERBR);

            serializer.startTag(null, SCHW);
            if(fall.getErk_schwindel()!=null) {
                serializer.text(String.valueOf(fall.getErk_schwindel()));
            }
            serializer.endTag(null, SCHW);

            serializer.startTag(null, SONST);
            if(fall.getErk_sonstiges()!=null) {
                serializer.text(String.valueOf(fall.getErk_sonstiges()));
            }
            serializer.endTag(null, SONST);

            serializer.endTag(null, SEC);

            //Tabelle Erstbefund
            serializer.startTag(null, SEC);
            serializer.attribute(null, "id", "erstbefund");

            serializer.startTag(null, BEW);
            if(fall.getErst_bewusstsein()!=null) {
                serializer.text(fall.getErst_bewusstsein());
            }
            serializer.endTag(null, BEW);

            serializer.startTag(null, SCHM);
            if(fall.getErst_schmerzen()!=null) {
                serializer.text(fall.getErst_schmerzen());
            }
            serializer.endTag(null, SCHM);

            serializer.startTag(null, KREISL);
            if(fall.getErst_kreislauf()!=null) {
                serializer.text(fall.getErst_kreislauf());
            }
            serializer.endTag(null, KREISL);

            serializer.startTag(null, EKG);
            if(fall.getErst_ekg()!=null) {
                serializer.text(fall.getErst_ekg());
            }
            serializer.endTag(null, EKG);

            serializer.startTag(null, ATM);
            if(fall.getErst_atmung()!=null) {
                serializer.text(fall.getErst_atmung());
            }
            serializer.endTag(null, ATM);

            serializer.startTag(null, RRSYS);
            if(fall.getErst_rr_sys()!=null) {
                serializer.text(fall.getErst_rr_sys());
            }
            serializer.endTag(null, RRSYS);

            serializer.startTag(null, RRDIA);
            if(fall.getErst_rr_dia()!=null) {
                serializer.text(fall.getErst_rr_dia());
            }
            serializer.endTag(null, RRDIA);

            serializer.startTag(null, PULS);
            if(fall.getErst_puls()!=null) {
                serializer.text(fall.getErst_puls());
            }
            serializer.endTag(null, PULS);

            serializer.startTag(null, AF);
            if(fall.getErst_af()!=null) {
                serializer.text(fall.getErst_af());
            }
            serializer.endTag(null, AF);

            serializer.startTag(null, SPO);
            if(fall.getErst_spo()!=null) {
                serializer.text(fall.getErst_spo());
            }
            serializer.endTag(null, SPO);

            serializer.startTag(null, BZ);
            if(fall.getErst_bz()!=null) {
                serializer.text(fall.getErst_bz());
            }
            serializer.endTag(null, BZ);

            serializer.startTag(null, PUPILLI);
            if(fall.getErst_pupille_li()!=null) {
                serializer.text(fall.getErst_pupille_li());
            }
            serializer.endTag(null, PUPILLI);

            serializer.startTag(null, PUPILRE);
            if(fall.getErst_pupille_re()!=null) {
                serializer.text(fall.getErst_pupille_re());
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
            serializer.endTag(null, ZUSTVERB);

            serializer.startTag(null, ERGZ);
            if(fall.getErg_ergebnis_zeit()!=null) {
                serializer.text(fall.getErg_ergebnis_zeit());
            }
            serializer.endTag(null, ERGZ);

            serializer.startTag(null, WERTS);
            if(fall.getErg_wertsachen()!=null) {
                serializer.text(fall.getErg_wertsachen());
            }
            serializer.endTag(null, WERTS);
            /*
                wird nicht erfasst
            serializer.startTag(null, WERTSZ);
            serializer.text("Zeit");
            serializer.endTag(null, WERTSZ);

            serializer.startTag(null, ZEITN);
            serializer.text("Zeit");
            serializer.endTag(null, ZEITN);
            */
            serializer.startTag(null, KTWN);
            if(fall.getErg_nachforderung_ktw()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_ktw()));
            }
            serializer.endTag(null, KTWN);

            serializer.startTag(null, RTWN);
            if(fall.getErg_nachforderung_rtw()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_rtw()));
            }
            serializer.endTag(null, RTWN);

            serializer.startTag(null, NEFN);
            if(fall.getErg_nachforderung_nef()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_nef()));
            }
            serializer.endTag(null, NEFN);

            serializer.startTag(null, NAWN);
            if(fall.getErg_nachforderung_naw()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_naw()));
            }
            serializer.endTag(null, NAWN);

            serializer.startTag(null, RTHN);
            if(fall.getErg_nachforderung_rth()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_rth()));
            }
            serializer.endTag(null, RTHN);

            serializer.startTag(null, FEUERWN);
            if(fall.getErg_nachforderung_feuerwehr()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_feuerwehr()));
            }
            serializer.endTag(null, FEUERWN);

            serializer.startTag(null, POLN);
            if(fall.getErg_nachforderung_polizei()!=null) {
                serializer.text(String.valueOf(fall.getErg_nachforderung_polizei()));
            }
            serializer.endTag(null, POLN);

            serializer.startTag(null, FUNK);
            if(fall.getErg_funkruf()!=null) {
                serializer.text(fall.getErg_funkruf());
            }
            serializer.endTag(null, FUNK);
            /*
                wird nicht erfasst
            serializer.startTag(null, FUNKZ);
            serializer.text("Zeit");
            serializer.endTag(null, FUNKZ);
            */
            serializer.startTag(null, EV);
            if(fall.getErg_entlassung_ev()!=null) {
                serializer.text(String.valueOf(fall.getErg_entlassung_ev()));
            }
            serializer.endTag(null, EV);

            serializer.startTag(null, ZEUG);
            if(fall.getErg_zeuge()!=null) {
                serializer.text(fall.getErg_zeuge());
            }
            serializer.endTag(null, ZEUG);
            /*
                wird nicht erfasst
            serializer.startTag(null, ZUST);
            serializer.text("Zustand");
            serializer.endTag(null, ZUST);
            */
            serializer.startTag(null, HAUSA);
            if(fall.getErg_hausarzt_informiert()!=null) {
                serializer.text(String.valueOf(fall.getErg_hausarzt_informiert()));
            }
            serializer.endTag(null, HAUSA);

            serializer.startTag(null, TOD);
            if(fall.getErg_tod()!=null) {
                serializer.text(String.valueOf(fall.getErg_tod()));
            }
            serializer.endTag(null, TOD);

            serializer.startTag(null, TRNSSONST);
            if(fall.getErg_transport_sonstiges()!=null) {
                serializer.text(fall.getErg_transport_sonstiges());
            }
            serializer.endTag(null, TRNSSONST);

            serializer.startTag(null, ERSTH);
            if(fall.getErg_ersthelfermassn()!=null) {
                serializer.text(fall.getErg_ersthelfermassn());
            }
            serializer.endTag(null, ERSTH);

            serializer.startTag(null, BEM);
            if(fall.getBem_bemerkung()!=null) {
                serializer.text(fall.getBem_bemerkung());
            }
            serializer.endTag(null, BEM);
            /*
                Wie geben wir das Foto aus?
            serializer.startTag(null, FOTO);
            serializer.text("??");
            serializer.endTag(null, FOTO);

            serializer.startTag(null, SONSTTXT);
            serializer.text("Text");
            serializer.endTag(null, SONSTTXT);
            */
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
    /*  von Vivien Stumpe, 31.05.16
        Funktion zum Löschen eines XML-Dokuments
     */
    public Boolean deleteXML(String id){
        File file=new File(Environment.getExternalStorageDirectory() + File.separator + "MEPA_Dateiordner" + File.separator + id + ".xml");
        if(file.exists()) {
            return file.delete();
        }
        else {
           Log.d("Fall", "XML-Dokument existiert nicht");
            return false;
        }
    }
    /*  von Vivien Stumpe, 31.05.16
    Prozedur zum Löschen aller Dokumente im MEPA_Dateiordner
    */

    public Boolean deleteAllXML() {
        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "MEPA_Dateiordner");
        Boolean geloescht=false;
        for(File tempFile : dir.listFiles()) {
            if( tempFile.delete())
                geloescht=true;
        }
                return geloescht;
    }
}
