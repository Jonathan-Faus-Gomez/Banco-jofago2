import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.OnClickListenerCajero
import com.example.banco_jofago.databinding.ItemCajeroBinding
import com.example.banco_jofago.entities.CajeroEntity

class CajeroAdapter(private var cajeros: MutableList<CajeroEntity>, private var listenerCajero: OnClickListenerCajero) :
    RecyclerView.Adapter<CajeroAdapter.CajeroViewHolder>() {

        private lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CajeroViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_cajero, parent, false)
        return CajeroViewHolder(view) //se puede poner unicamente ViewHolder en todo
    }

    override fun onBindViewHolder(holder: CajeroViewHolder, position: Int) {
        val cajero = cajeros.get(position)
        with(holder){
            setListener(cajero)
            binding.direccionTextView.text = cajero.direccion
            binding.latitudTextView.text= cajero.latitud.toString()
            binding.longitudTextView.text=cajero.latitud.toString()
        }
    }

    override fun getItemCount(): Int {
        return cajeros.size
    }
    inner class CajeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCajeroBinding.bind(itemView)
        fun setListener(cajero:CajeroEntity){
            binding.root.setOnClickListener{
                listenerCajero.onClick(cajero)
            }
        }
    }
    fun addCajero(cajeroEntity: CajeroEntity){
        cajeros.add(cajeroEntity)

    }

    fun setStores(cajero: MutableList<CajeroEntity>){
        this.cajeros = cajero

    }



}
