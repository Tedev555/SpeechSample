package devzy.com.speechsample


import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.util.*
import android.widget.Toast




/**
 * A simple [Fragment] subclass.
 */
class TextToSpeechFragment : Fragment() {
    var t1: TextToSpeech? = null
    var textEditText: EditText? = null

    companion object {
        fun newInstance(): TextToSpeechFragment {
            val fragment = TextToSpeechFragment()

            return fragment
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val myView = inflater!!.inflate(R.layout.fragment_text_to_speech, container, false)

        textEditText = myView.findViewById(R.id.text_edit_text)

        var btnSpeak: Button = myView.findViewById(R.id.btn_speak)

        t1 = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                t1!!.setLanguage(Locale.US)
            }
        })

        btnSpeak.setOnClickListener { playTextToSpeech() }

        return myView
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun playTextToSpeech() {
        val text: String? = textEditText?.text.toString()
        if (text == "") {
            Toast.makeText(context, "Please enter your text", Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        t1!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}// Required empty public constructor
