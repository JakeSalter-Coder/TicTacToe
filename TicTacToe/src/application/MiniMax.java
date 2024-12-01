package application;


import java.util.ArrayList;

public class MiniMax {
	private static final int[][] win_con = {{0, 1, 2},
									   	    {3, 4, 5},
									   	    {6, 7, 8},
									   	    {0, 3, 6},
									   	    {1, 4, 7},
									   	    {2, 5, 8},
									   	    {0, 4, 8},
									   	    {2, 4, 6}};
								
	public static void main(String [] args) {
		ArrayList<Integer> player_moves = new ArrayList<Integer>();
		ArrayList<Integer> comp_moves = new ArrayList<Integer>();
		player_moves.add(0); player_moves.add(5); player_moves.add(8); //{0,5,8}
		comp_moves.add(2); comp_moves.add(6);                          //{2,6}
		int move = getMin(player_moves , comp_moves);
		System.out.println(move);
	}
	
	private static int getMin(ArrayList<Integer> max_moves, ArrayList<Integer> min_moves) {
		int score = evaluate(max_moves, min_moves);
		int [] all_scores = new int[9];
		if(score != 0) {
			return score;
		}
		for(int i = 0; i < 9; i++) {
			if(!max_moves.contains(i) && !min_moves.contains(i)) {
				min_moves.add(i);
				score = evaluate(max_moves, min_moves);
				if(score != 0) {
					return score;
				}
				for(int j = 0; j < 9; j++) {
					if(!max_moves.contains(j) && !min_moves.contains(j)) {
						max_moves.add(j);
						all_scores[i] += getMin(max_moves, min_moves);
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for(int i = 0; i < 9; i++) {
			if(all_scores[i] < min) {
				min = all_scores[i];
				min_index = i;
				System.out.println("\nScore at " + i + " = " + all_scores[i]);
			}
		}
		System.out.println("Min Value: " + min);
		return min;
	}
	
	private static int evaluate(ArrayList<Integer> max_moves, ArrayList<Integer> min_moves) {
		for(int[] row : win_con) {
			if(max_moves.contains(row[0]) && max_moves.contains(row[1]) && max_moves.contains(row[2])) {
				return 10;
			}
			if(min_moves.contains(row[0]) && min_moves.contains(row[1]) && min_moves.contains(row[2])) {
				return -10;
			}
		}
		return 0;
	}
}
