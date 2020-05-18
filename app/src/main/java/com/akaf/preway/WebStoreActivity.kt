package com.akaf.preway

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web_store.*

class WebStoreActivity : AppCompatActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WebStoreActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_store)



        hqWebView.loadUrl("http://www.google.com")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
