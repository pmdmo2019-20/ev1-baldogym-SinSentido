package es.iessaladillo.pedrojoya.baldogym.data

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    fun queryAllSessions(): LiveData<List<TrainingSession>>

}