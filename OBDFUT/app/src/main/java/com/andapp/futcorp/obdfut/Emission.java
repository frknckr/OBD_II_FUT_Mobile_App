package com.andapp.futcorp.obdfut;

import java.io.Serializable;

public class Emission implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id, co2;
    private String model;

    public Emission(int co2, String model) {
        this.co2 = co2;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
