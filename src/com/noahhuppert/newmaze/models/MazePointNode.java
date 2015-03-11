package com.noahhuppert.newmaze.models;

/**
 * Created by block7 on 3/11/15.
 */
public class MazePointNode {
    private final Vector2 parent;
    private final Vector2 pointPosition;

    public MazePointNode(Vector2 pointPosition, Vector2 parent) {
        this.parent = parent;
        this.pointPosition = pointPosition;
    }

    /* Getters */
    public Vector2 getParent() {
        return parent;
    }

    public Vector2 getPointPosition() {
        return pointPosition;
    }
}
