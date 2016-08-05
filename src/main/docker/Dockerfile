FROM registry.access.redhat.com/jboss-fuse-6/fis-java-openshift:latest

USER root

ADD appdynamics/ /opt/appdynamics/

RUN chgrp -R 0 /opt/appdynamics/
RUN chmod -R g+rw /opt/appdynamics/
RUN find /opt/appdynamics/ -type d -exec chmod g+x {} +

#jboss from FIS
USER 185
