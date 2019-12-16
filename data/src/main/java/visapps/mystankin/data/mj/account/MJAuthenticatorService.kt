package visapps.mystankin.data.mj.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MJAuthenticatorService: Service() {

    private lateinit var authenticator: MJAuthenticator

    override fun onCreate() {
        super.onCreate()
        authenticator = MJAuthenticator(applicationContext)
    }

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}