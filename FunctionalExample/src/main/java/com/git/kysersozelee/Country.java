package com.git.kysersozelee;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public enum Country {
    KR("Korea", "ES"),
    US("USA", "WS"),
    PG("Portugal", "WS");

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String region;
}
