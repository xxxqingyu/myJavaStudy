package org.smart4j.smart_framework.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.smart4j.smart_framework.bean.FileParam;
import org.smart4j.smart_framework.bean.FormParam;
import org.smart4j.smart_framework.bean.Param;
import org.smart4j.smart_framework.util.CollectionUtil;
import org.smart4j.smart_framework.util.FileUtil;
import org.smart4j.smart_framework.util.StringUtil;

public final class UploadHelper {
	private static ServletFileUpload servletFileUpload;
	
	public static void inti(ServletContext servletContext){
		File repository=(File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if (uploadLimit!=0) {
			servletFileUpload.setFileSizeMax(uploadLimit*1024*1024);
		}
	}
	
	/**
	 * 判断请求是否为multipart
	 * @param request
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
	
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList=new ArrayList<>();
		List<FileParam> fileParamList=new ArrayList<>();
		
		try {
			Map<String, List<FileItem>> fileItemListMap=servletFileUpload.parseParameterMap(request);
			if (CollectionUtil.isNotEmpty(fileItemListMap)) {
				for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()) {
					String fieldName = fileItemListEntry.getKey();
					List<FileItem> fileItemList = fileItemListEntry.getValue();
					if (CollectionUtil.isNotEmpty(fileItemList)) {
						for (FileItem fileItem : fileItemList) {
							if (fileItem.isFormField()) {
								String fieldValue=fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName, fieldValue));
							}else {
								String fileName=FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "Utf-8"));
								if (StringUtil.isNotEmpty(fileName)) {
									long fileSize=fileItem.getSize();
									String contentType=fileItem.getContentType();
									InputStream inputStream=fileItem.getInputStream();
									fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new Param(formParamList,fileParamList);
	}
}
