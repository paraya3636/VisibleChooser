package org.paradrops.visiblechooser

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils

open class VisibleChooser(private val context: Context) {

    private val viewArray = SparseArray<View>()

    fun putView(key: Int, value: View) {
        viewArray.append(key, value)
    }

    fun putViews(vararg pair: Pair<Int, View>) {
        pair.forEach {
            viewArray.append(it.first, it.second)
        }
    }

    fun showView(key: Int) {
        val animationSet = setupAnimation()

        goneExcept(key, animationSet.goneAnimation)
        visible(key, animationSet.visibleAnimation)

        animationSet.start()
    }

    private fun setupAnimation(): VisibleAnimationSet {
        val goneAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        val visibleAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)

        visibleAnimation.startOffset = goneAnimation.duration
        return VisibleAnimationSet(goneAnimation, visibleAnimation)
    }

    private fun visible(key: Int, animation: Animation?) {
        val target = viewArray.get(key)
        if (target != null && target.visibility != View.VISIBLE) {
            target.clearAnimation()
            target.visibility = View.VISIBLE
            if (animation != null) {
                target.animation = animation
            }
        }
    }

    private fun goneExcept(key: Int, animation: Animation?) {
        for (i in 0..viewArray.size() - 1) {
            val viewKey = viewArray.keyAt(i)

            if (key == viewKey) {
                continue
            }
            val view = viewArray.get(viewKey)
            if (view != null && view.visibility != View.GONE) {
                view.clearAnimation()
                if (animation != null) {
                    view.animation = animation
                }
                view.visibility = View.GONE
            }
        }
    }

    private class VisibleAnimationSet(val goneAnimation: Animation, val visibleAnimation: Animation) : AnimationSet(false) {

        init {
            addAnimation(goneAnimation)
            addAnimation(visibleAnimation)
        }
    }
}
