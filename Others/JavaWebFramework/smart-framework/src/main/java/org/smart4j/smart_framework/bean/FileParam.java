package org.smart4j.smart_framework.bean;

import java.io.InputStream;

public class FileParam {
	private String fieldName;
	private String fileName;
	private long fileSize;
	private String contentType;
	private InputStream inpitStream;
	
	public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inpitStream) {
		super();
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inpitStream = inpitStream;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return the inpitStream
	 */
	public InputStream getInpitStream() {
		return inpitStream;
	}
	
	
}
