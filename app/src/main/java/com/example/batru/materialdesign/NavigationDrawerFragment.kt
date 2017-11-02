package com.example.batru.materialdesign


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NavigationDrawerFragment : Fragment() {
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerLayout: DrawerLayout
    private var mUserLearnedDrawer: Boolean = false
    private var mFromSaveInstanceSave: Boolean = false
    private lateinit var containerView: View

    companion object {
        val PREF_FILE_NAME = "testpref"
        val KEY_USER_LEARNED_DRAWER = "user_learned_drawer"

        fun saveToPreferences(context: Context, preferencesName: String, preferencesValue: String) {
            val sharePreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharePreferences.edit()
            editor.putString(preferencesName, preferencesValue)
            editor.apply()
        }

        fun readFromPreferences(context: Context, preferencesName: String, defaultValue: String): String {
            val sharePreferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
            return sharePreferences.getString(preferencesName, defaultValue)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserLearnedDrawer = readFromPreferences(activity, KEY_USER_LEARNED_DRAWER, "true").toBoolean()
        if (savedInstanceState != null) {
            mFromSaveInstanceSave = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_navigation_drawer, container, false)
    }

    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true
                    saveToPreferences(activity, PREF_FILE_NAME, mUserLearnedDrawer.toString())
                }
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
                activity.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                if (slideOffset < 0.6) {
                    toolbar.alpha = 1- slideOffset
                }
            }
        }
        if (!mUserLearnedDrawer && !mFromSaveInstanceSave) {
            mDrawerLayout.openDrawer(containerView)
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle)
        mDrawerLayout.post(object : Runnable {
            override fun run() {
                mDrawerToggle.syncState()
            }
        })
    }
}
