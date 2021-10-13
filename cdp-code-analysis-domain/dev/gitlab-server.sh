docker run \
    --publish 8443:443 --publish 8080:80 --publish 2242:22 \
    --name gitlab \
    --volume $GITLAB_HOME/config:/etc/gitlab \
    --volume $GITLAB_HOME/logs:/var/log/gitlab \
    --volume $GITLAB_HOME/data:/var/opt/gitlab \
    gitlab/gitlab-ce:13.12.10-ce.0
