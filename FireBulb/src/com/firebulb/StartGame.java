package com.firebulb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StartGame {

	/**
	 * @param args
	 */
	public boolean isFirable(Integer[][] matrix , int posX,int posY)
	{
		if(matrix[posX][posY]==1)
		{
			return true;
		}
		
		return false;
	}
	/**Input the position where to fire
	 * @param matrix
	 * @param posX
	 * @param posY
	 * @return
	 */
	public void fireNow(Integer[][] matrix , int posX,int posY)
	{
		if(isFirable(matrix, posX, posY))
		{
		
			
			
		}
	}
	
	/**We can alter only ON bulbs
	 * @param matrix
	 * @param posX
	 * @param posY
	 */
	public void alterBulb(Integer[][] matrix , int posX,int posY)
	{
		if(matrix[posX][posY]==1)
		{
		
			matrix[posX][posY]=0;	
			
		}
	}
	
	public void printMatrix(Integer[][] matrix)
	{
		Integer[] row=new Integer[8];
		for(int k=0;k<=7;k++)
		{
			for(int l=0;l<=7;l++)
			{
				row[l]=matrix[k][l];
			}
			for (int m=0;m<8;m++)
			{
				System.out.print(row[m]);
			}
//			System.out.println("\n");
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int player;
		Integer[][] matrix = new Integer[8][8];
		StartGame start=new StartGame();
		String line;
		BufferedReader playerStearm, playerBoard;
		System.out.println("Enter Turn of player : 1 or 2");
		 playerStearm=new BufferedReader(new InputStreamReader(System.in));
		try {
			
			if(null!=(line=playerStearm.readLine()))
			{
			 player=Integer.parseInt(line);
			}
			else
			{
			System.out.println("Give player number");	
			}
			
			
			System.out.println("Game board intial config . 8*8 matrix of 0 and 1 , 1 is on and 0 is off");
			
			playerBoard=new BufferedReader(new InputStreamReader(System.in));
			int i = 0;
			while(null!=(line=playerBoard.readLine()) && i<=7)
			{
				String row=line;
				for (int j=0;j<=7;j++)
				{
					matrix[i][j]=Character.getNumericValue(row.charAt(j));
				}
				i++;
			}
			System.out.println("Before firing Final matrix is : ");
			start.printMatrix(matrix);
			start.fireNow(matrix, 0, 1);
			System.out.println("After firing Final matrix is : ");
			start.printMatrix(matrix);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
