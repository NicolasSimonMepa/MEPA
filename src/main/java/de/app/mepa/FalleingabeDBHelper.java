//Zuletzt geändert von Vivien Stumpe, 09.05.16
package de.app.mepa;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by vivienstumpe on 06.05.16.
 * Hilfsklasse zum Erzeugen und Aktualisieren der DB
 */
public class FalleingabeDBHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = FalleingabeDBHelper.class.getSimpleName();
    //Konstanten für die DB
    private static final String db_name="Falleingabe.db";
    public static final int DB_VERSION = 1;

    /* von Vivien Stumpe, 08.05.16
    * Verletzung
    */
    public static final String SQL_CREATE_Verletzung =
            "CREATE TABLE " + FalleingabeContract.Tbl_Verletzung.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Verletzung.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_ELEKTROUNFALL + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_WUNDE_VERLETZUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_INHALATIONSTRAUMA + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_SONSTIGES + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_VERBRENNUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_PRELLUNG_VERLETZUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Verletzung.COL_SCHAEDEL_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_GESICHT_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BRUSTKORB_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BWS_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_HWS_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BECKEN_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BAUCH_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BEINE_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_ARME_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_WEICHTEILE_ART + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_SCHAEDEL_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_GESICHT_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BRUSTKORB_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BWS_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_HWS_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BECKEN_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BAUCH_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_BEINE_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_ARME_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_WEICHTEILE_GRAD + " TEXT, " +
                    FalleingabeContract.Tbl_Verletzung.COL_PROT_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Verletzung.COL_PROT_ID+") REFERENCES "+FalleingabeContract.Tbl_Einsatz.TABLE_NAME+"("+FalleingabeContract.Tbl_Einsatz.COL_ID+"));";
    public static final String SQL_DROP_Verletzung = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Verletzung.TABLE_NAME + ";";

    /* von Vivien Stumpe, 08.05.16
    * Maßnahmen
    */
    public static final String SQL_CREATE_Massnahmen =
            "CREATE TABLE " + FalleingabeContract.Tbl_Massnahmen.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Massnahmen.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_STB_SEITENLAGE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_OBERKOERPERHOCHLAGE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_FLACHLAGERUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_SCHOCKLAGERUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_VAKUUMM + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_HWS_ST + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_MEDIKAMENTE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_SCHIENUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_WUNDVERSORGUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_EKG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_ZUGANG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_INFUSION + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_ATEMWEGE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_NOTKOMPETENZ + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_SAUERSTOFFGABE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_INTUBATION + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_BEATMUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_HERZDRUCKM + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_ERSTDEFI + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_BETREUUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_SONSTIGES + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_SONSTIGESTEXT + " TEXT, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_AED + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_KEINE + " INTEGER, " +
                    FalleingabeContract.Tbl_Massnahmen.COL_PROT_ID + " INTEGER NOT NULL, "+
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Massnahmen.COL_PROT_ID+") REFERENCES "+FalleingabeContract.Tbl_Einsatz.TABLE_NAME+"("+FalleingabeContract.Tbl_Einsatz.COL_ID+"));";
    public static final String SQL_DROP_Massnahmen = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Massnahmen.TABLE_NAME + ";";

    /* von Vivien Stumpe, 08.05.16
    * Ergebnis
    */
    public static final String SQL_CREATE_Ergebnis =
            "CREATE TABLE " + FalleingabeContract.Tbl_Ergebnis.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Ergebnis.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ERGEBNISZEIT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ZUST_VERBESSERT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_WERTSACHEN + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_WERTSACHEN_ZEIT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_BEMERKUNG + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_ZEIT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_FUNKRUF + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_FUNKRUF_ZEIT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT_ZIEL + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ENTLASSUNG_EV + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ZEUGE + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ZUSTAND + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NOTARZT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_HAUSARZT_INFORMIERT + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_TOD + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT_SONSTIGES + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ERGEBNIS_ZEIT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_ERSTHELFERMASSN + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_KTW + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_RTW + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_NEF + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_NAW + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_RTH + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_FEUERWEHR + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_POLIZEI + " INTEGER, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_FOTO + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_SONSTIGESTEXT + " TEXT, " +
                    FalleingabeContract.Tbl_Ergebnis.COL_PROT_ID + " INTEGER NOT NULL, " +
    "FOREIGN KEY("+FalleingabeContract.Tbl_Ergebnis.COL_PROT_ID+") REFERENCES "+FalleingabeContract.Tbl_Einsatz.TABLE_NAME+"("+FalleingabeContract.Tbl_Einsatz.COL_ID+"));";
    public static final String SQL_DROP_Ergebnis = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Ergebnis.TABLE_NAME + ";";

    /* von Vivien Stumpe, 08.05.16
    * Erkrankung
    */
    public static final String SQL_CREATE_Erkrankung =
            "CREATE TABLE " + FalleingabeContract.Tbl_Erkrankung.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Erkrankung.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_ATMUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_HERZ_KREISLAUF + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_BAUCHERKRANKUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_STOFFWECHSEL + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_HITZSCHLAG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_VERGIFTUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_UNTERKUEHLUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_GYNAEKOLOGIE + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_GEBURTSHILFE + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_HITZEERSCHOEPFUNG + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_KINDERNOTFALL + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_NEUROLOGIE + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_PSYCHATRIE + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_ALKOHOLISIERT + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_SONSTIGES + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_SONSTIGESTEXT + " TEXT, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_SCHWINDEL + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_UEBELKEIT_ERBRECHEN + " INTEGER, " +
                    FalleingabeContract.Tbl_Erkrankung.COL_PROT_ID + " INTEGER NOT NULL, "+
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Erkrankung.COL_PROT_ID+") REFERENCES "+FalleingabeContract.Tbl_Einsatz.TABLE_NAME+"("+FalleingabeContract.Tbl_Einsatz.COL_ID+"));";
    public static final String SQL_DROP_Erkrankung = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Erkrankung.TABLE_NAME + ";";
    /* von Vivien Stumpe, 08.05.16
    * Erstbefund
    */
    public static final String SQL_CREATE_Erstbefund =
            "CREATE TABLE " + FalleingabeContract.Tbl_Erstbefund.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Erstbefund.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_BEWUSSTSEIN + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_SCHMERZEN + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_KREISLAUF + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_EKG + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_ATMUNG + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_RR_SYS + " INTEGER, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_RR_DIA + " INTEGER, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_PULS + " INTEGER, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_AF + " INTEGER, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_SPO2 + " REAL, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_BZ + " REAL, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_PUPILLE_LI + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_PUPILLE_RE + " TEXT, " +
                    FalleingabeContract.Tbl_Erstbefund.COL_PROT_ID + " INTEGER NOT NULL, "+
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Erstbefund.COL_PROT_ID+") REFERENCES "+FalleingabeContract.Tbl_Einsatz.TABLE_NAME+"("+FalleingabeContract.Tbl_Einsatz.COL_ID+"));";

    public static final String SQL_DROP_Erstbefund = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Erstbefund.TABLE_NAME + ";";
    /* von Vivien Stumpe, 08.05.16
    * Einsatz
    */
    public static final String SQL_CREATE_Einsatz =
            "CREATE TABLE " + FalleingabeContract.Tbl_Einsatz.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Einsatz.COL_ID + " INTEGER PRIMARY KEY, " +
                    FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_DATUM + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_ZEIT_VON + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_ZEIT_BIS + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_ZUGEFUEHRT_DURCH + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_HILFSSTELLE + " INTEGER, " +
                    FalleingabeContract.Tbl_Einsatz.COL_MOSAN_TEAM + " INTEGER, " +
                    FalleingabeContract.Tbl_Einsatz.COL_SAN_WACHE + " INTEGER, " +
                    FalleingabeContract.Tbl_Einsatz.COL_POLIZEI_TXT + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_RTW_KTW_TXT + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_SAN_TEAM_TXT + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_NOTFALL + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_FUNDORT + " TEXT, " +
                    FalleingabeContract.Tbl_Einsatz.COL_PAT_ID + " INTEGER NOT NULL, " +
    "FOREIGN KEY("+FalleingabeContract.Tbl_Einsatz.COL_PAT_ID+") REFERENCES "+FalleingabeContract.Tbl_Patient.TABLE_NAME+"("+FalleingabeContract.Tbl_Patient.COL_ID+"));";

    public static final String SQL_DROP_Einsatz = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Einsatz.TABLE_NAME + ";";
    /* von Vivien Stumpe, 09.05.16
    * Patient
    */
    public static final String SQL_CREATE_Patient =
            "CREATE TABLE " + FalleingabeContract.Tbl_Patient.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Patient.COL_ID + " INTEGER PRIMARY KEY, " +
                    FalleingabeContract.Tbl_Patient.COL_NAME + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Patient.COL_VORNAME + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Patient.COL_GEBURTSDATUM + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Patient.COL_GESCHLECHT + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_STRASSE + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_PLZ + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_WOHNORT + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_LAND + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_TELEFON + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_KRANKENKASSE + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_VERSICHERTENNUMMER + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_VERSICHERUNG_NUMMER + " TEXT, " +
                    FalleingabeContract.Tbl_Patient.COL_SANI_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Patient.COL_SANI_ID+") REFERENCES "+FalleingabeContract.Tbl_Sani.TABLE_NAME+"("+FalleingabeContract.Tbl_Sani.COL_ID+"));";
    public static final String SQL_DROP_Patient = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Patient.TABLE_NAME + ";";
    /* von Vivien Stumpe, 09.05.16
    * Sanitäter
    */
    public static final String SQL_CREATE_Sani =
            "CREATE TABLE " + FalleingabeContract.Tbl_Sani.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Sani.COL_ID + " INTEGER PRIMARY KEY, " +
                    FalleingabeContract.Tbl_Sani.COL_NAME + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Sani.COL_VORNAME + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Sani.COL_VERBAND_ID + " INTEGER NOT NULL, "+
                    "FOREIGN KEY("+FalleingabeContract.Tbl_Sani.COL_VERBAND_ID+") REFERENCES "+FalleingabeContract.Tbl_Verband.TABLE_NAME+"("+FalleingabeContract.Tbl_Verband.COL_ID+"));";
    public static final String SQL_DROP_Sani = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Sani.TABLE_NAME + ";";
    /* von Vivien Stumpe, 08.05.16
    * Verband
    */
    public static final String SQL_CREATE_Verband =
            "CREATE TABLE " + FalleingabeContract.Tbl_Verband.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Verband.COL_ID + " INTEGER PRIMARY KEY, " +
                    FalleingabeContract.Tbl_Verband.COL_KREISVERBAND + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Verband.COL_ORTSVEREIN + " TEXT NOT NULL );";
    public static final String SQL_DROP_Verband = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Verband.TABLE_NAME + ";";
    /* von Vivien Stumpe, 08.05.16
    * Veranstaltung
    */
    public static final String SQL_CREATE_Veranstaltung =
            "CREATE TABLE " + FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME +
                    "(" + FalleingabeContract.Tbl_Veranstaltung.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_NAME + " TEXT NOT NULL, " +
                    FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_ORT + " TEXT, " +
                    FalleingabeContract.Tbl_Veranstaltung.COL_VERBAND_ID + " INTEGER NOT NULL, " +
                    FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_DATUM + " TEXT NOT NULL, " +
    "FOREIGN KEY("+FalleingabeContract.Tbl_Veranstaltung.COL_VERBAND_ID+") REFERENCES "+FalleingabeContract.Tbl_Verband.TABLE_NAME+"("+FalleingabeContract.Tbl_Verband.COL_ID+"));";
    public static final String SQL_DROP_Veranstaltung = "DROP TABLE IF EXISTS " + FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME + ";";

    // von Vivien Stumpe, 06.05.16
    public FalleingabeDBHelper(Context context) {
        //Aufruf des Konstruktors der Oberklasse, damit die DB erzeugt werden kann
        super(context, db_name, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDbName() + " erzeugt.");
    }
    /* von Vivien Stumpe, 06.05.16
    mit execSQL können SQL-Befehle an der DB ausgeführt werden, nachdem der Konstruktor der Oberklasse aufgerufen wurde
    Dafür wird noch eine Referenz zur DB benötigt
    ->getWritableDatabase() und getReadableDatabase() ermöglichen diese Referenz
    wir wählen getWritableDatabase(), damit wir auch schreibend zugreifen können
    getWritableDatabase() prüft, ob die DB schon vorhanden ist und liefert diese zurück
    -> wenn Sie nicht vorhanden ist, wird onCreate(SQLiteDatabase) aufgerufen und eine neue DB erstellt
    */

    /* von Vivien Stumpe, 06.05.16
    Wird implizit aufgerufen, wenn noch keine Datenbank vorhanden ist
    und eine neue erzeugt werden soll
    -> hier werden die Tabellen der DB angelegt
    erweitert von Vivien Stumpe, 08.05.16
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            if (!db.isReadOnly()) {
                // Foreign Keys aktivieren - defaultmäßig ausgeschaltet
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Verletzung + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Verletzung);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Massnahmen + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Massnahmen);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Ergebnis + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Ergebnis);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Erkrankung + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Erkrankung);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Erstbefund + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Erstbefund);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Einsatz + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Einsatz);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Patient + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Patient);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Sani + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Sani);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Verband + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Verband);
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_Veranstaltung + " angelegt.");
            //.execSQL führt SQL Befehle an der DB aus
            db.execSQL(SQL_CREATE_Veranstaltung);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }
    /* von Vivien Stumpe, 09.05.16
    Wird implizit aufgerufen, wenn eine Datenbank vorhanden ist, aber die Versionsnummer veraltet ist
    -> hier werden die Tabellen der DB gelöscht und neu angelegt
    Bei Auslieferung muss die Methode erweiter werden, damit keine Daten verloren gehen!
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // alle Tabellen löschen
        db.execSQL(SQL_DROP_Einsatz);
        db.execSQL(SQL_DROP_Patient);
        db.execSQL(SQL_DROP_Sani);
        db.execSQL(SQL_DROP_Veranstaltung);
        db.execSQL(SQL_DROP_Verband);
        db.execSQL(SQL_DROP_Verletzung);
        db.execSQL(SQL_DROP_Massnahmen);
        db.execSQL(SQL_DROP_Ergebnis);
        db.execSQL(SQL_DROP_Erkrankung);
        db.execSQL(SQL_DROP_Erstbefund);

        // Tabellen neu anlegen
        onCreate(db);
    }

    public String getDbName(){
        return db_name;
    }
}
