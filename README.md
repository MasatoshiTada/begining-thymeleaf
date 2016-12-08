beginning thymeleaf
====================

2016 Java EEアドベントカレンダーの12日目のサンプルプログラムです。

# クローン

```bash
$ git clone https://github.com/MasatoshiTada/beginning-thymeleaf.git
```

# IDEにインポート

IntelliJで作成していますが、EclipseやNetBeansでもインポート可能です。
Eclipseの場合は、インポート時に[Maven]->[既存のMavenプロジェクト]でインポートしてください。

# 実行

Tomcat 8.0.xにデプロイして、ブラウザで下記のURLにアクセスしてください。

http://localhost:8080/beginning-thymeleaf/

# curlからの多言語対応の確認

日本語

```bash
$ curl -H "Accept-Language: ja" http:/localhost:8080/beginning-thymeleaf/hello?count=0
```

英語

```bash
$ curl -H "Accept-Language: en" http://localhost:8080/beginning-thymeleaf/hello?count=0
```