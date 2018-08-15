package ru.familion.ivapps_test.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import ru.familion.ivapps_test.R;
import ru.familion.ivapps_test.models.entity.Data;

import java.util.List;


/**
 * Data adapter for currency list
 */
public class CryptoCurrencyRecyclerAdapter extends RecyclerView.Adapter<CryptoCurrencyRecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Data> _currencyList;
    /**
     * List row click listener
     */
    private static AdapterClickListener clickListener;


    /**
     * Adapter constructor
     * @param context       context
     * @param currencyList  list with data
     */
    public CryptoCurrencyRecyclerAdapter(Context context, List<Data> currencyList) {
        inflater = LayoutInflater.from(context);
        _currencyList = currencyList;
    }

    /**
     * List rows ViewHolder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imgCurrencyIcon;
        final TextView txtCurrencySymbol;
        final TextView txtCurrencyName;
        final TextView txtCurrencyPrice;
        final TextView txt24hValue;
        final TextView txt7dValue;

        /**
         * Constructor
         * @param view ViewHolder view
         */
        public ViewHolder(View view) {
            super(view);

            imgCurrencyIcon = (ImageView)view.findViewById(R.id.imgCurrencyIcon);
            txtCurrencySymbol = (TextView)view.findViewById(R.id.txtCurrencySymbol);
            txtCurrencyName = (TextView)view.findViewById(R.id.txtCurrencyName);
            txtCurrencyPrice = (TextView)view.findViewById(R.id.txtCurrencyPrice);
            txt24hValue = (TextView)view.findViewById(R.id.txt24hValue);
            txt7dValue = (TextView)view.findViewById(R.id.txt7dValue);

            view.setOnClickListener(this);
        }

        /**
         * Row ViewHolder click handler
         * @param view ViewHolder view
         */
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    /**
     * ViewHolder create event handler
     * @param parent    row parent
     * @param viewType  type of view
     * @return          ViewHolder instance
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.currency_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binding rows event handler
     * @param holder    row ViewHolder instance
     * @param position  row position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data currencyData = _currencyList.get(position);

        // fill row view from currency data
        int iconResourceId = getDrawableResourceId(currencyData.getSymbol().toLowerCase());
        if (iconResourceId == 0) {
            iconResourceId = R.drawable.fail;
        }
        holder.imgCurrencyIcon.setImageDrawable(inflater.getContext().getResources().getDrawable(iconResourceId));

        holder.txtCurrencySymbol.setText(currencyData.getSymbol());
        holder.txtCurrencyName.setText(currencyData.getName());
        holder.txtCurrencyPrice.setText(currencyData.getQuotes().getUSD().getPrice().toString().substring(0,7) + " $");
        holder.txt24hValue.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange24h()) + " %");
        holder.txt24hValue.setTextColor(currencyData.getQuotes().getUSD().getPercentChange24h()<0 ? Color.RED : Color.CYAN);
        holder.txt7dValue.setText(String.format("%.2f", currencyData.getQuotes().getUSD().getPercentChange7d()) + " %");
        holder.txt7dValue.setTextColor(currencyData.getQuotes().getUSD().getPercentChange7d()<0 ? Color.RED : Color.CYAN);
    }

    /**
     * Total list items count
     * @return
     */
    @Override
    public int getItemCount() {
        return _currencyList.size();
    }

    /**
     * Set row click listener
     * @param clickListener
     */
    public void setOnItemClickListener(AdapterClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Get drawable resource id by image name for make currency icon
     * @param resourceName  image name
     * @return              drawable resource id
     */
    private int getDrawableResourceId(String resourceName) {
        return inflater.getContext().getResources().getIdentifier(resourceName, "drawable", inflater.getContext().getPackageName());
    }

}