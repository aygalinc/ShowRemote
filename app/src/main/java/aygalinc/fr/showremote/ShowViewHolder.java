package aygalinc.fr.showremote;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import aygalinc.fr.showremote.util.TvServerPath;

/**
 * Created by colin on 20/01/2018.
 */

public class ShowViewHolder extends RecyclerView.ViewHolder {

    private Show show;

    private ImageView itemImage;

    private TextView itemTitle;

    public ShowViewHolder(final View itemView) {
        super(itemView);

        itemImage = itemView.findViewById(R.id.item_image);
        itemTitle = itemView.findViewById(R.id.item_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                Intent intent = new Intent(itemView.getContext(), ShowDetailsActivity.class);
                //we send additional information the new activity can work with
                intent.putExtra("name", show.name);
                intent.putExtra("description", show.description);
                //we start the activity, we do not need a result
                itemView.getContext().startActivity(intent);
            }
        });
    }


    public void bindShow(Show show) {
        this.show = show;
        //Call to the server not asynchrone ... but all the cahcing is done by picasso
        Picasso.with(itemImage.getContext()).load(TvServerPath.SHOW_PATH+show.id+TvServerPath.SHOW_IMAGE_RELATIVE_PATH).centerCrop().fit().into(itemImage);
        this.itemTitle.setText(this.show.name);
    }
}
