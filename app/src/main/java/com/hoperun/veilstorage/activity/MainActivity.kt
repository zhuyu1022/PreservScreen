package com.hoperun.veilstorage.activity

import android.annotation.SuppressLint
import android.text.TextUtils
import com.blankj.utilcode.util.AppUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hoperun.veilstorage.R
import com.hoperun.veilstorage.adapter.BQXXAdapter
import com.hoperun.veilstorage.base.BaseActivity
import com.hoperun.veilstorage.common.Setting
import com.hoperun.veilstorage.entity.MsckEntity
import com.hoperun.veilstorage.net.IServiceImpl
import com.hoperun.veilstorage.net.RequestServiceImpl
import com.hoperun.veilstorage.util.ParseUtils
import com.hoperun.veilstorage.view.SimpleDialog
import com.zhuyu.downloadlibrary.DownloadDialog
import com.zhuyu.downloadlibrary.UpdateInfoBizData
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.toast
import org.json.JSONObject


class MainActivity : BaseActivity() {
    private lateinit var mAdapter: BQXXAdapter

    override fun getContentViewLayoutID(): Int {
        return R.layout.main_activity
    }

    @SuppressLint("SetTextI18n")
    override fun initViewAndEvent() {
        mAdapter = BQXXAdapter()

        recyclerView.adapter = mAdapter

       // verisionTv.text = "版本:"+AppUtils.getAppVersionName()
    }

    override fun initData() {
        query()
        //getNewVersion()

    }


    private fun getNewVersion() {
        showWait()
        RequestServiceImpl.getNewVersion(mMIPHandler, IServiceImpl.GET_NEW_VERSION)
    }


    private fun query() {

      var refreshThread: Thread? = Thread(Runnable {
            while (true) {
                try {
                    Thread.sleep(Setting.getPageRate())

                    RequestServiceImpl.BQGL_GetAllBQXX(mMIPHandler, IServiceImpl.BQGL_GetAllBQXX )

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        })
        refreshThread?.start()

    }







    override fun onPostHandle(requestType: Int, objBody: Any?, success: Boolean) {
        stopWait()
        var msg = ""
        var opt = ""
        if (success) {
            when (requestType) {
                IServiceImpl.GET_NEW_VERSION -> try {
                    val jsonObj = JSONObject(objBody as String)
                    opt = jsonObj.optString("OPT_FLAG")
                    val data = jsonObj.optJSONObject("DATA")
                    msg = jsonObj.optString("MSG_INFO")
                    if ("SUCCESS" == opt && data != null) {
                        val bizData = ParseUtils.parse(data.toString(), UpdateInfoBizData::class.java)
                        if (bizData != null) {
                            val url = bizData.androidUrl
                            val version = bizData.androidversionCode
                            val curVersion = AppUtils.getAppVersionName()
                            val forceUpdate = bizData.androidForced
                            val filename = url.substring(url.lastIndexOf("/") + 1)
                            // 文件保存在应用关联缓存目录
                            val filePath = this.getExternalFilesDir(null).toString() + "/" + filename
                            if (curVersion != version) {

                                if ("1" == forceUpdate) {
                                    SimpleDialog.forceShow(this, "应用有新版本，需强制升级！", SimpleDialog.OnPositiveClickListener {
                                        val dowDialog = DownloadDialog.newInstance(url, filePath, true)
                                        dowDialog.setDownloadListener(object : DownloadDialog.DownloadSimpleListener {
                                            override fun onSuccess() {
                                                AppUtils.installApp(filePath)
                                            }

                                            override fun onError() {
                                                SimpleDialog.show(this@MainActivity, "下载失败！")
                                            }
                                        })
                                        dowDialog.show(supportFragmentManager, "DownLoad")
                                    })
                                } else {
                                    SimpleDialog.show(this, "应用有新版本，是否立刻升级？", SimpleDialog.OnPositiveClickListener {
                                        val dowDialog = DownloadDialog.newInstance(url, filePath, false)
                                        dowDialog.setDownloadListener(object : DownloadDialog.DownloadSimpleListener {
                                            override fun onSuccess() {
                                                AppUtils.installApp(filePath)
                                            }

                                            override fun onError() {
                                                SimpleDialog.show(this@MainActivity, "下载失败！")
                                            }
                                        })
                                        dowDialog.show(supportFragmentManager, "DownLoad")
                                    })
                                }
                            }

                        }

                    } else {
                        if (TextUtils.isEmpty(msg)) {
                            SimpleDialog.show(this, "没有维护更新配置，请联系管理员！")
                        } else {
                            SimpleDialog.show(this, msg)
                        }

                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                    toast("数据解析异常$e")
                }
                IServiceImpl.BQGL_GetAllBQXX -> {
                    if (objBody.toString().isNotEmpty()) {
                        val mskcList = Gson().fromJson<List<MsckEntity>>(objBody.toString(), object : TypeToken<List<MsckEntity>>() {}.type)
                        mAdapter.setNewData(mskcList)

                    } else {
                        toast("暂无数据")
                    }

                }

            }

        } else {
            toast(objBody.toString())
        }
    }
}