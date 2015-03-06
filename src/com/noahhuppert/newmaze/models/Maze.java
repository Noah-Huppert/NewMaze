package com.noahhuppert.newmaze.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by block7 on 3/6/15.
 */
public class Maze {
    private Map<Vector2, MazePoint> grid;
    private final int width;
    private final int height;
    private final Vector2 startPos;
    private final Vector2 endPos;

    public Maze(int width, int height){
        this.width = width;
        this.height = height;

        startPos = new Vector2(0, 0);
        endPos = new Vector2(width, height);

        grid = new HashMap<Vector2, MazePoint>();

        for(int w = 0; w <= width; w++){
            for(int h = 0; h <= height; h++){
                Vector2 pos = new Vector2(w, h);
                MazePoint point = new MazePoint(pos, false);

                getGrid().put(pos, point);
            }
        }
    }

    /* Actions */
    @Override
    public String toString(){
        String out = "";

        for(int h = 0; h <= height; h++){
            for(int w = 0; w <= width; w++){
                Vector2 pos = new Vector2(w, h);
                MazePoint point = getGrid().get(pos);

                if(point.getPosition().getX() == 0){
                    out += "\n";
                }

                out += " ";

                if(point.getEmpty()) {
                    out += " ";
                } else if(point.getPosition().equals(getStartPos())){
                    out += "S";
                } else if(point.getPosition().equals(getEndPos())) {
                    out += "E";
                } else {
                    out += "*";
                }
            }
        }

        return out;
    }

    public List<Vector2> getPointsNextTo(Vector2 nextTo){
        Vector2 tUp = nextTo.add(Vector2.UP);
        Vector2 tDown = nextTo.add(Vector2.DOWN);
        Vector2 tRight = nextTo.add(Vector2.RIGHT);
        Vector2 tLeft = nextTo.add(Vector2.LEFT);
    }

    public static void FillRandom(Maze maze){

    }

    /* Getters */
    public Map<Vector2, MazePoint> getGrid() {
        return grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getStartPos() {
        return startPos;
    }

    public Vector2 getEndPos() {
        return endPos;
    }

    /* Setters */
    public void setGrid(Map<Vector2, MazePoint> grid) {
        this.grid = grid;
    }
}
