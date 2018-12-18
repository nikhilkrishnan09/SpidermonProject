package battle;
import java.util.Scanner;

public class FightSimulator
{
    public static void main (String args [])
    {
        int move;
        int attack;
        int SpidermonHealth = 10;
        int GobbyHealth = 10;
        int protectCount = 0;
        boolean screwUp = false;
        boolean protect = false;
        double rand;
        Scanner reader = new Scanner (System.in);


        System.out.println ("Hello Spidermon, you are fighting Gobby. Get ready for battle.");

        while (SpidermonHealth > 0 && GobbyHealth > 0)
        {
            System.out.print ("N Health: ");
            for (int i = 0; i < SpidermonHealth; i++)
            {
                System.out.print ("*");
            }
            System.out.println();
            delay();
            System.out.print ("B Health: ");
            for (int n = 0; n < GobbyHealth; n++)
            {
                System.out.print ("*");
            }
            System.out.println();
            delay();


            System.out.println ("1: Attack");
            System.out.println ("2: Protect");
            System.out.println ("3: Heal");
            System.out.println ("4: Run Away");
            System.out.println ("Enter the number of the move you would like to make: ");

            move = reader.nextInt();

            if (move == 1 | move == 2 | move == 3 | move == 4)
            {
                switch (move)
                {
                    case 1:
                    	delay();
                        System.out.println ("1: Punch");
                        System.out.println ("2: Stab");
                        System.out.println ("3: Web him with your Spidermon Webs");
                        System.out.println ("Enter the number of the attack you would like to do: ");

                        attack = reader.nextInt();

                        if (attack == 1 | attack == 2 | attack == 3 )
                        {
                            switch (attack)
                            {
                                case 1:
                                	delay();
                                    System.out.println ("You used Punch.");
                                    System.out.println ("Gobby took 1 damage.");
                                    GobbyHealth -= 1;
                                    break;
                                case 2:
                                	delay();
                                    System.out.println ("You used Stab.");
                                    System.out.println ("Gobby took 2 damage.");
                                    GobbyHealth -= 2;
                                    break;
                                case 3:
                                	delay();
                                    rand = Math.random();
                                    if (rand > 0 && rand <= .5) {
                                        System.out.println ("Web him with your Spidermon Webs.");
                                        System.out.println ("Gobby took 4 damage.");
                                        GobbyHealth -= 4;
                                        break;
                                    }
                                    else {
                                        System.out.println("The move failed.");
                                    }
                            }
                        }
                        else
                        {
                        	delay();
                            System.out.println ("Invalid input. The battle will now end.");
                            screwUp = true;
                            break;
                        }
                        break;

                    case 2:
                        if (protectCount == 0)
                        {
                        	delay();
                            System.out.println ("You used protect.");
                            protect = true;
                            protectCount++;
                        }
                        else if (protectCount == 1)
                        {
                        	delay();
                            System.out.println ("The move failed.");
                            protect = false;
                            protectCount = 0;
                        }
                        break;
                    case 3:
                        if (SpidermonHealth <= 8) {
                        	delay();
                            System.out.println ("You regained 2 health.");
                            SpidermonHealth += 2;
                        }
                        else if (SpidermonHealth == 9) {
                        	delay();
                            System.out.println ("You regained 1 health.");
                            SpidermonHealth ++;
                        }
                        else if (SpidermonHealth == 10) {
                        	delay();
                            System.out.println ("The move failed.");
                        }
                        break;
                    case 4:
                    	delay();
                        System.out.println ("You cannot run away.");
                        break;
                }
            }
            else
            {
            	delay();
                System.out.println ("Invalid input. The battle will now end.");
                screwUp = true;
                break;
            }

            //Gobby's moves

            if (GobbyHealth > 0) {
                if (GobbyHealth == 10) {
                    rand  = Math.random();
                    if (rand > 0 && rand < 0.5){
                        move = 1;
                    }
                    else {
                        move = 2;
                    }

                    switch (move) {
                        case 1:
                            if (protect) {
                            	delay();
                                System.out.println ("Gobby used tickle.");
                                System.out.println ("You took no damage.");
                                protect = false;
                                break;
                            }
                            else {
                            	delay();
                                SpidermonHealth--;
                                System.out.println ("Gobby used tickle.");
                                System.out.println ("You took 1 damage.");
                                break;
                            }
                        case 2:
                            if (protect) {
                            	delay();
                                System.out.println ("Gobby used torture.");
                                System.out.println ("You took no damage.");
                                protect = false;
                                break;
                            }
                            else {
                            	delay();
                                SpidermonHealth -= 3;
                                System.out.println ("Gobby used torture.");
                                System.out.println ("You took 3 damage.");
                                break;
                            }
                    }
                }
                else {
                    rand  = Math.random();
                    if (rand > 0 && rand < 0.33) {
                        move = 1;
                    }
                    else if (rand >= 0.33 && rand < 0.66) {
                        move = 2;
                    }
                    else {
                        move = 3;
                    }

                    switch (move){
                        case 1:
                            if (protect) {
                            	delay();
                                System.out.println ("Gobby used tickle.");
                                System.out.println ("Spidermon took no damage.");
                                protect = false;
                                break;
                            }
                            else {
                            	delay();
                                SpidermonHealth--;
                                System.out.println ("Gobby used tickle.");
                                System.out.println ("You took 1 damage.");
                                break;
                            }
                        case 2:
                            if (protect) {
                            	delay();
                                System.out.println ("Gobby used torture.");
                                System.out.println ("Spidermon took no damage.");
                                protect = false;
                                break;
                            }
                            else {
                            	delay();
                                SpidermonHealth -= 3;
                                System.out.println ("Gobby used torture.");
                                System.out.println ("You took 3 damage.");
                                break;
                            }
                        case 3:
                            if (GobbyHealth <= 7) {
                            	delay();
                                GobbyHealth += 3;
                                System.out.println ("Gobby used heal.");
                                System.out.println ("Gobby regained 3 HP.");
                                break;
                            }
                            else if (GobbyHealth == 8 ) {
                            	delay();
                                GobbyHealth += 2;
                                System.out.println ("Gobby used heal.");
                                System.out.println ("Gobby regained 2 HP.");
                                break;
                            }
                            else if (GobbyHealth == 9) {
                            	delay();
                                GobbyHealth += 1;
                                System.out.println ("Gobby used heal.");
                                System.out.println ("Gobby regained 1 HP.");
                                break;
                            }
                    }
                }
            }
        }

        if (SpidermonHealth > 0 && !screwUp)
        {
        	delay();
            System.out.println ("Congratulations Spidermon, you are the winner!");
        }
        else if (GobbyHealth > 0 && !screwUp)
        {
        	delay();
            System.out.println ("Better luck next time.");
        }
        else if (screwUp == true)
        {
        	delay();
            System.out.println ("You have entered an invalid number and the battle has been terminated.");
        }
    }
    
    public static void delay() {
    	try
		{
		    Thread.currentThread().sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
    	return;
    }
    
}
