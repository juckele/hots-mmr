package com.johnuckele.hots.mmr;

public class Game {
  // Attributes
  public final int id;
  public final long date;
  public final int region;
  public final Map map;
  public final GameType type;

  // Structural data
  private int _gamePlayerCount = 0;
  private GamePlayer[] _gamePlayers = new GamePlayer[10];

  public Game(int id, long date, int region, Map map, GameType type) {
    // System.out.println("Parsed game #" + id + "@" + date + ". " + map + ":" + type);
    this.id = id;
    this.date = date;
    this.region = region;
    this.map = map;
    this.type = type;
  }

  public void addGamePlayer(GamePlayer gamePlayer) {
    _gamePlayers[_gamePlayerCount++] = gamePlayer;
  }

  public GamePlayer getGamePlayer(int i) {
    return _gamePlayers[i];
  }
}
