# NBT Parser
If you would like to donate to SpoutDev: [Donate](https://flattr.com/submit/auto?user_id=spout&url=https://github.com/flow/nbt&title=Flow+NBT&language=Java&tags=github&category=software)

Named Binary Tag (NBT) library for Java based on flow nbt, based on Graham Edgecombe's JNBT library. NBT is a tag based binary format designed to carry large amounts of binary data with smaller amounts of additional data.

## Getting Started
* [Wiki](https://github.com/flow/examples/tree/master/nbt)

## Source Code
The source code can be found here on [GitHub](https://github.com/TheKodeToad/NBT-Parser). If you are using Git, use this command to clone the project:

    git clone https://github.com/TheKodeToad/NBT-Parser.git


## Test Dependencies
The following dependencies are only needed if you compiling the tests included with this project. Gotta test 'em all!
* [junit:junit](https://oss.sonatype.org/#nexus-search;gav~junit~junit~~~)

## Building from Source
This project can be built with maven using `mvn clean package`.

## Maven

```xml
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
```

```xml
	<dependency>
	    <groupId>com.github.TheKodeToad</groupId>
	    <artifactId>NBT-Parser</artifactId>
	    <version>1.0.1</version>
	</dependency>
```

## Credits
* [Spout](https://spout.org/) and contributors - *where we all began, and for much of the re-licensed code.*
* All the people behind [Java](http://www.oracle.com/technetwork/java/index.html), [Maven](https://maven.apache.org/), and [Gradle](https://www.gradle.org/).
* TheKodeToad
* natemort (provided region reader class file)
