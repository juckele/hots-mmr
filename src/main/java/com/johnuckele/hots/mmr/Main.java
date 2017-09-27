package com.johnuckele.hots.mmr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    long startTime = System.currentTimeMillis();
    System.out.println("Initializing program.");
    GlobalData data = new GlobalData();
    System.out.println("Parsing games.");
    FileParser.parseReplayFile(data, "/home/juckele/Desktop/replays.csv");
    long parseGamesTime = System.currentTimeMillis();
    System.out.println(
        "Parsing games took " + ((parseGamesTime - startTime) / 100 / 10.0) + " seconds.");
    System.out.println("Parsing players.");
    FileParser.parsePlayerFile(data, "/home/juckele/Desktop/players.csv");
    long parsePlayersTime = System.currentTimeMillis();
    System.out.println(
        "Parsing players took " + ((parsePlayersTime - parseGamesTime) / 100 / 10.0) + " seconds.");
    int globalGameCount = data.gamesById.size();
    int globalPlayerCount = data.playersByRegionAndBlizzardId.size();
    System.out.println("Games: " + globalGameCount);
    System.out.println("Players: " + globalPlayerCount);
    System.out.println(
        "Games played per player: " + ((double) globalGameCount / globalPlayerCount));

    HashMap<Integer, Integer> gamesPlayedToPlayerCounts = new HashMap<>();
    List<Player> printablePlayers = new LinkedList<>();
    for (Player player : data.playersByRegionAndBlizzardId.values()) {
      int playerGameCount = player.getGamePlayedCount();
      if (gamesPlayedToPlayerCounts.containsKey(playerGameCount)) {
        gamesPlayedToPlayerCounts.put(
            playerGameCount, gamesPlayedToPlayerCounts.get(playerGameCount) + 1);
      } else {
        gamesPlayedToPlayerCounts.put(playerGameCount, 1);
      }
      if (playerGameCount > 9) {
        printablePlayers.add(player);
      }
    }
    System.out.println("Games played by players: " + gamesPlayedToPlayerCounts);
    long postProcessTime = System.currentTimeMillis();
    System.out.println(
        "Post processing took "
            + ((postProcessTime - parsePlayersTime) / 100 / 10.0)
            + " seconds.");

    // Calculate Elo and then write it to a file
    calculateScores(data);
    FileWriter.writeScores("/home/juckele/Desktop/scores.csv", printablePlayers);

    long eloCalculationTime = System.currentTimeMillis();
    System.out.println(
        "Elo calculation took "
            + ((eloCalculationTime - postProcessTime) / 100 / 10.0)
            + " seconds.");
  }

  private static void calculateScores(GlobalData data) {
    int correctPredictions = 0;
    int totalPredictions = 0;
    for(Game game : data.orderedGames) {
      if(game.getGamePlayer(9) == null) {
        System.out.println("Game missing players...");
        continue;
      }
      if(computeAnteDelta(game)) { correctPredictions++;}
      totalPredictions++;
    }
    System.out.println("Prediction rate: "+correctPredictions+"/"+totalPredictions+" = "+((double)correctPredictions/totalPredictions));
  }

  private static boolean computeAnteDelta(Game game) {
    final double ANTE_SIZE = 0.02;

    GamePlayer[] gamePlayers = new GamePlayer[10];
    Player[] players = new Player[10];
    double blueTeamAntePot = 0;
    double redTeamAntePot = 0;
    double[] ante_bids = new double[10];
    for(int i = 0; i < 10; i++) {
      gamePlayers[i] = game.getGamePlayer(i);
      players[i] = gamePlayers[i].player;
      ante_bids[i] = players[i].getAnte()*ANTE_SIZE;
      int team = i < 5 ? 0 : 1;
      if(team == 0) {
        blueTeamAntePot += ante_bids[i];
      }
      else {
        redTeamAntePot += ante_bids[i];
      }
    }
    int predictedWinner = blueTeamAntePot > redTeamAntePot ? 0 : 1;
    int winningTeam = gamePlayers[0].win ? 0 : 1;
    for(int i = 0; i < 10; i++) 
    {
      int team = i < 5 ? 0 : 1;
      if(team == winningTeam) {
        if(team == 0) {
          players[i].setAnte(players[i].getAnte()+redTeamAntePot/5);
        }
        else {
          players[i].setAnte(players[i].getAnte()+blueTeamAntePot/5);
        }
      }
      else {
        players[i].setAnte(players[i].getAnte()-ante_bids[i]);
      }
    }
    
    return predictedWinner == winningTeam;
  }
}
