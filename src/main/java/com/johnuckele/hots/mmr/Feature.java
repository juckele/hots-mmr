package com.johnuckele.hots.mmr;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Feature {
  BATTLETAG("battletag") {
    @Override
    public String getValue(Player player) {
      {
        return player.getBattleTag();
      }
    }
  },
  REGION_AND_ID("region-blizzardId") {
    @Override
    public String getValue(Player player) {
      {
        return player.regionAndblizzardId;
      }
    }
  },
  ELO("elo") {
    @Override
    public String getValue(Player player) {
      return ""+player.getElo();
    }
  },
  ANTE("ante") {
    @Override
    public String getValue(Player player) {
      return ""+player.getAnte();
    }
  },
  ;

  public final String label;

  Feature(String label) {
    this.label = label;
  }

  public abstract String getValue(Player player);

  public static String getHeader() {
    return Arrays.stream(Feature.values()).map(i -> i.label).collect(Collectors.joining(","));
  }

  public static String getRow(Player player) {
    return Arrays.stream(Feature.values())
        .map(i -> i.getValue(player))
        .collect(Collectors.joining(","));
  }
}
