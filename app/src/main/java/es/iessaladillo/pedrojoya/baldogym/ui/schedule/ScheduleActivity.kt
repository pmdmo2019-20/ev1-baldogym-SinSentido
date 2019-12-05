package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity(), ScheduleActivityAdapter.OnItemClick {
    private lateinit var selectedDay: WeekDay
    var day = ""

    private lateinit var lblSelectedDay: TextView
    private lateinit var btnMon: Button
    private lateinit var btnTue: Button
    private lateinit var btnWed: Button
    private lateinit var btnThu: Button
    private lateinit var btnFri: Button
    private lateinit var btnSat: Button
    private lateinit var btnSun: Button

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().apply {
        setOnItemClick(this@ScheduleActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeSessionList()
        if (savedInstanceState != null) {
            day = savedInstanceState.getString("SELECTED_DAY")
            getSavedDay(day)
            getSessionsByDay(selectedDay)
        }
        else{
            getCurrentDaySessions()
        }

    }

    private fun setupViews(){
        setupRecyclerView()

        lblSelectedDay = findViewById(R.id.lblSelectedDay)
        btnMon = findViewById(R.id.btnMon)
        btnTue = findViewById(R.id.btnTue)
        btnWed = findViewById(R.id.btnWed)
        btnThu = findViewById(R.id.btnThu)
        btnFri = findViewById(R.id.btnFri)
        btnSat = findViewById(R.id.btnSat)
        btnSun = findViewById(R.id.btnSun)

        //Listeners
        btnMon.setOnClickListener{getSessionsByDay(WeekDay.MONDAY)}
        btnTue.setOnClickListener{getSessionsByDay(WeekDay.TUESDAY)}
        btnWed.setOnClickListener{getSessionsByDay(WeekDay.WEDNESDAY)}
        btnThu.setOnClickListener{getSessionsByDay(WeekDay.THURSDAY)}
        btnFri.setOnClickListener{getSessionsByDay(WeekDay.FRIDAY)}
        btnSat.setOnClickListener{getSessionsByDay(WeekDay.SATURDAY)}
        btnSun.setOnClickListener{getSessionsByDay(WeekDay.SUNDAY)}
    }

    private fun setupRecyclerView(){
        lstSessions.run{
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

    }

    //OBSERVE METHODS
    private fun observeSessionList(){
        viewModel.sessionList.observe(this){
            updateList(it)
        }
    }

    private fun updateList(newList: List<TrainingSession>){
        listAdapter.submitList(newList)
    }

    //SELECT DAY METHODS
    private fun getCurrentDaySessions(){
        selectedDay = getCurrentWeekDay()
        getSessionsByDay(selectedDay)
    }

    private fun getSessionsByDay(day: WeekDay){
        selectedDay = day

        lblSelectedDay.text = day.toString()
        viewModel.getSessionsByDay(day)
    }

    //CLICK LISTENERS
    override fun onSessionClick(position: Int) {
        val sessionToShow = listAdapter.sessions[position]

        goToSessionActivity(sessionToShow.id)
    }

    override fun onButtonClick(){
        //TODO
    }

    //NAVIGATION METHODS
    private fun goToSessionActivity(id: Long){
        val intent = TrainingSessionActivity.goToActivity(this, id)
        startActivity(intent)
    }

    //SAVE INSTACE STATE
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("SELECTED_DAY", selectedDay.toString())

        super.onSaveInstanceState(outState)
    }

    fun getSavedDay(day: String){
        when(day){
            "MONDAY" -> selectedDay = WeekDay.MONDAY
            "TUESDAY" -> selectedDay = WeekDay.TUESDAY
            "WEDNESDAY" -> selectedDay = WeekDay.WEDNESDAY
            "THURSDAY" -> selectedDay = WeekDay.THURSDAY
            "FRIDAY" -> selectedDay = WeekDay.FRIDAY
            "SATURDAY" -> selectedDay = WeekDay.SATURDAY
            "SUNDAY" -> selectedDay = WeekDay.SUNDAY
        }
    }

}
