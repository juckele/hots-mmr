package com.johnuckele.hots.mmr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GlobalData {
  public List<Game> orderedGames = new LinkedList<>();
  public HashMap<Integer, Game> gamesById = new HashMap<>();
  public HashMap<String, Player> playersByRegionAndBlizzardId = new HashMap<>();
}
