package com.johnuckele.hots.mmr;

public enum GameType {
  B("Brawl"),
  QM("QuickMatch"),
  UR("UnrankedDraft"),
  HL("HeroLeague"),
  TL("TeamLeague"),
  ;

  public final String name;

  GameType(String name) {
    this.name = name;
  }

  public static GameType parseGameType(String sourceString) {
    for (GameType value : GameType.values()) {
      if (value.name.equals(sourceString)) {
        return value;
      }
    }
    throw new IllegalStateException("Unable to map \"" + sourceString + "\" to a valid game type");
  }
}
