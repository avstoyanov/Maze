import java.util.Locale;

public class Map {

    private static String[][] myMaze;
    public static int heroRow = 0;
    public static int heroCol = 0;
    private static int mazeSize = 0;
    public static String prevVal = "";

    /*
    The Map constructor accepts a maze_size as an input and if it is used,
    generates a blank map. This is rarely used.
    */
    public Map(int maze_size) {
         mazeSize = maze_size;
         myMaze = new String[mazeSize][mazeSize];
         for (int i=0; i<myMaze.length; i++) {
             for (int j=0; j<myMaze[0].length; j++) {
                 myMaze[i][j] = " ";
             }
         }
        myMaze[0][0] = "X";
    }

    /*
    The setupMap() method sets the mazeSize variable when the Maze constructor 
    is not used. It also places the player in the initial position.
    */
    public static void setupMap(){
        mazeSize = myMaze.length;
        heroRow = 0;
        heroCol = 0; 
        myMaze[heroRow][heroCol] = "X";
        Game.printMaze();
    }

    //mapSize setter if needed
    public static void setSize(int size){
      mazeSize = size;
    }

    /*  
    The getMap() getter allows for the Maze to be accessed 
    */
    public static String[][] getMap() {
        return myMaze;
    }

    /*
    The updateMap method is unused in this current version. It is meant to
    interface with the map and edit it on the fly, changing obstacles and
    allowing for new paths to open. This would be used from the Level class 
    to create more interesting levels.
    */
    public static void updateMap(int y, int x, String val){
        myMaze[y][x] = val;
    }

    /*
    The setCustomMaze setter is responsible for setting the new map when a
    level changes. It also calls the setupMap() method the first time it is
    executed.
    */
    public static void setCustomMaze(String[][] map){
        myMaze = map;
        try{
          setupMap();
        } catch(NullPointerException e){
          return;
        }
        
    }

    /*
    The getHeroRow getter returns the player's current Row.
    */
    public static int getHeroRow(){
        return heroRow;
    }

    /*
    The getHeroCol getter returns the player's current Column.
    */
    public static int getHeroCol(){
        return heroCol;
    }

    /*
    The movePlayer() method is responsible for letting the player move. It 
    takes a user input that it interprets as a direction. It also checks if 
    moving in the specified direction is possible, and if it is, saves the 
    value of the spot it is moving to in prevVal, and moves to that spot. If 
    the player attempts an invalid move, no movement or change will occur.
    */
    public static void movePlayer(String direction) {
        direction = direction.strip().toLowerCase();
        prevVal = "";
        if (direction.equals("down") || direction.equals("s")) {
            if (heroRow + 1 >= mazeSize || (!myMaze[heroRow + 1][heroCol].equals(" ") && !myMaze[heroRow + 1][heroCol].equals("*"))) {
                System.out.println("You can't go that way.");
            }
            else {

                myMaze[heroRow][heroCol] = " ";
                heroRow += 1;
                prevVal = myMaze[heroRow][heroCol];
                myMaze[heroRow][heroCol] = "X";
            }
        }
        else if (direction.equals("up") || direction.equals("w")) {
            if (heroRow - 1 < 0 || (!myMaze[heroRow - 1][heroCol].equals(" ") && !myMaze[heroRow - 1][heroCol].equals("*"))) {
                System.out.println("You can't go that way.");
            }
            else {
                myMaze[heroRow][heroCol] = " ";
                heroRow -= 1;
                prevVal = myMaze[heroRow][heroCol];
                myMaze[heroRow][heroCol] = "X";
            }
        }
        else if (direction.equals("left") || direction.equals("a")) {
            if (heroCol - 1 < 0 || (!myMaze[heroRow][heroCol - 1].equals(" ") && !myMaze[heroRow][heroCol - 1].equals("*"))) {
                System.out.println("You can't go that way.");
            }
            else {
                myMaze[heroRow][heroCol] = " ";
                heroCol -= 1;
                prevVal = myMaze[heroRow][heroCol];
                myMaze[heroRow][heroCol] = "X";
            }
        }
        else if (direction.equals("right") || direction.equals("d")) {
            if (heroCol + 1 == mazeSize || (!myMaze[heroRow][heroCol + 1].equals(" ") && !myMaze[heroRow][heroCol + 1].equals("*"))) {
                System.out.println("You can't go that way.");
            }
            else {
                myMaze[heroRow][heroCol] = " ";
                heroCol += 1;
                prevVal = myMaze[heroRow][heroCol];
                myMaze[heroRow][heroCol] = "X";
            }
        }
        else if (direction.equals("restart") || direction.equals("r")) {
          Level.reset(Game.level);
        }
        else if (direction.equals("i")){
          Level.interact(heroCol, heroRow);
        }
    }

}