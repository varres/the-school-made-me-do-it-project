// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ee.itcollege.i377.team29.entities;

import java.lang.String;

privileged aspect Seaduse_punkt_Roo_ToString {
    
    public String Seaduse_punkt.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Avaja: ").append(getAvaja()).append(", ");
        sb.append("Avatud: ").append(getAvatud()).append(", ");
        sb.append("Kehtiv_alates: ").append(getKehtiv_alates()).append(", ");
        sb.append("Kehtiv_kuni: ").append(getKehtiv_kuni()).append(", ");
        sb.append("Kommentaar: ").append(getKommentaar()).append(", ");
        sb.append("Muudetud: ").append(getMuudetud()).append(", ");
        sb.append("Muutja: ").append(getMuutja()).append(", ");
        sb.append("Pais: ").append(getPais()).append(", ");
        sb.append("Paragrahv: ").append(getParagrahv()).append(", ");
        sb.append("Piirivalvuri_seadus_intsidendi: ").append(getPiirivalvuri_seadus_intsidendi() == null ? "null" : getPiirivalvuri_seadus_intsidendi().size()).append(", ");
        sb.append("Seadus: ").append(getSeadus()).append(", ");
        sb.append("Seaduse_punkt_ID: ").append(getSeaduse_punkt_ID()).append(", ");
        sb.append("Serialversionuid: ").append(getSerialversionuid()).append(", ");
        sb.append("Suletud: ").append(getSuletud()).append(", ");
        sb.append("Sulgeja: ").append(getSulgeja()).append(", ");
        sb.append("Text: ").append(getText()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
