package com.coder178.uit

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.coder178.uit.Activity.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.mainDrawer)
        val navView: NavigationView = findViewById(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.UIT, R.string.UIT)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menu_home -> updtateActivity(MainActivity(), it.title.toString())
                R.id.menu_faculty -> updtateActivity(FacultyActivity(), it.title.toString())
                R.id.menu_notice -> updtateActivity(NoticeActivity(), it.title.toString())
                R.id.menu_gallery -> updtateActivity(GalleryActivity(), it.title.toString())
                R.id.menu_ebook -> updtateActivity(EbookActivity(), "Ebook")
                R.id.menu_placement -> {intent = Intent(this, PlacementActivity::class.java)
                startActivity(intent)}


            }
            true
        }
    }

//        private fun updateFragment(fragment: Fragment, title: String) {
//
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.frameLayout, fragment)
//            fragmentTransaction.commit()
//            drawerLayout.closeDrawers()
//            setTitle(title)
//
//        }

    private fun updtateActivity(activity: AppCompatActivity, title: String) {

        intent = Intent(this, activity::class.java)
        startActivity(intent)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}