package org.paradrops.visiblechoosersample

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.paradrops.visiblechooser.VisibleChooser

class MainActivity : AppCompatActivity() {
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

        goneAllViewButton.setOnClickListener {
            viewChooser.goneAllView()
        }

        invisibleAllViewButton.setOnClickListener {
            viewChooser.invisibleAllView()
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
