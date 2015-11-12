package tm.davidwang.dwcinemaanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ImageView showimg;
    private RelativeLayout show_re;
    private View show_line;

    private Button back_btn;

    private LinearLayout show_layout;
    private TextView show_content;
    private TextView title_text_0,title_text_1;
    private View show_re_bg;

    private GridView gridview;
    private GridAdapter adapter;
    private ArrayList<GridModel> data;

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
        show_re_bg = (View)findViewById(R.id.show_re_bg);

        gridview = (GridView)findViewById(R.id.gridview);
        gridview.setOnItemClickListener(this);
        inData();

    }

    private void inData(){
        ObjectAnimator.ofFloat(gridview, "scaleX", 1, 1.3f).start();
        ObjectAnimator.ofFloat(gridview, "scaleY", 1, 1.3f).start();
        data = new ArrayList<GridModel>();
        for (int j = 0;j<10;j++){
            int num = j * 10;
            for (int i = 0 ;i< 10;i++){
                GridModel info = new GridModel();
                if (i == 2 || i == 7){
                    info.type_index = 2;
                }else{
                    info.type_index = 0;
                }
                data.add(info);
            }
        }
        adapter = new GridAdapter(MainActivity.this,data);
        gridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
                is_open = !is_open;
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GridModel info = data.get(position);
        if (info.type_index == 0){
            info.type_index = 1;
        }else if (info.type_index == 1){
            info.type_index = 0;
        }
        data.set(position,info);
        adapter.notifyDataSetChanged();
    }

    private void hideview(){
        gridview.setVisibility(View.VISIBLE);
        title_text_0.setText(getResources().getText(R.string.title_1));
        back_btn.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationY", 0, dip2px(-73)),
                ObjectAnimator.ofFloat(showimg, "rotationX", 0, -40),
                ObjectAnimator.ofFloat(showimg, "scaleX", 1, 0.9f),
                ObjectAnimator.ofFloat(showimg, "scaleY", 1, 0.0f),
                ObjectAnimator.ofFloat(show_content, "alpha", 1.0f, 0.0f),
                ObjectAnimator.ofFloat(back_btn, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(show_layout, "translationY", 0, dip2px(235)),
                ObjectAnimator.ofFloat(show_layout, "translationX", 0, dip2px(90)),
                ObjectAnimator.ofFloat(show_content, "translationX", 0, dip2px(65)),
                ObjectAnimator.ofFloat(title_text_1, "translationX", 0, dip2px(90)),
                ObjectAnimator.ofFloat(title_text_0, "translationX", 0, dip2px(70)),
                ObjectAnimator.ofFloat(gridview, "scaleX", 1.3f, 1),
                ObjectAnimator.ofFloat(gridview, "scaleY", 1.3f, 1),
                ObjectAnimator.ofFloat(gridview, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(show_re_bg, "scaleX", 1, 0.5f)
        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                show_line.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(duration).start();
    }

    private void showview(){
        show_line.setVisibility(View.GONE);
        title_text_0.setText(getResources().getText(R.string.title_0));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationY", dip2px(-73), 0),
                ObjectAnimator.ofFloat(showimg, "rotationX", -40, 0),
                ObjectAnimator.ofFloat(showimg, "scaleX", 0.9f, 1),
                ObjectAnimator.ofFloat(showimg, "scaleY", 0.0f, 1),
                ObjectAnimator.ofFloat(show_content, "alpha", 0.0f, 1.0f),
                ObjectAnimator.ofFloat(back_btn, "alpha", 1.0f, 0.0f),
                ObjectAnimator.ofFloat(show_layout, "translationY", dip2px(235),0),
                ObjectAnimator.ofFloat(show_layout, "translationX", dip2px(90),0),
                ObjectAnimator.ofFloat(show_content, "translationX", dip2px(70),0),
                ObjectAnimator.ofFloat(title_text_1, "translationX",dip2px(90),0),
                ObjectAnimator.ofFloat(title_text_0, "translationX",dip2px(70),0),
                ObjectAnimator.ofFloat(gridview, "scaleX", 1,1.3f),
                ObjectAnimator.ofFloat(gridview, "scaleY",1, 1.3f),
                ObjectAnimator.ofFloat(gridview, "alpha", 1.0f,0),
                ObjectAnimator.ofFloat(show_re_bg, "scaleX", 0.5f,1)

        );
        set.setDuration(duration).start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                gridview.setVisibility(View.GONE);
                back_btn.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
