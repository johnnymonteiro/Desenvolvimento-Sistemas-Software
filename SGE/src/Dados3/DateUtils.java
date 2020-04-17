// http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html
// IMAGEM RELATORIO: http://image.slidesharecdn.com/finalmentejavasabetrabalharcomdataehora-140517171549-phpapp01/95/finalmente-java-sabe-trabalhar-com-data-e-hora-32-638.jpg?cb=1400350523
//http://www.javaworld.com/article/2078757/java-se/java-se-java-101-the-next-generation-it-s-time-for-a-change.html?page=4

package Dados3;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface DateUtils {

    default Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

    default Date asDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

    default LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

    default LocalDateTime asLocalDateTime(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
    default java.sql.Date asSQLDate(Date date){
    return new java.sql.Date(date.getTime());
    }
    
    
}