package com.JIAT.Tumble;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.JIAT.Tumble.ScrollViewListener;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 1:51 PM
 *
 * We have to reimplement the scroll view to get access
 * to the onScrollEvent. That way we can tell how far
 * the user has scrolled down, and whether or not we
 * should change how the refresh button works and acts
 *
 *
 */
public class ObservableScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    /*
    * Create a bunch of Constructors
    * */
    public ObservableScrollView(Context context)
    {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener)
    {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY)
    {
        super.onScrollChanged(x, y, oldX, oldY);

        //If there is a listener hooked up, let it know
        //What has changed
        if(scrollViewListener != null)
        {
            scrollViewListener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

}
