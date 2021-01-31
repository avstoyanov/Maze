import java.util.Scanner;

public class Game {
  //variable definitions
    private static int level = 0; 
    private static int stars = 0;
    private static int starsInLevel;
    private static int totalStarsCollected = 0;

    /*
    The printMaze() method simply prints each value in the map and
    formats it to two spaces. This allows for a nicer look, and for 
    objects that take up more than one space, like some horizontal 
    walls.
    */
    public static void printMaze() {
        String[][] tempMaze = Map.getMap();
        for(int s = 0; s < tempMaze.length+1; s++){
            System.out.print("__");
        }
        System.out.println();
        for (int i = 0; i < tempMaze.length; i++) {
            System.out.print("|");
            for (int j = 0; j < tempMaze[i].length; j++) {
                System.out.printf("%-2s", tempMaze[i][j]);
            }
            System.out.println("|");
        }
        for(int s = 0; s < tempMaze.length+1; s++){
            System.out.print("TT");
        }
        System.out.println();
    }

    /*
    The play() method takes care of the flow of the game. It gives a
    short introduction to how the game works, and then loads the first
    level. On every loop, the user is prompted for an input, until either 
    the game ends, or the user inputs "quit".
    */
    public static void play(){
        System.out.println("Hello! welcome to this simple game.\n " +
                "Each time an action is taken, the game will update and prompt for another action. \n" +
                "The possible actions are either 'up', 'left', 'down', or 'right', \n" +
                "but 'w', 'a', 's', or 'd' respectively can be used instead");
        System.out.println("Find all stars to win!\n");

        loadLevel(level);
        Scanner scan = new Scanner(System.in);
        String input = ""; //this is so the while loop can check for "quit"
        int currLev = level; //initializes currLevel, keeping track of when the level changes
        while(!input.equals("quit")){
            printMaze(); //prints the maze
            System.out.println("Where do we go?");
            input = scan.nextLine();
            Map.movePlayer(input); //moves the player based on the user input
            checkStars(); //checks if there is a star where the player is. This method also controls when the level ends.

            if(currLev != level){ //if the level changes, load a new one
                loadLevel(level);
                currLev = level;
            }
            if(Level.gameOver){ //if there are no more levels, end the game
                System.out.println("Good job! You beat the game!");
                scan.close(); //prevent a memory leak
                return; //stop game
            }
        }
        scan.close(); //prevents a memory leak
    }

    /*
    The checkStars() method checks whether the position where the player is
    previously had a star on it. If so, increments the total star count.
    It also checks if all the stars in a level have been collected. If so, it goes to the next level, and resets the stars variable.
    */
    public static void checkStars(){
        if (Map.prevVal.equals("*")){ //The prevVal variable tracks what the previous value on a spot was before the player stepped on it.
            stars++;
            System.out.println("You found a star!");
            totalStarsCollected++;
            System.out.println("Total stars found: "+totalStarsCollected);
        }
        if(stars == starsInLevel){
            System.out.println("Nice! Level complete.");
            stars = 0;
            level++;
        }
    }

    /*
    The loadLevel method loads the level specified. It creates a new object
    of type Level, and sets the game map to the new map field of the Level class. This allows for multiple levels.
    */
    public static void loadLevel(int level){
        Level currentLevel = new Level(level);
        Map.setCustomMaze(currentLevel.maze);
        starsInLevel = currentLevel.stars;
    }
}