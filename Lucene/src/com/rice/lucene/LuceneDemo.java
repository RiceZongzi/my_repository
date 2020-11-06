package com.rice.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author wgz
 * @description
 * @date 2020/11/6 14:17
 */
public class LuceneDemo {

    final private static String INDEX_PATH = "index";

    final private static String FILE_PATH = "resource";

    private static String FILE_SEPARATOR = System.getProperty("file.separator");

    @Test
    public void createIndex() throws IOException {
        // 1、创建Directory对象，指定索引库保存位置。
        // 索引库保存在内存
//        Directory directory = new RAMDirectory();
        // 索引库保存在硬盘
        Directory directory = FSDirectory.open(new File(INDEX_PATH).toPath());

        // 2、基于Directory对象创建一个IndexWriter对象
        // IKAnalyzer，中文分词库
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, config);

        // 3、读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir = new File(FILE_PATH);
        // 预防空指针
//        File[] files = dir.listFiles();
        File[] files = Optional.ofNullable(dir.listFiles()).orElse(new File[]{});
        for (File file : files) {
            // 取文件名
            String fileName = file.getName();
            // 文件的路径
            String filePath = file.getPath();
            // 文件的内容
            String fileContent = FileUtils.readFileToString(file, "utf-8");
            // 文件的大小
            long fileSize = FileUtils.sizeOf(file);
            // 创建Field
            // 参数1：域的名称，参数2：域的内容，参数3：是否存储
            Field fieldName = new TextField("name", fileName, Field.Store.YES);
//            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldPath = new StoredField("path", filePath);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
//            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);
            Field fieldSizeValue = new LongPoint("size", fileSize);
            Field fieldSizeStore = new StoredField("size", fileSize);
            // 创建文档对象
            Document document = new Document();
            // 向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
//            document.add(fieldSize);
            document.add(fieldSizeValue);
            document.add(fieldSizeStore);
            // 5、把文档对象写入索引库
            indexWriter.addDocument(document);
        }
        // 6、关闭IndexWriter对象
        indexWriter.close();
    }
}
