package com.noahhuppert.newmaze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by block7 on 3/11/15.
 */
public class MazeSolver {
    public static List<MazePoint> Solve(Maze maze){
        Vector2 currentPos = maze.getStartPos();
        List<Vector2> visited = new ArrayList<Vector2>();

        List<Vector2> solution = new ArrayList<Vector2>();

        while(true){
            List<Vector2> pointsNextTo = maze.getEmptyPointsNextTo(currentPos);
            List<Vector2> newPointsNextTo = new ArrayList<Vector2>();

            for(Vector2 vector2 : pointsNextTo){
                if(!visited.contains(vector2) && (vector2.equals(maze.getStartPos()) || !maze.vector2InCorner(vector2))){
                    newPointsNextTo.add(vector2);
                }
            }

            if(newPointsNextTo.size() == 0){
                if(solution.isEmpty()){
                    System.out.println("No Solution");
                    break;
                } if(maze.getNonEmptyPointsNextTo(currentPos).contains(maze.getEndPos())){
                    System.out.println("Found Solution");
                    break;
                } else {
                    visited.add(solution.get(solution.size() - 1));

                    solution.remove(solution.size() - 1);

                    if(solution.isEmpty()){
                        continue;
                    }

                    currentPos = solution.get(solution.size() - 1);
                }
            } else{
                Vector2 goingTo = newPointsNextTo.get(0);

                if(newPointsNextTo.equals(maze.getEndPos())){
                    goingTo = maze.getEndPos();
                }

                visited.add(currentPos);

                solution.add(goingTo);

                currentPos = goingTo;
            }
        }

        maze.setSpecialPrintCoords(solution);

        return null;
    }
}
