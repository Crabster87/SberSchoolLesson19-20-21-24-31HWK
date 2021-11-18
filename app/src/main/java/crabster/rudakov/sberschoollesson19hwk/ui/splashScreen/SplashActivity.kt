package crabster.rudakov.sberschoollesson19hwk.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import crabster.rudakov.sberschoollesson19hwk.R
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    /**
     * Метод создаёт View 'SplashActivity', отображающееся с определённой
     * продолжительностью
     *
     * @param savedInstanceState ассоциативный массив-хранилище данных
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        makeFullScreen()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }

    /**
     * Метод задаёт параметры экрана текущей активности
     *
     * @return MutableLiveData<Boolean> boolean значение статуса
     * */
    private fun makeFullScreen() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

}