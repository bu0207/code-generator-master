package io.renren;

import io.renren.entity.ColumnEntity;
import io.renren.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenrenApplicationTests {

	@Test
	public void contextLoads() throws Exception {
		// 模板路径
		String templatePath = "F:\\template";
		// 模板名称
		String templateName = "Entity.java.vm";
		// 生成文件路径
		String outFilePath = "F:\\aa.java";

		//列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String columnName = "a" + i + "";
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setDataType("String1");
			columnEntity.setComments("String2");
			columnEntity.setAttrname(columnName);
			columnEntity.setAttrName("String4");
			columnEntity.setAttrType("String5");
			columnEntity.setExtra("String6");


			if (columnName.contains(".")) {
				columnName = columnName.substring(columnName.lastIndexOf(".") + 1);
			}
			columnEntity.setColumnName(columnName);


			columsList.add(columnEntity);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("tableName", "tablename111");
		map.put("comments", "comments111");
		map.put("pk", "pk111");
		map.put("className", "className111");
		map.put("classname", "classname111");
		map.put("pathName", "222");
		map.put("columns", columsList);
		map.put("hasList", "444");
		map.put("mainPath", "555");
		map.put("package", "666");
		map.put("moduleName", "7777");
		map.put("author", "8888");
		map.put("email", "9999");
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));


		getFile(templatePath,templateName,outFilePath,map);


	}


	/**
	 * 读取本地模板,生成文件
	 * @param templatePath  模板路径
	 * @param templateName 模板名称
	 * @param outFilePath 生成文件路径
	 * @param params 模板中填充参数
	 */
	public static void getFile(String templatePath, String templateName, String outFilePath,
							   Map<String, Object> params) {
		try {
			// 创建属性
			loadTemplateFileByTwo(templatePath);

			// 封装填充参数
			VelocityContext context = new VelocityContext(params);
			// 获取模板
			Template tpl = Velocity.getTemplate(templateName, "UTF-8");
			// 创建输出流
			Writer writer = new PrintWriter(new FileOutputStream(new File(outFilePath)));
			// 模板与数据填充
			tpl.merge(context, writer);
			// 刷新数据
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 加载配置文件方法二  加载绝对路径目录下的加载vm文件
	 *
	 * @param templatePath 模板路径
	 */
	public static void loadTemplateFileByTwo(String templatePath) {
		Properties p = new Properties();
		// 设置模板加载路径 为D盘 work文件夹
		p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templatePath);
		Velocity.init(p);
	}

}
