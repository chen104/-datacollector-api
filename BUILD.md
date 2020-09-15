<!---
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
--->

<img style="width:100%;" src="/github-banner.png">

[StreamSets](http://streamsets.com)

# Building StreamSets Data Collector

To build the Data Collector you will need the following software :

- Git 1.9+
- JDK 1.8+ (1.8, 11, or 14)
- Maven 3.6.3+

## Installing Java JDK via Homebrew
    brew cask install java<version>

    # latest version (Java 14)
    brew cask install java

    # LTS 11
    brew cask install java11

## Switch Java JDK via alias

Setup your JAVA_HOME path in your .zshrc or .bash_profile for your primary Java version and add an export for each
installed Java version.

    export JAVA_HOME=$(/usr/libexec/java_home -v11.8)
    export JAVA_8_HOME=$(/usr/libexec/java_home -v1.8)
    export JAVA_11_HOME=$(/usr/libexec/java_home -v11)
    export JAVA_14_HOME=$(/usr/libexec/java_home -v14)

Add an alias to your .zshrc or .bash_profile for each installed Java version.
The alias exports JAVA_HOME with the selected JAVA_VERSION_HOME.

    alias java8='export JAVA_HOME=$JAVA_8_HOME'
    alias java11='export JAVA_HOME=$JAVA_11_HOME'
    alias java14='export JAVA_HOME=$JAVA_14_HOME'


Follow these instructions to build the Data Collector :

- Get the latest code from github

`git clone http://github.com/streamsets/datacollector-api`

## Build

From within the DataCollector API directory, execute:

`mvn clean package`

## Install it locally

From within the DataCollector API directory, execute:

`mvn clean install`
