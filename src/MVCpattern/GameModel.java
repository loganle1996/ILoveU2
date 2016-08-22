/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCpattern;

import Bullet.BulletCache;
import Bullet.BulletHandler;
import Entity.AIenemy;
import Entity.Entity;
import Entity.EntityHandler;
import Entity.Id;
import Entity.Player;
import GameState.CareTaker;
import GameState.Originator;
import GraphicsforAnimation.Sprite;
import GraphicsforAnimation.SpriteSheet;
import Sound.SoundHandler;
import Target.AiHouse;
import Target.HouseHandler;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Map.GameMap;
import tile.Tile;
import tile.TileCache;
import tile.TileHandler;

import java.util.LinkedList;
import javax.swing.plaf.synth.SynthRootPaneUI;


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
    public SpriteSheet sheet,crocodileSheet, crocodileSheetFrozen;
    public Sprite playerSprite [] = new Sprite[6];
    public Sprite crocodileSprite [] = new Sprite[6];
    public Sprite crocodileFrozen [] = new Sprite[6];

    public Image imageLeft,imageRight,iceBallImage;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public TileCache tileCache = TileCache.getInstance();
    public BulletHandler bulletHandler = BulletHandler.getInstance();
    public HouseHandler houseHandler = HouseHandler.getInstance();
    public Player player1 = Player.getInstance();
    private PerspectiveCamera camera;
    private Image Background;
    public Originator originator = new Originator();
    public CareTaker careTaker = new CareTaker();
    public LinkedList<Entity> copiedEntity;
    private long lastSpawnTime = 0;
    private SoundHandler soundHandler = new SoundHandler();

    public Label hpLabel = new Label();
    public Label fireBallLabel = new Label();
    public Label iceBallLabel = new Label();

    public static GameMap gameMap;
    public BulletCache bulletCache = BulletCache.getInstance();

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

    public void TickModelGame(long currentime){
        entityHandler.tickEntities(currentime);
        tileHandler.tickTiles();
        bulletHandler.tickBullets(currentime);
        houseHandler.tickHouses();
    }
    public void renderModelGame(GraphicsContext g){
        entityHandler.renderEntities(g,playerSprite,crocodileSprite, crocodileFrozen);
        tileHandler.renderTiles(g);
        houseHandler.renderHouses(gc);
        bulletHandler.renderBullets(gc);
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

        gameMap = GameMap.getInstance();

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
        camera.setTranslateZ(-800);

        // Player's HP HUD
        hpLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        fireBallLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        iceBallLabel.setTextFill(javafx.scene.paint.Color.WHITE);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().addAll(canvas, camera, hpLabel, fireBallLabel, iceBallLabel);
//        root.getChildren().add(camera);
//        root.getChildren().add(hpLabel);
//        root.getChildren().add(fireBallLabel);

            
        //Associate gc to the canvas to draw.
        gc = canvas.getGraphicsContext2D();
        //getMapAndObjects
        getMapAndObjects();
        //Get all images from all resources.
        loadGraphicsAndObjects();
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
                tickAndRenderModel(currentNanoTime);

                camera.setTranslateX(player1.getX());
                camera.setTranslateY(player1.getY());
                hpLabel.setText("HP:" + player1.getHp());
                hpLabel.setTranslateX(player1.getX() - 350);
                hpLabel.setTranslateY(player1.getY() - 210);
                fireBallLabel.setText("Fireballs left: " + player1.getNumberFireball());
                fireBallLabel.setTranslateX(player1.getX() + 220);
                fireBallLabel.setTranslateY(player1.getY() - 210);
                
                iceBallLabel.setText("Ice Shards left: " + player1.getNumberIceBall());
                iceBallLabel.setTranslateX(player1.getX() + 220);
                iceBallLabel.setTranslateY(player1.getY() - 190);


            }
        }.start();
        gameStage.show();

        gameStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

    }
    public void getMapAndObjects(){
        gameMap.getMapData1();
        gameMap.addAllObjectsToGameModel();
    }
    private void loadGraphicsAndObjects()
    {
        Background = new Image("background.png");
        String[] soundArray = new String[] {"fireball", "iceball", "footstep"};

        for (String sound: soundArray)
        {
        soundHandler.loadSound(sound);
        }

        sheet = new SpriteSheet("gameSheet5.png");
        for(int i = 0; i< playerSprite.length;i++){
            playerSprite[i] = new Sprite(sheet,i+1,1);
        }

        crocodileSheet = new SpriteSheet("crocodileGameSheet.png");

        for(int i = 0; i< crocodileSprite.length;i++){
            crocodileSprite[i] = new Sprite(crocodileSheet, i+1, 1);
        }

        crocodileSheetFrozen = new SpriteSheet("crocodileGameSheetFrozen.png");
        for(int i = 0; i< crocodileSprite.length;i++) {
            crocodileFrozen[i] = new Sprite(crocodileSheetFrozen, i + 1, 1);
        }

            bulletCache.loadBulletCache();
    }
    public static void main(String[] args) {
        launch(args);
        mainStage = new Stage();
    }
    public void tickAndRenderModel(long currentime){
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(Background, 0, 0, WIDTH, HEIGHT);
        this.TickModelGame(currentime);
        this.renderModelGame(gc);
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
            // To Do (Dzung Le): Find a way to handle multiple key inputs
            @Override
            public void handle(KeyEvent event)
            {
                //currentlyActiveKeys.add(event.getCode().toString());
                copiedEntity = new LinkedList<Entity>(entityHandler.getEntity());
                for(Entity en: copiedEntity){
                    if(en.getId() == Id.player){

                        // TODO (Dzung Le): Add a key buffer

                        switch (event.getCode()) {
                            case SPACE:
                                if (en.isJumping() == false){
                                    en.setJumping(true);
                                    en.setVelY(-15);
                                }
                                break;
                            case I:
                                if (en.shootable == true)
                                {
                                    en.shootIceBall(gc);
                                    soundHandler.playSound("iceball");
                                }
                                break;

                            case R:
                                if (en.shootable == true)
                                {
                                    en.shootFireBall(gc);
                                    soundHandler.playSound("fireball");
                                    en.setShootable(false);
                                }
                                break;
                                
                            case T:
                                if (en.shootable == true)
                                {
                                    en.placeSlowSpirit(gc);
                                    en.setShootable(false);
                                }
                                break;
                                
                            case A:
                                en.setIsMovingLeft(true);
                                soundHandler.playSound("footstep");
                                break;
                            case D:
                                en.setIsMovingRight(true);
                                soundHandler.playSound("footstep");
                                break;
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
                        switch (event.getCode()) {
                            case A:
                                en.setIsMovingLeft(false);
                                break;
                            case D:
                                en.setIsMovingRight(false);
                                break;
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
