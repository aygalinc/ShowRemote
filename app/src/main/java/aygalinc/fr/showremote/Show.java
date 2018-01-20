package aygalinc.fr.showremote;

import android.support.annotation.NonNull;

/**
 * Created by colin on 20/01/2018.
 */

public class Show implements Comparable<Show> {

    long id;

    String name;

    String description;

    public Show(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int compareTo(@NonNull Show o) {
        if (id > o.id){
            return 1;
        }else if (id < o.id){
            return -1;
        }else {
            return 0;
        }
    }
}
