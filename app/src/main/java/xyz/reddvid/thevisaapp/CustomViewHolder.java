package xyz.reddvid.thevisaapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomViewHolder extends RecyclerView.ViewHolder{
    private TextView simpleTextView;
    private ImageView flagImage;
    private TextView visaTextView;
    private TextView daysTextView;

    public CustomViewHolder(final View itemView) {
        super(itemView);
        simpleTextView = itemView.findViewById(R.id.simple_text);
        flagImage = itemView.findViewById(R.id.imageView);
        visaTextView = itemView.findViewById(R.id.textView_visa);
        daysTextView =  itemView.findViewById(R.id.textView_days);
    }

    public void bindData(final Countries viewModel) {
        simpleTextView.setText(viewModel.getSimpleText());
        flagImage.setImageResource(viewModel.getFlagPath());
        visaTextView.setText(viewModel.getVisaText());
        daysTextView.setText(viewModel.getDaysText());
    }
}
