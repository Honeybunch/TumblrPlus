package com.JIAT.Tumble;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: Arsen
 * Date: 8/5/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ScrollViewListener {

    void onScrollChanged(ObservableScrollView  scrollView, int x, int y, int oldX, int oldY);

}
