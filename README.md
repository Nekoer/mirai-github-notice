# mirai-github-notice
github项目更新通知
- /github start 开始监控
- /github stop 停止监控
- /github reload 重载配置

```
{
  "admin": [
    123456, #管理
    2222
  ],
  "token": "githubToken",
  "project": [
    {
      "name": "作者/仓库名",
      "branch": "分支"
    }
  ],
  "group": [ #能被推送到的群
    111,
    222
  ]
}
```
