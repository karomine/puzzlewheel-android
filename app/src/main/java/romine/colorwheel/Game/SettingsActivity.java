package romine.colorwheel.Game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import romine.colorwheel.R;

public class SettingsActivity extends AppCompatActivity {

    private SeekBar difficultyBar;
    private SeekBar sizeBar;
    private TextView difficultyDisplay;
    private TextView sizeDisplay;
    private int difficulty;
    private int size;
    private Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        size = intent.getIntExtra(GameActivity.SIZE_VALUE, 4);
        difficulty = intent.getIntExtra(GameActivity.DIFFICULTY_VALUE, 2);

        result = new Intent();

        difficultyBar = (SeekBar) findViewById(R.id.game_difficulty);
        sizeBar = (SeekBar) findViewById(R.id.board_size);

        difficultyDisplay = (TextView) findViewById(R.id.difficulty_display);
        sizeDisplay = (TextView) findViewById(R.id.size_display);

        updateDifficulty();
        updateSize();

        AdView mAdView = (AdView) findViewById(R.id.ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        difficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                difficulty = progress + 1;
                updateDifficulty();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                size = progress + 2;
                updateSize();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateDifficulty() {
        difficultyDisplay.setText("Difficulty: " + difficulty);
        difficultyBar.setProgress(difficulty - 1);
        updateIntent();
    }

    private void updateSize() {
        sizeDisplay.setText("Board size: " + size);
        sizeBar.setProgress(size - 2);
        updateIntent();
    }

    private void updateIntent() {
        result.putExtra(GameActivity.DIFFICULTY_VALUE, difficulty);
        result.putExtra(GameActivity.SIZE_VALUE, size);
        setResult(GameActivity.SETTINGS_REQUEST, result);
    }
}
