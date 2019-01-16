package sample.things.sfeir.org.androidthingssample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat

private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {
    var buttonA: Button? = null
    var count: Int = 0
    var segmentDisplay: AlphanumericDisplay? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        segmentDisplay = RainbowHat.openDisplay()
        segmentDisplay!!.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
        segmentDisplay!!.clear()
        segmentDisplay!!.display(0)
        segmentDisplay!!.setEnabled(true)

        buttonA = RainbowHat.openButtonA()
        buttonA!!.setOnButtonEventListener { button, pressed ->
            if (pressed) {
                count++
                segmentDisplay!!.display(count)
                Log.d(TAG, count.toString())
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        buttonA!!.close()
        segmentDisplay!!.clear()
        segmentDisplay!!.setEnabled(false)
        segmentDisplay!!.close()
    }

}
