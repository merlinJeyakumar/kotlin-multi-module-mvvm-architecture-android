package com.data.repositories.remote.google.authentication

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.nativedevps.support.inline.orElse
import javax.inject.Inject


class GoogleAuthentication @Inject constructor(private val context: Context) {
    companion object {
        const val CONST_SIGNING_RESULT = 999
    }

    data class SignInModel(
        var email: String,
        var givenName: String,
    )

    /**
     * invoke signIn option
     **/
    fun signIn(
        activity: Activity,
        signOut: Boolean,
        callback: (exception: Exception) -> Unit,
    ) {
        if (signOut) {
            getSignInIntent().signOut()
        }
        val account = getLastSignedAccount()
        account?.let {
            activity.startActivityForResult(getSignInIntent().signInIntent, CONST_SIGNING_RESULT)
        }.orElse {
            callback(Exception("already signed in"))
        }
    }

    fun signOut(callback: (exception: java.lang.Exception?) -> Unit) {
        getSignInIntent().signOut().addOnCompleteListener {
            if (it.isSuccessful) {
                callback(null)
            } else {
                callback(it.exception!!)
            }
        }
    }

    /**
     *  support method to handle activity result to retrieve SignInModel attributes
     **/
    fun onActivityResult(
        resultCode: Int,
        data: Intent,
        callback: (
            signInModel: SignInModel?,
            exception: Exception?,
        ) -> Unit,
    ) {
        if (resultCode == RESULT_OK) {
            GoogleSignIn.getSignedInAccountFromIntent(data).apply {
                if (isSuccessful) {
                    getResult(ApiException::class.java).let {
                        SignInModel(it.email!!, it.givenName ?: it.displayName ?: it.familyName!!)
                    }.apply {
                        callback(this, null)
                    }
                } else {
                    callback(null, Exception(this.exception))
                }
            }
        } else {
            callback(null, Exception("action cancelled"))
        }
    }

    private fun getLastSignedAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    private fun getSignInIntent(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        return GoogleSignIn.getClient(context, gso)
    }
}