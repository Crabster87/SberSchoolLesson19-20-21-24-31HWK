package crabster.rudakov.sberschoollesson19hwk

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import crabster.rudakov.sberschoollesson19hwk.ui.main.view.MainActivity

class SplashActivity : AppCompatActivity() {

    /**
     * Метод создаёт View 'SplashActivity', отображающееся с определённой
     * продолжительностью
     *
     * @param savedInstanceState ассоциативный массив-хранилище данных
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
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
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }

}