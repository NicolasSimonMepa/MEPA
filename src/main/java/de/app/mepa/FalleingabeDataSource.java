//Zuletzt geändert von Vivien Stumpe, 10.05.16
package de.app.mepa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.app.mepa.pers_daten.Pers_daten;

/**
 * Created by vivienstumpe on 06.05.16.
 * Arbeiterklasse - Datensätze werden in die DB geschrieben und ausgelesen
 * hält die Verbindung zur Datenbank aufrecht
 */
public class FalleingabeDataSource {
    private static final String LOG_TAG = FalleingabeDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private FalleingabeDBHelper dbHelper;


    public FalleingabeDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new FalleingabeDBHelper(context);
    }
    /* von Vivien Stumpe, 06.05.16
    getWritableDatabase() und getReadableDatabase() ermöglichen eine Referenz zur DB
    wir wählen getWritableDatabase(), damit wir auch schreibend auf die DB zugreifen können
    getWritableDatabase() prüft, ob die DB schon vorhanden ist
    -> wenn Sie nicht vorhanden ist, wird onCreate(SQLiteDatabase) vom DBHelper aufgerufen und eine neue DB erstellt
    */
    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        //Verbindung zur DB herstellen -> beim ersten Aufruf wird die DB hier erstellt
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
        if (!database.isReadOnly()) {
            // Foreign Keys aktivieren - defaultmäßig ausgeschaltet
            database.execSQL("PRAGMA foreign_keys=ON;");
        }

    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    /* von Vivien Stumpe, 08.05.16
    Test, ob Daten in die DB geschrieben werden können
    */
    public void createSanitaeter(String name, String vname, Integer verbandnr) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Sani.COL_NAME, name);
        values.put(FalleingabeContract.Tbl_Sani.COL_VORNAME, vname);
        values.put(FalleingabeContract.Tbl_Sani.COL_VERBAND_ID, verbandnr);

        long insertId = database.insert(FalleingabeContract.Tbl_Sani.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Sani.TABLE_NAME + " eingefügt.");

       /* Cursor cursor = database.query(FalleingabeDBHelper.TABLE_SHOPPING_LIST,
                columns, FalleingabeContract.Tbl_Sani.COL_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Mitarbeiterkonfig/Sanitaeter sani = cursorToSani(cursor);
        cursor.close();

        return sani;
        */
    }

    /* von Vivien Stumpe, 09.05.16
    Test, ob Daten in die DB geschrieben werden können
    */
    public void createVerband(String kreisver, String ortsver) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Verband.COL_KREISVERBAND, kreisver);
        values.put(FalleingabeContract.Tbl_Verband.COL_ORTSVEREIN, ortsver);

        long insertId = database.insert(FalleingabeContract.Tbl_Verband.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Verband.TABLE_NAME + " eingefügt.");
    }
    /* von Vivien Stumpe, 10.05.16
    Test, ob Daten in die DB geschrieben werden können
     -> für AsyncTask (BackgroundTaskDB)
    */
    public void insertVerband(SQLiteDatabase db, String kreisver, String ortsver) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Verband.COL_KREISVERBAND, kreisver);
        values.put(FalleingabeContract.Tbl_Verband.COL_ORTSVEREIN, ortsver);

        long insertId = db.insert(FalleingabeContract.Tbl_Verband.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Verband.TABLE_NAME + " eingefügt.");
    }
        public void insertVerband(int id, String kreisver, String ortsver) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Verband.COL_ID, id);
        values.put(FalleingabeContract.Tbl_Verband.COL_KREISVERBAND, kreisver);
        values.put(FalleingabeContract.Tbl_Verband.COL_ORTSVEREIN, ortsver);

        long insertid= database.insert(FalleingabeContract.Tbl_Verband.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Verband.TABLE_NAME + " eingefügt.");
        Log.d(LOG_TAG, insertid+"Insert id");
    }
    /* von Vivien Stumpe, 09.05.16
    Test, ob Daten in die DB geschrieben werden können
    */
    public void insertPatient(Integer id, String name, String vname, String gebdat, Integer sani_id) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Patient.COL_ID, id);
        values.put(FalleingabeContract.Tbl_Patient.COL_NAME, name);
        values.put(FalleingabeContract.Tbl_Patient.COL_VORNAME, vname);
        values.put(FalleingabeContract.Tbl_Patient.COL_GEBURTSDATUM, gebdat);
        values.put(FalleingabeContract.Tbl_Patient.COL_SANI_ID, sani_id);

        long insertId = database.insert(FalleingabeContract.Tbl_Patient.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Patient.TABLE_NAME + " eingefügt.");
    }
    // von Nicolas Simon, 21.05.16
    public void insertVeranstaltung(SQLiteDatabase db, String verName, String verOrt, String verDate, String verbandID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_NAME, verName);
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_ORT, verOrt);
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_DATUM, verDate);

        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERBAND_ID, verbandID);

        long insertId = db.insert(FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME + " eingefügt.");
    }
        public void insertVeranstaltung( String verName, String verOrt, String verDate, int verbandID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_NAME, verName);
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_ORT, verOrt);
        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_DATUM, verDate);

        values.put(FalleingabeContract.Tbl_Veranstaltung.COL_VERBAND_ID, verbandID);

        long insertId = database.insert(FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME + " eingefügt.");
    }
    // von Nicolas Simon, 21.05.16
    public void insertSani(SQLiteDatabase db, String sName, String sVname, Integer verbandID, Integer saniID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Sani.COL_NAME, sName);
        values.put(FalleingabeContract.Tbl_Sani.COL_VORNAME, sVname);
        values.put(FalleingabeContract.Tbl_Sani.COL_ID, saniID);
        values.put(FalleingabeContract.Tbl_Sani.COL_VERBAND_ID, verbandID);

        long insertId = db.insert(FalleingabeContract.Tbl_Sani.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Sani.TABLE_NAME + " eingefügt.");
    }
     public void insertSani(int id, String sName, String sVname, int verbandID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Sani.COL_ID, id);
        values.put(FalleingabeContract.Tbl_Sani.COL_NAME, sName);
        values.put(FalleingabeContract.Tbl_Sani.COL_VORNAME, sVname);

        values.put(FalleingabeContract.Tbl_Sani.COL_VERBAND_ID, verbandID);

        long insertId = database.insert(FalleingabeContract.Tbl_Sani.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Sani.TABLE_NAME + " eingefügt.");
    }
    /* von Vivien Stumpe, 09.05.16, ergänzt von Nicolas Simon, 21.05.16
    Test, ob Daten in die DB geschrieben werden können
    */
    public void insertPatient(Integer id, String name, String vname, String gebdat, Integer sani_id, String sex, String str, String plz,
                              String ort, String land, String tel, String kk, String versicherungNum, String versichertenNum) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Patient.COL_ID, id);
        values.put(FalleingabeContract.Tbl_Patient.COL_NAME, name);
        values.put(FalleingabeContract.Tbl_Patient.COL_VORNAME, vname);
        values.put(FalleingabeContract.Tbl_Patient.COL_GEBURTSDATUM, gebdat);
        values.put(FalleingabeContract.Tbl_Patient.COL_GESCHLECHT, sex);
        values.put(FalleingabeContract.Tbl_Patient.COL_STRASSE, str);
        values.put(FalleingabeContract.Tbl_Patient.COL_PLZ, plz);
        values.put(FalleingabeContract.Tbl_Patient.COL_WOHNORT, ort);
        values.put(FalleingabeContract.Tbl_Patient.COL_LAND, land);
        values.put(FalleingabeContract.Tbl_Patient.COL_TELEFON, tel);
        values.put(FalleingabeContract.Tbl_Patient.COL_KRANKENKASSE, kk);
        values.put(FalleingabeContract.Tbl_Patient.COL_VERSICHERUNG_NUMMER, versicherungNum);
        values.put(FalleingabeContract.Tbl_Patient.COL_VERSICHERTENNUMMER, versichertenNum);
        values.put(FalleingabeContract.Tbl_Patient.COL_SANI_ID, sani_id);

        long insertId = database.insert(FalleingabeContract.Tbl_Patient.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Patient.TABLE_NAME + " eingefügt.");
    }
    // von Nicolas Simon, 21.05.16
    public void insertEinsatz(Integer id, String date, String zeitV, String zeitB, String zugefuehrt, Integer hilfsStelle, Integer mosan, Integer san,
                              String polizei, String rtw, String sanTeam, String notfall, String fundort, Integer fallID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Einsatz.COL_PAT_ID, id);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_DATUM, date);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_ZEIT_VON, zeitV);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_EINSATZ_ZEIT_BIS, zeitB);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_ZUGEFUEHRT_DURCH, zugefuehrt);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_HILFSSTELLE, hilfsStelle);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_MOSAN_TEAM, mosan);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_SAN_WACHE, san);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_POLIZEI_TXT, polizei);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_RTW_KTW_TXT, rtw);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_SAN_TEAM_TXT, sanTeam);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_NOTFALL, notfall);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_FUNDORT, fundort);
        values.put(FalleingabeContract.Tbl_Einsatz.COL_ID, fallID);

        long insertId = database.insert(FalleingabeContract.Tbl_Einsatz.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Einsatz.TABLE_NAME + " eingefügt.");
    }
    // von Daniel Semmler, 22.05.16
    public void insertErkrankung( Integer atmung, Integer kreislauf, Integer baucherkrankung, Integer stoffwechsel, Integer hitzschlag, Integer vergiftung, Integer untetrkunft,
                                 Integer gynaekologie, Integer geburtshilfe, Integer hitzeerschoepfung, Integer kindernotfall, Integer neurologie, Integer psychatrie, Integer alkoholiisiert, Integer sonsiges, String sonstigestext, Integer schwindel, Integer uebelkeit_erbrechen, Integer fallID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_ATMUNG, atmung);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_HERZ_KREISLAUF, kreislauf);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_BAUCHERKRANKUNG, baucherkrankung);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_STOFFWECHSEL, stoffwechsel);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_HITZSCHLAG, hitzschlag);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_VERGIFTUNG, vergiftung);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_UNTERKUEHLUNG, untetrkunft);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_GYNAEKOLOGIE, gynaekologie);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_GEBURTSHILFE, geburtshilfe);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_HITZEERSCHOEPFUNG, hitzeerschoepfung);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_KINDERNOTFALL, kindernotfall);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_NEUROLOGIE, neurologie);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_PSYCHATRIE, psychatrie);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_ALKOHOLISIERT, alkoholiisiert);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_SONSTIGES, sonsiges);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_SONSTIGESTEXT, sonstigestext);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_SCHWINDEL, schwindel);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_UEBELKEIT_ERBRECHEN, uebelkeit_erbrechen);
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_PROT_ID, fallID);

        long insertId = database.insert(FalleingabeContract.Tbl_Erkrankung.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Erkrankung.TABLE_NAME + " eingefügt.");
    }
    
    // von Nicolas Simon, 22.05.16
    public void insertErstbefund(Integer fallID, String bewust, String schmerz, String kreislauf, String ekg, String atmung, Integer rrSys, Integer rrDia,
                              Integer puls, Integer af, float spo , float bz, String pupilleLi, String pupilleRe) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_PROT_ID, fallID);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_BEWUSSTSEIN, bewust);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_SCHMERZEN, schmerz);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_KREISLAUF, kreislauf);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_EKG, ekg);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_ATMUNG, atmung);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_RR_SYS, rrSys);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_RR_DIA, rrDia);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_PULS, puls);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_AF, af);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_SPO2, spo);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_BZ, bz);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_PUPILLE_LI, pupilleLi);
        values.put(FalleingabeContract.Tbl_Erstbefund.COL_PUPILLE_RE, pupilleRe);

        long insertId = database.insert(FalleingabeContract.Tbl_Erstbefund.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Erstbefund.TABLE_NAME + " eingefügt.");
    }

    // von Nicolas Simon, 22.05.16
    public void insertErgebnis(Integer fallID, String ergzeit, String zustVerbess, String wertsachen, String wertsachenZeit, String bemerk, String nachfZeit, String funkruf,
                               String funkrufZeit, String transport, String transportZiel , Integer entlassungEV, String zeuge, String zustand, String notarzt,
                               Integer hausarztInfo, Integer tod, String transportSonstig, String ersthelfermassmahme, Integer nKTW, Integer nRTW,
                               Integer nNEF, Integer nNAW, Integer nRTH, Integer nFeuerwehr, Integer nPolizei, String sonstigesTxt) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_PROT_ID, fallID);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ERGEBNIS_ZEIT, ergzeit);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ZUST_VERBESSERT, zustVerbess);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_WERTSACHEN, wertsachen);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_WERTSACHEN_ZEIT, wertsachenZeit);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_BEMERKUNG, bemerk);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_ZEIT, nachfZeit);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_FUNKRUF, funkruf);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_FUNKRUF_ZEIT, funkrufZeit);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT, transport);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT_ZIEL, transportZiel);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ENTLASSUNG_EV, entlassungEV);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ZEUGE, zeuge);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ZUSTAND, zustand);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NOTARZT, notarzt);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_HAUSARZT_INFORMIERT, hausarztInfo);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_TOD, tod);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_TRANSPORT_SONSTIGES, transportSonstig);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_ERSTHELFERMASSN, ersthelfermassmahme);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_KTW, nKTW);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_RTW, nRTW);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_NEF, nNEF);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_NAW, nNAW);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_RTW, nRTH);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_FEUERWEHR, nFeuerwehr);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_NACHFORDERUNG_POLIZEI, nPolizei);
        values.put(FalleingabeContract.Tbl_Ergebnis.COL_SONSTIGESTEXT, sonstigesTxt);

        long insertId = database.insert(FalleingabeContract.Tbl_Ergebnis.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Ergebnis.TABLE_NAME + " eingefügt.");
    }
    // von Nicolas Simon, 22.05.16
    public void insertVerletzung(Integer fallID, Integer elektroUnfall, Integer wundeVerletzung, Integer inhalationstrauma, Integer sonstig, Integer verbrennung, Integer prellungVerletzung, String schaedelart,
                                 String gesichtArt, String brustkorbArt, String bwsArt , String hwsArt, String beckenArt, String bauchArt, String beineArt, String armeArt,
                                 String weichteileArt, String schaedelGrad, String gesichtGrad, String brustkorbGrad, String bwsGrad, String hwsGrad, String beckenGrad, String bauchGrad,
                                 String beineGrad, String armeGrad, String weichteileGrad) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Verletzung.COL_PROT_ID, fallID);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_ELEKTROUNFALL, elektroUnfall);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_WUNDE_VERLETZUNG, wundeVerletzung);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_INHALATIONSTRAUMA, inhalationstrauma);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_SONSTIGES, sonstig);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_VERBRENNUNG, verbrennung);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_PRELLUNG_VERLETZUNG, prellungVerletzung);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_SCHAEDEL_ART, schaedelart);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_GESICHT_ART, gesichtArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BRUSTKORB_ART, brustkorbArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BWS_ART, bwsArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_HWS_ART, hwsArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BECKEN_ART, beckenArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BAUCH_ART, bauchArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BEINE_ART, beineArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_ARME_ART, armeArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_WEICHTEILE_ART, weichteileArt);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_SCHAEDEL_GRAD, schaedelGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_GESICHT_GRAD, gesichtGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BRUSTKORB_GRAD, brustkorbGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_HWS_GRAD, hwsGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BECKEN_GRAD, beckenGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BAUCH_GRAD, bauchGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_BEINE_GRAD, beineGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_ARME_GRAD, armeGrad);
        values.put(FalleingabeContract.Tbl_Verletzung.COL_WEICHTEILE_GRAD, weichteileGrad);

        long insertId = database.insert(FalleingabeContract.Tbl_Verletzung.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Verletzung.TABLE_NAME + " eingefügt.");
    }
    // von Daniel Semmler, 22.05.16
    public void insertMassnahmen(Integer fallID,Integer stb_seitenlage, Integer oberkoerperhochlage, Integer flachlagerung, Integer schocklagerung,
                                 Integer vakuummatratze, Integer hws_st, Integer medikamente, Integer schienung, Integer wundversorgung,
                                 Integer ekg, Integer zugang, Integer infusion, Integer atemwege, 
                                 Integer notkompetenz, Integer sauerstoffgabe,Integer intubation, Integer beatmung, Integer herzdruckm,
                                 Integer erstdefi, Integer betreuung, Integer sonstiges, Integer sonstigestext, Integer aed,
                                 Integer keine) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_PROT_ID, fallID);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_STB_SEITENLAGE, stb_seitenlage);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_OBERKOERPERHOCHLAGE, oberkoerperhochlage);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_FLACHLAGERUNG, flachlagerung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_SCHOCKLAGERUNG, schocklagerung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_VAKUUMM, vakuummatratze);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_HWS_ST, hws_st);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_MEDIKAMENTE, medikamente);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_SCHIENUNG, schienung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_WUNDVERSORGUNG, wundversorgung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_EKG, ekg);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_ZUGANG, zugang);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_INFUSION, infusion);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_ATEMWEGE, atemwege);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_NOTKOMPETENZ, notkompetenz);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_SAUERSTOFFGABE, sauerstoffgabe);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_INTUBATION, intubation);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_BEATMUNG, beatmung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_HERZDRUCKM, herzdruckm);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_ERSTDEFI, erstdefi);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_BETREUUNG, betreuung);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_SONSTIGES, sonstiges);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_SONSTIGESTEXT, sonstigestext);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_AED, aed);
        values.put(FalleingabeContract.Tbl_Massnahmen.COL_KEINE, keine);

        long insertId = database.insert(FalleingabeContract.Tbl_Massnahmen.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Massnahmen.TABLE_NAME + " eingefügt.");
    }
    /* von Vivien Stumpe, 22.05.16
    Dienste, die die vorhandenen Stammdaten laden und zurückgeben
    */
 public GlobaleDaten selectVerband(){
        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        String[] columns={FalleingabeContract.Tbl_Verband.COL_ID, FalleingabeContract.Tbl_Verband.COL_KREISVERBAND, FalleingabeContract.Tbl_Verband.COL_ORTSVEREIN};
        Cursor cursor=database.query(FalleingabeContract.Tbl_Verband.TABLE_NAME, columns, null, null, null, null, null, "1");

        GlobaleDaten mfall=new GlobaleDaten();if(cursor.getCount() >= 1){
            cursor.moveToFirst();

            mfall.setVerb_ID(cursor.getInt(0));
            mfall.setVerb_kreisv(cursor.getString(1));
            mfall.setVerb_ortsv(cursor.getString(2));
            Log.d(LOG_TAG, mfall.toString());
        }
        return mfall;
    }
    public GlobaleDaten selectVeranstaltung(){
        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        String[] columns={FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_NAME, FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_DATUM,
                FalleingabeContract.Tbl_Veranstaltung.COL_VERANSTALTUNG_ORT};
        Cursor cursor=database.query(FalleingabeContract.Tbl_Veranstaltung.TABLE_NAME, columns, null, null, null, null, null, "1");
        GlobaleDaten mfall=new GlobaleDaten();
        if(cursor.getCount() >= 1){
            cursor.moveToFirst();
            mfall.setVer_name(cursor.getString(0));
            mfall.setVer_date(cursor.getString(1));
            mfall.setVer_ort(cursor.getString(2));
            Log.d(LOG_TAG, mfall.toString());
        }
        return mfall;
    }
    public GlobaleDaten selectSani(){
        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        String[] columns={FalleingabeContract.Tbl_Sani.COL_NAME, FalleingabeContract.Tbl_Sani.COL_VORNAME,
                FalleingabeContract.Tbl_Sani.COL_VERBAND_ID, FalleingabeContract.Tbl_Sani.COL_ID};
        Cursor cursor=database.query(FalleingabeContract.Tbl_Sani.TABLE_NAME, columns, null , null, null, null, null, "1");
        GlobaleDaten mfall=new GlobaleDaten();
        if(cursor.getCount() >= 1){
            cursor.moveToFirst();
            mfall.setSan_name(cursor.getString(0));
            mfall.setSan_vorname(cursor.getString(1));
            mfall.setSani_IDm(cursor.getInt(3));
            Log.d(LOG_TAG, mfall.toString());
        }
        return mfall;
    }
}

