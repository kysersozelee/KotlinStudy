package com.git.kysersozelee;

import com.git.kysersozelee.Country;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private int age;
    @Getter
    private Country country;
}
