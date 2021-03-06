package com.yunmoxx.store.protector

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 应用安装监听
 *
 * @author hyvenzhu
 * @version 2021-03-11
 */
class PackageListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val packageName = intent.data!!.schemeSpecificPart
        when {
            intent.action.equals(Intent.ACTION_PACKAGE_ADDED) -> {
                if (ProtectorService.appPackageName == packageName) {
                    // 主程序安装成功，拉起主程序
                    Log.d("PackageListener", "服务监控：主程序安装成功，拉起主程序")
                    context.startService(Intent(context, ProtectorService::class.java))
                }
            }
            intent.action.equals(Intent.ACTION_PACKAGE_REPLACED) -> {
                if (ProtectorService.appPackageName == packageName) {
                    // 主程序升级成功，拉起主程序
                    Log.d("PackageListener", "服务监控：主程序升级成功，拉起主程序")
                    context.startService(Intent(context, ProtectorService::class.java))
                }
            }
        }
    }
}