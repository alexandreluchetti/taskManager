FROM ubuntu:latest
WORKDIR /java
ADD . /java
# Define variáveis de ambiente
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV GRADLE_HOME /opt/gradle
ENV PATH ${GRADLE_HOME}/bin:${PATH}
ENV TZ=America/Sao_Paulo

# Instalações e configurações
RUN apt-get update && apt-get install -y openjdk-21-jdk wget unzip

# Baixa e instala o Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.6.1-bin.zip -P /tmp \
    && unzip /tmp/gradle-7.6.1-bin.zip -d /opt \
    && ln -s /opt/gradle-7.6.1 /opt/gradle

# Constroi o projeto utilizando Gradle
RUN gradle clean build -x test

# Define a variável de ambiente para o arquivo JAR
ENV JAR_FILE=task-manager-1.0.0.jar

# Comando padrão para executar quando o container iniciar
CMD ["sh", "-c", "java -jar build/libs/${JAR_FILE}"]