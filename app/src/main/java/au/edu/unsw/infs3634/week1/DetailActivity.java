package au.edu.unsw.infs3634.week1;

import android.content.Intent;
import java.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import static au.edu.unsw.infs3634.week1.Coin.searchCoin;


public class DetailActivity extends AppCompatActivity {
//    private TextView nDetailMessage;
//    private Button nShowVideoButton;
    private TextView nName;
    private TextView nSymbol;
    private TextView nValue;
    private TextView nChange1h;
    private TextView nChange24h;
    private TextView nChange7d;
    private TextView nMarketcap;
    private TextView nVolume;
    private ImageView nSearch;

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nName = findViewById(R.id.tvName);
        nSymbol = findViewById(R.id.tvSymbol);
        nValue = findViewById(R.id.tvValueField);
        nChange1h = findViewById(R.id.tvChange1hField);
        nChange24h = findViewById(R.id.tvChange24hField);
        nChange7d = findViewById(R.id.tvChange7dField);
        nMarketcap = findViewById(R.id.tvMarketcapField);
        nVolume = findViewById(R.id.tvVolumeField);
        nSearch = findViewById(R.id.ivSearch);

        Intent intent = getIntent();
        String coinSymbol = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Log.i(TAG, "Coin-Symbol = " + coinSymbol);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        ArrayList<Coin> coins = Coin.getCoins();

        final Coin coin = Coin.searchCoin(coinSymbol);

        nName.setText(coin.getName());
        nSymbol.setText(coin.getSymbol());
        nValue.setText(formatter.format(coin.getValue()));
        nChange1h.setText((coin.getChange1h()) + "%");
        nChange24h.setText((coin.getChange24h()) + "%");
        nChange7d.setText((coin.getChange7d()) + "%");
        nMarketcap.setText(formatter.format(coin.getMarketcap()));
        nVolume.setText(formatter.format(coin.getVolume()));
        nSearch.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v){
            searchCoin(coin.getName());
        }
    });

//        nDetailMessage = findViewById(R.id.tvdetailMessage);
//        nDetailMessage.setText(message);
//
//        nShowVideoButton = findViewById(R.id.btnShowVideo);
//        nShowVideoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showVideo("https://www.youtube.com/watch?v=-iIbc-h9ijc");
//            }
//        });
    }

    private void showVideo(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
    }
}
