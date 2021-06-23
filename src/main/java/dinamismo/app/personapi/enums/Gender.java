package dinamismo.app.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    F("Female"),
    M("male");

    private final String description;
}
