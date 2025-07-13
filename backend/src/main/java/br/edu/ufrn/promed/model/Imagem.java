package br.edu.ufrn.promed.model;

public class Imagem {

    private int id;

    private String path;

    private int exameId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getExameId() {
        return exameId;
    }

    public void setExameId(int exameId) {
        this.exameId = exameId;
    }
}
