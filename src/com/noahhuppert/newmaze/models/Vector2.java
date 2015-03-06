package com.noahhuppert.newmaze.models;

/**
 * Created by block7 on 3/6/15.
 */
public class Vector2 {
    public static final Vector2 UP = new Vector2(0, -1);
    public static final Vector2 DOWN = new Vector2(0, 1);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    public static final Vector2 LEFT = new Vector2(-1, 0);

    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector2){
        this.x = vector2.getX();
        this.y = vector2.getY();
    }

    /* Actions */
    public Vector2 add(Vector2 vector2){
        Vector2 out = new Vector2(this);
        out.setX(getX() + vector2.getX());
        out.setY(getY() + vector2.getY());

        return out;
    }

    @Override
    public String toString(){
        return "[" + getX() + ", " + getY() + "]";
    }

    @Override
    public boolean equals(Object o) {
        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /* Getters */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /* Setters */
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
