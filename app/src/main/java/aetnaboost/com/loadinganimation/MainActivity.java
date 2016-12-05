package aetnaboost.com.loadinganimation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    private ImageView ring1, ring2, ring3, ring4, bLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                LoadingFragment mNoInternetDialogFragment = new LoadingFragment();
                try {
                    mNoInternetDialogFragment.show(fragmentManager, "loading");
                } catch (IllegalStateException ie) {

                }
            }
        });
    }
}
