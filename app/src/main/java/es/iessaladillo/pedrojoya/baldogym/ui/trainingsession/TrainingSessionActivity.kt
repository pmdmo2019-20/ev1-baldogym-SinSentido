package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityViewModel
import es.iessaladillo.pedrojoya.baldogym.ui.schedule.ScheduleActivityViewModelFactory
import kotlinx.android.synthetic.main.training_session_activity.*

class TrainingSessionActivity : AppCompatActivity() {

    private var joined: Boolean  = false
    private var id: Long = 0
    private lateinit var session: TrainingSession

    private lateinit var btnJoin: Button
    private lateinit var lblSessionClassName: TextView
    private lateinit var lblSessionTrainerName: TextView
    private lateinit var lblSessionDay: TextView
    private lateinit var lblSessionTime: TextView
    private lateinit var lblSessionRoom: TextView
    private lateinit var lblSessionDescription: TextView
    private lateinit var lblSessionParticipants: TextView
    private lateinit var imgSessionActivity: ImageView

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        setupViews()
        getIntentData()
    }

    private fun setupViews(){
        btnJoin = findViewById(R.id.btnSessionJoinButton)
        lblSessionClassName = findViewById(R.id.lblSessionClassName)
        lblSessionTrainerName = findViewById(R.id.lblSessionTrainerName)
        lblSessionDay = findViewById(R.id.lblSessionDay)
        lblSessionTime = findViewById(R.id.lblSessionTime)
        lblSessionRoom = findViewById(R.id.lblSessionRoom)
        lblSessionDescription = findViewById(R.id.lblSessionDescription)
        lblSessionParticipants = findViewById(R.id.lblSessionParticipants)
        imgSessionActivity = findViewById(R.id.imgSessionActivity)

        btnJoin.setOnClickListener{joinButton()}

    }

    private fun getIntentData() {
        if (intent != null || intent.hasExtra(POSITION) || intent.hasExtra(POSITION)) {
            id = intent.getLongExtra(POSITION, 0)
            session = viewModel.getSessionById(id)
            printSessionInfo(session)
        }
    }

    private fun printSessionInfo(session: TrainingSession){
        session.run{
            lblSessionClassName.setText(name)
            lblSessionTrainerName.setText(trainer)
            lblSessionDay.setText(weekDay.toString())
            lblSessionTime.setText(time)
            lblSessionRoom.setText(room)
            lblSessionDescription.setText(description)
            lblSessionParticipants.text = getString(R.string.participants, session.participants)
            imgSessionActivity.setImageResource(photoResId)
        }
    }

    private fun joinButton(){
        if(joined){
            btnJoin.setTextColor(resources.getColor(R.color.white_semi))
            btnJoin.setBackgroundResource(R.color.black)
            btnJoin.setText(R.string.schedule_item_join)
            viewModel.removeParticipant(id)
            joined = false;
        }
        else{
            btnJoin.setTextColor(resources.getColor(R.color.black))
            btnJoin.setBackgroundResource(R.color.white)
            btnJoin.setText(R.string.schedule_item_quit)
            viewModel.addParticipant(id)
            joined = true;
        }

        session = viewModel.getSessionById(id)
        lblSessionParticipants.text = getString(R.string.participants, session.participants)
    }


    companion object{
        const val POSITION = "POSITION"

        fun goToActivity(context: Context, id: Long) = Intent(context, TrainingSessionActivity::class.java)
            .putExtra(POSITION, id)
    }


}
