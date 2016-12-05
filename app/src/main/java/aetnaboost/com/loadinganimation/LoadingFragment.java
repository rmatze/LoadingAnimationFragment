package aetnaboost.com.loadinganimation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rmatze@itriagehealth.com on 12/3/16.
 */

public class LoadingFragment extends DialogFragment {

    private Handler mHandler;
    private ImageView ring1, ring2, ring3, ring4, bLogo;
    private TextView mLoading;
    private FragmentActivity mContext;

    private String mLoadingString = "Boosting";
    private SpannableString mSpannableString;

    private static int NUM_OF_RINGS = 4;
    private static int LOADING_STRING_LEN = 8;
    private static int RING_DELAY = 300;
    private static int LOGO_DELAY = 1250;
    private static int LOADING_DELAY = 275;
//(RING_DELAY * NUM_OF_RINGS) + LOGO_DELAY / LOADING_STRING_LEN;

    private int mIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_loading_layout, container);
        getDialog().setCanceledOnTouchOutside(true);

        mContext = getActivity();

        ring1 = (ImageView) view.findViewById(R.id.loading_ring_1);
        ring2 = (ImageView) view.findViewById(R.id.loading_ring_2);
        ring3 = (ImageView) view.findViewById(R.id.loading_ring_3);
        ring4 = (ImageView) view.findViewById(R.id.loading_ring_4);
        bLogo = (ImageView) view.findViewById(R.id.loading_b_logo);

        ring1.setVisibility(View.INVISIBLE);
        ring2.setVisibility(View.INVISIBLE);
        ring3.setVisibility(View.INVISIBLE);
        ring4.setVisibility(View.INVISIBLE);
        bLogo.setVisibility(View.INVISIBLE);

        mLoading = (TextView) view.findViewById(R.id.loading_textview);

        mSpannableString = new SpannableString(mLoadingString);

        mHandler = new Handler();
        mRingOneRunnable.run();
        //mLoadingRunnable.run();

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    Runnable mRingOneRunnable = new Runnable() {
        @Override
        public void run() {
            ring1.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));

            ring2.clearAnimation();
            ring3.clearAnimation();
            ring4.clearAnimation();
            bLogo.clearAnimation();

            mHandler.postDelayed(mRingTwoRunnable, RING_DELAY);
        }
    };

    Runnable mRingTwoRunnable = new Runnable() {
        @Override
        public void run() {
            ring2.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
            mHandler.postDelayed(mRingThreeRunnable, RING_DELAY);
        }
    };

    Runnable mRingThreeRunnable = new Runnable() {
        @Override
        public void run() {
            ring3.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
            mHandler.postDelayed(mRingFourRunnable, RING_DELAY);
        }
    };

    Runnable mRingFourRunnable = new Runnable() {
        @Override
        public void run() {
            ring4.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
            mHandler.postDelayed(mRingLogoRunnable, RING_DELAY);
        }
    };

    Runnable mRingLogoRunnable = new Runnable() {
        @Override
        public void run() {
            bLogo.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in));
            mHandler.postDelayed(mRingOneRunnable, LOGO_DELAY);
        }
    };

    Runnable mLoadingRunnable = new Runnable() {
        @Override
        public void run() {
            updateTextColor(mIndex);
            mIndex++;
            mHandler.postDelayed(mLoadingRunnable, LOADING_DELAY);
        }
    };

    public void updateTextColor(int index) {
        int position = index % LOADING_STRING_LEN;

        if (position == 0) {
            mSpannableString.setSpan(new ForegroundColorSpan(Color.GRAY), 0, LOADING_STRING_LEN, 0);
            mSpannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, position, 0);
            mLoading.setText(mSpannableString);
        }
        mSpannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, position + 1, 0);
        mLoading.setText(mSpannableString);
    }
}
