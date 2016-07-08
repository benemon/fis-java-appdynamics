FROM registry.access.redhat.com/jboss-fuse-6/fis-java-openshift:latest

USER root

ADD appdynamics/ /opt/appdynamics/

RUN chown -R 185:185 /opt/appdynamics/

#jboss from FIS
USER 185
