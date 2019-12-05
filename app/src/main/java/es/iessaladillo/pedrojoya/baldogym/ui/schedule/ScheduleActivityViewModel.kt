package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

class ScheduleActivityViewModel(val repository: Repository, val application: Application): ViewModel() {

    var sessionList: LiveData<List<TrainingSession>> = repository.queryAllSessions()


    fun getSessionsByDay(day: WeekDay){
        sessionList = repository.getSessionsByDay(day)
    }

    fun getSessionById(id: Long): TrainingSession{
        return repository.getSessionById(id)
    }

    fun addParticipant(id: Long){
        repository.addParticipant(id)
    }

    fun removeParticipant(id: Long){
        repository.removeParticipant(id)
    }
}