docker run -it --rm --name maven-taxes -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.9.2-amazoncorretto-17-debian mvn clean install \
&& cp target/taxes-0.0.1-SNAPSHOT.war docker/ROOT.war \
&& docker image build -t codechallenge/taxes:latest docker/ \
&& docker run -it -p 8888:8080 --rm codechallenge/taxes:latest