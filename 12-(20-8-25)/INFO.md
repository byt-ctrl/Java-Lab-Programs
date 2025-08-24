# Sports Scoreboard System - Code Analysis

## Overview
The Sports Scoreboard System is a Java application that simulates and broadcasts two types of games: Football matches and 8-Ball Pool games. It provides real-time commentary, statistics tracking, and comprehensive scoring functionality through a menu-driven interface.

## System Architecture

### Core Design Pattern
- **Interface-based Design**: Uses the `Game` interface to ensure consistent behavior across different sports
- **Polymorphism**: Both `FootballGame` and `PoolGame` implement the same interface, allowing for unified handling
- **Modular Structure**: Each sport has its own implementation with sport-specific logic

## Code Block Analysis

### 1. Core Classes

#### Player Class
```java
class Player {
    String name;
    int score;
    int fouls;
    boolean hasGroup; // for pool
    int groupType; // 0=Solids, 1=Stripes
}
```
**Purpose**: Represents individual players with universal attributes
- Tracks personal statistics (score, fouls)
- Pool-specific attributes (group assignment)
- Used across both sports with different interpretations of `score`

#### Team Class
```java
class Team {
    String name;
    List<Player> players;
    int totalScore;
    // Football-specific attributes
    int possessionTime;
    int corners;
    int yellowCards;
    int redCards;
    boolean hasPossession;
}
```
**Purpose**: Container for players and team-level statistics
- Manages multiple players (3 for football, 1 for pool)
- Tracks team-wide metrics
- Football-specific possession and disciplinary tracking

### 2. Game Interface

#### Interface Definition
```java
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
```
**Purpose**: Defines contract for all game implementations
- Ensures consistent API across different sports
- Standardizes setup, gameplay, and reporting methods
- Enables polymorphic behavior in main application

### 3. Football Implementation

#### FootballGame Class Structure
**Key Components**:
- **Match Simulation**: 90-minute real-time simulation with first/second half
- **Event System**: Random events including goals, fouls, cards, corners
- **Commentary Engine**: Live commentary with minute-by-minute updates
- **Statistics Tracking**: Comprehensive match statistics

#### Core Methods Analysis

##### `simulateHalf(int endMinute)`
**Logic**:
- Iterates through each minute of the half
- 25% chance of event occurring per minute
- Updates possession continuously
- Provides periodic score updates every 5 minutes

##### `handleFootballEvent()`
**Event Distribution**:
- 40% - Attacking play leading to shot
  - 70% of shots on target
  - 35% of on-target shots result in goals
- 30% - Fouls with potential cards
- 30% - Possession turnover

**Smart Features**:
- Possession resets to defending team after goals
- Yellow cards (20% of fouls) and red cards (5% of fouls)
- Corner kicks and free kicks awarded appropriately

#### Statistics Tracking
```java
private int[] shotsOnTarget = {0, 0};
private int[] shotsOffTarget = {0, 0};
private int[] corners = {0, 0};
private int[] fouls = {0, 0};
private int[] yellowCards = {0, 0};
private int[] redCards = {0, 0};
```
**Purpose**: Comprehensive match statistics for realistic football simulation

### 4. 8-Ball Pool Implementation

#### PoolGame Class Structure
**Key Components**:
- **Break Shot Handling**: Special logic for opening break
- **Group Assignment**: Dynamic assignment of Solids/Stripes based on first ball sunk
- **Turn-based Play**: Alternating turns with continuation for successful shots
- **8-Ball Endgame**: Special winning condition handling

#### Core Methods Analysis

##### `handleBreakShot()`
**Logic**:
- 20% chance of sinking ball on break
- Automatic group assignment based on ball type sunk
- Special handling for 8-ball on break (illegal but not game-ending)

##### `handleSuccessfulShot(Player player)`
**Decision Tree**:
1. Check if 8-ball attempt (5% chance when available)
   - Validate player has cleared group (7 balls)
   - End game if legal, continue if illegal
2. Regular ball sinking
   - Assign group if first ball after break
   - Track progress toward group completion
   - Enable 8-ball when group cleared

#### Ball Management
```java
private String[] ballColors = {
    "Yellow (1)", "Blue (2)", "Red (3)", "Purple (4)", 
    "Orange (5)", "Green (6)", "Maroon (7)", "Black (8)",
    "Striped Yellow (9)", "Striped Blue (10)", // ... etc
};
```
**Purpose**: Realistic ball identification and commentary

### 5. Main Application

#### Menu System
**Two-Level Structure**:
1. **Sport Selection**: Choose between Football, Pool, or Exit
2. **Game Management**: Broadcast controls, statistics, and game operations

#### Game Lifecycle
```java
// Setup Phase
currentGame.createTeams();
currentGame.createTeamMembers();

// Runtime Phase
currentGame.playGame();        // Full simulation
currentGame.displayScore();    // Current status
currentGame.individualScore(); // Player stats
currentGame.detailScore();     // Comprehensive stats
```

## Key Algorithms and Logic

### Football Possession Algorithm
```java
private void updatePossession() {
    // 10% chance of possession change per minute
    if (random.nextInt(100) < 10) {
        possessionTeam = 1 - possessionTeam;
        // Update possession flags
    }
    // Accumulate possession time
    teams[possessionTeam].possessionTime++;
}
```
**Logic**: Realistic possession changes with time tracking

### Pool Turn Management
```java
// Continue turn if successful shot, switch if miss/foul
if (!gameEnded && !player.hasGroup) {
    currentPlayer = 1 - currentPlayer;
}
```
**Logic**: Turn continuation for successful shots, immediate switch for failures

### Winner Determination
**Football**: Simple score comparison with draw handling
**Pool**: Game ends immediately when 8-ball is legally sunk

## System Features

### Real-time Simulation
- **Threading**: Uses `Thread.sleep()` for realistic pacing
- **Live Commentary**: Minute-by-minute updates with event descriptions
- **Progressive Statistics**: Continuously updated throughout gameplay

### Comprehensive Statistics
- **Individual Tracking**: Player-specific achievements and fouls
- **Team Metrics**: Aggregate statistics and performance indicators
- **Sport-specific Data**: Possession, shots, cards (football) vs groups, progress (pool)

### User Experience
- **Interactive Menus**: Clear navigation and options
- **Real-time Feedback**: Immediate score and statistic updates
- **Flexible Broadcasting**: Can view stats during or after gameplay

## Technical Implementation Notes

### Randomization Strategy
- **Event Probability**: Carefully tuned percentages for realistic outcomes
- **Weighted Decisions**: Different probabilities for different event types
- **Cascading Events**: Events can trigger secondary events (goals → possession change)

### Data Structures
- **Arrays for Tracking**: Parallel arrays for team statistics (`int[] shotsOnTarget`)
- **Lists for Commentary**: `ArrayList<String>` for commentary log management
- **Boolean Flags**: State tracking for game phases and conditions

### Error Handling
- **Input Validation**: Scanner input handling with newline consumption
- **Thread Interruption**: Proper handling of sleep interruptions
- **State Validation**: Checks for legal moves and game states
