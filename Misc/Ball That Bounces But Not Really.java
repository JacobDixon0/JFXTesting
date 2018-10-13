package com.royalslothking.jfxtesting.ball;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ball extends Application{

	AnimationTimer timer;
	
	Circle circle = new Circle();
	
	double circleX = 400;
	double circleY = 400;
	int windowSizeX = 600;
	int windowSizeY = 400;
	double g = 2;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Parent createContent() {
		
		Pane root = new Pane();
		Rectangle rectangle = new Rectangle(0, 0, windowSizeX, windowSizeY);
		
		rectangle.setFill(Color.BLACK);
		
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				onUpdate();
			}
		};
		
		timer.start();
		
		circle.setCenterX(100);
		circle.setCenterY(100);
		circle.setFill(Color.WHITE);
		circle.setRadius(25);
		
		root.getChildren().addAll(rectangle, circle);
		
		return root;
	}
	
	public void onUpdate(){
			
			if( !(circle.getCenterY() + 24 > windowSizeY) ) {
				System.out.println("ay");
				g = g + 0.5;
				circle.setCenterY( circle.getCenterY() + g );
			}else {
				g = 0;
			}
		
		if( circle.getCenterY() + 25 > windowSizeY ) {
			g = g * -1;
		}
		if( circle.getCenterY() - 25 < 0 ) {
			g = g * -1;
		}
		System.out.println("G = " + g);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene(new Scene(createContent(), windowSizeX, windowSizeY));
		primaryStage.setTitle("Title");
		primaryStage.show();
	}
	
}
