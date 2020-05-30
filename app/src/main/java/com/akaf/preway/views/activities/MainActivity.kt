package com.akaf.preway.views.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.akaf.preway.R
import com.akaf.preway.views.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_hq_footer_menu.*

class MainActivity : BaseActivity() {

    val fm by lazy {
        supportFragmentManager
    }
    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm.beginTransaction().replace(
            R.id.fragment_container,
            MainFragment.newInstance()
        )
            .commit()
        setMenuItemsListeners()
        bottomNavigationView.setSelectedItemId(R.id.page_lobby)
        bottomNavigationView.setItemIconTintList(null);


    }

    private fun setMenuItemsListeners() {
        Log.e("page_discover_people","setMenuItemsListeners")
        bottomNavigationView.setOnNavigationItemSelectedListener(object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId)
                {
                    R.id.page_discover_people ->{
                        if (!SettingFragment.newInstance().isVisible) {
                            fm.popBackStack()
                            fm.beginTransaction()
                                .replace(R.id.fragment_container,
                                    SettingFragment.newInstance()
                                )
                                .commit()
                        }

                    }
                    R.id.page_store ->{
                        if (!StoreFragment.newInstance().isVisible) {
                            fm.popBackStack()
                            fm.beginTransaction().replace(R.id.fragment_container,
                                StoreFragment.newInstance()
                            ).commit()
                        }
                    }

                    R.id.page_lobby ->{
                        if (!MainFragment.newInstance().isVisible) {
                            fm.popBackStack()
                            fm.beginTransaction().replace(R.id.fragment_container,
                                MainFragment.newInstance()
                            ).commit()
                        }
                    }
                    R.id.page_leaderboard ->{
                        if (!LeaderBoardFragment.newInstance().isVisible) {
                            fm.popBackStack()
                            fm.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    LeaderBoardFragment.newInstance()
                                ).commit()
                        }
                    }
                    R.id.page_profile ->{
                        Log.e("page_discover_people","page_discover_people")
                        if (!DiscoverPeopleFragment.newInstance().isVisible) {
                            fm.popBackStack()
                            fm.beginTransaction().replace(
                                R.id.fragment_container,
                                DiscoverPeopleFragment.newInstance()
                            ).commit()
                        }
                    }
                }

                return true
            }

        })
    }


//    private fun setListeners() {
//        imageButton5.setOnClickListener {
//
//            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
//            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
//            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
//            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby_selected)
//
//
//
//        }
//
//        page_discover_people.setOnClickListener {
//            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people_selected)
//            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
//            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
//            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
//            if (!DiscoverPeopleFragment.newInstance().isVisible) {
//                fm.popBackStack()
//                fm.beginTransaction().replace(
//                    R.id.fragment_container,
//                    DiscoverPeopleFragment.newInstance()
//                )
//                    .commit()
//            }
//        }
//
//        imageButton3.setOnClickListener {
//            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
//            imageButton3.setBackgroundResource(R.drawable.ic_nav_store_selected)
//            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
//            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
//            if (!StoreFragment.newInstance().isVisible) {
//                fm.popBackStack()
//                fm.beginTransaction().replace(R.id.fragment_container, StoreFragment.newInstance())
//                    .commit()
//            }
//        }
//
//        imageButton4.setOnClickListener {
//            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
//            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
//            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard_selected)
//            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
//            if (!LeaderBoardFragment.newInstance().isVisible) {
//                fm.popBackStack()
//                fm.beginTransaction()
//                    .replace(R.id.fragment_container, LeaderBoardFragment.newInstance())
//                    .commit()
//            }
//        }
//
//        imageButton6.setOnClickListener {
//            imageButton2.setBackgroundResource(R.drawable.ic_nav_discover_people)
//            imageButton3.setBackgroundResource(R.drawable.ic_nav_store)
//            imageButton4.setBackgroundResource(R.drawable.ic_nav_leaderboard)
//            imageButton5.setBackgroundResource(R.drawable.ic_nav_lobby)
//            if (!SettingFragment.newInstance().isVisible) {
//                fm.popBackStack()
//                fm.beginTransaction()
//                    .replace(R.id.fragment_container, SettingFragment.newInstance())
//                    .commit()
//            }
//        }
//    }

}
