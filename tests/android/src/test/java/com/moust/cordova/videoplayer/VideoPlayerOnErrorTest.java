package com.moust.cordova.videoplayer;

import android.media.MediaPlayer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Regression tests for onError() behaviour.
 *
 * Previously onError() returned false, which caused Android to invoke onCompletion()
 * immediately after, leading to a double MediaPlayer.release() and double dialog.dismiss().
 * It also failed to notify the JavaScript error callback.
 */
@RunWith(MockitoJUnitRunner.class)
public class VideoPlayerOnErrorTest {

    /**
     * onError() must return true to consume the error and prevent Android from
     * invoking onCompletion() afterwards (which would cause a double-release).
     */
    @Test
    public void onError_returnsTrue() {
        VideoPlayer plugin = new VideoPlayer();
        MediaPlayer mockPlayer = mock(MediaPlayer.class);

        boolean result = plugin.onError(mockPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);

        assertTrue("onError must return true to suppress onCompletion", result);
    }

    /**
     * onError() must call release() on the MediaPlayer to free native resources.
     */
    @Test
    public void onError_releasesMediaPlayer() {
        VideoPlayer plugin = new VideoPlayer();
        MediaPlayer mockPlayer = mock(MediaPlayer.class);

        plugin.onError(mockPlayer, MediaPlayer.MEDIA_ERROR_UNKNOWN, 0);

        verify(mockPlayer).release();
    }

    /**
     * onError() must not throw when dialog and callbackContext are null
     * (i.e. when called before or after normal plugin lifecycle).
     */
    @Test
    public void onError_nullDialogAndCallback_doesNotThrow() {
        VideoPlayer plugin = new VideoPlayer();
        MediaPlayer mockPlayer = mock(MediaPlayer.class);

        plugin.onError(mockPlayer, MediaPlayer.MEDIA_ERROR_SERVER_DIED, -1);
    }
}
