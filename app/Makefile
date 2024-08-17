run-dist:
	./gradlew installDist
	./build/install/app/bin/app /home/selfhack/projects/hexlet/java-project-71/app/src/test/resources/json1.json /home/selfhack/projects/hexlet/java-project-71/app/src/test/resources/json2.json
build:
	./gradlew build checkstyleMain checkstyleTest
report:
	./gradlew jacocoTestReport

.PHONY: build