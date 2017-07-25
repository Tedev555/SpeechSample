package devzy.com.speechsample


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 */
class SpeechToTextFragment : Fragment() {
    var resultTextView: TextView? = null

    companion object {
        fun newInstance(): SpeechToTextFragment {
            val fragment = SpeechToTextFragment()

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myView = inflater!!.inflate(R.layout.fragment_speech_to_text, container, false)

        resultTextView = myView.findViewById(R.id.result_text_view)
        val btnSpeak: Button = myView.findViewById(R.id.btn_speak)

        btnSpeak.setOnClickListener { getSpeechInput() }

        return myView
    }


    fun getSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "EN")

        if (intent.resolveActivity(activity.packageManager) != null) {
            startActivityForResult(intent, 10)
        } else {
            Toast.makeText(activity, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10 -> if (resultCode == Activity.RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val textResult: String = result[0]
                resultTextView?.text = textResult
            }
        }
    }

}
