package com.johnuckele.hots.mmr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {
  public static void writeScores(String filename, List<Player> players) throws IOException {
    System.out.println("Writing file: filename");
    PrintWriter out = new PrintWriter(filename);

    // Write the header
    out.println(Feature.getHeader());
//    System.out.println(Feature.getHeader());

    // Write feature rows
    for (Player player : players) {
      String row = Feature.getRow(player);
      out.println(row);
//      System.out.println(row);
    }
    out.close();
  }
}
