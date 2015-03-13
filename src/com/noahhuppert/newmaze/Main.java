package com.noahhuppert.newmaze;

import com.noahhuppert.newmaze.models.Maze;
import com.noahhuppert.newmaze.models.MazeTraversers.MazeSolver;
import com.noahhuppert.newmaze.models.Vector2;

import java.util.List;

/**
 * Created by block7 on 3/6/15.
 */
public class Main {
    public static void main(String[] args){
        Maze maze = new Maze(10, 10);
        Maze.FillRandom(maze);

        List<Vector2> solution = new MazeSolver(maze).traverse(maze.getStartPos(), maze.getEndPos());
        solution.remove(0);

        maze.setSpecialPrintCoords(solution);

        System.out.println(maze);
    }
}
