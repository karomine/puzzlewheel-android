package romine.colorwheel.Game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.shapes.PathShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.R;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView view;
    private Canvas movingPieceCanvas;
    private Canvas backgroundCanvas;
    private Display display;
    private Bitmap background;
    private PuzzleGenerator pg;
    private int scale = 990;

    private BasePiece movingPiece;
    private float moveXOffset;
    private float moveYOffset;
    private int xPos;
    private int yPos;
    private int[] loc = new int[2];

    private SensorManager manager;
    private Sensor sensor;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private boolean gyroscopePaused = false;
    private float timePaused;

    public static final String DIFFICULTY_VALUE = "game difficulty";
    public static final String SIZE_VALUE = "game size";
    public static final int SETTINGS_REQUEST = 1;
    private int gameDifficulty = 2;
    private int boardSize = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        manager.registerListener(this, sensor, Sensor.TYPE_GYROSCOPE);

        background = Bitmap.createBitmap(scale + 6, (scale*3)/2 + 6, Bitmap.Config.ARGB_8888);
        backgroundCanvas = new Canvas(background);
        view = (ImageView) findViewById(R.id.image_view);
        view.setImageBitmap(background);

        pg = new PuzzleGenerator(boardSize, gameDifficulty);
        setNewGame();

        ImageButton moveLeft = (ImageButton) findViewById(R.id.move_left);
        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.searchPieceLeft();
            }
        });

        ImageButton moveRight = (ImageButton) findViewById(R.id.move_right);
        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.searchPieceRight();
            }
        });


        AdView mAdView = (AdView) findViewById(R.id.ad);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ImageButton newGame = (ImageButton) findViewById(R.id.new_game);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.newPuzzle(pg.generatePuzzle());
                display.display();
                view.invalidate();
            }
        });

        ImageButton settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SettingsActivity.class);
                intent.putExtra(DIFFICULTY_VALUE, gameDifficulty);
                intent.putExtra(SIZE_VALUE, boardSize);
                startActivityForResult(intent, SETTINGS_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_REQUEST) {
            gameDifficulty = data.getIntExtra(DIFFICULTY_VALUE, 2);
            boardSize = data.getIntExtra(SIZE_VALUE, 4);
            pg.setSettings(boardSize, gameDifficulty);
            setNewGame();
        }
    }

    public void setNewGame() {
        Puzzle puzzle = pg.generatePuzzle();
        display = new Display(boardSize, scale/boardSize, puzzle, backgroundCanvas);
        display.display();
    }


    void displayMovingPiece(BasePiece piece, float xPos, float yPos, float moveXOffset, float moveYOffset) {
        if (piece != null) {
            Bitmap foreground = Bitmap.createBitmap(background);
            movingPieceCanvas = new Canvas(foreground);

            PathShape shape = piece.getMovingShape(xPos - moveXOffset, yPos - moveYOffset, scale/boardSize);
            shape.draw(movingPieceCanvas, piece.getColor());
            view.setImageBitmap(foreground);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        view.getLocationInWindow(loc);
        xPos = (int) event.getRawX() - loc[0];
        yPos = (int) event.getRawY() - loc[1];

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                float[] offsets = display.getOffsets(xPos, yPos);
                if (offsets != null) {
                    moveXOffset = offsets[0];
                    moveYOffset = offsets[1];
                    movingPiece = display.canvasClick(xPos, yPos, moveXOffset, moveYOffset);
                    displayMovingPiece(movingPiece, xPos, yPos, moveXOffset, moveYOffset);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (movingPiece != null) {
                    display.placePiece(movingPiece, xPos - moveXOffset, yPos - moveYOffset, moveXOffset, moveYOffset);
                    view.setImageBitmap(background);
                    movingPiece = null;
                    if (display.isFinished()) {
                        Toast.makeText(this, "FINISHED", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                displayMovingPiece(movingPiece, xPos, yPos, moveXOffset, moveYOffset);
                break;
        }
        view.invalidate();
        return false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (gyroscopePaused) {
            if (timestamp - timePaused > 500000000) {
                gyroscopePaused = false;
            }
        } else {

            if (timestamp != 0) {
                final float dT = (event.timestamp - timestamp) * NS2S;
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];

                float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

                if (omegaMagnitude > Double.POSITIVE_INFINITY) {
                    axisX /= omegaMagnitude;
                    axisY /= omegaMagnitude;
                    axisZ /= omegaMagnitude;
                }

                float thetaOverTwo = omegaMagnitude * dT / 2.0f;
                float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
                float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
                deltaRotationVector[0] = sinThetaOverTwo * axisX;
                deltaRotationVector[1] = sinThetaOverTwo * axisY;
                deltaRotationVector[2] = sinThetaOverTwo * axisZ;
                deltaRotationVector[3] = cosThetaOverTwo;

            }

            if (deltaRotationVector[0] < -.1 || deltaRotationVector[0] > .1 ||
                    deltaRotationVector[1] < -.1 || deltaRotationVector[1] > .1 ||
                    deltaRotationVector[2] < -.1 || deltaRotationVector[2] > .1) {

                if (deltaRotationVector[0] < -.1 || deltaRotationVector[0] > .1) {
                    display.flipVertically();
                } else if (deltaRotationVector[1] < -.1 || deltaRotationVector[1] > .1) {
                    display.flipHorizontally();
                } else if (deltaRotationVector[2] < -.1) {
                    display.rotateRight();
                } else if (deltaRotationVector[2] > .1) {
                    display.rotateLeft();
                }
                gyroscopePaused = true;
                timePaused = timestamp;
                view.invalidate();
            }
        }
        timestamp = event.timestamp;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
