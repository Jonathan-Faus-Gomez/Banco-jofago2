import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.entities.CajeroEntity

class CajeroAdapter(private val cajeroList: List<CajeroEntity>) :
    RecyclerView.Adapter<CajeroAdapter.CajeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CajeroViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cajero, parent, false)
        return CajeroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CajeroViewHolder, position: Int) {
        val cajero = cajeroList[position]
        holder.bind(cajero)
    }

    override fun getItemCount(): Int {
        return cajeroList.size
    }

    inner class CajeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val direccionTextView: TextView = itemView.findViewById(R.id.direccionTextView)
        private val latitudTextView: TextView = itemView.findViewById(R.id.latitudTextView)
        private val longitudTextView: TextView = itemView.findViewById(R.id.longitudTextView)

        fun bind(cajero: CajeroEntity) {
            direccionTextView.text = cajero.direccion
            latitudTextView.text = cajero.latitud.toString()
            longitudTextView.text = cajero.longitud.toString()
        }
    }
}
