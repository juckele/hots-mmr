package com.johnuckele.hots.mmr;

public enum Map {
  // Standard Maps
  BHB("Blackheart's Bay"),
  GOT("Garden of Terror"),
  HOLLOW("Cursed Hollow"),
  BOE("Battlefield of Eternity"),
  TEMPLE("Sky Temple"),
  HAN("Hanamura"),
  SHRINES("Infernal Shrines"),
  TOWERS("Towers of Doom"),
  BRAXIS("Braxis Holdout"),
  WARHEAD("Warhead Junction"),
  TOMB("Tomb of the Spider Queen"),
  DS("Dragon Shire"),
  MINES("Haunted Mines"),
  // Brawl Maps
  LC("Lost Cavern"),
  SC("Silver City"),
  BO("Braxis Outpost"),
  CPH("Checkpoint: Hanamura"),
  POOL("Pull Party"),
  // MISSING MAP!
  NULL("\\N"),
  ;

  public final String name;

  Map(String name) {
    this.name = name;
  }

  public static Map parseMap(String sourceString) {
    for (Map value : Map.values()) {
      if (value.name.equals(sourceString)) {
        return value;
      }
    }
    throw new IllegalStateException("Unable to map \"" + sourceString + "\" to a valid map");
  }
}
