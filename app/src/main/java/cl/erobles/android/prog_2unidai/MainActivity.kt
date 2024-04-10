package cl.erobles.android.prog_2unidai

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var etPastelDeChoclo: EditText
    private lateinit var tvTotalPastelDeChoclo: TextView
    private lateinit var etCantidadCazuela: EditText
    private lateinit var tvTotalCazuela: TextView
    private lateinit var tvValorComida: TextView
    private lateinit var switchPropina: Switch
    private lateinit var tvValorPropina: TextView
    private lateinit var tvValorTotalComida: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar las vistas
        etPastelDeChoclo = findViewById(R.id.etPastelDeChoclo)
        tvTotalPastelDeChoclo = findViewById(R.id.tvTotalPastelDeChoclo)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        tvTotalCazuela = findViewById(R.id.tvTotalCazuela)
        tvValorComida = findViewById(R.id.tvValorComida)
        switchPropina = findViewById(R.id.switchPropina)
        tvValorPropina = findViewById(R.id.tvValorPropina)
        tvValorTotalComida = findViewById(R.id.tvValorTotalComida)

        // Establecer el EditText de pastel de choclo
        etPastelDeChoclo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                calcularTotalComida()
            }
        })

        // Establecer el EditText de cazuela
        etCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                calcularTotalComida()
            }
        })
        // Switch de propina
        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            calcularTotalComida()
        }
    }

    private fun calcularTotalComida() {
        val cantidadPastel = etPastelDeChoclo.text.toString().toIntOrNull() ?: 0
        val totalPastel = cantidadPastel * 12000
        val formatter = DecimalFormat("#,###")
        tvTotalPastelDeChoclo.text = "Total: \$${formatter.format(totalPastel)}"

        val cantidadCazuela = etCantidadCazuela.text.toString().toIntOrNull() ?: 0
        val totalCazuela = cantidadCazuela * 10000
        tvTotalCazuela.text = "Total: \$${formatter.format(totalCazuela)}"

        val totalComida = totalPastel + totalCazuela
        tvValorComida.text = "\$${formatter.format(totalComida)}"

        val propina = if (switchPropina.isChecked) (totalComida * 0.1).toInt() else 0
        tvValorPropina.text = "\$${formatter.format(propina)}"

        val totalConPropina = totalComida + propina
        tvValorTotalComida.text = "\$${formatter.format(totalConPropina)}"

    }
}