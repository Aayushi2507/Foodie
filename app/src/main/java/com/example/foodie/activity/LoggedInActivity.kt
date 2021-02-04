package com.example.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.foodie.R
import com.example.foodie.fragment.*
import com.example.foodie.fragment.FAQFragment
import com.example.foodie.fragment.OrderHistoryFragment
import com.example.foodie.fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView

class LoggedInActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        setUpToolbar()
        openHomePage()

        val intent = Intent(this@LoggedInActivity, LoginActivity::class.java)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@LoggedInActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> {
                    openHomePage()
                    drawerLayout.closeDrawers()
                }
                R.id.myProfile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            ProfileFragment()
                        )
                        .commit()

                    supportActionBar?.title = "MY PROFILE"
                    drawerLayout.closeDrawers()
                }
                R.id.favRes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            FavResFragment()
                        )
                        .commit()

                    supportActionBar?.title = "FAVOURITE RESTAURANTS"
                    drawerLayout.closeDrawers()
                }
                R.id.orderHis -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            OrderHistoryFragment()
                        )
                        .commit()

                    supportActionBar?.title = "ORDER HISTORY"
                    drawerLayout.closeDrawers()
                }
                R.id.faq -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            FAQFragment()
                        )
                        .commit()

                    supportActionBar?.title = "FAQ's"
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    startActivity(intent)
                    finish()
                    drawerLayout.closeDrawers()
                }
                else->{
                    openHomePage()
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="FOODIE"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openHomePage(){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout,
                HomeFragment()
            )
            .commit()

        supportActionBar?.title = "FOODIE"
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is HomeFragment -> openHomePage()
            else-> super.onBackPressed()
        }
    }
}
