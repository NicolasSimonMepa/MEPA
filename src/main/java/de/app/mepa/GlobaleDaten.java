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
    private String san_name;
    private String san_vname;
    private Boolean san_vorh=false;

    //Verband - Attribute
    private String verb_kreisv;
    private String verb_ortsv;
    private Boolean verb_vorh=false;

    //Veranstaltung - Attribute
    private String ver_name;
    private String ver_ort;
    private String ver_date;
    private Boolean ver_vorh=false;
    
    //Fall ID - Dienste, Simon 18.05.16
        public void setFallID(boolean a) {
        if (a == true){
            this.fallID = UUID.randomUUID().hashCode();
            if (fallID < 0 ){fallID = -fallID;}
        }
        else {fallID = 0;}
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
    public void loescheErg(){
        setErg_ersthelfermassn("");
        setErg_zustand("");
        setErg_transport("");
        setErg_notarzt("");
        setErg_hausarzt_informiert(0);
        setErg_tod(0);
        setErg_wertsachen("");
        setErg_nachforderung_ktw(0);
        setErg_nachforderung_rtw(0);
        setErg_nachforderung_nef(0);
        setErg_nachforderung_naw(0);
        setErg_nachforderung_rth(0);
        setErg_nachforderung_feuerwehr(0);
        setErg_nachforderung_polizei(0);
        setErg_funkruf("");
        setErg_transport_art("");
        setErg_transport_ziel("");
        setErg_transport_sonstiges("");
        setErg_entlassung_ev(0);
        setErg_zeuge("");
        setErg_ergebnis_zeit("");
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
    public void loescheMas(){
        setMas_keine(0);
        setMas_stb_seitenlage(0);
        setMas_herzdruckmassage(0);
        setMas_schocklagerung(0);
        setMas_oberkoerperhochlage(0);
        setMas_flachlagerung(0);
        setMas_wundversorgung(0);
        setMas_sauerstoffgabe(0);
        setMas_vakuummatratze(0);
        setMas_hws_stuetzkragen(0);
        setMas_extr_schienung(0);
        setMas_ekg(0);
        setMas_atemwege_freim(0);
        setMas_notkompetenz(0);
        setMas_erstdefibrillation(0);
        setMas_ven_zugang(0);
        setMas_medikamente(0);
        setMas_beatmung(0);
        setMas_betreuung(0);
        setMas_infusion(0);
        setMas_intubation(0);
        setMas_sonstiges(0);
        setMas_sonstiges_text("");
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
    public void loescheVerl(){
        setVerl_prellung_verletzung(0);
        setVerl_verbrennung(0);
        setVerl_wunde_verletzung(0);
        setVerl_elektrounfall(0);
        setVerl_inhalationstrauma(0);
        setVerl_sonstiges(0);
        setVerl_schaedel_art("");
        setVerl_schaedel_grad("");
        setVerl_gesicht_art("");
        setVerl_gesicht_grad("");
        setVerl_hws_art("");
        setVerl_hws_grad("");
        setVerl_brustkorb_art("");
        setVerl_brustkorb_grad("");
        setVerl_bauch_art("");
        setVerl_bauch_grad("");
        setVerl_bws_lws_art("");
        setVerl_bws_lws_grad("");
        setVerl_becken_art("");
        setVerl_becken_grad("");
        setVerl_arme_art("");
        setVerl_arme_grad("");
        setVerl_beine_art("");
        setVerl_beine_grad("");
        setVerl_weichteile_art("");
        setVerl_weichteile_grad("");
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
    public void loescheErst(){
        setErst_bewusstsein("");
        setErst_kreislauf("");
        setErst_pupille_li("");
        setErst_pupille_re("");
        setErst_ekg("");
        setErst_schmerzen("");
        setErst_atmung("");
        setErst_rr_sys("");
        setErst_rr_dia("");
        setErst_puls("");
        setErst_af("");
        setErst_spo("");
        setErst_bz("");
    }

    //Notfallsituation - Dienste
    public void setNotf_notfallsituation(String notfallsituation){this.notf_notfallsituation=notfallsituation;}
    public String getNotf_notfallsituation(){return notf_notfallsituation;}
    public void loescheNotf(){
        setNotf_notfallsituation("");
    }

    //Bemerkung - Dienste
    public void setBem_bemerkung(String bemerkung){this.bem_bemerkung=bemerkung;}
    public String getBem_bemerkung(){return bem_bemerkung;}
    public void loescheBem(){
        setBem_bemerkung("");
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
    public void loescheErk(){
        setErk_keine(0);
        setErk_alkoholisiert(0);
        setErk_erbrechen(0);
        setErk_schwindel(0);
        setErk_herzkreislauf(0);
        setErk_hitzeschlag(0);
        setErk_hitzeerschoepfung(0);
        setErk_vergiftung(0);
        setErk_atmung(0);
        setErk_unterkuehlung(0);
        setErk_baucherkrankung(0);
        setErk_stoffwechsel(0);
        setErk_neurologie(0);
        setErk_psychatrie(0);
        setErk_gynaekologie(0);
        setErk_kindernotfall(0);
        setErk_geburtshilfe(0);
        setErk_sonstiges(0);
        setErk_edtxt_sonstiges("");
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
        setPat_name("");
        setPat_vorname("");
        setPat_geb("");
        setPat_str("");
        setPat_plz("");
        setPat_ort("");
        setPat_land("");
        setPat_tel("");
        setPat_krankenkasse("");
        setPat_versnr("");
        setPat_versichertennr("");
        setPat_sex("");
        setPat_sani(null);
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
        setEin_zugef("");
        setEin_fundort("");
        setEin_hilfs(0);
        setEin_sanw(0);
        setEin_mosan(0);
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
        if((getSan_name().length()>0)&(getSan_vorname().length()>0)){
            san_vorh=true;
        }
    }
    public Boolean getSan_vorh(){
        return san_vorh;
    }

    public void loescheSan(){
        setSan_name("");
        setSan_vorname("");
        setSan_vorh();
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
        if((getVerb_kreisv().length()>0)&(getVerb_ortsv().length()>0)){
            verb_vorh=true;
        }
    }
    public Boolean getVerb_vorh(){
        return verb_vorh;
    }
    public void loescheVerb(){
        setVerb_ortsv("");
        setVerb_kreisv("");
        setVerb_vorh();
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
        setVer_name("");
        setVer_ort("");
        setVer_date("");
        setVer_vorh();
    }
    public String getVer_date(){
        return ver_date;
    }
    public void setVer_vorh(){
        if((getVer_name().length()>0)&(getVer_date().length()>0)){
            ver_vorh=true;
        }
    }
    public Boolean getVer_vorh(){
        return ver_vorh;
    }


}
