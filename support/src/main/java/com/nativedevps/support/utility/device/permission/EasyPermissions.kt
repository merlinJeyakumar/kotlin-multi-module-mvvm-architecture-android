package com.nativedevps.support.utility.device.permission

import android.Manifest
import android.R
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.*

object EasyPermissions {

    private val TAG = "EasyPermissions"
    internal var timeWhenRequestingStart: Long = 0
    internal lateinit var `object`: Any
    //private var finalPermissionDialog: MConfirmationDialog? = null
    private var callbacks: PermissionCallbacks? = null
    private var permissionGroups: HashMap<String, Array<String>>? = null

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context the calling context.
     * @param perms   one ore more permissions, such as `android.Manifest.permission.CAMERA`.
     * @return true if all permissions are already granted, false if at least one permission
     * is not yet granted.
     */
    fun hasPermissions(context: Context?, vararg perms: String): Boolean {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default")
            return true
        }

        for (perm in perms) {
            val hasPerm = ContextCompat.checkSelfPermission(context!!, perm) == PackageManager.PERMISSION_GRANTED
            if (!hasPerm) {
                return false
            }
        }

        return true
    }

    /**
     * Request a set of permissions, showing rationale if the system requests it.
     *
     * @param object      Activity or Fragment requesting permissions. Should implement
     * [ActivityCompat.OnRequestPermissionsResultCallback]
     * or
     * @param rationale   a message explaining why the application needs this set of permissions, will
     * be displayed if the user rejects the request the first time.
     * @param requestCode request code to track this request, must be < 256.
     * @param perms       a set of permissions to be requested.
     */
    fun requestPermissions(
        `object`: Any, rationale: String,
        requestCode: Int, callback: PermissionCallbacks, vararg perms: String
    ) {
        requestPermissions(
            `object`, rationale,
            R.string.ok,
            R.string.cancel,
            requestCode, callback, *perms
        )
    }

    /**
     * Request a set of permissions, showing rationale if the system requests it.
     *
     * @param object      Activity or Fragment requesting permissions. Should implement
     * [ActivityCompat.OnRequestPermissionsResultCallback]
     * or
     * be displayed if the user rejects the request the first time.
     * @param requestCode request code to track this request, must be < 256.
     * @param perms       a set of permissions to be requested.
     */
    fun requestPermissions(
        `object`: Any,
        requestCode: Int, callback: PermissionCallbacks, vararg perms: String
    ) {
        requestPermissions(
            `object`, "",
            R.string.ok,
            R.string.cancel,
            requestCode, callback, *perms
        )
    }

    /**
     * Request a set of permissions, showing rationale if the system requests it.
     *
     * @param obj            Activity or Fragment requesting permissions. Should implement
     * [ActivityCompat.OnRequestPermissionsResultCallback]
     * or
     * @param rationale      a message explaining why the application needs this set of permissions, will
     * be displayed if the user rejects the request the first time.
     * @param positiveButton custom text for positive button
     * @param negativeButton custom text for negative button
     * @param requestCode    request code to track this request, must be < 256.
     * @param permission     a set of permissions or permission.group to be requested.
     */
    fun requestPermissions(
        obj: Any, rationale: String,
        @StringRes positiveButton: Int,
        @StringRes negativeButton: Int,
        requestCode: Int, callback: PermissionCallbacks, vararg permission: String
    ) {

        callbacks = callback
        `object` = obj
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // only for lower of M
            //            PermissionCallbacks callbacks = (PermissionCallbacks) object;
            callbacks!!.onPermissionsGranted(requestCode, ArrayList(Arrays.asList(*permission)))
            return
        }


        checkCallingObjectSuitability(`object`)
        //        final PermissionCallbacks callbacks = (PermissionCallbacks) object;
        val perms = getActualPermissions(
            `object`,
            permission
        )

        if (perms.size <= 0) {
            callbacks!!.onPermissionsGranted(requestCode, ArrayList(Arrays.asList(*permission)))
            return
        }

        var shouldShowRationale = false
        for (perm in perms) {
            shouldShowRationale = shouldShowRationale || shouldShowRequestPermissionRationale(
                `object`, perm)
        }

        if (shouldShowRationale) {
            if (!TextUtils.isEmpty(rationale)) {
                Log.i(TAG, "shouldShowRationale: ")

//                val builder = MConfirmationDialog.Builder(getActivity(`object`)!!)
//                    .setMessage(rationale)
//                    .setTitle("Permission necessary")
//                    .setPositiveButton(positiveButton)
//                    .setNegativeButton(negativeButton)
//                    .setOnNegativeClickListener(DialogInterface.OnClickListener { dialog, which ->
//                        callbacks!!.onPermissionsDenied(requestCode, Arrays.asList(*perms))
//                        finalPermissionDialog!!.dismiss()
//                    }).setOnPositiveClickListener(DialogInterface.OnClickListener { dialog, which ->
//                        executePermissionsRequest(`object`, perms, requestCode)
//                        finalPermissionDialog!!.dismiss()
//                    })
//                finalPermissionDialog = MConfirmationDialog(getActivity(`object`), builder)
//                finalPermissionDialog!!.show()
            } else {
                executePermissionsRequest(`object`, perms, requestCode)
            }
        } else {
            for (perm in perms) {
                shouldShowRationale = shouldShowRationale || shouldShowRequestPermissionRationale(
                    `object`, perm)
            }
            if (shouldShowRationale) {
                Log.d(TAG, "requestPermissions: show dialog")

            } else {
                timeWhenRequestingStart = System.currentTimeMillis()
                executePermissionsRequest(`object`, perms, requestCode)
            }

        }
    }


    private fun getActualPermissions(`object`: Any, permission: Array<out String>): Array<String> {
        initPermissionGroups()
        val permissionList = ArrayList<String>()
        for (indiPerm in permission) {
            if (permissionGroups!!.containsKey(indiPerm)) {
                val arr = permissionGroups!![indiPerm]
                for (s in arr!!) {
                    if (!hasPermissions(getActivity(`object`), s)) {
                        permissionList.add(s)
                    }
                }
            } else {
                if (!hasPermissions(getActivity(`object`), indiPerm)) {
                    permissionList.add(indiPerm)
                }
            }
        }
        val set = LinkedHashSet(permissionList)

        return set.toTypedArray()
    }

    private fun initPermissionGroups() {
        if (permissionGroups == null) {
            permissionGroups = HashMap()
            permissionGroups!![Manifest.permission_group.CALENDAR] =
                arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
            permissionGroups!![Manifest.permission_group.CAMERA] = arrayOf(Manifest.permission.CAMERA)
            permissionGroups!![Manifest.permission_group.CONTACTS] =
                arrayOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS
                )
            permissionGroups!![Manifest.permission_group.LOCATION] =
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            permissionGroups!![Manifest.permission_group.MICROPHONE] = arrayOf(Manifest.permission.RECORD_AUDIO)
            permissionGroups!![Manifest.permission_group.PHONE] = arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.ADD_VOICEMAIL,
                Manifest.permission.USE_SIP,
                Manifest.permission.PROCESS_OUTGOING_CALLS
            )
            permissionGroups!![Manifest.permission_group.SENSORS] = arrayOf(Manifest.permission.BODY_SENSORS)
            permissionGroups!![Manifest.permission_group.SMS] = arrayOf(
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.RECEIVE_MMS
            )
            permissionGroups!![Manifest.permission_group.STORAGE] =
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    /**
     * Handle the result of a permission request, should be called from the calling Activity's
     * [ActivityCompat.OnRequestPermissionsResultCallback.onRequestPermissionsResult]
     * method.
     *
     *
     * If any permissions were granted or denied, the Activity will receive the appropriate
     * callbacks through [PermissionCallbacks] and methods annotated with
     *
     * @param requestCode  requestCode argument to permission result callback.
     * @param permissions  permissions argument to permission result callback.
     * @param grantResults grantResults argument to permission result callback.
     * @throws IllegalArgumentException if the calling Activity does not implement
     * [PermissionCallbacks].
     */
    fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var isPermenantlyDisabled = false
        //        checkCallingObjectSuitability(object);
        //        PermissionCallbacks callbacks = (PermissionCallbacks) object;

        // Make a collection of granted and denied permissions from the request.
        val granted = ArrayList<String>()
        val denied = ArrayList<String>()
        for (i in permissions.indices) {
            val perm = permissions[i]
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm)
            } else {
                /**if deny then it will true and on never as me again it will return false
                 */
                if(EasyPermissions::`object`.isInitialized) {
                    val showRationale = shouldShowRequestPermissionRationale(`object`, perm)
                    if (showRationale) {
                        isPermenantlyDisabled = false //deny
                        //                        timeWhenRequestingStart = System.currentTimeMillis() - 2;
                    } else {
                        isPermenantlyDisabled = true //never ask me again
                    }
                    denied.add(perm)
                }
            }
        }

        // Report granted permissions, if any.
        if (!granted.isEmpty() && denied.isEmpty()) {
            // Notify callbacks
            callbacks!!.onPermissionsGranted(requestCode, granted)
        } else if (granted.isEmpty() && !denied.isEmpty() && isPermenantlyDisabled) {
            val diff = System.currentTimeMillis() - timeWhenRequestingStart
            //            if (diff < 350) {
            //means it is permenantly disabled
            callbacks!!.onPermissionsPermanentlyDeclined(requestCode, denied)
            //            }
            Log.i("TAG", diff.toString() + "")
        }// Report denied permissions, if any.
        /*if (!denied.isEmpty()) {
            callbacks.onPermissionsDenied(requestCode, denied);
        }*///if 100% fail then check for whether timing

        // Report denied permissions, if any.
        if (!denied.isEmpty() && !isPermenantlyDisabled) {
            callbacks!!.onPermissionsDenied(requestCode, denied)
        }

        /*// If 100% successful, call annotated methods
        if (!granted.isEmpty() && denied.isEmpty()) {
            runAnnotatedMethods(object, requestCode);
        }*/
    }

    @TargetApi(23)
    private fun shouldShowRequestPermissionRationale(`object`: Any, perm: String): Boolean {
        return if (`object` is Activity) {
            ActivityCompat.shouldShowRequestPermissionRationale(`object`, perm)
        } else (`object` as? Fragment)?.shouldShowRequestPermissionRationale(perm)
            ?: ((`object` as? android.app.Fragment)?.shouldShowRequestPermissionRationale(perm) ?: false)
    }

    @TargetApi(23)
    private fun executePermissionsRequest(`object`: Any, perms: Array<String>, requestCode: Int) {
        checkCallingObjectSuitability(`object`)

        if (`object` is Activity) {
            ActivityCompat.requestPermissions(`object`, perms, requestCode)
        } else if (`object` is Fragment) {
            `object`.requestPermissions(perms, requestCode)
        } else if (`object` is android.app.Fragment) {
            `object`.requestPermissions(perms, requestCode)
        }
    }

    @TargetApi(11)
    private fun getActivity(`object`: Any): Activity? {
        return `object` as? Activity ?: if (`object` is Fragment) {
            `object`.activity
        } else if (`object` is android.app.Fragment) {
            `object`.activity
        } else {
            null
        }
    }

    private fun checkCallingObjectSuitability(`object`: Any) {
        // Make sure Object is an Activity or Fragment
        val isActivity = `object` is Activity
        val isSupportFragment = `object` is Fragment
        val isAppFragment = `object` is android.app.Fragment
        val isMinSdkM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

        if (!(isSupportFragment || isActivity || isAppFragment && isMinSdkM)) {
            if (isAppFragment) {
                throw IllegalArgumentException(
                    "Target SDK needs to be greater than 23 if caller is android.app.Fragment"
                )
            } else {
                throw IllegalArgumentException("Caller must be an Activity or a Fragment.")
            }
        }

        /*// Make sure Object implements callbacks
        if (!(object instanceof PermissionCallbacks)) {
            throw new IllegalArgumentException("Caller must implement PermissionCallbacks.");
        }*/
    }

    fun startSetting() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", getActivity(`object`)!!.packageName, null)
        intent.data = uri
        getActivity(`object`)!!.startActivity(intent)
    }

    interface PermissionCallbacks {

        fun onPermissionsGranted(requestCode: Int, perms: List<String>)

        fun onPermissionsDenied(requestCode: Int, perms: List<String>)

        fun onPermissionsPermanentlyDeclined(requestCode: Int, perms: List<String>)
    }

    /*private fun showSamplePermission() {
        EasyPermissions.requestPermissions(this, "Rational message", 102, object : EasyPermissions.PermissionCallbacks {
            override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
                Log.w("Permission:", "GRANTED");
            }

            override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
                Log.w("Permission:", "Denied");
            }

            override fun onPermissionsPermanentlyDeclined(requestCode: Int, perms: List<String>) {
                Log.w("Permission:", "Declined");
            }

        }, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }*/

}
