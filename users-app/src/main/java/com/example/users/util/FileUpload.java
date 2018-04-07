package com.example.users.util;

import java.io.File;

import oracle.cloudstorage.ftm.CloudStorageClass;
import oracle.cloudstorage.ftm.FileTransferAuth;
import oracle.cloudstorage.ftm.FileTransferManager;
import oracle.cloudstorage.ftm.TransferResult;
import oracle.cloudstorage.ftm.UploadConfig;
import oracle.cloudstorage.ftm.exception.ClientException;

public class FileUpload {
	
	public void uploadFile(File file) {
		FileTransferAuth auth = new FileTransferAuth(
				"cloud.admin", // user name
				"Dreamy@1LUrE".toCharArray(), // password
				"Storage", // service name
				"https://gse00015177.storage.oraclecloud.com", // service URL
				"gse00015177" // identity domain
				);
		FileTransferManager manager = null;
		try {
			manager = FileTransferManager.getDefaultFileTransferManager(auth);
			String containerName = "_apaas";
			
			UploadConfig uploadConfig = new UploadConfig();
			uploadConfig.setOverwrite(true);
			uploadConfig.setStorageClass(CloudStorageClass.Standard);
			System.out.println("Uploading file " + file.getName() + " to container " + containerName);
			TransferResult uploadResult = manager.upload(uploadConfig, containerName, file.getName(), file);
			System.out.println("Upload completed successfully.");
			System.out.println("Upload result:" + uploadResult.toString());
			
		} catch(ClientException ce) {
			System.out.println("Upload failed. " + ce.getMessage());
		} finally {
			if (manager != null) {
				 manager.shutdown();
			}
		}
	}		 
}
