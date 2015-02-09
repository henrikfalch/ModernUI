package android.course.modernui;

import junit.framework.TestCase;

public class FullscreenActivityTest extends TestCase {

    public void testInverse() throws Exception {
        assertEquals(255, FullscreenActivity.inverse(255, 0));
        assertEquals(255, FullscreenActivity.inverse(255, 40));
        assertEquals(0, FullscreenActivity.inverse(255, 100));
        assertEquals(205, FullscreenActivity.inverse(50, 100));
    }
}