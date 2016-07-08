package com.redhat.bh.appdynamics.buildconfig;

import java.util.HashMap;
import java.util.Map;

import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.openshift.api.model.BuildTriggerPolicy;
import io.fabric8.openshift.api.model.DeploymentTriggerImageChangeParams;
import io.fabric8.openshift.api.model.ImageChangeTrigger;
import io.fabric8.openshift.api.model.TemplateBuilder;

public class BuildConfigKubernetesModelProcessor {

    public void on(TemplateBuilder builder) {
        builder.addNewBuildConfigObject()
                .withNewMetadata()
                    .withName("fis-java-appdynamics")
                    .withLabels(getLabels())
                .endMetadata()
                .withNewSpec()
                    .withTriggers(getTriggers())
                    .withNewSource()
                        .withNewGit()
                            .withUri("https://github.com/benemon/fis-java-appdynamics")
                        .endGit()
                        .withContextDir("src/main/docker")
                        .withType("Git")
                    .endSource()
                    .withNewStrategy()
                        .withNewDockerStrategy()
                            .withNewFrom()
                                .withKind("ImageStreamTag")
                                .withName("fis-java-openshift:latest")
                                .withNamespace("openshift")
                            .endFrom()
                        .endDockerStrategy()
                        .withType("Docker")
                    .endStrategy()
                    .withNewOutput()
                        .withNewTo()
                            .withKind("ImageStreamTag")
                            .withName("fis-java-appdynamics:latest")
                        .endTo()
                    .endOutput()
                .endSpec()
            .endBuildConfigObject()
            .build();
    }

    private BuildTriggerPolicy getTriggers() {
        ObjectReference from = new ObjectReference();
		from.setName("fis-java-openshift:latest");
        from.setKind("ImageStreamTag");
        from.setNamespace("openshift");

        ImageChangeTrigger imageChangeTrigger = new ImageChangeTrigger();
        imageChangeTrigger.setFrom(from);

        BuildTriggerPolicy policy = new BuildTriggerPolicy();
        policy.setType("ImageChange");
        policy.setImageChange(imageChangeTrigger);

        return policy;
    }

    private Map<String, String> getLabels() {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", "fis-java-appdynamics");
        labels.put("project", "fis-java-appdynamics");
        labels.put("version", "1.0.0-SNAPSHOT");
        labels.put("group", "openshift");

        return labels;
    }
}
