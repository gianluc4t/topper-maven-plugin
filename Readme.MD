add this snippet to proj pom to use 

			<plugin>
				<groupId>com.lab.gt</groupId>
				<artifactId>giallo-maven-plugin</artifactId>
				<version>1.0-SNAPSHOT</version>
				<configuration>
					<msg>
/**
* la pippo pippa ,
* pluto pluta !
*/	
					</msg>
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