# Knight OnConsole :)
Small CLI implementation of KnightOnline game.

Features
-----
- You can save and load game
- You can decide to fight or run away

Game
------
### Story
This game is inspired from KnightOnline game, which is the best RPG game I've played so far :)

### Characters
There are three types of character in the game:

- Warrior: Warrior's defense and health is the best. He is a tank character. But it has less damage than the others.
- Mage: He is the magician. He has the best attack but the health and defense is the worst. Whenever you fight, make sure you finish it quickly :)
- Rogue: He is in the middle between Mage and Warrior. He has a decent defense and HP as well as attack. 

### Mobs
There are 80+ mobs in the game and all of them fetched from the original game. You can start with the easy one to gain experience. 

### Game modes

There are two options in the game:
- Free farm: With free farm, you can discover the map and kill some mobs to gain experience. Map may look small but it has more than 80 mobs in it :)
- Battle: Here is the fun part: You'll fight with other Characters, not mobs. So you should be careful :)

In both options, you'll gain experience. Once you get enough experience, you'll level up and get stronger. 

### Fight

Fights are automated so you're not allowed to fight manually. Before fights, your skills and opponent's will be shown, so you can decide if you want to fight or run away. 

And it has some chance factor in it :) So you might not always hit the best damage and win the fight.

Run Game
------
### Option 1: Build & Execute JAR
You can first build the JAR and then execute it. Just follow the commands:

      ./mvnw clean install
      cd /target
      java -jar knightcli-1.0-SNAPSHOT.jar

### Option 2: Run directly
If you don't want to waste your time with building etc., just run the command below under root folder:

        ./mvnw clean install exec:java
        
TODO
----
- Add MP into stats
- Add money, inventory and buying stuff
- Beautify outputs