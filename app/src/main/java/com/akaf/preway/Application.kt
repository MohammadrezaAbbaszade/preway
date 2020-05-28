package com.akaf.preway

import androidx.multidex.MultiDexApplication
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class Application : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()


        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/iranyekanwebregular.ttf")
                            .build()
                    )
                )
                .build()
        )




    }
}