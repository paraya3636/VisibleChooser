package org.paradrops.visiblechoosersample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import org.paradrops.visiblechooser.VisibleChooser

class MainActivity : AppCompatActivity() {

    private val firstView by lazy { findViewById(R.id.firstView) as TextView }
    private val secondView by lazy { findViewById(R.id.secondView) as TextView }
    private val thirdView by lazy { findViewById(R.id.thirdView) as TextView }

    private val firstViewButton by lazy { findViewById(R.id.firstViewButton) as Button }
    private val secondViewButton by lazy { findViewById(R.id.secondViewButton) as Button }
    private val thirdViewButton by lazy { findViewById(R.id.thirdViewButton) as Button}

    private val viewChooser = Chooser(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewChooser.putViews(*arrayOf(
                Pair(Chooser.Views.FirstView.value, firstView),
                Pair(Chooser.Views.SecondView.value, secondView),
                Pair(Chooser.Views.ThirdView.value, thirdView)
        ))

        firstViewButton.setOnClickListener {
            viewChooser.showView(Chooser.Views.FirstView.value)
        }

        secondViewButton.setOnClickListener {
            viewChooser.showView(Chooser.Views.SecondView.value)
        }

        thirdViewButton.setOnClickListener {
            viewChooser.showView(Chooser.Views.ThirdView.value)
        }
    }

    private class Chooser(context: Context) : VisibleChooser(context) {
        enum class Views(val value: Int) {
            FirstView(0),
            SecondView(1),
            ThirdView(2)
        }
    }
}
