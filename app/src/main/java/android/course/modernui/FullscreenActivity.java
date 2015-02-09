package android.course.modernui;

import android.course.modernui.util.SystemUiHider;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.rgb;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        final List<ColorBox> colorBoxes = colorBoxes();
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (ColorBox colorBox : colorBoxes) {
                    int red = colorBox.getRed();
                    int green = colorBox.getGreen();
                    int blue = colorBox.getBlue();
                    if (red == green && red == blue) {
                        continue;
                    }

                    colorBox.setBackgroundColor(rgb(
                            inverse(red, progress),
                            inverse(green, progress),
                            inverse(blue, progress)));
                    colorBox.invalidate();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    static int inverse(int color, int progress) {
        return color + (int) ((255f - (2 * color)) * (progress / 100f));
    }

    private List<ColorBox> colorBoxes() {
        List<ColorBox> colorBoxes = new ArrayList<>();
        colorBoxes.add(new ColorBox(findViewById(R.id.tv1)));
        colorBoxes.add(new ColorBox(findViewById(R.id.tv2)));
        colorBoxes.add(new ColorBox(findViewById(R.id.tv3)));
        colorBoxes.add(new ColorBox(findViewById(R.id.tv4)));
        colorBoxes.add(new ColorBox(findViewById(R.id.tv5)));

        return colorBoxes;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private class ColorBox {
        private final View view;
        private final int red;
        private final int green;
        private final int blue;

        public ColorBox(View view) {
            this.view = view;

            int color = ((ColorDrawable) view.getBackground()).getColor();
            red = Color.red (color);
            green = Color.green(color);
            blue = Color.blue(color);
        }

        public void setBackgroundColor(int color) {
            view.setBackgroundColor(color);
        }

        public void invalidate() {
            view.invalidate();
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }
    }
}
