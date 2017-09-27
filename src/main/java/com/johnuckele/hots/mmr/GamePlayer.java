package com.johnuckele.hots.mmr;

import com.google.common.base.Preconditions;

public class GamePlayer {
  // Attributes
  public final Hero hero;
  public final int heroLevel;
  public final int team;
  public final boolean win;

  // Structural data
  public final Game game;
  public final Player player;

  public GamePlayer(Hero hero, int heroLevel, int team, boolean win, Game game, Player player) {
    Preconditions.checkNotNull(hero);
    Preconditions.checkNotNull(game);
    Preconditions.checkNotNull(player);
    // System.out.println("Parsed game #" + id + "@" + date + ". " + map + ":" + type);
    this.hero = hero;
    this.heroLevel = heroLevel;
    this.team = team;
    this.win = win;
    this.game = game;
    this.player = player;
  }
}
