package com.memory.ezetapassignment.commonFunctions

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.memory.ezetapassignment.ActivityTwo
import com.memory.networkkit.models.UIElements
import com.memory.networkkit.models.UIinfo
import java.io.Serializable

class CommonFuncs {
     fun checkUiType(fromFirst:Boolean,ctx: Context ,ule: UIElements, mainLayout:LinearLayout, result:UIinfo) {
        when(ule.uitype){
            "label" ->{
                val textView = TextView(ctx)
                textView.layoutParams= LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                textView.text = ule.value
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                textView.setTextColor(Color.BLACK)
                mainLayout.addView(textView)
            }
            "edittext" ->{
                val editText = EditText(ctx)
                editText.layoutParams= LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                editText.hint = ule.hint
                editText.setPadding(20, 20, 20, 20)
                mainLayout.addView(editText)
            }
            "button" ->{
                val button = Button(ctx)
                button.layoutParams= LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                button.text = ule.value
                button.setOnClickListener {
                    if(fromFirst){
                        val intent = Intent(ctx, ActivityTwo::class.java)
                        intent.putExtra("jsonResult",result as Serializable)
                        ctx.startActivity(intent)
                    }
                }
                mainLayout.addView(button)
            }
        }
    }
}