package dinamismo.app.personapi.utils;

import dinamismo.app.personapi.dto.request.PhoneDTO;
import dinamismo.app.personapi.entity.Phone;
import dinamismo.app.personapi.enums.PhoneType;

public class PhoneUtils {
    
    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
