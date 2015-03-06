package com.noahhuppert.newmaze.models;

/**
 * Created by block7 on 3/6/15.
 */
public class MazePoint {
    private Vector2 position;
    private boolean empty;

    public MazePoint(Vector2 position, boolean empty) {
        this.position = position;
        this.empty = empty;
    }

    public MazePoint(int x, int y, boolean empty){
        this.position = new Vector2(x, y);
        this.empty = empty;
    }

    /* Actions */
    @Override
    public String toString(){
        return "[Position => " + getPosition() + " Empty => " + getEmpty() + "]";
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
    public Vector2 getPosition() {
        return position;
    }

    public boolean getEmpty() {
        return empty;
    }

    /* Setters */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
