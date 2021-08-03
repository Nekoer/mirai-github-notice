# mirai-github-notice
github项目更新通知
- /github start 开始监控
- /github stop 停止监控
- /github reload 重载配置
- /github rate_limit 查看Github api可用次数(只在群内发送有效)

```
{
  "admin": [
    123456, #管理
    2222
  ],
  "task-millisecond": 5000,
  "token": "githubToken",
  "project": [
      "作者/仓库名"
  ],
  "group": [ #能被推送到的群
    111,
    222
  ],
  "users": [
    4876
  ]
}
```
