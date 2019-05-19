Program name is Minesweeper. It is a small-console based minesweeper game.

The main way to running this program will be through a console, and compiling should be done through the console or terminal as well.

You could throw the two files in an IDE too, if that way is better. Make a new project in any IDE, place 
Minesweeper.java and Grid.java in the same directory and run Minesweeper.java's main. Though to be aware, you may
need to add package statements and modify your configuration to run the main, depending on your IDE.

I would personally just make a new project, create two java files called Minesweeper.java and Grid.java, then just copy
the content of my Minesweeper.java into the IDE's Minesweeper.java, and do the same for Grid.java. That should solve any configuration
issues that IDEs usually come with.

Three main files are included:
    README.txt
    Minesweeper.java
    Grid.java

Ten screenshots are included:
    [1]HowToCompile.png
    [2]HowToMakeANewGame.png
    [3]HowTheGameLooksRunning.png
    [4]OpenCommandPart1.png
    [5]OpenCommandPart2.png
    [6]MarkCommandPart1.png
    [7]MarkCommandPart2.png
    [8]QuitCommand.png
    [9]UnixTimeshareExample1.png
    [10]UnixTimeshareExample2.png

To compile:
    javac Minesweeper.java Grid.java

To run:
    java Minesweeper

How to play (example):
    Enter "o"
    Enter 5
    Enter 6

    This will "open" the spot at row 5 and column 6. If it's a bomb, you lose. If it's safe, then it the grid will change to reflect that.

    Enter "m"
    Enter 3
    Enter 2
    
    This will "mark" the spot that you think is a bomb.

    Enter "q"

    This will quit the game.

To win, mark all the spots that have a bomb and open everything that isn't a bomb. Else, you lose.
