package com.noahhuppert.newmaze.models;

import com.noahhuppert.newmaze.models.MazeTraversers.MazeGenerator;

import java.util.*;

/**
 * Created by block7 on 3/6/15.
 */
public class Maze {
    private Map<Vector2, MazePoint> grid;
    private List<Vector2> specialPrintCoords;
    private final int width;
    private final int height;
    private final Vector2 startPos;
    private final Vector2 endPos;

    public Maze(int width, int height){
        width -= 1;
        height -= 1;

        if(width < 2 || height < 2){
            throw new RuntimeException("Width and Height of the maze must be 3 or larger");
        }

        this.width = width;
        this.height = height;

        startPos = new Vector2(0, 0);
        endPos = new Vector2(width, height);

        specialPrintCoords = new ArrayList<Vector2>();

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
        String blockChar = "\033[1mO\033[0m";//"\u2588";
        String out = "";

        for(int i = 0; i <= width + 2; i++){
            out += blockChar + " ";
        }

        for(int h = 0; h <= height; h++){
            for(int w = 0; w <= width; w++){
                Vector2 pos = new Vector2(w, h);
                MazePoint point = getGrid().get(pos);

                if(point.getPosition().getX() == 0){
                    out += "\n";
                }

                if(point.getPosition().getX() == 0){
                    out += blockChar;
                }

                out += " ";

                if(point.getPosition().equals(getStartPos())){
                    out += "\033[1mS\033[0m";
                } else if(point.getPosition().equals(getEndPos())) {
                    out += "\033[1mE\033[0m";
                } else if(getSpecialPrintCoords().contains(point.getPosition())){
                    out += "\033[31m\u2022\033[0m";
                } else if(point.getEmpty()) {
                    out += " ";
                } else {
                    out += "\033[1m*\033[0m";
                }

                if(point.getPosition().getX() == getWidth()){
                    out += " " + blockChar;
                }
            }
        }

        out += "\n";

        for(int i = 0; i <= width + 2; i++){
            out += blockChar + " ";
        }

        return out;
    }

    public boolean vector2OnMaze(Vector2 vector2){
        return vector2.getX() >= 0 && vector2.getX() <= getWidth() &&
                vector2.getY() >= 0 && vector2.getY() <= getHeight();
    }

    public boolean vector2InCorner(Vector2 vector2){
        return (vector2.getX() == 0 || vector2.getX() == getWidth()) &&
                (vector2.getY() == 0 || vector2.getY() == getHeight());
    }

    public Vector2 randomPoint(){
        return new Vector2(new Random().nextInt(getWidth()), new Random().nextInt(getHeight()));
    }

    public Vector2 randomEmptyPoint(){
        while(true){
            Vector2 point = randomPoint();
            if(getPoint(point).getEmpty()){
                return point;
            }
        }
    }

    public Vector2 randomNonEmptyPoint(){
        while(true){
            Vector2 point = randomPoint();
            if(!getPoint(point).getEmpty()){
                return point;
            }
        }
    }

    public List<Vector2> getPointsNextTo(Vector2 nextTo){
        List<Vector2> transformedPoints = new ArrayList<Vector2>();

        transformedPoints.add(nextTo.add(Vector2.UP));
        transformedPoints.add(nextTo.add(Vector2.RIGHT));
        transformedPoints.add(nextTo.add(Vector2.DOWN));
        transformedPoints.add(nextTo.add(Vector2.LEFT));

        List<Vector2> validTransformedPoints = new ArrayList<Vector2>();

        for(Vector2 vector2 : transformedPoints){
            if(vector2OnMaze(vector2)){
                validTransformedPoints.add(vector2);
            }
        }

        return validTransformedPoints;
    }

    public List<Vector2> getEmptyPointsNextTo(Vector2 nextTo){
        List<Vector2> transformedPoints = getPointsNextTo(nextTo);

        List<Vector2> validTransformedPoints = new ArrayList<Vector2>();

        for(Vector2 vector2 : transformedPoints){
            if(vector2OnMaze(vector2) && getPoint(vector2).getEmpty()){
                validTransformedPoints.add(vector2);
            }
        }

        return validTransformedPoints;
    }

    public List<Vector2> getNonEmptyPointsNextTo(Vector2 nextTo){
        List<Vector2> transformedPoints = getPointsNextTo(nextTo);

        List<Vector2> validTransformedPoints = new ArrayList<Vector2>();

        for(Vector2 vector2 : transformedPoints){
            if(vector2OnMaze(vector2) && !getPoint(vector2).getEmpty()){
                validTransformedPoints.add(vector2);
            }
        }

        return validTransformedPoints;
    }

    public void tunnle(Vector2 tunnleStartPos, int distance){
        Vector2 currentPos = tunnleStartPos;
        List<Vector2> visited = new ArrayList<Vector2>();

        int tunnleDistance = 0;

        while((tunnleDistance < distance) || distance == -1){
            List<Vector2> pointsNextTo = getNonEmptyPointsNextTo(currentPos);
            List<Vector2> newPointsNextTo = new ArrayList<Vector2>();

            for(Vector2 vector2 : pointsNextTo){
                if(!visited.contains(vector2) && (!vector2InCorner(vector2) || vector2.equals(getStartPos()))){
                    newPointsNextTo.add(vector2);
                }
            }

            if(newPointsNextTo.size() == 0){
                //getSpecialPrintCoords().add(currentPos);
                break;
            } else{
                Vector2 goingTo = newPointsNextTo.get(new Random().nextInt(newPointsNextTo.size()));

                if(goingTo.equals(getEndPos())){
                    break;
                }

                getPoint(goingTo).setEmpty(true);
                visited.add(currentPos);
                currentPos = goingTo;
            }

            tunnleDistance++;
        }
    }

    public void tunnleRandom(int min, int max){
        Vector2 tunnleStart = randomNonEmptyPoint();
        int tunnleLength = new Random().nextInt((max - min) + 1) + min;

        tunnle(tunnleStart, tunnleLength);
    }

    public static void FillRandom(Maze maze){
        new MazeGenerator(maze).traverse(maze.getStartPos(), maze.getEndPos());

        for(int i = 0; i < maze.getWidth() / 2; i++){
            maze.tunnleRandom(3, 7);
        }

        /*maze.tunnle(maze.getStartPos(), -1);
        maze.tunnle(maze.getEndPos(), -1);

        maze.tunnleRandom(3, 7);

        maze.tunnle(maze.randomEmptyPoint(), 4);*/

        /*Vector2 currentPos = maze.getStartPos();
        List<Vector2> visited = new ArrayList<Vector2>();

        while(true){
            List<Vector2> pointsNextTo = maze.getNonEmptyPointsNextTo(currentPos);
            List<Vector2> newPointsNextTo = new ArrayList<Vector2>();

            for(Vector2 vector2 : pointsNextTo){
                if(!visited.contains(vector2) && (vector2.equals(maze.getStartPos()) || !maze.vector2InCorner(vector2))){
                    newPointsNextTo.add(vector2);
                }
            }

            System.out.println(newPointsNextTo);

            if(newPointsNextTo.isEmpty() && visited.isEmpty()){
                break;
            } else if(newPointsNextTo.size() == 0){
                maze.getPoint(visited.get(visited.size() - 1)).setEmpty(false);

                visited.remove(visited.size() - 1);
                currentPos = visited.get(visited.size() - 1);
            } else {
                long seed = System.nanoTime();
                Collections.shuffle(newPointsNextTo, new Random(seed));

                Vector2 goingTo = newPointsNextTo.get(0);

                maze.getPoint(goingTo).setEmpty(true);

                visited.add(goingTo);
                currentPos = goingTo;
            }
        }*/
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

    public List<Vector2> getSpecialPrintCoords() {
        return specialPrintCoords;
    }

    public MazePoint getPoint(Vector2 vector2){
        return getGrid().get(vector2);
    }

    /* Setters */
    public void setGrid(Map<Vector2, MazePoint> grid) {
        this.grid = grid;
    }

    public void setSpecialPrintCoords(List<Vector2> specialPrintCoords) {
        this.specialPrintCoords = specialPrintCoords;
    }
}
