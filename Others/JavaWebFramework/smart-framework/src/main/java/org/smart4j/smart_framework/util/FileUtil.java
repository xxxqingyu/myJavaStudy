package org.smart4j.smart_framework.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public final class FileUtil {
	
	public static String getRealFileName(String fileName) {
		return FilenameUtils.getName(fileName);
	}
	
	public static File createFile(String filePath) {
		File file;
		try {
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if (!parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return file;
	}
}
