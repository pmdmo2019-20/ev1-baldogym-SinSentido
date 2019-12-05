package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity

class ScheduleActivityAdapter: RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var onItemClick: ScheduleActivityAdapter.OnItemClick? = null
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



    fun setOnItemClick(listener: ScheduleActivityAdapter.OnItemClick){
        onItemClick = listener
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val lblClassTime: TextView = itemView.findViewById(R.id.lblClassTime)
        private val lblClassName: TextView = itemView.findViewById(R.id.lblClassName)
        private val lblTrainerName: TextView = itemView.findViewById(R.id.lblTrainerName)
        private val lblRoomName: TextView = itemView.findViewById(R.id.lblRoomName)
        private val lblParticipants: TextView = itemView.findViewById(R.id.lblParcipants)
        private val imgSession: ImageView = itemView.findViewById(R.id.imgSession)

        init{
            itemView.setOnClickListener(){ onItemClick?.onSessionClick(adapterPosition) }
        }

        fun bind(session: TrainingSession){
            session.run {
                lblClassTime.text = time
                lblClassName.text = name
                lblTrainerName.text = trainer
                lblRoomName.text = room
                lblParticipants.text = participants.toString()
                imgSession.setImageResource(photoResId)
            }

        }
    }

    interface OnItemClick{
        fun onSessionClick(position: Int)
    }
}