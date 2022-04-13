package com.hcyacg.github

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubTask
import com.hcyacg.GithubTask.Companion.token
import com.hcyacg.entity.Branch
import net.mamoe.mirai.utils.MiraiLogger
import okhttp3.*
import okhttp3.internal.closeQuietly
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

/**
 * 获取配置中project所有项目各自的分支
 */
class Branches {
    private val headers =
        Headers.Builder().add("Accept", "application/vnd.github.v3+json").add("Authorization", "token ${GithubTask.token}")
    private val requestBody: RequestBody? = null
    val logger:MiraiLogger = MiraiLogger.Factory.create(Branches::class, "Bot")
    private val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
        .readTimeout(60000, TimeUnit.MILLISECONDS)

    fun getBranchesByRepo(projects : JSONArray) : HashMap<String,List<Branch>>{

        var data: String? = null
        val list = HashMap<String,List<Branch>>()
        var response: Response? = null
        try {


            for (project in projects){
                val request = if (RateLimits().isResidue()){
                    Request.Builder()
                        .url("https://api.github.com/repos/$project/branches")
                        .addHeader("Authorization", "token $token")
                        .addHeader("Accept", "application/vnd.github.v3+json").build()

                }else{
                    Request.Builder()
                        .url("https://api.github.com/repos/$project/branches")
                        .addHeader("Accept", "application/vnd.github.v3+json").build()
                }


                response = client.build().newCall(request).execute()

                if (response.isSuccessful) {
                    data = response.body?.string()
                }

                val temp: JSONArray? = JSONArray.parseArray(data)
                val tempList = mutableListOf<Branch>()

                if (temp != null) {
                    for (t in temp){

                        val name = JSONObject.parseObject(t.toString())!!.getString("name")
                        val protected = JSONObject.parseObject(t.toString()).getBoolean("protected")
                        val sha = JSONObject.parseObject(JSONObject.parseObject(t.toString()).getString("commit")).getString("sha")
                        val url = JSONObject.parseObject(JSONObject.parseObject(t.toString()).getString("commit")).getString("url")
                        tempList.add(Branch(name,sha,url,protected))
                    }
                }

                list[project.toString()] = tempList
            }


            return list
        }catch (e: SocketTimeoutException){
            logger.warning("请求超时")
            return getBranchesByRepo(projects)
        }catch (e: ConnectException){
            logger.warning("无法连接到api.github.com")
            return getBranchesByRepo(projects)
        } catch (e: Exception) {
            e.printStackTrace()
            return getBranchesByRepo(projects)
        }finally {
            response?.close()
        }
    }

}