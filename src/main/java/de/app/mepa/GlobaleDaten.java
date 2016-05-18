package de.app.mepa;

import android.app.Application;
import android.util.Log;

/**
 * Created by vivienstumpe on 14.05.16.
 * enthält Variablen und Dienste, die während der Laufzeit in allen Activities verfügbar sind
 */
public class GlobaleDaten extends Application {
    
    //Notfallsituation - Variablen
    private String notfallsituation;

    //Bemerkung - Variablen
    private String bemerkung;
    
    //Erkrankung - Variablen
    private Integer erk_keine;
    private Integer alkoholisiert;
    private Integer uebelkeit_erbrechen;
    private Integer schwindel;
    private Integer herz_kreislauf;
    private Integer hitzeschlag;
    private Integer hitzeerschoepfung;
    private Integer vergiftung;
    private Integer atmung;
    private Integer unterkuehlung;
    private Integer baucherkrankung;
    private Integer stoffwechsel;
    private Integer neurologie;
    private Integer psychatrie;
    private Integer gynaekologie;
    private Integer kindernotfall;
    private Integer geburtshilfe;
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
    public void setNotfallsituation(String notfallsituation){this.notfallsituation=notfallsituation;}
    public String getNotfallsituation(){return notfallsituation;}
    public void loescheNotf(){
        setNotfallsituation("");
    }

    //Bemerkung - Dienste
    public void setBemerkung(String bemerkung){this.bemerkung=bemerkung;}
    public String getBemerkung(){return bemerkung;}
    public void loescheBem(){
        setBemerkung("");
    }
    
    //Erkrankung - Dienste
    public void setErk_keine(Integer keine){this.erk_keine=keine;}
    public void setAlkoholisiert(Integer alkoholisiert){this.alkoholisiert=alkoholisiert;}
    public void setUebelkeit_erbrechen(Integer uebelkeit_erbrechen){this.uebelkeit_erbrechen=uebelkeit_erbrechen;}
    public void setSchwindel(Integer schwindel){this.schwindel=schwindel;}
    public void setHerz_kreislauf(Integer herz_kreislauf){this.herz_kreislauf=herz_kreislauf;}
    public void setHitzeschlag(Integer hitzeschlag){this.hitzeschlag=hitzeschlag;}
    public void setHitzeerschoepfung(Integer hitzeerschoepfung){this.hitzeerschoepfung=hitzeerschoepfung;}
    public void setVergiftung(Integer vergiftung){this.vergiftung=vergiftung;}
    public void setAtmung(Integer atmung){this.atmung=atmung;}
    public void setUnterkuehlung(Integer unterkuehlung){this.unterkuehlung=unterkuehlung;}
    public void setBaucherkrankung(Integer baucherkrankung){this.baucherkrankung=baucherkrankung;}
    public void setStoffwechsel(Integer stoffwechsel){this.stoffwechsel=stoffwechsel;}
    public void setNeurologie(Integer neurologie){this.neurologie=neurologie;}
    public void setPsychatrie(Integer psychatrie){this.psychatrie=psychatrie;}
    public void setGynaekologie(Integer gynaekologie){this.gynaekologie=gynaekologie;}
    public void setKindernotfall(Integer kindernotfall){this.kindernotfall=kindernotfall;}
    public void setGeburtshilfe(Integer geburtshilfe){this.geburtshilfe=geburtshilfe;}
    public void setErk_sonstiges(Integer sonstiges){this.erk_sonstiges=sonstiges;}
    public void setErk_edtxt_sonstiges(String edtxtSonstiges){this.erk_edtxt_sonstiges=edtxtSonstiges;}
    public Integer getErk_keine(){return erk_keine;}
    public Integer getAlkoholisiert(){return alkoholisiert;}
    public Integer getUebelkeit_erbrechen(){return uebelkeit_erbrechen;}
    public Integer getSchwindel(){return schwindel;}
    public Integer getHerz_kreislauf(){return herz_kreislauf;}
    public Integer getHitzeschlag(){return hitzeschlag;}
    public Integer getHitzeerschoepfung(){return hitzeerschoepfung;}
    public Integer getVergiftung(){return vergiftung;}
    public Integer getAtmung(){return atmung;}
    public Integer getUnterkuehlung(){return unterkuehlung;}
    public Integer getBaucherkrankung(){return baucherkrankung;}
    public Integer getStoffwechsel(){return stoffwechsel;}
    public Integer getNeurologie(){return neurologie;}
    public Integer getPsychatrie(){return psychatrie;}
    public Integer getGynaekologie(){return gynaekologie;}
    public Integer getKindernotfall(){return kindernotfall;}
    public Integer getGeburtshilfe(){return geburtshilfe;}
    public Integer getErk_sonstiges(){return erk_sonstiges;}
    public String getErk_edtxt_sonstiges(){return erk_edtxt_sonstiges;}
    public void loescheErk(){
        setErk_keine(0);
        setAlkoholisiert(0);
        setUebelkeit_erbrechen(0);
        setSchwindel(0);
        setHerz_kreislauf(0);
        setHitzeschlag(0);
        setHitzeerschoepfung(0);
        setVergiftung(0);
        setAtmung(0);
        setUnterkuehlung(0);
        setBaucherkrankung(0);
        setStoffwechsel(0);
        setNeurologie(0);
        setPsychatrie(0);
        setGynaekologie(0);
        setKindernotfall(0);
        setGeburtshilfe(0);
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
