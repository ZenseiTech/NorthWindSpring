## Microsoft Northwind Traders Backend API

This is going to be a backend for a web app written using the javascript library [w2ui.com/web].
This is to showcase how intuitive, lightweight and easy is to create web applications for the desktop.
I wouldn't recommend it for mobile though.

As a database is using a sample of the famous Microsoft NorthWind Traders database that was ported to SQLite

Technologies used are: Spring, SpringBoot, SpringData JPA

export JAVA_HOME="$HOME/apps/openlogic-openjdk-8u382-b05-linux-x64"

mvn clean install

java -Dspring.profiles.active=sqlite -jar target/Northwind-0.0.1-SNAPSHOT.jar
