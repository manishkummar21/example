package rupeek.com.rupeek.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rupeek.com.rupeek.R;
import rupeek.com.rupeek.databinding.ItemHeadingBinding;
import rupeek.com.rupeek.databinding.ItemTravelmateBinding;
import rupeek.com.rupeek.model.Locations;

public class TravelMateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;


    private List<Locations> locations;
    private LayoutInflater layoutInflater;
    private AdapterListener listener;
    private String name;


    public TravelMateAdapter(String name, List<Locations> locations, AdapterListener listener) {
        this.name = name;
        this.locations = locations;
        this.listener = listener;
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemTravelmateBinding binding;

        MyItemViewHolder(final ItemTravelmateBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }


    public class MyHeaderViewHolder extends RecyclerView.ViewHolder {

        private final ItemHeadingBinding binding;

        MyHeaderViewHolder(final ItemHeadingBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == TYPE_HEADER) {
            ItemHeadingBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_heading, parent, false);
            return new MyHeaderViewHolder(binding);
        } else {
            ItemTravelmateBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_travelmate, parent, false);
            return new MyItemViewHolder(binding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHeaderViewHolder) {

            MyHeaderViewHolder myHeaderViewHolder = (MyHeaderViewHolder) holder;
            myHeaderViewHolder.binding.setCustomername(name);

        } else {
            MyItemViewHolder myItemViewHolder = (MyItemViewHolder) holder;

            myItemViewHolder.binding.setLocation(locations.get(position));
            myItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClicked(locations.get(position));
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        //adding the header so increment by 1
        return locations.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }


    public interface AdapterListener {
        void onItemClicked(Locations location);
    }
}
