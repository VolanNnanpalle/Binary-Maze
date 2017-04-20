package java3.maze;

/**
 *  This class finds all the solution of a maze which is entered by the user
 */
public class Maze
{

    private char[][] maze;
    private int row; //number of rows in maze 
    private int colm; //number of columns in maze
    private int rowOfEntrance; //row where the entrance is located
    private int colmOfEntrance; //column where the entrance is located 
    private final char PATH='p'; //part of the path 
    private final char CLEAR='0'; //clear path in maze 
    private final char EXIT='e'; //exit of the maze 
    private final char ENTRANCE='b'; //beginign of maze 
    private int entranceFound=0; // if 0 start not found else found
    private int numOfSolutions; //number of solutions

    /**
     * Default constructor which initializes the character array to a (row,columns) grid
       representing the maze
     * @param r the row 
       @param c the column
     */
    public Maze(int r, int c)
    {
        this.row=r;
        this.colm=c;
        numOfSolutions=0;
        maze=new char[r][c];
    }

    /**
     * Fills the maze with the supplied character array
     * @param m the character array representing the maze
     */
    public void fillMaze(char[][] m)
    {
        for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<colm; j++)
            {
                maze[i][j]=m[i][j]; //fills array at each position 
            }
        }
    }

    /**
     * Recursively navigate the maze and print out all possible solutions
     * @param r the row
     * @param c the column
     */
    public void navigate(int r, int c)
    {
        //finds the entrance of the maze
        if(entranceFound==0)
        {
            entranceFound=1; //to insure that entrance is never looked for again when found
            findEntrance();
            r=rowOfEntrance;
            c=colmOfEntrance;
        }
        //check up 
        /*
        looks up and checks if its not out of bounds and it is clear or it is 
        not out of bounds and the exit 
         */
        if(!outOfBounds(r-1, c)&&(maze[r-1][c]==CLEAR||findExit(r-1, c)))
        {
            maze[r][c]=partOfPath(r, c);//set position as part of the path
            navigate(r-1, c);//keeps going in that direction by doing recursion
        }
        //check right
        /*
        looks right and checks if its not out of bounds and it is clear or it is 
        not out of bounds and the exit 
         */
        if(!outOfBounds(r, c+1)&&(maze[r][c+1]==CLEAR||findExit(r, c+1)))
        {
            maze[r][c]=partOfPath(r, c);//set position as part of the path
            navigate(r, c+1); //keeps going in that direction by doing recursion
        }
        //check down
        /*
        looks down and checks if its not out of bounds and it is clear or it is 
        not out of bounds and the exit 
         */
        if(!outOfBounds(r+1, c)&&(maze[r+1][c]==CLEAR||findExit(r+1, c)))
        {
            maze[r][c]=partOfPath(r, c);//set position as part of the path
            navigate(r+1, c);//keeps going in that direction by doing recursion
        }
        //check left
        /*
        looks left and checks if its not out of bounds and it is clear or it is 
        not out of bounds and the exit 
         */
        if(!outOfBounds(r, c-1)&&(maze[r][c-1]==CLEAR||findExit(r, c-1)))
        {
            maze[r][c]=partOfPath(r, c);//set position as part of the path
            navigate(r, c-1); //keeps going in that direction by doing recursion
        }

        //check if the end is found
        if(findExit(r, c))
        {
            maze[r][c]=partOfPath(r, c); //set position as part of the path
            numOfSolutions++; //increment number of solutions 
            System.out.println("Solution: "+numOfSolutions);
            printMaze(); //print the maze
        }
        //checks if the position is not the end then backtracks 
        if(maze[r][c]!=EXIT)
        {
            maze[r][c]=CLEAR; //backtracking
        }
    }

    /**
    Checks if the position (row,column) is out of bounds of the maze array
    @param r the row 
    @param c the column
    @return true if the position(row,column) is out of bounds otherwise false
     */
    private boolean outOfBounds(int r, int c)
    {
        return r<0||c<0||r>=this.row||c>=this.colm;

    }

    /**
    Finds the entrance of the maze array
     */
    private void findEntrance()
    {
        for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<this.colm; j++)
            {
                if(maze[i][j]==ENTRANCE)
                {
                    rowOfEntrance=i;
                    colmOfEntrance=j;
                    return;
                }
            }
        }
    }

    /**
    Finds the exit of the maze array
    @param r the row
    @param c the column
    @return true if the exit is found otherwise false
     */
    private boolean findExit(int r, int c)
    {
        return maze[r][c]==EXIT;
    }

    /**
    Places a p where the the path is clear in the maze to mark it as part of the 
    solution of the maze 
    @param r the row
    @param c the column
    @return 'p' if the position(row,column) is not the exit else returns 'e'
     */
    private char partOfPath(int r, int c)
    {
        if(maze[r][c]!=EXIT)
        {
            return PATH;
        }
        return EXIT;
    }

    /**
       Prints and returns a string representation of the grid which represents
     * the maze.
     */
    private void printMaze()
    {
        for(int i=0; i<this.row; i++)
        {
            for(int j=0; j<colm; j++)
            {
                System.out.print(maze[i][j]+" ");
            }
            System.out.println("");
        }

    }
}
