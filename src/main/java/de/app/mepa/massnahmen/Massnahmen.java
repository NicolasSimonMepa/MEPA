//Zuletzt bearbeitet von Vivien Stumpe, 11.04.16
package de.app.mepa.massnahmen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.app.mepa.OnSwipeTouchListener;
import de.app.mepa.erkrankung.Erkrankung;
import de.app.mepa.mepa.R;

public class Massnahmen extends AppCompatActivity {
    //von Vivien Stumpe, 11.04.16
    //View für das Hauptelement der Aktivität - zum Wechseln mittels Swipe
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massnahmen);
        //von Vivien Stumpe, 11.04.16
      /**  view=(View) findViewById(R.id.scrV_massnahmen);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(Massnahmen.this, Erstbefund.class);
                startActivity(intent);
            }
        }); **/
    }

}
