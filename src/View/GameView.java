/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Model.Entity;
import Model.Player;
import Model.Sprite;
import Model.SpriteSheet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Controller.prepareKeyEvent;
import Model.GameModel;
import Model.Id;
import Model.TileCache;
import Model.TiledMap;



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
    public static GameModel gameModel;
    public static SpriteSheet sheet;
    public static Sprite playerSprite [] = new Sprite[6];
    public static TiledMap tiledMap = new TiledMap();
    public void display(GameModel model){
        String[] args = null;
        try {
            gameModel = model;
            main(args);
            System.out.println("gameView displayed");
        } catch (Exception ex) {
            System.out.println("cannot open stage");
            ex.printStackTrace();
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
            playerImage = new Image("1.png");
            tiledMap.mapData();
            TileCache.loadCache();
            gameModel.addEntity(new Player(700, 600, 40, 40, true, Id.player, gameModel));
            tiledMap.createBoundaryForMap();
            sheet = new SpriteSheet("gameSheet5.png");
            for(int i = 0; i< playerSprite.length;i++){
                playerSprite[i] = new Sprite(sheet,i+1,1);
            }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void updateAndRenderModel(){
         gc.clearRect(0, 0, WIDTH, HEIGHT);
         gameModel.updateModelGame();
         tiledMap.renderMapFromData(gc);
         gameModel.renderEntities(gc, playerImage);
    }
    @Override
    public void prepareKeyEvent(Scene mainScene) {
        mainScene.setOnKeyPressed((KeyEvent event) -> {
            //currentlyActiveKeys.add(event.getCode().toString());
            for(Entity en: gameModel.getEntity()){
                if(event.getCode() == KeyCode.SPACE){
                    if(en.isJumping() == false){
                        en.setJumping(true);
                        // en.setVelY(-10);
                        System.out.println("space pressed");
                    }
                }
                if(event.getCode() == KeyCode.A){
                    en.setVelX(-5.0);
                    en.facing = 0;
                }
                if(event.getCode() == KeyCode.D){
                    en.setVelX(5.0);
                    en.facing = 1;
                }
            }
        });
        mainScene.setOnKeyReleased((KeyEvent event) -> {
            for(Entity en: gameModel.getEntity()){
                if(event.getCode() == KeyCode.SPACE){
                    en.setJumping(false);
                    // en.setVelY(10);
                    System.out.println("space realeased");
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
        });
        
    }
}
