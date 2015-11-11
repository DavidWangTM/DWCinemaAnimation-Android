package tm.davidwang.dwcinemaanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView showimg;
    private RelativeLayout show_re;
    private View show_line;

    private Button back_btn;


    private LinearLayout show_layout;
    private TextView show_content;
    private TextView title_text_0,title_text_1;

    final int duration = 400;
    private boolean is_open = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showimg = (ImageView)findViewById(R.id.showimg);
        show_line = (View)findViewById(R.id.show_line);
        show_re = (RelativeLayout)findViewById(R.id.show_re);
        back_btn = (Button)findViewById(R.id.back_btn);
        show_re.setOnClickListener(this);
        back_btn.setOnClickListener(this);

        show_layout = (LinearLayout)findViewById(R.id.show_layout);
        show_content = (TextView)findViewById(R.id.show_content);
        title_text_0 = (TextView)findViewById(R.id.title_text_0);
        title_text_1 = (TextView)findViewById(R.id.title_text_1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_re:
                if (is_open){
                    hideview();
                }else{
                    showview();
                }
                is_open = !is_open;
                break;
            case R.id.back_btn:
                showview();
                break;
        }
    }

    private void hideview(){
        title_text_0.setText(getResources().getText(R.string.title_1));
        back_btn.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationY", 0, dip2px(-73)),
                ObjectAnimator.ofFloat(showimg, "rotationX", 0, -40),
                ObjectAnimator.ofFloat(showimg, "scaleX", 1, 0.9f),
                ObjectAnimator.ofFloat(showimg, "scaleY", 1, 0.0f),
                ObjectAnimator.ofFloat(show_line, "alpha", 0, 1.0f),
                ObjectAnimator.ofFloat(show_content, "alpha", 1.0f, 0.0f),
                ObjectAnimator.ofFloat(back_btn, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(show_layout, "translationY", 0, dip2px(235)),
                ObjectAnimator.ofFloat(show_layout, "translationX", 0, dip2px(90))
        );
        set.setDuration(duration).start();
    }

    private void showview(){
        back_btn.setVisibility(View.GONE);
        title_text_0.setText(getResources().getText(R.string.title_0));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationY", dip2px(-73), 0),
                ObjectAnimator.ofFloat(showimg, "rotationX", -40, 0),
                ObjectAnimator.ofFloat(showimg, "scaleX", 0.9f, 1),
                ObjectAnimator.ofFloat(showimg, "scaleY", 0.0f, 1),
                ObjectAnimator.ofFloat(show_line, "alpha", 1.0f, 0),
                ObjectAnimator.ofFloat(show_content, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(back_btn, "alpha", 1.0f, 0.0f),
                ObjectAnimator.ofFloat(show_layout, "translationY", dip2px(235),0),
                ObjectAnimator.ofFloat(show_layout, "translationX", dip2px(90),0)

        );
        set.setDuration(duration).start();
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
