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

        List<MazePointNode> solution = new ArrayList<MazePointNode>();

        while(true){
            List<Vector2> pointsNextTo = maze.getEmptyPointsNextTo(currentPos);
            List<Vector2> newPointsNextTo = new ArrayList<Vector2>();

            for(Vector2 vector2 : pointsNextTo){
                if(!visited.contains(vector2) && (vector2.equals(maze.getStartPos()) || !maze.vector2InCorner(vector2))){
                    newPointsNextTo.add(vector2);
                }
            }

            if(newPointsNextTo.size() == 0){
                MazePointNode lastNode = null;

                if(solution.size() != 0){
                    lastNode = solution.get(solution.size() - 1);
                }

                //TODO Trace back

                break;
            } else{
                Vector2 goingTo = newPointsNextTo.get(0);

                if(newPointsNextTo.equals(maze.getEndPos())){
                    goingTo = maze.getEndPos();
                }

                System.out.println(goingTo);

                if(goingTo.equals(maze.getEndPos())){
                    System.out.println(1);
                    break;
                }

                maze.getPoint(goingTo).setEmpty(true);
                visited.add(currentPos);

                if(solution.size() == 0){
                    solution.add(new MazePointNode(goingTo, null));
                } else {
                    Vector2 parent = solution.get(solution.size() - 1).getPointPosition();
                    solution.add(new MazePointNode(goingTo, parent));
                }

                currentPos = goingTo;
            }
        }

        for(MazePointNode node : solution){
            maze.getSpecialPrintCoords().add(node.getPointPosition());
        }

        return null;
    }
}
