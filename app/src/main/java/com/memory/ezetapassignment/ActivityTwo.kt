package com.memory.ezetapassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.memory.ezetapassignment.commonFunctions.CommonFuncs
import com.memory.networkkit.models.UIinfo
import retrofit2.Response

class ActivityTwo : AppCompatActivity() {
    lateinit var mainLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
        val result = intent.getSerializableExtra("jsonResult") as UIinfo
        val elements = result.uidata
        if(elements.isNotEmpty()){
            val cf = CommonFuncs();
            for(ule in result.uidata){
                cf.checkUiType(false,this,ule, mainLayout,result)
            }
        }else{
            Toast.makeText(this,"No elements found", Toast.LENGTH_SHORT).show()
        }
    }
}