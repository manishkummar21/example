package rupeek.com.rupeek;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import rupeek.com.rupeek.adapter.TravelMateAdapter;
import rupeek.com.rupeek.databinding.ActivityMainBinding;
import rupeek.com.rupeek.model.Locations;
import rupeek.com.rupeek.model.Travelmate;
import rupeek.com.rupeek.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.IMainView, TravelMateAdapter.AdapterListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainPresenter mainPresenter = new MainPresenter(this, this);

        //setting layout manager for recyerlerview
        binding.travellist.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.travellist.setLayoutManager(mLayoutManager);


        mainPresenter.loadData();


    }


    @Override
    public void sendresult(Travelmate travelmate) {
        TravelMateAdapter adapter = new TravelMateAdapter(travelmate.getCust_name(), travelmate.getLocations(), this);
        binding.travellist.setAdapter(adapter);

    }

    @Override
    public void onItemClicked(Locations location) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("detail", location);
        startActivity(intent);
    }
}
