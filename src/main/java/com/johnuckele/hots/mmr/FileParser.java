package com.johnuckele.hots.mmr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class FileParser {
  private static DateFormat dateParser = new SimpleDateFormat("y-M-d k:m:s");

  public static void parseReplayFile(GlobalData data, String filename) throws Exception {
    parseReplayLines(data, readFile(filename));
  }

  public static void parsePlayerFile(GlobalData data, String filename) throws Exception {
    parsePlayerLines(data, readFile(filename));
  }

  public static String[] readFile(String filename) throws IOException {
    Path filepath = Paths.get(filename);
    List<String> lines = Files.readAllLines(filepath, Charset.forName("UTF-8"));
    return lines.toArray(new String[lines.size()]);
  }

  public static void parseReplayLines(GlobalData data, String[] lines) throws Exception {
    for (int i = 0; i < lines.length; i++) {
      String line = lines[i];
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

  public static void parsePlayerLines(GlobalData data, String[] lines) throws Exception {
    for (int i = 0; i < lines.length; i++) {

      // Parse line
      String line = lines[i];
      String[] fragments = line.split(",");
      int gameId = Integer.parseInt(fragments[1]);
      String battletag = fragments[2];
      Hero hero = Hero.parseHero(fragments[3]);
      int heroLevel = Integer.parseInt(fragments[4]);
      int team = Integer.parseInt(fragments[5]);
      boolean win = "1".equals(fragments[6]);
      int blizzardId = Integer.parseInt(fragments[7]);
      Game game = data.gamesById.get(gameId);
      String regionAndBlizzardId = game.region+"-"+blizzardId;

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
