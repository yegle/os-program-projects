JAVA_HOME := $(shell echo $(JAVA_HOME))
TMP_FROM := $(shell mktemp)
TMP_TO := $(shell mktemp)

JAVAOPT := -classpath ".:../lib/commons-cli-1.2.jar:${JAVA_HOME}/jre/lib"
TESTS = test_1 test_2 test_3
MODULES = Copy.class libcopy.h libcopy.so

default: $(MODULES)

libcopy.h: Copy.java
	javah -jni $(JAVAOPT) -o libcopy.h Copy

test: $(TESTS)

doc: Copy.java
	javadoc $(JAVAOPT) Copy.java -d ../doc

clean:
	rm -rf $(MODULES) ../doc

%.so: %.c
	gcc -o $@ -shared -Wl,-soname,$@ \
		-I${JAVA_HOME}/include \
		-I${JAVA_HOME}/include/linux $< \
		-lc -fPIC

%.class: %.java
	javac $(JAVAOPT) $<

test_%: Copy.class libcopy.so
	LD_LIBRARY_PATH=. java $(JAVAOPT) Copy -m $* -f ${TMP_FROM} -t ${TMP_TO}

