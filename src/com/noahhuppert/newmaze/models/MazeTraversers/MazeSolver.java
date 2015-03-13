package com.noahhuppert.newmaze.models.MazeTraversers;

import com.noahhuppert.newmaze.models.Maze;
import com.noahhuppert.newmaze.models.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by block7 on 3/13/15.
 */
public class MazeSolver extends MazeTraverser{
    public MazeSolver(Maze maze){
        super(maze);
    }

    @Override
    public List<Vector2> getPointsToMoveTo() {
        List<Vector2> pointsNextTo = getMaze().getEmptyPointsNextTo(getCurrentPos());
        List<Vector2> newPointsNextTo = new ArrayList<Vector2>();

        for(Vector2 vector2 : pointsNextTo){
            if(!getVisited().contains(vector2) && (vector2.equals(getMaze().getStartPos()) || !getMaze().vector2InCorner(vector2))){
                newPointsNextTo.add(vector2);
            }
        }

        return newPointsNextTo;
    }

    @Override
    public int getMovingToPointIndex(List<Vector2> points) {
        return 0;
    }

    @Override
    public void backtrack() {
        getVisited().add(getTraversed().get(getTraversed().size() - 1));
        getTraversed().remove(getTraversed().size() - 1);

        setCurrentPos(getTraversed().get(getTraversed().size() - 1));
    }

    @Override
    public void moveTo(Vector2 movingTo) {

    }
}
