package aygalinc.fr.showremote;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by colin on 20/01/2018.
 */

public class RecylerAdapter extends RecyclerView.Adapter<ShowViewHolder> {

    private final Set showSet;

    RecylerAdapter(Set<Show> shows){
        showSet = shows;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        ShowViewHolder viewHolder = new ShowViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        Iterator<Show> showIterator = showSet.iterator();
        Show show = null;
        for (int i = 0; i <= position;i ++){
            show = showIterator.next();
        }
        if (show != null){
            holder.bindShow(show);
        }
    }

    @Override
    public int getItemCount() {
        return showSet.size();
    }
}
