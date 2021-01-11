package com.test.duonavigationdrawer.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.duonavigationdrawer.R;


public class DuoOptionView extends RelativeLayout {
    private OptionViewHolder mOptionViewHolder;

    private static final float ALPHA_CHECKED = 1f;
    private static final float ALPHA_UNCHECKED = 0.5f;

    private boolean mIsSideSelectorEnabled = false;
    private boolean mIsSelectorEnabled = false;

    public DuoOptionView(Context context) {
        this(context, null);
    }

    public DuoOptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DuoOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        ViewGroup rootView = (ViewGroup) inflate(getContext(), R.layout.duo_view_option, this);

        mOptionViewHolder = new OptionViewHolder(rootView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        setSelected(isSelected());
    }

    /**
     * Check if the side selector is enabled or not.
     *
     * @return True if the side selector is enabled.
     */
    public boolean isSideSelectorEnabled() {
        return mIsSideSelectorEnabled;
    }

    /**
     * Check if the selector is enabled or not.
     *
     * @return True if the selector is enabled.
     */
    public boolean isSelectorEnabled() {
        return mIsSelectorEnabled;
    }

    /**
     * Set the option view side selector enabled.
     * By default a red rectangle in front of the option text and when enabled
     * in front of the selector.
     */
    public void setSideSelectorEnabled(boolean sideSelectorEnabled) {
        mIsSideSelectorEnabled = sideSelectorEnabled;
        invalidate();
        requestLayout();
    }

    /**
     * Set the option view selector enabled.
     * By default a little white circle in front of the option text.
     */
    public void setSelectorEnabled(boolean selectorEnabled) {
        mIsSelectorEnabled = selectorEnabled;
        invalidate();
        requestLayout();
    }

    /**
     * Set the option view as selected.
     * Using the wishes of the programmer.
     * By default only makes the option text white.
     */
    public void setSelected(boolean selected) {
        if (selected) {
            mOptionViewHolder.mTextViewOption.setAlpha(ALPHA_CHECKED);
            if (mIsSelectorEnabled) {
                mOptionViewHolder.mImageViewSelector.setVisibility(VISIBLE);
                mOptionViewHolder.mImageViewSelector.setAlpha(ALPHA_CHECKED);
            } else {
                mOptionViewHolder.mImageViewSelector.setVisibility(GONE);
            }
            if (mIsSideSelectorEnabled) {
                mOptionViewHolder.mImageViewSelectorSide.setVisibility(VISIBLE);
            }
        } else {
            mOptionViewHolder.mTextViewOption.setAlpha(ALPHA_UNCHECKED);
            if (mIsSelectorEnabled) {
                mOptionViewHolder.mImageViewSelector.setVisibility(VISIBLE);
                mOptionViewHolder.mImageViewSelector.setAlpha(ALPHA_UNCHECKED);
            } else {
                mOptionViewHolder.mImageViewSelector.setVisibility(GONE);
            }
            if (mIsSideSelectorEnabled) {
                mOptionViewHolder.mImageViewSelectorSide.setVisibility(GONE);
            }
        }
    }

    /**
     * Check if the option view is selected or not..
     */
    public boolean isSelected() {
        return mOptionViewHolder.mTextViewOption.getAlpha() == ALPHA_CHECKED;
    }

    /**
     * Binds the option view with it's content
     */
    public void bind(String optionText) {
        mOptionViewHolder.mTextViewOption.setText(optionText);
        mOptionViewHolder.mTextViewOption.setAlpha(ALPHA_UNCHECKED);
        mOptionViewHolder.mImageViewSelector.setVisibility(GONE);
    }

    /**
     * Binds the option view with it's content
     */
    public void bind(String optionText, @Nullable Drawable selectorDrawable) {
        mOptionViewHolder.mTextViewOption.setText(optionText);
        mOptionViewHolder.mTextViewOption.setAlpha(ALPHA_UNCHECKED);
        if (selectorDrawable != null) {
            mOptionViewHolder.mImageViewSelector.setImageDrawable(selectorDrawable);
        }
        mOptionViewHolder.mImageViewSelector.setAlpha(ALPHA_UNCHECKED);
        setSelectorEnabled(true);
    }

    /**
     * Binds the option view with it's content
     */
    public void bind(String optionText, @Nullable Drawable selectorDrawable, @Nullable Drawable selectorSideDrawable) {
        mOptionViewHolder.mTextViewOption.setText(optionText);
        mOptionViewHolder.mTextViewOption.setAlpha(ALPHA_UNCHECKED);
        if (selectorDrawable != null) {
            mOptionViewHolder.mImageViewSelector.setImageDrawable(selectorDrawable);
        }
        mOptionViewHolder.mImageViewSelector.setAlpha(ALPHA_UNCHECKED);
        if (selectorSideDrawable != null) {
            mOptionViewHolder.mImageViewSelectorSide.setImageDrawable(selectorSideDrawable);
        }
        setSelectorEnabled(true);
        setSideSelectorEnabled(true);
    }

    /**
     * View holder that holds the views for this layout.
     */
    private class OptionViewHolder {
        private TextView mTextViewOption;
        private ImageView mImageViewSelector;
        private ImageView mImageViewSelectorSide;

        OptionViewHolder(ViewGroup rootView) {
            mTextViewOption = (TextView) rootView.findViewById(R.id.duo_view_option_text);
            mImageViewSelector = (ImageView) rootView.findViewById(R.id.duo_view_option_selector);
            mImageViewSelectorSide = (ImageView) rootView.findViewById(R.id.duo_view_option_selector_side);

            hideSelectorsByDefault();
        }

        /**
         * By default both selectors are disabled.
         */
        private void hideSelectorsByDefault() {
            mImageViewSelector.setVisibility(INVISIBLE);
            mImageViewSelectorSide.setVisibility(GONE);
        }
    }
}
