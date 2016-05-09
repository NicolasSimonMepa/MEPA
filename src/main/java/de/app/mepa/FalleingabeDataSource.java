//Zuletzt geändert von Vivien Stumpe, 08.05.16
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
    /* von Vivien Stumpe, 09.05.16
    Test, ob Daten in die DB geschrieben werden können
    */
    public void createPatient(Integer id, String name, String vname, String gebdat, Integer sani_id) {
        ContentValues values = new ContentValues();
        values.put(FalleingabeContract.Tbl_Patient.COL_ID, id);
        values.put(FalleingabeContract.Tbl_Patient.COL_NAME, name);
        values.put(FalleingabeContract.Tbl_Patient.COL_VORNAME, vname);
        values.put(FalleingabeContract.Tbl_Patient.COL_GEBURTSDATUM, gebdat);
        values.put(FalleingabeContract.Tbl_Patient.COL_SANI_ID, sani_id);

        long insertId = database.insert(FalleingabeContract.Tbl_Patient.TABLE_NAME, null, values);
        Log.d(LOG_TAG, "Datensätze in die Tabelle " + FalleingabeContract.Tbl_Patient.TABLE_NAME + " eingefügt.");
    }

}




