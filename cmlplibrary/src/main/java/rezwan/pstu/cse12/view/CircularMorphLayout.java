package rezwan.pstu.cse12.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

public class CircularMorphLayout extends FrameLayout implements Animatable {
    public static final Property<CircularMorphLayout, Float> CLIP_CIRCLE_RADIUS_PROGRESS = new Property<CircularMorphLayout, Float>(Float.class, "clipCircleRadius") {
        public Float get(CircularMorphLayout view) {
            return view.getClipCircleRadius();
        }

        public void set(CircularMorphLayout object, Float value) {
            object.setClipCircleRadius(value);
        }
    };
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private Path clipPath = new Path();
    private float centerX = 0.0F;
    private float centerY = 0.0F;
    private float startRadius = 0.0F;
    private float endRadius = 0.0F;
    private float clipCircleRadius = 0.0F;
    private long duration = 400L;
    private boolean isRunning = false;
    private ObjectAnimator clipCircleRadiusAnimator;
    private CircularMorphLayout.CallBacks listener;
    private boolean isDirty = true;

    public CircularMorphLayout(Context context) {
        super(context);
    }

    public CircularMorphLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularMorphLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircularMorphLayout revealFrom(float centerX, float centerY, float startRadius, float endRadius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.startRadius = startRadius + 10.0F;
        this.endRadius = endRadius;
        this.clipCircleRadiusAnimator = ObjectAnimator.ofFloat(this, CLIP_CIRCLE_RADIUS_PROGRESS, new float[]{startRadius, this.endRadius});
        this.isDirty = false;
        return this;
    }

    public CircularMorphLayout setListener(CircularMorphLayout.CallBacks listener) {
        this.listener = listener;
        return this;
    }

    public CircularMorphLayout setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.isDirty) {
            super.dispatchDraw(canvas);
        } else {
            canvas.save();
            this.clipPath.reset();
            this.clipPath.moveTo(this.centerX, this.centerY);
            this.clipPath.addCircle(this.centerX, this.centerY, this.clipCircleRadius, Direction.CW);
            this.clipPath.close();
            canvas.clipPath(this.clipPath);
            super.dispatchDraw(canvas);
            canvas.restore();
        }
    }

    public synchronized float getClipCircleRadius() {
        return this.clipCircleRadius;
    }

    public synchronized void setClipCircleRadius(float clipCircleRadius) {
        this.clipCircleRadius = clipCircleRadius;
        this.invalidate();
    }

    public void reverse() {
        if (this.isRunning()) {
            this.stop();
        }

        this.clipCircleRadiusAnimator = null;
        this.clipCircleRadiusAnimator = ObjectAnimator.ofFloat(this, CLIP_CIRCLE_RADIUS_PROGRESS, new float[]{this.endRadius, this.startRadius});
        this.clipCircleRadiusAnimator.setInterpolator(DECELERATE_INTERPOLATOR);
        this.clipCircleRadiusAnimator.setDuration(this.duration);
        this.clipCircleRadiusAnimator.start();
    }

    public void start() {
        if (!this.isRunning()) {
            this.isRunning = true;
            this.clipCircleRadiusAnimator.setInterpolator(ACCELERATE_INTERPOLATOR);
            this.clipCircleRadiusAnimator.setDuration(this.duration);
            this.clipCircleRadiusAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    if (CircularMorphLayout.this.listener != null) {
                        CircularMorphLayout.this.listener.onEnd();
                    }

                }
            });
            this.clipCircleRadiusAnimator.start();
        }
    }

    public void stop() {
        if (this.isRunning()) {
            this.isRunning = false;
            this.clipCircleRadiusAnimator.cancel();
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public interface CallBacks {
        void onEnd();
    }
}

