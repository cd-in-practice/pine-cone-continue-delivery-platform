create table cdp_apps (
  id                            varchar(32) not null,
  name                          varchar(64),
  namespace                     varchar(32),
  constraint pk_cdp_apps primary key (id)
);

create table cdp_app_configs (
  app_id                        varchar(255),
  namespace                     varchar(255),
  version_number                varchar(255),
  env_id                        varchar(255),
  env_name                      varchar(255),
  content                       varchar(255)
);

create table cdp_artifacts (
  artifact_type                 varchar(31) not null,
  id                            varchar(64) not null,
  name                          varchar(64),
  artifact_version              varchar(64),
  build_number                  integer not null,
  pipeline_id                   varchar(255),
  pipeline_history_id           varchar(255),
  pipeline_history_url          varchar(255),
  app_id                        varchar(32),
  namespace                     varchar(32),
  create_time                   datetime(6),
  updated_time                  datetime(6),
  raw_metadata                  longtext,
  docker_repo                   varchar(255),
  docker_tag                    varchar(64),
  java_repo_id                  varchar(128),
  java_group_id                 varchar(50),
  java_artifact_id              varchar(50),
  java_artifact_version         varchar(25),
  tar_url                       varchar(255),
  constraint pk_cdp_artifacts primary key (id)
);

create table cdp_repo_commits (
  id                            varchar(64) not null,
  short_id                      varchar(16),
  repo_id                       varchar(64),
  namespace                     varchar(32),
  json_src                      text,
  author_id                     varchar(255),
  added_lines                   integer not null,
  deleted_lines                 integer not null,
  changed_lines                 integer not null,
  message                       text,
  title                         text,
  commit_at                     datetime(6),
  create_time                   datetime(6),
  update_time                   datetime(6),
  year                          integer not null,
  month                         integer not null,
  date                          integer not null,
  hour                          integer not null,
  day_of_week                   integer not null,
  zone_id                       varchar(32),
  constraint pk_cdp_repo_commits primary key (id)
);

create table cdp_deploy_configs (
  app_id                        varchar(255),
  namespace                     varchar(255),
  env_id                        varchar(255),
  env_name                      varchar(255),
  version_number                varchar(255),
  content                       varchar(255)
);

create table cdp_deployment_history (
  number                        integer not null,
  app_id                        varchar(255),
  app_name                      varchar(255),
  artifact_id                   varchar(255),
  app_config_id                 varchar(255),
  app_config_version_number     varchar(255),
  deploy_config_id              varchar(255),
  deploy_config_version_number  varchar(255),
  env_id                        varchar(255),
  env_name                      varchar(255),
  duration                      bigint not null,
  deployment_time               datetime(6),
  deployment_end                datetime(6)
);

create table cdp_diffs (
  id                            varchar(64) not null,
  commit_id                     varchar(64),
  repo_id                       varchar(64),
  namespace                     varchar(32),
  diff_content                  text,
  deleted_file                  tinyint(1) default 0 not null,
  new_file                      tinyint(1) default 0 not null,
  renamed_file                  tinyint(1) default 0 not null,
  new_path                      varchar(128),
  old_path                      varchar(128),
  a_mode                        varchar(8),
  b_mode                        varchar(8),
  added_lines                   integer not null,
  deleted_lines                 integer not null,
  changed_lines                 integer not null,
  create_time                   datetime(6),
  update_time                   datetime(6),
  diff_bytes                    bigint not null,
  constraint pk_cdp_diffs primary key (id)
);

create table cdp_envs (
  id                            varchar(255),
  name                          varchar(255),
  order_num                     varchar(255),
  description                   varchar(255),
  namespace                     varchar(255)
);

create table cdp_commit_file_history (
  id                            varchar(255),
  repo_id                       varchar(255),
  author_id                     varchar(255),
  namespace                     varchar(32),
  commit_id                     varchar(255),
  diff_id                       varchar(255),
  file_path                     text,
  total_lines                   integer not null,
  extension                     varchar(32),
  added_lines                   integer not null,
  deleted_lines                 integer not null,
  changed_lines                 integer not null,
  year                          integer not null,
  month                         integer not null,
  date                          integer not null,
  hour                          integer not null,
  day_of_week                   integer not null
);

create table cdp_global_configs (
  namespace                     varchar(255),
  version_number                varchar(255),
  env_id                        varchar(255),
  env_name                      varchar(255),
  content                       varchar(255)
);

create table cdp_pipelines (
  id                            varchar(32) not null,
  constraint pk_cdp_pipelines primary key (id)
);

create table cdp_pipelines_repo_ids (
  cdp_pipelines_id              varchar(32) not null,
  value                         varchar(255) not null
);

create table cdp_pipeline_history (
  id                            varchar(32) not null,
  result                        varchar(7),
  url                           varchar(255),
  pipeline_id                   varchar(32),
  scm_commit                    varchar(255),
  pre_scm_commit                varchar(255),
  constraint pk_cdp_pipeline_history primary key (id)
);

create table cdp_repos (
  id                            varchar(255) not null,
  name                          varchar(64),
  namespace                     varchar(32),
  constraint pk_cdp_repos primary key (id)
);

create index ix_cdp_pipelines_repo_ids_cdp_pipelines_id on cdp_pipelines_repo_ids (cdp_pipelines_id);
alter table cdp_pipelines_repo_ids add constraint fk_cdp_pipelines_repo_ids_cdp_pipelines_id foreign key (cdp_pipelines_id) references cdp_pipelines (id) on delete restrict on update restrict;

