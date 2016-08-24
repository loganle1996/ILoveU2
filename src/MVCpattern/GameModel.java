/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCpattern;

import Bullet.BulletCache;
import Bullet.BulletHandler;

import Entity.*;
import GameState.CareTaker;
import GameState.Originator;
import GraphicsforAnimation.Sprite;
import GraphicsforAnimation.SpriteSheet;
import Items.BulletBox;
import Items.ItemCache;
import Items.ItemHandler;
import Map.GameMap;
import Sound.SoundHandler;
import Target.AiHouse;
import Target.HouseHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import tile.Tile;
import tile.TileCache;
import tile.TileHandler;

import java.util.LinkedList;
import java.util.Random;


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
    public Sprite playerSprite [] = new Sprite[8];
    public Sprite eaglSprite [] = new Sprite[8];
    public Sprite crocodileSprite [] = new Sprite[6];
    public Sprite crocodileFrozen [] = new Sprite[6];
    public Sprite crocodileBoss[] = new Sprite[1];
    public Image imageLeft,imageRight,iceBallImage;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public TileCache tileCache = TileCache.getInstance();
    public BulletHandler bulletHandler = BulletHandler.getInstance();
    public HouseHandler houseHandler = HouseHandler.getInstance();
    public ItemHandler itemHandler = ItemHandler.getInstance();
    public Player player1 = Player.getInstance();
    private PerspectiveCamera camera;
    private Image Background;
    public Originator originator = new Originator();
    public CareTaker careTaker = new CareTaker();
    public LinkedList<Entity> copiedEntity;
    private long lastSpawnTime = 0,bulletBoxLastSpTime = 0;

    public Label hpLabel = new Label();
    public Label fireBallLabel = new Label();
    public Label iceBallLabel = new Label();

    public static GameMap gameMap;
    public BulletCache bulletCache = BulletCache.getInstance();
    public ItemCache itemCache = ItemCache.getInstance();

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
        itemHandler.tickItems(currentime);
    }
    public void renderModelGame(GraphicsContext g){
        entityHandler.renderEntities(g,playerSprite,crocodileSprite, crocodileFrozen,crocodileBoss,eaglSprite);
        tileHandler.renderTiles(g);
        houseHandler.renderHouses(g);
        bulletHandler.renderBullets(g);
        itemHandler.renderItems(g);
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
        root.getChildren().addAll(canvas, hpLabel, fireBallLabel, iceBallLabel);


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
                if (lastSpawnTime == 0 || bulletBoxLastSpTime == 0){
                    lastSpawnTime = currentNanoTime;
                    bulletBoxLastSpTime = currentNanoTime;
                }
                else {
                    if (((currentNanoTime - lastSpawnTime) / 1000000000.0) > 5){
                        lastSpawnTime = currentNanoTime;
                        spawnEnemy();
                    }
                    if (((currentNanoTime - bulletBoxLastSpTime) / 1000000000.0) > 16){
                        bulletBoxLastSpTime = currentNanoTime;
                        spawnItems();
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

//        sheet = new SpriteSheet("gameSheet6.png");
        Image player;
        for(int i = 0; i< playerSprite.length;i++){
            player = new Image("player"+i+".png");
            playerSprite[i] = new Sprite(player);
        }
        Image eagle;
        for(int i = 0; i< eaglSprite.length;i++){
            eagle = new Image("eagle"+i+".png");
            eaglSprite[i] = new Sprite(eagle);
        }
        crocodileSheet = new SpriteSheet("crocodileGameSheet.png");

        for(int i = 0; i< crocodileSprite.length;i++){
            crocodileSprite[i] = new Sprite(crocodileSheet, i+1, 1);
        }

        crocodileSheetFrozen = new SpriteSheet("crocodileGameSheetFrozen.png");
        for(int i = 0; i< crocodileSprite.length;i++) {
            crocodileFrozen[i] = new Sprite(crocodileSheetFrozen, i + 1, 1);
        }
        
        Image bossImage0 = new Image("boss.png");
        crocodileBoss[0] = new Sprite(bossImage0);
        

        bulletCache.loadBulletCache();
        itemCache.loadCache();

        SoundHandler.getInstance().loadAllSounds();
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
    public void spawnItems(){
        Random random = new Random();
        double x = (((random.nextDouble() * 1550) % 1550) + 50);
        double y = (((random.nextDouble() * 1550) % 1550) + 50);
        BulletBox randomBulletBox = (BulletBox)itemCache.getItem("bulletBox");
        System.out.println("X is " + x + "" + "Y is " + y);
        randomBulletBox.setX(x);
        randomBulletBox.setY(y);
        itemHandler.addItem(randomBulletBox);
    }
    public void prepareKeyEvent(Scene mainScene) {

        mainScene.setOnKeyPressed(event -> {
            //currentlyActiveKeys.add(event.getCode().toString());
            copiedEntity = new LinkedList<Entity>(entityHandler.getEntity());
            for(Entity en: copiedEntity){
                if(en.getId() == Id.player){

                    // TODO (Dzung Le): Sound

                    switch (event.getCode()) {
                        case SPACE:
                            if (en.isJumping() == false){
                                en.setJumping(true);
                                en.setVelY(-15);
                                SoundHandler.getInstance().playSound("jump");
                            }
                            break;
                        case I:
                            if (en.shootable == true)
                            {
                                en.shootIceBall(gc);

                            }
                            break;

                        case R:
                            if (en.shootable == true)
                            {
                                en.shootFireBall(gc);

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

                            break;

                        case D:
                            en.setIsMovingRight(true);

                            break;
                    }
                }

            }
        });
        mainScene.setOnKeyReleased(event -> {
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
        });
    }

    public void display(){
        String[] args = null;
        main(args);
    }
}
