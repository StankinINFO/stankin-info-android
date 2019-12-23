package visapps.mystankin.data.util

import android.content.Context
import android.util.Base64
import com.google.crypto.tink.Aead
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import io.reactivex.Single

class CryptoStorage(context: Context) {

    private val aead: Aead

    init{
        TinkConfig.register()
        val keysetHandle= AndroidKeysetManager.Builder()
            .withSharedPref(context, KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
        aead = keysetHandle.getPrimitive(Aead::class.java)
    }

    fun encrypt(plainText: String): Single<String> = Single.create {
        try{
            val plainBytes = plainText.toByteArray(Charsets.UTF_8)
            val result = base64Encode(aead.encrypt(plainBytes, EMPTY_ASSOCIATED_DATA))
            it.onSuccess(result)
        }
        catch (e: Exception){
            it.onError(e)
        }
    }

    fun decrypt(cipherText: String): Single<String> = Single.create {
        try{
            val cipherBytes = base64Decode(cipherText)
            val result = aead.decrypt(cipherBytes, EMPTY_ASSOCIATED_DATA).toString(Charsets.UTF_8)
            it.onSuccess(result)
        }
        catch (e: Exception){
            it.onError(e)
        }
    }

    private fun base64Encode(input: ByteArray): String {
        return Base64.encodeToString(input, Base64.DEFAULT)
    }

    private fun base64Decode(input: String): ByteArray {
        return Base64.decode(input, Base64.DEFAULT)
    }

    companion object {
        private const val PREF_FILE_NAME = "stankin_info_pref"
        private const val KEYSET_NAME = "stankin_info_keyset"
        private const val MASTER_KEY_URI = "android-keystore://stankin_info_master_key"
        private val EMPTY_ASSOCIATED_DATA = ByteArray(0)
    }

}