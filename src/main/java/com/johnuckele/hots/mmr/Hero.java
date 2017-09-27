package com.johnuckele.hots.mmr;

public enum Hero {
  ABATHUR("Abathur"),
  Alarak("Alarak"),
  ANA("Ana"),
  ANUB("Anub'arak"),
  ARTANIS("Artanis"),
  ARTHAS("Arthas"),
  AURIEL("Auriel"),
  AZMO("Azmodan"),
  BUTCHER("The Butcher"),
  BW("Brightwing"),
  CASSIA("Cassia"),
  CHEN("Chen"),
  CHO("Cho"),
  CHROMIE("Chromie"),
  DEHAKA("Dehaka"),
  DIBBLES("Diablo"),
  DVA("D.Va"),
  ETC("E.T.C."),
  FALSTAD("Falstad"),
  GALL("Gall"),
  GARRY("Garrosh"),
  GAZ("Gazlowe"),
  GENJI("Genji"),
  GM("Greymane"),
  GULDAN("Gul'dan"),
  HAMMER("Sgt. Hammer"),
  ILLIDAN("Illidan"),
  JAINA("Jaina"),
  JO("Johanna"),
  KAEL("Kael'thas"),
  KERRI("Kerrigan"),
  KHARA("Kharazim"),
  KTZ("Kel'Thuzad"),
  LEORIC("Leoric"),
  LILI("Li Li"),
  LIMING("Li-Ming"),
  LUCIO("Lúcio"),
  LUNARA("Lunara"),
  MALF("Malfurion"),
  MALTH("Malthael"),
  MEDIC("Lt. Morales"),
  MEDIVH("Medivh"),
  MURADIN("Muradin"),
  MURKY("Murky"),
  NAZ("Nazeebo"),
  NOVA("Nova"),
  RAGNAROS("Ragnaros"),
  PROBIUS("Probius"),
  RAYNOR("Raynor"),
  REHGAR("Rehgar"),
  REXXAR("Rexxar"),
  SAMURO("Samuro"),
  SONYA("Sonya"),
  STITCHES("Stitches"),
  STUKOV("Stukov"),
  SYLV("Sylvanas"),
  TASS("Tassadar"),
  THRALL("Thrall"),
  TLV("The Lost Vikings"),
  TRACER("Tracer"),
  TYCHUS("Tychus"),
  TYRAEL("Tyrael"),
  TYRANDE("Tyrande"),
  UTHER("Uther"),
  VALEERA("Valeera"),
  VALLA("Valla"),
  VARIAN("Varian"),
  XUL("Xul"),
  ZAG("Zagara"),
  ZARYA("Zarya"),
  ZERA("Zeratul"),
  ZJ("Zul'jin"),
  ;

  public final String name;

  Hero(String name) {
    this.name = name;
  }

  public static Hero parseHero(String sourceString) {
    for (Hero value : Hero.values()) {
      if (value.name.equals(sourceString)) {
        return value;
      }
    }
    throw new IllegalStateException("Unable to map \"" + sourceString + "\" to a valid hero");
  }
}
