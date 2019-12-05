package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.baldogym.R

class TrainingSessionActivity : AppCompatActivity() {

    private lateinit var btnJoin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        setupViews()
    }

    private fun setupViews(){
        btnJoin = findViewById(R.id.btnSessionJoinButton)

    }


    companion object{

        fun goToActivity(context: Context) = Intent(context, TrainingSessionActivity::class.java)
    }


}
