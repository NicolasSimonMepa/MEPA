package de.app.mepa;

import android.app.Application;
import android.util.Log;

/**
 * Created by vivienstumpe on 14.05.16.
 * enthält Variablen und Dienste, die während der Laufzeit in allen Activities verfügbar sind
 */
public class GlobaleDaten extends Application {
    
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
