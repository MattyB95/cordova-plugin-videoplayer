package com.moust.cordova.videoplayer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VideoPlayerUnitTest {

    @Test
    public void stripFileProtocol_stripsFilePrefix() {
        assertEquals(
                "/sdcard/Movies/video.mp4",
                VideoPlayer.stripFileProtocol("file:///sdcard/Movies/video.mp4")
        );
    }

    @Test
    public void stripFileProtocol_decodesUrlEncodedPath() {
        assertEquals(
                "/sdcard/Movies/my video.mp4",
                VideoPlayer.stripFileProtocol("file:///sdcard/Movies/my%20video.mp4")
        );
    }

    @Test
    public void stripFileProtocol_leavesHttpUrlUnchanged() {
        assertEquals(
                "http://example.com/video.mp4",
                VideoPlayer.stripFileProtocol("http://example.com/video.mp4")
        );
    }

    @Test
    public void stripFileProtocol_leavesHttpsUrlUnchanged() {
        assertEquals(
                "https://example.com/video.mp4",
                VideoPlayer.stripFileProtocol("https://example.com/video.mp4")
        );
    }

    @Test
    public void stripFileProtocol_leavesAbsolutePathUnchanged() {
        assertEquals(
                "/sdcard/Movies/video.mp4",
                VideoPlayer.stripFileProtocol("/sdcard/Movies/video.mp4")
        );
    }

    @Test
    public void stripFileProtocol_emptyStringReturnsEmpty() {
        assertEquals("", VideoPlayer.stripFileProtocol(""));
    }
}
