package by.yakivan.bitsandpizzas;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class CaptionedImageAdapter extends RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder> {
    private String[] captions;
    private int[] imageIds;

    public CaptionedImageAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public CaptionedImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        CardView cv = (CardView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptionedImageAdapter.ViewHolder viewHolder, int i) {
        CardView cardView = viewHolder.cardView;

        ImageView image = cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[i]);
        image.setImageDrawable(drawable);
        image.setContentDescription(captions[i]);

        TextView text = cardView.findViewById(R.id.info_text);
        text.setText(captions[i]);
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
