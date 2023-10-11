package com.example.usersfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import com.example.usersfragments.home.UsersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentByTag(UsersFragment.TAG) == null) {
            supportFragmentManager.beginTransaction().run {
                val fragment = UsersFragment.newInstance()
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, fragment, UsersFragment.TAG)
                addToBackStack(UsersFragment.TAG)
                commit()
            }

        }
    }
}