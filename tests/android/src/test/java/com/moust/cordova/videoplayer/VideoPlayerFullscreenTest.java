package com.moust.cordova.videoplayer;

import android.view.Window;
import android.view.WindowManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VideoPlayerFullscreenTest {

    /**
     * On API < 30, applyFullscreen must use FLAG_FULLSCREEN since getInsetsController()
     * doesn't exist.
     */
    @Test
    public void applyFullscreen_belowApi30_setsFullscreenFlag() {
        Window mockWindow = mock(Window.class);

        VideoPlayer.applyFullscreen(mockWindow, 28);

        verify(mockWindow).setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    /**
     * On API 30+, applyFullscreen must NOT fall back to FLAG_FULLSCREEN. It should
     * use the WindowInsetsController path instead.
     */
    @Test
    public void applyFullscreen_api30_doesNotSetFullscreenFlag() {
        Window mockWindow = mock(Window.class);
        // getInsetsController() returns null from the mock — code takes the null check
        // branch and skips FLAG_FULLSCREEN, which is the correct API 30+ behaviour.

        VideoPlayer.applyFullscreen(mockWindow, 30);

        verify(mockWindow, never()).setFlags(anyInt(), anyInt());
    }

    @Test
    public void applyFullscreen_api28_doesNotThrow() {
        VideoPlayer.applyFullscreen(mock(Window.class), 28);
    }

    @Test
    public void applyFullscreen_api30_doesNotThrow() {
        VideoPlayer.applyFullscreen(mock(Window.class), 30);
    }
}
