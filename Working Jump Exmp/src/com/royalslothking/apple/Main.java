package com.royalslothking.apple;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application{
	
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;
    
    Label fpsCounter = new Label();
	
	private static boolean upPressed = false;
	
	private static boolean inAnimation = false;
	
	private static double g = 0.5;
	private static double vY = 0.0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane();
		Scene scene = new Scene(root, 600, 400);
		
		Rectangle player = new Rectangle(100, 300, 100, 100);

		scene.setOnKeyPressed(e ->{
			KeyCode key = e.getCode();
			if(key == KeyCode.UP) {
				upPressed = true;
			}
		});
		
		scene.setOnKeyReleased(e ->{
			KeyCode key = e.getCode();
			if(key == KeyCode.UP) {
				upPressed = false;
			}
		});
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }

                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    double frameRate = 1000000000.0 / elapsedNanosPerFrame;
                    fpsCounter.setText(String.format("UPS: %.2f", frameRate));

                    if(frameRate > 65.00 || frameRate < 55.00){
                        fpsCounter.setTextFill(Color.RED);
                    }else {
                        fpsCounter.setTextFill(Color.GREEN);
                    }

                }
				
				if(upPressed == true && inAnimation == false) {
					vY = -10.0;
					inAnimation = true;
				}
				
				player.setY(player.getY() + vY);
				
				if(inAnimation) {
					vY += g;
				}
				
				if(player.getY() + 100 >= 400) {
					vY = 0;
					player.setY(300);
					inAnimation = false;
				}
				
				System.out.println(vY);
				
			}
			
		};
		
		timer.start();
		
		root.getChildren().addAll(player, fpsCounter);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
