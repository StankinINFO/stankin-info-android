package visapps.mystankin.data.util

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import io.reactivex.Single
import kotlin.Exception

class EncryptionProvider(context: Context, keysProvider: KeysProvider) {

    private val aead: Aead

    init{
        TinkConfig.register()
        val masterKeyUri = keysProvider.getMasterKeyUri(MASTER_KEY_ALIAS)
        val keysetHandle= AndroidKeysetManager.Builder()
            .withSharedPref(context, KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(masterKeyUri)
            .build()
            .keysetHandle
        aead = keysetHandle.getPrimitive(Aead::class.java)
    }

    fun encrypt(plainText: CharArray): Single<String> = Single.create {
        try{
            val plainBytes = String(plainText).toByteArray(Charsets.UTF_8)
            val result = aead.encrypt(plainBytes, MASTER_KEY_ASSOCIATED.toByteArray()).toString(Charsets.UTF_8)
            it.onSuccess(result)
        }
        catch (e: Exception){
            it.onError(e)
        }
    }

    fun decrypt(cipherText: String): Single<CharArray> = Single.create {
        try{
            val cipherBytes = cipherText.toByteArray(Charsets.UTF_8)
            val result = aead.decrypt(cipherBytes, MASTER_KEY_ASSOCIATED.toByteArray()).toString(Charsets.UTF_8).toCharArray()
            it.onSuccess(result)
        }
        catch (e: Exception){
            it.onError(e)
        }
    }

    companion object {
        const val MASTER_KEY_ASSOCIATED = "STANKIN_INFO_MASTER"
        const val MASTER_KEY_ALIAS = "STANKIN_INFO_MASTER_KEY"
        const val KEYSET_NAME = "STANKIN_INFO_KEYSET"
        const val PREF_FILE_NAME = "STANKIN_INFO_KEYSET_FILE"
    }

}