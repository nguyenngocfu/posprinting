package co.`fun`.orderon.pos

import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.dantsu.escposprinter.connection.DeviceConnection
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import java.text.SimpleDateFormat

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "co.`fun`.orderon.pos.action.FOO"
private const val ACTION_BAZ = "co.`fun`.orderon.pos.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "co.`fun`.orderon.pos.extra.PARAM1"
private const val EXTRA_PARAM2 = "co.`fun`.orderon.pos.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class PrintingService : IntentService("PrintingService") {

    override fun onHandleIntent(intent: Intent?) {
        val action = intent?.action
        val data = intent?.data
        if (data != null) {
            val textToBePrinted = data.getQueryParameter("content")
            printBluetooth(textToBePrinted)
        }
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val notification: Notification = Notification.Builder(this)
            .setContentTitle("Printing")
            .setContentText("Printing")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setTicker("Printing")
            .build()

// Notification ID cannot be 0.
        startForeground(22, notification)
        /*when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }
        }*/
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String?, param2: String?) {
        TODO("Handle action Foo")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String?, param2: String?) {
        TODO("Handle action Baz")
    }

    fun printBluetooth(data: String?) {
        AsyncBluetoothEscPosPrint(this).execute(
            this.getAsyncEscPosPrinter(
                selectBluetoothPrinter(),
                data
            )
        )
    }

    private fun selectBluetoothPrinter() : BluetoothConnection? {
        val bluetoothDevicesList = BluetoothPrintersConnections().list
        return bluetoothDevicesList?.get(0)
    }

    fun getAsyncEscPosPrinter(
        printerConnection: DeviceConnection?,
        data: String?
    ): AsyncEscPosPrinter? {
        val format = SimpleDateFormat("'on' yyyy-MM-dd 'at' HH:mm:ss")
        val printer = AsyncEscPosPrinter(printerConnection, 203, 48f, 32)
        return printer.setTextToPrint(data)
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, PrintingService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, PrintingService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }


}