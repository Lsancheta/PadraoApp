package com.example.padraoapp

import android.app.DatePickerDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.Camera
import android.content.Intent
import android.provider.MediaStore
import android.widget.ImageView

class FormChecklist : AppCompatActivity() {

    private lateinit var tvDate: TextView
    private lateinit var btnDatePicker: Button
    private lateinit var btnPhotoPicker: Button
    private lateinit var imgViewer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_checklist)

        btnPhotoPicker.isEnabled = false

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 110)
        }
        else
            btnPhotoPicker.isEnabled = true
        btnPhotoPicker.setOnClickListener{
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           registerForActivityResult(i,android.Manifest.permission.CAMERA,)
        }
        tvDate = findViewById(R.id.tvDate)
        btnDatePicker = findViewById(R.id.btnDatePicker)

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        btnDatePicker.setOnClickListener(){
            DatePickerDialog(this, datePicker,myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        tvDate.setText(sdf.format(myCalendar.time))
    }
}
