package com.memory.ezetapassignment

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.memory.ezetapassignment.commonFunctions.CommonFuncs
import com.memory.networkkit.RetrofitClient
import com.memory.networkkit.interfaces.Network
import com.memory.networkkit.models.UIinfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ActivityOne : AppCompatActivity() {
    lateinit var mainLayout:LinearLayout
    lateinit var loader:LottieAnimationView
    lateinit var result:Response<UIinfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        loader = findViewById<LottieAnimationView>(R.id.loader)
        loader.visibility = View.VISIBLE
        mainLayout = findViewById<LinearLayout>(R.id.mainLayout)
        val uiElementsApi = RetrofitClient.getInstance().create(Network::class.java)

        // launching a new coroutine
        lifecycleScope.launch(Dispatchers.IO){
            result = uiElementsApi.fetchCustomUI()
            val elements = result.body()?.uidata

            withContext(Dispatchers.Main){
                if(elements!=null && elements.isNotEmpty()){
                    createHeader(result.body()!!)
                    val cf = CommonFuncs();
                    for(ule in elements){
                        cf.checkUiType(true,this@ActivityOne,ule,mainLayout, result.body()!!)
                    }
                }else{
                    Toast.makeText(this@ActivityOne,"No elements found",Toast.LENGTH_SHORT).show()
                }
                loader.visibility = View.GONE
            }
        }
    }
    
    private fun createHeader(ulinfo:UIinfo){
        try {
            val image = ImageView(this@ActivityOne)
            image.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            Glide.with(this@ActivityOne)
                .load(ulinfo.logoUrl).into(image)
            mainLayout.addView(image)
            val textView = TextView(this@ActivityOne)
            textView.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.text = ulinfo.headingtext
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
            textView.setTextColor(Color.RED)
            mainLayout.addView(textView)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}