package com.example.tareaspinner

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tareaspinner.databinding.ActivityMainBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var numeroUno: Int? =  null
    private var numeroDos: Int? =  null
    private var numeroMayor: Int? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSaludo.text = "App Números"
        binding.bnCambio.text = "Verificar"

        /* El adaptador es para poner la info junto con una vista bas en el spinner.
        Intermediario que agarra info y vista. */

        val adaptador = ArrayAdapter.createFromResource(this, R.array.misOpciones,
            android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adaptador2 = ArrayAdapter.createFromResource(this, R.array.misOpciones,
            android.R.layout.simple_spinner_item)
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spOpciones.adapter = adaptador
        binding.spOpciones.onItemSelectedListener = this

        binding.spOpciones2.adapter = adaptador2
        binding.spOpciones2.onItemSelectedListener = this

        binding.bnCambio.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
            if(numeroUno!! > numeroDos!!){
                numeroMayor = numeroUno
            }else{
                numeroMayor = numeroDos
            }
            alerta.setTitle("Atención").setMessage("El número mayor es $numeroMayor")
                .setCancelable(false).setPositiveButton("Regresar",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        //Aqui el codigo
                        Toast.makeText(this,"Hasta luego!", Toast.LENGTH_SHORT).show()
                    }).show()
        }

    }

    override fun onItemSelected(vistaPadre: AdapterView<*>?, vistaRow: View?, position: Int, idVista: Long) {
        numeroUno = Integer.valueOf(vistaPadre?.getItemAtPosition(position).toString())
        numeroDos = Integer.valueOf(vistaPadre?.getItemAtPosition(position).toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}