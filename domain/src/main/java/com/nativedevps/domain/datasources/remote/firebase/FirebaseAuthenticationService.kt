package com.nativedevps.domain.datasources.remote.firebase

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface FirebaseAuthenticationService {
    fun getFirebaseAuth(): FirebaseAuth
    fun signInWithPhoneAuth(
        credential: PhoneAuthCredential,
        callback: (boolean: Boolean, user: FirebaseUser?, error: String?) -> Unit,
    )

    fun sendVerificationCode(
        activity: Activity,
        timeout: Long,
        phoneNumberWithCC: String,
        onVerificationCompleted: (phoneAuthCredential: PhoneAuthCredential) -> Unit, //auto detection
        onCodeSent: (verificationId: String, token: PhoneAuthProvider.ForceResendingToken) -> Unit, //when sent
        onError: (exception: Exception) -> Unit, //when error
    )

    fun getAuthenticatedUser(): FirebaseUser?
}