load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# java

RULES_JVM_EXTERNAL_TAG = "4.0"

RULES_JVM_EXTERNAL_SHA = "31701ad93dbfe544d597dbe62c9a1fdd76d81d8a9150c2bf1ecf928ecdf97169"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

http_archive(
    name = "rules_proto_grpc",
    sha256 = "fa7a59e0d1527ac69be652407b457ba1cb40700752a3ee6cc2dd25d9cb28bb1a",
    strip_prefix = "rules_proto_grpc-3.1.0",
    urls = ["https://github.com/rules-proto-grpc/rules_proto_grpc/archive/3.1.0.tar.gz"],
)

load("@rules_proto_grpc//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")

rules_proto_grpc_toolchains()

rules_proto_grpc_repos()

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

load("@rules_proto_grpc//java:repositories.bzl", rules_proto_grpc_java_repos = "java_repos")

rules_proto_grpc_java_repos()

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

maven_install(
    artifacts = [
        "com.google.errorprone:error_prone_annotations:2.0.18",
        "com.google.j2objc:j2objc-annotations:1.1",
        "javax.persistence:persistence-api:1.0.2",
        "io.ebean:ebean:12.15.0",
        "org.apache.shiro:shiro-spring-boot-web-starter:1.8.0",
        "io.ebean:ebean-api:12.15.0",
        "io.ebean:ebean-core:12.15.0",
        "io.ebean:ebean-datasource:7.5",
        "io.ebean:ebean-ddl-generator:7.5",
        "io.ebean:querybean-generator:12.15.0",
        "org.hamcrest:hamcrest-library:2.2",
        "javax.annotation:javax.annotation-api:1.3.2",
        "javax.validation:validation-api:2.0.1.Final",
        "org.springframework.boot:spring-boot-starter-thymeleaf:2.5.1",
        "org.springframework.boot:spring-boot-starter-web:2.5.1",
        "org.springframework.boot:spring-boot-starter:2.5.1",
        "org.springframework.boot:spring-boot-autoconfigure:2.5.1",
        "org.springframework.boot:spring-boot-test:2.5.1",
        "org.springframework.boot:spring-boot:2.5.1",
        "org.springframework:spring-context:5.3.8",
        "org.springframework:spring-beans:5.3.8",
        "io.micrometer:micrometer-registry-prometheus:1.7.0",
        "io.github.lognet:grpc-spring-boot-starter:4.5.5",
        "com.github.seratch:notion-sdk-jvm-core:0.1.12",
        "org.apache.rocketmq:rocketmq-client:4.3.0",
        "org.apache.rocketmq:rocketmq-remoting:4.3.0",
        "com.fasterxml.jackson.core:jackson-databind:2.12.5",
        "com.fasterxml.jackson.core:jackson-annotations:2.12.5",
        "com.fasterxml.jackson.core:jackson-core:2.12.5",
        "junit:junit:4.12",
        "org.postgresql:postgresql:42.2.23",
        "com.vladmihalcea:hibernate-types-52:2.1.1",
        "org.mockito:mockito-core:1.10.19",
        "org.testcontainers:testcontainers:1.16.3",
        "org.testcontainers:postgresql:1.16.3",
        "org.gitlab4j:gitlab4j-api:4.17.0",
        "ch.qos.logback:logback-core:1.2.3",
        "org.slf4j:slf4j-api:1.7.30",
        "ch.qos.logback:logback-classic:1.2.3",
        "org.apache.commons:commons-lang3:3.12.0",
        "com.google.protobuf:protobuf-java:3.17.3",
        "io.grpc:grpc-netty:1.39.0",
        "io.grpc:grpc-protobuf:1.39.0",
        "io.grpc:grpc-stub:1.39.0",
        "io.grpc:grpc-api:1.39.0",
        "io.reflectoring.diffparser:diffparser:1.4",
    ] + IO_GRPC_GRPC_JAVA_ARTIFACTS,
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = [
        "http://maven.aliyun.com/nexus/content/groups/public/",
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

grpc_java_repositories()

#### docker

#### 安装rules_docker
http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "95d39fd84ff4474babaf190450ee034d958202043e366b9fc38f438c9e6c3334",
    strip_prefix = "rules_docker-0.16.0",
    urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.16.0/rules_docker-v0.16.0.tar.gz"],
)

load(
    "@io_bazel_rules_docker//repositories:repositories.bzl",
    container_repositories = "repositories",
)

container_repositories()

load("@io_bazel_rules_docker//repositories:deps.bzl", container_deps = "deps")

container_deps()

load(
    "@io_bazel_rules_docker//container:container.bzl",
    "container_pull",
)

container_pull(
    name = "java_base",
    # 'tag' is also supported, but digest is encouraged for reproducibility.
    digest = "sha256:deadbeef",
    registry = "gcr.io",
    repository = "distroless/java",
)

container_pull(
    name = "alpine_linux_amd64",
    registry = "index.docker.io",
    repository = "library/alpine",
    tag = "3.8",
)

container_pull(
    name = "openjdk8_alpine",
    registry = "index.docker.io",
    repository = "library/openjdk",
    tag = "8-jdk-alpine",
)
