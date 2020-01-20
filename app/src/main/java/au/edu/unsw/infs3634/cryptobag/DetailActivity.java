package au.edu.unsw.infs3634.cryptobag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mSymbol;
    private TextView mValue;
    private TextView mChange1h;
    private TextView mChange24h;
    private TextView mChange7d;
    private TextView mMarketcap;
    private TextView mVolume;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mName = findViewById(R.id.tvName);
        mSymbol = findViewById(R.id.tvSymbol);
        mValue = findViewById(R.id.tvValueField);
        mChange1h = findViewById(R.id.tvChange1hField);
        mChange24h = findViewById(R.id.tvChange24hField);
        mChange7d = findViewById(R.id.tvChange7dField);
        mMarketcap = findViewById(R.id.tvMarketcapField);
        mVolume = findViewById(R.id.tvVolumeField);
        mSearch = findViewById(R.id.ivSearch);

        Intent intent = getIntent();
        String coinSymbol = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        ArrayList<Coin> coins = Coin.getCoins();
        for(final Coin coin : coins) {
            if(coin.getSymbol().equals(coinSymbol)) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                setTitle(coin.getName());
                mName.setText(coin.getName());
                mSymbol.setText(coin.getSymbol());
                mValue.setText(formatter.format(coin.getValue()));
                mChange1h.setText(String.valueOf(coin.getChange1h()) + " %");
                mChange24h.setText(String.valueOf(coin.getChange24h()) + " %");
                mChange7d.setText(String.valueOf(coin.getChange7d()) + " %");
                mMarketcap.setText(formatter.format(coin.getMarketcap()));
                mVolume.setText(formatter.format(coin.getVolume()));
                mSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchCoin(coin.getName());
                    }
                });
            }
        }
    }

    private void searchCoin(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + name));
        startActivity(intent);
    }
}
