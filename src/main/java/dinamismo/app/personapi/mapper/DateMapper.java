package dinamismo.app.personapi.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateMapper {

    public String asString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                            : "";
    }

    public LocalDate asDate(String date) {
            return  date != "" ? LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")) 
                               : null;
    }
    
}