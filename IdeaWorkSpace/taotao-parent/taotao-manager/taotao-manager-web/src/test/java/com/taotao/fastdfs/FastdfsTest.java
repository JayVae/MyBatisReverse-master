package com.taotao.fastdfs;

import com.taotao.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @Author: Jay
 * @Date: Created in 21:01 2017/12/20
 * @Modified By:
 */
public class FastdfsTest {
    @Test
    public void  testUpload() throws Exception {
        ClientGlobal.init("E:\\gitlab\\IdeaWorkSpace\\taotao-parent\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = storageClient.upload_file("C:\\Users\\dell\\Desktop\\tmp\\3.png", "png", null);
        for (String string : strings) {
            System.out.println(string);
        }

    }

    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient client = new FastDFSClient("E:\\gitlab\\IdeaWorkSpace\\taotao-parent\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        String uploadFile = client.uploadFile("C:\\Users\\dell\\Desktop\\tmp\\2.png", "png");
        System.out.println(uploadFile);
    }

}
