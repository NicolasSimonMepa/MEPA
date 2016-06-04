package de.app.mepa;

import android.app.Application;
import android.util.Log;
import java.util.UUID;

/**
 * Created by vivienstumpe on 14.05.16.
 * enthält Variablen und Dienste, die während der Laufzeit in allen Activities verfügbar sind
 */
public class GlobaleDaten extends Application {

    // Fall ID - Variable, Simon 18.05.16
    private int fallID;
    private int patID;

    private Boolean fallAusgewaehlt=false;

    //Ergebnis (Übergabe) - Variablen
    private String erg_ersthelfermassn;
    private String erg_zustand;
    private String erg_transport;
    private String erg_notarzt;
    private Integer erg_hausarzt_informiert;
    private Integer erg_tod;
    private String erg_wertsachen;
    private Integer erg_nachforderung_ktw;
    private Integer erg_nachforderung_rtw;
    private Integer erg_nachforderung_nef;
    private Integer erg_nachforderung_naw;
    private Integer erg_nachforderung_rth;
    private Integer erg_nachforderung_feuerwehr;
    private Integer erg_nachforderung_polizei;
    private String erg_funkruf;
    private String erg_transport_art;
    private String erg_transport_ziel;
    private String erg_transport_sonstiges;
    private Integer erg_entlassung_ev;
    private String erg_zeuge;
    private String erg_ergebnis_zeit;

    //Maßnahmen - Variablen
    private Integer mas_keine;
    private Integer mas_stb_seitenlage;
    private Integer mas_oberkoerperhochlage;
    private Integer mas_flachlagerung;
    private Integer mas_schocklagerung;
    private Integer mas_vakuummatratze;
    private Integer mas_hws_stuetzkragen;
    private Integer mas_medikamente;
    private Integer mas_extr_schienung;
    private Integer mas_wundversorgung;
    private Integer mas_ekg;
    private Integer mas_ven_zugang;
    private Integer mas_infusion;
    private Integer mas_atemwege_freim;
    private Integer mas_notkompetenz;
    private Integer mas_sauerstoffgabe;
    private Integer mas_intubation;
    private Integer mas_beatmung;
    private Integer mas_herzdruckmassage;
    private Integer mas_erstdefibrillation;
    private Integer mas_betreuung;
    private Integer mas_sonstiges;
    private String mas_sonstiges_text;

    //Verletzung - Variablen
    private Integer verl_prellung_verletzung;
    private Integer verl_verbrennung;
    private Integer verl_wunde_verletzung;
    private Integer verl_elektrounfall;
    private Integer verl_inhalationstrauma;
    private Integer verl_sonstiges;
    private String verl_schaedel_art;
    private String verl_schaedel_grad;
    private String verl_gesicht_art;
    private String verl_gesicht_grad;
    private String verl_hws_art;
    private String verl_hws_grad;
    private String verl_brustkorb_art;
    private String verl_brustkorb_grad;
    private String verl_bauch_art;
    private String verl_bauch_grad;
    private String verl_bws_lws_art;
    private String verl_bws_lws_grad;
    private String verl_becken_art;
    private String verl_becken_grad;
    private String verl_arme_art;
    private String verl_arme_grad;
    private String verl_beine_art;
    private String verl_beine_grad;
    private String verl_weichteile_art;
    private String verl_weichteile_grad;

    //Erstbefund - Variablen
    private String erst_bewusstsein;
    private String erst_kreislauf;
    private String erst_pupille_li;
    private String erst_pupille_re;
    private String erst_ekg;
    private String erst_schmerzen;
    private String erst_atmung;
    private String erst_rr_sys;
    private String erst_rr_dia;
    private String erst_puls;
    private String erst_af;
    private String erst_spo;
    private String erst_bz;

    //Notfallsituation - Variablen
    private String notf_notfallsituation;

    //Bemerkung - Variablen
    private String bem_bemerkung;

    //Erkrankung - Variablen
    private Integer erk_keine;
    private Integer erk_alkoholisiert;
    private Integer erk_erbrechen;
    private Integer erk_schwindel;
    private Integer erk_herzkreislauf;
    private Integer erk_hitzeschlag;
    private Integer erk_hitzeerschoepfung;
    private Integer erk_vergiftung;
    private Integer erk_atmung;
    private Integer erk_unterkuehlung;
    private Integer erk_baucherkrankung;
    private Integer erk_stoffwechsel;
    private Integer erk_neurologie;
    private Integer erk_psychatrie;
    private Integer erk_gynaekologie;
    private Integer erk_kindernotfall;
    private Integer erk_geburtshilfe;
    private Integer erk_sonstiges;
    private String erk_edtxt_sonstiges;

    //Patient - Variablen
    private String pat_name;
    private String pat_vname;
    private String pat_sex;
    private String pat_geb;
    private String pat_str;
    private String pat_plz;
    private String pat_ort;
    private String pat_land;
    private String pat_tel;
    private String pat_krankenkasse;
    private String pat_versnr;
    private String pat_versichertennr;
    private Integer pat_sani;

    //Einsatz - Attribute
    private String ein_zugef;
    private String ein_fundort;
    private Integer ein_mosan, ein_hilfs, ein_sanw;

    //Stammdaten - Attribute
    //wurden die Stammdaten für den Fall angelegt?
    private Boolean fall_angelegt=false;
    //soll der OnBoarding Prozess übersprungen werden?
    private Boolean uebersprungen=false;

    //Sanitäter - Attribute
    private int saniID;
    private String san_name;
    private String san_vname;
    private Boolean san_vorh=false;

    //Verband - Attribute
    private int verbandID;
    private String verb_kreisv;
    private String verb_ortsv;
    private Boolean verb_vorh=false;

    //Veranstaltung - Attribute
    private String ver_name;
    private String ver_ort;
    private String ver_date;
    private Boolean ver_vorh=false;

    //Sani, Verband, Pat ID - Dienste, Simon 21.05.16
    public void setSaniID(boolean a) {
        if (a == true){
            this.saniID = UUID.randomUUID().hashCode();
            if (saniID < 0 ){saniID = -saniID;}
        }
        else {saniID = 0;}
    }
    public void setSani_IDm(int id){
        this.saniID=id;
    }
    public int getSaniID(){return saniID;}
    public void loescheSaniID(){setSaniID(false);}

    public void setVerbandID(boolean a) {
        if (a == true){
            this.verbandID = UUID.randomUUID().hashCode();
            if (verbandID < 0 ){verbandID = -verbandID;}
        }
        else {verbandID = 0;}
    }
    public int getVerbandID(){return verbandID;}
    public void setVerb_ID(int id){
        this.verbandID=id;
    }
    public void loescheVerbandID(){setVerbandID(false);}

    public void setPatID(boolean a) {
        if (a == true){
            this.patID = UUID.randomUUID().hashCode();
            if (patID < 0 ){patID = -patID;}
        }
        else {patID = 0;}
    }
    public void setPat_IDm(int id){
        this.patID=id;
    }
    public int getPatID(){return patID;}
    public void loeschePatID(){setPatID(false);}

    //Fall ID - Dienste, Simon 18.05.16
    public void setFallID(boolean a) {
        if (a == true){
            this.fallID = UUID.randomUUID().hashCode();
            if (fallID < 0 ){fallID = -fallID;}
        }
        else {fallID = 0;}
    }
    public void setEin_ID(int id){
        this.fallID=id;
    }
    public int getFallID(){return fallID;}
    public void loescheFallID(){setFallID(false);}

    //Ergebnis (Übergabe) - Dienste
    public void setErg_ersthelfermassn(String ersthelfermassn){this.erg_ersthelfermassn=ersthelfermassn;}
    public void setErg_zustand(String zustand){this.erg_zustand=zustand;}
    public void setErg_transport(String transport){this.erg_transport=transport;}
    public void setErg_notarzt(String notarzt){this.erg_notarzt=notarzt;}
    public void setErg_hausarzt_informiert(Integer hausarzt_informiert){this.erg_hausarzt_informiert=hausarzt_informiert;}
    public void setErg_tod(Integer tod){this.erg_tod=tod;}
    public void setErg_wertsachen(String wertsachen){this.erg_wertsachen=wertsachen;}
    public void setErg_nachforderung_ktw(Integer nachforderung_ktw){this.erg_nachforderung_ktw=nachforderung_ktw;}
    public void setErg_nachforderung_rtw(Integer nachforderung_rtw){this.erg_nachforderung_rtw=nachforderung_rtw;}
    public void setErg_nachforderung_nef(Integer nachforderung_nef){this.erg_nachforderung_nef=nachforderung_nef;}
    public void setErg_nachforderung_naw(Integer nachforderung_naw){this.erg_nachforderung_naw=nachforderung_naw;}
    public void setErg_nachforderung_rth(Integer nachforderung_rth){this.erg_nachforderung_rth=nachforderung_rth;}
    public void setErg_nachforderung_feuerwehr(Integer nachforderung_feuerwehr){this.erg_nachforderung_feuerwehr=nachforderung_feuerwehr;}
    public void setErg_nachforderung_polizei(Integer nachforderung_polizei){this.erg_nachforderung_polizei=nachforderung_polizei;}
    public void setErg_funkruf(String funkruf){this.erg_funkruf=funkruf;}
    public void setErg_transport_art(String transport_art){this.erg_transport_art=transport_art;}
    public void setErg_transport_ziel(String transport_ziel){this.erg_transport_ziel=transport_ziel;}
    public void setErg_transport_sonstiges(String transport_sonstiges){this.erg_transport_sonstiges=transport_sonstiges;}
    public void setErg_entlassung_ev(Integer entlassung_ev){this.erg_entlassung_ev=entlassung_ev;}
    public void setErg_zeuge(String zeuge){this.erg_zeuge=zeuge;}
    public void setErg_ergebnis_zeit(String ergebnis_zeit){this.erg_ergebnis_zeit=ergebnis_zeit;}
   
    public String getErg_ersthelfermassn(){return erg_ersthelfermassn;}
    public String getErg_zustand(){return erg_zustand;}
    public String getErg_transport(){return erg_transport;}
    public String getErg_notarzt(){return erg_notarzt;}
    public Integer getErg_hausarzt_informiert(){return erg_hausarzt_informiert;}
    public Integer getErg_tod(){return erg_tod;}
    public String getErg_wertsachen(){return erg_wertsachen;}
    public Integer getErg_nachforderung_ktw(){return erg_nachforderung_ktw;}
    public Integer getErg_nachforderung_rtw(){return erg_nachforderung_rtw;}
    public Integer getErg_nachforderung_nef(){return erg_nachforderung_nef;}
    public Integer getErg_nachforderung_naw(){return erg_nachforderung_naw;}
    public Integer getErg_nachforderung_rth(){return erg_nachforderung_rth;}
    public Integer getErg_nachforderung_feuerwehr(){return erg_nachforderung_feuerwehr;}
    public Integer getErg_nachforderung_polizei(){return erg_nachforderung_polizei;}
    public String getErg_funkruf(){return erg_funkruf;}
    public String getErg_transport_art(){return erg_transport_art;}
    public String getErg_transport_ziel(){return erg_transport_ziel;}
    public String getErg_transport_sonstiges(){return erg_transport_sonstiges;}
    public Integer getErg_entlassung_ev(){return erg_entlassung_ev;}
    public String getErg_zeuge(){return erg_zeuge;}
    public String getErg_ergebnis_zeit(){return erg_ergebnis_zeit;}
  

    public Boolean Erg_spinner_notNull(){
        return ((getErg_ersthelfermassn()!=null)|(getErg_zustand()!=null)|(getErg_transport()!=null)|
                (getErg_notarzt()!=null)|(getErg_transport_art()!=null));
    }
    public Boolean Erg_check_notNull(){
        return ((getErg_hausarzt_informiert()!=null)|(getErg_tod()!=null)|(getErg_nachforderung_ktw()!=null)|
                (getErg_nachforderung_rtw()!=null)|(getErg_nachforderung_nef()!=null|(getErg_nachforderung_naw()!=null)|
                (getErg_nachforderung_rth()!=null)| (getErg_nachforderung_feuerwehr()!= null)|(getErg_nachforderung_polizei()!=null)|
                (getErg_entlassung_ev()!=null)));
    }

    public Boolean Erg_check(){
        return ((getErg_hausarzt_informiert()==1)|(getErg_tod()==1)|(getErg_nachforderung_ktw()==1)|
                (getErg_nachforderung_rtw()==1)|(getErg_nachforderung_nef()==1|(getErg_nachforderung_naw()==1)|
                (getErg_nachforderung_rth()==1)| (getErg_nachforderung_feuerwehr()==1)|(getErg_nachforderung_polizei()==1)|
                (getErg_entlassung_ev()==1)));
    }

    public Boolean Erg_edit_notNull(){
        return ((getErg_wertsachen()!=null)|(getErg_funkruf()!=null)|(getErg_transport_ziel()!=null)|
                (getErg_transport_sonstiges()!=null)|(getErg_zeuge()!=null)| (getErg_ergebnis_zeit()!=null));
    }

    public Boolean Erg_eingabe(){
        return ((getErg_wertsachen()!=null&&getErg_wertsachen().length()!=0)|(getErg_funkruf()!=null&&getErg_funkruf().length()!=0)
                |(getErg_transport_ziel()!=null&&getErg_transport_ziel().length()!=0)|
                (getErg_transport_sonstiges()!=null&&getErg_transport_sonstiges().length()!=0)
                |(getErg_zeuge()!=null&&getErg_zeuge().length()!=0)| (getErg_ergebnis_zeit()!=null&&getErg_ergebnis_zeit().length()!=0));
    }

    public void loescheErg(){
        setErg_ersthelfermassn(null);
        setErg_zustand(null);
        setErg_transport(null);
        setErg_notarzt(null);
        setErg_hausarzt_informiert(null);
        setErg_tod(null);
        setErg_wertsachen(null);
        setErg_nachforderung_ktw(null);
        setErg_nachforderung_rtw(null);
        setErg_nachforderung_nef(null);
        setErg_nachforderung_naw(null);
        setErg_nachforderung_rth(null);
        setErg_nachforderung_feuerwehr(null);
        setErg_nachforderung_polizei(null);
        setErg_funkruf(null);
        setErg_transport_art(null);
        setErg_transport_ziel(null);
        setErg_transport_sonstiges(null);
        setErg_entlassung_ev(null);
        setErg_zeuge(null);
        setErg_ergebnis_zeit(null);
    }

    //Maßnahmen - Dienste
    public void setMas_keine(Integer keine){this.mas_keine=keine;}
    public void setMas_stb_seitenlage(Integer stb_seitenlage){this.mas_stb_seitenlage=stb_seitenlage;}
    public void setMas_herzdruckmassage(Integer herzdruckmassage){this.mas_herzdruckmassage=herzdruckmassage;}
    public void setMas_schocklagerung(Integer schocklagerung){this.mas_schocklagerung=schocklagerung;}
    public void setMas_oberkoerperhochlage(Integer oberkoerperhochlage){this.mas_oberkoerperhochlage=oberkoerperhochlage;}
    public void setMas_flachlagerung(Integer flachlagerung){this.mas_flachlagerung=flachlagerung;}
    public void setMas_wundversorgung(Integer wundversorgung){this.mas_wundversorgung=wundversorgung;}
    public void setMas_sauerstoffgabe(Integer sauerstoffgabe){this.mas_sauerstoffgabe=sauerstoffgabe;}
    public void setMas_vakuummatratze(Integer vakuummatratze){this.mas_vakuummatratze=vakuummatratze;}
    public void setMas_hws_stuetzkragen(Integer hws_stuetzkragen){this.mas_hws_stuetzkragen=hws_stuetzkragen;}
    public void setMas_extr_schienung(Integer extr_schienung){this.mas_extr_schienung=extr_schienung;}
    public void setMas_ekg(Integer ekg){this.mas_ekg=ekg;}
    public void setMas_atemwege_freim(Integer atemwege_freim){this.mas_atemwege_freim=atemwege_freim;}
    public void setMas_notkompetenz(Integer notkompetenz){this.mas_notkompetenz=notkompetenz;}
    public void setMas_erstdefibrillation(Integer erstdefibrillation){this.mas_erstdefibrillation=erstdefibrillation;}
    public void setMas_ven_zugang(Integer ven_zugang){this.mas_ven_zugang=ven_zugang;}
    public void setMas_medikamente(Integer medikamente){this.mas_medikamente=medikamente;}
    public void setMas_beatmung(Integer beatmung){this.mas_beatmung=beatmung;}
    public void setMas_betreuung(Integer betreuung){this.mas_betreuung=betreuung;}
    public void setMas_infusion(Integer infusion){this.mas_infusion=infusion;}
    public void setMas_intubation(Integer intubation){this.mas_intubation=intubation;}
    public void setMas_sonstiges(Integer sonstiges){this.mas_sonstiges=sonstiges;}
    public void setMas_sonstiges_text(String sonstiges_text){this.mas_sonstiges_text=sonstiges_text;}
    public Integer getMas_keine(){return mas_keine;}
    public Integer getMas_stb_seitenlage(){return mas_stb_seitenlage;}
    public Integer getMas_herzdruckmassage(){return mas_herzdruckmassage;}
    public Integer getMas_schocklagerung(){return mas_schocklagerung;}
    public Integer getMas_oberkoerperhochlage(){return mas_oberkoerperhochlage;}
    public Integer getMas_flachlagerung(){return mas_flachlagerung;}
    public Integer getMas_wundversorgung(){return mas_wundversorgung;}
    public Integer getMas_sauerstoffgabe(){return mas_sauerstoffgabe;}
    public Integer getMas_vakuummatratze(){return mas_vakuummatratze;}
    public Integer getMas_hws_stuetzkragen(){return mas_hws_stuetzkragen;}
    public Integer getMas_extr_schienung(){return mas_extr_schienung;}
    public Integer getMas_ekg(){return mas_ekg;}
    public Integer getMas_atemwege_freim(){return mas_atemwege_freim;}
    public Integer getMas_notkompetenz(){return mas_notkompetenz;}
    public Integer getMas_erstdefibrillation(){return mas_erstdefibrillation;}
    public Integer getMas_ven_zugang(){return mas_ven_zugang;}
    public Integer getMas_medikamente(){return mas_medikamente;}
    public Integer getMas_beatmung(){return mas_beatmung;}
    public Integer getMas_betreuung(){return mas_betreuung;}
    public Integer getMas_infusion(){return mas_infusion;}
    public Integer getMas_intubation(){return mas_intubation;}
    public Integer getMas_sonstiges(){return mas_sonstiges;}
    public String getMas_sonstiges_text(){return mas_sonstiges_text;}
    public Boolean Mas_notNull(){
        return (((getMas_atemwege_freim() !=null)|(getMas_beatmung()!=null)|(getMas_betreuung()!=null)|(getMas_ekg()!=null)|
                (getMas_erstdefibrillation()!=null)|(getMas_extr_schienung()!=null)|(getMas_flachlagerung()!=null)|(getMas_herzdruckmassage()!=null)|
                (getMas_hws_stuetzkragen()!= null)|(getMas_infusion() !=null)|(getMas_intubation()!=null)|
                (getMas_keine() !=null)|(getMas_medikamente()!=null)|(getMas_notkompetenz() !=null)|(getMas_oberkoerperhochlage()!=null)|
                (getMas_sauerstoffgabe()!=null)|(getMas_schocklagerung()!=null)|(getMas_sonstiges()!=null)|(getMas_sonstiges_text()!=null)
                |(getMas_stb_seitenlage()!=null)|(getMas_vakuummatratze()!=null)|(getMas_ven_zugang()!=null)|(getMas_wundversorgung()!=null)));
    }
    public Boolean Mas_eingabe(){
        return ((((getMas_atemwege_freim() !=null)&&getMas_atemwege_freim() ==1)|(getMas_beatmung()!=null&&getMas_beatmung()==1)|(getMas_betreuung()!=null&&getMas_betreuung()==1)
                |(getMas_ekg()!=null&&getMas_ekg()==1)| (getMas_erstdefibrillation()!=null&&getMas_erstdefibrillation()==1)|
                (getMas_extr_schienung()!=null&&getMas_extr_schienung()==1)|(getMas_flachlagerung()!=null&&getMas_flachlagerung()==1)
                |(getMas_herzdruckmassage()!=null&&getMas_herzdruckmassage()==1)|
                (getMas_hws_stuetzkragen()!= null&&getMas_hws_stuetzkragen()==1)|(getMas_infusion() !=null&&getMas_infusion()==1)
                |(getMas_intubation()!=null&&getMas_intubation()==1)|
                (getMas_keine() !=null&&getMas_keine()==1)|(getMas_medikamente()!=null&&getMas_medikamente()==1)
                |(getMas_notkompetenz() !=null&&getMas_notkompetenz()==1)|(getMas_oberkoerperhochlage()!=null&&getMas_oberkoerperhochlage()==1)|
                (getMas_sauerstoffgabe()!=null&&getMas_sauerstoffgabe()==1)|(getMas_schocklagerung()!=null&&getMas_schocklagerung()==1)
                |(getMas_sonstiges()!=null&&getMas_sonstiges()==1)|(getMas_sonstiges_text()!=null&&getMas_sonstiges_text().length()>0)
                |(getMas_stb_seitenlage()!=null&&getMas_stb_seitenlage()==1)|(getMas_vakuummatratze()!=null&&getMas_vakuummatratze()==1)
                |(getMas_ven_zugang()!=null&&getMas_ven_zugang()==1)|(getMas_wundversorgung()!=null&&getMas_wundversorgung()==1)));
    }
    public void loescheMas(){
        setMas_keine(null);
        setMas_stb_seitenlage(null);
        setMas_herzdruckmassage(null);
        setMas_schocklagerung(null);
        setMas_oberkoerperhochlage(null);
        setMas_flachlagerung(null);
        setMas_wundversorgung(null);
        setMas_sauerstoffgabe(null);
        setMas_vakuummatratze(null);
        setMas_hws_stuetzkragen(null);
        setMas_extr_schienung(null);
        setMas_ekg(null);
        setMas_atemwege_freim(null);
        setMas_notkompetenz(null);
        setMas_erstdefibrillation(null);
        setMas_ven_zugang(null);
        setMas_medikamente(null);
        setMas_beatmung(null);
        setMas_betreuung(null);
        setMas_infusion(null);
        setMas_intubation(null);
        setMas_sonstiges(null);
        setMas_sonstiges_text(null);
    }

    //Verletzung - Dienste
    public void setVerl_prellung_verletzung(Integer prellung_verletzung){this.verl_prellung_verletzung=prellung_verletzung;}
    public void setVerl_verbrennung(Integer verbrennung){this.verl_verbrennung=verbrennung;}
    public void setVerl_wunde_verletzung(Integer wunde_verletzung){this.verl_wunde_verletzung=wunde_verletzung;}
    public void setVerl_elektrounfall(Integer elektrounfall){this.verl_elektrounfall=elektrounfall;}
    public void setVerl_inhalationstrauma(Integer inhalationstrauma){this.verl_inhalationstrauma=inhalationstrauma;}
    public void setVerl_sonstiges(Integer sonstiges){this.verl_sonstiges=sonstiges;}
    public void setVerl_schaedel_art(String verl_schaedel_art){this.verl_schaedel_art=verl_schaedel_art;}
    public void setVerl_schaedel_grad(String verl_schaedel_grad){this.verl_schaedel_grad=verl_schaedel_grad;}
    public void setVerl_gesicht_art(String verl_gesicht_art){this.verl_gesicht_art=verl_gesicht_art;}
    public void setVerl_gesicht_grad(String verl_gesicht_grad){this.verl_gesicht_grad=verl_gesicht_grad;}
    public void setVerl_hws_art(String verl_hws_art){this.verl_hws_art=verl_hws_art;}
    public void setVerl_hws_grad(String verl_hws_grad){this.verl_hws_grad=verl_hws_grad;}
    public void setVerl_brustkorb_art(String verl_brustkorb_art){this.verl_brustkorb_art=verl_brustkorb_art;}
    public void setVerl_brustkorb_grad(String verl_brustkorb_grad){this.verl_brustkorb_grad=verl_brustkorb_grad;}
    public void setVerl_bauch_art(String verl_bauch_art){this.verl_bauch_art=verl_bauch_art;}
    public void setVerl_bauch_grad(String verl_bauch_grad){this.verl_bauch_grad=verl_bauch_grad;}
    public void setVerl_bws_lws_art(String verl_bws_lws_art){this.verl_bws_lws_art=verl_bws_lws_art;}
    public void setVerl_bws_lws_grad(String verl_bws_lws_grad){this.verl_bws_lws_grad=verl_bws_lws_grad;}
    public void setVerl_becken_art(String verl_becken_art){this.verl_becken_art=verl_becken_art;}
    public void setVerl_becken_grad(String verl_becken_grad){this.verl_becken_grad=verl_becken_grad;}
    public void setVerl_arme_art(String verl_arme_art){this.verl_arme_art=verl_arme_art;}
    public void setVerl_arme_grad(String verl_arme_grad){this.verl_arme_grad=verl_arme_grad;}
    public void setVerl_beine_art(String verl_beine_art){this.verl_beine_art=verl_beine_art;}
    public void setVerl_beine_grad(String verl_beine_grad){this.verl_beine_grad=verl_beine_grad;}
    public void setVerl_weichteile_art(String verl_weichteile_art){this.verl_weichteile_art=verl_weichteile_art;}
    public void setVerl_weichteile_grad(String verl_weichteile_grad){this.verl_weichteile_grad=verl_weichteile_grad;}
    public Integer getVerl_prellung_verletzung(){return verl_prellung_verletzung;}
    public Integer getVerl_verbrennung(){return verl_verbrennung;}
    public Integer getVerl_wunde_verletzung(){return verl_wunde_verletzung;}
    public Integer getVerl_elektrounfall(){return verl_elektrounfall;}
    public Integer getVerl_inhalationstrauma(){return verl_inhalationstrauma;}
    public Integer getVerl_sonstiges(){return verl_sonstiges;}
    public String getVerl_schaedel_art(){return verl_schaedel_art;}
    public String getVerl_schaedel_grad(){return verl_schaedel_grad;}
    public String getVerl_gesicht_art(){return verl_gesicht_art;}
    public String getVerl_gesicht_grad(){return verl_gesicht_grad;}
    public String getVerl_hws_art(){return verl_hws_art;}
    public String getVerl_hws_grad(){return verl_hws_grad;}
    public String getVerl_brustkorb_art(){return verl_brustkorb_art;}
    public String getVerl_brustkorb_grad(){return verl_brustkorb_grad;}
    public String getVerl_bauch_art(){return verl_bauch_art;}
    public String getVerl_bauch_grad(){return verl_bauch_grad;}
    public String getVerl_bws_lws_art(){return verl_bws_lws_art;}
    public String getVerl_bws_lws_grad(){return verl_bws_lws_grad;}
    public String getVerl_becken_art(){return verl_becken_art;}
    public String getVerl_becken_grad(){return verl_becken_grad;}
    public String getVerl_arme_art(){return verl_arme_art;}
    public String getVerl_arme_grad(){return verl_arme_grad;}
    public String getVerl_beine_art(){return verl_beine_art;}
    public String getVerl_beine_grad(){return verl_beine_grad;}
    public String getVerl_weichteile_art(){return verl_weichteile_art;}
    public String getVerl_weichteile_grad(){return verl_weichteile_grad;}
    public Boolean Verl_spinner_notNull(){
        return ((getVerl_arme_art()!=null)|(getVerl_arme_grad()!=null)|(getVerl_bauch_art()!=null)|
                (getVerl_bauch_grad()!=null)|(getVerl_becken_art()!=null)|(getVerl_becken_grad()!=null)|
                (getVerl_brustkorb_art()!=null)|(getVerl_brustkorb_grad()!=null)|(getVerl_bws_lws_art()!=null)|
                (getVerl_bws_lws_grad()!=null)|(getVerl_gesicht_art()!=null)|(getVerl_gesicht_grad()!=null)|
                (getVerl_schaedel_art()!=null)|(getVerl_schaedel_grad()!=null)|(getVerl_hws_art()!=null)|
                (getVerl_hws_grad()!=null)|(getVerl_weichteile_art()!=null)|(getVerl_weichteile_grad()!=null));
    }
    public Boolean Verl_check_notNull(){
        return ((getVerl_prellung_verletzung()!=null)|(getVerl_verbrennung()!=null)|(getVerl_wunde_verletzung()!=null)|
                (getVerl_elektrounfall()!=null)|(getVerl_inhalationstrauma()!=null|(getVerl_sonstiges()!=null)));

    }

    public Boolean Verl_check(){
        return ((getVerl_prellung_verletzung()==1)|(getVerl_verbrennung()==1)|(getVerl_wunde_verletzung()==1)|
                (getVerl_elektrounfall()==1)|(getVerl_inhalationstrauma()==1|(getVerl_sonstiges()==1)));
    }

    public void loescheVerl(){
        setVerl_prellung_verletzung(null);
        setVerl_verbrennung(null);
        setVerl_wunde_verletzung(null);
        setVerl_elektrounfall(null);
        setVerl_inhalationstrauma(null);
        setVerl_sonstiges(null);
        setVerl_schaedel_art(null);
        setVerl_schaedel_grad(null);
        setVerl_gesicht_art(null);
        setVerl_gesicht_grad(null);
        setVerl_hws_art(null);
        setVerl_hws_grad(null);
        setVerl_brustkorb_art(null);
        setVerl_brustkorb_grad(null);
        setVerl_bauch_art(null);
        setVerl_bauch_grad(null);
        setVerl_bws_lws_art(null);
        setVerl_bws_lws_grad(null);
        setVerl_becken_art(null);
        setVerl_becken_grad(null);
        setVerl_arme_art(null);
        setVerl_arme_grad(null);
        setVerl_beine_art(null);
        setVerl_beine_grad(null);
        setVerl_weichteile_art(null);
        setVerl_weichteile_grad(null);
    }

    //Erstbefund - Dienste
    public void setErst_bewusstsein(String erst_bewusstsein){this.erst_bewusstsein=erst_bewusstsein;}
    public void setErst_kreislauf(String erst_kreislauf){this.erst_kreislauf=erst_kreislauf;}
    public void setErst_pupille_li(String erst_pupille_li){this.erst_pupille_li=erst_pupille_li;}
    public void setErst_pupille_re(String erst_pupille_re){this.erst_pupille_re=erst_pupille_re;}
    public void setErst_ekg(String erst_ekg){this.erst_ekg=erst_ekg;}
    public void setErst_schmerzen(String erst_schmerzen){this.erst_schmerzen=erst_schmerzen;}
    public void setErst_atmung(String erst_atmung){this.erst_atmung=erst_atmung;}
    public void setErst_rr_sys(String erst_rr_sys){this.erst_rr_sys=erst_rr_sys;}
    public void setErst_rr_dia(String erst_rr_dia){this.erst_rr_dia=erst_rr_dia;}
    public void setErst_puls(String erst_puls){this.erst_puls=erst_puls;}
    public void setErst_af(String erst_af){this.erst_af=erst_af;}
    public void setErst_spo(String erst_spo){this.erst_spo=erst_spo;}
    public void setErst_bz(String erst_bz){this.erst_bz=erst_bz;}
    public String getErst_bewusstsein(){return erst_bewusstsein;}
    public String getErst_kreislauf(){return erst_kreislauf;}
    public String getErst_pupille_li(){return erst_pupille_li;}
    public String getErst_pupille_re(){return erst_pupille_re;}
    public String getErst_ekg(){return erst_ekg;}
    public String getErst_schmerzen(){return erst_schmerzen;}
    public String getErst_atmung(){return erst_atmung;}
    public String getErst_rr_sys(){return erst_rr_sys;}
    public String getErst_rr_dia(){return erst_rr_dia;}
    public String getErst_puls(){return erst_puls;}
    public String getErst_af(){return erst_af;}
    public String getErst_spo(){return erst_spo;}
    public String getErst_bz(){return erst_bz;}

    public Boolean Erst_spinner_notNull(){
        return ((getErst_bewusstsein()!=null)|(getErst_kreislauf()!=null)|(getErst_pupille_li()!=null)|
                (getErst_pupille_re()!=null)|(getErst_ekg()!=null)|(getErst_schmerzen()!=null)|
                (getErst_atmung()!=null));
    }
    public Boolean Erst_edit_notNull(){
        return ((getErst_rr_sys()!=null)|(getErst_rr_dia()!=null)|(getErst_puls()!=null)|
                (getErst_af()!=null)|(getErst_spo()!=null)| (getErst_bz()!=null));
    }
    public Boolean Erst_eingabe(){
        return ((getErst_rr_sys()!=null&&getErst_rr_sys().length()!=0)|(getErst_rr_dia()!=null&&getErst_rr_dia().length()!=0)|
                (getErst_puls()!=null&&getErst_puls().length()!=0)|(getErst_af()!=null&&getErst_af().length()!=0)
                |(getErst_spo()!=null&&getErst_spo().length()!=0)| (getErst_bz()!=null&&getErst_bz().length()!=0));
    }

    public void loescheErst(){
        setErst_bewusstsein(null);
        setErst_kreislauf(null);
        setErst_pupille_li(null);
        setErst_pupille_re(null);
        setErst_ekg(null);
        setErst_schmerzen(null);
        setErst_atmung(null);
        setErst_rr_sys(null);
        setErst_rr_dia(null);
        setErst_puls(null);
        setErst_af(null);
        setErst_spo(null);
        setErst_bz(null);
    }

    //Notfallsituation - Dienste
    public void setNotf_notfallsituation(String notfallsituation){this.notf_notfallsituation=notfallsituation;}
    public String getNotf_notfallsituation(){return notf_notfallsituation;}
    public void loescheNotf(){
        setNotf_notfallsituation(null);
    }

    //Bemerkung - Dienste
    public void setBem_bemerkung(String bemerkung){this.bem_bemerkung=bemerkung;}
    public String getBem_bemerkung(){return bem_bemerkung;}
    public void loescheBem(){
        setBem_bemerkung(null);
    }

    //Erkrankung - Dienste
    public void setErk_keine(Integer keine){this.erk_keine=keine;}
    public void setErk_alkoholisiert(Integer alkoholisiert){this.erk_alkoholisiert=alkoholisiert;}
    public void setErk_erbrechen(Integer erbrechen){this.erk_erbrechen=erbrechen;}
    public void setErk_schwindel(Integer schwindel){this.erk_schwindel=schwindel;}
    public void setErk_herzkreislauf(Integer herzkreislauf){this.erk_herzkreislauf=herzkreislauf;}
    public void setErk_hitzeschlag(Integer hitzeschlag){this.erk_hitzeschlag=hitzeschlag;}
    public void setErk_hitzeerschoepfung(Integer hitzeerschoepfung){this.erk_hitzeerschoepfung=hitzeerschoepfung;}
    public void setErk_vergiftung(Integer vergiftung){this.erk_vergiftung=vergiftung;}
    public void setErk_atmung(Integer atmung){this.erk_atmung=atmung;}
    public void setErk_unterkuehlung(Integer unterkuehlung){this.erk_unterkuehlung=unterkuehlung;}
    public void setErk_baucherkrankung(Integer baucherkrankung){this.erk_baucherkrankung=baucherkrankung;}
    public void setErk_stoffwechsel(Integer stoffwechsel){this.erk_stoffwechsel=stoffwechsel;}
    public void setErk_neurologie(Integer neurologie){this.erk_neurologie=neurologie;}
    public void setErk_psychatrie(Integer psychatrie){this.erk_psychatrie=psychatrie;}
    public void setErk_gynaekologie(Integer gynaekologie){this.erk_gynaekologie=gynaekologie;}
    public void setErk_kindernotfall(Integer kindernotfall){this.erk_kindernotfall=kindernotfall;}
    public void setErk_geburtshilfe(Integer geburtshilfe){this.erk_geburtshilfe=geburtshilfe;}
    public void setErk_sonstiges(Integer sonstiges){this.erk_sonstiges=sonstiges;}
    public void setErk_edtxt_sonstiges(String edtxtSonstiges){this.erk_edtxt_sonstiges=edtxtSonstiges;}
    public Integer getErk_keine(){return erk_keine;}
    public Integer getErk_alkoholisiert(){return erk_alkoholisiert;}
    public Integer getErk_erbrechen(){return erk_erbrechen;}
    public Integer getErk_schwindel(){return erk_schwindel;}
    public Integer getErk_herzkreislauf(){return erk_herzkreislauf;}
    public Integer getErk_hitzeschlag(){return erk_hitzeschlag;}
    public Integer getErk_hitzeerschoepfung(){return erk_hitzeerschoepfung;}
    public Integer getErk_vergiftung(){return erk_vergiftung;}
    public Integer getErk_atmung(){return erk_atmung;}
    public Integer getErk_unterkuehlung(){return erk_unterkuehlung;}
    public Integer getErk_baucherkrankung(){return erk_baucherkrankung;}
    public Integer getErk_stoffwechsel(){return erk_stoffwechsel;}
    public Integer getErk_neurologie(){return erk_neurologie;}
    public Integer getErk_psychatrie(){return erk_psychatrie;}
    public Integer getErk_gynaekologie(){return erk_gynaekologie;}
    public Integer getErk_kindernotfall(){return erk_kindernotfall;}
    public Integer getErk_geburtshilfe(){return erk_geburtshilfe;}
    public Integer getErk_sonstiges(){return erk_sonstiges;}
    public String getErk_edtxt_sonstiges(){return erk_edtxt_sonstiges;}
    public Boolean Erk_notNull(){
        return ((getErk_keine() !=null)|(getErk_alkoholisiert()!=null)|(getErk_erbrechen()!=null)|(getErk_schwindel()!=null)|
                (getErk_herzkreislauf()!=null)|(getErk_hitzeschlag()!=null)|(getErk_vergiftung()!=null)|(getErk_atmung()!=null)|
                (getErk_unterkuehlung()!= null)|(getErk_baucherkrankung()!=null)|(getErk_stoffwechsel()!=null)|
                (getErk_neurologie() !=null)|(getErk_gynaekologie()!=null)|(getErk_psychatrie()!=null)|(getErk_kindernotfall()!=null)|
                (getErk_geburtshilfe()!=null)|(getErk_sonstiges()!=null)|(getErk_edtxt_sonstiges()!=null));
    }

  public Boolean Erk_eingabe(){
      return ((getErk_keine()!=null&&getErk_keine() ==1)|(getErk_alkoholisiert()!=null&&getErk_alkoholisiert()==1)|
              (getErk_erbrechen()!=null&&getErk_erbrechen()==1)|(getErk_schwindel()!=null&&getErk_schwindel()==1)|
              (getErk_herzkreislauf()!=null&&getErk_herzkreislauf()==1)|(getErk_hitzeschlag()!=null&&getErk_hitzeschlag()==1)
              |(getErk_vergiftung()!=null&&getErk_vergiftung()==1)|(getErk_atmung()!=null&&getErk_atmung()==1)|
              (getErk_unterkuehlung()!=null&&getErk_unterkuehlung()==1)|(getErk_baucherkrankung()!=null&&getErk_baucherkrankung() ==1)|
              (getErk_stoffwechsel()!=null&&getErk_stoffwechsel()==1)| (getErk_neurologie()!=null&&getErk_neurologie()==1)
              |(getErk_gynaekologie()!=null&&getErk_gynaekologie()==1)|(getErk_psychatrie()!=null&&getErk_psychatrie()==1)|
              (getErk_kindernotfall()!=null&&getErk_kindernotfall()==1)| (getErk_geburtshilfe()!=null&&getErk_geburtshilfe()==1)|
              (getErk_sonstiges()!=null&&getErk_sonstiges()==1)|(getErk_edtxt_sonstiges()!=null&&getErk_edtxt_sonstiges().length()>0));
  }

    public void loescheErk(){
        setErk_keine(null);
        setErk_alkoholisiert(null);
        setErk_erbrechen(null);
        setErk_schwindel(null);
        setErk_herzkreislauf(null);
        setErk_hitzeschlag(null);
        setErk_hitzeerschoepfung(null);
        setErk_vergiftung(null);
        setErk_atmung(null);
        setErk_unterkuehlung(null);
        setErk_baucherkrankung(null);
        setErk_stoffwechsel(null);
        setErk_neurologie(null);
        setErk_psychatrie(null);
        setErk_gynaekologie(null);
        setErk_kindernotfall(null);
        setErk_geburtshilfe(null);
        setErk_sonstiges(null);
        setErk_edtxt_sonstiges(null);
    }

    //Patient - Dienste
    public void setPat_name(String n){
        this.pat_name=n;
    }
    public String getPat_name(){
        return pat_name;
    }
    public void setPat_vorname(String vn){
        this.pat_vname=vn;
    }
    public String getPat_vorname(){
        return pat_vname;
    }
    public void setPat_sex(String s){
        this.pat_sex=s;
    }
    public String getPat_sex(){
        return pat_sex;
    }
    public void setPat_geb(String g){
        this.pat_geb=g;
    }
    public String getPat_geb(){
        return pat_geb;
    }
    public void setPat_str(String s){
        this.pat_str=s;
    }
    public String getPat_str(){
        return pat_str;
    }
    public void setPat_plz(String p){
        this.pat_plz=p;
    }
    public String getPat_plz(){
        return pat_plz;
    }
    public void setPat_ort(String o){
        this.pat_ort=o;
    }
    public String getPat_ort(){
        return pat_ort;
    }
    public void setPat_land(String l){
        this.pat_land=l;
    }
    public String getPat_land(){
        return pat_land;
    }
    public void setPat_tel(String t){
        this.pat_tel=t;
    }
    public String getPat_tel(){
        return pat_tel;
    }
    public void setPat_krankenkasse(String k){
        this.pat_krankenkasse=k;
    }
    public String getPat_krankenkasse(){
        return pat_krankenkasse;
    }
    public void setPat_versnr(String v){
        this.pat_versnr=v;
    }
    public String getPat_versnr(){
        return pat_versnr;
    }
    public void setPat_versichertennr(String v){
        this.pat_versichertennr=v;
    }
    public String getPat_versichertennr(){
        return pat_versichertennr;
    }
    public void setPat_sani(Integer s){
        this.pat_sani=s;
    }
    public Integer getPat_sani(){
        return pat_sani;
    }
    public void loeschePat(){
        setPat_name(null);
        setPat_vorname(null);
        setPat_geb(null);
        setPat_str(null);
        setPat_plz(null);
        setPat_ort(null);
        setPat_land(null);
        setPat_tel(null);
        setPat_krankenkasse(null);
        setPat_versnr(null);
        setPat_versichertennr(null);
        setPat_sex(null);
        setPat_sani(null);
        setPat_IDm(0);
    }

    //Einsatz - Dienste
    public void setEin_zugef(String z){
        this.ein_zugef=z;
    }
    public String getEin_zugef(){
        return ein_zugef;
    }
    public void setEin_fundort(String f){
        this.ein_fundort=f;
    }
    public String getEin_fundort(){
        return ein_fundort;
    }
    public void setEin_mosan(Integer m){
        this.ein_mosan=m;
    }
    public Integer getEin_mosan(){
        return ein_mosan;
    }
    public void setEin_hilfs(Integer h){
        this.ein_hilfs=h;
    }
    public Integer getEin_hilfs(){
        return ein_hilfs;
    }
    public void setEin_sanw(Integer s){
        this.ein_sanw=s;
    }
    public Integer getEin_sanw(){
        return ein_sanw;
    }
    public void loescheEin(){
        setEin_zugef(null);
        setEin_fundort(null);
        setEin_ID(0);
    }

    //Stammdaten - Dienste
    public void setFall_angelegt(){
        if(ver_vorh&san_vorh&verb_vorh) {
            this.fall_angelegt = true;
        }
    }
    public Boolean getFall_angelegt(){
        return fall_angelegt;
    }
    public void setUebersprungen(Boolean u){
        this.uebersprungen=u;
    }
    public Boolean getUebersprungen(){
        return uebersprungen;
    }

    //Sanitäter - Dienste
    public void setSan_name(String n){
        this.san_name=n;
    }
    public String getSan_name(){
        return san_name;
    }
    public void setSan_vorname(String v){
        this.san_vname=v;
    }
    public String getSan_vorname(){
        return san_vname;
    }
    public void setSan_vorh(){
        if((getSan_name()!=null&&getSan_name().length()>0)&(getSan_vorname()!=null&&getSan_vorname().length()>0)){
            this.san_vorh=true;
        }
    }
    public void setSan_vorh_m(Boolean v){
        this.san_vorh=false;
    }
    public Boolean getSan_vorh(){
        return san_vorh;
    }

    public void loescheSan(){
        setSan_name(null);
        setSan_vorname(null);
        setSani_IDm(0);
     //   setSan_vorh();
    }
    //Verband - Dienste
    public void setVerb_kreisv(String k){
        this.verb_kreisv=k;
    }
    public String getVerb_kreisv(){
        return verb_kreisv;
    }
    public void setVerb_ortsv(String o){
        this.verb_ortsv=o;
    }
    public String getVerb_ortsv(){
        return verb_ortsv;
    }
    public void setVerb_vorh(){
        if((getVerb_kreisv()!=null&&getVerb_kreisv().length()>0)&(getVerb_ortsv()!=null&& getVerb_ortsv().length()>0)){
            this.verb_vorh=true;
        }
    }
    public void setVerb_vorh_m(Boolean v){
        this.verb_vorh=false;
    }
    public Boolean getVerb_vorh(){
        return verb_vorh;
    }
    public void loescheVerb(){
        setVerb_ortsv(null);
        setVerb_kreisv(null);
        setVerb_ID(0);
       // setVerb_vorh();
    }

    //Veranstaltung - Dienste
    public void setVer_name(String n){
        this.ver_name=n;
    }
    public String getVer_name(){
        return ver_name;
    }
    public void setVer_ort(String o){
        this.ver_ort=o;
    }
    public String getVer_ort(){
        return ver_ort;
    }
    public void setVer_date(String d){
        this.ver_date=d;
    }
    public void loescheVer(){
        setVer_name(null);
        setVer_ort(null);
        setVer_date(null);
       // setVer_vorh();
    }
    public String getVer_date(){
        return ver_date;
    }
    public void setVer_vorh(){
        if((getVer_name()!=null&&getVer_name().length()>0)&(getVer_date()!=null&&getVer_date().length()>0)){
            this.ver_vorh=true;
        }
    }
    public void setVer_vorh_m(Boolean v){
        this.ver_vorh=false;
    }
    public Boolean getVer_vorh(){
        return ver_vorh;
    }

    public void setFallAusgewahelt(Boolean aus){
        this.fallAusgewaehlt=aus;
    }
    public Boolean getFallAusgewaehlt(){
        return fallAusgewaehlt;
    }
}
