import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_jofago.R
import com.example.banco_jofago.pojo.Cuenta

class CuentasAdapter(private var cuentas: ArrayList<Cuenta>) : RecyclerView.Adapter<CuentasAdapter.CuentaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cuenta, parent, false)
        return CuentaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        val cuenta = cuentas[position]
        holder.bind(cuenta)
    }

    override fun getItemCount(): Int {
        return cuentas.size
    }

    inner class CuentaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cuenta: Cuenta) {
            itemView.findViewById<TextView>(R.id.txtNumeroCuenta).text = cuenta.getNumeroCuenta()
            itemView.findViewById<TextView>(R.id.txtSaldo).text = cuenta.getSaldoActual().toString()
        }
    }
}
