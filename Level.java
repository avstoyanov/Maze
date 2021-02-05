public class Level{
    public String[][] maze;
    public int stars;
    public static boolean gameOver = false; //this field decides if the game is over or not

    /*THESE FIELDS DEFINE THE LEVELS AND TURN THEM INTO MAZE OBJECTS. 
    FOLLOW THE SYNTAX SHOWN. WHILE LEVELS MAY BE OF DIFFERENT SIZES, THEY 
    MUST BE SQUARE. ANY " " (SPACE) IS AN EMPTY SPACE. ASTERISKS "*"
    ARE STARS, AND ANYTHING ELSE IS AN OBSTACLE. UP TO TWO CHARACTERS CAN BE 
    USED PER INDEX, AND IF TWO ARE USED, THEY SHOULD ONLY BE USED FOR OBSTACLES.
    */
    private static String[][] maze1 =
            {{" "," ","|","*","|","*"},
             {"*"," ","|"," "," "," "},
             {"-]"," ","|"," ","--","--"},
             {" "," ","|"," "," "," "},
            {" ","--","|"," ","|"," "},
             {" "," "," "," ","|","*"}};
    private static int maze1Stars = 0; //4
    private static Maze level1= new Maze(maze1, maze1Stars);

    //THIS IS THE END OF THE CREATION OF A LEVEL. FOLLOW THIS EXAMPLE FOR FUTURE LEVELS.

    private static String[][] maze2 =
            {{" ","*"," "," ","|","*"},
             {" ","--","|"," "," "," "},
             {"-]"," ","|"," ","*","--"},
             {" "," ","*"," "," "," "},
            {" ","--","|","--","|"," "},
             {" ","*"," "," ","|","*"}};
    private static int maze2Stars = 0;//6
    private static Maze level2= new Maze(maze2, maze2Stars);

    
    private static String[][] maze3 =
            {{" "," "," ",  " ", " ","|3","*"," "," ","4"},
             {"-"," ","--","-"," "," ","\\-","-"," ","*"},
             {" "," "," ","*",   "2|"," "," "," "," "," "},
             {" ","[-","--","-<", " "," ","__","__","__","__"},
             {" "," ","*",  "1|"," "," /","nu","mb","er","*"},
             {"__","__","_/"," "," ","/o","f","st","ars"," "},
             {" "," "," ",   " "," /","in"," f","ir","st"," "},
             {"##","__","__","__","/l","ev","el","?","*"," "},
             {"  ","#","#","#"," "," "," "," "," "," "},
             {"[]","#","#","#","1","2","3","4","5","6"}};
             
    private static int maze3Stars = 7;
    private static Maze level3= new Maze(maze3, maze3Stars);
    //add opening of gates if stars taken in order, add the box turning
    //into a star at the end, and add logic for answering the question,
    //so either running into the correct number or typing it



    //The following line creates the array of levels. 
    //When adding a level, make sure to add it's object in this array, or it will not be part of the game.
    private Maze[] levels = {level1, level2, level3};


    /*
    The Level constructor takes in the level number and if that level exists,
    updates the maze and stars fields to the corresponding values. Otherwise 
    both the Maze and stars fields are set to a value of void.
    */
    public Level(int level){
        if(level < levels.length){
            maze = levels[level].maze;
            stars = levels[level].stars;
        } else{
            gameOver = true;
        }

    }
}