package com.pgmail.martsulg.testapp;

import android.view.View;

/**
 * Created by g_washingt0n on 08.02.2018.
 */

public interface MyViewModel {

    public void init();

    public void release();

    public void resume();

    public void pause();

    public void getRequest(int currPage);

    public void delRequest(int cummentID);

    public void addRequest();

}
