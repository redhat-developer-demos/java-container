docker build -t rafabene/java-container:openjdk -f Dockerfile.openjdk .
docker build -t rafabene/java-container:openjdk-env -f Dockerfile.openjdk-env .
docker build -t rafabene/java-container:fabric8 -f Dockerfile.fabric8 .