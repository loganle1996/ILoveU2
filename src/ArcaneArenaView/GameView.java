/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArcaneArenaView;


import ArcaneArenaModel.GameModel;
import ArcaneArenaModel.Entity;
import ArcaneArenaModel.Id;
import ArcaneArenaModel.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ArcaneArenaController.prepareKeyEvent;
import ArcaneArenaModel.Wall;



/**
 *
 * @author owne
 */
public class GameView extends Application implements prepareKeyEvent{
    static Scene mainScene;
    static GraphicsContext gc;
    static int WIDTH = 800;
    static int HEIGHT = 800;
    static Image image1,image2,image3,playerImage;
    private Stage mainStage;
    private static GameModel gameModel;
  
    public void display(GameModel model){
        System.out.println("gameView displayed");
        String[] args = null;
        try {
            gameModel = model;
            gameModel.addEntity(new Player(700, 600, 60, 60, true,Id.player, gameModel));
            gameModel.addTile(new Wall(700, 500, 60, 60, true, Id.wall, gameModel, "wall 1"));
            main(args);
        } catch (Exception ex) {
            System.out.println("cannot open stage");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ArcaneArena");
        Group root = new Group();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        //Associate gc to the canvas to draw.
        gc = canvas.getGraphicsContext2D();
        //Get all images from all resources.
        loadGraphics();
        // main scene listens for keyevent
        prepareKeyEvent(mainScene);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);
                updateAndRenderModel();
            }
        }.start();
        primaryStage.show();
    }
    private static void loadGraphics()
    {
            image1 = new Image("Assets/bricks_2.png");
            image2 = new Image("Assets/bricks_1.png");
            image3 = new Image("Assets/bricks_3.png");
            playerImage = new Image("Assets/1.png");
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void updateAndRenderModel(){
         gc.clearRect(0, 0, WIDTH, HEIGHT);
         gameModel.updateModelGame();
         gameModel.renderModelGame(gc, playerImage);
    }

    @Override
    public void prepareKeyEvent(Scene mainScene) {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                //currentlyActiveKeys.add(event.getCode().toString());
                for(Entity en: gameModel.getEntity()){   
                    if(event.getCode() == KeyCode.SPACE){
                        if(en.isJumping() == false){
                            en.setJumping(true);
                        }
                    }
                    if(event.getCode() == KeyCode.S){
                        en.setVelY(0.0);
                    }
                    if(event.getCode() == KeyCode.A){
                        en.setVelX(-5.0);
                    }
                    if(event.getCode() == KeyCode.D){
                        en.setVelX(5.0);
                    }
                }
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                for(Entity en: gameModel.getEntity()){   
                    if(event.getCode() == KeyCode.SPACE){
                        en.setJumping(false);
                    }
                    if(event.getCode() == KeyCode.S){
                        en.setVelY(0);
                    }
                    if(event.getCode() == KeyCode.A){
                        en.setVelX(0);
                    }
                    if(event.getCode() == KeyCode.D){
                        en.setVelX(0);
                    }
                }
            }
        });
        
    }
}
