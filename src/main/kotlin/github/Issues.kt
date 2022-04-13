package com.hcyacg.github

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubTask
import com.hcyacg.utils.CardUtil
import com.hcyacg.utils.HttpUtil
import entity.Issue
import entity.IssueItem
import entity.Release
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.ContactUtils.getFriendOrGroup
import net.mamoe.mirai.utils.MiraiLogger
import okhttp3.Headers
import okhttp3.RequestBody
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*

class Issues {
    val logger: MiraiLogger = MiraiLogger.Factory.create(Issues::class, "Bot")
    private val headers = Headers.Builder().add("Accept","application/vnd.github.v3+json").add("Authorization","token ${GithubTask.token}")
    private val requestBody: RequestBody? = null

    @OptIn(ConsoleExperimentalApi::class)
    suspend fun checkIssuesUpdate(
        projects: Any?
    ){
        val bots = Bot.instances
        var time: String? = null
        var myDate :Date? =null
        try{
            if (!RateLimits().isResidue()){
                headers.removeAll("Authorization")
            }
            val data = HttpUtil.request(
                method = HttpUtil.Companion.Method.GET,
                uri = "https://api.github.com/repos/$projects/issues?state=open",
                body = requestBody,
                headers = headers.build(),
                logger = logger
            )

            if (null == data || JSONArray.parseArray(data).size < 1){
                return
            }

            val issueItem = JSONObject.parseObject(JSONArray.parseArray(data)[0].toString(), IssueItem::class.java)

            if (null == GithubTask.issueItem[projects.toString()]?.nodeId){
                GithubTask.issueItem[projects.toString()] = issueItem
                return
            }

            if (GithubTask.issueItem[projects.toString()]!!.nodeId == issueItem.nodeId){
                return
            }
            GithubTask.issueItem[projects.toString()] = issueItem

            if (null != issueItem.createdAt){
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                myDate = dateFormat.parse(issueItem.createdAt.toString().replace("Z", "+0000"))
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                time = sdf.format(Date(myDate.time.toString().toLong()))

            }

            if (myDate != null) {
                if (Date().time-(5 *60 * 1000) > myDate.time){
                    return
                }
            }

            for (e in GithubTask.groups) {
                for (bot in bots){

                    bot.getGroup(e.toString().toLong())?.sendMessage(
                        CardUtil().process(
                            message = issueItem.body.toString(),
                            html = issueItem.htmlUrl.toString(),
                            avatar = issueItem.user!!.avatarUrl.toString(),
                            time = time.toString(),
                            name = issueItem.user.login.toString()+ "为${projects.toString()}提交了新问题",
                            event = bot.getFriendOrGroup(e.toString().toLong())
                        )
                    )
                }
            }

            for (u in GithubTask.users) {
                for (bot in bots){
                    bot.getStranger(u.toString().toLong())?.sendMessage(
                        CardUtil().process(
                            message = issueItem.body.toString(),
                            html = issueItem.htmlUrl.toString(),
                            avatar = issueItem.user!!.avatarUrl.toString(),
                            time = time.toString(),
                            name = issueItem.user.login.toString()+ "为${projects.toString()}提交了新问题",
                            event = bot.getFriendOrGroup(u.toString().toLong())
                        )
                    )
                }
            }
        }catch (e: SocketTimeoutException){
            logger.warning("请求超时")
            return
        }catch (e: ConnectException){
            GithubTask.logger.warning("无法连接到api.github.com")
            return
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}