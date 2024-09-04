package com.licnosoft.linkedin.model.general;


import java.io.Serializable;

public class PlainListDTO implements Serializable {
    protected String id;
    protected String title;

    public PlainListDTO() {
    }

    public PlainListDTO(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
