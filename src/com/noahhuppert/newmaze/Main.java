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
        int mazeWidth = 200;
        int mazeHeight = 200;

        long mazeInitBefore = 0;
        long mazeInitAfter = 0;

        long mazeGenBefore = 0;
        long mazeGenAfter = 0;

        long mazeSolveBefore = 0;
        long mazeSolveAfter = 0;

        long mazePrintBefore = 0;
        long mazePrintAfter = 0;

        mazeInitBefore = System.currentTimeMillis();
        Maze maze = new Maze(mazeWidth, mazeHeight);
        mazeInitAfter = System.currentTimeMillis();
        System.out.println("\033[34mMaze Init Done: " + (mazeInitAfter - mazeInitBefore) + " ms\033[0m");

        mazeGenBefore = System.currentTimeMillis();
        Maze.FillRandom(maze);
        mazeGenAfter = System.currentTimeMillis();
        System.out.println("\033[34mMaze Generation Done: " + (mazeGenAfter - mazeGenBefore) + " ms\033[0m");

        mazeSolveBefore = System.currentTimeMillis();
        List<Vector2> solution = new MazeSolver(maze).traverse(maze.getStartPos(), maze.getEndPos());
        maze.setSpecialPrintCoords(solution);
        mazeSolveAfter = System.currentTimeMillis();
        System.out.println("\033[34mMaze Solve Done: " + (mazeSolveAfter - mazeSolveBefore) + " ms\033[0m");

        mazePrintBefore = System.currentTimeMillis();
        System.out.println(maze);
        mazePrintAfter = System.currentTimeMillis();
        System.out.println("\033[34mMaze Print Done: " + (mazePrintAfter - mazePrintBefore) + " ms\033[0m");

        System.out.println("\n| -----------------------------------\n" +
                           "| \033[31mMaze Size:    " + mazeWidth + "x" + mazeHeight + "\033[0m\n" +
                           "| \033[31mTotal Time:   " + (mazePrintAfter - mazeInitBefore) + " ms\033[0m\n" +
                           "| -----------------------------------\n" +
                           "| \033[34mMaze Init Time:           " + (mazeInitAfter - mazeInitBefore) + " ms\033[0m\n" +
                           "| \033[34mMaze Generator Time:      " + (mazeGenAfter - mazeGenBefore) + " ms\033[0m\n" +
                           "| \033[34mMaze Solve Time:          " + (mazeSolveAfter - mazeSolveBefore) + " ms\033[0m\n" +
                           "| \033[34mMaze Print Time:          " + (mazePrintAfter - mazePrintBefore) + " ms\033[0m\n");
    }
}
