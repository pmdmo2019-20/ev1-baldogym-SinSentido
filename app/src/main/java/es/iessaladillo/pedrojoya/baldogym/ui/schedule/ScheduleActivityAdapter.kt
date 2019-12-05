package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

class ScheduleActivityAdapter: RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var sessions: List<TrainingSession> = emptyList()

    init {
        setHasStableIds(true)
    }

    fun submitList(newList: List<TrainingSession>){
        sessions = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)

        return ViewHolder(itemView)    }

    override fun getItemCount(): Int = sessions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sessions[position])
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val lblClassTime: TextView = itemView.findViewById(R.id.lblClassTime)
        private val lblClassName: TextView = itemView.findViewById(R.id.lblClassName)
        private val lblTrainerName: TextView = itemView.findViewById(R.id.lblTrainerName)
        private val lblRoomName: TextView = itemView.findViewById(R.id.lblRoomName)
        private val lblParticipants: TextView = itemView.findViewById(R.id.lblParcipants)

        fun bind(session: TrainingSession){
            session.run {
                lblClassTime.text = time
                lblClassName.text = name
                lblTrainerName.text = trainer
                lblRoomName.text = room
                lblParticipants.text = participants.toString()
            }

        }

    }
}