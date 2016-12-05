package com.andapp.futcorp.obdfut;

import java.io.Serializable;

public class Fault implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id, status;
    private String description, code, part;

    public Fault(int status, String description, String code, String part) {
        this.status = status;
        this.description = description;
        this.code = code;
        this.part = part;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }
}
