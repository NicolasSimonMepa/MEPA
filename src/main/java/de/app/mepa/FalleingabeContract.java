//Zuletzt geändert von Vivien Stumpe, 08.05.16
package de.app.mepa;

import android.provider.BaseColumns;

/**
 * Created by vivienstumpe on 06.05.16
 * Contract-Klasse
 * enthält die Tabellennamen und Definitionen in statischen, abstrakten inneren Klassen
 */
public final class FalleingabeContract {

    public FalleingabeContract(){}

    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Verletzung
    */
    public static abstract class Tbl_Verletzung implements BaseColumns {
        public static final String TABLE_NAME = "verletzung";
        public static final String COL_ID = "_id";
        public static final String COL_PRELLUNG_VERLETZUNG = "prellung_verletzung";
        public static final String COL_VERBRENNUNG = "verbrennung";
        public static final String COL_WUNDE_VERLETZUNG = "wunde_verletzung";
        public static final String COL_ELEKTROUNFALL = "elektrounfall";
        public static final String COL_INHALATIONSTRAUMA = "inhalationstrauma";
        public static final String COL_SONSTIGES = "sonstiges";
        public static final String COL_SCHAEDEL_ART = "schaedel_art";
        public static final String COL_GESICHT_ART = "gesicht_art";
        public static final String COL_HWS_ART = "hws_art";
        public static final String COL_BRUSTKORB_ART = "brustkorb_art";
        public static final String COL_BAUCH_ART = "bauch_art";
        public static final String COL_BWS_ART = "bws_lws_art";
        public static final String COL_BECKEN_ART = "becken_art";
        public static final String COL_ARME_ART = "arme_art";
        public static final String COL_BEINE_ART = "beine_art";
        public static final String COL_WEICHTEILE_ART = "weichteile_art";
        public static final String COL_SCHAEDEL_GRAD = "schaedel_grad";
        public static final String COL_GESICHT_GRAD = "gesicht_grad";
        public static final String COL_HWS_GRAD = "hws_grad";
        public static final String COL_BRUSTKORB_GRAD = "brustkorb_grad";
        public static final String COL_BAUCH_GRAD = "bauch_grad";
        public static final String COL_BWS_GRAD = "bws_lws_grad";
        public static final String COL_BECKEN_GRAD = "becken_grad";
        public static final String COL_ARME_GRAD = "arme_grad";
        public static final String COL_BEINE_GRAD = "beine_grad";
        public static final String COL_WEICHTEILE_GRAD = "weichteile_grad";
        public static final String COL_PROT_ID = "prot_id";
    }

    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Maßnahmen
    */
    public static abstract class Tbl_Massnahmen implements BaseColumns {
        public static final String TABLE_NAME = "massnahmen";
        public static final String COL_ID = "_id";
        public static final String COL_STB_SEITENLAGE = "stb_seitenlage";
        public static final String COL_OBERKOERPERHOCHLAGE = "oberkoerperhochlage";
        public static final String COL_FLACHLAGERUNG = "flachlagerung";
        public static final String COL_SCHOCKLAGERUNG = "schocklagerung";
        public static final String COL_VAKUUMM = "vakuummatratze";
        public static final String COL_HWS_ST = "hws_stuetzkragen";
        public static final String COL_MEDIKAMENTE = "medikamente";
        public static final String COL_SCHIENUNG = "extr_schienung";
        public static final String COL_WUNDVERSORGUNG = "wundversorgung";
        public static final String COL_EKG = "ekg";
        public static final String COL_ZUGANG = "ven_zugang";
        public static final String COL_INFUSION = "infusion";
        public static final String COL_ATEMWEGE = "atemwege_freim";
        public static final String COL_NOTKOMPETENZ = "notkompetenz";
        public static final String COL_SAUERSTOFFGABE = "sauerstoffgabe";
        public static final String COL_INTUBATION = "intubation";
        public static final String COL_BEATMUNG = "beatmung";
        public static final String COL_HERZDRUCKM = "herzdruckmassage";
        public static final String COL_ERSTDEFI = "erstdefibrillation";
        public static final String COL_BETREUUNG = "betreuung";
        public static final String COL_SONSTIGES = "sonstiges";
        public static final String COL_SONSTIGESTEXT = "sonstiges_text";
        public static final String COL_AED = "aed";
        public static final String COL_KEINE = "keine";
        public static final String COL_PROT_ID = "prot_id";

    }
    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Ergebnis
    */
    public static abstract class Tbl_Ergebnis implements BaseColumns {
        public static final String TABLE_NAME = "ergebnis";
        public static final String COL_ID = "_id";
        public static final String COL_ZUST_VERBESSERT = "zust_verbessert";
        public static final String COL_ERGEBNIS_ZEIT = "ergebnis_zeit";
        public static final String COL_WERTSACHEN = "wertsachen";
        public static final String COL_WERTSACHEN_ZEIT = "wertsachen_zeit";
        public static final String COL_BEMERKUNG = "bemerkung";
        public static final String COL_NACHFORDERUNG_KTW = "nachforderung_ktw";
        public static final String COL_NACHFORDERUNG_RTW = "nachforderung_rtw";
        public static final String COL_NACHFORDERUNG_NEF = "nachforderung_nef";
        public static final String COL_NACHFORDERUNG_NAW = "nachforderung_naw";
        public static final String COL_NACHFORDERUNG_RTH = "nachforderung_rth";
        public static final String COL_NACHFORDERUNG_FEUERWEHR = "nachforderung_feuerwehr";
        public static final String COL_NACHFORDERUNG_POLIZEI = "nachforderung_polizei";
        public static final String COL_NACHFORDERUNG_ZEIT = "nachforderung_zeit";
        public static final String COL_FUNKRUF = "funkruf";
        public static final String COL_FUNKRUF_ZEIT = "funkruf_zeit";
        public static final String COL_TRANSPORT = "transport";
        public static final String COL_TRANSPORT_ZIEL = "transport_ziel";
        public static final String COL_ENTLASSUNG_EV = "entlassung_ev";
        public static final String COL_ZEUGE = "zeuge";
        public static final String COL_ZUSTAND = "zustand";
        public static final String COL_NOTARZT = "notarzt";
        public static final String COL_HAUSARZT_INFORMIERT = "hausarzt_informiert";
        public static final String COL_TOD = "tod";
        public static final String COL_TRANSPORT_SONSTIGES = "transport_sonstiges";
        public static final String COL_ERGEBNISZEIT = "ergebniszeit";           
        public static final String COL_ERSTHELFERMASSN = "ersthelfermassn";
        public static final String COL_FOTO = "foto";
        public static final String COL_SONSTIGESTEXT = "sonstiges_text";
        public static final String COL_PROT_ID = "prot_id";

    }

    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Erkrankung
    */
    public static abstract class Tbl_Erkrankung implements BaseColumns {
        public static final String TABLE_NAME = "erkrankung_vergiftung";
        public static final String COL_ID = "_id";
        public static final String COL_ATMUNG = "atmung";
        public static final String COL_HERZ_KREISLAUF = "herz_kreislauf";
        public static final String COL_BAUCHERKRANKUNG = "baucherkrankung";
        public static final String COL_STOFFWECHSEL = "stoffwechsel";
        public static final String COL_HITZSCHLAG = "hitzschlag";
        public static final String COL_VERGIFTUNG = "vergiftung";
        public static final String COL_UNTERKUEHLUNG = "unterkuehlung";
        public static final String COL_GYNAEKOLOGIE = "gynaekologie";
        public static final String COL_GEBURTSHILFE = "geburtshilfe";
        public static final String COL_HITZEERSCHOEPFUNG = "hitzeerschoepfung";
        public static final String COL_KINDERNOTFALL = "kindernotfall";
        public static final String COL_NEUROLOGIE = "neurologie";
        public static final String COL_PSYCHATRIE = "psychatrie";
        public static final String COL_ALKOHOLISIERT = "alkoholisiert";
        public static final String COL_SONSTIGES = "sonstiges";
        public static final String COL_SONSTIGESTEXT = "sonstiges_text";
        public static final String COL_SCHWINDEL = "schwindel";
        public static final String COL_UEBELKEIT_ERBRECHEN = "uebelkeit_erbrechen";
        public static final String COL_PROT_ID = "prot_id";

    }
    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Erstbefund
    */
    public static abstract class Tbl_Erstbefund implements BaseColumns {
        public static final String TABLE_NAME = "erstbefund";
        public static final String COL_ID = "_id";
        public static final String COL_BEWUSSTSEIN = "bewusstsein";
        public static final String COL_SCHMERZEN = "schmerzen";
        public static final String COL_KREISLAUF = "kreislauf";
        public static final String COL_EKG = "ekg";
        public static final String COL_ATMUNG = "atmung";
        public static final String COL_RR_SYS = "rr_sys";
        public static final String COL_RR_DIA = "rr_dia";
        public static final String COL_PULS = "puls";
        public static final String COL_AF = "af";
        public static final String COL_SPO2 = "spo";
        public static final String COL_BZ = "bz";
        public static final String COL_PUPILLE_LI = "pupille_li";
        public static final String COL_PUPILLE_RE = "pupille_re";
        public static final String COL_PROT_ID = "prot_id";
    }
    /* von Vivien Stumpe, 08.05.16
       * Inner class that defines the table contents
       * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
       * Einsatz
       */
    public static abstract class Tbl_Einsatz implements BaseColumns {
        public static final String TABLE_NAME = "einsatz";
        public static final String COL_ID = "_id";
        public static final String COL_EINSATZ_DATUM = "datum";
        public static final String COL_ZUGEFUEHRT_DURCH = "zugefuehrt_durch";
        public static final String COL_HILFSSTELLE = "hilfsstelle";
        public static final String COL_MOSAN_TEAM = "mosan_team";
        public static final String COL_SAN_WACHE = "san_wache";
        public static final String COL_FUNDORT = "fundort";
        public static final String COL_NOTFALL = "notfallsituation";
        public static final String COL_PAT_ID = "patienten_id";
    }
    /* von Vivien Stumpe, 09.05.16
   * Inner class that defines the table contents
   * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
   * Patient
   */
    public static abstract class Tbl_Patient implements BaseColumns {
        public static final String TABLE_NAME = "patient";
        public static final String COL_ID = "_id";
        public static final String COL_NAME = "nachname";
        public static final String COL_VORNAME = "vorname";
        public static final String COL_STRASSE = "strasse";
        public static final String COL_PLZ = "plz";
        public static final String COL_WOHNORT = "wohnort";
        public static final String COL_GEBURTSDATUM = "geburtsdatum";
        public static final String COL_TELEFON = "telefon";
        public static final String COL_LAND = "land";
        public static final String COL_KRANKENKASSE = "krankenkasse";
        public static final String COL_GESCHLECHT = "geschlecht";
        public static final String COL_VERSICHERTENNUMMER = "versichertennummer";
        public static final String COL_VERSICHERUNG_NUMMER = "versicherung_nummer";
        public static final String COL_SANI_ID = "sani_id";
    }
    /* von Vivien Stumpe, 09.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Sanitäter
    */
    public static abstract class Tbl_Sani implements BaseColumns {
        public static final String TABLE_NAME = "sanitaeter";
        public static final String COL_ID = "_id";
        public static final String COL_NAME = "nachname";
        public static final String COL_VORNAME = "vorname";
        public static final String COL_VERBAND_ID = "verband_id";
    }
    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Verband
    */
    public static abstract class Tbl_Verband implements BaseColumns {
        public static final String TABLE_NAME = "verband";
        public static final String COL_ID = "_id";
        public static final String COL_KREISVERBAND = "kreisverband";
        public static final String COL_ORTSVEREIN = "ortsverein";
    }
    /* von Vivien Stumpe, 08.05.16
    * Inner class that defines the table contents
    * BaseColumns implementieren, damit besser mit dem Android Framework gearbeitet wird
    * Veranstaltung
    */
    public static abstract class Tbl_Veranstaltung implements BaseColumns {
        public static final String TABLE_NAME = "veranstaltung";
        public static final String COL_ID = "_id";
        public static final String COL_VERANSTALTUNG_NAME = "veranstaltung_name";
        public static final String COL_VERANSTALTUNG_ORT = "veranstaltung_ort";
        public static final String COL_VERANSTALTUNG_DATUM = "veranstaltung_datum";
        public static final String COL_VERBAND_ID = "verband_id";
    }

}
