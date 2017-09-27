package com.johnuckele.hots.mmr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileParser {
  private static DateFormat dateParser = new SimpleDateFormat("y-M-d k:m:s");

  public static void parseReplayFile(GlobalData data, String filename) throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
      String line;
      while ((line = reader.readLine()) != null) {
    	String[] fragments = line.split(",");
        int id = Integer.parseInt(fragments[0]);
        long date = dateParser.parse(fragments[2]).getTime();
        Map map = Map.parseMap(fragments[3]);
        GameType type = GameType.parseGameType(fragments[1]);
        int region = Integer.parseInt(fragments[4]);
        Game game = new Game(id, date, region, map, type);
        data.orderedGames.add(game);
        data.gamesById.put(game.id, game);
      }
    }
  }

  public static void parsePlayerFile(GlobalData data, String filename) throws Exception {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Parse line
        String[] fragments = line.split(",");
        int gameId = Integer.parseInt(fragments[1]);
        String battletag = fragments[2];
        Hero hero = Hero.parseHero(fragments[3]);
        int heroLevel = Integer.parseInt(fragments[4]);
        int team = Integer.parseInt(fragments[5]);
        boolean win = "1".equals(fragments[6]);
        int blizzardId = Integer.parseInt(fragments[7]);
        Game game = data.gamesById.get(gameId);
        String regionAndBlizzardId = game.region + "-" + blizzardId;

        // Create / update player
        Player player;
        if (data.playersByRegionAndBlizzardId.containsKey(regionAndBlizzardId)) {
          player = data.playersByRegionAndBlizzardId.get(regionAndBlizzardId);
          player.setBattleTag(battletag);
        } else {
          player = new Player(regionAndBlizzardId, battletag);
          data.playersByRegionAndBlizzardId.put(regionAndBlizzardId, player);
        }

        // Join player to game
        GamePlayer gamePlayer = new GamePlayer(hero, heroLevel, team, win, game, player);
        game.addGamePlayer(gamePlayer);
        player.addGamePlayer(gamePlayer);
      }
    }
  }
}