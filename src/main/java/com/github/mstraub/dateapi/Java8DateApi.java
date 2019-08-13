package com.github.mstraub.dateapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Java8DateApi {

    public static void main(String[] args) throws ParseException {
        System.out.println(Duration.ofSeconds(500));
        System.out.println(Duration.ofSeconds(500).toMillis()/1000);
        System.out.println(Duration.ofSeconds(500).getSeconds());
        
        parsePartialDates();
//        funWithZonedDateTime();
        // funWithLocalDateTime();
    }

    public static void funWithZonedDateTime() {
        ZonedDateTime nearMidnight = ZonedDateTime.of(2016, 10, 24, 23, 59, 59, 999, ZoneId.of("Europe/Vienna"));
        ZonedDateTime afterMidnight = ZonedDateTime.of(2016, 10, 25, 0, 10, 0, 0, ZoneId.of("Europe/Vienna"));
        
        System.out.println(nearMidnight);
        System.out.println(nearMidnight.getHour());
        
        nearMidnight = nearMidnight.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(nearMidnight);
        System.out.println(afterMidnight);
        System.out.println(nearMidnight.plus(10, ChronoUnit.MINUTES));
        System.out.println(Duration.between(nearMidnight, afterMidnight).toMinutes());
        
    }

    public static void funWithLocalDateTime() throws ParseException {
        String localTimeString = "2014-11-12T08:00:00";

        LocalDateTime ldt = LocalDateTime.parse(localTimeString);
        // long ldtMillis = ldt.toInstant(ZoneOffset.UTC).toEpochMilli();
        long ldtMillis = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(ldtMillis + " = " + ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf.parse(localTimeString);
        System.out.println(date.getTime() + " = " + date);

        Date in = new Date();
        LocalDateTime ldt2 = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public static void parsePartialDates() throws ParseException {
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2011-10-18T13:15:00+02:00;P0Y0M0DT0H15M0S");
        
    }
    
}
