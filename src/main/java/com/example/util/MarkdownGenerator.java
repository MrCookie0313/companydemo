package com.example.util;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取mysql数据库下表的结构信息
 */
public class MarkdownGenerator {

    public static void main(String[] args) throws Exception {
        // 获取数据库下的所有表名称
        List<Table> tables = getAllTableName();
        // 获得表的建表语句
        buildTableComment(tables);
        // 获得表中所有字段信息
        buildColumns(tables);
        // 写文件
        write(tables);
    }

    /**
     * 写文件
     */
    private static void write(List<Table> tables) {
        for (Table table : tables) {
            System.out.println(table.getTableName());
            StringBuilder buffer = new StringBuilder();
            buffer.append("#### " + table.getTableName() + "\n");
//            buffer.append("**对象：**" + table.getObjectName() + "\n");
            buffer.append("**说明：** " + table.getComment() + "\n\n");
            buffer.append("------------\n");
            buffer.append("|序号|字段|字段名称|类型|计算规则|\n");
            buffer.append("|:-------|:-------|:-------|:-------|:-------|\n");
            List<Column> columns = table.getColumns();
            int seq = 1;
            for (Column column : columns) {
                String param = column.getParam();
                if ("del".equals(param) || "delDtm".equals(param)) continue;
                String type = column.getType();
                String comment = column.getComment();
                buffer.append("|" + seq++ +"|" + param +"|"+ ("".equals(comment) ? "" : comment)+ "|" + type + "| |\n");
            }
            buffer.append("\n");
            String content = buffer.toString();
            String path = System.getProperty("user.dir") + "/creator/txt/" + table.getObjectName() + ".txt";
            try {
                content = content.replaceAll("'", "\"");
                FileUtils.writeStringToFile(new File(path), content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接数据库
     */
    private static Connection getMySQLConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sensex?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false", "root", "root123");
        return conn;
    }

    /**
     * 获取当前数据库下的所有表名称
     */
    private static List<Table> getAllTableName() throws Exception {
        List<Table> tables = new ArrayList<>();
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        while (rs.next()) {
            String tableName = rs.getString(1);
            String objectName = camelCase(tableName);
            Table table = new Table(tableName, objectName);
            tables.add(table);
        }
        rs.close();
        stmt.close();
        conn.close();
        return tables;
    }

    /**
     * 获得某表的建表语句
     */
    private static void buildTableComment(List<Table> tables) throws Exception {
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();
        for (Table table : tables) {
            ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + table.getTableName());
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                String comment = parse(createDDL);
                table.setComment(comment);
            }
            if (rs != null) rs.close();
        }
        stmt.close();
        conn.close();
    }

    /**
     * 获得某表中所有字段信息
     */
    private static void buildColumns(List<Table> tables) throws Exception {
        Connection conn = getMySQLConnection();
        Statement stmt = conn.createStatement();
        for (Table table : tables) {
            List<Column> columns = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("show full columns from " + table.getTableName());
            if (rs != null) {
                while (rs.next()) {
                    String field = rs.getString("Field");
                    String type = rs.getString("Type");
                    String comment = rs.getString("Comment");
                    Column column = new Column(field, camelCase(field), type, comment);
                    columns.add(column);
                }
            }
            if (rs != null) {
                rs.close();
            }
            table.setColumns(columns);
        }
        stmt.close();
        conn.close();
    }

    /**
     * 返回注释信息
     */
    private static String parse(String all) {
        String comment;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    /**
     * 例如：employ_user_id变成employUserId
     */
    private static String camelCase(String str) {
        return str; //不做驼峰转变
//        String[] str1 = str.split("_");
//        int size = str1.length;
//        String str2;
//        StringBuilder str4 = null;
//        String str3;
//        for (int i = 0; i < size; i++) {
//            if (i == 0) {
//                str2 = str1[i];
//                str4 = new StringBuilder(str2);
//            } else {
//                str3 = initcap(str1[i]);
//                str4.append(str3);
//            }
//        }
//        return str4.toString();
    }

    /**
     * 把输入字符串的首字母改成大写
     */
    private static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}