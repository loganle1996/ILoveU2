# MultiRunningWorld-game
<img src="https://i.imgur.com/2PVI6Jn.gif" width=600><br>

Game Name: ​MultiRunningWorld (Arcane Arena) 
A 2D Arena multiplayer game. Each player controls a character in a map. By default, characters can only use fists, but they can pick up scrolls to gain powerful spells. Players attempt to eliminate the other by using these scrolls. Maps will be designed with scrolls, platforms and pitfalls.
Scrolls range from basic attacks such as Fireballs, to scrolls that grant effects such as Galeforce (pushes the enemy), Frost Nova (creates a wall that blocks enemy movement) and Storm Fist (increases player’s movement speed, makes default fist do more damage).

*****************************************
User Stories:
*****************************************
● As a player, I want to see how much HP and Mana I have left so I can adjust the way I play.
● As a player, I want to see what scrolls are there on the board so I can adjust my strategies.
● As a player, I would like my game to be smooth and nice so that I don’t get frustrated.
● As a player, I would like my game to be low on requirements so I can play it on bad computers.
● As a player, I would like my game to not disconnect when I am playing a match so I can enjoy a smooth experience.
● As a player, I want to have a balanced game so I can have fun.
● As a player, I want the game to look good so I can enjoy the aesthetics of the
game.
● As a player, I want diversity in scrolls so I don’t get bored.
● As a player, I want constant updates to the game so that it always feel fresh.
● As a player, I want to easily install and uninstall the game.

*****************************************
DESIGN PATTERNS
*****************************************
<img src="https://i.imgur.com/Mzobdfe.jpg" width=600><br>
1.Prototype Pattern
Where to use :
-This pattern has been used to clone different sorts of wall objects and then use a map which is a two dimensional array to modify the location of the objects using setX() and setY() methods.
How to implement:
-Create an object tileCache which has a hashtable map.
- this tileCache calls the loadCache method which assigns all the key of different sorts of wall to their corresponding objects.
- Use getTile method to get the correct walls which are expected and then using 2-dimensional map and setX(),setY() methods to modify their location.
- add all different types of wall objects to a Linkedlist called tile instantiated in gameModel.
Why use Prototype:
-Instead of creating these objects from scratch in each element of 2 dimensional map when looping, we could get a correct object quickly using the getTile method with the corresponding key type put in the method’s parameter. This can save lots of time and more convenient.

2.Singleton Pattern
Where to use
-This design pattern is applied to create TileCache class called by the loadGraphicsAndObjects method in the GameModel class.
-This can also be applied for some sorts of objects’s handlers such as EntityHandler, BulletHandler,TileHandlers and the player.

How to implement
- Create a private static TileCache object named tileCache
- Make the constructor of TileCache private so it wont be able to be instatiated
- Create a public static method which returns an object created in step 1.
Why use singleton pattern object
Because there is only one tileCache containing all different sorts of tile that is needed in the game. Singleton can benefit in avoiding the confusion of many tileCaches.


3.MVC pattern
MVC pattern has been used for controlling the whole game.
How to implement
-Create a gameView class for displaying all components of gamemodel
-Create a gameModel containing all linkedlist handling objects,Stage,scene and so on. These are components making the game.
-Create a controller which takes gameModel and gameView as parameters to manipulate the game.
Why use it
This separates the game logic and graphic of the games. This makes things much easier to maintain.

4 Other design patterns ( Decorator pattern and Strategy pattern are also applied)
