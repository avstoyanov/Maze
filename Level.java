
public class Level{
    private static String[][] maze;
    public static int stars;
    public static boolean gameOver = false; //this field decides if the game is over or not

    /*THESE FIELDS DEFINE THE LEVELS AND TURN THEM INTO MAZE OBJECTS. 
    FOLLOW THE SYNTAX SHOWN. WHILE LEVELS MAY BE OF DIFFERENT SIZES, THEY 
    MUST BE SQUARE. ANY " " (SPACE) IS AN EMPTY SPACE. ASTERISKS "*"
    ARE STARS, AND ANYTHING ELSE IS AN OBSTACLE. UP TO TWO CHARACTERS CAN BE 
    USED PER INDEX, AND IF TWO ARE USED, THEY SHOULD ONLY BE USED FOR OBSTACLES.
    */
    private static final String[][] maze1 =
            {{" "," ","|","*","|","*"},
             {"*"," ","|"," "," "," "},
             {"-]"," ","|"," ","--","--"},
             {" "," ","|"," "," "," "},
            {" ","--","|"," ","|"," "},
             {" "," "," "," ","|","*"}};
    private static int maze1Stars = 1; //4
    private static Maze level1= new Maze(maze1, maze1Stars);

    //THIS IS THE END OF THE CREATION OF A LEVEL. FOLLOW THIS EXAMPLE FOR FUTURE LEVELS.

    private static final String[][] maze2 =
            {{" ","*"," "," ","|","*"},
             {" ","--","|"," "," "," "},
             {"-]"," ","|"," ","*","--"},
             {" "," ","*"," "," "," "},
            {" ","--","|","--","|"," "},
             {" ","*"," "," ","|","*"}};
    private static int maze2Stars = 0;//6
    private static Maze level2= new Maze(maze2, maze2Stars);

    
    private static final String[][] maze3 =
            {{" "," "," ",  " ", " ","|3","*"," "," ","4"},
             {"-"," ","--","-"," "," ","\\-","-"," ","*"},
             {" "," "," ","*",   "2|"," "," "," "," "," "},
             {" ","[-","--","-<", " "," ","__","__","__","__"},
             {" "," ","*",  "1|"," "," /","nu","mb","er","*"},
             {"__","__","_/"," "," ","/o","f","st","ars"," "},
             {" "," #"," ",   " "," /","in"," f","ir","st"," "},
             {" ","4#","__","__","/l","ev","el","?","*"," "},
             {" "," #"," #"," #"," "," "," "," "," "," "},
             {"[]","3#","2#","1#","1*","2*","3*","4*","5*","6*"}};
             
    private static int maze3Stars = 7;
    private static final Maze level3= new Maze(maze3, maze3Stars);
    private static boolean maze3Condition(int in){
      if(in == maze1Stars){
        return true;
      } else{
        return false;
      }
    }
    private static void maze3Action(){
      int[] pos = matIndexOf(maze, "[]");
      Map.updateMap(pos[0], pos[1], "*");
    }

    public static void starInteraction(int y, int x){
      for(int num = 0; num < 10; num ++){
        String snum = num+"";
        int[] indexOfGate = matIndexOf(maze, snum+"#");
        if(matCheckAround(Map.getMap(), y, x, snum)[2] >= 0 && 
          indexOfGate[2] >= 0 &&
          ! (matIndexOf(Map.getMap(), (num-1)+"#")[2] >= 0)){
            Map.updateMap(indexOfGate[0], indexOfGate[1], "##");
            int[] gate = matCheckAround(maze, indexOfGate[0], indexOfGate[1], "#");
            Map.updateMap(gate[0], gate[1], " ");
            return;
        }
      }
    }

    public static void interact(int y, int x){
      for(int num = 0; num < 10; num ++){
        String snum = num+"*";
        int[] numstar = matCheckAround(maze, y, x, snum);
        if(numstar[2] >= 0){
          if(maze3Condition(num)){
            maze3Action();
            return;
          }
        }
      }
    }

    public static int[] matCheckAround(String[][] array, int y, int x, String val){
      int[] index = new int[3];
      if (y > 0 && array[y-1][x].indexOf(val) >=0){
        index[0] = y-1;
        index[1] = x;
        index[2] = array[y-1][x].indexOf(val);
      } 
      else if(y+1 < array.length && array[y+1][x].indexOf(val) >=0){
        index[0] = y+1;
        index[1] = x;
        index[2] = array[y+1][x].indexOf(val);
      }
      else if(x > 0 && array[y][x-1].indexOf(val) >=0){
        index[0] = y;
        index[1] = x-1;
        index[2] = array[y][x-1].indexOf(val); 
      }
      else if(x+1 < array[y].length && array[y][x+1].indexOf(val) >=0){
        index[0] = y;
        index[1] = x+1;
        index[2] = array[y][x+1].indexOf(val);
      }
      else{
        index[2] = -1;
      }
      return index;
    }

    public static int[] matIndexOf(String[][] array, String val){
      int[] index = new int[3];
      for(int subArray = 0; subArray < array.length; subArray++){
        for(int element = 0; element <array[subArray].length; element++){
          int subIndex = array[subArray][element].indexOf(val);
          if(subIndex >= 0){
            index[0] = subArray;
            index[1] = element;
            index[2] = subIndex;
            return index;
          }
        }
      }
      index[2] = -1;
      return index;
    }
    //add opening of gates if stars taken in order, add the box turning
    //into a star at the end, and add logic for answering the question,
    //so either running into the correct number or typing it



    //The following line creates the array of levels. 
    //When adding a level, make sure to add it's object in this array, or it will not be part of the game.
    private static final Maze[] levels = {level1, level2, level3};


    public static void reset(int level){
      maze = levels[level].maze.clone();
      Map.setCustomMaze(getMaze());
      Game.totalStarsCollected -= Game.stars;
      Game.stars = 0;
    }

    /*
    The Level constructor takes in the level number and if that level exists,
    updates the maze and stars fields to the corresponding values. Otherwise 
    both the Maze and stars fields are set to a value of void.
    */

    public static String[][] getMaze(){
      String[][] mazeOut = new String[maze.length][maze.length];
      for(int f = 0; f < maze.length; f ++){
        for(int g = 0; g < maze.length; g++){
          mazeOut[f][g] = maze[f][g];
        }
      }return mazeOut;
    }
    public Level(int level){
        if(level < levels.length){
            maze = levels[level].maze;
            stars = levels[level].stars;
        } else{
            gameOver = true;
        }

    }
}