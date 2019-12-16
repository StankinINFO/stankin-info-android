package visapps.mystankin.data.util

import android.annotation.TargetApi
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator

class KeysStorage {

    private val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)

    fun getMasterKeyUri(keyAlias: String): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getMasterKeyUriM(keyAlias)
    } else {
        ""
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun getMasterKeyUriM(keyAlias: String): String =
        try{
            val keyGenParameterSpec = createKeyGenParameterSpec(keyAlias)
            if (!keyExists(keyGenParameterSpec.keystoreAlias)) {
                generateKey(keyGenParameterSpec)
            }
            KEYSTORE_PATH_URI + keyGenParameterSpec.keystoreAlias
        }
        catch (e: Exception){
            ""
        }

    @TargetApi(Build.VERSION_CODES.M)
    private fun generateKey(keyGenParameterSpec: KeyGenParameterSpec) {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)
        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun createKeyGenParameterSpec(keyAlias: String): KeyGenParameterSpec =
        KeyGenParameterSpec.Builder(keyAlias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setKeySize(AES_KEY_SIZE)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

    private fun keyExists(keyAlias: String): Boolean {
        keyStore.load(null)
        return keyStore.containsAlias(keyAlias)
    }

    companion object {
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val KEYSTORE_PATH_URI = "android-keystore://"
        private const val AES_KEY_SIZE = 256
    }
}