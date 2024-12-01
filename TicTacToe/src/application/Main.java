package application;
	
import java.util.LinkedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;

public class Main extends Application {
	private final int[][] win_con = {{0, 1, 2},
								   	 {3, 4, 5},
								   	 {6, 7, 8},
								   	 {0, 3, 6},
								   	 {1, 4, 7},
								   	 {2, 5, 8},
								   	 {0, 4, 8},
								   	 {2, 4, 6}};
	
	ObservableList<Integer> player_played = FXCollections.observableArrayList();
	ObservableList<Integer> comp_played = FXCollections.observableArrayList();
	
	private boolean playing;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			playing = true;
			GridPane base = new GridPane();
			ArrayList<Tile> board = new ArrayList<Tile>();
			for(int i = 0; i < 9; i++) {
				Tile  playArea = new Tile(i);
				playArea.setOnMouseClicked(e -> {
					if(!playing || player_played.contains(playArea.getPos())) {
						return;
					}
					player_played.add(playArea.getPos());
					if(check_win()==1) {
						playing = false;
						System.out.println("Game Over! Win!");
						return;
					}
					if(check_win() == 0) {
						playing = false;
						System.out.println("Game Over! Draw!");
						return;
					}
					comp_turn();
					if(check_win()==-1) {
						playing = false;
						System.out.println("Game Over! Lose!");
					}
				});
				board.add(playArea);
				base.add(playArea, i%3, i/3);
			}
			player_played.addListener((ListChangeListener<Integer>) change -> {
				board.get(player_played.getLast()).setText("X");
			});
			comp_played.addListener((ListChangeListener<Integer>) change -> {
				board.get(comp_played.getLast()).setText("◯");
			});
			base.setAlignment(Pos.CENTER);
			base.setGridLinesVisible(true);
			root.setCenter(base);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private int check_win() {
		for(int[] row : win_con) {
			if (player_played.contains(row[0]) && player_played.contains(row[1]) && player_played.contains(row[2])) {
				return 1;
			}
		}
		for(int[] row : win_con) {
			if (comp_played.contains(row[0]) && comp_played.contains(row[1]) && comp_played.contains(row[2])) {
				return -1;
			}
		}
		if(player_played.size() + comp_played.size() == 9) {
			return 0;
		}
		return Integer.MIN_VALUE;
	}
	
	private void comp_turn() {
		int placement = 0;
		do {placement = (int)(Math.random()*9);} while(player_played.contains(placement) || comp_played.contains(placement));
		comp_played.add(placement);
	}
	
	private void comp_turn_hard() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
