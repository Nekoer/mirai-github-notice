package com.hcyacg.utils

import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubTask
import net.mamoe.mirai.utils.MiraiLogger
import okhttp3.*
import java.net.ConnectException
import java.net.Proxy
import java.util.concurrent.TimeUnit

class HttpUtil {
    companion object {
        private val client = OkHttpClient().newBuilder().connectTimeout(60000,TimeUnit.MILLISECONDS).readTimeout(60000,TimeUnit.MILLISECONDS)


        fun request(method: Method, uri: String, body: RequestBody?, headers: Headers, logger: MiraiLogger): String? {

            /**
             * 进行请求转发
             */
            when(method){
                Method.GET -> {
                    return http(Request.Builder().url(uri).headers(headers).get().build(),logger)
                }
                Method.POST -> {
                    return body?.let { Request.Builder().url(uri).headers(headers).post(it).build() }
                        ?.let { http(it,logger) }
                }
                Method.PUT -> {
                    return body?.let { Request.Builder().url(uri).headers(headers).put(it).build() }
                        ?.let { http(it,logger) }
                }
                Method.DEL -> {
                    return http(Request.Builder().url(uri).headers(headers).delete(body).build(),logger)
                }
            }
        }

        /**
         * 发送http请求，返回数据（其中根据proxy是否配置加入代理机制）
         */
        private fun http(request: Request, logger: MiraiLogger): String? {
            var response: Response? = null
            try{

                response = client.build().newCall(request).execute()

                if (response.isSuccessful) {
                    return response.body?.string()
                }

                return null
            }catch (e: ConnectException){
                GithubTask.logger.warning("无法连接到api.github.com")
                return http(request, logger)
            } catch (e : Exception){
                e.printStackTrace()
//                logger.error(e.message)
                return null
            }finally {
                response?.close()
            }
        }

        /**
         * http的请求方式
         */
        enum class Method {
            GET, POST, PUT, DEL
        }
    }
}