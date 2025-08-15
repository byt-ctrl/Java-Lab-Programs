/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [13-08-25]
 | Program Definition : 5. Design a Game development framework using abstract class
 |
 |  Abstract class: GameObject
 |  Common attributes: xPosition, yPosition, sprite (image representation).
 |  Common methods: move(), isVisible(), setSprite().
 |   Abstract methods: update(), render().
 |
 |  Subclasses:
 |  PlayerCharacter: Implements update() based on player input,
 |  implements render() by drawing the player sprite.
 |  Enemy: Implements update() with attack logic,
 |  implements render() by drawing the enemy sprite.
 |  CollectibleItem: Implements update() to check for player collision,
 |  implements render() by drawing the item sprite.
 -------------------------------------------------------------*/

 
import java.util.Scanner;

// abstract class GameObject
abstract class GameObject 
{
    protected int xPosition;
    protected int yPosition;
    protected String sprite;
    protected boolean visible=true;

    public GameObject(int x,int y,String sprite) 
    {
        this.xPosition=x;
        this.yPosition=y;
        this.sprite=sprite;
    }

    public void move(int dx, int dy) 
    {
        xPosition+=dx;
        yPosition+=dy;
        System.out.println(getClass().getSimpleName() + " moved to (" + xPosition + " , " + yPosition + ")");
    }

    public boolean isVisible() 
    {
        return visible;
    }

    public void setSprite(String sprite) 
    {
        this.sprite=sprite;
    }

    public abstract void update(PlayerCharacter player); // update behavior may depend on player
    public abstract void render(); // draw the object
}

// player-character class
class PlayerCharacter extends GameObject 
{

    public PlayerCharacter(int x , int y , String sprite) 
    {
        super(x , y , sprite);
    }

    @Override
    public void update(PlayerCharacter player) 
    {
        // player-controls are handled in main loop
    }

    @Override
    public void render() 
    {
        System.out.println("Player at (" + xPosition + " , " + yPosition + ") - Sprite: " + sprite);
    }
}

// enemy-class
class Enemy extends GameObject 
{

    public Enemy(int x , int y , String sprite) 
    {
        super(x , y , sprite);
    }

    @Override
    public void update(PlayerCharacter player) 
    {
        // simple-logic : move towards player
        if (xPosition < player.xPosition) xPosition++;
        else if (xPosition > player.xPosition) xPosition--;

        if (yPosition < player.yPosition) yPosition++;
        else if (yPosition > player.yPosition) yPosition--;

        System.out.println("Enemy moved closer to player.");
    }

    @Override
    public void render() 
    {
        System.out.println("Enemy at (" + xPosition + ", " + yPosition + ") - Sprite : " + sprite);
    }
}

// collectible-item class
class CollectibleItem extends GameObject 
{

    public CollectibleItem(int x , int y , String sprite) 
    {
        super(x , y , sprite);
    }

    @Override
    public void update(PlayerCharacter player) 
    {
        // if player position matches item position , collect it
        if (xPosition == player.xPosition && yPosition == player.yPosition) 
        {
            visible=false;
            System.out.println("Collectible item collected by player !!");
        }
    }

    @Override
    public void render() 
    {
        if (visible) 
        {
            System.out.println("Item at (" + xPosition + ", " + yPosition + ") - Sprite : " + sprite);
        }
    }
}

// main class to simulate the game
class GameApp 
{
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);

        // initialize game objects
        PlayerCharacter player=new PlayerCharacter(0, 0, "@");
        Enemy enemy=new Enemy(5, 5, "E");
        CollectibleItem item=new CollectibleItem(2, 2, "*");

        boolean running=true;

        while (running) 
        {
            System.out.println("\n========= Game Menu =========");
            System.out.println("1. Move Up");
            System.out.println("2. Move Down");
            System.out.println("3. Move Left");
            System.out.println("4. Move Right");
            System.out.println("5. Update Game World");
            System.out.println("6. Render Game World");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice=scanner.nextInt();
            switch (choice) 
            {
                case 1 :
                    player.move(0,-1);
                    break;
                case 2 :
                    player.move(0,1);
                    break;
                case 3 :
                    player.move(-1,0);
                    break;
                case 4 :
                    player.move(1, 0);
                    break;
                case 5 :
                    enemy.update(player);
                    item.update(player);
                    break;
                case 6 :
                    player.render();
                    enemy.render();
                    item.render();
                    break;
                case 7 :
                    running=false;
                    System.out.println("Exiting game........./");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}

// output example

/* 

========= Game Menu =========
1. Move Up
2. Move Down
3. Move Left
4. Move Right
5. Update Game World
6. Render Game World
7. Exit
Enter your choice: 2
Player moved to (0, 1)

========= Game Menu =========
Enter your choice: 2
Player moved to (0, 2)

========= Game Menu =========
Enter your choice: 4
Player moved to (1, 2)

========= Game Menu =========
Enter your choice: 4
Player moved to (2, 2)

========= Game Menu =========
Enter your choice: 5
Enemy moved closer to player.
Collectible item collected by player!

========= Game Menu =========
Enter your choice: 6
Player at (2, 2) - Sprite: @
Enemy at (4, 4) - Sprite: E

========= Game Menu =========
Enter your choice: 1
Player moved to (2, 1)

========= Game Menu =========
Enter your choice: 5
Enemy moved closer to player.

========= Game Menu =========
Enter your choice: 6
Player at (2, 1) - Sprite: @
Enemy at (3, 3) - Sprite: E

========= Game Menu =========
Enter your choice: 1
Player moved to (2, 0)

========= Game Menu =========
Enter your choice: 5
Enemy moved closer to player.

========= Game Menu =========
Enter your choice: 6
Player at (2, 0) - Sprite: @
Enemy at (2, 2) - Sprite: E

========= Game Menu =========
Enter your choice: 5
Enemy moved closer to player.

========= Game Menu =========
Enter your choice: 6
Player at (2, 0) - Sprite: @
Enemy at (2, 1) - Sprite: E

========= Game Menu =========
Enter your choice: 5
Enemy moved closer to player.

========= Game Menu =========
Enter your choice: 6
Player at (2, 0) - Sprite: @
Enemy at (2, 0) - Sprite: E


*/