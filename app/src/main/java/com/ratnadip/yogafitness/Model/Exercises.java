package com.ratnadip.yogafitness.Model;

/**
 * Created by Ratnadip on 08-Apr-18.
 */

public class Exercises {

    private int image_id;
    private String name;

    public Exercises(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
