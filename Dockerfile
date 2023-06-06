FROM maven:3.9.1-amazoncorretto-20

# Set the working directory inside the container
WORKDIR /FamilyRestaurant_PostgresDocker/

# Copy the pom.xml file to the container
COPY pom.xml /FamilyRestaurant_PostgresDocker/

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY . /FamilyRestaurant_PostgresDocker

# Build the Maven project
RUN mvn clean install;

#RUN mvn exec:java -Dexec.mainClass="starter.menu.StarterMenu"
CMD mvn exec:java -Dexec.mainClass="starter.menu.StarterMenu"