# TOPPER MAVEN PLUGIN
____

This plugin allow to apply a header on top of all kind of files specified in config by extension
____
 
** Prerequisites **

1. java 8
1. mvn 3.5

____
  
** install the plugin **

In order to execute this goal (apply) it's mandatory to compile and install this java project on the machine where do you want to use the plugin 

>mvn clean install

____
  
** run the plugin **

 add the snippet below to the destination projwect pom.



			<plugin>
				<groupId>com.lab.gt</groupId>
				<artifactId>giallo-maven-plugin</artifactId>
				<version>1.0-SNAPSHOT</version>
				<configuration>
					<msg>  <!-- specify here the header text -->
/**
*  HEADER ******
*   ****** HEADER
*/	
					</msg>  <!-- specify here the extension -->
					<ext>java
					</ext>
				</configuration>
				<executions>
					<execution>
						<phase>clean</phase>
						<goals>
							<goal>apply</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
 
 
 then call the maven clean goal in the destination project pom to run the plugin against destination project files
 
> mvn clean 
 
 