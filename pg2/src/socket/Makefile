JAVA_HOME := $(shell echo $(JAVA_HOME))

JAVAOPT := -classpath ".:${JAVA_HOME}/jre/lib"
MODULES = Client.class Server.class

default: $(MODULES)

.PHONY: clean 

clean:
	rm -rf $(MODULES) ../doc

%.class: %.java
	javac $(JAVAOPT) $<
