# Mobile Automation Framework

A Maven custom archetype to generate project tree structure for mobile automation

# How to run

1. Download the framework.
2. Navigate to root of project.
3. run command 'mvn install archetype:update-local-catalog'
5. run command 'mvn archetype:generate -DarchetypeCatalog=local -DarchetypeArtifactId=AppiumJavaFramework -DarchetypeGroupId=MobileAutomation -DarchetypeVersion=1.0-SNAPSHOT'
6. follow the onscreen process and enter project groupId, artifactId, Snapshot version, packaging when prompted.