package java3.maze;

import java.util.Scanner;

/**
 *Main class to test the maze class 
 * @author Volan Nnanpalle
 */
public class Java3Maze
{

    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        int r, c;
        System.out.println("Please enter the number of rows and press enter "+
            "then enter number of colums and press enter : ");
        r=scan.nextInt();
        c=scan.nextInt();
        System.out.println("Enter your binary maze. The entrance should be " +
            "set as b and the exit should be set as e");
        char[][] test=new char[r][c];
        for(int i=0; i<r; i++)
        {
            String data="";
            if(scan.hasNext())
            {
                // input from user 
                data=scan.next();
            }else
            {
                break;
            }
            for(int j=0; j<c; j++)
            {
                test[i][j]=data.charAt(j);
            }
        }
        Maze m=new Maze(r, c);
        m.fillMaze(test);
        m.navigate(r, c);
    }

}
