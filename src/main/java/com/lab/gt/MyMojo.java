package com.lab.gt;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Mojo(name = "apply")
public class MyMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project.basedir}/src/main/java", property = "sourceDir", required = true)
	private File sourceDir;

	@Parameter(property = "msg", defaultValue = " missing \n<plugin><executions><configuration>\n\t<msg>...message here .....\n\t</msg>\n</configuration>\n in pom file ", required = true)
	private String msg;

	@Parameter(property = "ext", defaultValue = ".java")
	private String ext;

	public void execute() throws MojoExecutionException {
		File souceDirFile = sourceDir;

		getLog().info(
				"\n************** just for files with extension " + ext
						+ " ,applying : \n" + msg + "\n**************");
		try {
			if (souceDirFile.exists()) {
				listFolderFilesRecursively(souceDirFile);
			} else {
				getLog().error(
						" missing ${project.basedir}/src/main/java folder?");
			}
		} catch (Exception e) {
			getLog().error("IO error: " + e.getMessage());
		}

	}

	private void spalma(File file) throws IOException {

		StringBuffer spalmex = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)))) {
			String line;
			if (br.ready()) {
				while ((line = br.readLine()) == null //
						|| line.isEmpty()//
						|| line.startsWith("/") //
						|| line.startsWith("*") //
						|| (ext.toLowerCase().endsWith("java") ? !line
								.startsWith("package ") : true)) {//
					// ignoring these rows, previous file manifest or invalid lines
				}

				while (line != null) {
					// rows to copy 
					getLog().info("sssss " + line);
					spalmex.append(line + "\n");
					line = br.readLine();
				}
			}
		}

		BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
		// appplying new file manifest
		writer.write(msg);
		writer.write("\n\n");
		writer.write(spalmex.toString());
		writer.close();

	}

	private void listFolderFilesRecursively(File fileName)
			throws FileNotFoundException, IOException {

		for (File file : fileName.listFiles()) {
			if (file.isDirectory()) {
				listFolderFilesRecursively(file);
			} else if (file.isFile() && file.getName().endsWith(ext)) {
				spalma(file);
			}
		}
	}

}
