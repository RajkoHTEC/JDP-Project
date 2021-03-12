package com.htec.jdp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult : TextView
    private var asyncTask : MyAsyncTask? = null
    private var thread: Thread? = null
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        tvResult = findViewById<TextView>(R.id.tvThreadResult)
        findViewById<Button>(R.id.btnStartThread).setOnClickListener {
            testThread()
            //testAsyncTask()
        }

        findViewById<Button>(R.id.btnStopThread).setOnClickListener {
            asyncTask?.cancel(true)
           // thread?.stop()
        }
    }

    private fun testAsyncTask() {
        asyncTask = MyAsyncTask().also {it.execute()}
    }

    private fun testThread() {
        /*Thread {
            tvResult.post {
                tvResult.text = "Hello from new thread"
            }

            this@MainActivity.runOnUiThread {
                tvResult.text = "Hello from UI Thread"
            }

            tvResult.postDelayed({
                tvResult.text = "${tvResult.text} and welcome"
            }, TimeUnit.SECONDS.toMillis(5))
        }.start()*/

        thread = Thread {
            for (i in 1..10) {
                Thread.sleep(500L)

                handler.post {
                //this@MainActivity.runOnUiThread {
                    val progress = (i*10)
                    tvResult.post { tvResult.text = "Progress: $progress%"  }
                }

            }
        }.also {
            it.start()
        }
    }

     inner class MyAsyncTask : AsyncTask<Void, Int, Long>() {
        override fun doInBackground(vararg params: Void): Long {
            for (i in 1..10) {
                Thread.sleep(500L)
                publishProgress(i*10)
                if(isCancelled) break
            }
            return 200L
        }

        override fun onProgressUpdate(vararg values: Int?) {
            setProgressPercent(values[0])
        }


        private fun setProgressPercent(i: Int?) {
            tvResult.text = "Progress: $i% percent"
        }

         override fun onPostExecute(result: Long?) {
             tvResult.text = "Done"
         }

         override fun onCancelled() {
             tvResult.text = "Cancel"
         }
     }
}