import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.adapters.OnClickListener
import com.example.banco_jofago.databinding.ItemCuentaBinding
import com.example.banco_jofago.pojo.Cuenta

class CuentasAdapter(private val cuentas: ArrayList<Cuenta>?,private val listener: OnClickListener): RecyclerView.Adapter<CuentasAdapter.ViewHolder>(){

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding = ItemCuentaBinding.bind(view)

        fun setListener(c:Cuenta){
            binding.root.setOnClickListener {
                listener.onClick(c)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuentas?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta = cuentas!!.get(position)
        with(holder){
            setListener(cuenta)
            binding.txtNumeroCuenta.text = cuenta.getNumeroCuenta()
            binding.txtSaldo.text = cuenta.getSaldoActual().toString()
        }
    }
}