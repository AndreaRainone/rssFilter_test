package it.faris;

public class Notizia {
    private String titolo;
    private String contenutoCompleto;
    private String categoria;
    private String fonte;
    private String dataPubblicazione;
    private String urlImmagine;

    public Notizia() {
    }

    public Notizia(String titolo, String contenutoCompleto, String categoria, String fonte, String dataPubblicazione, String urlImmagine) {
        this.titolo = titolo;
        this.contenutoCompleto = contenutoCompleto;
        this.categoria = categoria;
        this.fonte = fonte;
        this.dataPubblicazione = dataPubblicazione;
        this.urlImmagine = urlImmagine;
    }

    // --- GETTER E SETTER ---

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getContenutoCompleto() {
        return contenutoCompleto;
    }

    public void setContenutoCompleto(String contenutoCompleto) {
        this.contenutoCompleto = contenutoCompleto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}