package gonzalo.dev.mychallenge.common.animation

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import gonzalo.dev.mychallenge.R

class AnimationUtil {

    companion object {

        /**
         * Execute a view animation from the center to the left side.
         * @param context The current context.
         * @param view The view.
         */
        fun viewSlideLeft(context: Context, view: View) {
            val animator =
                AnimationUtils.loadAnimation(context, R.anim.design_trans_slide_out_to_left)
            view.startAnimation(animator)
        }
    }
}