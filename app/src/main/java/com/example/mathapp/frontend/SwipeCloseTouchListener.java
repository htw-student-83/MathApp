package com.example.mathapp.frontend;

/*
public class SwipeCloseTouchListener implements View.OnTouchListener {
    private GestureDetectorCompat gestureDetector;

    public SwipeCloseTouchListener(Context context) {
        gestureDetector = new GestureDetectorCompat(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true; // Verhindert, dass andere Touch-Events verarbeitet werden
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                // Wischgeste von links nach rechts oder von rechts nach links erkannt
                // Führen Sie hier den Code zum Schließen Ihrer App aus
                // Zum Beispiel: finish() oder System.exit(0);
                return true;
            }

            return false;
        }
    }
}
 */

