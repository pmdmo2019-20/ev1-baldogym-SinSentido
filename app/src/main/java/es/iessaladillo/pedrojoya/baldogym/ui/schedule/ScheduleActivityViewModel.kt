package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

class ScheduleActivityViewModel(val repository: Repository, val application: Application): ViewModel() {

    val sessionList: LiveData<List<TrainingSession>> = repository.queryAllSessions()


    fun getSessionsByDay(day: WeekDay){
        repository.getSessionsByDay(day)
    }
}