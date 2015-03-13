package com.noahhuppert.newmaze.models.MazeTraversers;

import com.noahhuppert.newmaze.models.Maze;
import com.noahhuppert.newmaze.models.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by block7 on 3/13/15.
 */
public abstract class MazeTraverser {
    private Maze maze;
    private List<Vector2> visited;
    private List<Vector2> traversed;
    private Vector2 currentPos;

    public MazeTraverser(Maze maze){
        this.maze = maze;
    }

    public List<Vector2> traverse(Vector2 startPos, Vector2 endPos){
        visited = new ArrayList<Vector2>();
        traversed = new ArrayList<Vector2>();
        currentPos = startPos;

        getTraversed().add(startPos);

        while(true){
            List<Vector2> pointsToMoveTo = getPointsToMoveTo();

            if(getMaze().getPointsNextTo(currentPos).contains(endPos)){
                break;
            }

            if(pointsToMoveTo.isEmpty() && getTraversed().isEmpty()){
                System.out.println("No solution");
                break;
            } else if(pointsToMoveTo.isEmpty() && !getTraversed().isEmpty()){
                backtrack();
            } else {
                int movingToPointIndex = getMovingToPointIndex(pointsToMoveTo);
                Vector2 movingTo = pointsToMoveTo.get(movingToPointIndex);

                moveTo(movingTo);

                getVisited().add(movingTo);
                getTraversed().add(movingTo);
                setCurrentPos(movingTo);
            }
        }

        return getTraversed();
    }

    public abstract List<Vector2> getPointsToMoveTo();
    public abstract int getMovingToPointIndex(List<Vector2> points);
    public abstract void backtrack();
    public abstract void moveTo(Vector2 movingTo);

    /* Getters */
    public Maze getMaze() {
        return maze;
    }

    public List<Vector2> getVisited() {
        return visited;
    }

    public List<Vector2> getTraversed() {
        return traversed;
    }

    public Vector2 getCurrentPos() {
        return currentPos;
    }

    /* Setters */
    public void setCurrentPos(Vector2 currentPos) {
        this.currentPos = currentPos;
    }
}
