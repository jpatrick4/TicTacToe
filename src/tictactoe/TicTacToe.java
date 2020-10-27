package tictactoe;
/*
 * -Tic Tac Toe game
 * -Player vs CPU
 * -results are stored and then checked for a winner
 * -Can be a win, lose, or tie
 * 
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	// create global array list so all objects can access. 
	// Make static so only have to create once.
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement 1-9: ");
			int playerPosition = scan.nextInt();
			
			while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPositions)){
				System.out.println("Position taken! Enter a correct position");
				playerPosition = scan.nextInt();
			}
			placePieceOnBoard(gameBoard, playerPosition, "player");
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPosition = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
				cpuPosition = rand.nextInt(9) + 1;
			}
			placePieceOnBoard(gameBoard, cpuPosition, "cpu");
			printGameBoard(gameBoard);	
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			
		}
		printGameBoard(gameBoard);
	}

	// for each row in the board, and then each character in each row
	// print each character
	public static void printGameBoard(char[] [] gameBoard) {
		for(char[] row: gameBoard) {
			for(char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		// 2nd method to print game board
		/*// Loop through all rows 
        for (int i = 0; i < gameBoard.length; i++) { 
  
            // Loop through all elements of current row 
            for (int j = 0; j < gameBoard[i].length; j++) 
                System.out.print(gameBoard[i][j] + " "); 
  
            System.out.println(); 
        } */
	}
	public static void placePieceOnBoard(char[][] gameBoard, int position, String user) {
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		}
		else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(position);
		}
		
		switch(position) {
		case 1:
			// row //column
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			break;	
		}
		
		
	
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List topCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List botCol = Arrays.asList(3, 6, 9);
		List leftDiag = Arrays.asList(1, 5, 9);
		List rightDiag = Arrays.asList(7, 5, 3);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(topCol);
		winningConditions.add(midCol);
		winningConditions.add(botCol);
		winningConditions.add(leftDiag);
		winningConditions.add(rightDiag);
		
		for(List l : winningConditions) {
			if(playerPositions.containsAll(l)){
				return "Congratulations you win!";
			}
			else if(cpuPositions.containsAll(l)) {
				return "CPU wins! Sorry..";
			}
			else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "CAT!";
			}
			
		}
		
		
		return "";
		
	}
	
	
	
	
	

}
