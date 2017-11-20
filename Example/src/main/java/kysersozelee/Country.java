package kysersozelee;

public enum Country {
    KR("Korea" ,"ES"),
    US("USA", "WS"),
    CN("China", "ES");

    String name;
    String region;

    Country(String name, String region) {
        this.name = name;
        this.region = region;
    }
}
