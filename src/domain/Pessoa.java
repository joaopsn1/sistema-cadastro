package domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Pessoa {
    String name ;
    String email;
    Integer age;
    Double height;
}
