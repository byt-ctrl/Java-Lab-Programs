/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [20-08-25]
 | Program Definition : 6. Write a program using an interface and core java options to design a utility like “scoreboard” (example cricbuzz mobile app). 
 |   It is having the method to create teams, Create team members, playGame, displayScore, updateScore, individual score, details score and Winnerteam etc. 
 |  Scoreboard should fit for multiple sports (i.e. Cricket, Football, Badminton etc).
 |
 |  Note :- for playGame options you can use random function to generate the points. Sample given below.
 |
 |  import java.util.Random;
 |  Random r= new Random();
 |  int r1 = r.nextInt(1000); // Generate a random integer number from 0-999
 |
 |  We have to make CLI Mode
 |
 -------------------------------------------------------------*/

// to run program write  'java Main' in terminal after compilation
// NOTE :- at some part of code i have taken the help of AI

import java.util.*;

// ====================== Core Classes ======================
class Player 
{
    String name;
    int score;
    int fouls;
    boolean hasGroup; // for pool - indicates if player has been assigned a group
    int groupType; // 0=Solids , 1=Stripes (for pool)
    
    Player(String name) 
    {
        this.name=name;
        this.score=0;
        this.fouls=0;
    }
}

class Team {
    String name;
    List<Player> players = new ArrayList<>();
    int totalScore=0;
    int possessionTime=0; // in minutes (for football)
    int corners=0;
    int yellowCards=0;
    int redCards=0;
    boolean hasPossession; // for football
    
    Team(String name) 
    {
        this.name=name;
        this.hasPossession=false;
    }

    void addPlayer(String playerName) 
    {
        players.add(new Player(playerName));
    }
}

// ====================== Game Interface ======================
interface Game {
    void createTeams();
    void createTeamMembers();
    void playGame();
    void displayScore();
    void individualScore();
    void detailScore();
    String getWinnerTeam();
    String getGameName();
}

// ====================== Football Implementation ======================
class FootballGame implements Game {
    private Team[] teams = new Team[2];
    private int currentMinute = 0;
    private boolean gameEnded = false;
    private Random random = new Random();
    private List<String> commentaryLog = new ArrayList<>();
    
    // Football-specific tracking
    private int[] shotsOnTarget = {0, 0};
    private int[] shotsOffTarget = {0, 0};
    private int[] corners = {0, 0};
    private int[] fouls = {0, 0};
    private int[] yellowCards = {0, 0};
    private int[] redCards = {0, 0};
    private int possessionTeam = 0; // 0 = team0, 1 = team1
    private int possessionTime = 0;

    @Override
    public void createTeams() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter name for Home Team: ");
        teams[0] = new Team(sc.nextLine());
        System.out.print("Enter name for Away Team: ");
        teams[1] = new Team(sc.nextLine());
    }

    @Override
    public void createTeamMembers() {
        for (int i = 0; i < 2; i++) {
            System.out.println("\nEnter 3 players for " + teams[i].name + ":");
            for (int j = 0; j < 3; j++) {
                System.out.print("Player " + (j+1) + ": ");
                teams[i].addPlayer(new Scanner(System.in).nextLine());
            }
        }
        // Initial possession
        possessionTeam = random.nextInt(2);
        teams[possessionTeam].hasPossession = true;
    }

    @Override
    public void playGame() {
        System.out.println("\n=== FOOTBALL MATCH SIMULATION STARTED ===");
        System.out.println("Broadcasting live from the stadium...");
        System.out.println("Lineups:");
        System.out.println("  " + teams[0].name + ": " + teams[0].players.get(0).name + ", " + 
                          teams[0].players.get(1).name + ", " + teams[0].players.get(2).name);
        System.out.println("  " + teams[1].name + ": " + teams[1].players.get(0).name + ", " + 
                          teams[1].players.get(1).name + ", " + teams[1].players.get(2).name);
        System.out.println("=========================================\n");
        
        gameEnded = false;
        currentMinute = 0;
        commentaryLog.clear();
        
        // First half
        System.out.println("----- FIRST HALF -----");
        simulateHalf(45);
        
        // Half time
        System.out.println("\nHALF TIME: " + teams[0].name + " " + teams[0].totalScore + " - " + 
                          teams[1].totalScore + " " + teams[1].name);
        displayScore();
        System.out.println("----------------------\n");
        
        // Second half
        System.out.println("----- SECOND HALF -----");
        simulateHalf(90);
        
        if (!gameEnded) {
            System.out.println("\nFULL TIME! " + teams[0].name + " " + teams[0].totalScore + " - " + 
                              teams[1].totalScore + " " + teams[1].name);
        }
        System.out.println("=========================");
    }
    
    private void simulateHalf(int endMinute) {
        for (currentMinute = (endMinute == 45 ? 1 : 46); currentMinute <= endMinute && !gameEnded; currentMinute++) {
            try {
                Thread.sleep(300); // Slow down the simulation for readability
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Update possession
            updatePossession();
            
            // 25% chance of event happening each minute
            if (random.nextInt(100) < 25) {
                handleFootballEvent();
            }
            
            // Display commentary every 5 minutes
            if (currentMinute % 5 == 0) {
                System.out.println("[" + currentMinute + "' | " + teams[0].name + " " + teams[0].totalScore + 
                                  " - " + teams[1].totalScore + " " + teams[1].name + "]");
            }
        }
    }
    
    private void updatePossession() {
        // 10% chance of possession change
        if (random.nextInt(100) < 10) {
            possessionTeam = 1 - possessionTeam;
            teams[possessionTeam].hasPossession = true;
            teams[1 - possessionTeam].hasPossession = false;
        }
        
        if (teams[0].hasPossession) teams[0].possessionTime++;
        else teams[1].possessionTime++;
    }
    
    private void handleFootballEvent() {
        Team attacking = teams[possessionTeam];
        Team defending = teams[1 - possessionTeam];
        Player attacker = attacking.players.get(random.nextInt(attacking.players.size()));
        
        int event = random.nextInt(100);
        
        if (event < 40) { // 40%: Attack leading to shot
            commentaryLog.add(currentMinute + "' | " + attacker.name + " advances with the ball!");
            
            if (random.nextInt(100) < 70) { // 70%: Shot on target
                shotsOnTarget[possessionTeam]++;
                commentaryLog.add(currentMinute + "' | " + attacker.name + " takes a shot!");
                
                if (random.nextInt(100) < 35) { // 35%: Goal
                    attacking.totalScore++;
                    attacker.score++;
                    commentaryLog.add(currentMinute + "' | GOOOOAL!!! " + attacker.name + " scores for " + attacking.name + "!");
                    commentaryLog.add("     " + attacking.name + " " + attacking.totalScore + " - " + 
                                     defending.totalScore + " " + defending.name);
                    
                    // Reset possession to defending team after goal
                    possessionTeam = 1 - possessionTeam;
                    teams[possessionTeam].hasPossession = true;
                    teams[1 - possessionTeam].hasPossession = false;
                } else {
                    commentaryLog.add(currentMinute + "' | Saved by " + defending.name + "'s goalkeeper!");
                }
            } else { // Shot off target
                shotsOffTarget[possessionTeam]++;
                commentaryLog.add(currentMinute + "' | " + attacker.name + " shoots wide of the goal!");
            }
        } 
        else if (event < 70) { // 30%: Foul
            fouls[possessionTeam]++;
            commentaryLog.add(currentMinute + "' | Foul by " + attacker.name + " on " + 
                             defending.players.get(random.nextInt(defending.players.size())).name);
            
            if (random.nextInt(100) < 20) { // 20%: Yellow card
                yellowCards[possessionTeam]++;
                commentaryLog.add("     Yellow card for " + attacker.name);
            }
            
            if (random.nextInt(100) < 5) { // 5%: Red card
                redCards[possessionTeam]++;
                commentaryLog.add("     RED CARD for " + attacker.name + "! " + 
                                 attacking.name + " down to 10 men!");
            }
            
            // Award corner or free kick
            if (random.nextInt(100) < 60) {
                corners[1 - possessionTeam]++;
                commentaryLog.add("     Corner kick for " + defending.name);
            } else {
                commentaryLog.add("     Free kick for " + defending.name);
            }
        }
        else { // 30%: Turnover
            commentaryLog.add(currentMinute + "' | " + attacking.name + " loses possession!");
            possessionTeam = 1 - possessionTeam;
            teams[possessionTeam].hasPossession = true;
            teams[1 - possessionTeam].hasPossession = false;
        }
        
        // Print the latest commentary
        if (!commentaryLog.isEmpty()) {
            System.out.println(commentaryLog.get(commentaryLog.size() - 1));
        }
    }

    @Override
    public void displayScore() {
        System.out.println("\n--- CURRENT SCORE ---");
        System.out.println(teams[0].name + ": " + teams[0].totalScore);
        System.out.println(teams[1].name + ": " + teams[1].totalScore);
        System.out.println("Time: " + currentMinute + "/90 minutes");
        
        // Show possession
        int pos0 = (int)(((double)teams[0].possessionTime / currentMinute) * 100);
        int pos1 = 100 - pos0;
        System.out.println("Possession: " + pos0 + "% - " + pos1 + "%");
    }

    @Override
    public void individualScore() {
        System.out.println("\n--- INDIVIDUAL SCORES ---");
        for (Team team : teams) {
            System.out.println("\n" + team.name + " Scorers:");
            boolean hasScorers = false;
            for (Player player : team.players) {
                if (player.score > 0) {
                    System.out.println("  - " + player.name + " (" + player.score + " goals)");
                    hasScorers = true;
                }
            }
            if (!hasScorers) System.out.println("  - No goals scored");
        }
    }

    @Override
    public void detailScore() {
        System.out.println("\n--- DETAILED MATCH STATS ---");
        System.out.println("Final Score: " + teams[0].name + " " + teams[0].totalScore + 
                          " - " + teams[1].totalScore + " " + teams[1].name);
        
        // Individual scorers
        for (Team team : teams) {
            System.out.println("\n" + team.name + " Scorers:");
            boolean hasScorers = false;
            for (Player player : team.players) {
                if (player.score > 0) {
                    System.out.println("  - " + player.name + " (" + player.score + ")");
                    hasScorers = true;
                }
            }
            if (!hasScorers) System.out.println("  - No goals scored");
        }
        
        // Match statistics
        System.out.println("\nMATCH STATISTICS");
        int totalPossession = teams[0].possessionTime + teams[1].possessionTime;
        int pos0 = totalPossession > 0 ? (teams[0].possessionTime * 100) / totalPossession : 50;
        int pos1 = 100 - pos0;
        
        System.out.printf(" %-20s %d-%d%n", "Possession", pos0, pos1);
        System.out.printf(" %-20s %d-%d%n", "Shots on Target", shotsOnTarget[0], shotsOnTarget[1]);
        System.out.printf(" %-20s %d-%d%n", "Shots off Target", shotsOffTarget[0], shotsOffTarget[1]);
        System.out.printf(" %-20s %d-%d%n", "Corners", corners[0], corners[1]);
        System.out.printf(" %-20s %d-%d%n", "Fouls", fouls[0], fouls[1]);
        System.out.printf(" %-20s %d-%d%n", "Yellow Cards", yellowCards[0], yellowCards[1]);
        System.out.printf(" %-20s %d-%d%n", "Red Cards", redCards[0], redCards[1]);
    }

    @Override
    public String getWinnerTeam() {
        if (teams[0].totalScore > teams[1].totalScore) return teams[0].name;
        else if (teams[1].totalScore > teams[0].totalScore) return teams[1].name;
        else return "Draw";
    }

    @Override
    public String getGameName() {
        return "Football";
    }
}

// ====================== 8-Ball Pool Implementation ======================
class PoolGame implements Game {
    private Team[] teams = new Team[2];
    private boolean gameEnded = false;
    private Random random = new Random();
    private List<String> commentaryLog = new ArrayList<>();
    
    // Pool-specific tracking
    private int[] ballsSunk = {0, 0}; // Track balls sunk per player
    private boolean[] groupAssigned = {false, false}; // Solids or Stripes
    private int[] groupType = {-1, -1}; // -1=Not assigned, 0=Solids, 1=Stripes
    private int currentPlayer = 0; // 0 = player1, 1 = player2
    private boolean breakCompleted = false;
    private boolean eightBallAvailable = false;
    private String[] ballColors = {"Yellow (1)", "Blue (2)", "Red (3)", "Purple (4)", 
                                 "Orange (5)", "Green (6)", "Maroon (7)", "Black (8)",
                                 "Striped Yellow (9)", "Striped Blue (10)", "Striped Red (11)", 
                                 "Striped Purple (12)", "Striped Orange (13)", "Striped Green (14)", 
                                 "Striped Maroon (15)"};

    @Override
    public void createTeams() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter name for Player 1: ");
        teams[0] = new Team(sc.nextLine());
        System.out.print("Enter name for Player 2: ");
        teams[1] = new Team(sc.nextLine());
    }

    @Override
    public void createTeamMembers() {
        teams[0].addPlayer(teams[0].name);
        teams[1].addPlayer(teams[1].name);
        // Set first player to serve
        currentPlayer = 0;
    }

    @Override
    public void playGame() {
        System.out.println("\n=== 8-BALL POOL MATCH SIMULATION STARTED ===");
        System.out.println("Broadcasting from the pool hall...");
        System.out.println("Players: " + teams[0].name + " vs " + teams[1].name);
        System.out.println("===========================================\n");
        
        gameEnded = false;
        commentaryLog.clear();
        ballsSunk[0] = ballsSunk[1] = 0;
        groupAssigned[0] = groupAssigned[1] = false;
        groupType[0] = groupType[1] = -1;
        breakCompleted = false;
        eightBallAvailable = false;
        currentPlayer = 0;
        
        System.out.println("Rack 'em up! The balls are set on the table...\n");
        
        // Simulate the game
        while (!gameEnded) {
            try {
                Thread.sleep(500); // Slow down the simulation for readability
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            Player player = teams[currentPlayer].players.get(0);
            System.out.println("[" + teams[0].name + " " + ballsSunk[0] + " - " + 
                              ballsSunk[1] + " " + teams[1].name + "]");
            
            simulatePoolShot();
            
            // Switch players if shot was unsuccessful or foul
            if (!gameEnded && !player.hasGroup) {
                currentPlayer = 1 - currentPlayer;
            }
        }
        
        if (!gameEnded) {
            System.out.println("\n--- GAME OVER! ---");
        }
        System.out.println("===================");
    }

    private void simulatePoolShot() {
        Player player = teams[currentPlayer].players.get(0);
        System.out.println("\n" + player.name + "'s turn");
        
        // First shot is always the break
        if (!breakCompleted) {
            handleBreakShot();
            return;
        }
        
        // 85% chance of legal shot
        boolean isLegalShot = random.nextInt(100) < 85;
        
        if (!isLegalShot) {
            handleFoul(player);
            return;
        }
        
        // 70% chance of sinking a ball
        if (random.nextInt(100) < 70) {
            handleSuccessfulShot(player);
        } else {
            handleMissedShot(player);
        }
    }
    
    private void handleBreakShot() {
        Player player = teams[currentPlayer].players.get(0);
        commentaryLog.add("Breaking the rack...");
        
        try { Thread.sleep(500); } catch (InterruptedException e) { }
        
        // 20% chance of sinking a ball on break
        if (random.nextInt(100) < 20) {
            int ballNumber = random.nextInt(15) + 1;
            commentaryLog.add(player.name + " breaks and sinks ball #" + ballNumber + "!");
            
            if (ballNumber == 8) {
                commentaryLog.add("Illegal break! Sinking the 8-ball on break doesn't win the game.");
            } else {
                // Assign group based on ball sunk
                int group = (ballNumber <= 7) ? 0 : 1;
                groupType[currentPlayer] = group;
                groupAssigned[currentPlayer] = true;
                
                String groupName = (group == 0) ? "Solids (1-7)" : "Stripes (9-15)";
                commentaryLog.add("Group assigned: " + groupName);
                
                ballsSunk[currentPlayer]++;
                player.score++;
                teams[currentPlayer].totalScore++;
                
                if (ballsSunk[currentPlayer] == 7) {
                    eightBallAvailable = true;
                    commentaryLog.add(player.name + " has cleared their group! Ready for 8-ball!");
                }
            }
        } else {
            commentaryLog.add(player.name + " breaks the rack...");
        }
        
        breakCompleted = true;
        
        // Print commentary
        for (String comment : commentaryLog) {
            System.out.println(comment);
        }
        commentaryLog.clear();
    }
    
    private void handleFoul(Player player) {
        commentaryLog.add("FOUL! Cue ball scratched or missed object ball");
        
        // Random foul type
        String[] fouls = {
            "Cue ball scratched into pocket",
            "Failed to hit any object ball",
            "Hit wrong group ball first",
            "Knocked ball off the table",
            "Cue stick touched cue ball twice"
        };
        commentaryLog.add("    - " + fouls[random.nextInt(fouls.length)]);
        
        player.fouls++;
        currentPlayer = 1 - currentPlayer; // Other player gets ball-in-hand
        
        // Print commentary
        for (String comment : commentaryLog) {
            System.out.println(comment);
        }
        commentaryLog.clear();
    }
    
    private void handleSuccessfulShot(Player player) {
        // 5% chance it's the 8-ball
        if (random.nextInt(100) < 5 && eightBallAvailable) {
            commentaryLog.add(player.name + " is targeting the 8-ball...");
            
            // Check if player has cleared their group
            if (ballsSunk[currentPlayer] >= 7 && groupAssigned[currentPlayer]) {
                commentaryLog.add(player.name + " SINKS THE 8-BALL AND WINS THE GAME!");
                gameEnded = true;
            } else {
                commentaryLog.add("ILLEGAL 8-BALL SHOT! " + player.name + " hasn't cleared their group yet");
                commentaryLog.add("    Game continues...");
                currentPlayer = 1 - currentPlayer; // Turn over
            }
        } else {
            // Sunk a regular ball
            int ballNumber;
            String ballDescription;
            
            if (!groupAssigned[currentPlayer]) {
                // First ball after break - assign group
                ballNumber = random.nextInt(15) + 1;
                groupType[currentPlayer] = (ballNumber <= 7) ? 0 : 1;
                groupAssigned[currentPlayer] = true;
                
                String groupName = (groupType[currentPlayer] == 0) ? "Solids" : "Stripes";
                commentaryLog.add("Group assigned: " + groupName);
            } else {
                // Already have group - get appropriate ball
                if (groupType[currentPlayer] == 0) {
                    // Solids - 1-7
                    ballNumber = random.nextInt(7) + 1;
                } else {
                    // Stripes - 9-15
                    ballNumber = random.nextInt(7) + 9;
                }
            }
            
            ballDescription = ballColors[ballNumber - 1];
            commentaryLog.add(player.name + " sunk the " + ballDescription + "!");
            
            ballsSunk[currentPlayer]++;
            player.score++;
            teams[currentPlayer].totalScore++;
            
            // Check if player cleared their group
            if (ballsSunk[currentPlayer] == 7 && groupAssigned[currentPlayer]) {
                eightBallAvailable = true;
                commentaryLog.add(player.name + " has cleared their group! Ready for 8-ball!");
            } else {
                commentaryLog.add("    Balls sunk: " + ballsSunk[currentPlayer] + "/7");
            }
        }
        
        // Print commentary
        for (String comment : commentaryLog) {
            System.out.println(comment);
        }
        commentaryLog.clear();
    }
    
    private void handleMissedShot(Player player) {
                    commentaryLog.add(player.name + " missed the shot!");
        
        // Random reason
        String[] reasons = {
            "Hit the rail without contacting object ball",
            "Object ball didn't hit rail after contact",
            "Failed to pocket a ball"
        };
                    commentaryLog.add("    - " + reasons[random.nextInt(reasons.length)]);
        
        currentPlayer = 1 - currentPlayer; // Turn over
        
        // Print commentary
        for (String comment : commentaryLog) {
            System.out.println(comment);
        }
        commentaryLog.clear();
    }

    @Override
    public void displayScore() {
        System.out.println("\n--- CURRENT SCORE ---");
        System.out.println(teams[0].name + ": " + ballsSunk[0] + " balls");
        System.out.println(teams[1].name + ": " + ballsSunk[1] + " balls");
        
        // Show group status
        for (int i = 0; i < 2; i++) {
            if (groupAssigned[i]) {
                String group = (groupType[i] == 0) ? "Solids" : "Stripes";
                System.out.println(teams[i].name + " group: " + group + 
                                  " (" + ballsSunk[i] + "/7)");
            }
        }
    }

    @Override
    public void individualScore() {
        System.out.println("\n--- PLAYER STATS ---");
        for (Team team : teams) {
            Player player = team.players.get(0);
            System.out.println("\n" + player.name + ":");
            System.out.println("  - Balls sunk: " + team.totalScore);
            System.out.println("  - Fouls: " + player.fouls);
            
            if (groupAssigned[team == teams[0] ? 0 : 1]) {
                String group = (groupType[team == teams[0] ? 0 : 1] == 0) ? "Solids" : "Stripes";
                System.out.println("  - Assigned group: " + group);
                System.out.println("  - Progress: " + ballsSunk[team == teams[0] ? 0 : 1] + "/7");
            } else {
                System.out.println("  - Group not assigned yet");
            }
        }
    }

    @Override
    public void detailScore() {
        System.out.println("\n--- DETAILED POOL STATS ---");
        System.out.println("Balls sunk: " + teams[0].name + " " + ballsSunk[0] + 
                          " - " + ballsSunk[1] + " " + teams[1].name);
        
        for (int i = 0; i < 2; i++) {
            System.out.println("\n" + teams[i].name + ":");
            System.out.println("  - Total balls: " + ballsSunk[i]);
            System.out.println("  - Fouls: " + teams[i].players.get(0).fouls);
            
            if (groupAssigned[i]) {
                String group = (groupType[i] == 0) ? "Solids (1-7)" : "Stripes (9-15)";
                System.out.println("  - Assigned group: " + group);
                System.out.println("  - Progress: " + ballsSunk[i] + "/7");
            } else {
                System.out.println("  - Group not assigned yet");
            }
        }
        
        System.out.println("\n8-Ball status: " + 
                          (eightBallAvailable ? "Available for shooting" : "Not available yet"));
    }

    @Override
    public String getWinnerTeam() {
        if (gameEnded) {
            return teams[currentPlayer].name;
        }
        
        if (ballsSunk[0] > ballsSunk[1]) return teams[0].name;
        else if (ballsSunk[1] > ballsSunk[0]) return teams[1].name;
        else return "Match Tied";
    }

    @Override
    public String getGameName() {
        return "8-Ball Pool";
    }
}

// ====================== Main Application ======================
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game currentGame = null;
        
        System.out.println("===== SPORTS SCOREBOARD SYSTEM =====");
        System.out.println("Your one-stop solution for sports broadcasting");
        
        while (true) {
            if (currentGame == null) {
                System.out.println("\nSelect a sport to broadcast:");
                System.out.println("1. Football Match");
                System.out.println("2. 8-Ball Pool Game");
                System.out.println("3. Exit System");
                System.out.print("Choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        currentGame = new FootballGame();
                        System.out.println("\n--- Setting up Football Match ---");
                        currentGame.createTeams();
                        currentGame.createTeamMembers();
                        break;
                    case 2:
                        currentGame = new PoolGame();
                        System.out.println("\n--- Setting up 8-Ball Pool Game ---");
                        currentGame.createTeams();
                        currentGame.createTeamMembers();
                        break;
                    case 3:
                        System.out.println("\nThank you for using Scoreboard!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } 
            else {
                System.out.println("\n--- " + currentGame.getGameName() + " BROADCAST MENU ---");
                System.out.println("1. Broadcast Game (full simulation)");
                System.out.println("2. Display Current Score");
                System.out.println("3. Individual Player Stats");
                System.out.println("4. Detailed Match Stats");
                System.out.println("5. Show Winner");
                System.out.println("6. Start New Broadcast");
                System.out.println("7. Exit System");
                System.out.print("Choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        currentGame.playGame();
                        break;
                    case 2:
                        currentGame.displayScore();
                        break;
                    case 3:
                        currentGame.individualScore();
                        break;
                    case 4:
                        currentGame.detailScore();
                        break;
                    case 5:
                        System.out.println("Winner: " + currentGame.getWinnerTeam());
                        break;
                    case 6:
                        currentGame = null;
                        break;
                    case 7:
                        System.out.println("\nThank you for using Scoreboard!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}





// output example

/* 

===== SPORTS SCOREBOARD SYSTEM =====
Your one-stop solution for sports broadcasting

Select a sport to broadcast:
1. Football Match
2. 8-Ball Pool Game
3. Exit System
Choice: 1

--- Setting up Football Match ---

Enter name for Home Team: Manchester United
Enter name for Away Team: Liverpool

Enter 3 players for Manchester United:
Player 1: Ronaldo
Player 2: Messi
Player 3: Neymar

Enter 3 players for Liverpool:
Player 1: Salah
Player 2: Mane
Player 3: Firmino

--- FOOTBALL MATCH BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 1

=== FOOTBALL MATCH SIMULATION STARTED ===
Broadcasting live from the stadium...
Lineups:
  Manchester United: Ronaldo, Messi, Neymar
  Liverpool: Salah, Mane, Firmino
=========================================

----- FIRST HALF -----
[5' | Manchester United 0 - 0 Liverpool]
5' | Ronaldo advances with the ball!
5' | Ronaldo takes a shot!
5' | Saved by Liverpool's goalkeeper!
[10' | Manchester United 0 - 0 Liverpool]
12' | Foul by Messi on Firmino
     Free kick for Liverpool
[15' | Manchester United 0 - 0 Liverpool]
20' | Ronaldo advances with the ball!
20' | Ronaldo takes a shot!
20' | GOOOOAL!!! Ronaldo scores for Manchester United!
     Manchester United 1 - 0 Liverpool
[25' | Manchester United 1 - 0 Liverpool]
30' | Salah advances with the ball!
30' | Salah takes a shot!
30' | Saved by Manchester United's goalkeeper!
[35' | Manchester United 1 - 0 Liverpool]
40' | Foul by Neymar on Mane
     Yellow card for Neymar
     Free kick for Liverpool
[45' | Manchester United 1 - 0 Liverpool]

HALF TIME: Manchester United 1 - 0 Liverpool
--- CURRENT SCORE ---
Manchester United: 1
Liverpool: 0
Time: 45/90 minutes
Possession: 52% - 48%

----- SECOND HALF -----
50' | Salah advances with the ball!
50' | Salah takes a shot!
50' | GOOOOAL!!! Salah scores for Liverpool!
     Liverpool 1 - 1 Manchester United
[55' | Manchester United 1 - 1 Liverpool]
65' | Messi advances with the ball!
65' | Messi takes a shot!
65' | GOOOOAL!!! Messi scores for Manchester United!
     Manchester United 2 - 1 Liverpool
[70' | Manchester United 2 - 1 Liverpool]
85' | Foul by Firmino on Ronaldo
     Yellow card for Firmino
     Free kick for Manchester United
[90' | Manchester United 2 - 1 Liverpool]

FULL TIME! Manchester United 2 - 1 Liverpool
=========================

--- FOOTBALL MATCH BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 4

--- DETAILED MATCH STATS ---
Final Score: Manchester United 2 - 1 Liverpool

Manchester United Scorers:
  - Ronaldo (1)
  - Messi (1)

Liverpool Scorers:
  - Salah (1)

MATCH STATISTICS
 Possession           52-48
 Shots on Target      8-6
 Shots off Target     4-3
 Corners              3-2
 Fouls                8-9
 Yellow Cards         1-1
 Red Cards            0-0

--- FOOTBALL MATCH BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 5
Winner: Manchester United

--- FOOTBALL MATCH BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 6

Select a sport to broadcast:
1. Football Match
2. 8-Ball Pool Game
3. Exit System
Choice: 2

--- Setting up 8-Ball Pool Game ---

Enter name for Player 1: John
Enter name for Player 2: Mike

=== 8-BALL POOL MATCH SIMULATION STARTED ===
Broadcasting from the pool hall...
Players: John vs Mike
===========================================

Rack 'em up! The balls are set on the table...

[John 0 - 0 Mike]
John's turn
Breaking the rack...

[John 0 - 0 Mike]
John's turn
John breaks and sinks ball #3!
Group assigned: Solids (1-7)

[John 1 - 0 Mike]
John's turn
John sunk the Red (3)!
    Balls sunk: 1/7

[John 1 - 0 Mike]
Mike's turn
Mike missed the shot!
    - Failed to pocket a ball

[John 1 - 0 Mike]
John's turn
John sunk the Blue (2)!
    Balls sunk: 2/7

[John 2 - 0 Mike]
John's turn
John sunk the Yellow (1)!
    Balls sunk: 3/7

[John 3 - 0 Mike]
John's turn
John sunk the Purple (4)!
    Balls sunk: 4/7

[John 4 - 0 Mike]
John's turn
John sunk the Orange (5)!
    Balls sunk: 5/7

[John 5 - 0 Mike]
John's turn
John sunk the Green (6)!
    Balls sunk: 6/7

[John 6 - 0 Mike]
John's turn
John sunk the Maroon (7)!
    Balls sunk: 7/7
John has cleared their group! Ready for 8-ball!

[John 7 - 0 Mike]
John's turn
John is targeting the 8-ball...
John SINKS THE 8-BALL AND WINS THE GAME!

--- GAME OVER! ---
===================

--- 8-BALL POOL BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 5
Winner: John

--- 8-BALL POOL BROADCAST MENU ---
1. Broadcast Game (full simulation)
2. Display Current Score
3. Individual Player Stats
4. Detailed Match Stats
5. Show Winner
6. Start New Broadcast
7. Exit System
Choice: 7

Thank you for using Scoreboard!
  
*/