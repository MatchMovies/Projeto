package com.br.matchmovies.imagecapture

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.br.matchmovies.view.EditarCadastro

class PermissionsHelper(
    private val activity: EditarCadastro
) {

    fun requestPermission(permission: String): Boolean {
        val isGranted = ContextCompat.checkSelfPermission(activity, permission)

        return if (isGranted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                1
            )
            false
        } else {
            Log.d(
                PermissionsHelper::class.java.toString(),
                "permission already granted: $permission"
            )
            true
        }
    }

    fun requestAllPermission(permissions: List<String>): Boolean {
        val permissionsNotGranted = mutableListOf<String>()

        permissions.forEach {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsNotGranted.add(it)
            }
        }

        return if (permissionsNotGranted.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                permissionsNotGranted.toTypedArray(),
                2
            )
            false
        } else {
            Log.d(PermissionsHelper::class.java.toString(), "all permission granted")
            true
        }
    }

    fun handleRequestPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (permissions.isEmpty()) return

        when (requestCode) {
            1 -> {
                if (shouldAskRationale(permissions[0])) {
                    AlertDialog.Builder(activity).setTitle("Alerta de permissao importante")
                        .setMessage("Porque utiliza a permissao")
                        .setPositiveButton("OK") { _, _ ->
                            requestPermission(permissions[0])
                        }.setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }.show()

                }
            }
            else -> {

            }
        }
    }

    private fun shouldAskRationale(permission: String) =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                activity.shouldShowRequestPermissionRationale(permission)
}