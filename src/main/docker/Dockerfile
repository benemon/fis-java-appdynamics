FROM registry.access.redhat.com/jboss-fuse-6/fis-java-openshift:1.0

USER root

COPY ./configuration/oniguruma-5.9.5-3.el7.x86_64.rpm /tmp/oniguruma-5.9.5-3.el7.x86_64.rpm
COPY ./configuration/jq-1.5-1.el7.x86_64.rpm /tmp/jq-1.5-1.el7.x86_64.rpm

RUN yum install -y wget && \
    yum clean all -y && \
    rpm -ivh /tmp/oniguruma-5.9.5-3.el7.x86_64.rpm && \
    rpm -ivh /tmp/jq-1.5-1.el7.x86_64.rpm

#jboss from FIS
USER 185
