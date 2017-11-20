package kysersozelee.part3;

public enum Country {
    KR("Korea", "ES"),
    US("USA", "WS"),
    PG("Porutgal", "WS");

    String name;
    String region;
    Country(String name, String region) {
        this.name = name;
        this.region = region;
    }
}
