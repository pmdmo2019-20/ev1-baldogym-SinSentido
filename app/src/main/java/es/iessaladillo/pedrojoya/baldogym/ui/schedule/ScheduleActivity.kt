package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import android.os.PersistableBundle
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
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().apply {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeSessionList()
    }

    private fun setupViews(){
        setupRecyclerView()
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

}
