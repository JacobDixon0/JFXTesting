//I have no idea what I'm doing...

package com.royalslothking.jfxtesting;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class JFXTesting extends Application{

	Stage window;
	Scene scene, scene2;
	
	double f = 10.0;
	int windowSizeX = 600;
	int windowSizeY = 400;
	
	int rectColorRed = 0;
	int rectColorBlue = 0;
	int rectColorGreen = 0;
	boolean colorShift = true;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		
		Button btn = new Button();
		Button btn2 = new Button("Start");
		Button btn3 = new Button("Back");
		Pane root = new Pane();
		VBox root2 = new VBox();
		Canvas canvas = new Canvas(windowSizeX, windowSizeY);
		Rectangle rect = makeRect(0, 0, 100, 100, Color.WHITE, true);
		Rectangle rect2 = makeRect(0, 0, windowSizeX, windowSizeY, Color.BLACK, false);
		Rectangle rect3 = makeRect(0, 0, 50, 50, Color.BLUE, false);
		Label label = new Label("hi :)");
		
		Rectangle[] rectanglez = { rect, rect2, rect3 };
		
		btn.setText("Reset");
		btn.setTranslateX(10);
		btn.setTranslateY(10);
		btn.setOnAction(e -> {
			for( int i = 0; i < rectanglez.length; i++) {
			reset(rectanglez[i]);
			}
			
		});
		
		btn3.setOnAction(e -> {
			window.setScene(scene2);
		});
		
		btn2.setTranslateX(10);
		btn2.setTranslateY(10);
		
		btn3.setTranslateX(10);
		btn3.setTranslateY(40);
		
		label.setTranslateX(5);
		label.setTranslateY(5);
		
		scene = new Scene(root, windowSizeX, windowSizeY);
		scene2 = new Scene(root2, windowSizeX, windowSizeY);
		
		root.getChildren().addAll(canvas, rect2, rect, rect3, btn, btn3);
		root2.getChildren().addAll(label, btn2);
		
		moveThisThing(scene, rect);
		moveThisThingCursor(scene, rect3);
		
		btn2.setOnAction(e -> window.setScene(scene));
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				onUpdate(rect);
				
			}
		
		};
		
		timer.start();
		
		window.setTitle("Title");
		window.setScene(scene2);
		window.setResizable(false);
		window.sizeToScene();
		window.show();
		
	}
	
	//This is art, don't judge.
	private Rectangle makeRect(int posX, int posY, int sizeX, int sizeY, Color color, boolean hasStroke){
		Rectangle r1 = new Rectangle(posX, posY, sizeX, sizeY);
		r1.setFill(color);
		if(hasStroke) {
			r1.setStroke(Color.BLACK);
			r1.setStrokeWidth(2);
		}
		return r1;
	}
	
	public void reset(Rectangle rect) {
		rect.setX(0);
		rect.setY(0);
		//System.out.println("reset");
	}
	
	private void moveThisThing(Scene scene, Rectangle rect) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				switch (event.getCode()) {
				
				case UP:
					//System.out.println("up");
					if(rect.getY() > 0) {
						rect.setY(rect.getY() - f);
						//System.out.println("reached out of bounds: +x");
					}
					break;
				case DOWN:
					//System.out.println("down");
					if(rect.getY() + 100 < windowSizeY) {
						rect.setY(rect.getY() + f);
						//System.out.println("reached out of bounds: +x");
					}
					break;	
				case LEFT:
					//System.out.println("left");
					if(rect.getX() > 0) {
						rect.setX(rect.getX() - f);
						//System.out.println("reached out of bounds: +x");
					}
					break;
				case RIGHT:
					//System.out.println("right");
					if(rect.getX() + 100 < windowSizeX) {
						rect.setX(rect.getX() + f);
						//System.out.println("reached out of bounds: +x");
					}
					break;
						
				}
				
			}
			
		});
		
	}
	
	private void moveThisThingCursor(Scene scene, Rectangle rect) {
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				rect.setX(event.getX() - 25);
				rect.setY(event.getY() - 25);
				
				if(rect.getX() + 50 > 600) {
					reset(rect);
					//System.out.println("reached out of bounds: +x");
				}
				if(rect.getX() < 0) {
					reset(rect);
					//System.out.println("reached out of bounds: -x");
				}
				if(rect.getY() + 50 > 400) {
					reset(rect);
					//System.out.println("reached out of bounds: +y");
				}
				if(rect.getY() < 0) {
					reset(rect);
					//System.out.println("reached out of bounds: -y");
				}
				
			}
			
		});
		
	}
	
	public void onUpdate(Rectangle rect){
		
		if (window.getScene() == scene) {
			
		rect.setFill(Color.rgb(rectColorRed, rectColorGreen, rectColorBlue));
		
		//I don't know.
		if(rectColorRed == 0) {
			colorShift = true;
		}else if(rectColorRed == 255) {
			colorShift = false;
		}
		if(colorShift) {
			rectColorRed += 1;
		}else if (!(colorShift)) {
			rectColorRed -= 1;
		}
		if(colorShift) {
			rectColorGreen += 1;
		}else if (!(colorShift)) {
			rectColorGreen -= 1;
		}
		if(colorShift) {
			rectColorBlue += 1;
		}else if (!(colorShift)) {
			rectColorBlue -= 1;
		}
		//System.out.println(rectColorRed);
		
		}
		
	}
	
}
