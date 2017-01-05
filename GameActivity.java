package romine.colorwheel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import romine.colorwheel.Board.GameBoard;
import romine.colorwheel.Board.PaintColors;
import romine.colorwheel.Shapes.TileSectionPaths;

public class GameActivity extends AppCompatActivity {

    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bitmap b = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view = (ImageView) findViewById(R.id.image_view);
        view.setImageBitmap(b);

        GameBoard board = new GameBoard(0, 0, 250, 4, c);
        board.display();



    }
}
