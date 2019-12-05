package es.iessaladillo.pedrojoya.baldogym.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {

    fun queryAllSessions(): LiveData<List<TrainingSession>>

    fun getSessionsByDay(day: WeekDay)

}