import java.util.*;

public class Main {

	public static boolean isLegalPosition(int[] board, int n) {
			
		if(n == 2 || n == 3) {
			return false;
		}
		
		for(int i = 0; i < n - 1; i++) {
			for(int j = i; j < n; j++) {
				if((board[i] - board[j] == i - j
						|| board[i] == board[j]
						|| board[j] - board[i] == i - j)
						&& i != j
						&& board[i] != 0
						&& board[j] != 0) {
					return false;
				}
			}
		}
			
		return true;
	}
	
	public static int[] nextPosition(int[] board, int n) {
		int[] current = new int[n];
		for(int i = 0; i < n; i++) {
			current[i] = board[i];
		}
		boolean botRightQueen = false;
		int start = -1;
		int[] nextPos = board;
		if(board[n-1] == n) {
			botRightQueen = true;
		}
		
		for(int i = n-1; i > 0; i--) {
			if(board[i] == n && isLegalPosition(current,n)) {
				break;
			}
			else if(board[i] == n || board[i] == 0) {
				board[i] = 0;
			}
			else {
				break;
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(board[i] == 0 && isLegalPosition(current, n) && botRightQueen == false && board[i] == n) {
				start = i - 1;
				break;
			}
			else if(board[i] == 0 && isLegalPosition(current, n) && botRightQueen == false) {
				start = i;
				break;
			}
			else if(board[i] == 0 && !isLegalPosition(current, n)){
				start = i - 1;
				break;
			}
			else if(board[i] == n && isLegalPosition(current, n) && botRightQueen == true) {
				start = i - 1;
				break;
			}
			else {
				start = n - 1;
			}
		}
		nextPos[start] += 1;
		return nextPos;
	}
	
	public static boolean isSolution(int[] board, int n) {
		boolean solution = false;
		for(int i = 0; i < n; i++) {
			if(board[i] == 0) {
				solution = false;
			} else if(isLegalPosition(board, n)) {
				solution = true;
			}
		}
		return solution;
	}
	
	public static int[] nextLegalPosition(int[] board, int n) {
		int[] next = nextPosition(board, n);
		
		while(!isLegalPosition(next, n)) {
			next = nextPosition(next, n);
		}
		return next;
	}
	
	public static void print(int[] b) {
		for(int i = 0; i < b.length; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.println("");
	}
	
	public static void firstSolutions() {
		
		for(int i = 4; i<= 19; i++) {
			int[] temp = new int[i];
			Arrays.fill(temp, 0);
			while(isSolution(temp, i) != true) {
				
				nextLegalPosition(temp, i);
			}
			print(temp);
		}
	}
	
	public static void countOfSolutions() {
		for(int i = 4; i <= 12; i++) {
			int count = 0;
			int[] temp = new int[i];
			Arrays.fill(temp, 0);
			
			while(temp[0] != i + 1) {
				nextLegalPosition(temp, i);
				
				if(isSolution(temp, i))
					count++;
			}
			System.out.println("Count for " + i + " Queens: " + count);
		}
	}
	
	public static void main(String[] args) {
		
		int[] legalPosExample = {1, 6, 8, 3, 7, 4, 2, 5};
		boolean legalTest = isLegalPosition(legalPosExample, 8);
		int[] illegalPosExample = {1, 5, 3, 7, 6, 2, 4, 8};
		boolean illegalTest = isLegalPosition(illegalPosExample, 8);
		int[] legalPartial = {1, 6, 8, 3, 7, 0, 0, 0};
		boolean legalPartialTest = isLegalPosition(legalPartial, 8);
		System.out.println(legalTest);
		System.out.println(illegalTest);
		System.out.println(legalPartialTest);
		
		System.out.println("First solutions for N-Queens problems for n = 4 -> n = 19: ");
		firstSolutions();
		System.out.println("Number of solutions for problems n = 4 -> n = 12: ");
		countOfSolutions();
	}

}
