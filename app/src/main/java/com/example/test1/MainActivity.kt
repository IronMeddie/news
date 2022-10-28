package com.example.test1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.test1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!
    private val view by viewModels<VmM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val bottomMenu = mBinding.bottomnavigationmenu
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomMenu.setupWithNavController(navController = navController)
        view.state.observe(this){
            bottomMenu.visibility = it
        }
        navController.navigate(R.id.splashFragment2)
    }



    fun getBottomMenu(){
        view.getVisible()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}