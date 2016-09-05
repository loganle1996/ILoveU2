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
import Items.*;
import Map.GameMap;
import Sound.SoundHandler;
import Target.AiHouse;
import Target.HouseHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
public class GameModel{
    static Scene mainScene;
    static GraphicsContext gc;
    public static int WIDTH = 1600;
    public static int HEIGHT = 1600;
    static Image image1,image2,image3,playerImage;
    private static Stage mainStage;
    public SpriteSheet sheet,crocodileSheet, crocodileSheetFrozen;
    public Sprite crocodileSprite [] = new Sprite[6];
    public Sprite crocodileFrozen [] = new Sprite[6];
    public Sprite playerSprite [] = new Sprite[22];
    public Sprite eagleSprite [] = new Sprite[8];
    public Sprite crocodileBoss[] = new Sprite[1];

    public Image imageLeft,imageRight,iceBallImage;
    public EntityHandler entityHandler = EntityHandler.getInstance();
    public TileHandler tileHandler = TileHandler.getInstance();
    public TileCache tileCache = TileCache.getInstance();
    public BulletHandler bulletHandler = BulletHandler.getInstance();
    public HouseHandler houseHandler = HouseHandler.getInstance();
    public ItemHandler itemHandler = ItemHandler.getInstance();
    public Tile tileA;
    public Player player1 = Player.getInstance();
    private PerspectiveCamera camera;
    private Image Background;
    public Originator originator = new Originator();
    public CareTaker careTaker = new CareTaker();
    public LinkedList<Entity> copiedEntity;
    private long lastSpawnTime = 0,bulletBoxLastSpTime = 0;
    public long timePerSecond = 0;
    public int second = 0, minute = 0;

    public Label hpLabel = new Label();
    public Label fireBallLabel = new Label();
    public Label iceBallLabel = new Label();
    public Label timeLabel = new Label();
    public Label scoreLabel = new Label();

    public GameMap gameMap = GameMap.getInstance();
    public BulletCache bulletCache = BulletCache.getInstance();
    public ItemCache itemCache = ItemCache.getInstance();
    // FPS calculation
    boolean showFPS = false;
    boolean firstTick = true;
    int frames = 0;
    long startTime = 0;
    double fps = 0.0;
    AnimationTimer animation = new AnimationTimer()
    {
        @Override
        public void handle(long currentNanoTime)
        {

            if (showFPS)
               {
                   fps = calculateFPS(currentNanoTime);
               }

            if (lastSpawnTime == 0 || bulletBoxLastSpTime == 0 || timePerSecond == 0){
                lastSpawnTime = currentNanoTime;
                bulletBoxLastSpTime = currentNanoTime;
                timePerSecond = currentNanoTime;
            }
            else {
                if (((currentNanoTime - lastSpawnTime) / 1000000000.0) > 20){
                    lastSpawnTime = currentNanoTime;
                    spawnEnemy();
                }
                if (((currentNanoTime - bulletBoxLastSpTime) / 1000000000.0) > 6){
                    bulletBoxLastSpTime = currentNanoTime;
                    spawnItems();
                }
                if (((currentNanoTime - timePerSecond) / 1000000000.0) > 1){
                    second +=1;
                    if(second >= 60){
                        second = 0;
                        minute +=1;
                    }
                    timePerSecond = currentNanoTime;
                }
            }
            tickAndRenderModel(currentNanoTime);
//            gc.setFill(Color.BLACK);
//            gc.strokeLine(pointHandler.getPoints().get(0).getX(),pointHandler.getPoints().get(0).getY(),pointHandler.getPoints().get(1).getX(),pointHandler.getPoints().get(1).getY());

            camera.setTranslateX(player1.getX());
            camera.setTranslateY(player1.getY());

            timeLabel.setText("Time: "+minute +":"+ (second));
            timeLabel.setTranslateX(player1.getX());
            timeLabel.setTranslateY(player1.getY() - 210);

            scoreLabel.setText("Score: "+ player1.getScore());
            scoreLabel.setTranslateX(player1.getX());
            scoreLabel.setTranslateY(player1.getY() - 180);

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
    };

    public void setGameMap(String map) {
        gameMap.chooseMap(map);
    }

    public GameModel(){
        //By default, this game will start with map1
//        gameMap.getMapData1();
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
        tileHandler.tickTiles(currentime);
        bulletHandler.tickBullets(currentime);
        houseHandler.tickHouses();
        itemHandler.tickItems(currentime);
    }
    public void renderModelGame(GraphicsContext g){
        tileHandler.renderTiles(g);
        entityHandler.renderEntities(g,playerSprite,crocodileSprite, crocodileFrozen,crocodileBoss,eagleSprite);
        houseHandler.renderHouses(g);
        bulletHandler.renderBullets(g);
        itemHandler.renderItems(g);
    }
    public void main(String[] args) {
//        mainStage.close();

        //Create a camera
        Stage gameStage = new Stage();

        this.camera = new PerspectiveCamera(true);

        Group root = new Group();
        mainScene = new Scene(root);
        gameStage.setScene(mainScene);
        mainScene.setCamera(camera);
        mainScene.setFill(Color.BLACK);
        camera.setNearClip(0.1);
        camera.setFarClip(1500);
        camera.setTranslateX(800);
        camera.setTranslateY(800);
        camera.setTranslateZ(-800);

        // Player's HP HUD
        hpLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        fireBallLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        iceBallLabel.setTextFill(javafx.scene.paint.Color.BLACK);
        scoreLabel.setTextFill(javafx.scene.paint.Color.BLACK);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().addAll(canvas, hpLabel, fireBallLabel, iceBallLabel,timeLabel, scoreLabel);

        //Associate gc to the canvas to draw.
        gc = canvas.getGraphicsContext2D();
        //getMapAndObjects
        addObjects();
        //Get all images from all resources.
        loadGraphicsAndObjects();
        // main scene listens for keyevent
        prepareKeyEvent(mainScene);
        //run animation
        animation.start();
        gameStage.show();

        gameStage.setOnCloseRequest(e -> {
            animation.stop();
            gameMap.emptyMap();
            SoundHandler.getInstance().stopBackgroundMusic();
//            Platform.exit();
//            System.exit(0);
        });
    }
//
   private double calculateFPS(long timePassed)
   {

       // To measure framerate, we need to know:
       // How many frames have passed
       // How much time have passed

       // If this tick is the first tick of the game, do some setup
      if (firstTick)
      {
          frames = 0;
          startTime = timePassed;
          firstTick = false;
          return 0.0;
      }

      // Increment the number of frames that have passed
      frames++;

       // 0.25 -> We update the FPS count each 0.25 seconds.
       // FPS = frames per second = frames / second

       if (timePassed - startTime > 0.25 && frames > 10)
       {
           fps = (double) frames / ((timePassed - startTime) / 1000000000.0);
           startTime = timePassed;
           frames = 0;
       }

       return fps;
   }
//
    public void addObjects(){
        gameMap.addAllObjectsToGameModel();
    }
    private void loadGraphicsAndObjects()
    {
        Background = new Image("background.png");
        Image eagle, player;
        sheet = new SpriteSheet("gameSheet5.png");
        for(int i = 0; i< playerSprite.length;i++)
        {
          player = new Image("player"+i+".png");
          playerSprite[i] = new Sprite(player);
        }

        for(int i = 0; i< eagleSprite.length;i++)
        {
          eagle = new Image("eagle"+i+".png");
          eagleSprite[i] = new Sprite(eagle);
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
    public void tickAndRenderModel(long currentime){
        gc.clearRect(0-50, 0, WIDTH+50, HEIGHT+50);
        gc.drawImage(Background, 0, 0, WIDTH, HEIGHT);
        this.TickModelGame(currentime);
        this.renderModelGame(gc);

    }

    public void spawnEnemy()
    {
      if(entityHandler.getEntity().size() < 15){
          for(AiHouse aiHouse: houseHandler.getAiHouses()){
            entityHandler.addEntity(new AIenemy((int)aiHouse.getX(), (int)aiHouse.getY(), 40, 40, true, Id.Goomba, entityHandler));
            entityHandler.addEntity(new Eagle((int)aiHouse.getX()-80, (int)aiHouse.getY()-50, 40, 40, true, Id.Eagle, entityHandler));
          }
      }
    }
    public void spawnItems(){
        if(itemHandler.getItems().size() < 7){
            Random random = new Random();
            double x = (((random.nextDouble() * 1440) % 1440) + 50);
            double y = (((random.nextDouble() * 1440) % 1440) + 50);
            FireRune randomFireRune = (FireRune)itemCache.getItem("fireRune");
            AirRune randomAirRune = (AirRune) itemCache.getItem("airRune");
            WaterRune randomWaterRune = (WaterRune) itemCache.getItem("waterRune");
            EarthRune randomEarthRune = (EarthRune) itemCache.getItem("earthRune");

            Integer itemIndex = random.nextInt(4);

            switch (itemIndex)
            {
                case 0:
                    randomFireRune.setX(x);
                    randomFireRune.setY(y);
                    itemHandler.addItem(randomFireRune);
                    break;
                case 1:
                    randomAirRune.setX(x);
                    randomAirRune.setY(y);
                    itemHandler.addItem(randomAirRune);
                    break;
                case 2:
                    randomWaterRune.setX(x);
                    randomWaterRune.setY(y);
                    itemHandler.addItem(randomWaterRune);
                    break;
                case 3:
                    randomEarthRune.setX(x);
                    randomEarthRune.setY(y);
                    itemHandler.addItem(randomEarthRune);
                    break;

            }
        }

//        System.out.println("X is " + x + "" + "Y is " + y);

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
                            if (en.isJumping() == false)
                            {
                                en.setJumping(true);
                                if (en.isSwimming == false){
                                    en.setVelY(-15);
                                }
                                else{
                                    en.setVelY(-10);
                                }

                                SoundHandler.getInstance().playSound("jump");
                            }

                            break;
                        case K:
                            if (en.shootable == true)
                            {
                                en.shootIceBall(gc);

                            }
                            break;

                        case J:
                            if (en.shootable == true)
                            {
                                en.shootFireBall(gc);
                                en.isShooting = true;
                                en.setShootable(false);
                            }
                            break;

                        case L:
                            if (en.shootable == true)
                            {
                                en.placeSlowSpirit(gc);
                                en.setShootable(false);
                            }
                            break;

                        case A:
                                en.moveLeft();
                            break;

                        case D:
                                en.moveRight();
                            break;

                        case MINUS:
                            if (showFPS)
                            {
                                showFPS = false;
                            }
                            else
                            {
                                showFPS = true;
                            }
                            break;
                        case W:
//                            en.swimUp();
                            if(en.isSwimming){
                                if(en.isSwimmingUp == false){
                                    en.isSwimmingUp = true;
                                    en.setVelY(-3);
                                }
                            }
                            break;
                        case S:
//                            en.swimDown();
                            if(en.isSwimming){
                                if(en.isSwimmingDown == false){
                                    en.isSwimmingDown = true;
                                    en.setVelY(3);
                                }
                            }
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
                        case W:
                            en.notSwimUpDown();
                            break;
                        case S:
                            en.notSwimUpDown();
                            break;
                        case J:
                            en.isShooting = false;
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
    public AnimationTimer getAnimationTimer(){
        return animation;
    }
}
