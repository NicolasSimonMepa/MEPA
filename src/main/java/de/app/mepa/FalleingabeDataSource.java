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
    // von Nicolas Simon, 21.05.16
    public void insertSani(SQLiteDatabase db, String sName, String sVname, String verbandID) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Sani.COL_NAME, sName);
        values.put(FalleingabeContract.Tbl_Sani.COL_VORNAME, sVname);

        values.put(FalleingabeContract.Tbl_Sani.COL_VERBAND_ID, verbandID);

        long insertId = db.insert(FalleingabeContract.Tbl_Sani.TABLE_NAME, null, values);
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
    public void insertErkrankung(Integer id, Integer atmung, Integer kreislauf, Integer baucherkrankung, Integer stoffwechsel, Integer hitzschlag, Integer vergiftung, Integer untetrkunft,
                                 Integer gynaekologie, Integer geburtshilfe, Integer hitzeerschoepfung, Integer kindernotfall, Integer neurologie, Integer psychatrie, Integer alkoholiisiert, Integer sonsiges, Integer sonstigestext, Integer schwindel, Integer uebelkeit_erbrechen, Integer prot_id) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_ID, id);
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
        values.put(FalleingabeContract.Tbl_Erkrankung.COL_PROT_ID, prot_id);

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
}
