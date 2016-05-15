package de.app.mepa;

import android.app.Application;
import android.util.Log;

/**
 * Created by vivienstumpe on 14.05.16.
 * enthält Variablen und Dienste, die während der Laufzeit in allen Activities verfügbar sind
 */
public class GlobaleDaten extends Application {

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

}