package com.noahhuppert.newmaze;

import com.noahhuppert.newmaze.models.Maze;
import com.noahhuppert.newmaze.models.MazeSolver;

/**
 * Created by block7 on 3/6/15.
 */
public class Main {
    public static void main(String[] args){
        Maze maze = new Maze(10, 10);
        Maze.FillRandom(maze);

        MazeSolver.Solve(maze);

        System.out.println(maze);
    }
}
