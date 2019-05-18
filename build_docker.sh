mvn clean package -DskipTests
docker build -t rafabene/java-container:openjdk9 -f Dockerfile.openjdk9 .
docker build -t rafabene/java-container:openjdk8 -f Dockerfile.openjdk8 .
docker build -t rafabene/java-container:openjdk12 -f Dockerfile.openjdk12 .
docker build -t rafabene/java-container:openjdk-env -f Dockerfile.openjdk-env .
docker build -t rafabene/java-container:fabric8 -f Dockerfile.fabric8 .
docker build -t rafabene/stress -f Dockerfile.stress .
