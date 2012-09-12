JAVA_HOME := $(shell echo $(JAVA_HOME))

JAVAOPT := -classpath ".:${JAVA_HOME}/jre/lib"
MODULES := $(wildcard *.java)

default: $(MODULES:.java=.class)

.PHONY: clean 

clean:
	rm -rf $(MODULES:.java=.class) doc

%.class: %.java
	javac $(JAVAOPT) $<

doc: $(MODULES)
	javadoc $(JAVAOPT) $(MODULES) -d ../doc
