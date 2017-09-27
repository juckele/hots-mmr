package com.johnuckele.hots.mmr;

import java.util.ArrayList;
import java.util.List;

public class Player {
  // Attributes
  public final String regionAndblizzardId;
  private String battletag;
  private double elo = 1500;
  private double anteScore = 100;

  // Structural data
  private List<GamePlayer> _gamePlayers = new ArrayList<>();

  public Player(String regionAndblizzardId, String battletag) {
    // System.out.println("Creating player... " + battletag + " (" + blizzardId + ")");
    this.regionAndblizzardId = regionAndblizzardId;
    this.battletag = battletag;
  }

  public void addGamePlayer(GamePlayer gamePlayer) {
    _gamePlayers.add(gamePlayer);
  }

  public String getBattleTag() {
    return battletag;
  }

  public double getElo() {
    return elo;
  }

  public int getGamePlayedCount() {
    return _gamePlayers.size();
  }

  public GamePlayer getGamePlayer(int i) {
    return _gamePlayers.get(i);
  }

  public void setElo(double elo) {
    this.elo = elo;
  }

  public void setBattleTag(String battletag) {
    this.battletag = battletag;
  }

  public double getAnte() {
    return anteScore;
  }
  public void setAnte(double ante) {
    this.anteScore = ante;
  }
}
