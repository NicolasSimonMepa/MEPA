//Zuletzt geändert von Vivien Stumpe, 23.05.16
package de.app.mepa;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.app.mepa.mepa.R;

/**
 * Created by vivienstumpe on 21.05.16.
 * Adapter für die ListView zur Fallübersicht
 * nötig, damit auf das Löschen Icon geklickt werden kann
 */
public class Adapter_Falluebersicht extends ArrayAdapter<String> {
    Context ctx;
    String[] faelle;
    LayoutInflater inflater;
    int layout;
    FalleingabeDataSource dataSource;
    public Adapter_Falluebersicht(Context context, int layout, String[] faelle) {
        super(context, layout, faelle );
        this.ctx=context;
        this.faelle=faelle;
        this.layout=layout;
    }
    public View getView(int position,View convertView,ViewGroup parent) {
        View row=null;
        final int position_clicked;
        if(convertView==null)
        {
            inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout, null, true);
        }
        else
        {
            row=convertView;
        }


        ImageView img_delete = (ImageView) row.findViewById(R.id.imgv_delete_falluebersicht);
        TextView txtv_fall=(TextView)row.findViewById(R.id.txtv_falluebersicht_listview);
        txtv_fall.setText(faelle[position]);
        position_clicked=position;
        img_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ctx)
                        .setTitle("Fall löschen")
                        .setMessage("Soll dieser Fall wirklich gelöscht werden?")
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                // von Vivien Stumpe, 23.05.16
                                String string = faelle[position_clicked];
                                //Teilt den String bei einem Leerzeichen
                                String[] parts = string.split("\\s+");
                                String prot_id_string = parts[0];
                                String name = parts[1];
                                //prot_id enthält die ID des ausgewählten Falls
                                int prot_id=Integer.parseInt(prot_id_string);
                                Log.d("Fall", prot_id+" ID");
                                dataSource=new FalleingabeDataSource(getContext());
                                //Fall löschen
                                //dataSource.deleteFall(prot_id);
                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        return row;

    };

}
