//I have no idea what I'm doing...

package jfxTesting;

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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		
		Button btn = new Button();
		Button btn2 = new Button("Start");
		Pane root = new Pane();
		VBox root2 = new VBox();
		Canvas canvas = new Canvas(windowSizeX, windowSizeY);
		Rectangle rect = makeRect(0, 0, 100, 100, Color.BLUE, true);
		Rectangle rect2 = makeRect(0, 0, windowSizeX, windowSizeY, Color.BLACK, false);
		Rectangle rect3 = makeRect(0, 0, 50, 50, Color.WHITE, false);
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
		
		btn2.setTranslateX(10);
		btn2.setTranslateY(10);
		
		scene = new Scene(root, windowSizeX, windowSizeY, Color.BLACK);
		scene2 = new Scene(root2, windowSizeX, windowSizeY, Color.BLACK);
		
		root.getChildren().addAll(canvas, rect2, rect, rect3, btn);
		root2.getChildren().addAll(label, btn2);
		
		moveThisThing(scene, rect);
		moveThisThingCursor(scene, rect3);
		
		btn2.setOnAction(e -> window.setScene(scene));
		
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
		System.out.println("reset");
	}
	
	private void moveThisThing(Scene scene, Rectangle rect) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				
				switch (event.getCode()) {
				
				case UP:
					System.out.println("up");
					rect.setY(rect.getY() - f);
					break;
				case DOWN:
					System.out.println("down");
					rect.setY(rect.getY() + f);
					break;	
				case LEFT:
					System.out.println("left");
					rect.setX(rect.getX() - f);
					break;
				case RIGHT:
					System.out.println("right");
					rect.setX(rect.getX() + f);
					break;
				default:
					System.out.println("input IE");
				}
				
				if(rect.getX() + 100 > 600) {
					rect.setX( rect.getX() - 10 );
					System.out.println("reached out of bounds: +x");
				}
				if(rect.getX() < 0) {
					rect.setX( rect.getX() + 10 );
					System.out.println("reached out of bounds: -x");
				}
				if(rect.getY() + 100 > 400) {
					rect.setY( rect.getY() - 10 );
					System.out.println("reached out of bounds: +y");
				}
				if(rect.getY() < 0) {
					rect.setY( rect.getY() + 10 );
					System.out.println("reached out of bounds: -y");
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
					rect.setX( rect.getX() - 100);
					System.out.println("reached out of bounds: +x");
				}
				if(rect.getX() < 0) {
					reset(rect);
					System.out.println("reached out of bounds: -x");
				}
				if(rect.getY() + 50 > 400) {
					reset(rect);
					System.out.println("reached out of bounds: +y");
				}
				if(rect.getY() < 0) {
					reset(rect);
					System.out.println("reached out of bounds: -y");
				}
				
			}
			
		});
		
	}
	
}
