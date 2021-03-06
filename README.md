Pine Cone Continue Delivery Platform
===

松果持续交付平台

[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fcd-in-practice%2Fpine-cone-continue-delivery-platform%2Fbadge&style=popout-square)](https://actions-badge.atrox.dev/cd-in-practice/pine-cone-continue-delivery-platform/goto)


### 概念定义
#### Artifact(制品)
Artifact可以是一个jar包、一个二进制文件、一个Docker镜像，至于它能否被部署，并不是由它本身定义的。
是由使用的它的人定义的。

#### App
是一个虚拟的概念，是所有研发元数据的集成点。即制品信息、部署历史、告警、配置信息的关联中心。

Artifact可直接转成App

#### Pipeline
一系列动作的集合。它不关心执行的内容是什么。执行过程的元数据，应该发送给CDP。

#### Config
配置，但是和市面上的配置管理不同。配置项的定义与配置项的使用是两回事。



### 开发日志
* 7/25/2021: GitHub Actions试用：
    1. 实现maven构建，docker构建，docker push；
    2. 使用了加密的环境变量；
    3. 遗留问题：不知道它的default变量该如何引用。
* 7/14/2021: 设计DevOps的UI
* 7/12/2021: 试用coding流水线
    * 支持原生的Jenkinsfile，灵活。这也意味着对人的能力要求高了；
    * 环境变量丰富，但是对新手来说有点难找；支持加密环境变量，但是不确定这个环境变量是否支持项目级的；
    * 构建环境的准备：默认构建环境只试用于demo演示给老板看。真正的构建环境的准备，建议还是自定义。明确的版本控制，会给软件工程带来更好的确定性；
    * 通过制品的上传：使用curl上传，没毛病。只是文档在告诉我使用USERNAME:PASSWORD时，我一下没反应过来。按我的理解，这应该是一个独立的账号才对。另一个问题，如果是一个项目里的多个仓库，该如何处理？
    
* 7/9/2021: 加入对Pipeline与App之间的建模，引入分页实现
* 7/7/2021: 加入Mock模块用于测试，解耦Id的生成实现
* 7/6/2021: 引入gRPC服务模块
* 7/1/2021: 对制品、App进行建模
* 6/30/2021: 改名为pine cone continue delivery platform
* 6/23/2021: 试用阿里云效（1）尽早工程化原则 
    * 自身的自动化构建
        * 构建环境的准备：每个步骤都要重新选一次，配置麻烦。比如我是JDK11，但是每个Java相关的步骤里都是JDK8。
            * 改进：
                1. 平台自动检查出构建环境或者提供一个“应用到所有”步骤的功能。
    * 代码质量
        * 自动集成p3c-pmd
    * 制品管理
        * 自定义版本号：提示不友好。而且不够灵活，如何只取commitId的前15拉？Docker镜像目前没有被当成制品，因为制品库中都没有。最后我无法从流水线中轻松地找到我的镜像的版本号。
        * 上传制品时权限问题：通过RAM控制
    * 工程化问题：
        * 构建模板：没看到。只能一开始使用云效的模块。不能自定义模块。
    
* 6/22/2021: 初始化代码仓库，对制品进行建模，引入Ebean和IoC，在单元测试中加入testcontainers



