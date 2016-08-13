/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCpattern;

import Bullet.BulletHandler;
import Entity.AIenemy;
import Entity.Entity;
import Entity.EntityHandler;
import Entity.Id;
import GameState.CareTaker;
import GameState.Originator;
import GraphicsforAnimation.Sprite;
import GraphicsforAnimation.SpriteSheet;
import Target.AiHouse;
import Target.HouseHandler;
import java.util.LinkedList;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tile.Tile;
import tile.TileCache;

import tile.GameMap;
import tile.TileHandler;



/**
 *
 * @author owne
 */
public class GameModel extends Application {
    static Scene mainScene;
    static GraphicsContext gc;
    public static int WIDTH = 1600;
    public static int HEIGHT = 1600;
    static Image image1,image2,image3,playerImage;
    private static Stage mainStage;
    public static SpriteSheet sheet,crocodileSheet;
    public static Sprite playerSprite [] = new Sprite[6];
    public static Sprite crocodileSprite [] = new Sprite[6];
    public static GameMap gameMap = new GameMap();
    public Image imageLeft,imageRight;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public TileCache tileCache = TileCache.getInstance();
    public BulletHandler bulletHandler = BulletHandler.getInstance();
    public HouseHandler houseHandler = HouseHandler.getInstance();
    public Entity player1;
    private PerspectiveCamera camera;
    private Image Background;
    public Originator originator = new Originator();
    public CareTaker careTaker = new CareTaker();
    public LinkedList<Entity> copiedEntity;
    private long lastSpawnTime = 0;
    public GameModel(){
        
    }

    public void addTile(Tile ti){
        tileHandler.addTile(ti);
    }

    //Accessors and mutators
    public EntityHandler getEntityHandler() {
        return entityHandler;
    }

    public void setEntityHandler(EntityHandler entityHandler) {
        this.entityHandler = entityHandler;
    }

    public TileHandler getTileHandler() {
        return tileHandler;
    }

    public void setTileHandler(TileHandler tileHandler) {
        this.tileHandler = tileHandler;
    }

    public HouseHandler getHouseHandler() {
        return houseHandler;
    }

    public void setHouseHandler(HouseHandler houseHandler) {
        this.houseHandler = houseHandler;
    }
    
    public void TickModelGame(){
        entityHandler.tickEntities();
        tileHandler.tickTiles();
        bulletHandler.tickBullets();
        houseHandler.tickHouses();
    }
    public void renderModelGame(GraphicsContext g){
        entityHandler.renderEntities(g,playerSprite,crocodileSprite);
        tileHandler.renderTiles(g);
        houseHandler.renderHouses(gc);
    }
    public void renderBulletOfPlayer(Image imageleft,Image imageRight){
        bulletHandler.renderBullets(gc, imageleft, imageRight);
    }

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        mainScene = new Scene(root);

        Platform.setImplicitExit(false);
        mainStage.setScene(mainScene);
        mainStage.setTitle("Arcane Arena");
        mainStage.show();

        mainStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

    }

    @FXML
    private void loadGame(ActionEvent event) throws Exception {

        mainStage.close();

        //Create a camera
        Stage gameStage = new Stage();

        this.camera = new PerspectiveCamera(true);

        Group root = new Group();
        mainScene = new Scene(root);
        gameStage.setScene(mainScene);
        mainScene.setCamera(camera);
        camera.setNearClip(0.1);
        camera.setFarClip(1500);
        camera.setTranslateX(800);
        camera.setTranslateY(800);
        camera.setTranslateZ(-950);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        root.getChildren().add(camera);
        //Associate gc to the canvas to draw.
        gc = canvas.getGraphicsContext2D();
        //Get all images from all resources.
        loadGraphicsAndObjects();
        player1 = gameMap.returnPlayer1();
        // main scene listens for keyevent
        prepareKeyEvent(mainScene);

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                if (lastSpawnTime == 0){
                    lastSpawnTime = currentNanoTime;
                }
                else {
                    if (((currentNanoTime - lastSpawnTime) / 1000000000.0) > 5){
                        lastSpawnTime = currentNanoTime;
                        spawnEnemy();
                    }
                }

//                double x = 232 + 128 * Math.cos(t);
//                double y = 232 + 128 * Math.sin(t);
                tickAndRenderModel();
                
                camera.setTranslateX(player1.getX());
                camera.setTranslateY(player1.getY());


            }
        }.start();
        gameStage.show();
        
        gameStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        
    }
    private void loadGraphicsAndObjects()
    {
            Background = new Image("background.png");
            imageLeft = new Image("fireball.png");
            imageRight = new Image("fireball.png");
            gameMap.getMapData1();
            tileCache.loadCache(this);
            sheet = new SpriteSheet("gameSheet5.png");
            for(int i = 0; i< playerSprite.length;i++){
                playerSprite[i] = new Sprite(sheet,i+1,1);
            }
            crocodileSheet = new SpriteSheet("crocodileGameSheet.png");
            for(int i = 0; i< crocodileSprite.length;i++){
                crocodileSprite[i] = new Sprite(crocodileSheet,i+1,1);
            }
            gameMap.addAllObjectsToGameModel(this,tileCache,gc);
    }
    public static void main(String[] args) {
        launch(args);
        mainStage = new Stage();
    }
    public void tickAndRenderModel(){
         gc.clearRect(0, 0, WIDTH, HEIGHT);
         gc.drawImage(Background, 0, 0, WIDTH, HEIGHT);
         this.TickModelGame();
         this.renderModelGame(gc);
         this.renderBulletOfPlayer(imageLeft, imageRight);
    }
    
    public void spawnEnemy()
    {
        for(AiHouse aiHouse: houseHandler.getAiHouses()){
            entityHandler.addEntity(new AIenemy((int)aiHouse.getX(), (int)aiHouse.getY(), 40, 40, true, Id.Goomba, entityHandler));
        }
    }
    public void prepareKeyEvent(Scene mainScene) {
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                //currentlyActiveKeys.add(event.getCode().toString());
                copiedEntity = new LinkedList<Entity>(entityHandler.getEntity());
                for(Entity en: copiedEntity){
                    if(en.getId() == Id.player){
                        if(event.getCode() == KeyCode.SPACE){
                            if(en.isJumping() == false){
                                en.setJumping(true);
                                en.setVelY(-15);
                            }
                        }
//                        if(event.getCode() == KeyCode.H){
//                            originator.setEntityState((EntityHandler) entityHandler.clone());
//                            originator.setBulletState((BulletHandler) bulletHandler.clone());
//                            originator.setTileState((TileHandler) tileHandler.clone());
//                            careTaker.add(originator.saveStateMemento());
//                            System.out.println("saving successfully");
//                            
//                        }
//                        if(event.getCode() == KeyCode.J){
//                            originator.getStateFromMemento(careTaker.get());
//                            entityHandler.editInstance(originator.getEntityState());
//                            bulletHandler.editInstance(originator.getBulletState());                            
//                            tileHandler.editInstance(originator.getTileState());
//                            System.out.println("undo succesfully");
//                        }
                        if(event.getCode() == KeyCode.R){
                            en.shootFireBall(gc);
                        }
                            if(event.getCode() == KeyCode.A){
                              en.setIsMovingLeft(true);
                            }
                            if(event.getCode() == KeyCode.D){
                                en.setIsMovingRight(true);
                            }
                    }

                }
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                copiedEntity = new LinkedList<Entity>(entityHandler.getEntity());
                for(Entity en: copiedEntity){   
                    if(en.getId() == Id.player){
                        if(event.getCode() == KeyCode.SPACE){
                        }
                        if(event.getCode() == KeyCode.A){
                            en.setIsMovingLeft(false);
                        }
                        if(event.getCode() == KeyCode.D){
                            en.setIsMovingRight(false);
                        }
                    }
                }
            }
        });      
    }

    public void display(){
        String[] args = null;
        main(args);
    }
}
