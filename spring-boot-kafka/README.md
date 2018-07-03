### 参考文档

1. [Kafka 安装及快速入门](https://zhuanlan.zhihu.com/p/32780543)
2. [SpringBoot Kafka 整合使用](https://zhuanlan.zhihu.com/p/32780164)

### PS

1. 如果kafka部署在服务器上需要外网访问，需要在```server.properties```上做如下设置

```
advertised.listeners=PLAINTEXT://xx.xx.xx.xx:9092
```

2. 如果要查看kafka在zookeeper上的注册信息，可以通过如下命令

```
bin/zookeeper-shell.sh localhost:2181 <<< "get /brokers/ids/1"
```