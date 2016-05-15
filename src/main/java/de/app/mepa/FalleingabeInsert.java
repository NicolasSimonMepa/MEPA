package de.app.mepa;
import java.sql.*;
/**
 * Created by Daniel on 15.05.2016.
 */


public class FalleingabeInsert {



    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Falleingabe.db");
            c.setAutoCommit(false); // do multiple operations
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO Tbl_Verletzung (COL_ID, COL_ELEKTROUNFALL, COL_WUNDE_VERLETZUNG, COL_INHALATIONSTRAUMA, COL_SONSTIGES, COL_VERBRENNUNG, COL_PRELLUNG_VERLETZUNG, COL_SCHAEDEL_ART, COL_GESICHT_ART, COL_BRUSTKORB_ART, COL_BWS_ART, COL_HWS_ART, COL_BECKEN_ART, COL_BAUCH_ART, COL_BEINE_ART, COL_ARME_ART, COL_WEICHTEILE_ART, COL_GESICHT_GRAD, COL_BRUSTKORB_GRAD, COL_BWS_GRAD, COL_BECKEN_GRAD, COL_BAUCH_GRAD, COL_BEINE_GRAD, COL_ARME_GRAD, COL_WEICHTEILE_GRAD, COL_PROT_ID) " +
                            "VALUES (1, 0, 0, 0, 0, 0, 0, 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test');";
            stmt.executeUpdate(sql);


            sql = "INSERT INTO Tbl_Massnahmen (COL_ID, COL_STB_SEITENLAGE, COL_OBERKOERPERHOCHLAGE, COL_FLACHLAGERUNG, COL_SCHOCKLAGERUNG, COL_VAKUUMM, COL_HWS_ST, COL_MEDIKAMENTE, COL_SCHIENUNG, COL_WUNDVERSORGUNG, COL_EKG, COL_ZUGANG, COL_INFUSION, COL_ATEMWEGE, COL_NOTKOMPETENZ, COL_SAUERSTOFFGABE, COL_INTUBATION, COL_BEATMUNG, COL_HERZDRUCKM, COL_ERSTDEFI, COL_BETREUUNG, COL_SONSTIGES, COL_SONSTIGESTEXT, COL_AED, COL_KEINE, COL_PROT_ID) " +
                    "VALUES (1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'test', 'test', 1 );";
            stmt.executeUpdate(sql);


            sql = "INSERT INTO Tbl_Ergebnis (COL_ID, COL_ERGEBNISZEIT, COL_ZUST_VERBESSERT, COL_WERTSACHEN, COL_WERTSACHEN_ZEIT, COL_BEMERKUNG, COL_NACHFORDERUNG_ZEIT, COL_FUNKRUF, COL_FUNKRUF_ZEIT, COL_TRANSPORT, COL_TRANSPORT_ZIEL, COL_ENTLASSUNG_EV, COL_ZEUGE, COL_ZUSTAND, COL_NOTARZT, COL_HAUSARZT_INFORMIERT, COL_TOD, COL_TRANSPORT_SONSTIGES, COL_ERGEBNIS_ZEIT, COL_ERSTHELFERMASSN, COL_NACHFORDERUNG_KTW, COL_NACHFORDERUNG_RTW, COL_NACHFORDERUNG_NEF, COL_NACHFORDERUNG_NAW, COL_NACHFORDERUNG_RTH, COL_NACHFORDERUNG_FEUERWEHR, COL_NACHFORDERUNG_POLIZEI, COL_FOTO, COL_SONSTIGESTEXT, COL_PROT_ID) " +
                    "VALUES (1, 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 0, 'test', 'test', 'test', 0, 0, 'test', 'test', 'test',  0, 0, 0, 0, 0, 0, 0, 'test', 'test', 1);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Erkrankung (COL_ID, COL_ATMUNG, COL_HERZ_KREISLAUF, COL_BAUCHERKRANKUNG, COL_STOFFWECHSEL, COL_HITZSCHLAG, COL_VERGIFTUNG, COL_UNTERKUEHLUNG, COL_GYNAEKOLOGIE, COL_GEBURTSHILFE, COL_HITZEERSCHOEPFUNG, COL_KINDERNOTFALL, COL_NEUROLOGIE, COL_PSYCHATRIE, COL_ALKOHOLISIERT, COL_SONSTIGES, COL_SONSTIGESTEXT, COL_SCHWINDEL, COL_UEBELKEIT_ERBRECHEN, COL_PROT_ID) " +
                    "VALUES (1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'test', 0, 0, 1);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Erstbefund (COL_ID, COL_BEWUSSTSEIN, COL_SCHMERZEN ,COL_KREISLAUF ,COL_EKG ,COL_ATMUNG ,COL_RR_SYS ,COL_RR_DIA ,COL_PULS ,COL_AF ,COL_SPO2 ,COL_BZ ,COL_PUPILLE_LI ,COL_PUPILLE_RE ,COL_PROT_ID) " +
                    "VALUES (1, 'test', 'test', 'test', 'test', 'test', 0, 0, 0, 0, 255, 255, 0, 0, 1);";
            stmt.executeUpdate(sql);


            sql = "INSERT INTO Tbl_Einsatz (COL_ID, COL_EINSATZ_DATUM, COL_EINSATZ_ZEIT_VON, COL_EINSATZ_ZEIT_BIS, COL_ZUGEFUEHRT_DURCH, COL_HILFSSTELLE, COL_MOSAN_TEAM, COL_SAN_WACHE, COL_POLIZEI_TXT, COL_RTW_KTW_TXT, COL_SAN_TEAM_TXT, COL_NOTFALL, COL_FUNDORT, COL_PAT_ID) " +
                    "VALUES (1,  'test', 'test', 'test', 'test', 0, 0, 0, 'test', 'test', 'test', 'test', 'test', 1);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Patient (COL_ID, COL_NAME, COL_VORNAME, COL_GEBURTSDATUM, COL_GESCHLECHT, COL_STRASSE, COL_PLZ, COL_WOHNORT, COL_LAND, COL_TELEFON, COL_KRANKENKASSE, COL_VERSICHERTENNUMMER, COL_VERSICHERUNG_NUMMER, COL_SANI_ID) " +
                    "VALUES (1, 'pflichttest', 'pflichttest', 'pflichttest', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 1);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Sani (COL_ID, COL_NAME, COL_VORNAME, COL_VERBAND_ID) " +
                    "VALUES (1, 'pflichttest', 'pflichttest', 2);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Verband (COL_ID, COL_KREISVERBAND, COL_ORTSVEREIN) " +
                    "VALUES (1, 'pflichttest', 'pflichttest');";
                    stmt.executeUpdate(sql);

            sql = "INSERT INTO Tbl_Veranstaltung (COL_ID, COL_VERANSTALTUNG_NAME, COL_VERANSTALTUNG_ORT, COL_VERBAND_ID, COL_VERANSTALTUNG_DATUM) " +
                    "VALUES (1 'pflichttest', 'test', 2, 'pflichttest');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();  // HERE WE KNOW THE WORK WAS DONE
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}