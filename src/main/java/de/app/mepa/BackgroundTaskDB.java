//zuletzt bearbeitet von Vivien Stumpe, 10.05.16
package de.app.mepa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by vivienstumpe on 10.05.16.
 * Gewährleistet eine schnelle Arbeit vom UI-Thread
 * kompliziertere, länger dauernde Operationen werden hierhin ausgelagert,
 * um den UI-Thread nicht zu verlangsamen und Aktionen im Hintergrund ausführen zu können
 * bei uns sind das Speichern der Daten in der DB und Laden der Daten aus der DB
 */
public class BackgroundTaskDB extends AsyncTask<String, Void, String> {

    Context ctx;

   public BackgroundTaskDB(Context c){
        this.ctx=c;
    }
    // von Vivien Stumpe, 10.05.16
    // wird im UI-Thread ausgeführt, bevor die Background Aktivitäten ausgeführt werden
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* von Vivien Stumpe, 10.05.16
    Wird in einem zweiten Thread ausgeführt - NICHT im UI-Thread!
    Interaktionen mit der Datenbank finden hier statt
     */
    @Override
    protected String doInBackground(String... params) {
        //params[0] entspricht dem ersten Parameter im Aufruf von bgtask.execute();
        String method=params[0];
        FalleingabeDataSource datasource=new FalleingabeDataSource(ctx);
        FalleingabeDBHelper dbhelper = new FalleingabeDBHelper(ctx);
        SQLiteDatabase db;
        // wenn ein Fall hinzugefügt werden soll:
        if(method.equals("Verein hinzufügen")){
            String verband=params[1];
            String ortsverein=params[2];
            db=dbhelper.getWritableDatabase();
            datasource.insertVerband(db, verband, ortsverein);
            return "BackgroundTask: Einen Verein hinzugefügt";
        }
        // wenn ein Fall angezeigt werden soll könnte es so beginnen:
        if(method.equals("Fall anzeigen")){
            db=dbhelper.getReadableDatabase();
           // Cursor cursor= datasource.getFaelle(db);
            return "BackgroundTask: Fälle anzeigen";
        }
        return null;

    }
    /* von Vivien Stumpe, 10.05.16
     wird im UI-Thread ausgeführt
     nützlich, um User anzuzeigen, wenn ein Vorgang länger dauert (Progress-Bar oder ähnliches)
    */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    /* von Vivien Stumpe, 10.05.16
     wird im UI-Thread ausgeführt
     zeigt das Ergebnis von doInBackground an
    */
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

    }
}
