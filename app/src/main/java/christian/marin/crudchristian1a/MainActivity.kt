package christian.marin.crudchristian1a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.claseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        val rcvDatos = findViewById<RecyclerView>(R.id.rcvDatos)

        //1 ponerle un layout a mi recyclerview
        rcvDatos.layoutManager = LinearLayoutManager(this)


        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO){

                //guardar datos
                //1 crear un objeto de la clase conexion

                val objConexion = claseConexion().cadenaConexion()

                //2 crear variable que sea igual a un PrepareStatement

                val addProducto = objConexion?.prepareStatement("insert into tbProductoss values(?, ?, ?)")!!
                addProducto.setString(1, txtNombre.text.toString())
                addProducto.setInt(2, txtPrecio.text.toString().toInt())
                addProducto.setInt(3, txtCantidad.text.toString().toInt())
                addProducto.executeUpdate()
            }
        }

        //mostrar datos



    }
}