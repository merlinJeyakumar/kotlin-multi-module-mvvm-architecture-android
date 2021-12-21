package com.nativedevps.data.repositories.remote.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.nativedevps.domain.datasources.remote.firebase.FirebaseAuthenticationService
import java.util.concurrent.TimeUnit

class FirebaseAuthenticationRepository : FirebaseAuthenticationService {
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun signInWithPhoneAuth(
        phoneAuthCredential: PhoneAuthCredential,
        callback: (boolean: Boolean, user: FirebaseUser?, error: String?) -> Unit,
    ) {
        getFirebaseAuth().signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener { taskAuthResult ->
                if (taskAuthResult.isSuccessful) {
                    Log.d("signInWithPhoneAuth", "signInWithCredential:success")
                    val user: FirebaseUser? = taskAuthResult.result?.user
                    callback(true, user, null)
                } else {
                    // Sign in failed, display a message and update the UI
                    if (taskAuthResult.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        callback(
                            false,
                            null,
                            taskAuthResult.exception!!.localizedMessage)
                    } else {
                        Log.e("signInWithPhoneAuth",
                            "signInWithCredential:failure",
                            taskAuthResult.exception)
                    }
                }
            }
    }

    override fun sendVerificationCode(
        activity: Activity,
        timeout: Long,
        phoneNumberWithCC: String,
        onVerificationCompleted: (phoneAuthCredential: PhoneAuthCredential) -> Unit,
        onCodeSent: (verificationId: String, token: PhoneAuthProvider.ForceResendingToken) -> Unit,
        onError: (exception: Exception) -> Unit,
    ) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("sendVerificationCode", "onVerificationCompleted:$credential")
                onVerificationCompleted(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("sendVerificationCode", "onVerificationFailed", e)
                onVerificationFailed(e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                Log.d("sendVerificationCode", "onCodeSent:$verificationId")
                onCodeSent(verificationId, token)
            }
        }

        val options = PhoneAuthOptions.newBuilder(getFirebaseAuth())
            .setPhoneNumber("+91${phoneNumberWithCC}")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun getAuthenticatedUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}