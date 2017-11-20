package kysersozelee.part2;

import kysersozelee.part3.Country;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Getter
    public String firstName;
    @Getter
    public String lastName;
    @Getter
    public int age;
    @Getter
    public Country country;
}
