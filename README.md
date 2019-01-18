# MKS21X-FinalProject

Development Log

1/3/19 - We did not begin the actual coding today; however, we did make the final project repo and one of us was added as a collaborator. We also changed our project name to "Alice in Asylum" to more accurately describe the story we had in mind for our game.

1/4/19 - We did some research on terminal games to get an idea of how these types of games should be organized.  We altered some class names to reflect our changed backstory in our UML diagrams and began creating separate java files for all our classes.  We created the character designs for our icons as well.

1/6/19 - We made all the class files we believe our game would need (this may change later based on necessity).  We also started coding the framework for the Player, Character, RegRoom, Room, and Map class.  Helper functions were also created to aid with the placing of rooms in the Map class.

1/7/19 - We analyzed the demos that Mr. K went over in class. Using that as a basis, we were able to truly start the coding for the Maps class (makeRoom, placeRoom, makeString, and two putString).  Using a newly created Driver file, we tested to see if what we had in those functions were operating properly. We also started the Character class code, including its methods.

<<<<<<< HEAD
<<<<<<< HEAD
1/9/19 - We continued to work on the Character class, and started the Player, Item, and Alice classes. Deleted wand and cloak classes as they seemed unnessecary. Fixed a typo in weapon class. Having a little trouble with Player class, will ask tomorrow.

1/9/19[in class] - We discussed changes we made last night, changed the constuctor of character class and player class to include super() in the constructor properly. Slightly edited usePotion and added arraylist import to player. Starting to work on addtoinventory method.
=======
<<<<<<< HEAD
1/8/19 - We continued to work on the Character class, and continued on to the Player, Item, and Monster class code.  We also changed our approach for the Map.  Instead of printing directly into the terminal, we used a 2D array of Strings instead.  One room has been created and tested (Room, RegRoom, Maps, Driver, etc has been altered accordingly as well).
=======
1/9/19 - We continued to work on the Character class, and started the Player, Item, and Alice classes. Deleted wand and cloak classes as they seemed unnessecary. Fixed a typo in weapon class. Having a little trouble with Player class, will ask tomorrow.  We created the Intersection (may be deleted later) and Path class to aid in the creation of the map.  We also added more rooms of different dimensions to test out our code.
>>>>>>> 3cb8f0c293d81bdf150d47d5e9e6f63a081f9055
=======
1/9/19 - We continued to work on the Character class, and started the Player, Item, and Alice classes. Deleted wand and cloak classes as they seemed unnessecary. Fixed a typo in weapon class. Having a little trouble with Player class, will ask tomorrow.  We created the Intersection (may be deleted later) and Path class to aid in the creation of the map.  We also added more rooms of different dimensions to test out our code. We also changed our approach for the Map.  Instead of printing directly into the terminal, we used a 2D array of Strings instead.  One room has been created and tested (Room, RegRoom, Maps, Driver, etc has been altered accordingly as well).
>>>>>>> 8fab3dbc64d6828d197a13daa64a24ee00c54277

<<<<<<< HEAD
1/10/19 - We added a lot more methods to the player class, especially having to do with the inventory. Made the constructor for the Alice class as well.
>>>>>>> 1b420aae867606d877a19f6dc6e30a5d07a02bac
=======
1/10/19 - We added a lot more methods to the player class, especially having to do with the inventory. Made the constructor for the Alice class as well.  We also started to experiment with the Screen class.  Instead of working directly on the terminal, we are attempting to work on a separate screen instead.  We also started considering how to code the paths for our map.

1/11/19 - We managed to get the screen working/printing correctly.  Most of the terminal methods were replaced with their corresponding screen ones.  We also changed putString and placeRoom.

1/12/19 - We added the code for the paths and thus the map is almost finished (only coloring left).  After coding the map, we turned our focus to the different modes and the keys.  The first mode would be the start screen (title & directions).  The second mode would be the backstory.  The third mode would be the map and the next six modes would be enlarged versions of the rooms on the map.  This is where the game would be played.  The code has been started for all of these modes.
<<<<<<< HEAD

>>>>>>> bd10f5abb39c363dfef82ec22c967fa07e3b265e
=======
>>>>>>> 8fab3dbc64d6828d197a13daa64a24ee00c54277

<<<<<<< HEAD
1/16/19 - We did not code too much today.  However, we did begin discussing how we planned on creating the monsters.  Our monsters were glitching and thus a solution to this problem was discussed.

1/17/19 - Unfortunately, all we got started on today were the entrances.  We were attempting to place them in the right locations so that players could access the next rooms.
=======
1/14/19 - We completely changed our approach for our display.  Instead of modes for our different rooms, we used 2D arrays and reprinted the array with the new room every time we entered the next room. created lizards and frogs that display. The character can move around the room and hover over them to make them disappear. Can't currently just switch from room to room, but can access the map using backspace anytime.

HOW TO PLAY:
100 * 40 screen
java -cp lanterna.jar:. Driver

Press Enter
Press Tab
Press Enter to access first room
Press Backspace to go back to map at anytime
Use arrow keys to move character around
Don't go outside the walls, the game will crash
Hover over monsters to kill them
Demo Over
>>>>>>> 52b37dd9d5f6a79b044c0b13e9f9b7922125834f
