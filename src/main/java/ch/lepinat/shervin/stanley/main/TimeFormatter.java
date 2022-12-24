package ch.lepinat.shervin.stanley.main;

public class TimeFormatter {

    public static String format(long timer, char colorcode) {
        int seconds = (int) (timer / 1000) % 60 ;
        int minutes = (int) ((timer / (1000L*60L)) % 60);
        int hours   = (int) ((timer / (1000L*60L*60L)) % 24);
        int days    = (int) ((timer / (1000L*60L*60L*24L)) % 30);
        int month   = (int) ((timer / (1000L*60L*60L*24L*30L)));
        if (month == 0 && days == 0 && hours == 0 && minutes == 0) {
            return "§6" + seconds + " §" + colorcode + "Sekunden";
        } else if (month == 0 && days == 0 && hours == 0) {
            return "§6" + minutes + " §" + colorcode + "Minuten und §6" + seconds + " §" + colorcode + "Sekunden";
        } else if (month == 0 && days == 0)  {
            return "§6" + hours + " §" + colorcode + "Stunden §6" + minutes + " §" + colorcode + "Minuten und §6" + seconds + " §" + colorcode + "Sekunden";
        } else if (month == 0){
            return "§6" + days + " §" + colorcode + "Tage §6" + hours + " §" + colorcode + "Stunden §6" + minutes + " §" + colorcode + "Minuten und §6" + seconds + " §" + colorcode + "Sekunden";
        } else {
            return "§6" + month + " §" + colorcode + "Monate §6" + days + " §" + colorcode + "Tage §6" + hours + " §" + colorcode + "Stunden §6" + minutes + " §" + colorcode + "Minuten und §6" + seconds + " §" + colorcode + "Sekunden";
        }
    }
}
