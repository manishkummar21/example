package rupeek.com.rupeek;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import rupeek.com.rupeek.databinding.ActivityDetailBinding;
import rupeek.com.rupeek.model.Locations;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private Locations locations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        if (getIntent().getExtras() == null)
            finish();

        locations = (Locations) getIntent().getSerializableExtra("detail");

        setSupportActionBar(binding.collapsingToolbar);


        binding.collapsingToolbarLayout.setTitleEnabled(false);

        getSupportActionBar().setTitle(locations.getPlace());

        binding.setLocation(locations);


    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
