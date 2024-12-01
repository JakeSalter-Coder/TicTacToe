package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class Tile extends Label {
	private int pos;
	
	Tile(int i){
		super("");
		pos =  i;
		super.setStyle("-fx-font-size: 64;");
		super.setMinHeight(100);
		super.setMinWidth(100);
		super.setAlignment(Pos.CENTER);
		super.setTextAlignment(TextAlignment.CENTER);
		super.setOnMouseEntered(e -> {
			super.setStyle("-fx-background-color: grey; -fx-font-size: 64;");
		});
		super.setOnMouseExited(e -> {
			super.setStyle("-fx-background-color: white; -fx-font-size: 64;");
		});
	}
	
	public int getPos() {
		return pos;
	}
}
