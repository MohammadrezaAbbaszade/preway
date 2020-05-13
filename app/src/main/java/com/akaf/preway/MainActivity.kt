package com.akaf.preway.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akaf.preway.framgments.DiscoverPeopleFragment
import com.akaf.preway.R
import com.akaf.preway.framgments.FourthFragment
import com.akaf.preway.framgments.MainFragment
import com.akaf.preway.framgments.StoreFragment
import com.akaf.preway.framgments.ThirdFragment
import kotlinx.android.synthetic.main.main_hq_footer_menu.*

class MainActivity : AppCompatActivity() {

    val fm by lazy {
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm.beginTransaction().replace(
            R.id.fragment_container,
            MainFragment.newInstance()
        )
            .commit()
        setListeners()
    }


    private fun setListeners() {
        imageButton5.setOnClickListener {

            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby_selected)

            if (!MainFragment.newInstance().isVisible) {
                fm.popBackStack()
                fm.beginTransaction().replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit()
            }

        }

        imageButton2.setOnClickListener {
            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people_selected)
            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
            if (!DiscoverPeopleFragment.newInstance().isVisible) {
                fm.popBackStack()
                fm.beginTransaction().replace(
                    R.id.fragment_container,
                    DiscoverPeopleFragment.newInstance()
                )
                    .commit()
            }
        }

        imageButton3.setOnClickListener {
            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
            imageButton3.setBackgroundResource(R.drawable.ic_nav_store_selected)
            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
            if (!StoreFragment.newInstance().isVisible) {
                fm.popBackStack()
                fm.beginTransaction().replace(R.id.fragment_container, StoreFragment.newInstance())
                    .commit()
            }
        }

        imageButton4.setOnClickListener {
            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard_selected)
            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
            if (!ThirdFragment.newInstance().isVisible) {
                fm.popBackStack()
                fm.beginTransaction().replace(R.id.fragment_container, ThirdFragment.newInstance())
                    .commit()
            }
        }

        imageButton6.setOnClickListener {
            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
            if (!FourthFragment.newInstance().isVisible) {
                fm.popBackStack()
                fm.beginTransaction().replace(R.id.fragment_container, FourthFragment.newInstance())
                    .commit()
            }
        }
    }

}
